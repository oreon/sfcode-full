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
			"legalProcess.id = #{legalProcessList.legalProcess.id}",

			"legalProcess.completedDate >= #{legalProcessList.process_completedDateRange.begin}",
			"legalProcess.completedDate <= #{legalProcessList.process_completedDateRange.end}",

			"legalProcess.processNumber >= #{legalProcessList.process_processNumberRange.begin}",
			"legalProcess.processNumber <= #{legalProcessList.process_processNumberRange.end}",

			"legalProcess.expiryDate >= #{legalProcessList.process_expiryDateRange.begin}",
			"legalProcess.expiryDate <= #{legalProcessList.process_expiryDateRange.end}",

			"lower(legalProcess.reason) like concat(lower(#{legalProcessList.legalProcess.reason}),'%')",

			"lower(legalProcess.type) like concat(lower(#{legalProcessList.legalProcess.type}),'%')",

			"lower(legalProcess.description) like concat(lower(#{legalProcessList.legalProcess.description}),'%')",

			"legalProcess.legalNumber >= #{legalProcessList.process_legalNumberRange.begin}",
			"legalProcess.legalNumber <= #{legalProcessList.process_legalNumberRange.end}",

			"legalProcess.documentAdded = #{legalProcessList.legalProcess.documentAdded}",

			"legalProcess.skip = #{legalProcessList.legalProcess.skip}",

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
