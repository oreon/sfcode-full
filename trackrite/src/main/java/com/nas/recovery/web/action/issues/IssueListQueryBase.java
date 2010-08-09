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

	private Issue issue = new Issue();

	private static final String[] RESTRICTIONS = {
			"issue.id = #{issueList.issue.id}",

			"lower(issue.title) like concat(lower(#{issueList.issue.title}),'%')",

			"issue.description = #{issueList.issue.description}",

			"issue.project = #{issueList.issue.project}",

			"issue.status = #{issueList.issue.status}",

			"issue.priority = #{issueList.issue.priority}",

			"issue.developer = #{issueList.issue.developer}",

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
