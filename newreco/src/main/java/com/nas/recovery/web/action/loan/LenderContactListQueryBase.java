package com.nas.recovery.web.action.loan;

import com.nas.recovery.domain.loan.LenderContact;

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

import com.nas.recovery.domain.loan.LenderContact;

/**
 * @author WitchcraftMDA Seam Cartridge
 *
 */
public abstract class LenderContactListQueryBase
		extends
			BaseQuery<LenderContact, Long> {

	//private static final String EJBQL = "select lenderContact from LenderContact lenderContact";

	private LenderContact lenderContact = new LenderContact();

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
			"lenderContact.id = #{lenderContactList.lenderContact.id}",

			"lenderContact.lender.id = #{lenderContactList.lenderContact.lender.id}",

			"lower(lenderContact.lastName) like concat(lower(#{lenderContactList.lenderContact.lastName}),'%')",

			"lower(lenderContact.firstName) like concat(lower(#{lenderContactList.lenderContact.firstName}),'%')",

			"lower(lenderContact.address.streetDirection) like concat(lower(#{lenderContactList.lenderContact.address.streetDirection}),'%')",

			"lenderContact.address.streetNumber >= #{lenderContactList.address_streetNumberRange.begin}",
			"lenderContact.address.streetNumber <= #{lenderContactList.address_streetNumberRange.end}",

			"lower(lenderContact.address.streetName) like concat(lower(#{lenderContactList.lenderContact.address.streetName}),'%')",

			"lower(lenderContact.address.province) like concat(lower(#{lenderContactList.lenderContact.address.province}),'%')",

			"lower(lenderContact.address.streetType) like concat(lower(#{lenderContactList.lenderContact.address.streetType}),'%')",

			"lower(lenderContact.address.postalCode) like concat(lower(#{lenderContactList.lenderContact.address.postalCode}),'%')",

			"lenderContact.address.unitNumber >= #{lenderContactList.address_unitNumberRange.begin}",
			"lenderContact.address.unitNumber <= #{lenderContactList.address_unitNumberRange.end}",

			"lower(lenderContact.address.city) like concat(lower(#{lenderContactList.lenderContact.address.city}),'%')",

			"lower(lenderContact.contactDetails.primaryPhone) like concat(lower(#{lenderContactList.lenderContact.contactDetails.primaryPhone}),'%')",

			"lower(lenderContact.contactDetails.secondaryPhone) like concat(lower(#{lenderContactList.lenderContact.contactDetails.secondaryPhone}),'%')",

			"lower(lenderContact.user.userName) like concat(lower(#{lenderContactList.lenderContact.user.userName}),'%')",

			"lenderContact.user.enabled = #{lenderContactList.lenderContact.user.enabled}",

			"lower(lenderContact.user.email) like concat(lower(#{lenderContactList.lenderContact.user.email}),'%')",

			"lenderContact.dateCreated <= #{lenderContactList.dateCreatedRange.end}",
			"lenderContact.dateCreated >= #{lenderContactList.dateCreatedRange.begin}",};

	public LenderContact getLenderContact() {
		return lenderContact;
	}

	@Override
	public Class<LenderContact> getEntityClass() {
		return LenderContact.class;
	}

	@Override
	public String[] getEntityRestrictions() {
		// TODO Auto-generated method stub
		return RESTRICTIONS;
	}

	@Observer("archivedLenderContact")
	public void onArchive() {
		refresh();
	}
}
