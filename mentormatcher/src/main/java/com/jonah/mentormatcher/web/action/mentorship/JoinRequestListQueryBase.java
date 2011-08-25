package com.jonah.mentormatcher.web.action.mentorship;

import com.jonah.mentormatcher.domain.mentorship.JoinRequest;

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

import com.jonah.mentormatcher.domain.mentorship.JoinRequest;

/**
 * 
 * @author WitchcraftMDA Seam Cartridge - 
 *
 */
public abstract class JoinRequestListQueryBase
		extends
			BaseQuery<JoinRequest, Long> {

	private static final String EJBQL = "select joinRequest from JoinRequest joinRequest";

	protected JoinRequest joinRequest = new JoinRequest();

	public JoinRequest getJoinRequest() {
		return joinRequest;
	}

	@Override
	protected String getql() {
		return EJBQL;
	}

	@Override
	public Class<JoinRequest> getEntityClass() {
		return JoinRequest.class;
	}

	@Override
	public String[] getEntityRestrictions() {
		return RESTRICTIONS;
	}

	private static final String[] RESTRICTIONS = {
			"joinRequest.id = #{joinRequestList.joinRequest.id}",

			"joinRequest.mentorshipOffering.id = #{joinRequestList.joinRequest.mentorshipOffering.id}",

			"lower(joinRequest.requestText) like concat(lower(#{joinRequestList.joinRequest.requestText}),'%')",

			"joinRequest.prospectiveMentee.id = #{joinRequestList.joinRequest.prospectiveMentee.id}",

			"joinRequest.dateCreated <= #{joinRequestList.dateCreatedRange.end}",
			"joinRequest.dateCreated >= #{joinRequestList.dateCreatedRange.begin}",};

	@Observer("archivedJoinRequest")
	public void onArchive() {
		refresh();
	}

	/** create comma delimited row 
	 * @param builder
	 */
	//@Override
	public void createCsvString(StringBuilder builder, JoinRequest e) {

		builder.append("\""
				+ (e.getMentorshipOffering() != null ? e
						.getMentorshipOffering().getDisplayName().replace(",",
								"") : "") + "\",");

		builder.append("\""
				+ (e.getRequestText() != null ? e.getRequestText().replace(",",
						"") : "") + "\",");

		builder.append("\""
				+ (e.getProspectiveMentee() != null ? e.getProspectiveMentee()
						.getDisplayName().replace(",", "") : "") + "\",");

		builder.append("\r\n");
	}

	/** create the headings 
	 * @param builder
	 */
	//@Override
	public void createCSvTitles(StringBuilder builder) {

		builder.append("MentorshipOffering" + ",");

		builder.append("RequestText" + ",");

		builder.append("ProspectiveMentee" + ",");

		builder.append("\r\n");
	}
}
