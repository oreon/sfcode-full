package com.nas.recovery.web.action.legal;

import com.nas.recovery.domain.legal.InsurerProcess;

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

import com.nas.recovery.domain.legal.InsurerProcess;

/**
 * @author WitchcraftMDA Seam Cartridge
 *
 */
public abstract class InsurerProcessListQueryBase
		extends
			BaseQuery<InsurerProcess, Long> {

	//private static final String EJBQL = "select insurerProcess from InsurerProcess insurerProcess";

	private InsurerProcess insurerProcess = new InsurerProcess();

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
			"insurerProcess.id = #{insurerProcessList.insurerProcess.id}",

			"insurerProcess.legal.id = #{insurerProcessList.insurerProcess.legal.id}",

			"insurerProcess.process = #{insurerProcessList.insurerProcess.process}",

			"insurerProcess.completedDate >= #{insurerProcessList.process_completedDateRange.begin}",
			"insurerProcess.completedDate <= #{insurerProcessList.process_completedDateRange.end}",

			"insurerProcess.dueDate >= #{insurerProcessList.process_dueDateRange.begin}",
			"insurerProcess.dueDate <= #{insurerProcessList.process_dueDateRange.end}",

			"insurerProcess.processNumber >= #{insurerProcessList.process_processNumberRange.begin}",
			"insurerProcess.processNumber <= #{insurerProcessList.process_processNumberRange.end}",

			"insurerProcess.documentAdded = #{insurerProcessList.insurerProcess.documentAdded}",

			"insurerProcess.notRequired = #{insurerProcessList.insurerProcess.notRequired}",

			"lower(insurerProcess.explanation) like concat(lower(#{insurerProcessList.insurerProcess.explanation}),'%')",

			"insurerProcess.dateCreated <= #{insurerProcessList.dateCreatedRange.end}",
			"insurerProcess.dateCreated >= #{insurerProcessList.dateCreatedRange.begin}",};

	public InsurerProcess getInsurerProcess() {
		return insurerProcess;
	}

	@Override
	public Class<InsurerProcess> getEntityClass() {
		return InsurerProcess.class;
	}

	@Override
	public String[] getEntityRestrictions() {
		// TODO Auto-generated method stub
		return RESTRICTIONS;
	}

	@Observer("archivedInsurerProcess")
	public void onArchive() {
		refresh();
	}
}
