package property;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * @author Alexander Diachenko.
 */
public class AppProperty {

    public static String getProperty(String property) throws IOException {
        Properties mainProperties = new Properties();
        FileInputStream file;
        String path = "./main.properties";
        file = new FileInputStream(path);
        mainProperties.load(file);
        file.close();
        return mainProperties.getProperty(property);
    }
}