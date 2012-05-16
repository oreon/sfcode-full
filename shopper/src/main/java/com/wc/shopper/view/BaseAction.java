package com.wc.shopper.view;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.PostConstruct;
import javax.ejb.Stateful;
import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import com.wc.shopper.domain.BaseEntity;


/**
 * @author singj3
 * 
 * @param <T>
 */
public abstract class BaseAction<T extends BaseEntity> {

	private Long id;

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	protected T entity;

	
	@Inject
	private Conversation conversation;

	@PersistenceContext(type = PersistenceContextType.EXTENDED)
	protected EntityManager entityManager;

	public String create() {

		this.conversation.begin();
		return "edit" + getEntityClass().getSimpleName() +  "?faces-redirect=true";
	}

	protected abstract Class<T> getEntityClass();

	public void retrieve() {

		if (FacesContext.getCurrentInstance().isPostback()) {
			return;
		}

		if (this.conversation.isTransient()) {
			this.conversation.begin();
		}

		if (this.id == null) {
			this.entity = this.search;
			if(entity == null)
				entity = createInstance();
		} else {
			this.entity = this.entityManager.find(getEntityClass(), getId());
		}
	}

	/*
	 * Support updating and deleting T entities
	 */

	public String update() {
		if(!conversation.isTransient())
			this.conversation.end();

		try {
			if (this.id == null) {
				//TODO: Identical code
				this.entityManager.merge(this.entity);
				return "view" + getEntityClass().getSimpleName() + "?faces-redirect=true&id=" + this.entity.getId();
			} else {
				this.entityManager.merge(this.entity);
				return "view" + getEntityClass().getSimpleName() + "?faces-redirect=true&id=" + this.entity.getId();
			}
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(e.getMessage()));
			return null;
		}
	}

	public String delete() {
		this.conversation.end();

		try {
			this.entityManager.remove(this.entityManager.find(getEntityClass(),
					getId()));
			this.entityManager.flush();
			return "search?faces-redirect=true";
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(e.getMessage()));
			return null;
		}
	}

	/*
	 * Support searching T entities with pagination
	 */

	private int page;
	private long count;
	private List<T> pageItems;

	protected T search = createInstance();
	
	
	public abstract T createInstance();

	public int getPage() {
		return this.page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getPageSize() {
		return 10;
	}

	public T getSearch() {
		return this.search;
	}

	public void setSearch(T search) {
		this.search = search;
	}

	public void search() {
		this.page = 0;
	}

	public void paginate() {

		CriteriaBuilder builder = this.entityManager.getCriteriaBuilder();

		// Populate this.count

		CriteriaQuery<Long> countCriteria = builder.createQuery(Long.class);
		Root<T> root = countCriteria.from(getEntityClass());
		
		countCriteria = countCriteria.select(builder.count(root)).where(
				getSearchPredicates(root));
		
		this.count = this.entityManager.createQuery(countCriteria)
				.getSingleResult();

		// Populate this.pageItems

		CriteriaQuery<T> criteria = builder.createQuery(getEntityClass());
		root = criteria.from(getEntityClass());
		TypedQuery<T> query = this.entityManager.createQuery(criteria.select(
				root).where(getSearchPredicates(root)));
		query.setFirstResult(this.page * getPageSize()).setMaxResults(
				getPageSize());
		this.pageItems = query.getResultList();
	}

	abstract Predicate[] getSearchPredicates(Root<T> root) ; 

	public List<T> getPageItems() {
		return this.pageItems;
	}

	public long getCount() {
		return this.count;
	}

	/*
	 * Support listing and POSTing back T entities (e.g. from inside an
	 * HtmlSelectOneMenu)
	 */

	public List<T> getAll() {

		CriteriaQuery<T> criteria = this.entityManager.getCriteriaBuilder()
				.createQuery(getEntityClass());
		return this.entityManager.createQuery(
				criteria.select(criteria.from(getEntityClass())))
				.getResultList();
	}

	public Converter getConverter() {

		return new Converter() {

			@Override
			public Object getAsObject(FacesContext context,
					UIComponent component, String value) {

				return (T ) entityManager.find(getEntityClass(),
						Long.valueOf(value));
			}

			@Override
			public String getAsString(FacesContext context,
					UIComponent component, Object value) {

				if (value == null) {
					return "";
				}

				return String.valueOf(((T ) value).getId());
			}
		};
	}

	/*
	 * Support adding children to bidirectional, one-to-many tables
	 */

	private T add = createInstance();

	public T getAdd() {
		return this.add;
	}

	public T getAdded() {
		T added = this.add;
		this.add = createInstance();
		return added;
	}
	
	///////////////////////////////// Lazy data model ///////////////////////////////////////////////////////////////////////////////////
	
	
	private LazyDataModel<T> model;

	public LazyDataModel<T> getModel() {
		return model;
	}
	

	protected class EntityLazyDataModel extends LazyDataModel<T> {
		
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		@Override
		public List<T> load( int first, int pageSize, String sortField, SortOrder sortOrder, Map<String,String> filters ) {
	
			setPageSize( pageSize );
			
			Session s = (Session) entityManager.getDelegate();

	 		Criteria crit = s.createCriteria(getEntityClass());

	 		if (sortField != null && !sortField.isEmpty()) {
	 			if (sortOrder == SortOrder.ASCENDING) {
	 				crit = crit.addOrder(Order.asc(sortField));
	 			} else {
	 				crit = crit.addOrder(Order.desc(sortField));
	 			}
	 		}

	 		if (!filters.isEmpty()) {
	 			Iterator<Entry<String, String>> iterator = filters.entrySet()
	 					.iterator();
	 			while (iterator.hasNext()) {
	 				Entry<String, String> entry = iterator.next();
	 				crit = crit.add(Restrictions.like(entry.getKey(),
	 						entry.getValue(), MatchMode.START));
	 			}
	 		}

	 		crit = crit.setFirstResult(first).setMaxResults(pageSize);

			model.setRowCount(  safeLongToInt (getCount() )  );
			
			
			
			return crit.list();
		}
		
		
		public Long getCount() {
	 		Session s = (Session) entityManager.getDelegate();

	 		Criteria crit = s.createCriteria(getEntityClass()).setProjection(
	 				Projections.rowCount());

	 		return (Long) crit.list().get(0);
	 	}

		@Override
		public Object getRowKey( T t ) {
			return t.getId();
		}

		@Override
		public T getRowData( String rowKey ) {
			T t = entityManager.find( getEntityClass(), Long.valueOf( rowKey ) );
			return t;
		}
		
		
		/* (non-Javadoc)
		 * @see org.primefaces.model.LazyDataModel#setRowIndex(int) 
		 *  Override created to address the issue - http://code.google.com/p/primefaces/issues/detail?id=1544
		 */
		@Override
	    public void setRowIndex(int rowIndex) {
	        /*
	         * The following is in ancestor (LazyDataModel):
	         * this.rowIndex = rowIndex == -1 ? rowIndex : (rowIndex % pageSize);
	         */
	        if (rowIndex == -1 || getPageSize() == 0) {
	            super.setRowIndex(-1);
	        }
	        else
	            super.setRowIndex(rowIndex % getPageSize());
	    }
		
		//TODO: should be moved to a utils class
		public  int safeLongToInt(long l) {
		    if (l < Integer.MIN_VALUE || l > Integer.MAX_VALUE) {
		        throw new IllegalArgumentException
		            (l + " cannot be cast to int without changing its value.");
		    }
		    return (int) l;
		}

	}
	
	

	@PostConstruct
	public void init() {
		model = new EntityLazyDataModel();
	}
	

}
