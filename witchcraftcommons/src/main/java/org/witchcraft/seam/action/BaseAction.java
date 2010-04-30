package org.witchcraft.seam.action;

import java.util.List;
import java.util.Map;

import javax.faces.component.UIComponent;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.queryParser.MultiFieldQueryParser;
import org.apache.lucene.queryParser.ParseException;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.search.jpa.FullTextQuery;
import org.jboss.seam.annotations.Begin;
import org.jboss.seam.annotations.Destroy;
import org.jboss.seam.annotations.End;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Logger;
import org.jboss.seam.annotations.Observer;
import org.jboss.seam.annotations.web.RequestParameter;
import org.jboss.seam.core.Events;
import org.jboss.seam.faces.FacesMessages;
import org.jboss.seam.log.Log;
import org.witchcraft.base.entity.BusinessEntity;
import org.witchcraft.base.entity.EntityComment;
import org.witchcraft.base.entity.EntityTemplate;



/**
 * The base action class - contains common persistence related methods, also
 * contains comment related functionality
 * 
 * @author jsingh
 * 
 * @param <T>
 */
public abstract class BaseAction<T extends BusinessEntity> {

	@Logger
	protected Log log;
	@In
	// @PersistenceContext(type=EXTENDED)
	protected FullTextEntityManager entityManager;

	@In
	protected FacesMessages facesMessages;

	@In
	protected Events events;

	@RequestParameter
	private String queryString;
	
	@RequestParameter
	Long id;
	
	@In 
	protected Map<String, UIComponent> uiComponent;

	private List<EntityComment> comments;
	private String currentCommentText;

	private boolean templateMode;

	private EntityTemplate<T> entityTemplate = new EntityTemplate<T>();

	public EntityTemplate<T> getEntityTemplate() {
		return entityTemplate;
	}

	public void setEntityTemplate(EntityTemplate<T> entityTemplate) {
		this.entityTemplate = entityTemplate;
	}

	public boolean isTemplateMode() {
		return templateMode;
	}

	public void setTemplateMode(boolean templateMode) {
		this.templateMode = templateMode;
	}

	@Begin
	public String createTemplate() {
		setTemplateMode(true);
		return "edit";
	}

	public String getCurrentCommentText() {
		return currentCommentText;
	}

	public void setCurrentCommentText(String currentCommentText) {
		this.currentCommentText = currentCommentText;
	}

	@Begin(join = true)
	public String select(T t) {
		setEntity(entityManager.merge(t));
		log
				.info("User selected #{t.getClass().getName()}: #{t.displayName} #{t.id} ");
		updateAssociations();
		log.info("returnring: " + "view" + getClassName(t));
		return "view" + getClassName(t);
	}

	public String getQueryString() {
		return queryString;
	}

	public void setQueryString(String queryString) {
		this.queryString = queryString;
	}

	@End
	public String archive(T t) {
		if(t == null) t = getEntity();
		t.setArchived(true);
		entityManager.merge(t);
		facesMessages.add("Successfully archived  " + t.getDisplayName());
		log
				.info("User archived #{t.getClass().getName()}: #{t.displayName} #{t.id} ");
		events.raiseTransactionSuccessEvent("archived" + getClassName(t));
		events.raiseTransactionSuccessEvent("resetList");
		return "archived";
	}

	public String saveTemplate() {
		EntityTemplate<T> template = getEntityTemplate();
		template.setEntity(getEntity());
		template.setEntityName(getClassName(getEntity()));
		// template.setTemplateName(getEn);
		entityManager.persist(template);

		// TODO: replace with statusmessages seam class
		if (facesMessages != null)
			facesMessages.add("Successfully saved template: "
					+ getEntity().getDisplayName());
		return "save";
	}

	public void loadFromTemplate(String templateName) {
		setEntity((T) getEntityTemplate().getEntity());
	}

	public EntityTemplate getCurrentTemplate() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * The current template
	 * 
	 * @return
	 */
	public String getTemplateName() {
		// TODO Auto-generated method stub
		return StringUtils.EMPTY;
	}

