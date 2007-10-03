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
import org.springframework.stereotype.Repository;
import org.witchcraft.model.support.BusinessEntity;

@Repository
public class BaseDao<T> implements GenericDAO<T> {

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

		/*
		 * FIXME : This code will make if (!entityManager.contains(entity))
		 * entityManager.persist(entity); else entityManager.merge(entity);
		 */

		BusinessEntity be = (BusinessEntity) entity;

		if (be.getId() != null)
			entityManager.merge(entity);
		else
			entityManager.persist(entity);

		return entity;
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

	public void delete(T entity) {
		entityManager.remove(entity);
	}

	private String excludedProperties[] = { "dateModified", "dateCreated", "id" };

	@SuppressWarnings("unchecked")
	public List<T> searchByExample(T exampleInstance) {

		Session session = (Session) entityManager.getDelegate();

		Example example = Example.create(exampleInstance).enableLike(
				MatchMode.START).ignoreCase().excludeZeroes();

		Criteria criteria = session.createCriteria(getPersistentClass()).add(
				example);

		for (String exclude : excludedProperties) {
			example.excludeProperty(exclude);
		}

		List list = criteria.list();

		return list;
	}
}
