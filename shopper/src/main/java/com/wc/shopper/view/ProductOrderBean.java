package com.wc.shopper.view;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

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
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.wc.shopper.domain.ProductOrder;
import com.wc.shopper.domain.Address;
import com.wc.shopper.domain.Customer;

/**
 * Backing bean for ProductOrder entities.
 * <p>
 * This class provides CRUD functionality for all ProductOrder entities. It focuses
 * purely on Java EE 6 standards (e.g. <tt>&#64;ConversationScoped</tt> for
 * state management, <tt>PersistenceContext</tt> for persistence,
 * <tt>CriteriaBuilder</tt> for searches) rather than introducing a CRUD framework or
 * custom base class.
 */

@Named
@Stateful
@ConversationScoped
public class ProductOrderBean implements Serializable {

	private static final long serialVersionUID = 1L;

	/*
	 * Support creating and retrieving ProductOrder entities
	 */

	private Long id;

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	private ProductOrder productOrder;

	public ProductOrder getProductOrder() {
		return this.productOrder;
	}

	@Inject
	private Conversation conversation;

	@PersistenceContext(type = PersistenceContextType.EXTENDED)
	private EntityManager entityManager;

	public String create() {

		this.conversation.begin();
		return "create?faces-redirect=true";
	}
	
	public void retrieve() {

		if (FacesContext.getCurrentInstance().isPostback()) {
			return;
		}

		if (this.conversation.isTransient()) {
			this.conversation.begin();
		}

		if (this.id == null) {
			this.productOrder = this.search;
		} else {
			this.productOrder = this.entityManager.find(ProductOrder.class, getId());
		}
	}

	/*
	 * Support updating and deleting ProductOrder entities
	 */

	public String update() {
		this.conversation.end();
		
		try {
			if (this.id == null) {
				this.entityManager.persist(this.productOrder);
				return "search?faces-redirect=true";			
			} else {
				this.entityManager.merge(this.productOrder);
				return "view?faces-redirect=true&id=" + this.productOrder.getId();
			}
		} catch( Exception e ) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage( e.getMessage() ));
			return null;
		}
	}

	public String delete() {
		this.conversation.end();

		try {
			this.entityManager.remove(this.entityManager.find(ProductOrder.class,
					getId()));
			this.entityManager.flush();
			return "search?faces-redirect=true";
		} catch( Exception e ) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage( e.getMessage() ));
			return null;
		}
	}

	/*
	 * Support searching ProductOrder entities with pagination
	 */

	private int page;
	private long count;
	private List<ProductOrder> pageItems;
	
	private ProductOrder search = new ProductOrder();

	public int getPage() {
		return this.page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getPageSize() {
		return 10;
	}

	public ProductOrder getSearch() {
		return this.search;
	}

	public void setSearch(ProductOrder search) {
		this.search = search;
	}

	public void search() {
		this.page = 0;
	}

	public void paginate() {

		CriteriaBuilder builder = this.entityManager.getCriteriaBuilder();

		// Populate this.count

		CriteriaQuery<Long> countCriteria = builder.createQuery(Long.class);
		Root<ProductOrder> root = countCriteria.from(ProductOrder.class);
		countCriteria = countCriteria.select(builder.count(root)).where(
				getSearchPredicates(root));
		this.count = this.entityManager.createQuery(countCriteria)
				.getSingleResult();

		// Populate this.pageItems

		CriteriaQuery<ProductOrder> criteria = builder.createQuery(ProductOrder.class);
		root = criteria.from(ProductOrder.class);
		TypedQuery<ProductOrder> query = this.entityManager.createQuery(criteria
				.select(root).where(getSearchPredicates(root)));
		query.setFirstResult(this.page * getPageSize()).setMaxResults(
				getPageSize());
		this.pageItems = query.getResultList();
	}

	private Predicate[] getSearchPredicates(Root<ProductOrder> root) {

		CriteriaBuilder builder = this.entityManager.getCriteriaBuilder();
		List<Predicate> predicatesList = new ArrayList<Predicate>();

		Customer customer = this.search.getCustomer();
		if (customer != null) {
			predicatesList.add(builder.equal(root.get("customer"), customer));
		}
		Address shippingAddress = this.search.getShippingAddress();
		if (shippingAddress != null) {
			predicatesList.add(builder.equal(root.get("shippingAddress"), shippingAddress));
		}

		return predicatesList.toArray(new Predicate[predicatesList.size()]);
	}

	public List<ProductOrder> getPageItems() {
		return this.pageItems;
	}

	public long getCount() {
		return this.count;
	}

	/*
	 * Support listing and POSTing back ProductOrder entities (e.g. from inside an
	 * HtmlSelectOneMenu)
	 */

	public List<ProductOrder> getAll() {

		CriteriaQuery<ProductOrder> criteria = this.entityManager
				.getCriteriaBuilder().createQuery(ProductOrder.class);
		return this.entityManager.createQuery(
				criteria.select(criteria.from(ProductOrder.class))).getResultList();
	}

	public Converter getConverter() {

		return new Converter() {

			@Override
			public Object getAsObject(FacesContext context,
					UIComponent component, String value) {

				return ProductOrderBean.this.entityManager.find(ProductOrder.class,
						Long.valueOf(value));
			}

			@Override
			public String getAsString(FacesContext context,
					UIComponent component, Object value) {

				if (value == null) {
					return "";
				}

				return String.valueOf(((ProductOrder) value).getId());
			}
		};
	}
	
	/*
	 * Support adding children to bidirectional, one-to-many tables
	 */
	 
	private ProductOrder add = new ProductOrder();

	public ProductOrder getAdd() {
		return this.add;
	}

	public ProductOrder getAdded() {
		ProductOrder added = this.add;
		this.add = new ProductOrder();
		return added;
	}
}