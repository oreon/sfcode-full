
/**
 * This is generated code - to edit code or override methods use - Category class
 * @author witchCraft Code Generator
 * WARNING  - DO NOT EDIT - CHANGES WILL BE OVERWRITTEN
 */

package com.oreon.jshoppingcart.domain;

import javax.persistence.*;
import org.hibernate.annotations.Cascade;
import java.util.Date;

@MappedSuperclass
/*@Entity
@Table(name="Category",uniqueConstraints={@UniqueConstraint(columnNames={})})*/
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

	private com.oreon.jshoppingcart.domain.Category parent;

	private java.util.Set<com.oreon.jshoppingcart.domain.Category> children = new java.util.HashSet<com.oreon.jshoppingcart.domain.Category>();

	public void setParent(com.oreon.jshoppingcart.domain.Category parent) {
		this.parent = parent;
	}

	@ManyToOne
	@JoinColumn(name = "parent_ID", nullable = true)
	public com.oreon.jshoppingcart.domain.Category getParent() {
		return this.parent;
	}

	public void addChildren(com.oreon.jshoppingcart.domain.Category children) {

		children.setParent(categoryInstance());

		this.children.add(children);
	}

	public void removeChildren(com.oreon.jshoppingcart.domain.Category children) {
		this.children.remove(children);
	}

	@OneToMany(mappedBy = "parent", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	public java.util.Set<com.oreon.jshoppingcart.domain.Category> getChildren() {
		return this.children;
	}

	public void setChildren(
			java.util.Set<com.oreon.jshoppingcart.domain.Category> children) {
		this.children = children;
	}

	@Transient
	public java.util.Iterator<com.oreon.jshoppingcart.domain.Category> getChildrenIterator() {
		return this.children.iterator();
	}

	/** Method size on the set doesn't work with technologies requiring 
	 *  java beans get/set  interface so we provide a getter method 
	 * @return
	 */
	@Transient
	public int getChildrenCount() {
		return this.children.size();
	}

	public abstract Category categoryInstance();

	@Transient
	public String getDisplayName() {
		return name + "";
	}

}
