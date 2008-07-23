
/**
 * This is generated code - to edit code or override methods use - Divsion class
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
public abstract class DivsionBase
		extends
			org.witchcraft.model.support.BusinessEntity
		implements
			java.io.Serializable,
			org.witchcraft.model.support.audit.Auditable {

	private static final long serialVersionUID = 1L;

	@Field(index = Index.TOKENIZED, store = Store.NO)
	protected String name;

	/* Default Constructor */
	public DivsionBase() {
	}

	/* Constructor with all attributes */
	public DivsionBase(String name) {
		this.name = name;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	private com.cc.civlit.domain.courtdivisions.FilingOffice filingOffice = new com.cc.civlit.domain.courtdivisions.FilingOffice();

	@ManyToOne
	@JoinColumn(name = "filingOffice_ID", nullable = true, updatable = true)
	@XmlTransient
	public com.cc.civlit.domain.courtdivisions.FilingOffice getFilingOffice() {
		return this.filingOffice;
	}

	public void setFilingOffice(
			com.cc.civlit.domain.courtdivisions.FilingOffice filingOffice) {
		this.filingOffice = filingOffice;
	}

	public abstract Divsion divsionInstance();

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
