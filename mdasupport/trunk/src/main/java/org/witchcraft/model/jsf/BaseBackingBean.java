package org.witchcraft.model.jsf;

import java.util.Date;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

import org.springframework.dao.DataAccessException;
import org.witchcraft.model.support.audit.AuditLog;
import org.witchcraft.model.support.errorhandling.BusinessException;
import org.witchcraft.model.support.service.BaseService;


/** Base class of all crud backing beans
 * @author jsingh
 *
 * @param <T> - The BusinessEntity 
 */
public abstract class BaseBackingBean<T> {
	private Date fromDate;
	private Date toDate;
	
	protected String action; //whether action is search or update/add new 
	protected static final String SEARCH = "SEARCH";
	
	public abstract BaseService<T> getBaseService();
	
	public abstract T getEntity();
	
	
	/** Get all instances of this entity as a drop down list of 
	 *  selectable items - useful when this entity is being refereced
	 *  from an association
	 * @return
	 */
	public List<SelectItem> getAsSelectItems() {
		List<T> entities = getBaseService().loadAll();
		return JSFUtils.getAsSelectItems(entities);
	}
	
	public long getCount(){
		return getBaseService().getCount();
	}
	
	/**Write values to the database 
	 * @return - "success" if everthing goes fine
	 */
	public String delete() {
		try {
			getBaseService().delete(getEntity());
		} catch (DataAccessException dae) {
			createErrorMessage(dae.getMessage(), "Delete Error");
			return "failure";
		}

		return "success";
	}
	
	/**Write values to the database 
	 * @return - "success" if everthing goes fine
	 */
	public String update() {
		try {
			getBaseService().save(getEntity());
		} catch (BusinessException be) {
			createErrorMessage(be.getMessage(), "Update Error");
			return "failure";
		} catch (DataAccessException dae) {
			createErrorMessage(dae.getMessage(), "Update Error");
			return "failure";
		} catch (Exception ex) {
			createErrorMessage(ex.getMessage(), "Update Error");
			return "failure";
		}

		return "success";
	}
	
	/** Get the audit logs for this entity
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<AuditLog<T>> getAuditLog() {
		return getBaseService().getAuditLogs();
	}
	
	/**Get a list of  Records - if action is search, get a subset otherwise
	 * get all
	 * @return - a list of records of type T 
	 */
	public List<T> getRecords() {
		List<T> entities = null;
		if (action != null && action.equals(SEARCH))
			entities = getBaseService().searchByExample(getEntity());
		else
			entities = getBaseService().loadAll();

		return entities;
	}
	
	
	/** Creates and add an error message to the faces context
	 * @param errorDetail
	 * @param errorTitle
	 */
	protected void createErrorMessage(String errorDetail, String errorTitle) {
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_ERROR, errorTitle, 
						errorDetail));
	}
	
	
}
