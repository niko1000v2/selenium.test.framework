package selenium.test.framework.Tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import selenium.test.framework.Pages.AddEnvironmentPage;
import selenium.test.framework.Pages.DashboardPage;
import selenium.test.framework.Pages.EnvironmentsPage;

import java.util.Date;

public class AddEnvironmentTest extends AbstractTest {

    private AddEnvironmentPage addEnvironmentPage;

    @BeforeClass
    public void setUpClass() {
        DashboardPage dashboardPage = new DashboardPage(driver);
        addEnvironmentPage = dashboardPage
                .goToEnvironmentsPage()
                .goToAddEnvironmentPage();
    }

    @Test
    public void successAddEnvironmentTest() {
        EnvironmentsPage environmentsPage = addEnvironmentPage
                .fillAddEnvironmentForm(
                        "env_" + new Date().getTime(),
                        dataGenerator.getRandomText(100))
                .submitAddEnvironmentForm();

        Assert.assertTrue(environmentsPage.isInfoBoxDisplayed());
    }
}