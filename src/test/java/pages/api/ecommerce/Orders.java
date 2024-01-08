package pages.api.ecommerce;

import base.BasePage;
import org.openqa.selenium.WebDriver;

import java.util.List;

public class Orders extends BasePage {

    private List<OrderDetail> orders;

    public Orders(WebDriver driver) {
        super(driver);
    }

    public List<OrderDetail> getOrders() {
        return orders;
    }

    public void setOrders(List<OrderDetail> orders) {
        this.orders = orders;
    }
}
