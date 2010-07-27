package com.nas.recovery.web.action.loan;

import com.nas.recovery.domain.loan.Underwriter;

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

import com.nas.recovery.domain.loan.Underwriter;

/**
 * @author WitchcraftMDA Seam Cartridge
 *
 */
public abstract class UnderwriterListQueryBase
		extends
			BaseQuery<Underwriter, Long> {

	//private static final String EJBQL = "select underwriter from Underwriter underwriter";

	private Underwriter underwriter = new Underwriter();

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
			"underwriter.id = #{underwriterList.underwriter.id}",

			"lower(underwriter.lastName) like concat(lower(#{underwriterList.underwriter.lastName}),'%')",

			"lower(underwriter.firstName) like concat(lower(#{underwriterList.underwriter.firstName}),'%')",

			"lower(underwriter.address.streetDirection) like concat(lower(#{underwriterList.underwriter.address.streetDirection}),'%')",

			"underwriter.address.streetNumber >= #{underwriterList.address_streetNumberRange.begin}",
			"underwriter.address.streetNumber <= #{underwriterList.address_streetNumberRange.end}",

			"lower(underwriter.address.streetName) like concat(lower(#{underwriterList.underwriter.address.streetName}),'%')",

			"lower(underwriter.address.province) like concat(lower(#{underwriterList.underwriter.address.province}),'%')",

			"lower(underwriter.address.streetType) like concat(lower(#{underwriterList.underwriter.address.streetType}),'%')",

			"lower(underwriter.address.postalCode) like concat(lower(#{underwriterList.underwriter.address.postalCode}),'%')",

			"underwriter.address.unitNumber >= #{underwriterList.address_unitNumberRange.begin}",
			"underwriter.address.unitNumber <= #{underwriterList.address_unitNumberRange.end}",

			"lower(underwriter.address.city) like concat(lower(#{underwriterList.underwriter.address.city}),'%')",

			"lower(underwriter.contactDetails.primaryPhone) like concat(lower(#{underwriterList.underwriter.contactDetails.primaryPhone}),'%')",

			"lower(underwriter.contactDetails.secondaryPhone) like concat(lower(#{underwriterList.underwriter.contactDetails.secondaryPhone}),'%')",

			"lower(underwriter.user.userName) like concat(lower(#{underwriterList.underwriter.user.userName}),'%')",

			"underwriter.user.enabled = #{underwriterList.underwriter.user.enabled}",

			"lower(underwriter.user.email) like concat(lower(#{underwriterList.underwriter.user.email}),'%')",

			"underwriter.dateCreated <= #{underwriterList.dateCreatedRange.end}",
			"underwriter.dateCreated >= #{underwriterList.dateCreatedRange.begin}",};

	public Underwriter getUnderwriter() {
		return underwriter;
	}

	@Override
	public Class<Underwriter> getEntityClass() {
		return Underwriter.class;
	}

	@Override
	public String[] getEntityRestrictions() {
		// TODO Auto-generated method stub
		return RESTRICTIONS;
	}

	@Observer("archivedUnderwriter")
	public void onArchive() {
		refresh();
	}
}
