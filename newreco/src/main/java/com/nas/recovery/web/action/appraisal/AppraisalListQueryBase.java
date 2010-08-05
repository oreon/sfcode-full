package com.nas.recovery.web.action.appraisal;

import com.nas.recovery.domain.appraisal.Appraisal;

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

import com.nas.recovery.domain.appraisal.Appraisal;

/**
 * @author WitchcraftMDA Seam Cartridge
 *
 */
public abstract class AppraisalListQueryBase extends BaseQuery<Appraisal, Long> {

	//private static final String EJBQL = "select appraisal from Appraisal appraisal";

	private Appraisal appraisal = new Appraisal();

	private Range<Integer> appraisal_appraisalNumberRange = new Range<Integer>();
	public Range<Integer> getAppraisal_appraisalNumberRange() {
		return appraisal_appraisalNumberRange;
	}
	public void setAppraisal_appraisalNumber(
			Range<Integer> appraisal_appraisalNumberRange) {
		this.appraisal_appraisalNumberRange = appraisal_appraisalNumberRange;
	}

	private Range<Date> appraisal_orderedRange = new Range<Date>();
	public Range<Date> getAppraisal_orderedRange() {
		return appraisal_orderedRange;
	}
	public void setAppraisal_ordered(Range<Date> appraisal_orderedRange) {
		this.appraisal_orderedRange = appraisal_orderedRange;
	}

	private Range<Date> appraisal_dueDateRange = new Range<Date>();
	public Range<Date> getAppraisal_dueDateRange() {
		return appraisal_dueDateRange;
	}
	public void setAppraisal_dueDate(Range<Date> appraisal_dueDateRange) {
		this.appraisal_dueDateRange = appraisal_dueDateRange;
	}

	private Range<Date> appraisal_receivedRange = new Range<Date>();
	public Range<Date> getAppraisal_receivedRange() {
		return appraisal_receivedRange;
	}
	public void setAppraisal_received(Range<Date> appraisal_receivedRange) {
		this.appraisal_receivedRange = appraisal_receivedRange;
	}

	private static final String[] RESTRICTIONS = {
			"appraisal.id = #{appraisalList.appraisal.id}",

			"appraisal.appraisalNumber >= #{appraisalList.appraisal_appraisalNumberRange.begin}",
			"appraisal.appraisalNumber <= #{appraisalList.appraisal_appraisalNumberRange.end}",

			"appraisal.specialInstruction = #{appraisalList.appraisal.specialInstruction}",

			"appraisal.serviceType = #{appraisalList.appraisal.serviceType}",

			"appraisal.status = #{appraisalList.appraisal.status}",

			"appraisal.ordered >= #{appraisalList.appraisal_orderedRange.begin}",
			"appraisal.ordered <= #{appraisalList.appraisal_orderedRange.end}",

			"appraisal.dueDate >= #{appraisalList.appraisal_dueDateRange.begin}",
			"appraisal.dueDate <= #{appraisalList.appraisal_dueDateRange.end}",

			"appraisal.received >= #{appraisalList.appraisal_receivedRange.begin}",
			"appraisal.received <= #{appraisalList.appraisal_receivedRange.end}",

			"appraisal.realEstateProperty = #{appraisalList.appraisal.realEstateProperty}",

			"appraisal.dateCreated <= #{appraisalList.dateCreatedRange.end}",
			"appraisal.dateCreated >= #{appraisalList.dateCreatedRange.begin}",};

	public Appraisal getAppraisal() {
		return appraisal;
	}

	@Override
	public Class<Appraisal> getEntityClass() {
		return Appraisal.class;
	}

	@Override
	public String[] getEntityRestrictions() {
		// TODO Auto-generated method stub
		return RESTRICTIONS;
	}

	@Observer("archivedAppraisal")
	public void onArchive() {
		refresh();
	}
}
