package org.cerebrum.domain.billing;

import java.util.Set;
import java.util.HashSet;
import java.util.Date;

import javax.persistence.*;
import org.hibernate.validator.*;

import org.jboss.seam.annotations.Name;
import org.witchcraft.base.entity.*;
import org.hibernate.annotations.Filter;

@Entity
@Table(name = "premium")
@Name("premium")
@Filter(name = "archiveFilterDef")
public class Premium extends BusinessEntity {

	@NotNull
	@Length(min = 2, max = 50)
	protected String name;

	protected String description;

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

	@Transient
	public String getDisplayName() {
		return name + "";
	}

}
