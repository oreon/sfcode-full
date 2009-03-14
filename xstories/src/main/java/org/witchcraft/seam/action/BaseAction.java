package org.witchcraft.seam.action;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.MatchMode;
import org.jboss.seam.annotations.Begin;
import org.jboss.seam.annotations.End;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Logger;
import org.jboss.seam.core.Events;
import org.jboss.seam.faces.FacesMessages;
import org.jboss.seam.log.Log;
import org.witchcraft.base.entity.BusinessEntity;


public abstract class BaseAction<T extends BusinessEntity> {
	
	public EntityManager getEntityManager() {
		return entityManager;
	}

	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	@Logger
	protected Log log;
	@In
	// @PersistenceContext(type=EXTENDED)
	protected EntityManager entityManager;

	@In
	protected FacesMessages facesMessages;
	
	@In
	protected Events events;


	@Begin
	public String select(T t) {
		setEntity( entityManager.merge(t) );
		log.info("User selected #{t.getClass().getName()}: #{t.displayName} #{t.id} ");
		return "select";
	}
	
	public String archive(T t) {
		t.setArchived(true);
		entityManager.merge(t);
		facesMessages.add("Successfully archived  #{t.displayName}");
		log.info("User archived #{t.getClass().getName()}: #{t.displayName} #{t.id} ");
		events.raiseTransactionSuccessEvent("t archived");
		return "";
	}

	@End
	public String save() {
		facesMessages.add("Successfully saved  #{t.displayName}");
		updateComposedAssociations();
		entityManager.persist(getEntity());
		return "save";
	}

	@End
	public String cancel() {
		return "cancel";
	}
	
	public void search(){
		Criteria criteria = createExampleCriteria();
		setEntityList( criteria.list() );
	}
	
	public void clearSearch(){
		try {
			setEntity( (T) getEntity().getClass().newInstance() );
			//TODO: do exception handling
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		findRecords();
	}

	public Criteria createExampleCriteria() {
		Session session = (Session) entityManager.getDelegate();

		Example example = Example.create( getEntity() ).enableLike(
				MatchMode.START).ignoreCase().excludeZeroes();

		Criteria criteria = session.createCriteria(getEntity().getClass()).add(example);
		/*
		for (String exclude : excludedProperties) {
			example.excludeProperty(exclude);
		}*/
		addAssoications(criteria);
		
		return criteria;
	}
	
	/** This method should be overloaded by actions to add associations e.g. an order would need the
	 * associated customer
	 * @param criteria
	 */
	public void addAssoications(Criteria criteria) {
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
	public<S> List<S> executeQuery(String queryString, Object... params) {
		Query query = entityManager.createQuery(queryString);
		setQueryParams(query, params);
		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	public<S> S executeSingleResultQuery(String queryString, Object... params) {
		Query query = entityManager.createQuery(queryString);
		setQueryParams(query, params);
		return (S)executeSingleResultQuery(query);
	}

	private<S> S executeSingleResultQuery(Query query) {
		try {
			return (S) query.getSingleResult();
		} catch (NoResultException nre) {
			log.info("No " + "record " + " found !");
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	public<S> List<S> executeNamedQuery(String queryString, Object... params) {
		Query query = entityManager.createNamedQuery(queryString);
		setQueryParams(query, params);
		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	public<S> S executeSingleResultNamedQuery(String queryString, Object... params) {
		Query query = entityManager.createNamedQuery(queryString);
		setQueryParams(query, params);
		return (S)executeSingleResultQuery(query); 
	}
	
	@SuppressWarnings("unchecked")
	public<S> S executeSingleResultNativeQuery(String queryString, Object... params) {
		Query query = entityManager.createNativeQuery(queryString);
		setQueryParams(query, params);
		return (S)query.getSingleResult();
	}
	
	@SuppressWarnings("unchecked")
	public<S> List<S> executeNativeQuery(String queryString, Object... params) {
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
	
	

}
