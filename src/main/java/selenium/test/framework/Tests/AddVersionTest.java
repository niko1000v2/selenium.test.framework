package selenium.test.framework.Tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import selenium.test.framework.Pages.AddVersionPage;
import selenium.test.framework.Pages.DashboardPage;
import selenium.test.framework.Pages.VersionsPage;
import selenium.test.framework.Utils.Data.CsvDataReader;

import java.util.Date;

public class AddVersionTest extends AbstractTest {

    private VersionsPage versionsPage;
    private AddVersionPage addVersionPage;

    @BeforeClass
    public void setUpClass(){
        DashboardPage dashboardPage = new DashboardPage(driver);
        versionsPage = dashboardPage
                .goToVersionsPage();
    }

    @BeforeMethod
    public void setUpMethod() {
        addVersionPage = versionsPage
                .goToAddVersionPage();
    }

    @DataProvider(name = "addVersionData")
    public Object[][] addVersionData (){
        return new CsvDataReader(p).getCsvData("addVersionData.csv");
    }

    @Test (dataProvider = "addVersionData")
    public void successAddVersionTest(String name) {
        VersionsPage versionsPage = addVersionPage
                .fillAddVersionForm(
                        name + "_" + new Date().getTime(),
                        dataGenerator.getRandomText(100))
                .submitAddVersionForm();

        Assert.assertTrue(versionsPage.isInfoBoxDisplayed());
    }
}