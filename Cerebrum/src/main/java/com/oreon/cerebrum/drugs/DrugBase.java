
/**
 * This is generated code - to edit code or override methods use - Drug class
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
public abstract class DrugBase
		extends
			org.witchcraft.model.support.BusinessEntity
		implements
			java.io.Serializable,
			org.witchcraft.model.support.audit.Auditable {

	private static final long serialVersionUID = 1L;

	@Field(index = Index.TOKENIZED, store = Store.NO)
	protected String name;

	protected Integer bioavalability;

	protected Integer halfLife;

	protected Excretion excretion;

	protected PregnancyCategory pregnancyCategory;

	@Field(index = Index.TOKENIZED, store = Store.NO)
	protected String text;

	/* Default Constructor */
	public DrugBase() {
	}

	/* Constructor with all attributes */
	public DrugBase(String name, Integer bioavalability, Integer halfLife,
			Excretion excretion, PregnancyCategory pregnancyCategory,
			String text) {

		this.name = name;

		this.bioavalability = bioavalability;

		this.halfLife = halfLife;

		this.excretion = excretion;

		this.pregnancyCategory = pregnancyCategory;

		this.text = text;

	}

	@Column(nullable = false, unique = true)
	public String getName() {

		return this.name;

	}

	public Integer getBioavalability() {
		return this.bioavalability;
	}

	public Integer getHalfLife() {
		return this.halfLife;
	}

	public Excretion getExcretion() {
		return this.excretion;
	}

	public PregnancyCategory getPregnancyCategory() {
		return this.pregnancyCategory;
	}

	@Column(nullable = false, unique = false)
	public String getText() {

		return this.text;

	}

	public void setName(String name) {
		this.name = name;
	}

	public void setBioavalability(Integer bioavalability) {
		this.bioavalability = bioavalability;
	}

	public void setHalfLife(Integer halfLife) {
		this.halfLife = halfLife;
	}

	public void setExcretion(Excretion excretion) {
		this.excretion = excretion;
	}

	public void setPregnancyCategory(PregnancyCategory pregnancyCategory) {
		this.pregnancyCategory = pregnancyCategory;
	}

	public void setText(String text) {
		this.text = text;
	}

	public abstract Drug drugInstance();

	@Transient
	public String getDisplayName() {
		return name + "";
	}

	/** This method is used by hibernate full text search - override to add additional fields
	 * @see org.witchcraft.model.support.BusinessEntity#retrieveSearchableFieldsArray()
	 */
	@Override
	public String[] retrieveSearchableFieldsArray() {
		List<String> listSearchableFields = new ArrayList<String>();

		listSearchableFields.add("name");

		listSearchableFields.add("text");

		String[] arrFields = new String[listSearchableFields.size()];
		return listSearchableFields.toArray(arrFields);
	}

}
