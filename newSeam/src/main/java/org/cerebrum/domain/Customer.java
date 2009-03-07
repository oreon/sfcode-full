package org.cerebrum.domain;

import java.util.Set;
import java.util.HashSet;
import java.util.Date;

import javax.persistence.*;
import org.hibernate.validator.*;

import org.jboss.seam.annotations.Name;
import org.witchcraft.base.entity.BusinessEntity;

@Entity
@Table(name = "Customer")
@Name("customer")
public class Customer extends Person {

	@Transient
	public String getDisplayName() {
		return lastName + "," + firstName;
	}

}
