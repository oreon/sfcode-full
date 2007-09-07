package bizobjects.web.jsf;

import bizobjects.PlacedOrder;

import java.util.ArrayList;
import java.util.List;


public class PlacedOrderBackingBean {
    private PlacedOrder placedOrder = new PlacedOrder();

    public PlacedOrder getPlacedOrder() {
        return placedOrder;
    }

    public void set(PlacedOrder placedOrder) {
        this.placedOrder = placedOrder;
    }

    /**Write values to the database
    * @return - a list of
    */
    public String update() {
        return "success";
    }

    /**Get a list of all placedOrders
    * @return - a list of placedOrders
    */
    public List<PlacedOrder> getPlacedOrders() {
        List<PlacedOrder> placedOrders = new ArrayList<PlacedOrder>();

        return placedOrders;
    }
}
