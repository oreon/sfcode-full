package com.nas.recovery.web.action.legal;

import com.nas.recovery.domain.legal.Chargee;

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

import com.nas.recovery.domain.legal.Chargee;

/**
 * @author WitchcraftMDA Seam Cartridge
 *
 */
public abstract class ChargeeListQueryBase extends BaseQuery<Chargee, Long> {

	//private static final String EJBQL = "select chargee from Chargee chargee";

	private Chargee chargee = new Chargee();

	private Range<Double> chargee_amountRange = new Range<Double>();
	public Range<Double> getChargee_amountRange() {
		return chargee_amountRange;
	}
	public void setChargee_amount(Range<Double> chargee_amountRange) {
		this.chargee_amountRange = chargee_amountRange;
	}

	private Range<Integer> chargee_priorityRange = new Range<Integer>();
	public Range<Integer> getChargee_priorityRange() {
		return chargee_priorityRange;
	}
	public void setChargee_priority(Range<Integer> chargee_priorityRange) {
		this.chargee_priorityRange = chargee_priorityRange;
	}

	private static final String[] RESTRICTIONS = {
			"chargee.id = #{chargeeList.chargee.id}",

			"lower(chargee.firstName) like concat(lower(#{chargeeList.chargee.firstName}),'%')",

			"lower(chargee.lastName) like concat(lower(#{chargeeList.chargee.lastName}),'%')",

			"lower(chargee.phone) like concat(lower(#{chargeeList.chargee.phone}),'%')",

			"lower(chargee.email) like concat(lower(#{chargeeList.chargee.email}),'%')",

			"lower(chargee.company) like concat(lower(#{chargeeList.chargee.company}),'%')",

			"chargee.amount >= #{chargeeList.chargee_amountRange.begin}",
			"chargee.amount <= #{chargeeList.chargee_amountRange.end}",

			"chargee.priority >= #{chargeeList.chargee_priorityRange.begin}",
			"chargee.priority <= #{chargeeList.chargee_priorityRange.end}",

			"chargee.chargeeType = #{chargeeList.chargee.chargeeType}",

			"chargee.dateCreated <= #{chargeeList.dateCreatedRange.end}",
			"chargee.dateCreated >= #{chargeeList.dateCreatedRange.begin}",};

	public Chargee getChargee() {
		return chargee;
	}

	@Override
	public Class<Chargee> getEntityClass() {
		return Chargee.class;
	}

	@Override
	public String[] getEntityRestrictions() {
		// TODO Auto-generated method stub
		return RESTRICTIONS;
	}

	@Observer("archivedChargee")
	public void onArchive() {
		refresh();
	}
}
