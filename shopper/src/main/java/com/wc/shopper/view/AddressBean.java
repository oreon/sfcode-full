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

import com.wc.shopper.domain.Address;
import com.wc.shopper.domain.ZipCode;

/**
 * Backing bean for Address entities.
 * <p>
 * This class provides CRUD functionality for all Address entities. It focuses
 * purely on Java EE 6 standards (e.g. <tt>&#64;ConversationScoped</tt> for
 * state management, <tt>PersistenceContext</tt> for persistence,
 * <tt>CriteriaBuilder</tt> for searches) rather than introducing a CRUD framework or
 * custom base class.
 */

@Named
@Stateful
@ConversationScoped
public class AddressBean implements Serializable {

	private static final long serialVersionUID = 1L;

	/*
	 * Support creating and retrieving Address entities
	 */

	private Long id;

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	private Address address;

	public Address getAddress() {
		return this.address;
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
			this.address = this.search;
		} else {
			this.address = this.entityManager.find(Address.class, getId());
		}
	}

	/*
	 * Support updating and deleting Address entities
	 */

	public String update() {
		this.conversation.end();
		
		try {
			if (this.id == null) {
				this.entityManager.persist(this.address);
				return "search?faces-redirect=true";			
			} else {
				this.entityManager.merge(this.address);
				return "view?faces-redirect=true&id=" + this.address.getId();
			}
		} catch( Exception e ) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage( e.getMessage() ));
			return null;
		}
	}

	public String delete() {
		this.conversation.end();

		try {
			this.entityManager.remove(this.entityManager.find(Address.class,
					getId()));
			this.entityManager.flush();
			return "search?faces-redirect=true";
		} catch( Exception e ) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage( e.getMessage() ));
			return null;
		}
	}

	/*
	 * Support searching Address entities with pagination
	 */

	private int page;
	private long count;
	private List<Address> pageItems;
	
	private Address search = new Address();

	public int getPage() {
		return this.page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getPageSize() {
		return 10;
	}

	public Address getSearch() {
		return this.search;
	}

	public void setSearch(Address search) {
		this.search = search;
	}

	public void search() {
		this.page = 0;
	}

	public void paginate() {

		CriteriaBuilder builder = this.entityManager.getCriteriaBuilder();

		// Populate this.count

		CriteriaQuery<Long> countCriteria = builder.createQuery(Long.class);
		Root<Address> root = countCriteria.from(Address.class);
		countCriteria = countCriteria.select(builder.count(root)).where(
				getSearchPredicates(root));
		this.count = this.entityManager.createQuery(countCriteria)
				.getSingleResult();

		// Populate this.pageItems

		CriteriaQuery<Address> criteria = builder.createQuery(Address.class);
		root = criteria.from(Address.class);
		TypedQuery<Address> query = this.entityManager.createQuery(criteria
				.select(root).where(getSearchPredicates(root)));
		query.setFirstResult(this.page * getPageSize()).setMaxResults(
				getPageSize());
		this.pageItems = query.getResultList();
	}

	private Predicate[] getSearchPredicates(Root<Address> root) {

		CriteriaBuilder builder = this.entityManager.getCriteriaBuilder();
		List<Predicate> predicatesList = new ArrayList<Predicate>();

		String street = this.search.getStreet();
		if (street != null && !"".equals(street)) {
			predicatesList.add(builder.like(root.<String>get("street"), '%' + street + '%'));
		}
		String city = this.search.getCity();
		if (city != null && !"".equals(city)) {
			predicatesList.add(builder.like(root.<String>get("city"), '%' + city + '%'));
		}
		ZipCode zip = this.search.getZip();
		if (zip != null) {
			predicatesList.add(builder.equal(root.get("zip"), zip));
		}

		return predicatesList.toArray(new Predicate[predicatesList.size()]);
	}

	public List<Address> getPageItems() {
		return this.pageItems;
	}

	public long getCount() {
		return this.count;
	}

	/*
	 * Support listing and POSTing back Address entities (e.g. from inside an
	 * HtmlSelectOneMenu)
	 */

	public List<Address> getAll() {

		CriteriaQuery<Address> criteria = this.entityManager
				.getCriteriaBuilder().createQuery(Address.class);
		return this.entityManager.createQuery(
				criteria.select(criteria.from(Address.class))).getResultList();
	}

	public Converter getConverter() {

		return new Converter() {

			@Override
			public Object getAsObject(FacesContext context,
					UIComponent component, String value) {

				return AddressBean.this.entityManager.find(Address.class,
						Long.valueOf(value));
			}

			@Override
			public String getAsString(FacesContext context,
					UIComponent component, Object value) {

				if (value == null) {
					return "";
				}

				return String.valueOf(((Address) value).getId());
			}
		};
	}
	
	/*
	 * Support adding children to bidirectional, one-to-many tables
	 */
	 
	private Address add = new Address();

	public Address getAdd() {
		return this.add;
	}

	public Address getAdded() {
		Address added = this.add;
		this.add = new Address();
		return added;
	}
}