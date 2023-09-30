package base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import java.lang.reflect.Method;
import java.time.Duration;

public abstract class BaseTest {

    /*private static final String BASE_URL;

    static {
        AtfConfig.readConfig();
        BASE_URL = AtfConfig.getAppUrl();
    }*/

    private WebDriver driver;
    private WebDriverWait webDriverWait;

    public static String getBaseUrl() {

        return System.getenv("APP_URL");
        /* return BASE_URL;*/
    }

    @BeforeSuite
    protected void beforeSuite(ITestContext context) {
        Reporter.log(ReportUtils.getReportHeader(context), true);
    }

    @BeforeMethod
    protected void beforeMethod(Method method, ITestResult result) {
        driver = BaseUtils.createDriver();
        openBaseUrl();

        Reporter.log(ReportUtils.END_LINE, true);
        Reporter.log("TEST RUN", true);
        Reporter.log(ReportUtils.getClassNameTestName(method, result), true);
    }

    @AfterMethod
    protected void afterMethod(Method method, ITestResult result) {

        Reporter.log(ReportUtils.getTestStatistics(method, result), true);
        driver.quit();
        webDriverWait = null;
    }

    protected WebDriver getDriver() {

        return driver;
    }

    protected WebDriverWait getWait() {
        if (webDriverWait == null) {
            webDriverWait = new WebDriverWait(driver, Duration.ofSeconds(10));
        }

        return webDriverWait;
    }

    public void openBaseUrl() {

        getDriver().navigate().to(getBaseUrl());
    }
}