	public String save() {
		try{
		if (templateMode)
			return saveTemplate();

		updateComposedAssociations();
		entityManager.persist(getEntity());
		//entityManager.

		// TODO: replace with statusmessages seam class
		if (facesMessages != null)
			facesMessages.add("Successfully saved record: "
					+ getEntity().getDisplayName());
		updateAssociations();
		}catch(Exception e){
			facesMessages.add("Error saving record : " + e.getMessage());
			log.error("error saving ", e);
			return "error";
		}
		return "save";
	}
	
	public void load(Long entityId) {
		if (entityId != null) {
	
			try {
				T t = (T) entityManager.createQuery(
						"Select d from " + getClassName()  + " d where d.id=:id")
						.setParameter("id", entityId).getSingleResult();
				setEntity(t);

			} catch (NoResultException noResultException) {
				facesMessages.add("Invalid Id");
			}
		}
		//return "edit";
	}
	
	public String edit(){
		load(id);
		return "edit";
	}
	
	public String view(){
		load(id);
		return "view" + getClassName();
	}
	
	public void archive(){
		load(id);
		archive(getEntity());
	}
	
	
	
	
	@SuppressWarnings("unchecked")
    public Long getRecordCount() {
        try {
            String queryString = "select count(c) from " +  getClassName() +  " c ";
            return (Long)getEntityManager().createQuery(queryString).getSingleResult();
        } catch (RuntimeException re) {
            throw re;
        }
    }


	@End
	public String cancel() {
		return "cancel";
	}

	public void search() {
		Criteria criteria = createExampleCriteria();
		setEntityList(criteria.list());
	}

