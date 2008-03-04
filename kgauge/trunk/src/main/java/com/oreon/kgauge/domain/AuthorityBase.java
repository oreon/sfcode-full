
/**
 * This is generated code - to edit code or override methods use - Authority class
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
public abstract class AuthorityBase
		extends
			org.witchcraft.model.support.BusinessEntity
		implements
			java.io.Serializable,
			org.witchcraft.model.support.audit.Auditable {

	private static final long serialVersionUID = 1L;

	protected String name;

	/* Default Constructor */
	public AuthorityBase() {
	}

	/* Constructor with all attributes */
	public AuthorityBase(String name) {
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

	public void setUser(com.oreon.kgauge.domain.User user) {
		this.user = user;
	}

	@ManyToOne
	@JoinColumn(name = "user_ID", nullable = false)
	public com.oreon.kgauge.domain.User getUser() {
		return this.user;
	}

	public abstract Authority authorityInstance();

	@Transient
	public String getDisplayName() {
		return name + "";
	}

}
