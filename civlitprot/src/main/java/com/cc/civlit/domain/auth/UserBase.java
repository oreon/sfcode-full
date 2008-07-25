
/**
 * This is generated code - to edit code or override methods use - User class
 * @author witchCraft Code Generator
 * WARNING  - DO NOT EDIT - CHANGES WILL BE OVERWRITTEN
 */

package com.cc.civlit.domain.auth;

import javax.persistence.*;
import java.util.Date;
import org.hibernate.annotations.Cascade;

import javax.xml.bind.annotation.XmlTransient;

import org.hibernate.search.annotations.Analyzer;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Index;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.Store;

import org.witchcraft.model.jsf.Image;
import java.util.Set;

import java.util.List;
import java.util.ArrayList;

@MappedSuperclass
@Indexed
//@Analyzer(impl = example.EnglishAnalyzer.class)
public abstract class UserBase
		extends
			org.witchcraft.model.support.security.AbstractUser
		implements
			java.io.Serializable,
			org.witchcraft.model.support.audit.Auditable {

	private static final long serialVersionUID = 1L;

	@Field(index = Index.TOKENIZED, store = Store.NO)
	protected String password;

	protected boolean enabled;

	@Field(index = Index.TOKENIZED, store = Store.NO)
	protected String username;

	/* Default Constructor */
	public UserBase() {
	}

	/* Constructor with all attributes */
	public UserBase(String password, boolean enabled, String username) {
		this.password = password;
		this.enabled = enabled;
		this.username = username;
	}

	@Column(nullable = false, unique = false)
	/*
	
	 */
	public String getPassword() {

		return this.password;
	}

	@Column(nullable = false, unique = false)
	/*
	
	 */
	public boolean isEnabled() {

		return this.enabled;
	}

	@Column(nullable = false, unique = true)
	/*
	
	 */
	public String getUsername() {

		return this.username;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	private java.util.Set<com.cc.civlit.domain.auth.GrantedRole> grantedRoles = new java.util.HashSet<com.cc.civlit.domain.auth.GrantedRole>();

	public void addGrantedRole(
			com.cc.civlit.domain.auth.GrantedRole grantedRoles) {
		grantedRoles.setUser(userInstance());
		this.grantedRoles.add(grantedRoles);
	}

	public void removeGrantedRole(
			com.cc.civlit.domain.auth.GrantedRole grantedRoles) {
		this.grantedRoles.remove(grantedRoles);
	}

	@OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "user_ID", nullable = false)
	public java.util.Set<com.cc.civlit.domain.auth.GrantedRole> getGrantedRoles() {
		return this.grantedRoles;
	}

	public void setGrantedRoles(
			java.util.Set<com.cc.civlit.domain.auth.GrantedRole> grantedRoles) {
		this.grantedRoles = grantedRoles;
	}

	@Transient
	public java.util.Iterator<com.cc.civlit.domain.auth.GrantedRole> getGrantedRolesIterator() {
		return this.grantedRoles.iterator();
	}

	/** Method size on the set doesn't work with technologies requiring 
	 *  java beans get/set  interface so we provide a getter method 
	 * @return
	 */
	@Transient
	public int getGrantedRolesCount() {
		return this.grantedRoles.size();
	}

	public abstract User userInstance();

	@Transient
	public String getDisplayName() {
		return password + "";
	}

	@Transient
	public Set getUserAuthorities() {
		return getGrantedRoles();
	}

	@Override
	public String[] retrieveSearchableFieldsArray() {
		List<String> listSearchableFields = new ArrayList<String>();

		listSearchableFields.add("password");

		listSearchableFields.add("username");

		String[] arrFields = new String[listSearchableFields.size()];
		return listSearchableFields.toArray(arrFields);
	}

}
