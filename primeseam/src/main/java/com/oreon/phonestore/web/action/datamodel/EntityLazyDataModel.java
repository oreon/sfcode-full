/**
 * 
 */
package com.oreon.phonestore.web.action.datamodel;

import java.util.List;

import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.ajax4jsf.model.DataVisitor;
import org.ajax4jsf.model.ExtendedDataModel;
import org.ajax4jsf.model.Range;
import org.ajax4jsf.model.SequenceRange;
import org.richfaces.model.Arrangeable;
import org.richfaces.model.ArrangeableState;

/**
 * @author Shadeven
 * @param <T>
 *
 */
public abstract class EntityLazyDataModel<T> extends ExtendedDataModel<T> implements Arrangeable {

	private EntityManager entityManager;
	
	private Object rowKey;
	
	private ArrangeableState arrangeableState;
	
	private Class<T> entityClass;
	
	private String queryString;
	
	public EntityLazyDataModel(EntityManager entityManager, Class<T> entityClass, String queryString) {
		super();
		this.entityManager = entityManager;
		this.entityClass = entityClass;
		this.queryString = queryString;
	}

	/* (non-Javadoc)
	 * @see org.richfaces.model.Arrangeable#arrange(javax.faces.context.FacesContext, org.richfaces.model.ArrangeableState)
	 */
	@Override
	public void arrange(FacesContext context, ArrangeableState state) {
		arrangeableState = state;
	}

	/* (non-Javadoc)
	 * @see org.ajax4jsf.model.ExtendedDataModel#getRowKey()
	 */
	@Override
	public Object getRowKey() {
		return rowKey;
	}

	/* (non-Javadoc)
	 * @see org.ajax4jsf.model.ExtendedDataModel#setRowKey(java.lang.Object)
	 */
	@Override
	public void setRowKey(Object key) {
		this.rowKey = key;
	}

	/* (non-Javadoc)
	 * @see org.ajax4jsf.model.ExtendedDataModel#walk(javax.faces.context.FacesContext, org.ajax4jsf.model.DataVisitor, org.ajax4jsf.model.Range, java.lang.Object)
	 */
	@Override
	public void walk(FacesContext context, DataVisitor visitor, Range range, Object argument) {
		// TODO Should we use TypedQuery instead?
		System.out.println("Inside walk method...");
		Query query = entityManager.createQuery(queryString);
		
		SequenceRange sequenceRange = (SequenceRange) range;
		if (sequenceRange.getFirstRow() >= 0 && sequenceRange.getRows() > 0) {
			query.setFirstResult(sequenceRange.getFirstRow());
			query.setMaxResults(sequenceRange.getRows());
		}
		
		List<T> resultList = query.getResultList();
		for (T object : resultList) {
			visitor.process(context, getId(object), argument);
		}
	}

	protected abstract Object getId(T t);
	
	/* (non-Javadoc)
	 * @see javax.faces.model.DataModel#getRowCount()
	 */
	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return 0;
	}

	/* (non-Javadoc)
	 * @see javax.faces.model.DataModel#getRowData()
	 */
	@Override
	public T getRowData() {
		return entityManager.find(entityClass, rowKey);
	}

	/* (non-Javadoc)
	 * @see javax.faces.model.DataModel#getRowIndex()
	 */
	@Override
	public int getRowIndex() {
		return -1;
	}

	/* (non-Javadoc)
	 * @see javax.faces.model.DataModel#getWrappedData()
	 */
	@Override
	public Object getWrappedData() {
		throw new UnsupportedOperationException();
	}

	/* (non-Javadoc)
	 * @see javax.faces.model.DataModel#isRowAvailable()
	 */
	@Override
	public boolean isRowAvailable() {
		return rowKey != null;
	}

	/* (non-Javadoc)
	 * @see javax.faces.model.DataModel#setRowIndex(int)
	 */
	@Override
	public void setRowIndex(int rowIndex) {
		throw new UnsupportedOperationException();
	}

	/* (non-Javadoc)
	 * @see javax.faces.model.DataModel#setWrappedData(java.lang.Object)
	 */
	@Override
	public void setWrappedData(Object data) {
		throw new UnsupportedOperationException();
	}

	protected ArrangeableState getArrangeableState() {
		return arrangeableState;
	}
	
}
