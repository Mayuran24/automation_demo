package helper;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigReader {
    private static Properties properties = new Properties();
    private static final Logger logger = LogManager.getLogger(ConfigReader.class);

    static {
        try {
            // Get the Maven system properties first
            String browser = System.getProperty("browser");  // browser from profile

            //Load environment dynamically from resources folder
            InputStream is = ConfigReader.class
                    .getClassLoader()
                    .getResourceAsStream("config-" + browser + ".properties");

            properties.load(is);

            // Override browser from Maven profile if provided
            if (browser != null) {
                properties.setProperty("browser", browser);
            }

            logger.info("Browser selected: {}", properties.getProperty("browser"));
        } catch (IOException e) {
            logger.error("Failed to load properties file", e);
        }
    }

    public static String getProperty(String key) {
        return properties.getProperty(key);
    }
}
