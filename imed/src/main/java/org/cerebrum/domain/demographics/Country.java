package org.cerebrum.domain.demographics;

import java.util.Set;
import java.util.HashSet;
import java.util.Date;

import javax.persistence.*;
import org.hibernate.validator.*;

import org.jboss.seam.annotations.Name;
import org.witchcraft.base.entity.*;
import org.hibernate.annotations.Filter;

@Entity
@Table(name = "country")
@Name("country")
@Filter(name = "archiveFilterDef")
public class Country extends BusinessEntity {

	@Unique(entityName = "org.cerebrum.domain.demographics.Country", fieldName = "name")
	@NotNull
	@Length(min = 2, max = 50)
	@Column(name = "name", unique = true)
	protected String name;

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	@Transient
	public String getDisplayName() {
		return name + "";
	}

}
