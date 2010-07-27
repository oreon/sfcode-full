package com.nas.recovery.web.action.legal;

import com.nas.recovery.domain.legal.Lawyer;

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

import com.nas.recovery.domain.legal.Lawyer;

/**
 * @author WitchcraftMDA Seam Cartridge
 *
 */
public abstract class LawyerListQueryBase extends BaseQuery<Lawyer, Long> {

	//private static final String EJBQL = "select lawyer from Lawyer lawyer";

	private Lawyer lawyer = new Lawyer();

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
			"lawyer.id = #{lawyerList.lawyer.id}",

			"lawyer.lawfirm = #{lawyerList.lawyer.lawfirm}",

			"lower(lawyer.lastName) like concat(lower(#{lawyerList.lawyer.lastName}),'%')",

			"lower(lawyer.firstName) like concat(lower(#{lawyerList.lawyer.firstName}),'%')",

			"lower(lawyer.address.streetDirection) like concat(lower(#{lawyerList.lawyer.address.streetDirection}),'%')",

			"lawyer.address.streetNumber >= #{lawyerList.address_streetNumberRange.begin}",
			"lawyer.address.streetNumber <= #{lawyerList.address_streetNumberRange.end}",

			"lower(lawyer.address.streetName) like concat(lower(#{lawyerList.lawyer.address.streetName}),'%')",

			"lower(lawyer.address.province) like concat(lower(#{lawyerList.lawyer.address.province}),'%')",

			"lower(lawyer.address.streetType) like concat(lower(#{lawyerList.lawyer.address.streetType}),'%')",

			"lower(lawyer.address.postalCode) like concat(lower(#{lawyerList.lawyer.address.postalCode}),'%')",

			"lawyer.address.unitNumber >= #{lawyerList.address_unitNumberRange.begin}",
			"lawyer.address.unitNumber <= #{lawyerList.address_unitNumberRange.end}",

			"lower(lawyer.address.city) like concat(lower(#{lawyerList.lawyer.address.city}),'%')",

			"lower(lawyer.contactDetails.primaryPhone) like concat(lower(#{lawyerList.lawyer.contactDetails.primaryPhone}),'%')",

			"lower(lawyer.contactDetails.secondaryPhone) like concat(lower(#{lawyerList.lawyer.contactDetails.secondaryPhone}),'%')",

			"lower(lawyer.user.userName) like concat(lower(#{lawyerList.lawyer.user.userName}),'%')",

			"lawyer.user.enabled = #{lawyerList.lawyer.user.enabled}",

			"lower(lawyer.user.email) like concat(lower(#{lawyerList.lawyer.user.email}),'%')",

			"lawyer.dateCreated <= #{lawyerList.dateCreatedRange.end}",
			"lawyer.dateCreated >= #{lawyerList.dateCreatedRange.begin}",};

	public Lawyer getLawyer() {
		return lawyer;
	}

	@Override
	public Class<Lawyer> getEntityClass() {
		return Lawyer.class;
	}

	@Override
	public String[] getEntityRestrictions() {
		// TODO Auto-generated method stub
		return RESTRICTIONS;
	}

	@Observer("archivedLawyer")
	public void onArchive() {
		refresh();
	}
}
