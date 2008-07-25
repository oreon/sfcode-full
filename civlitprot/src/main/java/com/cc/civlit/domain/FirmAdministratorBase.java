
/**
 * This is generated code - to edit code or override methods use - FirmAdministrator class
 * @author witchCraft Code Generator
 * WARNING  - DO NOT EDIT - CHANGES WILL BE OVERWRITTEN
 */

package com.cc.civlit.domain;

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
public abstract class FirmAdministratorBase extends Person
		implements
			java.io.Serializable,
			org.witchcraft.model.support.audit.Auditable {

	private static final long serialVersionUID = 1L;

	@Field(index = Index.TOKENIZED, store = Store.NO)
	protected String email;

	/* Default Constructor */
	public FirmAdministratorBase() {
	}

	/* Constructor with all attributes */
	public FirmAdministratorBase(String email) {
		this.email = email;
	}

	@Column(nullable = false, unique = true)
	/*
	
	 */
	public String getEmail() {

		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	private com.cc.civlit.domain.Firm firm = new com.cc.civlit.domain.Firm();

	@ManyToOne
	@JoinColumn(name = "firm_ID", nullable = false, updatable = true)
	@XmlTransient
	public com.cc.civlit.domain.Firm getFirm() {
		return this.firm;
	}

	public void setFirm(com.cc.civlit.domain.Firm firm) {
		this.firm = firm;
	}

	public abstract FirmAdministrator firmAdministratorInstance();

	@Transient
	public String getDisplayName() {
		return email + "";
	}

	@Override
	public String[] retrieveSearchableFieldsArray() {
		List<String> listSearchableFields = new ArrayList<String>();

		listSearchableFields.add("email");

		String[] arrFields = new String[listSearchableFields.size()];
		return listSearchableFields.toArray(arrFields);
	}

}
