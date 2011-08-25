package com.jonah.mentormatcher.web.action.mentorship;

import com.jonah.mentormatcher.domain.mentorship.Mentorship;

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

import com.jonah.mentormatcher.domain.mentorship.Mentorship;

/**
 * 
 * @author WitchcraftMDA Seam Cartridge - 
 *
 */
public abstract class MentorshipListQueryBase
		extends
			BaseQuery<Mentorship, Long> {

	private static final String EJBQL = "select mentorship from Mentorship mentorship";

	protected Mentorship mentorship = new Mentorship();

	public Mentorship getMentorship() {
		return mentorship;
	}

	@Override
	protected String getql() {
		return EJBQL;
	}

	@Override
	public Class<Mentorship> getEntityClass() {
		return Mentorship.class;
	}

	@Override
	public String[] getEntityRestrictions() {
		return RESTRICTIONS;
	}

	private Range<Date> startDateRange = new Range<Date>();
	public Range<Date> getStartDateRange() {
		return startDateRange;
	}
	public void setStartDate(Range<Date> startDateRange) {
		this.startDateRange = startDateRange;
	}

	private Range<Date> endDateRange = new Range<Date>();
	public Range<Date> getEndDateRange() {
		return endDateRange;
	}
	public void setEndDate(Range<Date> endDateRange) {
		this.endDateRange = endDateRange;
	}

	private static final String[] RESTRICTIONS = {
			"mentorship.id = #{mentorshipList.mentorship.id}",

			"mentorship.mentor.id = #{mentorshipList.mentorship.mentor.id}",

			"mentorship.startDate >= #{mentorshipList.startDateRange.begin}",
			"mentorship.startDate <= #{mentorshipList.startDateRange.end}",

			"mentorship.endDate >= #{mentorshipList.endDateRange.begin}",
			"mentorship.endDate <= #{mentorshipList.endDateRange.end}",

			"mentorship.dateCreated <= #{mentorshipList.dateCreatedRange.end}",
			"mentorship.dateCreated >= #{mentorshipList.dateCreatedRange.begin}",};

	@Observer("archivedMentorship")
	public void onArchive() {
		refresh();
	}

	/** create comma delimited row 
	 * @param builder
	 */
	//@Override
	public void createCsvString(StringBuilder builder, Mentorship e) {

		builder.append("\""
				+ (e.getMentor() != null ? e.getMentor().getDisplayName()
						.replace(",", "") : "") + "\",");

		builder.append("\""
				+ (e.getStartDate() != null ? e.getStartDate() : "") + "\",");

		builder.append("\"" + (e.getEndDate() != null ? e.getEndDate() : "")
				+ "\",");

		builder.append("\r\n");
	}

	/** create the headings 
	 * @param builder
	 */
	//@Override
	public void createCSvTitles(StringBuilder builder) {

		builder.append("Mentor" + ",");

		builder.append("StartDate" + ",");

		builder.append("EndDate" + ",");

		builder.append("\r\n");
	}
}
