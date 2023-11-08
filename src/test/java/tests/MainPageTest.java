package tests;

import base.BaseTest;
import org.assertj.core.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import pages.MainPage;

import java.time.Duration;

public class MainPageTest extends BaseTest {

    @Test
    @Ignore
    public void switchToBrowseLanguagesPageTest() {

        MainPage mainPage = new MainPage(getDriver())
                .clickBrowseLanguagesTab();

        String pageTextContext = getDriver().findElement(By.tagName("body")).getText().trim();
        String createdNewTitleName = "All languages starting with the letter A are shown, sorted by Language.";

        Assertions.assertThat(pageTextContext).contains(createdNewTitleName);
    }

    @Test
    public void startPageTitleTest() {

        openBaseUrl();
        String pageTitle = getDriver().getTitle();

        System.out.println(pageTitle);

        WebDriverWait  webDriverWait = new WebDriverWait(getDriver(), Duration.ofSeconds(15));
        webDriverWait.until(ExpectedConditions.titleContains(pageTitle));

        Assertions.assertThat(pageTitle).isEqualTo("Your Ultimate Convenience Store for Food, Drink, Fuel, and More | Wawa");
    }
}
