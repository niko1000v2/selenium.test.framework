package selenium.test.framework.Utils.Drivers;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import selenium.test.framework.Utils.CustomFileUtils;

import java.util.Properties;

public class CustomFirefoxDriver {

    private Properties p;

    public CustomFirefoxDriver (Properties properties){
        this.p = properties;
    }

    public WebDriver getDriver() {
        System.setProperty(
                "webdriver.gecko.driver",
                CustomFileUtils.getProjectPath() +
                        p.getProperty("drivers.path") +
                        p.getProperty("drivers.firefox"));
        return new FirefoxDriver();
    }

}
