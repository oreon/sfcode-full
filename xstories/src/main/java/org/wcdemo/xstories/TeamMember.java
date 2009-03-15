package org.wcdemo.xstories;

import java.util.Set;
import java.util.HashSet;
import java.util.Date;

import javax.persistence.*;
import org.hibernate.validator.*;

import org.jboss.seam.annotations.Name;
import org.witchcraft.base.entity.BusinessEntity;

@Entity
@Table(name = "teammember")
@Name("teamMember")
public class TeamMember extends ApplicationUser {

	@NotNull
	protected String firstName;

	@NotNull
	protected String lastName;

	@NotNull
	protected String email;

	protected String country;

	@OneToMany(mappedBy = "teamMember", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "TeamMember_ID", nullable = false)
	private Set<MemberSkill> skills = new HashSet<MemberSkill>();

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

	public void setSkills(Set<MemberSkill> skills) {
		this.skills = skills;
	}

	public Set<MemberSkill> getSkills() {
		return skills;
	}

	@Transient
	public String getDisplayName() {
		return lastName + "," + firstName;
	}

}
