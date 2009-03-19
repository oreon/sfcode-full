package org.cerebrum.domain.provider;

import java.util.Set;
import java.util.HashSet;
import java.util.Date;

import javax.persistence.*;
import org.hibernate.validator.*;

import org.jboss.seam.annotations.Name;
import org.witchcraft.base.entity.*;

@Entity
@Table(name = "nurse")
@Name("nurse")
public class Nurse extends org.cerebrum.domain.demographics.Person {

	@ManyToOne(optional = true, fetch = FetchType.LAZY)
	@JoinColumn(name = "specialization_id", nullable = true)
	protected Specialization specialization;

	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id", nullable = false)
	protected org.cerebrum.domain.users.User user;

	public void setSpecialization(Specialization specialization) {
		this.specialization = specialization;
	}

	public Specialization getSpecialization() {
		return specialization;
	}

	public void setUser(org.cerebrum.domain.users.User user) {
		this.user = user;
	}

	public org.cerebrum.domain.users.User getUser() {
		return user;
	}

	@Transient
	public String getDisplayName() {
		return specialization + "";
	}

}
