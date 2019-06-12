package selenium.test.framework.Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class VersionsPage extends AbstractPage {

    @FindBy(xpath = "//*[@id=\"j_info_box\"]")
    private WebElement infoBox;

    @FindBy(xpath = "//*[@id=\"content\"]/article/nav/ul/li/a")
    private WebElement addVersionPageButton;

    public VersionsPage(WebDriver driver){
        super(driver);
    }

    public AddVersionPage goToAddVersionPage(){
        clickElement(addVersionPageButton);
        return new AddVersionPage(driver);
    }

    public boolean isInfoBoxDisplayed(){
        return isElementDisplayed(infoBox);
    }
}
