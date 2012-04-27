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

import com.wc.shopper.domain.ZipCode;

/**
 * Backing bean for ZipCode entities.
 * <p>
 * This class provides CRUD functionality for all ZipCode entities. It focuses
 * purely on Java EE 6 standards (e.g. <tt>&#64;ConversationScoped</tt> for
 * state management, <tt>PersistenceContext</tt> for persistence,
 * <tt>CriteriaBuilder</tt> for searches) rather than introducing a CRUD framework or
 * custom base class.
 */

@Named
@Stateful
@ConversationScoped
public class ZipCodeBean implements Serializable {

	private static final long serialVersionUID = 1L;

	/*
	 * Support creating and retrieving ZipCode entities
	 */

	private Long id;

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	private ZipCode zipCode;

	public ZipCode getZipCode() {
		return this.zipCode;
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
			this.zipCode = this.search;
		} else {
			this.zipCode = this.entityManager.find(ZipCode.class, getId());
		}
	}

	/*
	 * Support updating and deleting ZipCode entities
	 */

	public String update() {
		this.conversation.end();
		
		try {
			if (this.id == null) {
				this.entityManager.persist(this.zipCode);
				return "search?faces-redirect=true";			
			} else {
				this.entityManager.merge(this.zipCode);
				return "view?faces-redirect=true&id=" + this.zipCode.getId();
			}
		} catch( Exception e ) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage( e.getMessage() ));
			return null;
		}
	}

	public String delete() {
		this.conversation.end();

		try {
			this.entityManager.remove(this.entityManager.find(ZipCode.class,
					getId()));
			this.entityManager.flush();
			return "search?faces-redirect=true";
		} catch( Exception e ) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage( e.getMessage() ));
			return null;
		}
	}

	/*
	 * Support searching ZipCode entities with pagination
	 */

	private int page;
	private long count;
	private List<ZipCode> pageItems;
	
	private ZipCode search = new ZipCode();

	public int getPage() {
		return this.page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getPageSize() {
		return 10;
	}

	public ZipCode getSearch() {
		return this.search;
	}

	public void setSearch(ZipCode search) {
		this.search = search;
	}

	public void search() {
		this.page = 0;
	}

	public void paginate() {

		CriteriaBuilder builder = this.entityManager.getCriteriaBuilder();

		// Populate this.count

		CriteriaQuery<Long> countCriteria = builder.createQuery(Long.class);
		Root<ZipCode> root = countCriteria.from(ZipCode.class);
		countCriteria = countCriteria.select(builder.count(root)).where(
				getSearchPredicates(root));
		this.count = this.entityManager.createQuery(countCriteria)
				.getSingleResult();

		// Populate this.pageItems

		CriteriaQuery<ZipCode> criteria = builder.createQuery(ZipCode.class);
		root = criteria.from(ZipCode.class);
		TypedQuery<ZipCode> query = this.entityManager.createQuery(criteria
				.select(root).where(getSearchPredicates(root)));
		query.setFirstResult(this.page * getPageSize()).setMaxResults(
				getPageSize());
		this.pageItems = query.getResultList();
	}

	private Predicate[] getSearchPredicates(Root<ZipCode> root) {

		CriteriaBuilder builder = this.entityManager.getCriteriaBuilder();
		List<Predicate> predicatesList = new ArrayList<Predicate>();

		int code = this.search.getCode();
		if (code != 0) {
			predicatesList.add(builder.equal(root.get("code"), code));
		}

		return predicatesList.toArray(new Predicate[predicatesList.size()]);
	}

	public List<ZipCode> getPageItems() {
		return this.pageItems;
	}

	public long getCount() {
		return this.count;
	}

	/*
	 * Support listing and POSTing back ZipCode entities (e.g. from inside an
	 * HtmlSelectOneMenu)
	 */

	public List<ZipCode> getAll() {

		CriteriaQuery<ZipCode> criteria = this.entityManager
				.getCriteriaBuilder().createQuery(ZipCode.class);
		return this.entityManager.createQuery(
				criteria.select(criteria.from(ZipCode.class))).getResultList();
	}

	public Converter getConverter() {

		return new Converter() {

			@Override
			public Object getAsObject(FacesContext context,
					UIComponent component, String value) {

				return ZipCodeBean.this.entityManager.find(ZipCode.class,
						Long.valueOf(value));
			}

			@Override
			public String getAsString(FacesContext context,
					UIComponent component, Object value) {

				if (value == null) {
					return "";
				}

				return String.valueOf(((ZipCode) value).getId());
			}
		};
	}
	
	/*
	 * Support adding children to bidirectional, one-to-many tables
	 */
	 
	private ZipCode add = new ZipCode();

	public ZipCode getAdd() {
		return this.add;
	}

	public ZipCode getAdded() {
		ZipCode added = this.add;
		this.add = new ZipCode();
		return added;
	}
}