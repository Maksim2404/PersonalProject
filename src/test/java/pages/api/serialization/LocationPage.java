package pages.api.serialization;

import base.BasePage;
import org.openqa.selenium.WebDriver;

public class LocationPage extends BasePage {

    private double lat;
    private double lng;

    public LocationPage(WebDriver driver) {
        super(driver);
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLng() {
        return lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }
}
