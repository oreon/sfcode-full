package bizobjects.web.jsf;

import java.util.ArrayList;
import java.util.List;

import javax.faces.component.UIParameter;
import javax.faces.event.ActionEvent;

import bizobjects.OrderItem;


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

    public String select() {
        return "edit";
    }

    /** This action Listener Method is called when a row is clicked in the dataTable
     *
     * @param event contians the database id of the row being selected
     */
    public void selectEntity(ActionEvent event) {
        UIParameter component = (UIParameter) event.getComponent()
                                                   .findComponent("editId");

        // parse the value of the UIParameter component    	 
        long id = Long.parseLong(component.getValue().toString());
    }

    /**Get a list of all orderItems
    * @return - a list of orderItems
    */
    public List<OrderItem> getOrderItems() {
        List<OrderItem> orderItems = new ArrayList<OrderItem>();

        return orderItems;
    }
}
