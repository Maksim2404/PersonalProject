package pages;

import base.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class MainPage extends BasePage {

    @FindBy(xpath = "//li//a[text()='Browse Languages']")
    private WebElement browseLanguagesTab;

    @FindBy(xpath = "(//div[@class='header-navigation']//ul[contains(@class, 'main-navigation-ul')])[1]/li[contains(@class, 'main-navigation-li')]")
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
