package pages.api.pojo;

import base.BasePage;
import org.openqa.selenium.WebDriver;

public class MobilePage extends BasePage {

    private String courseTitle;
    private String price;

    protected MobilePage(WebDriver driver) {
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
