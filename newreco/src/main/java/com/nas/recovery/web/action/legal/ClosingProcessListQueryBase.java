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

	private Range<Date> process_dueDateRange = new Range<Date>();
	public Range<Date> getProcess_dueDateRange() {
		return process_dueDateRange;
	}
	public void setProcess_dueDate(Range<Date> process_dueDateRange) {
		this.process_dueDateRange = process_dueDateRange;
	}

	private Range<Integer> process_processNumberRange = new Range<Integer>();
	public Range<Integer> getProcess_processNumberRange() {
		return process_processNumberRange;
	}
	public void setProcess_processNumber(
			Range<Integer> process_processNumberRange) {
		this.process_processNumberRange = process_processNumberRange;
	}

	private static final String[] RESTRICTIONS = {
			"closingProcess.id = #{closingProcessList.closingProcess.id}",

			"closingProcess.legal = #{closingProcessList.closingProcess.legal}",

			"closingProcess.process = #{closingProcessList.closingProcess.process}",

			"closingProcess.completedDate >= #{closingProcessList.process_completedDateRange.begin}",
			"closingProcess.completedDate <= #{closingProcessList.process_completedDateRange.end}",

			"closingProcess.dueDate >= #{closingProcessList.process_dueDateRange.begin}",
			"closingProcess.dueDate <= #{closingProcessList.process_dueDateRange.end}",

			"closingProcess.processNumber >= #{closingProcessList.process_processNumberRange.begin}",
			"closingProcess.processNumber <= #{closingProcessList.process_processNumberRange.end}",

			"closingProcess.documentAdded = #{closingProcessList.closingProcess.documentAdded}",

			"closingProcess.notRequired = #{closingProcessList.closingProcess.notRequired}",

			"closingProcess.explanation = #{closingProcessList.closingProcess.explanation}",

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
