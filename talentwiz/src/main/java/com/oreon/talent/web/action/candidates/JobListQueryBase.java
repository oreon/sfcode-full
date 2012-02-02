package com.oreon.talent.web.action.candidates;

import com.oreon.talent.candidates.Job;

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

import com.oreon.talent.candidates.Job;

/**
 * 
 * @author WitchcraftMDA Seam Cartridge - 
 *
 */
public abstract class JobListQueryBase extends BaseQuery<Job, Long> {

	private static final String EJBQL = "select job from Job job";

	protected Job job = new Job();

	public Job getJob() {
		return job;
	}

	@Override
	protected String getql() {
		return EJBQL;
	}

	@Override
	public Class<Job> getEntityClass() {
		return Job.class;
	}

	@Override
	public String[] getEntityRestrictions() {
		return RESTRICTIONS;
	}

	private static final String[] RESTRICTIONS = {
			"job.id = #{jobList.job.id}",

			"lower(job.title) like concat(lower(#{jobList.job.title}),'%')",

			"lower(job.description) like concat(lower(#{jobList.job.description}),'%')",

			"job.client.id = #{jobList.job.client.id}",

			"job.active = #{jobList.job.active}",

			"job.dateCreated <= #{jobList.dateCreatedRange.end}",
			"job.dateCreated >= #{jobList.dateCreatedRange.begin}",};

	@Observer("archivedJob")
	public void onArchive() {
		refresh();
	}

	/** create comma delimited row 
	 * @param builder
	 */
	//@Override
	public void createCsvString(StringBuilder builder, Job e) {

		builder.append("\""
				+ (e.getTitle() != null ? e.getTitle().replace(",", "") : "")
				+ "\",");

		builder.append("\""
				+ (e.getDescription() != null ? e.getDescription().replace(",",
						"") : "") + "\",");

		builder.append("\""
				+ (e.getClient() != null ? e.getClient().getDisplayName()
						.replace(",", "") : "") + "\",");

		builder.append("\"" + (e.getActive() != null ? e.getActive() : "")
				+ "\",");

		builder.append("\r\n");
	}

	/** create the headings 
	 * @param builder
	 */
	//@Override
	public void createCSvTitles(StringBuilder builder) {

		builder.append("Title" + ",");

		builder.append("Description" + ",");

		builder.append("Client" + ",");

		builder.append("Active" + ",");

		builder.append("\r\n");
	}
}
