package hexlet.code;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;
import java.util.Objects;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.Set;
import java.util.LinkedHashMap;
import java.util.stream.Collectors;

public class Differ {
    public static String generate(String filePath1, String filePath2, String format) throws IOException {



        Set<String> keys = new TreeSet<>();
        keys.addAll(data1.keySet());
        keys.addAll(data2.keySet());

        Map<String, Object> result = new LinkedHashMap<>();

        for (String key : keys) {

            if (data1.containsKey(key) && !data2.containsKey(key)) {
                result.put("  - " + key, data1.get(key));
            } else if (!data1.containsKey(key) && data2.containsKey(key)) {
                result.put("  + " + key, data2.get(key));
            } else if (!Objects.equals(data1.get(key), data2.get(key))) {
                result.put("  - " + key, data1.get(key));
                result.put("  + " + key, data2.get(key));
            } else {
                result.put("    " + key, data1.get(key));
            }
        }

        String strResult = result.entrySet().stream()
                .map(item -> item.getKey() + ": " + item.getValue())
                .collect(Collectors.joining("\n"));

        return "{\n" + strResult + "\n}";

    }

    public static String generate(String filePath1, String filePath2) throws IOException {
        return generate(filePath1, filePath2, "stylish");
    }


}
