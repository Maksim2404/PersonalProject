package pages.api.ecommerce;

import base.BasePage;
import org.openqa.selenium.WebDriver;

public class LoginRequestPage extends BasePage {

    String userEmail;
    String userPassword;

    public LoginRequestPage(WebDriver driver) {
        super(driver);
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }
}
