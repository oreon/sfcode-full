package org.cerebrum.domain.billing;

import java.util.Set;
import java.util.HashSet;
import java.util.Date;

import javax.persistence.*;
import org.hibernate.validator.*;

import org.jboss.seam.annotations.Name;
import org.witchcraft.base.entity.*;

@Entity
@Table(name = "claim")
@Name("claim")
public class Claim extends BusinessEntity {

	@OneToMany(mappedBy = "claim", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "Claim_ID", nullable = false)
	private Set<Service> services = new HashSet<Service>();

	public void setServices(Set<Service> services) {
		this.services = services;
	}

	public Set<Service> getServices() {
		return services;
	}

	@Transient
	public String getDisplayName() {
		return services + "";
	}

}
