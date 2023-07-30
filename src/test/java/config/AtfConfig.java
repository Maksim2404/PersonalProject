package config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class AtfConfig {
    private static Properties properties;

    public static void readConfig() {
        properties = new Properties();
        try {
            InputStream inputStream = AtfConfig.class.getClassLoader().getResourceAsStream("local.properties");
            properties.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getAppUrl() {
        return properties.getProperty("appUrl");
    }
}
