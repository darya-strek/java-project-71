package hexlet.code.formatters;

import java.util.Map;

public class Stylish {

    static final int WIDTH_OF_INDENT = 4;

    public static String format(Map<String, Map<String, Object>> diffMap) {
        StringBuilder result = new StringBuilder();
        result.append("{\n");

        for (String key : diffMap.keySet()) {
            Map<String, Object> valueMap = diffMap.get(key);
            Object status = valueMap.get("status");
            if (status.equals("deleted")) {
                result.append("  - ").append(key).append(": ").append(valueMap.get("value")).append("\n");
            } else if (status.equals("added")) {
                result.append("  + ").append(key).append(": ").append(valueMap.get("value")).append("\n");
            } else if (status.equals("changed")) {
                result.append("  - ").append(key).append(": ").append(valueMap.get("oldValue")).append("\n");
                result.append("  + ").append(key).append(": ").append(valueMap.get("newValue")).append("\n");
            } else if (status.equals("unchanged")) {
                result.append(" ".repeat(WIDTH_OF_INDENT)).append(key).append(": ")
                        .append(valueMap.get("value")).append("\n");
            } else {
                throw new RuntimeException("Unknown status: " + status);
            }
        }
        result.append("}");

        return result.toString();
    }
}
