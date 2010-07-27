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
			"insurerProcess.id = #{insurerProcessList.insurerProcess.id}",

			"insurerProcess.completedDate >= #{insurerProcessList.process_completedDateRange.begin}",
			"insurerProcess.completedDate <= #{insurerProcessList.process_completedDateRange.end}",

			"insurerProcess.processNumber >= #{insurerProcessList.process_processNumberRange.begin}",
			"insurerProcess.processNumber <= #{insurerProcessList.process_processNumberRange.end}",

			"insurerProcess.expiryDate >= #{insurerProcessList.process_expiryDateRange.begin}",
			"insurerProcess.expiryDate <= #{insurerProcessList.process_expiryDateRange.end}",

			"lower(insurerProcess.reason) like concat(lower(#{insurerProcessList.insurerProcess.reason}),'%')",

			"lower(insurerProcess.type) like concat(lower(#{insurerProcessList.insurerProcess.type}),'%')",

			"lower(insurerProcess.description) like concat(lower(#{insurerProcessList.insurerProcess.description}),'%')",

			"insurerProcess.legalNumber >= #{insurerProcessList.process_legalNumberRange.begin}",
			"insurerProcess.legalNumber <= #{insurerProcessList.process_legalNumberRange.end}",

			"insurerProcess.documentAdded = #{insurerProcessList.insurerProcess.documentAdded}",

			"insurerProcess.skip = #{insurerProcessList.insurerProcess.skip}",

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
