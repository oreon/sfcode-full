package com.oreon.smartsis.web.action.exam;

import com.oreon.smartsis.exam.ElectronicExamEvent;

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

import com.oreon.smartsis.exam.ElectronicExamEvent;

/**
 * 
 * @author WitchcraftMDA Seam Cartridge - 
 *
 */
public abstract class ElectronicExamEventListQueryBase
		extends
			BaseQuery<ElectronicExamEvent, Long> {

	private static final String EJBQL = "select electronicExamEvent from ElectronicExamEvent electronicExamEvent";

	protected ElectronicExamEvent electronicExamEvent = new ElectronicExamEvent();

	public ElectronicExamEvent getElectronicExamEvent() {
		return electronicExamEvent;
	}

	@Override
	protected String getql() {
		return EJBQL;
	}

	@Override
	public Class<ElectronicExamEvent> getEntityClass() {
		return ElectronicExamEvent.class;
	}

	@Override
	public String[] getEntityRestrictions() {
		return RESTRICTIONS;
	}

	private Range<Date> dateOfExamRange = new Range<Date>();
	public Range<Date> getDateOfExamRange() {
		return dateOfExamRange;
	}
	public void setDateOfExam(Range<Date> dateOfExamRange) {
		this.dateOfExamRange = dateOfExamRange;
	}

	private static final String[] RESTRICTIONS = {
			"electronicExamEvent.id = #{electronicExamEventList.electronicExamEvent.id}",

			"electronicExamEvent.electronicExam.id = #{electronicExamEventList.electronicExamEvent.electronicExam.id}",

			"electronicExamEvent.dateOfExam >= #{electronicExamEventList.dateOfExamRange.begin}",
			"electronicExamEvent.dateOfExam <= #{electronicExamEventList.dateOfExamRange.end}",

			"lower(electronicExamEvent.remarks) like concat(lower(#{electronicExamEventList.electronicExamEvent.remarks}),'%')",

			"electronicExamEvent.dateCreated <= #{electronicExamEventList.dateCreatedRange.end}",
			"electronicExamEvent.dateCreated >= #{electronicExamEventList.dateCreatedRange.begin}",};

	@Observer("archivedElectronicExamEvent")
	public void onArchive() {
		refresh();
	}

	/** create comma delimited row 
	 * @param builder
	 */
	//@Override
	public void createCsvString(StringBuilder builder, ElectronicExamEvent e) {

		builder.append("\""
				+ (e.getElectronicExam() != null ? e.getElectronicExam()
						.getDisplayName().replace(",", "") : "") + "\",");

		builder.append("\""
				+ (e.getDateOfExam() != null ? e.getDateOfExam() : "") + "\",");

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

		builder.append("ElectronicExam" + ",");

		builder.append("DateOfExam" + ",");

		builder.append("Remarks" + ",");

		builder.append("\r\n");
	}
}
