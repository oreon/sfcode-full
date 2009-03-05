package org.cerebrum.domain;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Filter;
import org.hibernate.annotations.FilterDef;
import org.jboss.seam.annotations.Name;

@Entity
@Table(name = "Customer")
@Name("customer")
@FilterDef(name="current")
@Filter(name = "currentFilter", condition="archived=0") 
public class Customer extends Person {

	@Transient
	public String getDisplayName() {
		return lastName + "," + firstName;
	}

}
