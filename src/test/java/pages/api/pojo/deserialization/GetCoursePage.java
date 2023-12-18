package pages.api.pojo.deserialization;

import base.BasePage;
import org.openqa.selenium.WebDriver;
import pages.api.pojo.deserialization.CoursesPage;

public class GetCoursePage extends BasePage {

    private String url;
    private String services;
    private String expertise;
    private CoursesPage Courses;
    private String instructor;
    private String linkedin;

    protected GetCoursePage(WebDriver driver) {
        super(driver);
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getServices() {
        return services;
    }

    public void setServices(String services) {
        this.services = services;
    }

    public String getExpertise() {
        return expertise;
    }

    public void setExpertise(String expertise) {
        this.expertise = expertise;
    }

    public CoursesPage getCourses() {
        return Courses;
    }

    public void setCourses(CoursesPage courses) {
        Courses = courses;
    }

    public String getInstructor() {
        return instructor;
    }

    public void setInstructor(String instructor) {
        this.instructor = instructor;
    }

    public String getLinkedin() {
        return linkedin;
    }

    public void setLinkedin(String linkedin) {
        this.linkedin = linkedin;
    }
}
