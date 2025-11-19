package helper;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.devtools.v140.io.IO;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {
    private static Properties properties = new Properties();
    private static final Logger logger = LogManager.getLogger(ConfigReader.class);

    static {
        try {
            // Get the Maven system properties first
            String browser = System.getProperty("browser");  // browser from profile
            //String env = System.getProperty("env", "staging");   // default env = dev

            // Load environment-specific properties if needed
            String path = "src/test/resources/config-" + browser + ".properties";
            FileInputStream fis = new FileInputStream(path);
            properties.load(fis);
            logger.info("Loaded properties from {}", path);

            // Override browser from Maven profile if provided
            if (browser != null) {
                properties.setProperty("browser", browser);
            }

            logger.info("Browser selected: {}", properties.getProperty("browser"));
        }catch (IOException e){
            logger.error("Failed to load properties file", e);
            throw new RuntimeException("Failed to load config properties.");
        }
    }

    public static String getProperty(String key) {
        return properties.getProperty(key);
    }
}
