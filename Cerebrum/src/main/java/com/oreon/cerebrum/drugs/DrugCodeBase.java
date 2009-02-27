
/**
 * This is generated code - to edit code or override methods use - DrugCode class
 * @author witchCraft Code Generator
 * WARNING  - DO NOT EDIT - CHANGES WILL BE OVERWRITTEN
 */

package com.oreon.cerebrum.drugs;

import javax.persistence.*;
import java.util.Date;
import org.hibernate.annotations.Cascade;

import javax.xml.bind.annotation.XmlTransient;

import org.hibernate.search.annotations.Analyzer;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Index;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.Store;
import org.hibernate.search.annotations.ContainedIn;
import org.hibernate.search.annotations.IndexedEmbedded;

import org.witchcraft.model.jsf.Image;
import java.util.Set;

import java.util.List;
import java.util.ArrayList;

@MappedSuperclass
@Indexed
@Analyzer(impl = org.witchcraft.lucene.analyzers.EnglishAnalyzer.class)
public abstract class DrugCodeBase
		extends
			org.witchcraft.model.support.BusinessEntity
		implements
			java.io.Serializable,
			org.witchcraft.model.support.audit.Auditable {

	private static final long serialVersionUID = 1L;

	@Field(index = Index.TOKENIZED, store = Store.NO)
	protected String code;

	/* Default Constructor */
	public DrugCodeBase() {
	}

	/* Constructor with all attributes */
	public DrugCodeBase(String code) {

		this.code = code;

	}

	@Column(nullable = false, unique = true)
	public String getCode() {

		return this.code;

	}

	public void setCode(String code) {
		this.code = code;
	}

	private com.oreon.cerebrum.drugs.Drug drug;

	private com.oreon.cerebrum.drugs.Category category = new com.oreon.cerebrum.drugs.Category();

	@ManyToOne
	@JoinColumn(name = "drug_ID", nullable = false, updatable = true)
	@XmlTransient
	public com.oreon.cerebrum.drugs.Drug getDrug() {
		return this.drug;
	}

	public void setDrug(com.oreon.cerebrum.drugs.Drug drug) {
		this.drug = drug;
	}

	@ManyToOne
	@JoinColumn(name = "category_ID", nullable = false, updatable = true)
	@ContainedIn
	@XmlTransient
	public com.oreon.cerebrum.drugs.Category getCategory() {
		return this.category;
	}

	public void setCategory(com.oreon.cerebrum.drugs.Category category) {
		this.category = category;
	}

	public abstract DrugCode drugCodeInstance();

	@Transient
	public String getDisplayName() {
		return code + "";
	}

	/** This method is used by hibernate full text search - override to add additional fields
	 * @see org.witchcraft.model.support.BusinessEntity#retrieveSearchableFieldsArray()
	 */
	@Override
	public String[] retrieveSearchableFieldsArray() {
		List<String> listSearchableFields = new ArrayList<String>();

		listSearchableFields.add("code");

		String[] arrFields = new String[listSearchableFields.size()];
		return listSearchableFields.toArray(arrFields);
	}

}
