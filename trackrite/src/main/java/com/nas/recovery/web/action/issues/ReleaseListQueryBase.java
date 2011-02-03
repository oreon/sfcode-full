package com.nas.recovery.web.action.issues;

import org.wc.trackrite.issues.Release;

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

import org.wc.trackrite.issues.Release;

/**
 * 
 * @author WitchcraftMDA Seam Cartridge - 
 *
 */
public abstract class ReleaseListQueryBase extends BaseQuery<Release, Long> {

	private static final String EJBQL = "select release from Release release";

	protected Release release = new Release();

	public Release getRelease() {
		return release;
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
	public Class<Release> getEntityClass() {
		return Release.class;
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
			"release.id = #{releaseList.release.id}",

			"#{releaseList.issueToSearch} in elements(release.issues)",

			"release.dueDate >= #{releaseList.dueDateRange.begin}",
			"release.dueDate <= #{releaseList.dueDateRange.end}",

			"lower(release.comments) like concat(lower(#{releaseList.release.comments}),'%')",

			"release.dateCreated <= #{releaseList.dateCreatedRange.end}",
			"release.dateCreated >= #{releaseList.dateCreatedRange.begin}",};

	@Observer("archivedRelease")
	public void onArchive() {
		refresh();
	}

	/** create comma delimited row 
	 * @param builder
	 */
	//@Override
	public void createCsvString(StringBuilder builder, Release e) {

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
