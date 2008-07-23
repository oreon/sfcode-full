
/**
 * This is generated code - to edit code or override methods use - LevelOfCourt class
 * @author witchCraft Code Generator
 * WARNING  - DO NOT EDIT - CHANGES WILL BE OVERWRITTEN
 */

package com.cc.civlit.domain.courtdivisions;

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
public abstract class LevelOfCourtBase
		extends
			org.witchcraft.model.support.BusinessEntity
		implements
			java.io.Serializable,
			org.witchcraft.model.support.audit.Auditable {

	private static final long serialVersionUID = 1L;

	@Field(index = Index.TOKENIZED, store = Store.NO)
	protected String name;

	/* Default Constructor */
	public LevelOfCourtBase() {
	}

	/* Constructor with all attributes */
	public LevelOfCourtBase(String name) {
		this.name = name;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	private com.cc.civlit.domain.courtdivisions.Jurisdiction jurisdiction = new com.cc.civlit.domain.courtdivisions.Jurisdiction();

	private java.util.Set<com.cc.civlit.domain.courtdivisions.FilingOffice> filingOffice = new java.util.HashSet<com.cc.civlit.domain.courtdivisions.FilingOffice>();

	@ManyToOne
	@JoinColumn(name = "jurisdiction_ID", nullable = true, updatable = true)
	@XmlTransient
	public com.cc.civlit.domain.courtdivisions.Jurisdiction getJurisdiction() {
		return this.jurisdiction;
	}

	public void setJurisdiction(
			com.cc.civlit.domain.courtdivisions.Jurisdiction jurisdiction) {
		this.jurisdiction = jurisdiction;
	}

	public void add(
			com.cc.civlit.domain.courtdivisions.FilingOffice filingOffice) {
		filingOffice.setLevelOfCourt(levelOfCourtInstance());
		this.filingOffice.add(filingOffice);
	}

	public void remove(
			com.cc.civlit.domain.courtdivisions.FilingOffice filingOffice) {
		this.filingOffice.remove(filingOffice);
	}

	@OneToMany(mappedBy = "levelOfCourt", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "levelOfCourt_ID", nullable = false)
	public java.util.Set<com.cc.civlit.domain.courtdivisions.FilingOffice> getFilingOffice() {
		return this.filingOffice;
	}

	public void setFilingOffice(
			java.util.Set<com.cc.civlit.domain.courtdivisions.FilingOffice> filingOffice) {
		this.filingOffice = filingOffice;
	}

	@Transient
	public java.util.Iterator<com.cc.civlit.domain.courtdivisions.FilingOffice> getFilingOfficeIterator() {
		return this.filingOffice.iterator();
	}

	/** Method size on the set doesn't work with technologies requiring 
	 *  java beans get/set  interface so we provide a getter method 
	 * @return
	 */
	@Transient
	public int getFilingOfficeCount() {
		return this.filingOffice.size();
	}

	public abstract LevelOfCourt levelOfCourtInstance();

	@Transient
	public String getDisplayName() {
		return name + "";
	}

	@Override
	public String[] retrieveSearchableFieldsArray() {
		List<String> listSearchableFields = new ArrayList<String>();

		listSearchableFields.add("name");

		String[] arrFields = new String[listSearchableFields.size()];
		return listSearchableFields.toArray(arrFields);
	}

}
