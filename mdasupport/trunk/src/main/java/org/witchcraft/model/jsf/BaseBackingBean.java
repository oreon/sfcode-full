package org.witchcraft.model.jsf;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.el.ValueBinding;
import javax.faces.event.ActionEvent;
import javax.faces.model.SelectItem;
import javax.servlet.http.HttpSession;

import org.acegisecurity.Authentication;
import org.acegisecurity.context.SecurityContextHolder;
import org.acegisecurity.userdetails.UserDetails;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.dao.DataAccessException;
import org.witchcraft.model.support.BusinessEntity;
import org.witchcraft.model.support.Range;
import org.witchcraft.model.support.audit.AuditLog;
import org.witchcraft.model.support.errorhandling.BusinessException;
import org.witchcraft.model.support.jsfbackingbeans.AuthenticationController;
import org.witchcraft.model.support.security.AbstractUser;
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
	public static final String SEARCH = "SEARCH";
	public static final int INITIAL_RECORDS = 0;

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

		return "successDelete";
	}

	public String search() {
		action = SEARCH;
		return "successSearch";
	}

	public String gotoSearchPage() {
		reset();
		return "search";
	}

	public String clearSearch() {
		action = null;
		return "clearSearch";
	}

	/**
	 * Returns a success string upon selection of an entity in model - majority
	 * of work is done in the actionListener selectEntity
	 * 
	 * @return - "success" if everthing goes fine
	 * @see -
	 */
	public String select() {
		return "edit";
	}

	/**
	 * This action Listener Method is called when a row is clicked in the
	 * dataTable
	 * 
	 * @param event
	 *            contains the database id of the row being selected
	 */
	public void selectEntity(ActionEvent actionEvent) {
		String idStr = (String) getActionParamValue("id");

		reset();
		if (idStr != null) {
			long id = Long.parseLong(idStr);
			reloadFromId(id);
		} else { // this is a new record
			initForAddNew();
		}
	}

	/**
	 * Gets the value of the given parameter from faces context
	 * 
	 * @param parameterName -
	 * @return
	 */
	public static Object getActionParamValue(String parameterName) {
		FacesContext ctx = FacesContext.getCurrentInstance();
		return ctx.getExternalContext().getRequestParameterMap().get(
				parameterName);
	}

	/**
	 * This method should be overridden by child classes to implement any
	 * initializations such as setting defaults when the user choses add new
	 */
	public void initForAddNew() {

	}

	/**
	 * This function should be overridden by derived classes so as to reset the
	 * instance varaible with the new one loaded from database using the id
	 * 
	 * @param id
	 */
	protected void reloadFromId(long id) {

	}

	protected UserDetails getLoggedInUser() {
		Authentication authentication = SecurityContextHolder.getContext()
				.getAuthentication();
		return (UserDetails) authentication.getPrincipal();
	}

	/**
	 * Write values to the database
	 * 
	 * @return - "success" if everything goes fine
	 */
	public String update() {
		boolean isNew = ((BusinessEntity) getEntity()).getId() == null;
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

		createSuccessMessage(getEntity().getClass().getSimpleName()
				+ " was successfully " + (isNew ? "added." : "updated."));

		return "successUpdate";
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
			entities = getBaseService().searchByExample(getEntity(),
					getRangeList());
		else
			entities = getBaseService().loadAll();

		// Sort results.
		/*
		 * if (!StringUtils.isEmpty(sortField)) { Collections.sort(entities, new
		 * DTOComparator(sortField, sortAscending)); }
		 */

		// createSuccessMessage( entities.size() > 0 ? ("Found " +
		// entities.size() + " records ." ):
		// "no.records.found" );
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
		if (throwable != null) {
			log.error(errorDetail, throwable);
		}
		FacesContext.getCurrentInstance().addMessage(
				null,
				new FacesMessage(FacesMessage.SEVERITY_ERROR, errorTitle,
						errorDetail));
	}

	/** Business errors should call this method
	 * @param errorDetail
	 * @param errorTitle
	 */
	protected void createErrorMessage(String errorDetail, String errorTitle) {
		createErrorMessage(errorDetail, errorTitle, null);
	}

	protected void createSuccessMessage(String message) {
		// log.error(errorDetail, throwable);
		//String localizedMessage = JSFUtils.getMessageFromBundle("password_mailed",new String[]{ email });
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, message, ""));
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

	/**
	 * This method is needed by backing beans which need to show uploaded
	 * images.
	 * 
	 * @return
	 */
	public Class getImageRendererCls() {
		return UploadedImageRenderer.class;
	}

	/**
	 * Utility method to get current http session
	 * 
	 * @return - session associated with current context - will create a new
	 *         session if one doesn't exist
	 */
	protected HttpSession getSession() {
		return (HttpSession) FacesContext.getCurrentInstance()
				.getExternalContext().getSession(true);
	}

	/**
	 * This method is used for getting range objects and is typically overriden
	 * by backing beans
	 * 
	 * @return
	 */
	protected List<Range> getRangeList() {
		return new ArrayList<Range>();
	}

	/**
	 * Use reset as an actionlistener e.g. to clear member variables for search
	 * 
	 * @param actionEvent
	 */
	public void reset(ActionEvent actionEvent) {
		reset();
	}

	public abstract void reset();

	/**
	 * Convenience method to get a given backing bean
	 * 
	 * @param name
	 */
	public <TB> TB getBean(String name) {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		ValueBinding valueBinding = facesContext.getApplication()
				.createValueBinding("#{name}");
		return (TB) valueBinding.getValue(facesContext);
	}

	public String getAction() {
		return action;
	}

	protected void resetRanges() {
		List<Range> listRanges = getRangeList();
		for (Range range : listRanges) {
			range.clear();
		}
	}

	public void setAction(String action) {
		this.action = action;
	}
}
