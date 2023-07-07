package hexlet.code;

import java.io.IOException;
import java.util.Map;
import java.util.Objects;
import java.util.TreeSet;
import java.util.Set;
import java.util.LinkedHashMap;

public class Differ {
    public static String generate(String filePath1, String filePath2, String format) throws IOException {

        Map<String, Object> data1 = Parser.parse(filePath1);
        Map<String, Object> data2 = Parser.parse(filePath2);

        Set<String> keys = new TreeSet<>();
        keys.addAll(data1.keySet());
        keys.addAll(data2.keySet());

        Map<String, Map<String, Object>> diffMap = new LinkedHashMap<>();

        for (String key : keys) {
            Map<String, Object> valueMap = new LinkedHashMap<>();
            if (data1.containsKey(key) && !data2.containsKey(key)) {
                valueMap.put("status", "deleted");
                valueMap.put("value", data1.get(key));
            } else if (!data1.containsKey(key) && data2.containsKey(key)) {
                valueMap.put("status", "added");
                valueMap.put("value", data2.get(key));
            } else if (!Objects.equals(data1.get(key), data2.get(key))) {
                valueMap.put("status", "changed");
                valueMap.put("oldValue", data1.get(key));
                valueMap.put("newValue", data2.get(key));
            } else {
                valueMap.put("status", "unchanged");
                valueMap.put("value", data1.get(key));
            }
            diffMap.put(key, valueMap);
        }
        return Formatter.format(diffMap, format);
    }

    public static String generate(String filePath1, String filePath2) throws IOException {
        return generate(filePath1, filePath2, "stylish");
    }
}
