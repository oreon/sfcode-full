package bizobjects.web.jsf;

import bizobjects.Product;
import java.util.ArrayList;
import java.util.List;
import bizobjects.service.ProductService;

import javax.faces.component.UIParameter;
import javax.faces.event.ActionEvent;
import javax.faces.context.FacesContext;

import javax.faces.application.FacesMessage;

import org.springframework.dao.DataAccessException;

public class ProductBackingBean {

	private Product product = new Product();

	private ProductService productService;

	private String action; //whether action is search or update/add new 

	private static final String SEARCH = "SEARCH";

	public void setProductService(ProductService productService) {
		this.productService = productService;
	}

	public Product getProduct() {
		return product;
	}

	public void set(Product product) {
		this.product = product;
	}

	/**Write values to the database 
	 * @return - "success" if everthing goes fine
	 */
	public String update() {
		try {
			productService.save(product);
		} catch (DataAccessException dae) {
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR,
							"Update Error: ", dae.getMessage()));
			return "failure";
		}

		return "success";
	}

	/**Write values to the database 
	 * @return - "success" if everthing goes fine
	 */
	public String delete() {
		try {
			productService.delete(product);
		} catch (DataAccessException dae) {
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR,
							"Delete Error: ", dae.getMessage()));
			return "failure";
		}

		return "success";
	}

	public String search() {
		action = SEARCH;
		return "search";
	}

	/**If update is canceled we go to the listing page - invoked in response to clicking cancel 
	 * on save/edit record form
	 * @return - "success" (always)
	 */
	public String cancelUpdate() {
		return "success";
	}

	public String cancelSearch() {
		return "success";
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
	 * @param event contians the database id of the row being selected 
	 */
	public void selectEntity(ActionEvent event) {

		UIParameter component = (UIParameter) event.getComponent()
				.findComponent("editId");

		// parse the value of the UIParameter component    	 
		long id = Long.parseLong(component.getValue().toString());

		product = productService.load(id);
	}

	/**Get a list of  products - if action is search , get a subset otherwise
	 * get all
	 * @return - a list of products 
	 */
	public List<Product> getProducts() {
		List<Product> products = null;
		if (action != null && action.equals(SEARCH))
			products = productService.searchByExample(product);
		else
			products = productService.loadAll();

		return products;
	}

}
