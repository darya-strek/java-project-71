package hexlet.code;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

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



        ObjectMapper mapper = new ObjectMapper();
        Map<String, Object> data1 = mapper.readValue(file1, new TypeReference<TreeMap<String, Object>>() { });
        Map<String, Object> data2 = mapper.readValue(file2, new TypeReference<TreeMap<String, Object>>() { });
    }

    public static String getExtension(String filePath) {
        line.substring(line.indexOf('\"') + 1, line.lastIndexOf('\"')))
    }
}
