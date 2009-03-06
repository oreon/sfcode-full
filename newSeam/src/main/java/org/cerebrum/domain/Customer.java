package org.cerebrum.domain;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;

import org.hibernate.validator.event.JPAValidateListener;
import org.jboss.seam.annotations.Name;
import org.witchcraft.base.entity.Unique;

@Entity
@Table(name = "Customer", uniqueConstraints=@UniqueConstraint(columnNames="email"))
@Name("customer")
@EntityListeners( JPAValidateListener.class )
public class Customer extends Person {

	@Transient
	public String getDisplayName() {
		return lastName + "," + firstName;
	}
	
	@Unique(fieldName="email", entityName="")
	public String getEmail() {
		return email;
	}

}
