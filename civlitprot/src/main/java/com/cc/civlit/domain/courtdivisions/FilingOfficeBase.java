
/**
 * This is generated code - to edit code or override methods use - FilingOffice class
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
public abstract class FilingOfficeBase
		extends
			org.witchcraft.model.support.BusinessEntity
		implements
			java.io.Serializable,
			org.witchcraft.model.support.audit.Auditable {

	private static final long serialVersionUID = 1L;

	@Field(index = Index.TOKENIZED, store = Store.NO)
	protected String name;

	/* Default Constructor */
	public FilingOfficeBase() {
	}

	/* Constructor with all attributes */
	public FilingOfficeBase(String name) {
		this.name = name;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	private java.util.Set<com.cc.civlit.domain.courtdivisions.Divsion> divsion = new java.util.HashSet<com.cc.civlit.domain.courtdivisions.Divsion>();

	private com.cc.civlit.domain.courtdivisions.LevelOfCourt levelOfCourt = new com.cc.civlit.domain.courtdivisions.LevelOfCourt();

	@ManyToOne
	@JoinColumn(name = "levelOfCourt_ID", nullable = true, updatable = true)
	@XmlTransient
	public com.cc.civlit.domain.courtdivisions.LevelOfCourt getLevelOfCourt() {
		return this.levelOfCourt;
	}

	public void setLevelOfCourt(
			com.cc.civlit.domain.courtdivisions.LevelOfCourt levelOfCourt) {
		this.levelOfCourt = levelOfCourt;
	}

	public void add(com.cc.civlit.domain.courtdivisions.Divsion divsion) {
		divsion.setFilingOffice(filingOfficeInstance());
		this.divsion.add(divsion);
	}

	public void remove(com.cc.civlit.domain.courtdivisions.Divsion divsion) {
		this.divsion.remove(divsion);
	}

	@OneToMany(mappedBy = "filingOffice", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "filingOffice_ID", nullable = false)
	public java.util.Set<com.cc.civlit.domain.courtdivisions.Divsion> getDivsion() {
		return this.divsion;
	}

	public void setDivsion(
			java.util.Set<com.cc.civlit.domain.courtdivisions.Divsion> divsion) {
		this.divsion = divsion;
	}

	@Transient
	public java.util.Iterator<com.cc.civlit.domain.courtdivisions.Divsion> getDivsionIterator() {
		return this.divsion.iterator();
	}

	/** Method size on the set doesn't work with technologies requiring 
	 *  java beans get/set  interface so we provide a getter method 
	 * @return
	 */
	@Transient
	public int getDivsionCount() {
		return this.divsion.size();
	}

	public abstract FilingOffice filingOfficeInstance();

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
