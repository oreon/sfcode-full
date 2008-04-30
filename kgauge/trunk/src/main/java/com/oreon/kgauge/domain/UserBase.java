
/**
 * This is generated code - to edit code or override methods use - User class
 * @author witchCraft Code Generator
 * WARNING  - DO NOT EDIT - CHANGES WILL BE OVERWRITTEN
 */

package com.oreon.kgauge.domain;

import javax.persistence.*;
import org.hibernate.annotations.Cascade;

import org.witchcraft.model.jsf.Image;
import java.util.Date;
import javax.xml.bind.annotation.XmlTransient;
import java.util.Set;

@MappedSuperclass
public abstract class UserBase
		extends
			org.witchcraft.model.support.security.AbstractUser
		implements
			java.io.Serializable,
			org.witchcraft.model.support.audit.Auditable {

	private static final long serialVersionUID = 1L;

	protected String username;

	protected String password;

	protected boolean enabled;

	/* Default Constructor */
	public UserBase() {
	}

	/* Constructor with all attributes */
	public UserBase(String username, String password, boolean enabled) {
		this.username = username;
		this.password = password;
		this.enabled = enabled;
	}

	@Column(nullable = false, unique = true)
	/*
	
	 */
	public String getUsername() {

		return this.username;
	}

	@Column(nullable = false, unique = false)
	/*
	
	 */
	public String getPassword() {

		return this.password;
	}

	/*
	
	 */
	public boolean isEnabled() {
		return this.enabled;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	private java.util.Set<com.oreon.kgauge.domain.GrantedRole> grantedRoles = new java.util.HashSet<com.oreon.kgauge.domain.GrantedRole>();

	public void addGrantedRole(com.oreon.kgauge.domain.GrantedRole grantedRoles) {
		grantedRoles.setUser(userInstance());
		this.grantedRoles.add(grantedRoles);
	}

	public void removeGrantedRole(
			com.oreon.kgauge.domain.GrantedRole grantedRoles) {
		this.grantedRoles.remove(grantedRoles);
	}

	@OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "user_ID", nullable = false)
	public java.util.Set<com.oreon.kgauge.domain.GrantedRole> getGrantedRoles() {
		return this.grantedRoles;
	}

	public void setGrantedRoles(
			java.util.Set<com.oreon.kgauge.domain.GrantedRole> grantedRoles) {
		this.grantedRoles = grantedRoles;
	}

	@Transient
	public java.util.Iterator<com.oreon.kgauge.domain.GrantedRole> getGrantedRolesIterator() {
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
		return username + "";
	}

	@Transient
	public Set getUserAuthorities() {
		return getGrantedRoles();
	}

}
