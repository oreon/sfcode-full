package com.nas.recovery.web.action.issues;

import org.wc.trackrite.issues.MilestoneRelease;

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

import org.wc.trackrite.issues.MilestoneRelease;

/**
 * 
 * @author WitchcraftMDA Seam Cartridge - 
 *
 */
public abstract class MilestoneReleaseListQueryBase
		extends
			BaseQuery<MilestoneRelease, Long> {

	private static final String EJBQL = "select milestoneRelease from MilestoneRelease milestoneRelease";

	protected MilestoneRelease milestoneRelease = new MilestoneRelease();

	public MilestoneRelease getMilestoneRelease() {
		return milestoneRelease;
	}

	private org.wc.trackrite.issues.Issue issueToSearch;

	public void setIssueToSearch(org.wc.trackrite.issues.Issue issueToSearch) {
		this.issueToSearch = issueToSearch;
	}

	public org.wc.trackrite.issues.Issue getIssueToSearch() {
		return issueToSearch;
	}

	@Override
	protected String getql() {
		return EJBQL;
	}

	@Override
	public Class<MilestoneRelease> getEntityClass() {
		return MilestoneRelease.class;
	}

	@Override
	public String[] getEntityRestrictions() {
		return RESTRICTIONS;
	}

	private Range<Date> dueDateRange = new Range<Date>();
	public Range<Date> getDueDateRange() {
		return dueDateRange;
	}
	public void setDueDate(Range<Date> dueDateRange) {
		this.dueDateRange = dueDateRange;
	}

	private static final String[] RESTRICTIONS = {
			"milestoneRelease.id = #{milestoneReleaseList.milestoneRelease.id}",

			"#{milestoneReleaseList.issueToSearch} in elements(milestoneRelease.issues)",

			"milestoneRelease.dueDate >= #{milestoneReleaseList.dueDateRange.begin}",
			"milestoneRelease.dueDate <= #{milestoneReleaseList.dueDateRange.end}",

			"lower(milestoneRelease.comments) like concat(lower(#{milestoneReleaseList.milestoneRelease.comments}),'%')",

			"milestoneRelease.dateCreated <= #{milestoneReleaseList.dateCreatedRange.end}",
			"milestoneRelease.dateCreated >= #{milestoneReleaseList.dateCreatedRange.begin}",};

	@Observer("archivedMilestoneRelease")
	public void onArchive() {
		refresh();
	}

	/** create comma delimited row 
	 * @param builder
	 */
	//@Override
	public void createCsvString(StringBuilder builder, MilestoneRelease e) {

		builder.append("\"" + (e.getIssues() != null ? e.getIssues() : "")
				+ "\",");

		builder.append("\"" + (e.getDueDate() != null ? e.getDueDate() : "")
				+ "\",");

		builder.append("\"" + (e.getComments() != null ? e.getComments() : "")
				+ "\",");

		builder.append("\r\n");
	}

	/** create the headings 
	 * @param builder
	 */
	//@Override
	public void createCSvTitles(StringBuilder builder) {

		builder.append("Issues" + ",");

		builder.append("DueDate" + ",");

		builder.append("Comments" + ",");

		builder.append("\r\n");
	}
}
