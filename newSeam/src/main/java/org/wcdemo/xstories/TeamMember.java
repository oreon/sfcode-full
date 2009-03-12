package org.wcdemo.xstories;

import java.util.Set;
import java.util.HashSet;
import java.util.Date;

import javax.persistence.*;
import org.hibernate.validator.*;

import org.jboss.seam.annotations.Name;
import org.witchcraft.base.entity.BusinessEntity;

@Entity
@Table(name = "TeamMember")
@Name("teamMember")
public class TeamMember extends ApplicationUser {

	protected String firstName;

	protected String lastName;

	protected String email;

	protected String country;

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getEmail() {
		return email;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getCountry() {
		return country;
	}

	@Transient
	public String getDisplayName() {
		return firstName + "";
	}
	
	@Override
	public boolean equals(Object obj) {
		
		if(this != null && obj instanceof TeamMember){
			return this.getId().equals(((TeamMember)obj).getId());
		}
		// TODO Auto-generated method stub
		return super.equals(obj);
	}
	
	
}
