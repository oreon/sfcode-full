package com.nas.recovery.web.action.legal;

import com.nas.recovery.domain.legal.ClosingProcess;

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

import com.nas.recovery.domain.legal.ClosingProcess;

/**
 * @author WitchcraftMDA Seam Cartridge
 *
 */
public abstract class ClosingProcessListQueryBase
		extends
			BaseQuery<ClosingProcess, Long> {

	//private static final String EJBQL = "select closingProcess from ClosingProcess closingProcess";

	private ClosingProcess closingProcess = new ClosingProcess();

	private Range<Date> process_completedDateRange = new Range<Date>();
	public Range<Date> getProcess_completedDateRange() {
		return process_completedDateRange;
	}
	public void setProcess_completedDate(Range<Date> process_completedDateRange) {
		this.process_completedDateRange = process_completedDateRange;
	}

	private Range<Integer> process_processNumberRange = new Range<Integer>();
	public Range<Integer> getProcess_processNumberRange() {
		return process_processNumberRange;
	}
	public void setProcess_processNumber(
			Range<Integer> process_processNumberRange) {
		this.process_processNumberRange = process_processNumberRange;
	}

	private Range<Date> process_expiryDateRange = new Range<Date>();
	public Range<Date> getProcess_expiryDateRange() {
		return process_expiryDateRange;
	}
	public void setProcess_expiryDate(Range<Date> process_expiryDateRange) {
		this.process_expiryDateRange = process_expiryDateRange;
	}

	private Range<Integer> process_legalNumberRange = new Range<Integer>();
	public Range<Integer> getProcess_legalNumberRange() {
		return process_legalNumberRange;
	}
	public void setProcess_legalNumber(Range<Integer> process_legalNumberRange) {
		this.process_legalNumberRange = process_legalNumberRange;
	}

	private static final String[] RESTRICTIONS = {
			"closingProcess.id = #{closingProcessList.closingProcess.id}",

			"closingProcess.completedDate >= #{closingProcessList.process_completedDateRange.begin}",
			"closingProcess.completedDate <= #{closingProcessList.process_completedDateRange.end}",

			"closingProcess.processNumber >= #{closingProcessList.process_processNumberRange.begin}",
			"closingProcess.processNumber <= #{closingProcessList.process_processNumberRange.end}",

			"closingProcess.expiryDate >= #{closingProcessList.process_expiryDateRange.begin}",
			"closingProcess.expiryDate <= #{closingProcessList.process_expiryDateRange.end}",

			"lower(closingProcess.reason) like concat(lower(#{closingProcessList.closingProcess.reason}),'%')",

			"lower(closingProcess.type) like concat(lower(#{closingProcessList.closingProcess.type}),'%')",

			"lower(closingProcess.description) like concat(lower(#{closingProcessList.closingProcess.description}),'%')",

			"closingProcess.legalNumber >= #{closingProcessList.process_legalNumberRange.begin}",
			"closingProcess.legalNumber <= #{closingProcessList.process_legalNumberRange.end}",

			"closingProcess.documentAdded = #{closingProcessList.closingProcess.documentAdded}",

			"closingProcess.skip = #{closingProcessList.closingProcess.skip}",

			"closingProcess.dateCreated <= #{closingProcessList.dateCreatedRange.end}",
			"closingProcess.dateCreated >= #{closingProcessList.dateCreatedRange.begin}",};

	public ClosingProcess getClosingProcess() {
		return closingProcess;
	}

	@Override
	public Class<ClosingProcess> getEntityClass() {
		return ClosingProcess.class;
	}

	@Override
	public String[] getEntityRestrictions() {
		// TODO Auto-generated method stub
		return RESTRICTIONS;
	}

	@Observer("archivedClosingProcess")
	public void onArchive() {
		refresh();
	}
}
