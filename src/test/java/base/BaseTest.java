package base;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
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

    private WebDriver driver;
    private WebDriverWait webDriverWait;
    private final static String BASE_URL = "https://www.yusen-logistics.com/us_en/";

    public static String getBaseUrl() {

        return BASE_URL;
    }

    @BeforeSuite
    protected void beforeSuite(ITestContext context) {
        Reporter.log(ReportUtils.getReportHeader(context), true);
    }

    @BeforeMethod
    protected void beforeMethod(Method method, ITestResult result) {
        driver = BaseUtils.createDriver();
        openBaseUrl();
        closePopupIfPresent();

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
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(25));
    }

    protected void closePopupIfPresent() {

        WebElement closePopUpButton = getDriver().findElement(
                By.xpath("//div[@id='onetrust-banner-sdk']//button[contains(@class, 'banner-close-button')]"));
        try {
            WebElement closeButton = getWait().until(ExpectedConditions.elementToBeClickable(closePopUpButton));
            closeButton.click();
            System.out.println("Popup closed successfully.");
        } catch (TimeoutException e) {
            System.out.println("Popup was not present or not clickable within 10 seconds.");
        } catch (WebDriverException e) {
            System.out.println("Error occurred while trying to close the popup: " + e.getMessage());
        }
    }
}
