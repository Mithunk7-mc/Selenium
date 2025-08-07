package utils;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.InputStream;

public class JsonUtils {

    private static JsonNode rootNode;

    static {
        ObjectMapper mapper = new ObjectMapper();
        try {
            // Load the file from the classpath (resources folder)
            InputStream is = JsonUtils.class.getClassLoader().getResourceAsStream("TestData/test_data.json");
            if (is == null) {
                throw new RuntimeException("JSON file not found in classpath!");
            }
            rootNode = mapper.readTree(is);
        } catch (Exception e) {
            throw new RuntimeException("Failed to load JSON test data: " + e.getMessage(), e);
        }
    }

    public static String get(String scenario, String key) {
        String value = rootNode.path(scenario).path(key).asText();
        System.out.println("Reading JSON [" + scenario + "][" + key + "] = " + value);
        return value;
    }
}

