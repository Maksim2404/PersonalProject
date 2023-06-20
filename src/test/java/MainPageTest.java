import base.BaseTest;
import org.testng.annotations.Test;

public class MainPageTest extends BaseTest {

    @Test
    public void  newTest() {

        openBaseUrl();
        getDriver().getTitle();
        getDriver().navigate().to("https://redrover.school/");
    }
}
