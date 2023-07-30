package tests;

import base.BaseTest;
import org.assertj.core.api.Assertions;
import org.openqa.selenium.By;
import org.testng.annotations.Test;
import pages.MainPage;

public class MainPageTest extends BaseTest {

    @Test
    public void pageOpenedTest() {

        String pageTitle = getDriver().getTitle();

        Assertions.assertThat(pageTitle).isEqualTo("99 Bottles of Beer | Start");
    }

    @Test
    public void swichToBrowseLanguagesPageTest() {
        MainPage mainPage = new MainPage(getDriver());
        mainPage.clickBrowseLanguagesTab();

        String pageTextContext = getDriver().findElement(By.tagName("body")).getText().trim();
        String createdNewTitleName = "All languages starting with the letter A are shown, sorted by Language.";

        Assertions.assertThat(pageTextContext).contains(createdNewTitleName);
    }
}
