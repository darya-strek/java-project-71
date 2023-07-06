package hexlet.code;

import java.util.Map;

public class Formatter {
    public static String format(Map<String, Map<String, Object>> diffMap, String format) {

        return switch (format) {
            case "stylish" -> formatStylish(diffMap);
            default -> throw new RuntimeException("Unexpected format: " + format);
        };
    }

    public static String formatStylish(Map<String, Map<String, Object>> diffMap) {
        StringBuilder result = new StringBuilder();
        result.append("{\n");

        for (String key : diffMap.keySet()) {
            Map<String, Object> map = diffMap.get(key);
            if (map.get("status").equals("deleted")) {
                result.append("  - ").append(key).append(": ").append(map.get("value")).append("\n");
            } else if (map.get("status").equals("added")) {
                result.append("  + ").append(key).append(": ").append(map.get("value")).append("\n");
            } else if (map.get("status").equals("changed")) {
                result.append("  - ").append(key).append(": ").append(map.get("oldValue")).append("\n");
                result.append("  + ").append(key).append(": ").append(map.get("newValue")).append("\n");
            } else if (map.get("status").equals("unchanged")) {
                result.append(" ".repeat(4)).append(key).append(": ").append(map.get("value")).append("\n");
            } else {
                throw new RuntimeException();
            }
        }
        result.append("}");

        return result.toString();
    }
}
