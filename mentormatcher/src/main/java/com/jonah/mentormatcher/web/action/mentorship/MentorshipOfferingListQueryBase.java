package com.jonah.mentormatcher.web.action.mentorship;

import com.jonah.mentormatcher.domain.mentorship.MentorshipOffering;

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

import com.jonah.mentormatcher.domain.mentorship.MentorshipOffering;

/**
 * 
 * @author WitchcraftMDA Seam Cartridge - 
 *
 */
public abstract class MentorshipOfferingListQueryBase
		extends
			BaseQuery<MentorshipOffering, Long> {

	private static final String EJBQL = "select mentorshipOffering from MentorshipOffering mentorshipOffering";

	protected MentorshipOffering mentorshipOffering = new MentorshipOffering();

	public MentorshipOffering getMentorshipOffering() {
		return mentorshipOffering;
	}

	@Override
	protected String getql() {
		return EJBQL;
	}

	@Override
	public Class<MentorshipOffering> getEntityClass() {
		return MentorshipOffering.class;
	}

	@Override
	public String[] getEntityRestrictions() {
		return RESTRICTIONS;
	}

	private static final String[] RESTRICTIONS = {
			"mentorshipOffering.id = #{mentorshipOfferingList.mentorshipOffering.id}",

			"lower(mentorshipOffering.title) like concat(lower(#{mentorshipOfferingList.mentorshipOffering.title}),'%')",

			"lower(mentorshipOffering.description) like concat(lower(#{mentorshipOfferingList.mentorshipOffering.description}),'%')",

			"lower(mentorshipOffering.keywords) like concat(lower(#{mentorshipOfferingList.mentorshipOffering.keywords}),'%')",

			"mentorshipOffering.inactive = #{mentorshipOfferingList.mentorshipOffering.inactive}",

			"mentorshipOffering.scope = #{mentorshipOfferingList.mentorshipOffering.scope}",

			"mentorshipOffering.category.id = #{mentorshipOfferingList.mentorshipOffering.category.id}",

			"mentorshipOffering.mentor.id = #{mentorshipOfferingList.mentorshipOffering.mentor.id}",

			"mentorshipOffering.dateCreated <= #{mentorshipOfferingList.dateCreatedRange.end}",
			"mentorshipOffering.dateCreated >= #{mentorshipOfferingList.dateCreatedRange.begin}",};

	@Observer("archivedMentorshipOffering")
	public void onArchive() {
		refresh();
	}

	/** create comma delimited row 
	 * @param builder
	 */
	//@Override
	public void createCsvString(StringBuilder builder, MentorshipOffering e) {

		builder.append("\""
				+ (e.getTitle() != null ? e.getTitle().replace(",", "") : "")
				+ "\",");

		builder.append("\""
				+ (e.getDescription() != null ? e.getDescription().replace(",",
						"") : "") + "\",");

		builder.append("\""
				+ (e.getKeywords() != null
						? e.getKeywords().replace(",", "")
						: "") + "\",");

		builder.append("\"" + (e.getInactive() != null ? e.getInactive() : "")
				+ "\",");

		builder.append("\"" + (e.getScope() != null ? e.getScope() : "")
				+ "\",");

		builder.append("\""
				+ (e.getCategory() != null ? e.getCategory().getDisplayName()
						.replace(",", "") : "") + "\",");

		builder.append("\""
				+ (e.getMentor() != null ? e.getMentor().getDisplayName()
						.replace(",", "") : "") + "\",");

		builder.append("\r\n");
	}

	/** create the headings 
	 * @param builder
	 */
	//@Override
	public void createCSvTitles(StringBuilder builder) {

		builder.append("Title" + ",");

		builder.append("Description" + ",");

		builder.append("Keywords" + ",");

		builder.append("Inactive" + ",");

		builder.append("Scope" + ",");

		builder.append("Category" + ",");

		builder.append("Mentor" + ",");

		builder.append("\r\n");
	}
}
