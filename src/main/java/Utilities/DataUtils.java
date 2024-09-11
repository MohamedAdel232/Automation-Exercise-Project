package Utilities;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class DataUtils {
    // Test data folder path
    public static final String TEST_DATA_PATH = "src/test/resources/TestData/";

    // A methode to read data from json file
    public static String readDataFromJsonFile(String fileName, String field) {
        try {
            // Create a file reader to read from json file
            FileReader fileReader = new FileReader(TEST_DATA_PATH + fileName + ".json");
            // Parse json file
            JsonElement jsonElement = JsonParser.parseReader(fileReader);
            // Get specific field from json file (key)
            return jsonElement.getAsJsonObject().get(field).getAsString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    // A methode to read data from properties file
    public static String readDataFromProperties(String fileName, String key) throws IOException {
        Properties properties = new Properties();
        // Read from properties file and return a specific field (key)
        properties.load(new FileInputStream(TEST_DATA_PATH + fileName + ".properties"));
        return properties.getProperty(key);
    }
}
