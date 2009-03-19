package org.cerebrum.domain.facility;

import java.util.Set;
import java.util.HashSet;
import java.util.Date;

import javax.persistence.*;
import org.hibernate.validator.*;

import org.jboss.seam.annotations.Name;
import org.witchcraft.base.entity.*;

@Entity
@Table(name = "floor")
@Name("floor")
public class Floor extends BusinessEntity {

	protected Integer number;

	@OneToMany(mappedBy = "floor", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "Floor_ID", nullable = true)
	private Set<Ward> wards = new HashSet<Ward>();

	public void setNumber(Integer number) {
		this.number = number;
	}

	public Integer getNumber() {
		return number;
	}

	public void setWards(Set<Ward> wards) {
		this.wards = wards;
	}

	public Set<Ward> getWards() {
		return wards;
	}

	@Transient
	public String getDisplayName() {
		return number + "";
	}

}
