package hexlet.code;

import java.util.Map;

import com.fasterxml.jackson.core.JsonProcessingException;
import hexlet.code.formatters.Json;
import hexlet.code.formatters.Stylish;
import hexlet.code.formatters.Plain;

public class Formatter {
    public static String format(Map<String, Map<String, Object>> diffMap, String format)
            throws JsonProcessingException {

        return switch (format) {
            case "stylish" -> Stylish.format(diffMap);
            case "plain" -> Plain.format(diffMap);
            case "json" -> Json.format(diffMap);
            default -> throw new RuntimeException("Unexpected format: " + format);
        };
    }
}
