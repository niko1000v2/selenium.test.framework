package selenium.test.framework.Utils;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.util.Properties;

public class CustomScreenshot {

    private Properties p;

    public CustomScreenshot (Properties properties) {
        this.p = properties;
    }

    public void makeScreenshot(WebDriver driver, String name){
        File screenshot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        try {

            FileUtils.copyFile(screenshot, new File(
                    CustomFileUtils.getProjectPath() +
                    p.getProperty("screenshots.path") +
                    name + ".png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
