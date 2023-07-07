package hexlet.code;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Paths;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class AppTest {

    String result;

    public static String getAbsolutePath(String filePath) {
        return Paths.get("src", "test", "resources", filePath).toAbsolutePath().normalize().toString();
    }

    @BeforeEach
    public void beforeEach() {
        result = """
                {
                    chars1: [a, b, c]
                  - chars2: [d, e, f]
                  + chars2: false
                  - checked: false
                  + checked: true
                  - default: null
                  + default: [value1, value2]
                  - id: 45
                  + id: null
                  - key1: value1
                  + key2: value2
                    numbers1: [1, 2, 3, 4]
                  - numbers2: [2, 3, 4, 5]
                  + numbers2: [22, 33, 44, 55]
                  - numbers3: [3, 4, 5]
                  + numbers4: [4, 5, 6]
                  + obj1: {nestedKey=value, isNested=true}
                  - setting1: Some value
                  + setting1: Another value
                  - setting2: 200
                  + setting2: 300
                  - setting3: true
                  + setting3: none
                }""";
    }

    @Test
    void diffTestJsonExt() throws IOException {
        String json1 = getAbsolutePath("file1.json");
        String json2 = getAbsolutePath("file2.json");
        assertThat(Differ.generate(json1, json2)).isEqualTo(result);
    }

    @Test
    void diffTestYmlExt() throws IOException {
        String yml1 = getAbsolutePath("file1.yml");
        String yml2 = getAbsolutePath("file2.yml");
        assertThat(Differ.generate(yml1, yml2)).isEqualTo(result);
    }

    @Test
    void diffTestYamlExt() throws IOException {
        String yaml1 = getAbsolutePath("file1.yaml");
        String yaml2 = getAbsolutePath("file2.yaml");
        assertThat(Differ.generate(yaml1, yaml2)).isEqualTo(result);
    }

    @Test
    void diffTestDifferentExt() throws IOException {
        String json1 = getAbsolutePath("file1.json");
        String json2 = getAbsolutePath("file2.json");
        String yml1 = getAbsolutePath("file1.yml");
        String yml2 = getAbsolutePath("file2.yml");
        assertThat(Differ.generate(json1, yml2)).isEqualTo(result);
        assertThat(Differ.generate(yml1, json2)).isEqualTo(result);
    }

    @Test
    void diffTestOtherExt() throws IOException {
        String json1 = getAbsolutePath("file1.json");
        String otherExtension = getAbsolutePath("fileOtherExtension.txt");

        Throwable thrown = assertThrows(RuntimeException.class, () -> {
            Differ.generate(json1, otherExtension);
        });
        assertThat(thrown.getMessage()).isNotNull();
    }

    @Test
    void diffTestStylishFormat() throws IOException {
        String json1 = getAbsolutePath("file1.json");
        String json2 = getAbsolutePath("file2.json");
        assertThat(Differ.generate(json1, json2, "stylish")).isEqualTo(result);
    }

    @Test
    void diffTestPlainFormat() throws IOException {
        String resultPlain = """
                Property 'chars2' was updated. From [complex value] to false
                Property 'checked' was updated. From false to true
                Property 'default' was updated. From null to [complex value]
                Property 'id' was updated. From 45 to null
                Property 'key1' was removed
                Property 'key2' was added with value: 'value2'
                Property 'numbers2' was updated. From [complex value] to [complex value]
                Property 'numbers3' was removed
                Property 'numbers4' was added with value: [complex value]
                Property 'obj1' was added with value: [complex value]
                Property 'setting1' was updated. From 'Some value' to 'Another value'
                Property 'setting2' was updated. From 200 to 300
                Property 'setting3' was updated. From true to 'none'""";

        String json1 = getAbsolutePath("file1.json");
        String json2 = getAbsolutePath("file2.json");
        String yml1 = getAbsolutePath("file1.yml");
        String yml2 = getAbsolutePath("file2.yml");
        assertThat(Differ.generate(json1, json2, "plain")).isEqualTo(resultPlain);
        assertThat(Differ.generate(yml1, yml2, "plain")).isEqualTo(resultPlain);
    }

    @Test
    void diffTestJsonFormat() throws IOException {
        String resultJson = """
                {
                  "chars1" : {
                    "status" : "unchanged",
                    "value" : [ "a", "b", "c" ]
                  },
                  "chars2" : {
                    "status" : "changed",
                    "oldValue" : [ "d", "e", "f" ],
                    "newValue" : false
                  },
                  "checked" : {
                    "status" : "changed",
                    "oldValue" : false,
                    "newValue" : true
                  },
                  "default" : {
                    "status" : "changed",
                    "oldValue" : null,
                    "newValue" : [ "value1", "value2" ]
                  },
                  "id" : {
                    "status" : "changed",
                    "oldValue" : 45,
                    "newValue" : null
                  },
                  "key1" : {
                    "status" : "deleted",
                    "value" : "value1"
                  },
                  "key2" : {
                    "status" : "added",
                    "value" : "value2"
                  },
                  "numbers1" : {
                    "status" : "unchanged",
                    "value" : [ 1, 2, 3, 4 ]
                  },
                  "numbers2" : {
                    "status" : "changed",
                    "oldValue" : [ 2, 3, 4, 5 ],
                    "newValue" : [ 22, 33, 44, 55 ]
                  },
                  "numbers3" : {
                    "status" : "deleted",
                    "value" : [ 3, 4, 5 ]
                  },
                  "numbers4" : {
                    "status" : "added",
                    "value" : [ 4, 5, 6 ]
                  },
                  "obj1" : {
                    "status" : "added",
                    "value" : {
                      "nestedKey" : "value",
                      "isNested" : true
                    }
                  },
                  "setting1" : {
                    "status" : "changed",
                    "oldValue" : "Some value",
                    "newValue" : "Another value"
                  },
                  "setting2" : {
                    "status" : "changed",
                    "oldValue" : 200,
                    "newValue" : 300
                  },
                  "setting3" : {
                    "status" : "changed",
                    "oldValue" : true,
                    "newValue" : "none"
                  }
                }""";

        String json1 = getAbsolutePath("file1.json");
        String json2 = getAbsolutePath("file2.json");
        String yml1 = getAbsolutePath("file1.yml");
        String yml2 = getAbsolutePath("file2.yml");
        assertThat(Differ.generate(json1, json2, "json")).isEqualTo(resultJson);
        assertThat(Differ.generate(yml1, yml2, "json")).isEqualTo(resultJson);
    }

}
