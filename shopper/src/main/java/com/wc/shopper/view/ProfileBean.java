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

import com.wc.shopper.domain.Profile;

/**
 * Backing bean for Profile entities.
 * <p>
 * This class provides CRUD functionality for all Profile entities. It focuses
 * purely on Java EE 6 standards (e.g. <tt>&#64;ConversationScoped</tt> for
 * state management, <tt>PersistenceContext</tt> for persistence,
 * <tt>CriteriaBuilder</tt> for searches) rather than introducing a CRUD framework or
 * custom base class.
 */

@Named
@Stateful
@ConversationScoped
public class ProfileBean implements Serializable {

	private static final long serialVersionUID = 1L;

	/*
	 * Support creating and retrieving Profile entities
	 */

	private Long id;

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	private Profile profile;

	public Profile getProfile() {
		return this.profile;
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
			this.profile = this.search;
		} else {
			this.profile = this.entityManager.find(Profile.class, getId());
		}
	}

	/*
	 * Support updating and deleting Profile entities
	 */

	public String update() {
		this.conversation.end();
		
		try {
			if (this.id == null) {
				this.entityManager.persist(this.profile);
				return "search?faces-redirect=true";			
			} else {
				this.entityManager.merge(this.profile);
				return "view?faces-redirect=true&id=" + this.profile.getId();
			}
		} catch( Exception e ) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage( e.getMessage() ));
			return null;
		}
	}

	public String delete() {
		this.conversation.end();

		try {
			this.entityManager.remove(this.entityManager.find(Profile.class,
					getId()));
			this.entityManager.flush();
			return "search?faces-redirect=true";
		} catch( Exception e ) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage( e.getMessage() ));
			return null;
		}
	}

	/*
	 * Support searching Profile entities with pagination
	 */

	private int page;
	private long count;
	private List<Profile> pageItems;
	
	private Profile search = new Profile();

	public int getPage() {
		return this.page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getPageSize() {
		return 10;
	}

	public Profile getSearch() {
		return this.search;
	}

	public void setSearch(Profile search) {
		this.search = search;
	}

	public void search() {
		this.page = 0;
	}

	public void paginate() {

		CriteriaBuilder builder = this.entityManager.getCriteriaBuilder();

		// Populate this.count

		CriteriaQuery<Long> countCriteria = builder.createQuery(Long.class);
		Root<Profile> root = countCriteria.from(Profile.class);
		countCriteria = countCriteria.select(builder.count(root)).where(
				getSearchPredicates(root));
		this.count = this.entityManager.createQuery(countCriteria)
				.getSingleResult();

		// Populate this.pageItems

		CriteriaQuery<Profile> criteria = builder.createQuery(Profile.class);
		root = criteria.from(Profile.class);
		TypedQuery<Profile> query = this.entityManager.createQuery(criteria
				.select(root).where(getSearchPredicates(root)));
		query.setFirstResult(this.page * getPageSize()).setMaxResults(
				getPageSize());
		this.pageItems = query.getResultList();
	}

	private Predicate[] getSearchPredicates(Root<Profile> root) {

		CriteriaBuilder builder = this.entityManager.getCriteriaBuilder();
		List<Predicate> predicatesList = new ArrayList<Predicate>();

		String bio = this.search.getBio();
		if (bio != null && !"".equals(bio)) {
			predicatesList.add(builder.like(root.<String>get("bio"), '%' + bio + '%'));
		}
		String preferredName = this.search.getPreferredName();
		if (preferredName != null && !"".equals(preferredName)) {
			predicatesList.add(builder.like(root.<String>get("preferredName"), '%' + preferredName + '%'));
		}
		String notes = this.search.getNotes();
		if (notes != null && !"".equals(notes)) {
			predicatesList.add(builder.like(root.<String>get("notes"), '%' + notes + '%'));
		}

		return predicatesList.toArray(new Predicate[predicatesList.size()]);
	}

	public List<Profile> getPageItems() {
		return this.pageItems;
	}

	public long getCount() {
		return this.count;
	}

	/*
	 * Support listing and POSTing back Profile entities (e.g. from inside an
	 * HtmlSelectOneMenu)
	 */

	public List<Profile> getAll() {

		CriteriaQuery<Profile> criteria = this.entityManager
				.getCriteriaBuilder().createQuery(Profile.class);
		return this.entityManager.createQuery(
				criteria.select(criteria.from(Profile.class))).getResultList();
	}

	public Converter getConverter() {

		return new Converter() {

			@Override
			public Object getAsObject(FacesContext context,
					UIComponent component, String value) {

				return ProfileBean.this.entityManager.find(Profile.class,
						Long.valueOf(value));
			}

			@Override
			public String getAsString(FacesContext context,
					UIComponent component, Object value) {

				if (value == null) {
					return "";
				}

				return String.valueOf(((Profile) value).getId());
			}
		};
	}
	
	/*
	 * Support adding children to bidirectional, one-to-many tables
	 */
	 
	private Profile add = new Profile();

	public Profile getAdd() {
		return this.add;
	}

	public Profile getAdded() {
		Profile added = this.add;
		this.add = new Profile();
		return added;
	}
}