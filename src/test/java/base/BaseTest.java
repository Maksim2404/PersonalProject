package base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import java.lang.reflect.Method;
import java.time.Duration;

public abstract class BaseTest {

    private final static String BASE_URL = "https://99-bottles-of-beer.net/";

    private WebDriver driver;
    private WebDriverWait webDriverWait;

    public static String getBaseUrl() {

        return BASE_URL;
    }

    @BeforeMethod
    protected void beforeMethod(Method method, ITestResult result) {

        driver = BaseUtils.createDriver();
    }

    @AfterMethod
    protected void afterMethod(Method method, ITestResult result) {

        driver.quit();
        webDriverWait = null;
    }

    protected WebDriver getDriver() {

        return driver;
    }

    protected WebDriverWait getWait() {
        if (webDriverWait == null) {
            webDriverWait = new WebDriverWait(driver, Duration.ofSeconds(20));
        }

        return webDriverWait;
    }

    public void sleep(int millis) throws InterruptedException {
        Thread.sleep(millis);
    }

    public void openBaseUrl() {

        getDriver().navigate().to(BASE_URL);
    }
}
