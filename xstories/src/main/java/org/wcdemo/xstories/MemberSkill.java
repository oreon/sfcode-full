package org.wcdemo.xstories;

import java.util.Set;
import java.util.HashSet;
import java.util.Date;

import javax.persistence.*;
import org.hibernate.validator.*;

import org.jboss.seam.annotations.Name;
import org.witchcraft.base.entity.BusinessEntity;

@Entity
@Table(name = "memberskill")
@Name("memberSkill")
public class MemberSkill extends BusinessEntity {

	protected SkillLevel skillLevel;

	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	@JoinColumn(name = "teamMember_id", nullable = false)
	protected TeamMember teamMember;

	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	@JoinColumn(name = "skill_id", nullable = false)
	protected Skill skill;

	public void setSkillLevel(SkillLevel skillLevel) {
		this.skillLevel = skillLevel;
	}

	public SkillLevel getSkillLevel() {
		return skillLevel;
	}

	public void setTeamMember(TeamMember teamMember) {
		this.teamMember = teamMember;
	}

	public TeamMember getTeamMember() {
		return teamMember;
	}

	public void setSkill(Skill skill) {
		this.skill = skill;
	}

	public Skill getSkill() {
		return skill;
	}

	@Transient
	public String getDisplayName() {
		return skillLevel + "";
	}

}
