package com.publicfountain.domain.web.jsf;

import com.publicfountain.domain.Category;
import java.util.ArrayList;
import java.util.List;
import com.publicfountain.domain.service.CategoryService;

import javax.faces.component.UIParameter;
import javax.faces.event.ActionEvent;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

import javax.faces.application.FacesMessage;

import org.springframework.dao.DataAccessException;

import org.witchcraft.model.support.errorhandling.BusinessException;
import org.witchcraft.model.jsf.JSFUtils;
import org.witchcraft.model.support.audit.AuditLog;

public class CategoryBackingBean {

	private Category category = new Category();

	private CategoryService categoryService;

	private String action; //whether action is search or update/add new 

	private static final String SEARCH = "SEARCH";

	public void setCategoryService(CategoryService categoryService) {
		this.categoryService = categoryService;
	}

	public Category getCategory() {
		return category;
	}

	public void set(Category category) {
		this.category = category;
	}

	/**Write values to the database 
	 * @return - "success" if everthing goes fine
	 */
	public String update() {
		try {
			categoryService.save(category);
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
			categoryService.delete(category);
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

	public List<AuditLog<Category>> getAuditLog() {
		return categoryService.getAuditLogs();
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

		category = categoryService.load(id);
	}

	/**Get a list of  categorys - if action is search , get a subset otherwise
	 * get all
	 * @return - a list of categorys 
	 */
	public List<Category> getCategorys() {
		List<Category> categorys = null;
		if (action != null && action.equals(SEARCH))
			categorys = categoryService.searchByExample(category);
		else
			categorys = categoryService.loadAll();

		return categorys;
	}

	public List<SelectItem> getAsSelectItems() {
		List<Category> categorys = categoryService.loadAll();
		return JSFUtils.getAsSelectItems(categorys);
	}

}