	@Observer("resetList")
	public void clearSearch() {
		try {
			setEntity((T) getEntity().getClass().newInstance());
			// TODO: do exception handling
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		findRecords();
	}

	public Criteria createExampleCriteria() {
		Session session = (Session) entityManager.getDelegate();

		Example example = Example.create(getEntity()).enableLike(
				MatchMode.START).ignoreCase().excludeZeroes();

		Criteria criteria = session.createCriteria(getEntity().getClass()).add(
				example);
		/*
		 * for (String exclude : excludedProperties) {
		 * example.excludeProperty(exclude); }
		 */
		addAssoications(criteria);

		criteria.addOrder(getOrder());

		return criteria;
	}

	public Order getOrder() {
		return Order.asc("id");
	}

	/**
	 * This method should be overloaded by actions to add associations e.g. an
	 * order would need the associated customer
	 * 
	 * @param criteria
	 */
	public void addAssoications(Criteria criteria) {
	}

	public void updateAssociations() {
	}

	public abstract T getEntity();

	public abstract void setEntity(T t);

	public abstract void findRecords();

	public abstract void setEntityList(List<T> list);

	/**
	 * This method should be overridden by classes that need to fck
	 */
	public void updateComposedAssociations() {
	}

	@SuppressWarnings("unchecked")
	public <S> List<S> executeQuery(String queryString, Object... params) {
		Query query = entityManager.createQuery(queryString);
		setQueryParams(query, params);
		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	public <S> S executeSingleResultQuery(String queryString, Object... params) {
		Query query = entityManager.createQuery(queryString);
		setQueryParams(query, params);
		return (S) executeSingleResultQuery(query);
	}

	private <S> S executeSingleResultQuery(Query query) {
		try {
			return (S) query.getSingleResult();
		} catch (NoResultException nre) {
			log.info("No " + "record " + " found !");
			return null;
		}
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see org.witchcraft.model.support.dao.GenericDAO#performTextSearch(java.lang
	 *      .String)
	 */
	public String textSearch() {

		BusinessEntity businessEntity = getEntity();
		
		List<String> listSearchableFields = businessEntity.listSearchableFields();
 
		if ( listSearchableFields == null) {
			throw new RuntimeException(
					businessEntity.getClass().getSimpleName()
							+ " needs to override retrieveSearchableFieldsArray method ");
		}

		String[] arrFields = new String[listSearchableFields.size()];
		listSearchableFields.toArray(arrFields);

		
		MultiFieldQueryParser parser = new MultiFieldQueryParser(arrFields, new StandardAnalyzer());

		org.apache.lucene.search.Query query = null;

		try {
			query = parser.parse(queryString);
		} catch (ParseException e) {
			throw new RuntimeException(e);
		}

		FullTextQuery ftq = entityManager.createFullTextQuery(query,
				getEntity().getClass());

		List<T> result = ftq.getResultList();
		
		setEntityList(result);
		return getClassName();
	}

	/**
	 * To create a full text index for the given entity
	 * 
	 * @return
	 */
	public String reIndex() {
		final List<T> entries = entityManager.createQuery(
				"select d from " + getClassName(getEntity()) +  "  d").getResultList();
		for (T t : entries)
			entityManager.index(t);
		return null;
	}

	@SuppressWarnings("unchecked")
	public <S> List<S> executeNamedQuery(String queryString, Object... params) {
		Query query = entityManager.createNamedQuery(queryString);
		setQueryParams(query, params);
		// setEntityList(query.getResultList());
		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	public <S> S executeSingleResultNamedQuery(String queryString,
			Object... params) {
		Query query = entityManager.createNamedQuery(queryString);
		setQueryParams(query, params);
		return (S) executeSingleResultQuery(query);
	}

	@SuppressWarnings("unchecked")
	public <S> S executeSingleResultNativeQuery(String queryString,
			Object... params) {
		Query query = entityManager.createNativeQuery(queryString);
		setQueryParams(query, params);
		return (S) query.getSingleResult();
	}

	@SuppressWarnings("unchecked")
	public <S> List<S> executeNativeQuery(String queryString, Object... params) {
		Query query = entityManager.createNativeQuery(queryString);
		setQueryParams(query, params);
		return query.getResultList();
	}

	private void setQueryParams(Query query, Object... params) {
		int counter = 1;
		for (Object param : params) {
			query.setParameter(counter++, param);
		}
	}

	@End
	public void reset() {
	}

	@Destroy
	public void destroy() {
	}

	protected String getClassName(T t) {
		String name = t.getClass().getSimpleName();
		if (name.indexOf("$$") > 0)
			name = name.substring(0, name.indexOf("$$"));
		return name;
	}
	
	protected String getClassName() {
		return getClassName(getEntity());
	}

	// //////////////// Comments
	// /////////////////////////////////////////////////////////

	public void saveComment() {
		EntityComment currentComment = new EntityComment();
		currentComment.setText(currentCommentText);
		currentComment.setEntityId(getEntity().getId());
		currentComment.setEntityName(getClassName(getEntity()));
		getEntityManager().persist(currentComment);
		currentCommentText = new String();
		events.raiseTransactionSuccessEvent("entityCommentsUpdated",
				getClassName(getEntity()));
	}

	public List<EntityComment> getComments() {
		if (comments == null) {
			loadComments();
		}
		return comments;
	}

	@Observer("entityCommentsUpdated")
	public void loadComments() {
		comments = executeNamedQuery("commentsForRecord", getEntity().getId(),
				getClassName(getEntity()));
	}

	public FullTextEntityManager getEntityManager() {
		return entityManager;
	}

	public void setEntityManager(FullTextEntityManager entityManager) {
		this.entityManager = entityManager;
	}

	// /////////////////// Delete Modal Dialog
	// ////////////////////////////////////////

	private boolean deleteDialogRendered = false;

	public boolean isDeleteDialogRendered() {
		return deleteDialogRendered;
	}

	public void setDeleteDialogRendered(boolean deleteDialogRendered) {
		this.deleteDialogRendered = deleteDialogRendered;
	}

	@Begin(join = true)
	public void showDeleteDialog(T t) {
		setEntity(entityManager.merge(t));
		deleteDialogRendered = true;
	}

	public void archiveAndClose() {
		archive(getEntity());
		closeModal();
	}

	public void closeModal() {
		setDeleteDialogRendered(false);
	}

}
