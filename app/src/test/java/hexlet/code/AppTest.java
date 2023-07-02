package hexlet.code;

import org.junit.jupiter.api.Test;
// import org.junit.jupiter.api.Assertions;

import java.io.IOException;
import java.nio.file.Paths;

import static org.assertj.core.api.Assertions.assertThat;


public class AppTest {

    public static String getAbsolutePath(String filePath) {
        return Paths.get("src", "test", "resources", filePath).toAbsolutePath().normalize().toString();
    }

    @Test
    void diffTestJson() throws IOException {
        String file1 = getAbsolutePath("file1.json");
        String file2 = getAbsolutePath("file2.json");
        String result = """
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

        assertThat(Differ.generate(file1, file2)).isEqualTo(result);
    }

}
