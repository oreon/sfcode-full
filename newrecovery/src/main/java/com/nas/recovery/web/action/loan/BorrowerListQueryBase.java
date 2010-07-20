package com.nas.recovery.web.action.loan;

import com.nas.recovery.domain.loan.Borrower;

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

import com.nas.recovery.domain.loan.Borrower;

/**
 * @author WitchcraftMDA Seam Cartridge
 *
 */
public abstract class BorrowerListQueryBase extends BaseQuery<Borrower, Long> {

	//private static final String EJBQL = "select borrower from Borrower borrower";

	private Borrower borrower = new Borrower();

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
			"borrower.id = #{borrowerList.borrower.id}",

			"lower(borrower.lastName) like concat(lower(#{borrowerList.borrower.lastName}),'%')",

			"lower(borrower.firstName) like concat(lower(#{borrowerList.borrower.firstName}),'%')",

			"lower(borrower.address.streetDirection) like concat(lower(#{borrowerList.borrower.address.streetDirection}),'%')",

			"borrower.address.streetNumber >= #{borrowerList.address_streetNumberRange.begin}",
			"borrower.address.streetNumber <= #{borrowerList.address_streetNumberRange.end}",

			"lower(borrower.address.streetName) like concat(lower(#{borrowerList.borrower.address.streetName}),'%')",

			"lower(borrower.address.province) like concat(lower(#{borrowerList.borrower.address.province}),'%')",

			"lower(borrower.address.streetType) like concat(lower(#{borrowerList.borrower.address.streetType}),'%')",

			"lower(borrower.address.postalCode) like concat(lower(#{borrowerList.borrower.address.postalCode}),'%')",

			"borrower.address.unitNumber >= #{borrowerList.address_unitNumberRange.begin}",
			"borrower.address.unitNumber <= #{borrowerList.address_unitNumberRange.end}",

			"lower(borrower.address.city) like concat(lower(#{borrowerList.borrower.address.city}),'%')",

			"lower(borrower.contactDetails.primaryPhone) like concat(lower(#{borrowerList.borrower.contactDetails.primaryPhone}),'%')",

			"lower(borrower.contactDetails.secondaryPhone) like concat(lower(#{borrowerList.borrower.contactDetails.secondaryPhone}),'%')",

			"lower(borrower.user.userName) like concat(lower(#{borrowerList.borrower.user.userName}),'%')",

			"borrower.user.enabled = #{borrowerList.borrower.user.enabled}",

			"lower(borrower.user.email) like concat(lower(#{borrowerList.borrower.user.email}),'%')",

			"borrower.dateCreated <= #{borrowerList.dateCreatedRange.end}",
			"borrower.dateCreated >= #{borrowerList.dateCreatedRange.begin}",};

	public Borrower getBorrower() {
		return borrower;
	}

	@Override
	public Class<Borrower> getEntityClass() {
		return Borrower.class;
	}

	@Override
	public String[] getEntityRestrictions() {
		// TODO Auto-generated method stub
		return RESTRICTIONS;
	}

	@Observer("archivedBorrower")
	public void onArchive() {
		refresh();
	}
}
