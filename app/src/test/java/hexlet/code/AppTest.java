package hexlet.code;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

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

    @Test
    void diffTestJsonFormatStylishFormatter() throws Exception {
        String json1 = getFixturePath("file1.json").toString();
        String json2 = getFixturePath("file2.json").toString();
        assertThat(Differ.generate(json1, json2, "stylish")).isEqualTo(resultStylish);
    }

    @Test
    void diffTestJsonFormatPlainFormatter() throws Exception {
        String json1 = getFixturePath("file1.json").toString();
        String json2 = getFixturePath("file2.json").toString();
        assertThat(Differ.generate(json1, json2, "plain")).isEqualTo(resultPlain);
    }

    @Test
    void diffTestJsonFormatJsonFormatter() throws Exception {
        String json1 = getFixturePath("file1.json").toString();
        String json2 = getFixturePath("file2.json").toString();
        assertThat(Differ.generate(json1, json2, "json")).isEqualTo(resultJson);
    }

    @Test
    void diffTestJsonFormatDefaultFormatter() throws Exception {
        String json1 = getFixturePath("file1.json").toString();
        String json2 = getFixturePath("file2.json").toString();
        assertThat(Differ.generate(json1, json2)).isEqualTo(resultStylish);
    }

    @Test
    void diffTestYamlFormatStylishFormatter() throws Exception {
        String yaml1 = getFixturePath("file1.yaml").toString();
        String yaml2 = getFixturePath("file2.yaml").toString();
        String yml1 = getFixturePath("file1.yml").toString();
        String yml2 = getFixturePath("file2.yml").toString();
        assertThat(Differ.generate(yaml1, yaml2, "stylish")).isEqualTo(resultStylish);
        assertThat(Differ.generate(yml1, yml2, "stylish")).isEqualTo(resultStylish);
    }

    @Test
    void diffTestYamlFormatPlainFormatter() throws Exception {
        String yaml1 = getFixturePath("file1.yaml").toString();
        String yaml2 = getFixturePath("file2.yaml").toString();
        String yml1 = getFixturePath("file1.yml").toString();
        String yml2 = getFixturePath("file2.yml").toString();
        assertThat(Differ.generate(yaml1, yaml2, "plain")).isEqualTo(resultPlain);
        assertThat(Differ.generate(yml1, yml2, "plain")).isEqualTo(resultPlain);
    }

    @Test
    void diffTestYamlFormatJsonFormatter() throws Exception {
        String yaml1 = getFixturePath("file1.yaml").toString();
        String yaml2 = getFixturePath("file2.yaml").toString();
        String yml1 = getFixturePath("file1.yml").toString();
        String yml2 = getFixturePath("file2.yml").toString();
        assertThat(Differ.generate(yaml1, yaml2, "json")).isEqualTo(resultJson);
        assertThat(Differ.generate(yml1, yml2, "json")).isEqualTo(resultJson);
    }

    @Test
    void diffTestYamlFormatDefaultFormatter() throws Exception {
        String yaml1 = getFixturePath("file1.yaml").toString();
        String yaml2 = getFixturePath("file2.yaml").toString();
        String yml1 = getFixturePath("file1.yml").toString();
        String yml2 = getFixturePath("file2.yml").toString();
        assertThat(Differ.generate(yaml1, yaml2)).isEqualTo(resultStylish);
        assertThat(Differ.generate(yml1, yml2)).isEqualTo(resultStylish);
    }


    @Test
    void diffTestDifferentFormatDifferentFormatter() throws Exception {
        String json1 = getFixturePath("file1.json").toString();
        String json2 = getFixturePath("file2.json").toString();
        String yml1 = getFixturePath("file1.yml").toString();
        String yml2 = getFixturePath("file2.yml").toString();

        assertThat(Differ.generate(json1, yml2, "stylish")).isEqualTo(resultStylish);
        assertThat(Differ.generate(yml1, json2, "stylish")).isEqualTo(resultStylish);

        assertThat(Differ.generate(json1, yml2, "plain")).isEqualTo(resultPlain);
        assertThat(Differ.generate(yml1, json2, "plain")).isEqualTo(resultPlain);

        assertThat(Differ.generate(json1, yml2, "json")).isEqualTo(resultJson);
        assertThat(Differ.generate(yml1, json2, "json")).isEqualTo(resultJson);

        assertThat(Differ.generate(json1, yml2)).isEqualTo(resultStylish);
        assertThat(Differ.generate(yml1, json2)).isEqualTo(resultStylish);
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
