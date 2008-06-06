package org.witchcraft.model.support.dao;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
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
import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.search.jpa.Search;
import org.springframework.stereotype.Repository;
import org.witchcraft.model.support.BusinessEntity;

//causes circular dependency
import org.witchcraft.model.support.Range;

/**
 * @author jsingh
 * 
 * @param <T>
 *            The type of entity to be persisted
 */
@Repository
public class BaseDao<T> implements GenericDAO<T> {

	private static final Logger logger = Logger.getLogger(BaseDao.class);

	private Class<T> persistentClass;
	// private Session session;

	protected EntityManager entityManager;

	protected Interceptor entityAuditLogInterceptor;

	@PersistenceContext
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
		Session session = (Session) entityManager.getDelegate();
		session = session.getSessionFactory().openSession(
				entityAuditLogInterceptor);
	}

	@SuppressWarnings("unchecked")
	public BaseDao() {

		try {
			Type superclass = ((Class) getClass().getGenericSuperclass())
					.getGenericSuperclass();

			this.persistentClass = (Class<T>) ((ParameterizedType) superclass)
					.getActualTypeArguments()[0];
		} catch (ClassCastException cce) {
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

		/*
		 * FIXME : This code will make entityManager.persist(entity); else
		 * entityManager.merge(entity);
		 */

		// Session session = (Session) entityManager.getDelegate();
		// session = session.getSessionFactory().openSession(interceptor);
		if (isPersistedBefore(entity)) {
			entity = entityManager.merge(entity);
			entityAuditLogInterceptor.onFlushDirty(entity, "TESTUSER", null,
					null, null, null);
		} else {
			entityManager.persist(entity);
			entityAuditLogInterceptor.onSave(entity, "TESTUSER", null, null,
					null);
		}

		return entity;
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
		return be.getId() != null;
	}

	public T load(Long id) {
		return entityManager.find(getPersistentClass(), id);
	}

	@SuppressWarnings("unchecked")
	public List<T> loadAll() {
		String qryString = "select e from "
				+ getPersistentClass().getSimpleName() + "  e ";
		Query query = entityManager.createQuery(qryString);

		return query.getResultList();
	}

	public long getCount() {
		return getCount(null, null);
	}

	public void delete(T entity) {
		entityAuditLogInterceptor
				.onDelete(entity, "TESTUSER", null, null, null);
		entityManager.remove(entityManager.getReference(getPersistentClass(),
				((BusinessEntity) entity).getId()));
		// entityManager.remove(entity);
	}

	private String excludedProperties[] = { "dateModified", "dateCreated", "id" };

	@SuppressWarnings("unchecked")
	public List<T> searchByExample(T exampleInstance) {
		Criteria criteria = createExampleCriteria(exampleInstance);
		List list = criteria.list();
		return list;
	}

	@SuppressWarnings("unchecked")
	public List<T> searchByExample(T exampleInstance, List<Range> rangeObjects) {
		Criteria criteria = createExampleCriteria(exampleInstance);
		for (Range range : rangeObjects) {
			range.updateCriterion(criteria);
		}

		addAssoications(criteria, exampleInstance);

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

		Object result = entityManager.createQuery(qryString).getSingleResult();

		return ((Long) entityManager.createQuery(qryString).getSingleResult());

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.witchcraft.model.support.dao.GenericDAO#performTextSearch(java.lang.String)
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
	public List<T> executeQuery(String queryString, Object... params) {
		Query query = entityManager.createQuery(queryString);
		runQueryWithParams(query, params);
		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	public T executeSingleResultQuery(String queryString, Object... params) {
		Query query = entityManager.createQuery(queryString);
		runQueryWithParams(query, params);
		return executeSingleResultQuery(query);
	}

	private T executeSingleResultQuery(Query query) {
		try {
			return (T) query.getSingleResult();
		} catch (NoResultException nre) {
			logger.info("No " + getPersistentClass().getSimpleName()
					+ " found !");
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	public List<T> executeNamedQuery(String queryString, Object... params) {
		Query query = entityManager.createNamedQuery(queryString);
		runQueryWithParams(query, params);
		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	public T executeSingleResultNamedQuery(String queryString, Object... params) {
		Query query = entityManager.createNamedQuery(queryString);
		runQueryWithParams(query, params);
		return executeSingleResultQuery(query); 
	}

	private void runQueryWithParams(Query query, Object... params) {
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
