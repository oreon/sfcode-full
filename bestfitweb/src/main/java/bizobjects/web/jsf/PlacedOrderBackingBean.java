package bizobjects.web.jsf;

import javax.faces.component.UIParameter;
import javax.faces.event.ActionEvent;

import org.witchcraft.model.jsf.BaseBackingBean;
import org.witchcraft.model.support.service.BaseService;

import bizobjects.PlacedOrder;
import bizobjects.service.PlacedOrderService;

public class PlacedOrderBackingBean extends BaseBackingBean<PlacedOrder> {

	private PlacedOrder placedOrder = new PlacedOrder();

	private PlacedOrderService placedOrderService;

	public void setPlacedOrderService(PlacedOrderService placedOrderService) {
		this.placedOrderService = placedOrderService;
	}

	public PlacedOrder getPlacedOrder() {
		return placedOrder;
	}

	public void set(PlacedOrder placedOrder) {
		this.placedOrder = placedOrder;
	}

	@SuppressWarnings("unchecked")
	public BaseService<PlacedOrder> getBaseService() {
		return placedOrderService;
	}

	public PlacedOrder getEntity() {
		return getPlacedOrder();
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

		placedOrder = placedOrderService.load(id);
	}

}
