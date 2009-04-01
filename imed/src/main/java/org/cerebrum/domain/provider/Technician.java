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
@Table(name = "technician")
@Name("technician")
@Filter(name = "archiveFilterDef")
public class Technician extends org.cerebrum.domain.demographics.Person {

	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id", nullable = false)
	protected org.witchcraft.users.User user;

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
