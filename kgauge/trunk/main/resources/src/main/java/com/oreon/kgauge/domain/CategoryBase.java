
/**
 * This is generated code - to edit code or override methods use - Category class
 * @author witchCraft Code Generator
 * WARNING  - DO NOT EDIT - CHANGES WILL BE OVERWRITTEN
 */

package com.oreon.kgauge.domain;

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

@MappedSuperclass
@Indexed
//@Analyzer(impl = example.EnglishAnalyzer.class)
public abstract class CategoryBase
		extends
			org.witchcraft.model.support.BusinessEntity
		implements
			java.io.Serializable,
			org.witchcraft.model.support.audit.Auditable {

	private static final long serialVersionUID = 1L;

	protected String name;

	/* Default Constructor */
	public CategoryBase() {
	}

	/* Constructor with all attributes */
	public CategoryBase(String name) {
		this.name = name;
	}

	@Column(nullable = false, unique = false)
	/*
	
	 */
	public String getName() {

		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	private com.oreon.kgauge.domain.Category parent;

	private java.util.Set<com.oreon.kgauge.domain.Category> subcategories = new java.util.HashSet<com.oreon.kgauge.domain.Category>();

	@ManyToOne
	@JoinColumn(name = "parent_ID", nullable = true, updatable = true)
	@XmlTransient
	public com.oreon.kgauge.domain.Category getParent() {
		return this.parent;
	}

	public void setParent(com.oreon.kgauge.domain.Category parent) {
		this.parent = parent;
	}

	public void addSubcategorie(com.oreon.kgauge.domain.Category subcategories) {
		subcategories.setParent(categoryInstance());
		this.subcategories.add(subcategories);
	}

	public void removeSubcategorie(
			com.oreon.kgauge.domain.Category subcategories) {
		this.subcategories.remove(subcategories);
	}

	@OneToMany(mappedBy = "parent", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "parent_ID", nullable = true)
	public java.util.Set<com.oreon.kgauge.domain.Category> getSubcategories() {
		return this.subcategories;
	}

	public void setSubcategories(
			java.util.Set<com.oreon.kgauge.domain.Category> subcategories) {
		this.subcategories = subcategories;
	}

	@Transient
	public java.util.Iterator<com.oreon.kgauge.domain.Category> getSubcategoriesIterator() {
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

	public abstract Category categoryInstance();

	@Transient
	public String getDisplayName() {
		return name + "";
	}

}
