package com.nas.recovery.web.action.legal;

import com.nas.recovery.domain.legal.Bankruptcy;

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

import com.nas.recovery.domain.legal.Bankruptcy;

/**
 * @author WitchcraftMDA Seam Cartridge
 *
 */
public abstract class BankruptcyListQueryBase
		extends
			BaseQuery<Bankruptcy, Long> {

	//private static final String EJBQL = "select bankruptcy from Bankruptcy bankruptcy";

	private Bankruptcy bankruptcy = new Bankruptcy();

	private Range<Integer> bankruptcy_legalNumberRange = new Range<Integer>();
	public Range<Integer> getBankruptcy_legalNumberRange() {
		return bankruptcy_legalNumberRange;
	}
	public void setBankruptcy_legalNumber(
			Range<Integer> bankruptcy_legalNumberRange) {
		this.bankruptcy_legalNumberRange = bankruptcy_legalNumberRange;
	}

	private Range<Date> bankruptcy_dateFiledRange = new Range<Date>();
	public Range<Date> getBankruptcy_dateFiledRange() {
		return bankruptcy_dateFiledRange;
	}
	public void setBankruptcy_dateFiled(Range<Date> bankruptcy_dateFiledRange) {
		this.bankruptcy_dateFiledRange = bankruptcy_dateFiledRange;
	}

	private Range<Date> bankruptcy_dischargedDateRange = new Range<Date>();
	public Range<Date> getBankruptcy_dischargedDateRange() {
		return bankruptcy_dischargedDateRange;
	}
	public void setBankruptcy_dischargedDate(
			Range<Date> bankruptcy_dischargedDateRange) {
		this.bankruptcy_dischargedDateRange = bankruptcy_dischargedDateRange;
	}

	private static final String[] RESTRICTIONS = {
			"bankruptcy.id = #{bankruptcyList.bankruptcy.id}",

			"bankruptcy.legalNumber >= #{bankruptcyList.bankruptcy_legalNumberRange.begin}",
			"bankruptcy.legalNumber <= #{bankruptcyList.bankruptcy_legalNumberRange.end}",

			"lower(bankruptcy.trustee) like concat(lower(#{bankruptcyList.bankruptcy.trustee}),'%')",

			"lower(bankruptcy.name) like concat(lower(#{bankruptcyList.bankruptcy.name}),'%')",

			"bankruptcy.dateFiled >= #{bankruptcyList.bankruptcy_dateFiledRange.begin}",
			"bankruptcy.dateFiled <= #{bankruptcyList.bankruptcy_dateFiledRange.end}",

			"bankruptcy.dischargedDate >= #{bankruptcyList.bankruptcy_dischargedDateRange.begin}",
			"bankruptcy.dischargedDate <= #{bankruptcyList.bankruptcy_dischargedDateRange.end}",

			"bankruptcy.proofOfClaim = #{bankruptcyList.bankruptcy.proofOfClaim}",

			"bankruptcy.dateCreated <= #{bankruptcyList.dateCreatedRange.end}",
			"bankruptcy.dateCreated >= #{bankruptcyList.dateCreatedRange.begin}",};

	public Bankruptcy getBankruptcy() {
		return bankruptcy;
	}

	@Override
	public Class<Bankruptcy> getEntityClass() {
		return Bankruptcy.class;
	}

	@Override
	public String[] getEntityRestrictions() {
		// TODO Auto-generated method stub
		return RESTRICTIONS;
	}

	@Observer("archivedBankruptcy")
	public void onArchive() {
		refresh();
	}
}
