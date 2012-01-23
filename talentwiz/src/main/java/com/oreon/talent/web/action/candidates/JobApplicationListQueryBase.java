package com.oreon.talent.web.action.candidates;

import com.oreon.talent.candidates.JobApplication;

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

import com.oreon.talent.candidates.JobApplication;

/**
 * 
 * @author WitchcraftMDA Seam Cartridge - 
 *
 */
public abstract class JobApplicationListQueryBase
		extends
			BaseQuery<JobApplication, Long> {

	private static final String EJBQL = "select jobApplication from JobApplication jobApplication";

	protected JobApplication jobApplication = new JobApplication();

	public JobApplication getJobApplication() {
		return jobApplication;
	}

	@Override
	protected String getql() {
		return EJBQL;
	}

	@Override
	public Class<JobApplication> getEntityClass() {
		return JobApplication.class;
	}

	@Override
	public String[] getEntityRestrictions() {
		return RESTRICTIONS;
	}

	private static final String[] RESTRICTIONS = {
			"jobApplication.id = #{jobApplicationList.jobApplication.id}",

			"jobApplication.candidate.id = #{jobApplicationList.jobApplication.candidate.id}",

			"jobApplication.job.id = #{jobApplicationList.jobApplication.job.id}",

			"jobApplication.dateCreated <= #{jobApplicationList.dateCreatedRange.end}",
			"jobApplication.dateCreated >= #{jobApplicationList.dateCreatedRange.begin}",};

	@Observer("archivedJobApplication")
	public void onArchive() {
		refresh();
	}

	/** create comma delimited row 
	 * @param builder
	 */
	//@Override
	public void createCsvString(StringBuilder builder, JobApplication e) {

		builder.append("\""
				+ (e.getCandidate() != null ? e.getCandidate().getDisplayName()
						.replace(",", "") : "") + "\",");

		builder.append("\""
				+ (e.getJob() != null ? e.getJob().getDisplayName().replace(
						",", "") : "") + "\",");

		builder.append("\r\n");
	}

	/** create the headings 
	 * @param builder
	 */
	//@Override
	public void createCSvTitles(StringBuilder builder) {

		builder.append("Candidate" + ",");

		builder.append("Job" + ",");

		builder.append("\r\n");
	}
}
