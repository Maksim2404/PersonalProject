package pages.api.pojo;

import base.BasePage;
import org.openqa.selenium.WebDriver;

public class APIPage extends BasePage {

    private String courseTitle;
    private String price;
    protected APIPage(WebDriver driver) {
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
