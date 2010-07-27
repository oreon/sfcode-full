package com.nas.recovery.web.action.realestate;

import com.nas.recovery.domain.realestate.Cma;

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

import com.nas.recovery.domain.realestate.Cma;

/**
 * @author WitchcraftMDA Seam Cartridge
 *
 */
public abstract class CmaListQueryBase extends BaseQuery<Cma, Long> {

	//private static final String EJBQL = "select cma from Cma cma";

	private Cma cma = new Cma();

	private Range<Date> cma_cmaOrderedRange = new Range<Date>();
	public Range<Date> getCma_cmaOrderedRange() {
		return cma_cmaOrderedRange;
	}
	public void setCma_cmaOrdered(Range<Date> cma_cmaOrderedRange) {
		this.cma_cmaOrderedRange = cma_cmaOrderedRange;
	}

	private Range<Date> cma_cmaDueDateRange = new Range<Date>();
	public Range<Date> getCma_cmaDueDateRange() {
		return cma_cmaDueDateRange;
	}
	public void setCma_cmaDueDate(Range<Date> cma_cmaDueDateRange) {
		this.cma_cmaDueDateRange = cma_cmaDueDateRange;
	}

	private Range<Date> cma_cmaReceivedRange = new Range<Date>();
	public Range<Date> getCma_cmaReceivedRange() {
		return cma_cmaReceivedRange;
	}
	public void setCma_cmaReceived(Range<Date> cma_cmaReceivedRange) {
		this.cma_cmaReceivedRange = cma_cmaReceivedRange;
	}

	private Range<Double> cma_cmaAsIsValueRange = new Range<Double>();
	public Range<Double> getCma_cmaAsIsValueRange() {
		return cma_cmaAsIsValueRange;
	}
	public void setCma_cmaAsIsValue(Range<Double> cma_cmaAsIsValueRange) {
		this.cma_cmaAsIsValueRange = cma_cmaAsIsValueRange;
	}

	private Range<Double> cma_cmaImprovedValueRange = new Range<Double>();
	public Range<Double> getCma_cmaImprovedValueRange() {
		return cma_cmaImprovedValueRange;
	}
	public void setCma_cmaImprovedValue(Range<Double> cma_cmaImprovedValueRange) {
		this.cma_cmaImprovedValueRange = cma_cmaImprovedValueRange;
	}

	private static final String[] RESTRICTIONS = {
			"cma.id = #{cmaList.cma.id}",

			"cma.cmaOrdered >= #{cmaList.cma_cmaOrderedRange.begin}",
			"cma.cmaOrdered <= #{cmaList.cma_cmaOrderedRange.end}",

			"cma.cmaDueDate >= #{cmaList.cma_cmaDueDateRange.begin}",
			"cma.cmaDueDate <= #{cmaList.cma_cmaDueDateRange.end}",

			"cma.cmaReceived >= #{cmaList.cma_cmaReceivedRange.begin}",
			"cma.cmaReceived <= #{cmaList.cma_cmaReceivedRange.end}",

			"cma.cmaAsIsValue >= #{cmaList.cma_cmaAsIsValueRange.begin}",
			"cma.cmaAsIsValue <= #{cmaList.cma_cmaAsIsValueRange.end}",

			"cma.cmaImprovedValue >= #{cmaList.cma_cmaImprovedValueRange.begin}",
			"cma.cmaImprovedValue <= #{cmaList.cma_cmaImprovedValueRange.end}",

			"cma.lockBox = #{cmaList.cma.lockBox}",

			"cma.dateCreated <= #{cmaList.dateCreatedRange.end}",
			"cma.dateCreated >= #{cmaList.dateCreatedRange.begin}",};

	public Cma getCma() {
		return cma;
	}

	@Override
	public Class<Cma> getEntityClass() {
		return Cma.class;
	}

	@Override
	public String[] getEntityRestrictions() {
		// TODO Auto-generated method stub
		return RESTRICTIONS;
	}

	@Observer("archivedCma")
	public void onArchive() {
		refresh();
	}
}
