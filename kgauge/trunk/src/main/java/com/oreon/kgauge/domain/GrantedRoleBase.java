
/**
 * This is generated code - to edit code or override methods use - GrantedRole class
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
public abstract class GrantedRoleBase
		extends
			org.witchcraft.model.support.security.AbstractAuthority
		implements
			java.io.Serializable,
			org.witchcraft.model.support.audit.Auditable {

	private static final long serialVersionUID = 1L;

	protected String name;

	/* Default Constructor */
	public GrantedRoleBase() {
	}

	/* Constructor with all attributes */
	public GrantedRoleBase(String name) {
		this.name = name;
	}

	@Column(nullable = false, unique = false)
	/*
	
	 */
	public String getName() {

		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	private com.oreon.kgauge.domain.User user;

	@ManyToOne
	@JoinColumn(name = "user_ID", nullable = false)
	@XmlTransient
	public com.oreon.kgauge.domain.User getUser() {
		return this.user;
	}

	public void setUser(com.oreon.kgauge.domain.User user) {
		this.user = user;
	}

	public abstract GrantedRole grantedRoleInstance();

	@Transient
	public String getDisplayName() {
		return name + "";
	}

	@Transient
	public String getAuthority() {
		return getName();
	}

}
