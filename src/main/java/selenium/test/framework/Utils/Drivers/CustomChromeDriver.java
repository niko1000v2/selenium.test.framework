package selenium.test.framework.Utils.Drivers;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import selenium.test.framework.Utils.CustomFileUtils;

import java.util.Properties;

public class CustomChromeDriver {

    private Properties p;

    public CustomChromeDriver (Properties properties){
        this.p = properties;
    }

    public WebDriver getDriver() {
        System.setProperty(
                "webdriver.chrome.driver",
                CustomFileUtils.getProjectPath() +
                        p.getProperty("drivers.path") +
                        p.getProperty("drivers.chrome"));
        return new ChromeDriver();
    }
}