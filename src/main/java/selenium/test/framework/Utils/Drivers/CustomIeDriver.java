package selenium.test.framework.Utils.Drivers;

import org.openqa.selenium.WebDriver;
import selenium.test.framework.Utils.CustomFileUtils;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;

import java.util.Properties;

public class CustomIeDriver {

    private Properties p;

    public CustomIeDriver(Properties properties){
        this.p = properties;
    }

    public WebDriver getDriver() {
        System.setProperty(
                "webdriver.ie.driver",
                CustomFileUtils.getProjectPath() +
                        p.getProperty("drivers.path") +
                        p.getProperty("drivers.ie"));

        InternetExplorerOptions options = new InternetExplorerOptions();
        //Ignoring security domains which are driven by bank policy and always get back to default state (different levels)
        options.introduceFlakinessByIgnoringSecurityDomains();

        //Faster start IE (blank page instead internal intranet page)
        options.withInitialBrowserUrl("about:blank");

        //Faster typing in forms, without that option, typing take 1-2 sec for one letter!
        options.requireWindowFocus();

        return new InternetExplorerDriver(options);
    }
}