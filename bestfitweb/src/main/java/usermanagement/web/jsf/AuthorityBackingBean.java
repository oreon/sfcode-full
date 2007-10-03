package usermanagement.web.jsf;

import usermanagement.Authority;
import java.util.ArrayList;
import java.util.List;
import usermanagement.service.AuthorityService;

import javax.faces.component.UIParameter;
import javax.faces.event.ActionEvent;
import javax.faces.context.FacesContext;

import javax.faces.application.FacesMessage;

import org.springframework.dao.DataAccessException;

public class AuthorityBackingBean {

	private Authority authority = new Authority();

	private AuthorityService authorityService;

	private String action; //whether action is search or update/add new 

	private static final String SEARCH = "SEARCH";

	public void setAuthorityService(AuthorityService authorityService) {
		this.authorityService = authorityService;
	}

	public Authority getAuthority() {
		return authority;
	}

	public void set(Authority authority) {
		this.authority = authority;
	}

	/**Write values to the database 
	 * @return - "success" if everthing goes fine
	 */
	public String update() {
		try {
			authorityService.save(authority);
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
			authorityService.delete(authority);
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

		authority = authorityService.load(id);
	}

	/**Get a list of  authoritys - if action is search , get a subset otherwise
	 * get all
	 * @return - a list of authoritys 
	 */
	public List<Authority> getAuthoritys() {
		List<Authority> authoritys = null;
		if (action != null && action.equals(SEARCH))
			authoritys = authorityService.searchByExample(authority);
		else
			authoritys = authorityService.loadAll();

		return authoritys;
	}

}
