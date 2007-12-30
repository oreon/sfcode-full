package org.witchcraft.model.jsf;

import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.model.SelectItem;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.dao.DataAccessException;
import org.witchcraft.model.support.audit.AuditLog;
import org.witchcraft.model.support.errorhandling.BusinessException;
import org.witchcraft.model.support.service.BaseService;

/**
 * Base class of all crud backing beans
 * 
 * @author jsingh
 * 
 * @param <T> -
 *            The BusinessEntity
 */
public abstract class BaseBackingBean<T> {
	private Date fromDate;
	private Date toDate;

	private static Logger log = Logger.getLogger(BaseBackingBean.class);

	protected String action; // whether action is search or update/add new
	protected static final String SEARCH = "SEARCH";

	public abstract BaseService<T> getBaseService();

	public abstract T getEntity();

	private String sortField = null;
	private boolean sortAscending = true;

	/**
	 * Get all instances of this entity as a drop down list of selectable items -
	 * useful when this entity is being referenced from an association
	 * 
	 * @return
	 */
	public List<SelectItem> getAsSelectItems() {
		List<T> entities = getBaseService().loadAll();
		return JSFUtils.getAsSelectItems(entities);
	}

	public long getCount() {
		return getBaseService().getCount();
	}

	/**
	 * Write values to the database
	 * 
	 * @return - "success" if everthing goes fine
	 */
	public String delete() {
		try {
			getBaseService().delete(getEntity());
		} catch (DataAccessException dae) {
			createErrorMessage(dae.getMessage(), "Delete Error", dae);
			return "failure";
		}

		return "success";
	}

	/**
	 * Write values to the database
	 * 
	 * @return - "success" if everthing goes fine
	 */
	public String update() {
		try {
			getBaseService().save(getEntity());
		} catch (BusinessException be) {
			createErrorMessage(be.getMessage(), "Business Exception", be);
			return "failure";
		} catch (DataAccessException dae) {
			createErrorMessage(dae.getMessage(), "DB Error updating", dae);
			return "failure";
		} catch (Exception ex) {
			createErrorMessage(ex.getMessage(), "Critical Error updating", ex);
			return "failure";
		}

		return "success";
	}

	/**
	 * Get the audit logs for this entity
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<AuditLog<T>> getAuditLog() {
		return getBaseService().getAuditLogs();
	}

	/**
	 * Get a list of Records - if action is search, get a subset otherwise get
	 * all
	 * 
	 * @return - a list of records of type T
	 */
	public List<T> getRecords() {
		List<T> entities = null;
		if (action != null && action.equals(SEARCH))
			entities = getBaseService().searchByExample(getEntity());
		else
			entities = getBaseService().loadAll();

		// Sort results.
		if (sortField != null) {
			Collections.sort(entities, new DTOComparator(sortField,
					sortAscending));
		}
		return entities;
	}

	/**
	 * Creates and add an error message to the faces context
	 * 
	 * @param errorDetail
	 * @param errorTitle
	 */
	protected void createErrorMessage(String errorDetail, String errorTitle,
			Throwable throwable) {
		log.error(errorDetail, throwable);
		FacesContext.getCurrentInstance().addMessage(
				null,
				new FacesMessage(FacesMessage.SEVERITY_ERROR, errorTitle,
						errorDetail));
	}

	
	public void sortDataList(ActionEvent event) {
		String sortFieldAttribute = getAttribute(event, "sortField");
		System.out.println(sortField + " :: " + sortFieldAttribute);

		// Get and set sort field and sort order.
		if (sortField != null && sortField.equals(sortFieldAttribute)) {
			sortAscending = !sortAscending;
		} else {
			sortField = sortFieldAttribute;
			sortAscending = true;
		}

	}

	public String getSortField() {
		return sortField;
	}

	public boolean getSortAscending() {
		return sortAscending;
	}

	public void setSortField(String sortField) {
		this.sortField = sortField;
	}

	public void setSortAscending(boolean sortAscending) {
		this.sortAscending = sortAscending;
	}


	private static String getAttribute(ActionEvent event, String name) {
		return (String) event.getComponent().getAttributes().get(name);
	}
	
	/** This method is needed by backing beans which need to show uploaded images. 
	 * @return
	 */
	public Class getImageRendererCls() {
		return UploadedImageRenderer.class;
	}
	
	/** Utility method to get current http session 
	 * @return - session associated with current context - will create a new session if one doesn't exist
	 */
	protected HttpSession getSession(){
		return (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
	}

}
