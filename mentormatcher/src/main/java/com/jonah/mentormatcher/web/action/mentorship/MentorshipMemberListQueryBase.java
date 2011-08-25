package com.jonah.mentormatcher.web.action.mentorship;

import com.jonah.mentormatcher.domain.mentorship.MentorshipMember;

import org.witchcraft.seam.action.BaseAction;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;
import org.jboss.seam.framework.EntityQuery;
import org.witchcraft.base.entity.BaseQuery;
import org.witchcraft.base.entity.Range;

import org.jboss.seam.annotations.Observer;

import com.jonah.mentormatcher.domain.mentorship.MentorshipMember;

/**
 * 
 * @author WitchcraftMDA Seam Cartridge - 
 *
 */
public abstract class MentorshipMemberListQueryBase
		extends
			BaseQuery<MentorshipMember, Long> {

	private static final String EJBQL = "select mentorshipMember from MentorshipMember mentorshipMember";

	protected MentorshipMember mentorshipMember = new MentorshipMember();

	public MentorshipMember getMentorshipMember() {
		return mentorshipMember;
	}

	@Override
	protected String getql() {
		return EJBQL;
	}

	@Override
	public Class<MentorshipMember> getEntityClass() {
		return MentorshipMember.class;
	}

	@Override
	public String[] getEntityRestrictions() {
		return RESTRICTIONS;
	}

	private static final String[] RESTRICTIONS = {
			"mentorshipMember.id = #{mentorshipMemberList.mentorshipMember.id}",

			"mentorshipMember.mentorship.id = #{mentorshipMemberList.mentorshipMember.mentorship.id}",

			"mentorshipMember.employee.id = #{mentorshipMemberList.mentorshipMember.employee.id}",

			"mentorshipMember.dateCreated <= #{mentorshipMemberList.dateCreatedRange.end}",
			"mentorshipMember.dateCreated >= #{mentorshipMemberList.dateCreatedRange.begin}",};

	public List<MentorshipMember> getMenteesByMentorship(
			com.jonah.mentormatcher.domain.mentorship.Mentorship mentorship) {
		//setMaxResults(10000);
		mentorshipMember.setMentorship(mentorship);
		return getResultList();
	}

	@Observer("archivedMentorshipMember")
	public void onArchive() {
		refresh();
	}

	/** create comma delimited row 
	 * @param builder
	 */
	//@Override
	public void createCsvString(StringBuilder builder, MentorshipMember e) {

		builder.append("\""
				+ (e.getMentorship() != null ? e.getMentorship()
						.getDisplayName().replace(",", "") : "") + "\",");

		builder.append("\""
				+ (e.getEmployee() != null ? e.getEmployee().getDisplayName()
						.replace(",", "") : "") + "\",");

		builder.append("\r\n");
	}

	/** create the headings 
	 * @param builder
	 */
	//@Override
	public void createCSvTitles(StringBuilder builder) {

		builder.append("Mentorship" + ",");

		builder.append("Employee" + ",");

		builder.append("\r\n");
	}
}
