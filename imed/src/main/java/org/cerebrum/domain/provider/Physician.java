package org.cerebrum.domain.provider;

import java.util.Set;
import java.util.HashSet;
import java.util.Date;

import javax.persistence.*;
import org.hibernate.validator.*;

import org.jboss.seam.annotations.Name;
import org.witchcraft.base.entity.*;
import org.hibernate.annotations.Filter;

@Entity
@Table(name = "physician")
@Name("physician")
@Filter(name = "archiveFilterDef")
public class Physician extends org.cerebrum.domain.demographics.Person {

	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	@JoinColumn(name = "specialization_id", nullable = false)
	protected Specialization specialization;

	protected String billingNumber;

	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id", nullable = false)
	protected org.witchcraft.users.User user;

	public void setSpecialization(Specialization specialization) {
		this.specialization = specialization;
	}

	public Specialization getSpecialization() {
		return specialization;
	}

	public void setBillingNumber(String billingNumber) {
		this.billingNumber = billingNumber;
	}

	public String getBillingNumber() {
		return billingNumber;
	}

	public void setUser(org.witchcraft.users.User user) {
		this.user = user;
	}

	public org.witchcraft.users.User getUser() {
		return user;
	}

	@Transient
	public String getDisplayName() {
		return super.getDisplayName();
	}

}
