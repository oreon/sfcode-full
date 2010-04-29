package org.witchcraft.base.entity;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;

import javax.persistence.Query;

import org.jboss.seam.framework.EntityQuery;
import org.jboss.seam.persistence.PersistenceProvider;

@SuppressWarnings("serial")
public class BaseQuery<E extends Serializable,PK extends Serializable> extends EntityQuery<E> {

	private Class<E> entityClass = null;

	protected E instance;

	public BaseQuery() {
		String simpleEntityName = getSimpleEntityName().toLowerCase();
		setEjbql("SELECT "+simpleEntityName+" from " + getEntityName() + " " + simpleEntityName);
	}

	/**
	 * Get the class of the entity being managed.
	 * <br />
	 * If not explicitly specified, the generic type of implementation is used.
	 */
	@SuppressWarnings("unchecked")
	public Class<E> getEntityClass() {
		if (entityClass == null)
		{
			Type type = getClass().getGenericSuperclass();

			if (type instanceof ParameterizedType)
			{
				ParameterizedType paramType = (ParameterizedType) type;
				entityClass = (Class<E>) paramType.getActualTypeArguments()[0];
			}
			else
			{
				type = getClass().getSuperclass().getGenericSuperclass();
				if (type instanceof ParameterizedType)
				{
					ParameterizedType paramType = (ParameterizedType) type;
					entityClass = (Class<E>) paramType.getActualTypeArguments()[0];
				}
				else
				{
					throw new IllegalArgumentException("Could not guess entity class by reflection");
				}
			}
		}
		return entityClass;
	}

	public E getInstance() {
		if (instance==null){
			setInstance( createInstance() );
		}
		return instance;
	}


	public void setInstance(E instance) {
		this.instance = instance;
	}

	/**
	 * Create a new instance of the entity.
	 * <br />
	 * Utility method called by {@link #getInstance()} to create a new instance
	 * of the entity.
	 */
	protected E createInstance() {
		if (getEntityClass()!=null) {
			try {
				return getEntityClass().newInstance();
			}
			catch (Exception e) {
				throw new RuntimeException(e);
			}
		} else {
			return null;
		}
	}

	/**
	 * The simple name of the entity
	 */
	protected String getSimpleEntityName()
	{
		String name = getEntityName();
		if (name != null)
		{
			return name.lastIndexOf(".") > 0 && name.lastIndexOf(".") < name.length()  ? name.substring(name.lastIndexOf(".") + 1, name.length()) : name;
		}
		else
		{
			return null;
		}
	}

	protected String getEntityName()
	{
		try
		{
			return PersistenceProvider.instance().getName(getInstance(), getEntityManager());
		}
		catch (IllegalArgumentException e)
		{
			// Handle that the passed object may not be an entity
			return null;
		}
	}

	public E getObjectByID(PK id) {
		return (E) getEntityManager().find(getEntityClass(), id);
	}

	/**
	 * Get records by a named query. Can also optionally get all records starting
	 * at a start position and the count of records to return
	 * @param name
	 * @param parameters
	 * @param rowStartIndexAndCount
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public Collection<E> findRecordsByName(final String name, final Map parameters, final int... rowStartIndexAndCount) {
		Query query = getEntityManager().createNamedQuery(name);
		if (parameters != null) {
			for (Iterator<Map.Entry<String, Object>> it = parameters.entrySet().iterator(); it.hasNext();) {
				Map.Entry<String, Object> entry = it.next();
				query.setParameter(entry.getKey(), entry.getValue());
			}
		}
		setupQueryForStartIndexAndCount(query, rowStartIndexAndCount);
		return query.getResultList();
	}

	/**
	 * Find a single record by a named query
	 * @param name
	 * @param parameters
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public E findRecordByName(String name, Map parameters) {
		Query query = getEntityManager().createNamedQuery(name);
		if (parameters != null) {
			for (Iterator<Map.Entry<String, Object>> it = parameters.entrySet().iterator(); it.hasNext();) {
				Map.Entry<String, Object> entry = it.next();
				query.setParameter(entry.getKey(), entry.getValue());
			}
		}
		return (E)query.getSingleResult();

	}

	/**
	 * Helper method to setup a query with a start position and the count of records to return.
	 * @param query
	 * @param rowStartIndexAndCount
	 */
	private void setupQueryForStartIndexAndCount(Query query, final int... rowStartIndexAndCount) {
		if (rowStartIndexAndCount != null && rowStartIndexAndCount.length > 0) {
			int rowStartIdx = Math.max(0, rowStartIndexAndCount[0]);
			if (rowStartIdx > 0) {
				query.setFirstResult(rowStartIdx);
			}
			if (rowStartIndexAndCount.length > 1) {
				int rowCount = Math.max(0, rowStartIndexAndCount[1]);
				if (rowCount > 0) {
					query.setMaxResults(rowCount);
				}
			}
		}
	}

}