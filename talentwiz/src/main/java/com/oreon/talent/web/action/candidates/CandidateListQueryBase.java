package com.oreon.talent.web.action.candidates;

import com.oreon.talent.candidates.Candidate;

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

import com.oreon.talent.candidates.Candidate;

/**
 * 
 * @author WitchcraftMDA Seam Cartridge - 
 *
 */
public abstract class CandidateListQueryBase extends BaseQuery<Candidate, Long> {

	private static final String EJBQL = "select candidate from Candidate candidate";

	protected Candidate candidate = new Candidate();

	public Candidate getCandidate() {
		return candidate;
	}

	@Override
	protected String getql() {
		return EJBQL;
	}

	@Override
	public Class<Candidate> getEntityClass() {
		return Candidate.class;
	}

	@Override
	public String[] getEntityRestrictions() {
		return RESTRICTIONS;
	}

	private Range<Date> appUser_lastLoginRange = new Range<Date>();
	public Range<Date> getAppUser_lastLoginRange() {
		return appUser_lastLoginRange;
	}
	public void setAppUser_lastLogin(Range<Date> appUser_lastLoginRange) {
		this.appUser_lastLoginRange = appUser_lastLoginRange;
	}

	private static final String[] RESTRICTIONS = {
			"candidate.id = #{candidateList.candidate.id}",

			"lower(candidate.firstName) like concat(lower(#{candidateList.candidate.firstName}),'%')",

			"lower(candidate.lastName) like concat(lower(#{candidateList.candidate.lastName}),'%')",

			"lower(candidate.contactDetails.phone) like concat(lower(#{candidateList.candidate.contactDetails.phone}),'%')",

			"lower(candidate.contactDetails.secondaryPhone) like concat(lower(#{candidateList.candidate.contactDetails.secondaryPhone}),'%')",

			"lower(candidate.contactDetails.city) like concat(lower(#{candidateList.candidate.contactDetails.city}),'%')",

			"candidate.availibility = #{candidateList.candidate.availibility}",

			"candidate.preferredJobType = #{candidateList.candidate.preferredJobType}",

			"candidate.chiefExpertiese = #{candidateList.candidate.chiefExpertiese}",

			"candidate.educationLevel = #{candidateList.candidate.educationLevel}",

			"candidate.willingToRelocate = #{candidateList.candidate.willingToRelocate}",

			"lower(candidate.appUser.userName) like concat(lower(#{candidateList.candidate.appUser.userName}),'%')",

			"candidate.appUser.enabled = #{candidateList.candidate.appUser.enabled}",

			"lower(candidate.appUser.email) like concat(lower(#{candidateList.candidate.appUser.email}),'%')",

			"candidate.appUser.lastLogin >= #{candidateList.appUser_lastLoginRange.begin}",
			"candidate.appUser.lastLogin <= #{candidateList.appUser_lastLoginRange.end}",

			"lower(candidate.textResume) like concat(lower(#{candidateList.candidate.textResume}),'%')",

			"candidate.dateCreated <= #{candidateList.dateCreatedRange.end}",
			"candidate.dateCreated >= #{candidateList.dateCreatedRange.begin}",};

	@Observer("archivedCandidate")
	public void onArchive() {
		refresh();
	}

	/** create comma delimited row 
	 * @param builder
	 */
	//@Override
	public void createCsvString(StringBuilder builder, Candidate e) {

		builder.append("\""
				+ (e.getAvailibility() != null ? e.getAvailibility() : "")
				+ "\",");

		builder.append("\""
				+ (e.getPreferredJobType() != null
						? e.getPreferredJobType()
						: "") + "\",");

		builder
				.append("\""
						+ (e.getChiefExpertiese() != null ? e
								.getChiefExpertiese() : "") + "\",");

		builder.append("\""
				+ (e.getEducationLevel() != null ? e.getEducationLevel() : "")
				+ "\",");

		builder.append("\""
				+ (e.getWillingToRelocate() != null
						? e.getWillingToRelocate()
						: "") + "\",");

		builder.append("\""
				+ (e.getAppUser() != null ? e.getAppUser().getDisplayName()
						.replace(",", "") : "") + "\",");

		builder.append("\""
				+ (e.getTextResume() != null ? e.getTextResume().replace(",",
						"") : "") + "\",");

		builder.append("\r\n");
	}

	/** create the headings 
	 * @param builder
	 */
	//@Override
	public void createCSvTitles(StringBuilder builder) {

		builder.append("Availibility" + ",");

		builder.append("PreferredJobType" + ",");

		builder.append("ChiefExpertiese" + ",");

		builder.append("EducationLevel" + ",");

		builder.append("WillingToRelocate" + ",");

		builder.append("AppUser" + ",");

		builder.append("TextResume" + ",");

		builder.append("\r\n");
	}
}
