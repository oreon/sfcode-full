package com.nas.recovery.web.action.issues;

import org.wc.trackrite.issues.Issue;

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

import org.wc.trackrite.issues.Issue;

/**
 * 
 * @author WitchcraftMDA Seam Cartridge - 
 *
 */
public abstract class IssueListQueryBase extends BaseQuery<Issue, Long> {

	private static final String EJBQL = "select issue from Issue issue";

	protected Issue issue = new Issue();

	public Issue getIssue() {
		return issue;
	}

	private org.wc.trackrite.issues.MilestoneRelease milestoneReleaseToSearch;

	public void setMilestoneReleaseToSearch(
			org.wc.trackrite.issues.MilestoneRelease milestoneReleaseToSearch) {
		this.milestoneReleaseToSearch = milestoneReleaseToSearch;
	}

	public org.wc.trackrite.issues.MilestoneRelease getMilestoneReleaseToSearch() {
		return milestoneReleaseToSearch;
	}

	@Override
	protected String getql() {
		return EJBQL;
	}

	@Override
	public Class<Issue> getEntityClass() {
		return Issue.class;
	}

	@Override
	public String[] getEntityRestrictions() {
		return RESTRICTIONS;
	}

	private Range<Date> closeTimeRange = new Range<Date>();
	public Range<Date> getCloseTimeRange() {
		return closeTimeRange;
	}
	public void setCloseTime(Range<Date> closeTimeRange) {
		this.closeTimeRange = closeTimeRange;
	}

	private Range<Integer> estimateRange = new Range<Integer>();
	public Range<Integer> getEstimateRange() {
		return estimateRange;
	}
	public void setEstimate(Range<Integer> estimateRange) {
		this.estimateRange = estimateRange;
	}

	private static final String[] RESTRICTIONS = {
			"issue.id = #{issueList.issue.id}",

			"lower(issue.title) like concat(lower(#{issueList.issue.title}),'%')",

			"lower(issue.description) like concat(lower(#{issueList.issue.description}),'%')",

			"issue.project.id = #{issueList.issue.project.id}",

			"issue.status = #{issueList.issue.status}",

			"issue.priority = #{issueList.issue.priority}",

			"issue.developer.id = #{issueList.issue.developer.id}",

			"issue.closeTime >= #{issueList.closeTimeRange.begin}",
			"issue.closeTime <= #{issueList.closeTimeRange.end}",

			"issue.estimate >= #{issueList.estimateRange.begin}",
			"issue.estimate <= #{issueList.estimateRange.end}",

			"lower(issue.creator) like concat(lower(#{issueList.issue.creator}),'%')",

			"issue.category.id = #{issueList.issue.category.id}",

			"issue.severity = #{issueList.issue.severity}",

			"issue.qa.id = #{issueList.issue.qa.id}",

			"#{issueList.milestoneReleaseToSearch} in elements(issue.milestoneReleases)",

			"issue.dateCreated <= #{issueList.dateCreatedRange.end}",
			"issue.dateCreated >= #{issueList.dateCreatedRange.begin}",};

	public List<Issue> getIssuesByProject(
			org.wc.trackrite.issues.Project project) {
		//setMaxResults(10000);
		issue.setProject(project);
		return getResultList();
	}

	public List<Issue> getIssuesByDeveloper(
			org.wc.trackrite.domain.Employee employee) {
		//setMaxResults(10000);
		issue.setDeveloper(employee);
		return getResultList();
	}

	@Observer("archivedIssue")
	public void onArchive() {
		refresh();
	}

	@Override
	protected void setupForAutoComplete(String input) {

		issue.setTitle(input);

	}

	/** create comma delimited row 
	 * @param builder
	 */
	//@Override
	public void createCsvString(StringBuilder builder, Issue e) {

		builder.append("\"" + (e.getTitle() != null ? e.getTitle() : "")
				+ "\",");

		builder.append("\""
				+ (e.getDescription() != null ? e.getDescription() : "")
				+ "\",");

		builder.append("\""
				+ (e.getProject() != null
						? e.getProject().getDisplayName()
						: "") + "\",");

		builder.append("\"" + (e.getStatus() != null ? e.getStatus() : "")
				+ "\",");

		builder.append("\"" + (e.getPriority() != null ? e.getPriority() : "")
				+ "\",");

		builder.append("\""
				+ (e.getDeveloper() != null
						? e.getDeveloper().getDisplayName()
						: "") + "\",");

		builder.append("\""
				+ (e.getCloseTime() != null ? e.getCloseTime() : "") + "\",");

		builder.append("\"" + (e.getEstimate() != null ? e.getEstimate() : "")
				+ "\",");

		builder.append("\"" + (e.getCreator() != null ? e.getCreator() : "")
				+ "\",");

		builder.append("\""
				+ (e.getCategory() != null
						? e.getCategory().getDisplayName()
						: "") + "\",");

		builder.append("\"" + (e.getSeverity() != null ? e.getSeverity() : "")
				+ "\",");

		builder
				.append("\""
						+ (e.getQa() != null ? e.getQa().getDisplayName() : "")
						+ "\",");

		builder.append("\""
				+ (e.getMilestoneReleases() != null
						? e.getMilestoneReleases()
						: "") + "\",");

		builder.append("\r\n");
	}

	/** create the headings 
	 * @param builder
	 */
	//@Override
	public void createCSvTitles(StringBuilder builder) {

		builder.append("Title" + ",");

		builder.append("Description" + ",");

		builder.append("Project" + ",");

		builder.append("Status" + ",");

		builder.append("Priority" + ",");

		builder.append("Developer" + ",");

		builder.append("CloseTime" + ",");

		builder.append("Estimate" + ",");

		builder.append("Creator" + ",");

		builder.append("Category" + ",");

		builder.append("Severity" + ",");

		builder.append("Qa" + ",");

		builder.append("MilestoneReleases" + ",");

		builder.append("\r\n");
	}
}
