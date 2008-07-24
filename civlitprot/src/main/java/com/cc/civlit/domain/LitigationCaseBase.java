
/**
 * This is generated code - to edit code or override methods use - LitigationCase class
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
public abstract class LitigationCaseBase
		extends
			org.witchcraft.model.support.BusinessEntity
		implements
			java.io.Serializable,
			org.witchcraft.model.support.audit.Auditable {

	private static final long serialVersionUID = 1L;

	@Field(index = Index.TOKENIZED, store = Store.NO)
	protected String name;

	@Field(index = Index.TOKENIZED, store = Store.NO)
	protected String accountName;

	@Field(index = Index.TOKENIZED, store = Store.NO)
	protected String courtFileNumber;

	@Field(index = Index.TOKENIZED, store = Store.NO)
	protected String styleOfCase;

	protected ProceedingType proceedingType;

	protected CaseType caseType;

	/* Default Constructor */
	public LitigationCaseBase() {
	}

	/* Constructor with all attributes */
	public LitigationCaseBase(String name, String accountName,
			String courtFileNumber, String styleOfCase,
			ProceedingType proceedingType, CaseType caseType) {
		this.name = name;
		this.accountName = accountName;
		this.courtFileNumber = courtFileNumber;
		this.styleOfCase = styleOfCase;
		this.proceedingType = proceedingType;
		this.caseType = caseType;
	}

	public String getName() {
		return this.name;
	}

	public String getAccountName() {
		return this.accountName;
	}

	public String getCourtFileNumber() {
		return this.courtFileNumber;
	}

	public String getStyleOfCase() {
		return this.styleOfCase;
	}

	public ProceedingType getProceedingType() {
		return this.proceedingType;
	}

	public CaseType getCaseType() {
		return this.caseType;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	public void setCourtFileNumber(String courtFileNumber) {
		this.courtFileNumber = courtFileNumber;
	}

	public void setStyleOfCase(String styleOfCase) {
		this.styleOfCase = styleOfCase;
	}

	public void setProceedingType(ProceedingType proceedingType) {
		this.proceedingType = proceedingType;
	}

	public void setCaseType(CaseType caseType) {
		this.caseType = caseType;
	}

	private com.cc.civlit.domain.courtdivisions.Divsion divsion;

	private java.util.Set<com.cc.civlit.domain.CaseAdministrator> caseAdministrator = new java.util.HashSet<com.cc.civlit.domain.CaseAdministrator>();

	private com.cc.civlit.domain.Firm firm;

	@ManyToOne
	@JoinColumn(name = "divsion_ID", nullable = false, updatable = true)
	@XmlTransient
	public com.cc.civlit.domain.courtdivisions.Divsion getDivsion() {
		return this.divsion;
	}

	public void setDivsion(com.cc.civlit.domain.courtdivisions.Divsion divsion) {
		this.divsion = divsion;
	}

	@ManyToOne
	@JoinColumn(name = "firm_ID", nullable = false, updatable = true)
	@XmlTransient
	public com.cc.civlit.domain.Firm getFirm() {
		return this.firm;
	}

	public void setFirm(com.cc.civlit.domain.Firm firm) {
		this.firm = firm;
	}

	public void add(com.cc.civlit.domain.CaseAdministrator caseAdministrator) {
		this.caseAdministrator.add(caseAdministrator);
	}

	public void remove(com.cc.civlit.domain.CaseAdministrator caseAdministrator) {
		this.caseAdministrator.remove(caseAdministrator);
	}

	@OneToMany(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "litigationCase_ID", nullable = true)
	public java.util.Set<com.cc.civlit.domain.CaseAdministrator> getCaseAdministrator() {
		return this.caseAdministrator;
	}

	public void setCaseAdministrator(
			java.util.Set<com.cc.civlit.domain.CaseAdministrator> caseAdministrator) {
		this.caseAdministrator = caseAdministrator;
	}

	@Transient
	public java.util.Iterator<com.cc.civlit.domain.CaseAdministrator> getCaseAdministratorIterator() {
		return this.caseAdministrator.iterator();
	}

	/** Method size on the set doesn't work with technologies requiring 
	 *  java beans get/set  interface so we provide a getter method 
	 * @return
	 */
	@Transient
	public int getCaseAdministratorCount() {
		return this.caseAdministrator.size();
	}

	public abstract LitigationCase litigationCaseInstance();

	@Transient
	public String getDisplayName() {
		return name + "";
	}

	@Override
	public String[] retrieveSearchableFieldsArray() {
		List<String> listSearchableFields = new ArrayList<String>();

		listSearchableFields.add("name");

		listSearchableFields.add("accountName");

		listSearchableFields.add("courtFileNumber");

		listSearchableFields.add("styleOfCase");

		String[] arrFields = new String[listSearchableFields.size()];
		return listSearchableFields.toArray(arrFields);
	}

}
