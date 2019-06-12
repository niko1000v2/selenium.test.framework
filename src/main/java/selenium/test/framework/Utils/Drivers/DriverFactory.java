package selenium.test.framework.Utils.Drivers;

import org.openqa.selenium.WebDriver;

import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class DriverFactory {

    private Properties p;

    public DriverFactory (Properties properties){
        this.p = properties;
    }

    public WebDriver getDriver(String driverType){
        if (driverType.equalsIgnoreCase("chrome")){
            return new CustomChromeDriver(p).getDriver();
        } else if (driverType.equalsIgnoreCase("firefox")){
            return new CustomFirefoxDriver(p).getDriver();
        } else if (driverType.equalsIgnoreCase("ie")){
            return new CustomIeDriver(p).getDriver();
        } else {
            return new CustomIeDriver(p).getDriver();
        }
    }

    public WebDriver getConfiguredDriver(String driverType){
        WebDriver driver = getDriver(driverType);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        return driver;
    }
}
