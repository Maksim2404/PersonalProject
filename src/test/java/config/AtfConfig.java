package config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class AtfConfig {
    private static Properties properties;

    public static void init() {
        if (properties == null) {
            properties = new Properties();
            try {
                InputStream inputStream = AtfConfig.class.getClassLoader().getResourceAsStream("local.properties");
                if (inputStream == null) {
                    throw new IOException("The local.properties file not found in src/test/resources/ directory.");
                }
                properties.load(inputStream);
            } catch (IOException e) {
                e.printStackTrace();
                System.exit(1);
            }
        }
    }

    public static String getAppUrl() {
        if (properties == null) {
            init();
        }
        return properties.getProperty("appUrl");
    }
  /*  public static void readConfig() {
        properties = new Properties();
        try {
            InputStream inputStream = AtfConfig.class.getClassLoader().getResourceAsStream("local.properties");
            properties.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }*/

 /*   public static String getAppUrl() {
        return properties.getProperty("appUrl");
    }*/
}
