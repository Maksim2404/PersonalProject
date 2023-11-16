package pages.api;

import base.BasePage;
import io.restassured.path.json.JsonPath;
import org.openqa.selenium.WebDriver;

public class ReUsableMethods extends BasePage {
    protected ReUsableMethods(WebDriver driver) {
        super(driver);
    }

    public static JsonPath rawToJson(String response) {

        JsonPath js1 = new JsonPath(response);
        return js1;
    }
}
