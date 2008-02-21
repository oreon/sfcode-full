
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
			java.io.Serializable {

	//named queries : 0

	private static final long serialVersionUID = 1L;

	protected String name;

	@Column(nullable = false, unique = true)
	/*
	
	 */
	public String getName() {

		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public abstract Authority authorityInstance();

	@Transient
	public String getDisplayName() {
		return name + "";
	}

}
