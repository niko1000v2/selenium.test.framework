package selenium.test.framework.Tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import selenium.test.framework.Pages.DashboardPage;
import selenium.test.framework.Pages.LoginPage;

public class LoginTest extends AbstractTest {

    @Test
    public void successLoginTest() {
        LoginPage loginPage = new LoginPage(driver);

        DashboardPage dashboardPage = loginPage
                .fillLoginForm(
                        p.getProperty("base.admin.email"),
                        p.getProperty("base.admin.password"))
                .submitLoginForm();

        Assert.assertTrue(dashboardPage.isLogoutButtonDisplayed());
    }

//  Alternatywny zapis metody successLoginTest z zastosowaniem
//  full fluent interface
//
//    @Test
//    public void successLoginTest() {
//        Assert.assertTrue(
//                new LoginPage(driver)
//                .fillLoginForm(
//                  BaseProperties.ADMIN_EMAIL,
//                  BaseProperties.ADMIN_PASSWORD)
//                .submitLoginForm()
//                .isLogoutButtonDisplayed());
//    }
}
