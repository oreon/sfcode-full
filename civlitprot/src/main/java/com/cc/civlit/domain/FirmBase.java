
/**
 * This is generated code - to edit code or override methods use - Firm class
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
public abstract class FirmBase extends Party
		implements
			java.io.Serializable,
			org.witchcraft.model.support.audit.Auditable {

	private static final long serialVersionUID = 1L;

	@Field(index = Index.TOKENIZED, store = Store.NO)
	protected String firmName;

	protected FirmType firmType;

	/* Default Constructor */
	public FirmBase() {
	}

	/* Constructor with all attributes */
	public FirmBase(String firmName, FirmType firmType) {
		this.firmName = firmName;
		this.firmType = firmType;
	}

	public String getFirmName() {
		return this.firmName;
	}

	@Column(nullable = false, unique = false)
	/*
	
	 */
	public FirmType getFirmType() {

		return this.firmType;
	}

	public void setFirmName(String firmName) {
		this.firmName = firmName;
	}

	public void setFirmType(FirmType firmType) {
		this.firmType = firmType;
	}

	private java.util.Set<com.cc.civlit.domain.FirmAdministrator> firmAdministrator = new java.util.HashSet<com.cc.civlit.domain.FirmAdministrator>();

	private com.cc.civlit.domain.ContactDetails contactDetails = new com.cc.civlit.domain.ContactDetails();

	@XmlTransient
	public com.cc.civlit.domain.ContactDetails getContactDetails() {
		return this.contactDetails;
	}

	public void setContactDetails(
			com.cc.civlit.domain.ContactDetails contactDetails) {
		this.contactDetails = contactDetails;
	}

	public void add(com.cc.civlit.domain.FirmAdministrator firmAdministrator) {
		firmAdministrator.setFirm(firmInstance());
		this.firmAdministrator.add(firmAdministrator);
	}

	public void remove(com.cc.civlit.domain.FirmAdministrator firmAdministrator) {
		this.firmAdministrator.remove(firmAdministrator);
	}

	@OneToMany(mappedBy = "firm", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "firm_ID", nullable = true)
	public java.util.Set<com.cc.civlit.domain.FirmAdministrator> getFirmAdministrator() {
		return this.firmAdministrator;
	}

	public void setFirmAdministrator(
			java.util.Set<com.cc.civlit.domain.FirmAdministrator> firmAdministrator) {
		this.firmAdministrator = firmAdministrator;
	}

	@Transient
	public java.util.Iterator<com.cc.civlit.domain.FirmAdministrator> getFirmAdministratorIterator() {
		return this.firmAdministrator.iterator();
	}

	/** Method size on the set doesn't work with technologies requiring 
	 *  java beans get/set  interface so we provide a getter method 
	 * @return
	 */
	@Transient
	public int getFirmAdministratorCount() {
		return this.firmAdministrator.size();
	}

	public abstract Firm firmInstance();

	@Transient
	public String getDisplayName() {
		return firmName + "";
	}

	@Override
	public String[] retrieveSearchableFieldsArray() {
		List<String> listSearchableFields = new ArrayList<String>();

		listSearchableFields.add("firmName");

		String[] arrFields = new String[listSearchableFields.size()];
		return listSearchableFields.toArray(arrFields);
	}

}
