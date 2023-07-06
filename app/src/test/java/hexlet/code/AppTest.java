package hexlet.code;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
// import org.junit.jupiter.api.Assertions;

import java.io.IOException;
import java.nio.file.Paths;

import static org.assertj.core.api.Assertions.assertThat;


public class AppTest {

    String result;

    public static String getAbsolutePath(String filePath) {
        return Paths.get("src", "test", "resources", filePath).toAbsolutePath().normalize().toString();
    }

    @BeforeEach
    public void beforeEach() {
        result = """
                {
                  - age: 30
                  - children: false
                  + children: true
                  + count of children: 2
                    id: 1
                  + key: 30
                    marriage: true
                    name: Alex
                  - password: [3, 5, 7]
                  + password: [1, 3, 5]
                  - second name: Smith
                }""";
    }

    @Test
    void diffTestJson() throws IOException {
        String json1 = getAbsolutePath("file1.json");
        String json2 = getAbsolutePath("file2.json");
        assertThat(Differ.generate(json1, json2)).isEqualTo(result);
    }

    @Test
    void diffTestYml() throws IOException {
        String yml1 = getAbsolutePath("file1.yml");
        String yml2 = getAbsolutePath("file2.yml");
        assertThat(Differ.generate(yml1, yml2)).isEqualTo(result);
    }

    @Test
    void diffTestYaml() throws IOException {
        String yaml1 = getAbsolutePath("file1.yaml");
        String yaml2 = getAbsolutePath("file2.yaml");
        assertThat(Differ.generate(yaml1, yaml2)).isEqualTo(result);
    }

    @Test
    void diffTestExt() throws IOException {
        String json1 = getAbsolutePath("file1.json");
        String json2 = getAbsolutePath("file2.json");
        String yml1 = getAbsolutePath("file1.yaml");
        String yml2 = getAbsolutePath("file2.yaml");
        assertThat(Differ.generate(json1, yml2)).isEqualTo(result);
        assertThat(Differ.generate(yml1, json2)).isEqualTo(result);
    }

}
