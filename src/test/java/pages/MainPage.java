package pages;

import base.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class MainPage extends BasePage {

    @FindBy(xpath = "//li//a[text()='Browse Languages']")
    private WebElement browseLanguagesTab;

    @FindBy(xpath = "//ul[@id='primary-menu']/li[contains(@class, 'menu-item')]")
    private List<WebElement> listOfElementsFromHeaderMenu;

    public MainPage(WebDriver driver) {
        super(driver);
    }

    public MainPage clickBrowseLanguagesTab() {

        click(browseLanguagesTab);
        return this;
    }

    public int checkQuantityOfAllHeaderMenuElements() {

        areAllElementsVisibleAndClickable(listOfElementsFromHeaderMenu);
        return getListSize(listOfElementsFromHeaderMenu);
    }
}
