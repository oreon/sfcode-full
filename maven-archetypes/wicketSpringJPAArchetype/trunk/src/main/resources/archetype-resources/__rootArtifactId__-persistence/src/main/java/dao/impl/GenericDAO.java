package ${package}.dao.impl;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import ${package}.dao.IGenericDAO;

/**
 * JPA as a persistence provider implementation of the {@link IGenericDAO}.
 * 
 * @author Kamalpreet Singh
 *
 * @param <T> the entity type
 * @param <ID> the primary key type
 */
public abstract class GenericDAO<T, ID extends Serializable>implements IGenericDAO<T, ID> {

	/** Logger for this class. */
	private static Log log = LogFactory.getLog(GenericDAO.class);

	/** Generic instance field that holds the entity class-type of any concrete implementation. */
	private final Class<T> persistentClass;
	
	/** The JPA EntityManager that holds the persistence context. */
	@PersistenceContext
	private EntityManager entityManager;

	/**
	 * Constructor that gets the class-type of a concrete implementation by reflection.
	 */
	@SuppressWarnings("unchecked")
	public GenericDAO() {
		Class<T> localPersistentClass = null;
		Class clazz = getClass();
		Class superclass = clazz.getSuperclass();
		
		if (clazz.getSuperclass().getTypeParameters().length > 0) {
			localPersistentClass = (Class<T>) ((ParameterizedType) clazz.getGenericSuperclass()).getActualTypeArguments()[0];
		} else if (superclass.getSuperclass().getTypeParameters().length > 0) {
		    localPersistentClass = (Class<T>) ((ParameterizedType) superclass.getGenericSuperclass()).getActualTypeArguments()[0];
		}
		
		this.persistentClass = localPersistentClass;
	}

	/**
	 * @return the persistentClass
	 */
	public Class<T> getPersistentClass() {
		return persistentClass;
	}

	/**
	 * @return the entityManager
	 */
	public EntityManager getEntityManager() {
		return entityManager;
	}
	
	@Override
	public T create(T entity) {
		this.entityManager.persist(entity);
		return entity;
	}

	@Override
	public T update(T entity) {
		return this.entityManager.merge(entity);
	}

	@Override
	public void flagDelete(T entity) {
		this.entityManager.merge(entity);
	}
	
	@Override
	public void delete(T entity) {
		this.entityManager.remove(entity);
	}

	@Override
	public T findById(ID id) {
		return this.entityManager.find(getPersistentClass(), id);
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public List<T> findAll() {
		Query query = entityManager.createQuery("select entity from " + getPersistentClass().getSimpleName() + " entity");
		return query.getResultList();
	}

	/*@Override
	@SuppressWarnings("unchecked")
	public <S> List<S> executeQuery(String queryString, Object... parameters) {
		Query query = entityManager.createQuery(queryString);
		setQueryParameters(query, parameters);
		return query.getResultList();
	}*/
	
	@Override
	@SuppressWarnings("unchecked")
	public <S> S executeSingleResultQuery(String queryString, Object... parameters) {
		Query query = entityManager.createQuery(queryString);
		setQueryParameters(query, parameters);
		return (S) executeSingleResultQuery(query);
	}
	
	@SuppressWarnings("unchecked")
	private <S> S executeSingleResultQuery(Query query) {
		try {
			return (S) query.getSingleResult();
		} catch (NoResultException noResultException) {
			//log.info("No " + "record " + " found !");
			return null;
		}
	}
	
	/**
	 * @param query
	 * @param parameters
	 */
	private void setQueryParameters(Query query, Object... parameters) {
		int counter = 1;
		
		for (Object parameter : parameters) {
			query.setParameter(counter++, parameter);
		}
	}
	
	/*@Override
	public List<T> findByExample(T exampleInstance) {
		return null;
	}*/

	/*@Override
	public List<T> findByExample(T exampleInstance, String... excludeProperty) {
		return null;
	}*/
}
