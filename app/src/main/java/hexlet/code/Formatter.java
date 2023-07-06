package hexlet.code;

import java.util.Map;

public class Formatter {
    public static String format(Map<String, Map<String, Object>> diffMap, String format) {

        return switch (format) {
            case "stylish" -> Stylish.format(diffMap);
            default -> throw new RuntimeException("Unexpected format: " + format);
        };
    }
}
