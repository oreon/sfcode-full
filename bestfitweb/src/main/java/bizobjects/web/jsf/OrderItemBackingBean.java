package bizobjects.web.jsf;

import bizobjects.OrderItem;


public class OrderItemBackingBean {
    private OrderItem orderItem = new OrderItem();

    public OrderItem getOrderItem(OrderItem orderItem) {
        return orderItem;
    }

    public void set(OrderItem orderItem) {
        this.orderItem = orderItem;
    }

    public String update() {
        return "success";
    }
}
