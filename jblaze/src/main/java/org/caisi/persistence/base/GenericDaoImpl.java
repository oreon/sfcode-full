package org.caisi.persistence.base;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.log4j.Logger;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.queryParser.MultiFieldQueryParser;
import org.apache.lucene.queryParser.ParseException;
import org.hibernate.Criteria;
import org.hibernate.Interceptor;
import org.hibernate.Session;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.search.jpa.Search;
import org.hibernate.validator.ClassValidator;
import org.hibernate.validator.InvalidValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author jsingh
 * 
 * @param <T>
 *            The type of entity to be persisted
 */
@Repository
@Transactional
public class GenericDaoImpl<T> implements GenericDao<T> {

	private static final Logger logger = Logger.getLogger(GenericDaoImpl.class);

	private Class<T> persistentClass;
	// private Session session;

	@Autowired
	protected EntityManager entityManager;

	protected Interceptor entityAuditLogInterceptor;

	@PersistenceContext
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
		Session session = (Session) entityManager.getDelegate();
		// session = session.getSessionFactory().openSession(
		// entityAuditLogInterceptor);
	}

	@SuppressWarnings("unchecked")
	public GenericDaoImpl() {

		try {
			ParameterizedType superclass = (ParameterizedType) getClass()
					.getGenericSuperclass();
			Type t = superclass.getActualTypeArguments()[0];
			// if (superclass != null && !superclass.equals(Object.class))
			this.persistentClass = (Class<T>) ((ParameterizedType) getClass()
					.getGenericSuperclass()).getActualTypeArguments()[0];
			// else
			// this.persistentClass = (Class<T>) ((ParameterizedType) getClass()
			// .getGenericSuperclass()).getActualTypeArguments()[0];

		} catch (ClassCastException cce) {
			cce.printStackTrace();
			this.persistentClass = (Class<T>) ((ParameterizedType) ((Class) getClass())
					.getGenericSuperclass()).getActualTypeArguments()[0];
		}
	}

	public Class<T> getPersistentClass() {
		return persistentClass;
	}

	/**
	 * This method saves or updates the given entity based upon whether the id
	 * is null
	 */
	public T save(T entity) {

		validate(entity);

		if (isPersistedBefore(entity)) {
			entity = entityManager.merge(entity);
		} else {
			entityManager.persist(entity);
		}

		return entity;
	}

	private void validate(T entity) {
		ClassValidator<T> vaidator = new ClassValidator<T>(getPersistentClass());
		InvalidValue[] values = vaidator.getInvalidValues(entity);

		if (values != null && values.length > 0) {
			List<UserMessage> messages = new ArrayList<UserMessage>();
			for (InvalidValue invalidValue : values) {
				messages.add(new UserMessage(invalidValue.getPropertyName(),
						invalidValue.getMessage()));
			}
			throw new ValidationException(messages);
		}
	}

	/**
	 * This method indicates wheteher this entity has been saved before
	 * 
	 * @param entity
	 * @return
	 */
	public boolean isPersistedBefore(T entity) {
		// return entityManager.contains(entity);
		BusinessEntity be = (BusinessEntity) entity;
		return be.getEntityId() != null;
	}

	public T load(Integer id) {
		return entityManager.find(getPersistentClass(), id);
	}

	/**
	 * Will load all the records for this entity - (non-Javadoc)
	 * 
	 * @see org.witchcraft.model.support.dao.GenericDAO#loadAll()
	 */
	@SuppressWarnings("unchecked")
	public List<T> loadAll() {
		String qryString = getLoadAllQuery();
		Query query = entityManager.createQuery(qryString);
		return query.getResultList();
	}

	/**
	 * This method returns the query to load all records - this method should be
	 * overridden by Daos in generated apps who need to filter records based on
	 * the need e.g show all subjects that belong to a certain school where the
	 * website hosts multiple schools
	 * 
	 * @return
	 */
	public String getLoadAllQuery() {
		String qryString = "SELECT e FROM "
				+ getPersistentClass().getSimpleName() + "  e ";
		return qryString;
	}

	public long getCount() {
		return getCount(null, null);
	}

	public void delete(T entity) {
		entityAuditLogInterceptor
				.onDelete(entity, "TESTUSER", null, null, null);
		// entityManager.remove(entityManager.getReference(getPersistentClass(),
		// ((BusinessEntity) entity).getId()));
		// entityManager.remove(entity);
	}

	private String excludedProperties[] = { "dateModified", "dateCreated", "id" };

	@SuppressWarnings("unchecked")
	public List<T> searchByExample(T exampleInstance) {
		Criteria criteria = createCriteria(exampleInstance,
				new ArrayList<Range>());
		List list = getPaginatedList(criteria, 0, 0);
		return list;
	}

	/*
	 * @see
	 * org.caisi.persistence.base.GenericDAO#searchByExample(java.lang.Object,
	 * java.util.List)
	 */
	@SuppressWarnings("unchecked")
	public List<T> searchByExample(T exampleInstance, List<Range> rangeObjects, int start, int pageSize) {
		Criteria criteria = createCriteria(exampleInstance, rangeObjects);
		return getPaginatedList(criteria, start, pageSize);
	}
	
	public List<T> searchByExample(T exampleInstance, List<Range> rangeObjects) {
		return searchByExample(exampleInstance, rangeObjects, 0, 0);
	}

	protected Criteria createCriteria(T exampleInstance, List<Range> rangeObjects) {
		Criteria criteria = createExampleCriteria(exampleInstance);
		
		if ( ((BusinessEntity)exampleInstance).getDefaultOrder() != null ){
			//TODO:  order object should contain asc/desc
			criteria.addOrder(Order.asc(((BusinessEntity)exampleInstance).getDefaultOrder()) );
		}
		
		if (rangeObjects != null) {
			for (Range range : rangeObjects) {
				if (range != null)
					range.updateCriterion(criteria);
			}
		}

		addAssoications(criteria, exampleInstance);
		return criteria;
	}
	
	

	public Integer getSearchByExampleCount(T exampleInstance,
			List<Range> rangeObjects) {
		Criteria criteria = createCriteria(exampleInstance, rangeObjects);
		
		criteria.setProjection(Projections.projectionList().add(
				Projections.rowCount()));
		logger.debug("result count is " + (Integer) criteria.uniqueResult());
		return (Integer) criteria.uniqueResult();

	}

	@SuppressWarnings("unchecked") List<T> getPaginatedList(Criteria criteria, int start, int max) {

		if (start > 0)
			criteria.setFirstResult(start);
		if (max > 0)
			criteria.setMaxResults(max);

		return criteria.list();
	}

	/**
	 * This method should be overridden by derived classes to add searchable
	 * associations to the criteria
	 * 
	 * @param criteria
	 */
	protected void addAssoications(Criteria criteria, T exampleInstance) {

	}

	public Criteria createExampleCriteria(T exampleInstance) {
		Session session = (Session) entityManager.getDelegate();

		Example example = Example.create(exampleInstance).enableLike(
				MatchMode.START).ignoreCase().excludeZeroes();

		Criteria criteria = session.createCriteria(getPersistentClass()).add(
				example);

		for (String exclude : excludedProperties) {
			example.excludeProperty(exclude);
		}
		return criteria;
	}

	public Interceptor getEntityAuditLogInterceptor() {
		return entityAuditLogInterceptor;
	}

	public void setEntityAuditLogInterceptor(
			Interceptor entityAuditLogInterceptor) {
		this.entityAuditLogInterceptor = entityAuditLogInterceptor;
	}

	public long getCount(Date fromDate, Date toDate) {
		String qryString = getCountQueryString(fromDate, toDate);
		Object result = entityManager.createQuery(qryString).getSingleResult();
		return ((Long) entityManager.createQuery(qryString).getSingleResult());
	}

	protected String getCountQueryString(Date fromDate, Date toDate) {
		String qryString = "select count(e) from "
				+ getPersistentClass().getSimpleName() + " e ";
		if (fromDate != null || toDate != null) {
			qryString += " WHERE ";
			if (fromDate != null)
				qryString += " e.dateCreated >= '" + fromDate + "'";

			if (fromDate != null && toDate != null)
				qryString += " AND ";

			if (toDate != null)
				qryString += " e.dateCreated <= '" + toDate + "'";
		}
		return qryString;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.witchcraft.model.support.dao.GenericDAO#performTextSearch(java.lang
	 * .String)
	 */
	public List<T> performTextSearch(String searchText) {

		BusinessEntity businessEntity = (BusinessEntity) getInstanceOfPersistentClass();

		FullTextEntityManager fullTextEntityManager;
		fullTextEntityManager = Search
				.createFullTextEntityManager(entityManager);

		if (businessEntity.retrieveSearchableFieldsArray() == null) {
			throw new RuntimeException(
					businessEntity.getClass().getSimpleName()
							+ " needs to override retrieveSearchableFieldsArray method ");
		}

		MultiFieldQueryParser parser = new MultiFieldQueryParser(businessEntity
				.retrieveSearchableFieldsArray(), new StandardAnalyzer());
		org.apache.lucene.search.Query query = null;
		try {
			query = parser.parse(searchText);
		} catch (ParseException e) {
			throw new RuntimeException(e);
		}
		org.hibernate.search.jpa.FullTextQuery ftq = fullTextEntityManager
				.createFullTextQuery(query, getPersistentClass());

		List<T> result = ftq.getResultList();
		System.out.println(result.size());
		return result;
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
			logger.info("No " + getPersistentClass().getSimpleName()
					+ " found !");
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	public <S> List<S> executeNamedQuery(String queryString, Object... params) {
		Query query = entityManager.createNamedQuery(queryString);
		setQueryParams(query, params);
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
		for (Object param : params) {
			int counter = 1;
			query.setParameter(counter++, param);
		}
	}

	private T getInstanceOfPersistentClass() {
		T t = null;
		try {
			t = persistentClass.newInstance();
		} catch (InstantiationException e) {
			logger.error("Error Instantiating", e);
			throw new RuntimeException(e);
		} catch (IllegalAccessException e1) {
			logger.error(getPersistentClass().getSimpleName()
					+ " Does not have a no arguement constructor ", e1);
			throw new RuntimeException(e1);
		}
		return t;
	}

	/**
	 * Utility function to get the name of this class
	 * 
	 * @return
	 */
	private String getName() {
		return getPersistentClass().getSimpleName();
	}

	

}
