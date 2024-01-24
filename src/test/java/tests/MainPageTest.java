package tests;

import base.BaseTest;
import org.assertj.core.api.Assertions;
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
}
