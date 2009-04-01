package org.cerebrum.domain.prescription;

import java.util.Set;
import java.util.HashSet;
import java.util.Date;

import javax.persistence.*;
import org.hibernate.validator.*;

import org.jboss.seam.annotations.Name;
import org.witchcraft.base.entity.*;
import org.hibernate.annotations.Filter;

@Entity
@Table(name = "frequency")
@Name("frequency")
@Filter(name = "archiveFilterDef")
public class Frequency extends BusinessEntity {

	@NotNull
	@Length(min = 2, max = 50)
	protected String name;

	protected Integer qtyPerDay;

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setQtyPerDay(Integer qtyPerDay) {
		this.qtyPerDay = qtyPerDay;
	}

	public Integer getQtyPerDay() {
		return qtyPerDay;
	}

	@Transient
	public String getDisplayName() {
		return name + "";
	}

}
