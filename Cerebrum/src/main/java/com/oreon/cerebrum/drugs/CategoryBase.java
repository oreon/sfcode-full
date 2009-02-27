
/**
 * This is generated code - to edit code or override methods use - Category class
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
public abstract class CategoryBase
		extends
			org.witchcraft.model.support.BusinessEntity
		implements
			java.io.Serializable,
			org.witchcraft.model.support.audit.Auditable {

	private static final long serialVersionUID = 1L;

	@Field(index = Index.TOKENIZED, store = Store.NO)
	protected String code;

	@Field(index = Index.TOKENIZED, store = Store.NO)
	protected String description;

	/* Default Constructor */
	public CategoryBase() {
	}

	/* Constructor with all attributes */
	public CategoryBase(String code, String description) {

		this.code = code;

		this.description = description;

	}

	@Column(nullable = false, unique = true)
	public String getCode() {

		return this.code;

	}

	public String getDescription() {
		return this.description;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	private com.oreon.cerebrum.drugs.Category parent;

	private java.util.Set<com.oreon.cerebrum.drugs.Category> subcategories = new java.util.HashSet<com.oreon.cerebrum.drugs.Category>();

	private java.util.Set<com.oreon.cerebrum.drugs.DrugCode> drugCode = new java.util.HashSet<com.oreon.cerebrum.drugs.DrugCode>();

	@ManyToOne
	@JoinColumn(name = "parent_ID", nullable = true, updatable = true)
	@XmlTransient
	public com.oreon.cerebrum.drugs.Category getParent() {
		return this.parent;
	}

	public void setParent(com.oreon.cerebrum.drugs.Category parent) {
		this.parent = parent;
	}

	public void addSubcategorie(com.oreon.cerebrum.drugs.Category subcategories) {
		checkMaximumSubcategories();
		subcategories.setParent(categoryInstance());
		this.subcategories.add(subcategories);
	}

	public void removeSubcategorie(
			com.oreon.cerebrum.drugs.Category subcategories) {
		this.subcategories.remove(subcategories);
	}

	@OneToMany(mappedBy = "parent", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "parent_ID", nullable = true)
	public java.util.Set<com.oreon.cerebrum.drugs.Category> getSubcategories() {
		return this.subcategories;
	}

	public void setSubcategories(
			java.util.Set<com.oreon.cerebrum.drugs.Category> subcategories) {
		this.subcategories = subcategories;
	}

	@Transient
	public java.util.Iterator<com.oreon.cerebrum.drugs.Category> getSubcategoriesIterator() {
		return this.subcategories.iterator();
	}

	/** Method size on the set doesn't work with technologies requiring 
	 *  java beans get/set  interface so we provide a getter method 
	 * @return
	 */
	@Transient
	public int getSubcategoriesCount() {
		return this.subcategories.size();
	}

	public void checkMaximumSubcategories() {
		// if(subcategories.size() > Constants.size())
		// 		throw new BusinessException ("msg.tooMany." + subcategories );
	}

	public void addDrugCode(com.oreon.cerebrum.drugs.DrugCode drugCode) {
		checkMaximumDrugCode();
		drugCode.setCategory(categoryInstance());
		this.drugCode.add(drugCode);
	}

	public void remove(com.oreon.cerebrum.drugs.DrugCode drugCode) {
		this.drugCode.remove(drugCode);
	}

	@OneToMany(mappedBy = "category", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@IndexedEmbedded
	@JoinColumn(name = "category_ID", nullable = true)
	public java.util.Set<com.oreon.cerebrum.drugs.DrugCode> getDrugCode() {
		return this.drugCode;
	}

	public void setDrugCode(
			java.util.Set<com.oreon.cerebrum.drugs.DrugCode> drugCode) {
		this.drugCode = drugCode;
	}

	@Transient
	public java.util.Iterator<com.oreon.cerebrum.drugs.DrugCode> getDrugCodeIterator() {
		return this.drugCode.iterator();
	}

	/** Method size on the set doesn't work with technologies requiring 
	 *  java beans get/set  interface so we provide a getter method 
	 * @return
	 */
	@Transient
	public int getDrugCodeCount() {
		return this.drugCode.size();
	}

	public void checkMaximumDrugCode() {
		// if(drugCode.size() > Constants.size())
		// 		throw new BusinessException ("msg.tooMany." + drugCode );
	}

	public abstract Category categoryInstance();

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

		listSearchableFields.add("description");

		listSearchableFields.add("subcategories.code");

		listSearchableFields.add("subcategories.description");

		listSearchableFields.add("drugCode.code");

		String[] arrFields = new String[listSearchableFields.size()];
		return listSearchableFields.toArray(arrFields);
	}

}
