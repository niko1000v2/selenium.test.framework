package selenium.test.framework.Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends AbstractPage {

    @FindBy(id = "email")
    private WebElement emailField;

    @FindBy(id = "password")
    private WebElement passwordField;

    @FindBy(id = "login")
    private WebElement submitButton;

    public LoginPage(WebDriver driver){
        super(driver);
    }

    public LoginPage fillLoginForm(String email, String password){
        fillElement(emailField,email);
        fillElement(passwordField,password);
        return this;
    }

    public DashboardPage submitLoginForm(){
        clickElement(submitButton);
        return new DashboardPage(driver);
    }
}
