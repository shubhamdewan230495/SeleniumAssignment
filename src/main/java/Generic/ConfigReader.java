package Generic;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {
    private final Properties properties;
    public ConfigReader() {
        String fileName = "config.properties";
        properties = new Properties();
        try (FileInputStream inputStream = new FileInputStream(fileName)) {
            properties.load(inputStream);
        } catch (IOException e) {
            System.err.println("Error reading configuration file: " + e.getMessage());
        }
    }
    public String getProperty(String key) {
        return properties.getProperty(key);
    }
}