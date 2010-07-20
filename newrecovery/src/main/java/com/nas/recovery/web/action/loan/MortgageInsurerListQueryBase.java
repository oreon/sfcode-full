package com.nas.recovery.web.action.loan;

import com.nas.recovery.domain.loan.MortgageInsurer;

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

import com.nas.recovery.domain.loan.MortgageInsurer;

/**
 * @author WitchcraftMDA Seam Cartridge
 *
 */
public abstract class MortgageInsurerListQueryBase
		extends
			BaseQuery<MortgageInsurer, Long> {

	//private static final String EJBQL = "select mortgageInsurer from MortgageInsurer mortgageInsurer";

	private MortgageInsurer mortgageInsurer = new MortgageInsurer();

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
			"mortgageInsurer.id = #{mortgageInsurerList.mortgageInsurer.id}",

			"lower(mortgageInsurer.policyNumber) like concat(lower(#{mortgageInsurerList.mortgageInsurer.policyNumber}),'%')",

			"lower(mortgageInsurer.brand) like concat(lower(#{mortgageInsurerList.mortgageInsurer.brand}),'%')",

			"lower(mortgageInsurer.name) like concat(lower(#{mortgageInsurerList.mortgageInsurer.name}),'%')",

			"lower(mortgageInsurer.companyType) like concat(lower(#{mortgageInsurerList.mortgageInsurer.companyType}),'%')",

			"lower(mortgageInsurer.address.streetDirection) like concat(lower(#{mortgageInsurerList.mortgageInsurer.address.streetDirection}),'%')",

			"mortgageInsurer.address.streetNumber >= #{mortgageInsurerList.address_streetNumberRange.begin}",
			"mortgageInsurer.address.streetNumber <= #{mortgageInsurerList.address_streetNumberRange.end}",

			"lower(mortgageInsurer.address.streetName) like concat(lower(#{mortgageInsurerList.mortgageInsurer.address.streetName}),'%')",

			"lower(mortgageInsurer.address.province) like concat(lower(#{mortgageInsurerList.mortgageInsurer.address.province}),'%')",

			"lower(mortgageInsurer.address.streetType) like concat(lower(#{mortgageInsurerList.mortgageInsurer.address.streetType}),'%')",

			"lower(mortgageInsurer.address.postalCode) like concat(lower(#{mortgageInsurerList.mortgageInsurer.address.postalCode}),'%')",

			"mortgageInsurer.address.unitNumber >= #{mortgageInsurerList.address_unitNumberRange.begin}",
			"mortgageInsurer.address.unitNumber <= #{mortgageInsurerList.address_unitNumberRange.end}",

			"lower(mortgageInsurer.address.city) like concat(lower(#{mortgageInsurerList.mortgageInsurer.address.city}),'%')",

			"mortgageInsurer.dateCreated <= #{mortgageInsurerList.dateCreatedRange.end}",
			"mortgageInsurer.dateCreated >= #{mortgageInsurerList.dateCreatedRange.begin}",};

	public MortgageInsurer getMortgageInsurer() {
		return mortgageInsurer;
	}

	@Override
	public Class<MortgageInsurer> getEntityClass() {
		return MortgageInsurer.class;
	}

	@Override
	public String[] getEntityRestrictions() {
		// TODO Auto-generated method stub
		return RESTRICTIONS;
	}

	@Observer("archivedMortgageInsurer")
	public void onArchive() {
		refresh();
	}
}
