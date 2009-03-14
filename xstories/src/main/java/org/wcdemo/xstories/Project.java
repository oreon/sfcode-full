package org.wcdemo.xstories;

import java.util.Set;
import java.util.HashSet;
import java.util.Date;

import javax.persistence.*;
import org.hibernate.validator.*;

import org.jboss.seam.annotations.Name;
import org.witchcraft.base.entity.BusinessEntity;

@Entity
@Table(name = "project")
@Name("project")
public class Project extends BusinessEntity {

	protected String name;

	@ManyToOne(optional = true, fetch = FetchType.LAZY)
	@JoinColumn(name = "coordinator_id", nullable = true)
	protected TeamMember coordinator;

	@ManyToOne(optional = true, fetch = FetchType.LAZY)
	@JoinColumn(name = "teamMember_id", nullable = true)
	protected TeamMember teamMember;

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setCoordinator(TeamMember coordinator) {
		this.coordinator = coordinator;
	}

	public TeamMember getCoordinator() {
		return coordinator;
	}

	public void setTeamMember(TeamMember teamMember) {
		this.teamMember = teamMember;
	}

	public TeamMember getTeamMember() {
		return teamMember;
	}

	@Transient
	public String getDisplayName() {
		return name + "";
	}

}
