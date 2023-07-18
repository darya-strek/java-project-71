package hexlet.code;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class AppTest {
    private static String resultStylish;
    private static String resultPlain;
    private static String resultJson;

    private static Path getFixturePath(String fileName) {
        return Paths.get("src", "test", "resources", "fixtures", fileName).toAbsolutePath().normalize();
    }

    private static String readFixture(String fileName) throws Exception {
        Path filePath = getFixturePath(fileName);
        return Files.readString(filePath).trim();
    }

    @BeforeAll
    public static void beforeAll() throws Exception {
        resultStylish = readFixture("result_stylish.txt");
        resultPlain = readFixture("result_plain.txt");
        resultJson = readFixture("result_json.txt");
    }

    @ParameterizedTest
    @ValueSource(strings = {"json", "yml", "yaml"})
    public void generateTest(String format) throws Exception {
        String filePath1 = getFixturePath("file1." + format).toString();
        String filePath2 = getFixturePath("file2." + format).toString();

        assertThat(Differ.generate(filePath1, filePath2, "stylish")).isEqualTo(resultStylish);
        assertThat(Differ.generate(filePath1, filePath2, "plain")).isEqualTo(resultPlain);
        assertThat(Differ.generate(filePath1, filePath2, "json")).isEqualTo(resultJson);
        assertThat(Differ.generate(filePath1, filePath2)).isEqualTo(resultStylish);
    }

    @Test
    void diffTestWrongFormat() {
        String json1 = getFixturePath("file1.json").toString();
        String wrongExtension = getFixturePath("fileOtherExtension.txt").toString();

        Throwable thrown = assertThrows(RuntimeException.class, () -> {
            Differ.generate(json1, wrongExtension, "stylish");
        });
        assertThat(thrown.getMessage()).isNotNull();
    }

    @Test
    void diffTestStraightPath() throws Exception {
        String json1 = "src/test/resources/fixtures/file1.json";
        String json2 = "src/test/resources/fixtures/file2.json";
        assertThat(Differ.generate(json1, json2)).isEqualTo(resultStylish);
    }

}
