package hexlet.code;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;

public class Differ {
    public static String generate(String filePath1, String filePath2, String format) throws IOException {

        String dataString1 = getData(filePath1);
        String dataString2 = getData(filePath2);

        String dataFormat1 = getDataFormat(filePath1);
        String dataFormat2 = getDataFormat(filePath2);

        Map<String, Object> dataMap1 = Parser.parse(dataString1, dataFormat1);
        Map<String, Object> dataMap2 = Parser.parse(dataString2, dataFormat2);

        Map<String, Map<String, Object>> diffMap = DifferCalc.generate(dataMap1, dataMap2);

        return Formatter.getFormat(diffMap, format);
    }

    public static String generate(String filePath1, String filePath2) throws IOException {
        return generate(filePath1, filePath2, "stylish");
    }

    public static String getData(String filePath) throws IOException {
        Path normalizedFilePath = Paths.get(filePath).toAbsolutePath().normalize();
        return Files.readString(normalizedFilePath);
    }

    public static String getDataFormat(String filePath) {
        int index = filePath.lastIndexOf('.');
        return index > 0 ? filePath.substring(index + 1) : "";
    }
}
