package tests;

import base.BaseTest;
import org.assertj.core.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import pages.MainPage;

import java.time.Duration;

public class MainPageTest extends BaseTest {

    @Test
    public void startPageTitleTest() {

        String pageTitle = getDriver().getTitle();

        WebDriverWait webDriverWait = new WebDriverWait(getDriver(), Duration.ofSeconds(15));
        webDriverWait.until(ExpectedConditions.titleContains(pageTitle));

        Assertions.assertThat(pageTitle).isEqualTo("Yusen Logistics | United States: Global Logistics & Supply Chain Management");
    }

    @Test
    public void checkQuantityElementsFromHeaderMenuTest() {

        final int expectedQuantityOfHeaderMenuElements = 6;

        MainPage mainPage = new MainPage(getDriver());
        int actualQuantityOfElements = mainPage.checkQuantityOfAllHeaderMenuElements();

        Assertions.assertThat(actualQuantityOfElements).isEqualTo(expectedQuantityOfHeaderMenuElements);
    }

    @Test
    public void changeLocationFromHeaderMenu() {

        final String location = MainPage.generateRandomLocation();

        MainPage mainPage = new MainPage(getDriver());
        mainPage
                .clickLocationDropDownMenu()
                .inputValueToLocationSearchField(location)
                .selectGeneratedLocationFromDropDownMenu(location);

        String pageTextContent = getDriver().findElement(By.tagName("body")).getText();
        Assertions.assertThat(pageTextContent).as("Check if the page contains the expected text").contains(location);
    }
}
