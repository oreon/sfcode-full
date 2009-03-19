package org.cerebrum.domain.facility;

import java.util.Set;
import java.util.HashSet;
import java.util.Date;

import javax.persistence.*;
import org.hibernate.validator.*;

import org.jboss.seam.annotations.Name;
import org.witchcraft.base.entity.*;

@Entity
@Table(name = "ward")
@Name("ward")
public class Ward extends BusinessEntity {

	@OneToMany(mappedBy = "ward", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "Ward_ID", nullable = true)
	private Set<Bed> beds = new HashSet<Bed>();

	@NotNull
	@Length(min = 2, max = 50)
	protected String name;

	protected org.cerebrum.domain.demographics.Gender allowedGender;

	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	@JoinColumn(name = "floor_id", nullable = false)
	protected Floor floor;

	public void setBeds(Set<Bed> beds) {
		this.beds = beds;
	}

	public Set<Bed> getBeds() {
		return beds;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setAllowedGender(
			org.cerebrum.domain.demographics.Gender allowedGender) {
		this.allowedGender = allowedGender;
	}

	public org.cerebrum.domain.demographics.Gender getAllowedGender() {
		return allowedGender;
	}

	public void setFloor(Floor floor) {
		this.floor = floor;
	}

	public Floor getFloor() {
		return floor;
	}

	@Transient
	public String getDisplayName() {
		return beds + "";
	}

}
