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
@Table(name = "claim")
@Name("claim")
@Filter(name = "archiveFilterDef")
public class Claim extends BusinessEntity {

	//services->claim ->Claim->Service->Service

	@OneToMany(mappedBy = "claim", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "Claim_ID", nullable = false)
	private Set<Service> services = new HashSet<Service>();

	@Lob
	protected String notes;

	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	@JoinColumn(name = "referringPhysician_id", nullable = false)
	protected org.cerebrum.domain.provider.Physician referringPhysician;

	public void setServices(Set<Service> services) {
		this.services = services;
	}

	public Set<Service> getServices() {
		return services;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public String getNotes() {
		return notes;
	}

	public void setReferringPhysician(
			org.cerebrum.domain.provider.Physician referringPhysician) {
		this.referringPhysician = referringPhysician;
	}

	public org.cerebrum.domain.provider.Physician getReferringPhysician() {
		return referringPhysician;
	}

	@Transient
	public String getDisplayName() {
		return services + "";
	}

}
