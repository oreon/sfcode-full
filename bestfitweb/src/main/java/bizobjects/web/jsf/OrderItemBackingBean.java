package bizobjects.web.jsf;

import javax.faces.component.UIParameter;
import javax.faces.event.ActionEvent;

import org.witchcraft.model.jsf.BaseBackingBean;
import org.witchcraft.model.support.service.BaseService;

import bizobjects.OrderItem;
import bizobjects.service.OrderItemService;

public class OrderItemBackingBean extends BaseBackingBean<OrderItem> {

	private OrderItem orderItem = new OrderItem();

	private OrderItemService orderItemService;

	public void setOrderItemService(OrderItemService orderItemService) {
		this.orderItemService = orderItemService;
	}

	public OrderItem getOrderItem() {
		return orderItem;
	}

	public void set(OrderItem orderItem) {
		this.orderItem = orderItem;
	}

	@SuppressWarnings("unchecked")
	public BaseService<OrderItem> getBaseService() {
		return orderItemService;
	}

	public OrderItem getEntity() {
		return getOrderItem();
	}

	public String search() {
		action = SEARCH;
		return "search";
	}

	/** Returns a success string upon selection of an entity in model - majority of work is done
	 * in the actionListener selectEntity
	 * @return - "success" if everthing goes fine
	 * @see - 
	 */
	public String select() {
		return "edit";
	}

	/** This action Listener Method is called when a row is clicked in the dataTable
	 *  
	 * @param event contains the database id of the row being selected 
	 */
	public void selectEntity(ActionEvent event) {

		UIParameter component = (UIParameter) event.getComponent()
				.findComponent("editId");

		// parse the value of the UIParameter component    	 
		long id = Long.parseLong(component.getValue().toString());

		orderItem = orderItemService.load(id);
	}

}
