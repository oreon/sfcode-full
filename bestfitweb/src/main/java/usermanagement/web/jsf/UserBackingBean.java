package usermanagement.web.jsf;

import usermanagement.User;
import java.util.ArrayList;
import java.util.List;
import usermanagement.service.UserService;

import javax.faces.component.UIParameter;
import javax.faces.event.ActionEvent;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

import javax.faces.application.FacesMessage;

import org.springframework.dao.DataAccessException;

import org.witchcraft.model.support.errorhandling.BusinessException;
import org.witchcraft.model.jsf.JSFUtils;

public class UserBackingBean {

	private User user = new User();

	private UserService userService;

	private String action; //whether action is search or update/add new 

	private static final String SEARCH = "SEARCH";

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public User getUser() {
		return user;
	}

	public void set(User user) {
		this.user = user;
	}

	/**Write values to the database 
	 * @return - "success" if everthing goes fine
	 */
	public String update() {
		try {
			userService.save(user);
		} catch (BusinessException be) {
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR,
							"Update Error: ", be.getMessage()));
			return "failure";
		} catch (DataAccessException dae) {
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR,
							"Update Error: ", dae.getMessage()));
			return "failure";
		} catch (Exception ex) {
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR,
							"Update Error: ", ex.getMessage()));
			return "failure";
		}

		return "success";
	}

	/**Write values to the database 
	 * @return - "success" if everthing goes fine
	 */
	public String delete() {
		try {
			userService.delete(user);
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

		user = userService.load(id);
	}

	/**Get a list of  users - if action is search , get a subset otherwise
	 * get all
	 * @return - a list of users 
	 */
	public List<User> getUsers() {
		List<User> users = null;
		if (action != null && action.equals(SEARCH))
			users = userService.searchByExample(user);
		else
			users = userService.loadAll();

		return users;
	}

	public List<SelectItem> getAsSelectItems() {
		List<User> users = userService.loadAll();
		return JSFUtils.getAsSelectItems(users);
	}

}
