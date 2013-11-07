package com.oreon.cerebrum.web.action.patient;

import com.oreon.cerebrum.patient.BedStay;

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

import java.math.BigDecimal;

import org.jboss.seam.annotations.security.Restrict;

import org.jboss.seam.annotations.In;

import com.oreon.cerebrum.patient.BedStay;

/**
 * 
 * @author WitchcraftMDA Seam Cartridge - 
 *
 */
public abstract class BedStayListQueryBase extends BaseQuery<BedStay, Long> {

	private static final String EJBQL = "select bedStay from BedStay bedStay";

	protected BedStay bedStay = new BedStay();

	@In(create = true)
	BedStayAction bedStayAction;

	public BedStayListQueryBase() {
		super();
		setOrderColumn("id");
		setOrderDirection("desc");
	}

	public BedStay getBedStay() {
		return bedStay;
	}

	@Override
	public BedStay getInstance() {
		return getBedStay();
	}

	@Override
	protected String getql() {
		return EJBQL;
	}

	@Override
	//@Restrict("#{s:hasPermission('bedStay', 'view')}")
	public List<BedStay> getResultList() {
		return super.getResultList();
	}

	@Override
	public Class<BedStay> getEntityClass() {
		return BedStay.class;
	}

	@Override
	public String[] getEntityRestrictions() {
		return RESTRICTIONS;
	}

	private Range<Date> fromDateRange = new Range<Date>();

	public Range<Date> getFromDateRange() {
		return fromDateRange;
	}
	public void setFromDate(Range<Date> fromDateRange) {
		this.fromDateRange = fromDateRange;
	}

	private Range<Date> toDateRange = new Range<Date>();

	public Range<Date> getToDateRange() {
		return toDateRange;
	}
	public void setToDate(Range<Date> toDateRange) {
		this.toDateRange = toDateRange;
	}

	private static final String[] RESTRICTIONS = {
			"bedStay.id = #{bedStayList.bedStay.id}",

			"bedStay.archived = #{bedStayList.bedStay.archived}",

			"bedStay.fromDate >= #{bedStayList.fromDateRange.begin}",
			"bedStay.fromDate <= #{bedStayList.fromDateRange.end}",

			"bedStay.toDate >= #{bedStayList.toDateRange.begin}",
			"bedStay.toDate <= #{bedStayList.toDateRange.end}",

			"bedStay.admission.id = #{bedStayList.bedStay.admission.id}",

			"bedStay.bed.id = #{bedStayList.bedStay.bed.id}",

			"bedStay.dateCreated <= #{bedStayList.dateCreatedRange.end}",
			"bedStay.dateCreated >= #{bedStayList.dateCreatedRange.begin}",};

	public List<BedStay> getBedStaysByAdmission(
			com.oreon.cerebrum.patient.Admission admission) {
		bedStay.setAdmission(admission);
		return getResultList();
	}

	@Observer("archivedBedStay")
	public void onArchive() {
		refresh();
	}

	public void setAdmissionId(Long id) {
		if (bedStay.getAdmission() == null) {
			bedStay.setAdmission(new com.oreon.cerebrum.patient.Admission());
		}
		bedStay.getAdmission().setId(id);
	}

	public Long getAdmissionId() {
		return bedStay.getAdmission() == null ? null : bedStay.getAdmission()
				.getId();
	}

	public void setBedId(Long id) {
		if (bedStay.getBed() == null) {
			bedStay.setBed(new com.oreon.cerebrum.facility.Bed());
		}
		bedStay.getBed().setId(id);
	}

	public Long getBedId() {
		return bedStay.getBed() == null ? null : bedStay.getBed().getId();
	}

	//@Restrict("#{s:hasPermission('bedStay', 'delete')}")
	public void archiveById(Long id) {
		bedStayAction.archiveById(id);
		refresh();
	}

	/** create comma delimited row 
	 * @param builder
	 */
	//@Override
	public void createCsvString(StringBuilder builder, BedStay e) {

		builder.append("\"" + (e.getFromDate() != null ? e.getFromDate() : "")
				+ "\",");

		builder.append("\"" + (e.getToDate() != null ? e.getToDate() : "")
				+ "\",");

		builder.append("\""
				+ (e.getAdmission() != null ? e.getAdmission().getDisplayName()
						.replace(",", "") : "") + "\",");

		builder.append("\""
				+ (e.getBed() != null ? e.getBed().getDisplayName().replace(
						",", "") : "") + "\",");

		builder.append("\r\n");
	}

	/** create the headings 
	 * @param builder
	 */
	//@Override
	public void createCSvTitles(StringBuilder builder) {

		builder.append("FromDate" + ",");

		builder.append("ToDate" + ",");

		builder.append("Admission" + ",");

		builder.append("Bed" + ",");

		builder.append("\r\n");
	}
}
