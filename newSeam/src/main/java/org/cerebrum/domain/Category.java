package org.cerebrum.domain;

import java.util.Set;
import java.util.HashSet;
import java.util.Date;

import javax.persistence.*;
import org.hibernate.validator.*;

import org.jboss.seam.annotations.Name;
import org.witchcraft.base.entity.BusinessEntity;

@Entity
@Table(name = "Category")
@Name("category")
public class Category extends BusinessEntity {

	protected String name;

	@OneToMany(mappedBy = "parent", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "Category_ID", nullable = true)
	private Set<Category> children = new HashSet();

	@ManyToOne(optional = true, fetch = FetchType.LAZY)
	@JoinColumn(name = "parent_id", nullable = true)
	protected Category parent;

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setChildren(Set<Category> children) {
		this.children = children;
	}

	public Set<Category> getChildren() {
		return children;
	}

	public void setParent(Category parent) {
		this.parent = parent;
	}

	public Category getParent() {
		return parent;
	}

	@Transient
	public String getDisplayName() {
		return name + "";
	}

}
