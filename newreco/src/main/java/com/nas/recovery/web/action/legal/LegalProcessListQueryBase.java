package com.nas.recovery.web.action.legal;

import com.nas.recovery.domain.legal.LegalProcess;

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

import com.nas.recovery.domain.legal.LegalProcess;

/**
 * @author WitchcraftMDA Seam Cartridge
 *
 */
public abstract class LegalProcessListQueryBase
		extends
			BaseQuery<LegalProcess, Long> {

	//private static final String EJBQL = "select legalProcess from LegalProcess legalProcess";

	private LegalProcess legalProcess = new LegalProcess();

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
			"legalProcess.id = #{legalProcessList.legalProcess.id}",

			"legalProcess.legal.id = #{legalProcessList.legalProcess.legal.id}",

			"legalProcess.process = #{legalProcessList.legalProcess.process}",

			"legalProcess.completedDate >= #{legalProcessList.process_completedDateRange.begin}",
			"legalProcess.completedDate <= #{legalProcessList.process_completedDateRange.end}",

			"legalProcess.dueDate >= #{legalProcessList.process_dueDateRange.begin}",
			"legalProcess.dueDate <= #{legalProcessList.process_dueDateRange.end}",

			"legalProcess.processNumber >= #{legalProcessList.process_processNumberRange.begin}",
			"legalProcess.processNumber <= #{legalProcessList.process_processNumberRange.end}",

			"legalProcess.documentAdded = #{legalProcessList.legalProcess.documentAdded}",

			"legalProcess.notRequired = #{legalProcessList.legalProcess.notRequired}",

			"lower(legalProcess.explanation) like concat(lower(#{legalProcessList.legalProcess.explanation}),'%')",

			"legalProcess.dateCreated <= #{legalProcessList.dateCreatedRange.end}",
			"legalProcess.dateCreated >= #{legalProcessList.dateCreatedRange.begin}",};

	public LegalProcess getLegalProcess() {
		return legalProcess;
	}

	@Override
	public Class<LegalProcess> getEntityClass() {
		return LegalProcess.class;
	}

	@Override
	public String[] getEntityRestrictions() {
		// TODO Auto-generated method stub
		return RESTRICTIONS;
	}

	@Observer("archivedLegalProcess")
	public void onArchive() {
		refresh();
	}
}
