package hexlet.code;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;
import java.util.TreeMap;

public class Parser {
    public static Map<String, Object> parse(String filePath) throws IOException {

        Path normalizedFilePath = Paths.get(filePath).toAbsolutePath().normalize();
        String file = Files.readString(normalizedFilePath);

        String extension = getExtension(filePath);

        ObjectMapper mapper;

        switch (extension) {
            case "json":
                mapper = new ObjectMapper();
                break;
            case "yaml", "yml":
                mapper = new YAMLMapper();
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + extension);
        }
        return mapper.readValue(file, new TypeReference<TreeMap<String, Object>>() { });
    }

    public static String getExtension(String filePath) {
        return filePath.substring(filePath.indexOf('.') + 1);
    }
}
