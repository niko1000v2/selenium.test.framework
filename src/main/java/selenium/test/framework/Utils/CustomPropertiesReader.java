package selenium.test.framework.Utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class CustomPropertiesReader {

    public Properties loadProperties(String filePath) {
        Properties prop = new Properties();
        InputStream input = null;
        try {
            input = new FileInputStream(filePath);
            prop.load(input);
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return prop;
    }
}

