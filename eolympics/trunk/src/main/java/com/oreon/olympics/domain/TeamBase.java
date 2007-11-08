
/**
 * This is generated code - to edit code or override methods use - Team class
 * @author witchCraft Code Generator
 * WARNING  - DO NOT EDIT - CHANGES WILL BE OVERWRITTEN
 */

package com.oreon.olympics.domain;

import javax.persistence.*;
import org.hibernate.annotations.Cascade;
import java.util.Date;

@MappedSuperclass
/*@Entity
@Table(name="Team",uniqueConstraints={@UniqueConstraint(columnNames={})})*/
public abstract class TeamBase
		extends
			org.witchcraft.model.support.BusinessEntity
		implements
			java.io.Serializable,
			Participant {

	private static final long serialVersionUID = 1L;

	private com.oreon.olympics.domain.Country country;

	private java.util.Set<com.oreon.olympics.domain.Athlete> athletes = new java.util.HashSet<com.oreon.olympics.domain.Athlete>();

	private com.oreon.olympics.domain.TeamEventInstance teamEventInstance;

	public void setCountry(com.oreon.olympics.domain.Country country) {
		this.country = country;
	}

	@ManyToOne
	@JoinColumn(name = "country_ID", nullable = false)
	public com.oreon.olympics.domain.Country getCountry() {
		return this.country;
	}

	public void setTeamEventInstance(
			com.oreon.olympics.domain.TeamEventInstance teamEventInstance) {
		this.teamEventInstance = teamEventInstance;
	}

	@ManyToOne
	@JoinColumn(name = "TeamEventInstance_ID", nullable = true)
	public com.oreon.olympics.domain.TeamEventInstance getTeamEventInstance() {
		return this.teamEventInstance;
	}

	public void addAthlete(com.oreon.olympics.domain.Athlete athletes) {

		this.athletes.add(athletes);
	}

	public void removeAthlete(com.oreon.olympics.domain.Athlete athletes) {
		this.athletes.remove(athletes);
	}

	@OneToMany
	@JoinColumn(name = "Team_ID", nullable = false)
	public java.util.Set<com.oreon.olympics.domain.Athlete> getAthletes() {
		return this.athletes;
	}

	public void setAthletes(
			java.util.Set<com.oreon.olympics.domain.Athlete> athletes) {
		this.athletes = athletes;
	}

	@Transient
	public java.util.Iterator<com.oreon.olympics.domain.Athlete> getAthletesIterator() {
		return this.athletes.iterator();
	}

	/** Method size on the set doesn't work with technologies requiring 
	 *  java beans get/set  interface so we provide a getter method 
	 * @return
	 */
	@Transient
	public int getAthletesCount() {
		return this.athletes.size();
	}

	//Implementing interface Participant
	//*****Done Implementing interface Participant ****

	public abstract Team teamInstance();

}
