package bizobjects.web.jsf;

import java.util.ArrayList;
import java.util.List;

import javax.faces.component.UIParameter;
import javax.faces.event.ActionEvent;

import bizobjects.Customer;

public class CustomerBackingBean {
	private Customer customer = new Customer();

	public Customer getCustomer() {
		return customer;
	}

	public void set(Customer customer) {
		this.customer = customer;
	}

	/**
	 * Write values to the database
	 * 
	 * @return - a list of
	 */
	public String update() {
		return "success";
	}

	public String select() {
		return "edit";
	}

	/**
	 * This action Listener Method is called when a row is clicked in the
	 * dataTable
	 * 
	 * @param event
	 *            contians the database id of the row being selected
	 */
	public void selectEntity(ActionEvent event) {
		UIParameter component = (UIParameter) event.getComponent()
				.findComponent("editId");

		// parse the value of the UIParameter component
		long id = Long.parseLong(component.getValue().toString());
		
		customer = getCustomers().get(0);
	}

	private long count;

	/**
	 * Get a list of all
	 * 
	 * @return
	 */
	public List<Customer> getCustomers() {
		List<Customer> customers = new ArrayList<Customer>();

		customers.add(createCustomer("Eric", "Regis"));
		customers.add(createCustomer("Huy", "Mokys"));
		customers.add(createCustomer("Levi", "Mokys"));
		customers.add(createCustomer("Sukh", "Bal"));
		customers.add(createCustomer("Amrita", "Bal"));

		return customers;
	}

	/**
	 * @param fn
	 * @param ln
	 * @return
	 */
	private Customer createCustomer(String fn, String ln) {
		Customer cust = new Customer();
		cust.setId(++count);
		cust.setFirstName(fn);
		cust.setLastName(ln);
		cust.getPrimaryAddress().setCity("Burlington");
		cust.getPrimaryAddress().setEmail("ton@yahoo.com");
		return cust;
	}
}
