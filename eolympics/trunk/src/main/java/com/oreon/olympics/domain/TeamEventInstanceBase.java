
/**
 * This is generated code - to edit code or override methods use - TeamEventInstance class
 * @author witchCraft Code Generator
 * WARNING  - DO NOT EDIT - CHANGES WILL BE OVERWRITTEN
 */

package com.oreon.olympics.domain;

import javax.persistence.*;
import org.hibernate.annotations.Cascade;
import java.util.Date;

@MappedSuperclass
/*@Entity
@Table(name="TeamEventInstance",uniqueConstraints={@UniqueConstraint(columnNames={})})*/
public abstract class TeamEventInstanceBase extends EventInstance
		implements
			java.io.Serializable {

	private static final long serialVersionUID = 1L;

	private com.oreon.olympics.domain.Team team;

	public void setTeam(com.oreon.olympics.domain.Team team) {
		this.team = team;
	}

	@ManyToOne
	@JoinColumn(name = "Team_ID", nullable = true)
	public com.oreon.olympics.domain.Team getTeam() {
		return this.team;
	}

	public abstract TeamEventInstance teamEventInstanceInstance();

}
