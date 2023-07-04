package tests;

import base.BaseTest;
import org.assertj.core.api.Assertions;
import org.testng.annotations.Test;

public class MainPageTest extends BaseTest {

    @Test
    public void pageOpenedTest() {

        String pageTitle = getDriver().getTitle();

        Assertions.assertThat(pageTitle).isEqualTo("99 Bottles of Beer | Start");
    }
}
