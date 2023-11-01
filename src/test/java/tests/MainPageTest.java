package tests;

import base.BaseTest;
import org.assertj.core.api.Assertions;
import org.openqa.selenium.By;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import pages.MainPage;

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

        String pageTitle = getDriver().getTitle();

        Assertions.assertThat(pageTitle).isEqualTo("Your Ultimate Convenience Store for Food, Drink, Fuel, " +
                "and More | Wawa");
    }
}
