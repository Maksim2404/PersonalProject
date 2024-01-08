package pages.api.ecommerce;

import base.BasePage;
import org.openqa.selenium.WebDriver;

public class OrderDetail extends BasePage {

    private String country;

    private String productOrderId;

    public OrderDetail(WebDriver driver) {
        super(driver);
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getProductOrderId() {
        return productOrderId;
    }

    public void setProductOrderId(String productOrderId) {
        this.productOrderId = productOrderId;
    }
}
