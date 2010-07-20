package com.nas.recovery.web.action.appraisal;

import com.nas.recovery.domain.appraisal.AppraisalReports;

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

import com.nas.recovery.domain.appraisal.AppraisalReports;

/**
 * @author WitchcraftMDA Seam Cartridge
 *
 */
public abstract class AppraisalReportsListQueryBase
		extends
			BaseQuery<AppraisalReports, Long> {

	//private static final String EJBQL = "select appraisalReports from AppraisalReports appraisalReports";

	private AppraisalReports appraisalReports = new AppraisalReports();

	private Range<Integer> appraisalReports_appraisalNumberRange = new Range<Integer>();
	public Range<Integer> getAppraisalReports_appraisalNumberRange() {
		return appraisalReports_appraisalNumberRange;
	}
	public void setAppraisalReports_appraisalNumber(
			Range<Integer> appraisalReports_appraisalNumberRange) {
		this.appraisalReports_appraisalNumberRange = appraisalReports_appraisalNumberRange;
	}

	private Range<Date> appraisalReports_dateUploadedRange = new Range<Date>();
	public Range<Date> getAppraisalReports_dateUploadedRange() {
		return appraisalReports_dateUploadedRange;
	}
	public void setAppraisalReports_dateUploaded(
			Range<Date> appraisalReports_dateUploadedRange) {
		this.appraisalReports_dateUploadedRange = appraisalReports_dateUploadedRange;
	}

	private static final String[] RESTRICTIONS = {
			"appraisalReports.id = #{appraisalReportsList.appraisalReports.id}",

			"appraisalReports.appraisalNumber >= #{appraisalReportsList.appraisalReports_appraisalNumberRange.begin}",
			"appraisalReports.appraisalNumber <= #{appraisalReportsList.appraisalReports_appraisalNumberRange.end}",

			"lower(appraisalReports.fileName) like concat(lower(#{appraisalReportsList.appraisalReports.fileName}),'%')",

			"appraisalReports.dateUploaded >= #{appraisalReportsList.appraisalReports_dateUploadedRange.begin}",
			"appraisalReports.dateUploaded <= #{appraisalReportsList.appraisalReports_dateUploadedRange.end}",

			"appraisalReports.dateCreated <= #{appraisalReportsList.dateCreatedRange.end}",
			"appraisalReports.dateCreated >= #{appraisalReportsList.dateCreatedRange.begin}",};

	public AppraisalReports getAppraisalReports() {
		return appraisalReports;
	}

	@Override
	public Class<AppraisalReports> getEntityClass() {
		return AppraisalReports.class;
	}

	@Override
	public String[] getEntityRestrictions() {
		// TODO Auto-generated method stub
		return RESTRICTIONS;
	}

	@Observer("archivedAppraisalReports")
	public void onArchive() {
		refresh();
	}
}
