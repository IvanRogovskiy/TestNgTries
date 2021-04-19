package AutoUni.config;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class ConfigLoader {

    private final Properties properties;
    private static final String CONFIG_FILE = "src/main/resources/config/config.properties";

    public ConfigLoader() {
        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader(CONFIG_FILE));
            properties = new Properties();
            try {
                properties.load(reader);
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            throw new RuntimeException("properties wasn't found");
        }
    }

    public String getProperty(String property) {
        String propertyValue = properties.getProperty(property);
         if (propertyValue != null) {
             return propertyValue;
         }  else throw new RuntimeException("property: " + property + "wasn't found");
    }


}
