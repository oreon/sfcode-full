package org.cerebrum.domain.diseases;

import java.util.Set;
import java.util.HashSet;
import java.util.Date;

import javax.persistence.*;
import org.hibernate.validator.*;

import org.jboss.seam.annotations.Name;
import org.witchcraft.base.entity.*;
import org.hibernate.annotations.Filter;

@Entity
@Table(name = "category")
@Name("category")
@Filter(name = "archiveFilterDef")
public class Category extends BusinessEntity {

	//children->parent ->Category->Category->Category

	@OneToMany(mappedBy = "parent", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "Category_ID", nullable = true)
	private Set<Category> children = new HashSet<Category>();

	@ManyToOne(optional = true, fetch = FetchType.LAZY)
	@JoinColumn(name = "parent_id", nullable = true)
	protected Category parent;

	@NotNull
	@Length(min = 2, max = 50)
	protected String name;

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

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	@Transient
	public String getDisplayName() {
		return children + "";
	}

}
