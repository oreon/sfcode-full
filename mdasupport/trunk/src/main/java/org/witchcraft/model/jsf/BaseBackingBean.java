package org.witchcraft.model.jsf;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
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
	private static final String SUCCESS_UPDATE = "successUpdate";
	private static final String CLEAR_SEARCH = "clearSearch";
	private static final String SUCCESS_SEARCH = "successSearch";
	private static final String SUCCESS_DELETE = "successDelete";
	private static final String TEXT_SEARCH = "textSearch";
	public static final String SEARCH = "search";
	
	private static final String AUTHENTICATION_CONTROLLER = "authenticationController";
	private Date fromDate; // entity creation from date
	private Date toDate; // entity creation to date
	private String searchText; // text field for full text search;

	private static Logger log = Logger.getLogger(BaseBackingBean.class);

	protected String action; // whether action is search or update/add new
	
	public static final int INITIAL_RECORDS = 0;

	public abstract BaseService<T> getBaseService();
	
	//This list is meant to cache all records - should be invalidated after add new , delete and edit
	public Collection<T> cachedAllRecordsList ;

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
			createErrorMessage(dae.getMessage(), "Delete Error", dae, null);
			return "failure";
		}

		return SUCCESS_DELETE;
	}

	public String search() {
		action = SEARCH;
		log.info("Action changed to search");
		return SUCCESS_SEARCH;
	}

	public String gotoSearchPage() {
		reset();
		return SEARCH;
	}

	public String clearSearch() {
		action = null;
		return CLEAR_SEARCH;
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
			log.info("Trying to load " + getEntity().getClass().getSimpleName()
					+ " with id " + idStr);
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
	
	
	/** The action listener version of reload from Id - will call parent classe's reload from Id
	 * This will help with hibernate lazy loading exceptions by simply reattaching the current 
	 * entity contained in this backing bean
	 * @param actionEvent
	 */
	public void reloadFromId(ActionEvent actionEvent) {
		reloadFromId( ((BusinessEntity)getEntity()).getId() );
	}

	/**
	 * This function should be overridden by derived classes so as to reset the
	 * instance varaible with the new one loaded from database using the id
	 * 
	 * @param id
	 */
	abstract protected void reloadFromId(long id) ;

	

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
			resetCachedList();
		} catch (BusinessException be) {
			createErrorMessage(be.getMessage(), "Business Exception", be, null);
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

		return SUCCESS_UPDATE;
	}

	private void createErrorMessage(String message, String title, Exception ex) {
		createErrorMessage(message, title, ex, null);
	}
	
	/**
	 * This method should be called whenever the cached list of objects has changed - ie. delete , update etc
	 */
	public void resetCachedList(){
		cachedAllRecordsList = null;
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

		if (action == null){
			return getCachedAllRecordsList();
		}

		if (action.equals(SEARCH))
			entities = getBaseService().searchByExample(getEntity(),
					getRangeList());

		else if (action.equals(TEXT_SEARCH)) {
			entities = getBaseService().performTextSearch(getSearchText());
			action = null;
		}

		createSuccessMessage("Found " + entities.size() + " result(s).");

		return entities;
	}
	
	/**
	 * @return
	 */
	public List<T> getCachedAllRecordsList(){
		if(cachedAllRecordsList == null)
			cachedAllRecordsList = getBaseService().loadAll();
		return (List<T>) cachedAllRecordsList;
	}

	public String textSearch() {
		action = TEXT_SEARCH;
		log.debug("setting action to textsearch");
		return "successTextSearchExams";
	}

	/**
	 * Creates and add an error message to the faces context
	 * 
	 * @param errorDetail
	 * @param errorTitle
	 */
	protected void createErrorMessage(String errorDetail, String errorTitle,
			Throwable throwable, Object[] params) {
		if (throwable != null) {
			log.error(errorDetail, throwable);
		} else
			log.info(errorDetail);
		String localizedMessage = JSFUtils.getMessageFromBundle(errorDetail,
				params);
		FacesContext.getCurrentInstance().addMessage(
				null,
				new FacesMessage(FacesMessage.SEVERITY_ERROR, errorTitle,
						errorDetail));
	}

	/**
	 * Business errors should call this method
	 * 
	 * @param errorDetail
	 * @param errorTitle
	 */
	/*
	 * protected void createErrorMessage(String errorDetail, String errorTitle) {
	 * createErrorMessage(errorDetail, errorTitle, null, null); }
	 */

	/**
	 * Creates a localized error message for the given key
	 * 
	 * @param message
	 */
	protected void createErrorMessage(String key) {
		createErrorMessage(key, key, null, null);
	}

	/**
	 * Creates a localized error message for the given key
	 * 
	 * @param key -
	 *            key in the resource bundle
	 * @param param -
	 *            the param to be substituted e.g. "Email sent to {0}" .
	 */
	protected void createErrorMessage(String key, Object param) {
		createErrorMessage(key, key, null, new Object[] { param });
	}

	/**
	 * Creates a localized error message for the given key
	 * 
	 * @param key -
	 *            key in the resource bundle
	 * @param param -
	 *            the param to be substituted e.g. "Email sent to {0}" .
	 */
	protected void createErrorMessage(String key, Object[] params) {
		createErrorMessage(key, key, null, params);
	}

	/**
	 * Creates a success/info message from the given key
	 * 
	 * @param message
	 */
	protected void createSuccessMessage(String key) {
		createSuccessMessage(key, null);
	}

	/**
	 * @param key -
	 *            key in the resource bundle
	 * @param param -
	 *            the param to be substituted e.g. "Email sent to {0}" .
	 */
	protected void createSuccessMessage(String key, Object param) {
		createSuccessMessage(key, new Object[] { param });
	}

	/**
	 * Creates a localized success message to be displayed in the messages
	 * section, uses info style from css If the key is not found in resource
	 * bundle displays the key
	 * 
	 * @param message
	 * @param params
	 */
	protected void createSuccessMessage(String key, Object[] params) {
		String localizedMessage = JSFUtils.getMessageFromBundle(key, params);
		FacesContext.getCurrentInstance().addMessage(
				null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, localizedMessage,
						""));
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
				.createValueBinding("#{" + name + "}");
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
		log.info("Action is being set to " + action);
		this.action = action;
	}

	public String getSearchText() {
		return searchText;
	}

	public void setSearchText(String searchText) {
		this.searchText = searchText;
	}

	protected AuthenticationController getAuthenticationController() {
		return getBean(getAuthenticationControllerName());
	}

	protected String getAuthenticationControllerName() {
		return AUTHENTICATION_CONTROLLER;
	}
	
	public boolean isActionSearch(){
		if(action == null )
			return false;
		return action.equalsIgnoreCase(SEARCH) || action.equalsIgnoreCase(TEXT_SEARCH);
	}
	
	/** This function is used for generic autocompletion in rich suggestion box components
	 * @param <S> - The type of the returned list
	 * @param enteredText - The text entered by the user
	 * @param data - The source list which needs to be filtered
	 * @param stringFunctionName - the name of the function that needs to be invoked on the object 
	 * in order to get a sring name e.g for city it will be "getName"
	 * @return
	 */
	public <S>List genericAutoComplete(Object enteredText, List<S> data, String stringFunctionName){
		List<S> returnList  = new ArrayList();
		log.debug("autocomplete state called for " + enteredText);

		for (S t : data) {
			String text = ((String) enteredText).toUpperCase();
			//t.getClass().getMethod("getName")
			Method method = null;
			String nameFromObject = null;
			try {
				method = t.getClass().getMethod(stringFunctionName);
				nameFromObject = (String) method.invoke(t);
			} catch (Exception e) {
				log.error("An exception occured trying to invoke " + stringFunctionName, e);
				return null;
			} 
			
			if (nameFromObject.startsWith(text)) {
				returnList.add(t);
			}
		}

		return returnList;
	}
}
