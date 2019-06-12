package selenium.test.framework.Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class EnvironmentsPage extends AbstractPage {

    @FindBy(xpath = "//*[@id=\"j_info_box\"]")
    private WebElement infoBox;

    @FindBy(xpath = "//html/body/div[1]/section/article/nav/ul/li/a")
    private WebElement addEnvironmentPageButton;

    public EnvironmentsPage (WebDriver driver){
        super(driver);
    }

    public AddEnvironmentPage goToAddEnvironmentPage(){
        clickElement(addEnvironmentPageButton);
        return new AddEnvironmentPage(driver);
    }

    public boolean isInfoBoxDisplayed(){
        return isElementDisplayed(infoBox);
    }
}
