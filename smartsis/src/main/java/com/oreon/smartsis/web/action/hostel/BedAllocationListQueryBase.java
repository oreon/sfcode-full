package com.oreon.smartsis.web.action.hostel;

import com.oreon.smartsis.hostel.BedAllocation;

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

import com.oreon.smartsis.hostel.BedAllocation;

/**
 * 
 * @author WitchcraftMDA Seam Cartridge - 
 *
 */
public abstract class BedAllocationListQueryBase
		extends
			BaseQuery<BedAllocation, Long> {

	private static final String EJBQL = "select bedAllocation from BedAllocation bedAllocation";

	protected BedAllocation bedAllocation = new BedAllocation();

	public BedAllocation getBedAllocation() {
		return bedAllocation;
	}

	@Override
	protected String getql() {
		return EJBQL;
	}

	@Override
	public Class<BedAllocation> getEntityClass() {
		return BedAllocation.class;
	}

	@Override
	public String[] getEntityRestrictions() {
		return RESTRICTIONS;
	}

	private Range<Date> startDateRange = new Range<Date>();
	public Range<Date> getStartDateRange() {
		return startDateRange;
	}
	public void setStartDate(Range<Date> startDateRange) {
		this.startDateRange = startDateRange;
	}

	private Range<Date> endDateRange = new Range<Date>();
	public Range<Date> getEndDateRange() {
		return endDateRange;
	}
	public void setEndDate(Range<Date> endDateRange) {
		this.endDateRange = endDateRange;
	}

	private static final String[] RESTRICTIONS = {
			"bedAllocation.id = #{bedAllocationList.bedAllocation.id}",

			"bedAllocation.startDate >= #{bedAllocationList.startDateRange.begin}",
			"bedAllocation.startDate <= #{bedAllocationList.startDateRange.end}",

			"bedAllocation.endDate >= #{bedAllocationList.endDateRange.begin}",
			"bedAllocation.endDate <= #{bedAllocationList.endDateRange.end}",

			"bedAllocation.bed.id = #{bedAllocationList.bedAllocation.bed.id}",

			"bedAllocation.student.id = #{bedAllocationList.bedAllocation.student.id}",

			"lower(bedAllocation.remarks) like concat(lower(#{bedAllocationList.bedAllocation.remarks}),'%')",

			"bedAllocation.dateCreated <= #{bedAllocationList.dateCreatedRange.end}",
			"bedAllocation.dateCreated >= #{bedAllocationList.dateCreatedRange.begin}",};

	@Observer("archivedBedAllocation")
	public void onArchive() {
		refresh();
	}

	/** create comma delimited row 
	 * @param builder
	 */
	//@Override
	public void createCsvString(StringBuilder builder, BedAllocation e) {

		builder.append("\""
				+ (e.getStartDate() != null ? e.getStartDate() : "") + "\",");

		builder.append("\"" + (e.getEndDate() != null ? e.getEndDate() : "")
				+ "\",");

		builder.append("\""
				+ (e.getBed() != null ? e.getBed().getDisplayName().replace(
						",", "") : "") + "\",");

		builder.append("\""
				+ (e.getStudent() != null ? e.getStudent().getDisplayName()
						.replace(",", "") : "") + "\",");

		builder.append("\""
				+ (e.getRemarks() != null
						? e.getRemarks().replace(",", "")
						: "") + "\",");

		builder.append("\r\n");
	}

	/** create the headings 
	 * @param builder
	 */
	//@Override
	public void createCSvTitles(StringBuilder builder) {

		builder.append("StartDate" + ",");

		builder.append("EndDate" + ",");

		builder.append("Bed" + ",");

		builder.append("Student" + ",");

		builder.append("Remarks" + ",");

		builder.append("\r\n");
	}
}
