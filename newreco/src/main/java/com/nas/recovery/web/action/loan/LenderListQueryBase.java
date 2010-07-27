package com.nas.recovery.web.action.loan;

import com.nas.recovery.domain.loan.Lender;

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

import com.nas.recovery.domain.loan.Lender;

/**
 * @author WitchcraftMDA Seam Cartridge
 *
 */
public abstract class LenderListQueryBase extends BaseQuery<Lender, Long> {

	//private static final String EJBQL = "select lender from Lender lender";

	private Lender lender = new Lender();

	private Range<Integer> address_streetNumberRange = new Range<Integer>();
	public Range<Integer> getAddress_streetNumberRange() {
		return address_streetNumberRange;
	}
	public void setAddress_streetNumber(Range<Integer> address_streetNumberRange) {
		this.address_streetNumberRange = address_streetNumberRange;
	}

	private Range<Integer> address_unitNumberRange = new Range<Integer>();
	public Range<Integer> getAddress_unitNumberRange() {
		return address_unitNumberRange;
	}
	public void setAddress_unitNumber(Range<Integer> address_unitNumberRange) {
		this.address_unitNumberRange = address_unitNumberRange;
	}

	private static final String[] RESTRICTIONS = {
			"lender.id = #{lenderList.lender.id}",

			"lower(lender.brand) like concat(lower(#{lenderList.lender.brand}),'%')",

			"lower(lender.name) like concat(lower(#{lenderList.lender.name}),'%')",

			"lower(lender.companyType) like concat(lower(#{lenderList.lender.companyType}),'%')",

			"lower(lender.address.streetDirection) like concat(lower(#{lenderList.lender.address.streetDirection}),'%')",

			"lender.address.streetNumber >= #{lenderList.address_streetNumberRange.begin}",
			"lender.address.streetNumber <= #{lenderList.address_streetNumberRange.end}",

			"lower(lender.address.streetName) like concat(lower(#{lenderList.lender.address.streetName}),'%')",

			"lower(lender.address.province) like concat(lower(#{lenderList.lender.address.province}),'%')",

			"lower(lender.address.streetType) like concat(lower(#{lenderList.lender.address.streetType}),'%')",

			"lower(lender.address.postalCode) like concat(lower(#{lenderList.lender.address.postalCode}),'%')",

			"lender.address.unitNumber >= #{lenderList.address_unitNumberRange.begin}",
			"lender.address.unitNumber <= #{lenderList.address_unitNumberRange.end}",

			"lower(lender.address.city) like concat(lower(#{lenderList.lender.address.city}),'%')",

			"lender.dateCreated <= #{lenderList.dateCreatedRange.end}",
			"lender.dateCreated >= #{lenderList.dateCreatedRange.begin}",};

	public Lender getLender() {
		return lender;
	}

	@Override
	public Class<Lender> getEntityClass() {
		return Lender.class;
	}

	@Override
	public String[] getEntityRestrictions() {
		// TODO Auto-generated method stub
		return RESTRICTIONS;
	}

	@Observer("archivedLender")
	public void onArchive() {
		refresh();
	}
}
