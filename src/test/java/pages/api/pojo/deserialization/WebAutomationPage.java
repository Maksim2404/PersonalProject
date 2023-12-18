package pages.api.pojo.deserialization;

import base.BasePage;
import org.openqa.selenium.WebDriver;

public class WebAutomationPage extends BasePage {

    private String courseTitle;
    private String price;

    protected WebAutomationPage(WebDriver driver) {
        super(driver);
    }

    public String getCourseTitle() {
        return courseTitle;
    }

    public void setCourseTitle(String courseTitle) {
        this.courseTitle = courseTitle;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
