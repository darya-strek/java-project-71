package hexlet.code.formatters;

import java.util.Map;

public class Stylish {
    public static String format(Map<String, Map<String, Object>> diffMap) {
        StringBuilder result = new StringBuilder();
        result.append("{\n");
        for (String key : diffMap.keySet()) {
            Map<String, Object> valueMap = diffMap.get(key);
            if (valueMap.get("status").equals("deleted")) {
                result.append("  - ").append(key).append(": ").append(valueMap.get("value")).append("\n");
            } else if (valueMap.get("status").equals("added")) {
                result.append("  + ").append(key).append(": ").append(valueMap.get("value")).append("\n");
            } else if (valueMap.get("status").equals("changed")) {
                result.append("  - ").append(key).append(": ").append(valueMap.get("oldValue")).append("\n");
                result.append("  + ").append(key).append(": ").append(valueMap.get("newValue")).append("\n");
            } else if (valueMap.get("status").equals("unchanged")) {
                result.append(" ".repeat(4)).append(key).append(": ").append(valueMap.get("value")).append("\n");
            } else {
                throw new RuntimeException();
            }
        }
        result.append("}");

        return result.toString();
    }
}
