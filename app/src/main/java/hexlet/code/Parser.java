package hexlet.code;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;

import java.io.IOException;
import java.util.Map;
import java.util.TreeMap;

public class Parser {
    public static Map<String, Object> parse(String data, String dataFormat) throws IOException {
        ObjectMapper mapper = switch (dataFormat) {
            case "json" -> new ObjectMapper();
            case "yaml", "yml" -> new YAMLMapper();
            default -> throw new RuntimeException("Unexpected value: " + dataFormat);
        };
        return mapper.readValue(data, new TypeReference<TreeMap<String, Object>>() { });
    }
}
