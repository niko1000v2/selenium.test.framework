package selenium.test.framework.Tests;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.*;
import selenium.test.framework.Utils.*;
import selenium.test.framework.Utils.Data.DataGenerator;
import selenium.test.framework.Utils.Drivers.DriverFactory;

import java.io.File;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.Properties;

public abstract class AbstractTest {

    protected static WebDriver driver;
    protected static CustomScreenshot customScreenshot;
    protected static Logger log;
    protected static ExtentReports extent;
    protected static ExtentTest test;
    protected static DataGenerator dataGenerator;

    protected static Properties p;

    @Parameters({"properties"})
    @BeforeSuite
    public void setUpSuite(@Optional("properties") String properties) {

        p = new CustomPropertiesReader().loadProperties(
                !properties.equalsIgnoreCase("properties") ?
                        new CustomFileUtils().getResourcesPath(properties) :
                        new CustomFileUtils().getResourcesPath("baseConfiguration.properties"));

        if (Boolean.parseBoolean(p.getProperty("screenshots.delete"))) {
            new CustomFileUtils().deleteDirectory(new File(
                    CustomFileUtils.getProjectPath() +
                            p.getProperty("screenshots.path")));
        }
        dataGenerator = new DataGenerator();
        customScreenshot = new CustomScreenshot(p);

        extent = new ExtentReports(
                CustomFileUtils.getProjectPath()
                        + p.getProperty("reports.path") +
                        "report.html", true);

        driver = new DriverFactory(p).getConfiguredDriver(p.getProperty("base.browser"));
        driver.get(p.getProperty("base.url"));
    }

    @AfterSuite
    public void tearDownSuite() {
        if (test.getEndedTime() == null){
            extent.endTest(test);
        }
        extent.flush();
        extent.close();
        driver.quit();
    }

    @AfterMethod
    public void tearDownMethod(ITestResult result) {

        String formattedDate = new CustomDate(p).getFormattedDate(new Date());

        String makeScreenshot = p.getProperty("screenshots.make");
        String writeLogs = p.getProperty("logs.write");

        if (result.getStatus() == ITestResult.FAILURE) {
            if (makeScreenshot.equalsIgnoreCase("onFail") ||
                    makeScreenshot.equalsIgnoreCase("always")) {
                customScreenshot.makeScreenshot(
                        driver,
                        result.getMethod().getMethodName() + "_" +
                                "FAIL" + "_" + formattedDate);
            }
            if (writeLogs.equalsIgnoreCase("onFail") ||
                    writeLogs.equalsIgnoreCase("always")) {
                log.error("FAIL" + " : " + result.getThrowable());
            }
            test.log(LogStatus.FAIL, "FAIL - " + result.getMethod().getMethodName() +
                    test.addScreenCapture(
                            "../" + p.getProperty("screenshots.path") +
                                    result.getMethod().getMethodName() + "_" +
                                    "FAIL" + "_" + formattedDate + ".png"));
        } else if (result.getStatus() == ITestResult.SUCCESS) {
            if (makeScreenshot.equalsIgnoreCase("always")) {
                customScreenshot.makeScreenshot(
                        driver,
                        result.getMethod().getMethodName() + "_" +
                                "PASS" + "_" + formattedDate);
            }
            if (writeLogs.equalsIgnoreCase("always")) {
                log.info("PASS");
            }
            test.log(LogStatus.PASS, "PASS - " + result.getMethod().getMethodName());
        } else {
            if (writeLogs.equalsIgnoreCase("always")) {
                log.info("OTHER");
            }
            test.log(LogStatus.SKIP, "SKIP - " + result.getMethod().getMethodName());
        }

        extent.endTest(test);
    }

    @BeforeMethod
    public void setUpMethod(Method method) {
        log = LogManager.getLogger(this.getClass().getName() +
                " - " + method.getName());

        test = extent.startTest(method.getName());
    }
}
