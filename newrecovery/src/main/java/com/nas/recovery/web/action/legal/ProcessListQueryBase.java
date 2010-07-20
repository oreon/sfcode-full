package com.nas.recovery.web.action.legal;

import com.nas.recovery.domain.legal.Process;

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

import com.nas.recovery.domain.legal.Process;

/**
 * @author WitchcraftMDA Seam Cartridge
 *
 */
public abstract class ProcessListQueryBase extends BaseQuery<Process, Long> {

	//private static final String EJBQL = "select process from Process process";

	private Process process = new Process();

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
			"process.id = #{processList.process.id}",

			"process.completedDate >= #{processList.process_completedDateRange.begin}",
			"process.completedDate <= #{processList.process_completedDateRange.end}",

			"process.processNumber >= #{processList.process_processNumberRange.begin}",
			"process.processNumber <= #{processList.process_processNumberRange.end}",

			"process.expiryDate >= #{processList.process_expiryDateRange.begin}",
			"process.expiryDate <= #{processList.process_expiryDateRange.end}",

			"lower(process.reason) like concat(lower(#{processList.process.reason}),'%')",

			"lower(process.type) like concat(lower(#{processList.process.type}),'%')",

			"lower(process.description) like concat(lower(#{processList.process.description}),'%')",

			"process.legalNumber >= #{processList.process_legalNumberRange.begin}",
			"process.legalNumber <= #{processList.process_legalNumberRange.end}",

			"process.documentAdded = #{processList.process.documentAdded}",

			"process.skip = #{processList.process.skip}",

			"process.dateCreated <= #{processList.dateCreatedRange.end}",
			"process.dateCreated >= #{processList.dateCreatedRange.begin}",};

	public Process getProcess() {
		return process;
	}

	@Override
	public Class<Process> getEntityClass() {
		return Process.class;
	}

	@Override
	public String[] getEntityRestrictions() {
		// TODO Auto-generated method stub
		return RESTRICTIONS;
	}

	@Observer("archivedProcess")
	public void onArchive() {
		refresh();
	}
}
