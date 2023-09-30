package pages;

import base.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MainPage extends BasePage {

    @FindBy(xpath = "//li//a[text()='Browse Languages']")
    private WebElement browseLanguagesTab;

    public MainPage(WebDriver driver) {
        super(driver);
    }

    public MainPage clickBrowseLanguagesTab() {
        click(browseLanguagesTab);

        return this;
    }
}
