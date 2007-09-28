package org.witchcraft.model.support.dao;

import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.MatchMode;

import bizobjects.Customer;

public class BaseDao<T>  implements GenericDAO<T> {

	private Class<T> persistentClass;
	// private Session session;

	protected EntityManager entityManager;

	@PersistenceContext
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	@SuppressWarnings("unchecked")
	public BaseDao() {
		this.persistentClass = (Class<T>) ((ParameterizedType) getClass()
				.getGenericSuperclass()).getActualTypeArguments()[0];
	}

	public Class<T> getPersistentClass() {
		return persistentClass;
	}

	/**
	 * This method saves or updates the given entity based upon whether the id
	 * is null
	 */
	public T save(T entity) {

		if (!entityManager.contains(entity))
			entityManager.persist(entity);
		else
			entityManager.merge(entity);

		return entity;
	}

	public T load(Long id) {
		return entityManager.find(getPersistentClass(), id);
	}

	@SuppressWarnings("unchecked")
	public List<T> loadAll() {
		String qryString = "select e from " + getPersistentClass().getSimpleName() + "  e ";
		Query query = entityManager.createQuery(qryString);
		return query.getResultList();
	}

	public void delete(T entity) {
		entityManager.remove(entity);
	}

	@SuppressWarnings("unchecked")
	public List<T> searchByExample(T exampleInstance) {
		
		Session session = (Session) entityManager.getDelegate();

		Criteria criteria = session.createCriteria(Customer.class).add(
				Example.create(getPersistentClass()).enableLike(
						MatchMode.START).ignoreCase().excludeZeroes()
						.excludeProperty("dateModified")
						.excludeProperty("id").excludeProperty(
								"dateCreated"));
		return criteria.list();
		
	}

}
