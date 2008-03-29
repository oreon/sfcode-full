
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

	protected Boolean enabled;

	/* Default Constructor */
	public UserBase() {
	}

	/* Constructor with all attributes */
	public UserBase(String username, String password, Boolean enabled) {
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
	public Boolean getEnabled() {
		return this.enabled;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	private java.util.Set<com.oreon.kgauge.domain.GrantedAuthority> grantedAuthorities = new java.util.HashSet<com.oreon.kgauge.domain.GrantedAuthority>();

	public void addGrantedAuthoritie(
			com.oreon.kgauge.domain.GrantedAuthority grantedAuthorities) {
		grantedAuthorities.setUser(userInstance());
		this.grantedAuthorities.add(grantedAuthorities);
	}

	public void removeGrantedAuthoritie(
			com.oreon.kgauge.domain.GrantedAuthority grantedAuthorities) {
		this.grantedAuthorities.remove(grantedAuthorities);
	}

	@OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "user_ID", nullable = false)
	public java.util.Set<com.oreon.kgauge.domain.GrantedAuthority> getGrantedAuthorities() {
		return this.grantedAuthorities;
	}

	public void setGrantedAuthorities(
			java.util.Set<com.oreon.kgauge.domain.GrantedAuthority> grantedAuthorities) {
		this.grantedAuthorities = grantedAuthorities;
	}

	@Transient
	public java.util.Iterator<com.oreon.kgauge.domain.GrantedAuthority> getGrantedAuthoritiesIterator() {
		return this.grantedAuthorities.iterator();
	}

	/** Method size on the set doesn't work with technologies requiring 
	 *  java beans get/set  interface so we provide a getter method 
	 * @return
	 */
	@Transient
	public int getGrantedAuthoritiesCount() {
		return this.grantedAuthorities.size();
	}

	public abstract User userInstance();

	@Transient
	public String getDisplayName() {
		return username + "";
	}
	
	

}
