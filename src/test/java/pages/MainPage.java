package pages;

import base.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class MainPage extends BasePage {

    @FindBy(xpath = "(//div[@class='header-navigation']//ul[contains(@class, 'main-navigation-ul')])[1]/li[contains(@class, 'main-navigation-li')]")
    private List<WebElement> listOfElementsFromHeaderMenu;

    @FindBy(xpath = "//div[@class='nav-item dropdown country-switcher']")
    private WebElement locationDropDownMenu;

    @FindBy(xpath = "//input[@aria-label='Search']")
    private WebElement locationSearchField;

    public MainPage(WebDriver driver) {
        super(driver);
    }

    public int checkQuantityOfAllHeaderMenuElements() {

        areAllElementsVisibleAndClickable(listOfElementsFromHeaderMenu);
        return getListSize(listOfElementsFromHeaderMenu);
    }

    public MainPage clickLocationDropDownMenu() {

        click(locationDropDownMenu);
        return this;
    }

    public MainPage inputValueToLocationSearchField(String location) {

        input(location, locationSearchField);
        return this;
    }

    public MainPage selectGeneratedLocationFromDropDownMenu(String generatedElement) {

        WebElement dynamicElement = getDynamicElement("//ul[@class='selector-input-options']//li//span[text()='{placeholder}']", generatedElement);

        click(dynamicElement);
        return this;
    }
}
