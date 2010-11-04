package com.nas.recovery.web.action.issues;

import org.wc.trackrite.issues.Issue;

import org.witchcraft.seam.action.BaseAction;

import java.util.Arrays;
import java.util.Date;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;
import org.jboss.seam.framework.EntityQuery;
import org.witchcraft.base.entity.BaseQuery;
import org.witchcraft.base.entity.Range;

import org.jboss.seam.annotations.Observer;

import org.wc.trackrite.issues.Issue;

/**
 * @author WitchcraftMDA Seam Cartridge
 *
 */
public abstract class IssueListQueryBase extends BaseQuery<Issue, Long> {

	//private static final String EJBQL = "select issue from Issue issue";

	protected Issue issue = new Issue();

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

			"issue.dateCreated <= #{issueList.dateCreatedRange.end}",
			"issue.dateCreated >= #{issueList.dateCreatedRange.begin}",};

	public Issue getIssue() {
		return issue;
	}

	@Override
	public Class<Issue> getEntityClass() {
		return Issue.class;
	}

	@Override
	public String[] getEntityRestrictions() {
		// TODO Auto-generated method stub
		return RESTRICTIONS;
	}

	@Observer("archivedIssue")
	public void onArchive() {
		refresh();
	}
}
