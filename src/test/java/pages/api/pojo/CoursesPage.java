package pages.api.pojo;

import base.BasePage;
import org.openqa.selenium.WebDriver;

import java.util.List;

public class CoursesPage extends BasePage {

    private List<WebAutomationPage> webAutomation;
    private List<APIPage> api;
    private List<MobilePage> mobile;

    protected CoursesPage(WebDriver driver) {
        super(driver);
    }

    public List<WebAutomationPage> getWebAutomation() {
        return webAutomation;
    }

    public void setWebAutomation(List<WebAutomationPage> webAutomation) {
        this.webAutomation = webAutomation;
    }

    public List<APIPage> getApi() {
        return api;
    }

    public void setApi(List<APIPage> api) {
        this.api = api;
    }

    public List<MobilePage> getMobile() {
        return mobile;
    }

    public void setMobile(List<MobilePage> mobile) {
        this.mobile = mobile;
    }
}
