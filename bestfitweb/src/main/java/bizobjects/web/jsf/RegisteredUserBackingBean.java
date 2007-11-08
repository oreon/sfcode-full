package bizobjects.web.jsf;

import javax.faces.component.UIParameter;
import javax.faces.event.ActionEvent;

import org.witchcraft.model.jsf.BaseBackingBean;
import org.witchcraft.model.support.service.BaseService;

import bizobjects.RegisteredUser;
import bizobjects.service.RegisteredUserService;

public class RegisteredUserBackingBean extends BaseBackingBean<RegisteredUser> {

	private RegisteredUser registeredUser = new RegisteredUser();

	private RegisteredUserService registeredUserService;

	public void setRegisteredUserService(
			RegisteredUserService registeredUserService) {
		this.registeredUserService = registeredUserService;
	}

	public RegisteredUser getRegisteredUser() {
		return registeredUser;
	}

	public void set(RegisteredUser registeredUser) {
		this.registeredUser = registeredUser;
	}

	@SuppressWarnings("unchecked")
	public BaseService<RegisteredUser> getBaseService() {
		return registeredUserService;
	}

	public RegisteredUser getEntity() {
		return getRegisteredUser();
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

		registeredUser = registeredUserService.load(id);
	}

}
