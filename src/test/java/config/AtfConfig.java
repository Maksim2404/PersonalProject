package config;

public class AtfConfig {

    private static final String APP_URL_ENV_VARIABLE = "APP_URL";
    private static String appUrl;

    private AtfConfig() {
        // Private constructor to prevent instantiation
    }

    public static void init() {
        if (appUrl == null) {
            appUrl = System.getenv(APP_URL_ENV_VARIABLE);
            if (appUrl == null) {
                throw new RuntimeException("APP_URL environment variable not set.");
            }
        }
    }

    public static String getAppUrl() {
        String appUrl = System.getenv("APP_URL");
        System.out.println("APP_URL: " + appUrl);
        if (appUrl == null) {
            throw new RuntimeException("APP_URL environment variable is not set.");
        }
        return appUrl;
    }

    /*private static final String PROPERTIES_FILE = "/local.properties";
    private static Properties properties;

    private AtfConfig() {
        // Private constructor to prevent instantiation
    }

    public static void init() {
        if (properties == null) {
            properties = new Properties();
            try (InputStream inputStream = AtfConfig.class.getResourceAsStream(PROPERTIES_FILE)) {
                if (inputStream == null) {
                    throw new RuntimeException("Failed to load properties file. Resource not found.");
                }
                properties.load(inputStream);
            } catch (IOException e) {
                e.printStackTrace();
                throw new RuntimeException("Failed to load properties file.", e);
            }
        }
    }

    public static String getAppUrl() {
        if (properties == null) {
            init();
        }
        return properties.getProperty("appUrl");
    }*/
}
