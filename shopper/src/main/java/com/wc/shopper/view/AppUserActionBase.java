package com.wc.shopper.view;

import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.primefaces.model.DualListModel;

import com.wc.shopper.domain.AppRole;
import com.wc.shopper.domain.AppUser;

public abstract class AppUserActionBase extends BaseAction<AppUser> implements java.io.Serializable {

	private DualListModel<AppRole> listAppRoles;

	// @Inject
	// AppRoleAction appRoleAction;

	protected Predicate[] getSearchPredicates( Root<AppUser> root ) {

		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		List<Predicate> predicatesList = new ArrayList<Predicate>();

		/*
		 * String name = search.getName(); if (name != null && !"".equals(name)) { predicatesList.add(builder.like(root.<String> get("name"), '%' + name +
		 * '%')); }
		 */
		/*
		 * int stock = search.getStock(); if (stock != 0) { predicatesList.add(builder.equal(root.get("stock"), stock)); }
		 */

		return predicatesList.toArray( new Predicate[predicatesList.size()] );
	}

	protected void initLists() {

		try {
			Context initialContext = new InitialContext();
			AppRoleAction appRoleAction = (AppRoleAction) initialContext.lookup( "java:module/AppRoleAction" );

			List<AppRole> availableRoles = appRoleAction.getAll();
			List<AppRole> currentRoles = getAppUser() != null ? ViewUtils.asList( getAppUser().getAppRoles() )  : new ArrayList<AppRole>(); 
			
			if(availableRoles == null)
				availableRoles = new ArrayList<AppRole>();
			if(getAppUser() != null)
				availableRoles.removeAll( getAppUser().getAppRoles() );
			
			listAppRoles = new DualListModel<AppRole>( availableRoles, currentRoles );
		} catch ( Exception e ) {
			e.printStackTrace();
		}
	}

	@Override
	protected Class<AppUser> getEntityClass() {
		return AppUser.class;
	}

	public AppUser createInstance() {
		return new AppUser();
	}

	public AppUser getAppUser() {
		return this.entity;
	}

	public void setAppUser( AppUser appUser ) {
		this.entity = appUser;
	}

	public DualListModel<AppRole> getAppRoles() {
		return listAppRoles;
	}

	public void setAppRoles( DualListModel<AppRole> appRoles ) {
		this.listAppRoles = appRoles;
	}

	protected void updateComposedAssociations() {

		if ( listAppRoles != null ) {
			entity.getAppRoles().clear();
			entity.getAppRoles().addAll( listAppRoles.getTarget() );
		}
	}

}
