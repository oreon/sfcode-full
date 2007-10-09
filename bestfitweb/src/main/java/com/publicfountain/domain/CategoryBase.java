package com.publicfountain.domain;

import javax.persistence.*;
import java.util.Date;

@MappedSuperclass
public abstract class CategoryBase
		extends
			org.witchcraft.model.support.BusinessEntity
		implements
			java.io.Serializable {

	private static final long serialVersionUID = 1L;

	protected String name;

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	private java.util.Set<com.publicfountain.domain.Topic> topics = new java.util.HashSet<com.publicfountain.domain.Topic>();

	private java.util.Set<com.publicfountain.domain.Category> subcategories = new java.util.HashSet<com.publicfountain.domain.Category>();

	private com.publicfountain.domain.Category parent;

	public void setParent(com.publicfountain.domain.Category parent) {
		this.parent = parent;
	}

	@ManyToOne
	@JoinColumn(name = "parent_ID", nullable = true)
	public com.publicfountain.domain.Category getParent() {
		return this.parent;
	}

	public void addTopics(com.publicfountain.domain.Topic topics) {

		topics.setCategory(categoryInstance());

		this.topics.add(topics);
	}

	public void removeTopics(com.publicfountain.domain.Topic topics) {
		this.topics.remove(topics);
	}

	@OneToMany(mappedBy = "category", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	public java.util.Set<com.publicfountain.domain.Topic> getTopics() {
		return this.topics;
	}

	public void setTopics(java.util.Set<com.publicfountain.domain.Topic> topics) {
		this.topics = topics;
	}

	@Transient
	public java.util.Iterator<com.publicfountain.domain.Topic> getTopicsIterator() {
		return this.topics.iterator();
	}

	public void addSubcategories(
			com.publicfountain.domain.Category subcategories) {

		subcategories.setParent(categoryInstance());

		this.subcategories.add(subcategories);
	}

	public void removeSubcategories(
			com.publicfountain.domain.Category subcategories) {
		this.subcategories.remove(subcategories);
	}

	@OneToMany(mappedBy = "parent", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	public java.util.Set<com.publicfountain.domain.Category> getSubcategories() {
		return this.subcategories;
	}

	public void setSubcategories(
			java.util.Set<com.publicfountain.domain.Category> subcategories) {
		this.subcategories = subcategories;
	}

	@Transient
	public java.util.Iterator<com.publicfountain.domain.Category> getSubcategoriesIterator() {
		return this.subcategories.iterator();
	}

	public abstract Category categoryInstance();

	@Transient
	public String getDisplayName() {
		return name + "";
	}

}
