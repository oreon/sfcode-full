package bizobjects.web.jsf;

import bizobjects.OrderItem;

import java.util.ArrayList;
import java.util.List;


public class OrderItemBackingBean {
    private OrderItem orderItem = new OrderItem();

    public OrderItem getOrderItem() {
        return orderItem;
    }

    public void set(OrderItem orderItem) {
        this.orderItem = orderItem;
    }

    /**Write values to the database
    * @return - a list of
    */
    public String update() {
        return "success";
    }

    /**Get a list of all orderItems
    * @return - a list of orderItems
    */
    public List<OrderItem> getOrderItems() {
        List<OrderItem> orderItems = new ArrayList<OrderItem>();

        return orderItems;
    }
}
