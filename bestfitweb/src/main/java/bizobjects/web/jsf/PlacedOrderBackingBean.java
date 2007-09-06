package bizobjects.web.jsf;

import bizobjects.PlacedOrder;


public class PlacedOrderBackingBean {
    private PlacedOrder placedOrder = new PlacedOrder();

    public PlacedOrder getPlacedOrder(PlacedOrder placedOrder) {
        return placedOrder;
    }

    public void set(PlacedOrder placedOrder) {
        this.placedOrder = placedOrder;
    }

    public String update() {
        return "success";
    }
}
