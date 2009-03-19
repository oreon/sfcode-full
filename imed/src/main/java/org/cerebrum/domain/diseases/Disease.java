package org.cerebrum.domain.diseases;

import java.util.Set;
import java.util.HashSet;
import java.util.Date;

import javax.persistence.*;
import org.hibernate.validator.*;

import org.jboss.seam.annotations.Name;
import org.witchcraft.base.entity.*;

@Entity
@Table(name = "disease")
@Name("disease")
public class Disease extends BusinessEntity {

	@NotNull
	@Length(min = 2, max = 50)
	protected String name;

	@Lob
	protected String description;

	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	@JoinColumn(name = "category_id", nullable = false)
	protected Category category;

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDescription() {
		return description;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public Category getCategory() {
		return category;
	}

	@Transient
	public String getDisplayName() {
		return name + "";
	}

}
