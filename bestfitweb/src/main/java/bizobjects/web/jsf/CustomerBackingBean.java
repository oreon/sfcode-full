package bizobjects.web.jsf;

import javax.faces.component.UIParameter;
import javax.faces.event.ActionEvent;

import org.witchcraft.model.jsf.BaseBackingBean;
import org.witchcraft.model.support.service.BaseService;

import bizobjects.Customer;
import bizobjects.service.CustomerService;

public class CustomerBackingBean extends BaseBackingBean<Customer> {

	private Customer customer = new Customer();

	private CustomerService customerService;

	public void setCustomerService(CustomerService customerService) {
		this.customerService = customerService;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void set(Customer customer) {
		this.customer = customer;
	}

	@SuppressWarnings("unchecked")
	public BaseService<Customer> getBaseService() {
		return customerService;
	}

	public Customer getEntity() {
		return getCustomer();
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

		customer = customerService.load(id);
	}

}
