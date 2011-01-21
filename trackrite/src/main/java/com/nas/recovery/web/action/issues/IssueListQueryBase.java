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

		if (e.getTitle() != null)

			builder.append(e.getTitle() + ",");

		builder.append(",");

		if (e.getDescription() != null)

			builder.append(e.getDescription() + ",");

		builder.append(",");

		if (e.getProject() != null)

			builder.append(e.getProject().getDisplayName());

		builder.append(",");

		if (e.getStatus() != null)

			builder.append(e.getStatus() + ",");

		builder.append(",");

		if (e.getPriority() != null)

			builder.append(e.getPriority() + ",");

		builder.append(",");

		if (e.getDeveloper() != null)

			builder.append(e.getDeveloper().getDisplayName());

		builder.append(",");

		if (e.getCloseTime() != null)

			builder.append(e.getCloseTime() + ",");

		builder.append(",");

		if (e.getEstimate() != null)

			builder.append(e.getEstimate() + ",");

		builder.append(",");

		if (e.getCreator() != null)

			builder.append(e.getCreator() + ",");

		builder.append(",");

		if (e.getCategory() != null)

			builder.append(e.getCategory().getDisplayName());

		builder.append(",");

		if (e.getSeverity() != null)

			builder.append(e.getSeverity() + ",");

		builder.append(",");

		if (e.getQa() != null)

			builder.append(e.getQa().getDisplayName());

		builder.append(",");

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

		builder.append("\r\n");
	}
}
