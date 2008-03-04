package com.oreon.kgauge.web.jsf;

import javax.faces.component.UIParameter;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;

import org.witchcraft.model.jsf.BaseBackingBean;
import org.witchcraft.model.support.service.BaseService;

import com.oreon.kgauge.domain.User;
import com.oreon.kgauge.service.UserService;

public class UserBackingBean extends BaseBackingBean<User> {

	private User user = new User();

	private UserService userService;

	private String repeatPassword;

	public String getRepeatPassword() {
		return repeatPassword;
	}

	public void setRepeatPassword(String repeatpassword) {
		this.repeatPassword = repeatpassword;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public User getUser() {
		return user;
	}
	
	//variables for setUser
	String uName="";
	String pWord="";
	String enabled="true";
	String registered="true";//this needs to be coded!!!!?
	boolean isEnabled=false;
	boolean isRegistered=true;
	public void setUName(String u){uName=u;}
	public String getUName(){return uName;}
	public void setPWord(String p){pWord=p;}
	public String getPWord(){return pWord;}
	public void setEnabled(String e){enabled=e;
	if(enabled=="true"){isEnabled=true;}
	else{isEnabled=false;}}
	public String getEnabled(){return enabled;}
	
	//trying different methods but none work so far
	public void setUser(){
		FacesContext context = FacesContext.getCurrentInstance();
		uName = (String)context.getExternalContext().getRequestParameterMap().get("UserID");
		pWord = (String)context.getExternalContext().getRequestParameterMap().get("Password");		
		user=new User(uName,pWord,isEnabled);
		getBaseService().save(new User(uName,pWord,isEnabled));
	}
	
	public void setUser(ActionEvent e){
		FacesContext context = FacesContext.getCurrentInstance();
		uName = (String)context.getExternalContext().getRequestParameterMap().get("UserID");
		pWord = (String)context.getExternalContext().getRequestParameterMap().get("Password");		
		user=new User(uName,pWord,isEnabled);
		getBaseService().save(new User(uName,pWord,isEnabled));
	}
	
	public String getRegistered(){return registered;}
	
	public void setRegistered(String r){registered=r;
	if(registered=="true"){isRegistered=true;}
	else{isRegistered=false;}}
	
	
	public void setTargetUser(){
		if(enabled=="true"){isEnabled=true;}
		else{isEnabled=false;}
		user=new User(uName,pWord,isEnabled);
		getBaseService().save(new User(uName,pWord,isEnabled));		
	}
		
	public void set(User user) {
		this.user = user;
	}
	
	public void set(String u, String p) {		
		user=new User(uName,pWord,isEnabled);
		getBaseService().save(user);
		//this.user = getBaseService().load(getCount());
	}

	@SuppressWarnings("unchecked")
	public BaseService<User> getBaseService() {
		return userService;
	}

	public User getEntity() {
		return getUser();
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
	public void selectEntity(ActionEvent actionEvent) {

		FacesContext ctx = FacesContext.getCurrentInstance();
		String idStr = (String) ctx.getExternalContext()
				.getRequestParameterMap().get("id");
		long id = Long.parseLong(idStr);
		user = userService.load(id);
		if (actionEvent.getComponent().getId() == "deleteId") {
			getBaseService().delete(user);

		}

		/*
		UIParameter component = (UIParameter) actionEvent.getComponent().findComponent("editId");
		// parse the value of the UIParameter component    	 
		long id = Long.parseLong(component.getValue().toString());
		 */

	}

}
