package com.nas.recoveryportal.appraisal.action;

import com.nas.recoveryportal.appraisal.TeamMember;

import org.witchcraft.seam.action.BaseAction;

import java.util.Arrays;
import java.util.Date;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;
import org.jboss.seam.framework.EntityQuery;
import org.witchcraft.base.entity.BaseQuery;
import org.witchcraft.base.entity.Range;

import com.nas.recoveryportal.appraisal.TeamMember;

/**
 * @author WitchcraftMDA Seam Cartridge
 *
 */
public abstract class TeamMemberListQueryBase
		extends
			BaseQuery<TeamMember, Long> {

	//private static final String EJBQL = "select teamMember from TeamMember teamMember";

	private TeamMember teamMember = new TeamMember();

	private Range<Double> teamMember_costRange = new Range<Double>();
	public Range<Double> getTeamMember_costRange() {
		return teamMember_costRange;
	}
	public void setTeamMember_cost(Range<Double> teamMember_costRange) {
		this.teamMember_costRange = teamMember_costRange;
	}

	private static final String[] RESTRICTIONS = {
			"teamMember.id = #{teamMemberList.teamMember.id}",

			"lower(teamMember.firstName) like concat(lower(#{teamMemberList.teamMember.firstName}),'%')",

			"lower(teamMember.lastName) like concat(lower(#{teamMemberList.teamMember.lastName}),'%')",

			"lower(teamMember.contactDetails.primaryPhone) like concat(lower(#{teamMemberList.teamMember.contactDetails.primaryPhone}),'%')",

			"lower(teamMember.contactDetails.secondaryPhone) like concat(lower(#{teamMemberList.teamMember.contactDetails.secondaryPhone}),'%')",

			"lower(teamMember.contactDetails.email) like concat(lower(#{teamMemberList.teamMember.contactDetails.email}),'%')",

			"teamMember.cost >= #{teamMemberList.teamMember_costRange.begin}",
			"teamMember.cost <= #{teamMemberList.teamMember_costRange.end}",

			"teamMember.dateCreated <= #{teamMemberList.dateCreatedRange.end}",
			"teamMember.dateCreated >= #{teamMemberList.dateCreatedRange.begin}",};

	public TeamMember getTeamMember() {
		return teamMember;
	}

	@Override
	public Class<TeamMember> getEntityClass() {
		return TeamMember.class;
	}

	@Override
	public String[] getEntityRestrictions() {
		// TODO Auto-generated method stub
		return RESTRICTIONS;
	}
}
