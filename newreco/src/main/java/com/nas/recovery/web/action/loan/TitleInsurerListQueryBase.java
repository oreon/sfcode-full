package com.nas.recovery.web.action.loan;

import com.nas.recovery.domain.loan.TitleInsurer;

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

import com.nas.recovery.domain.loan.TitleInsurer;

/**
 * @author WitchcraftMDA Seam Cartridge
 *
 */
public abstract class TitleInsurerListQueryBase
		extends
			BaseQuery<TitleInsurer, Long> {

	//private static final String EJBQL = "select titleInsurer from TitleInsurer titleInsurer";

	private TitleInsurer titleInsurer = new TitleInsurer();

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
			"titleInsurer.id = #{titleInsurerList.titleInsurer.id}",

			"lower(titleInsurer.brand) like concat(lower(#{titleInsurerList.titleInsurer.brand}),'%')",

			"lower(titleInsurer.name) like concat(lower(#{titleInsurerList.titleInsurer.name}),'%')",

			"lower(titleInsurer.companyType) like concat(lower(#{titleInsurerList.titleInsurer.companyType}),'%')",

			"lower(titleInsurer.address.streetDirection) like concat(lower(#{titleInsurerList.titleInsurer.address.streetDirection}),'%')",

			"titleInsurer.address.streetNumber >= #{titleInsurerList.address_streetNumberRange.begin}",
			"titleInsurer.address.streetNumber <= #{titleInsurerList.address_streetNumberRange.end}",

			"lower(titleInsurer.address.streetName) like concat(lower(#{titleInsurerList.titleInsurer.address.streetName}),'%')",

			"lower(titleInsurer.address.province) like concat(lower(#{titleInsurerList.titleInsurer.address.province}),'%')",

			"lower(titleInsurer.address.streetType) like concat(lower(#{titleInsurerList.titleInsurer.address.streetType}),'%')",

			"lower(titleInsurer.address.postalCode) like concat(lower(#{titleInsurerList.titleInsurer.address.postalCode}),'%')",

			"titleInsurer.address.unitNumber >= #{titleInsurerList.address_unitNumberRange.begin}",
			"titleInsurer.address.unitNumber <= #{titleInsurerList.address_unitNumberRange.end}",

			"lower(titleInsurer.address.city) like concat(lower(#{titleInsurerList.titleInsurer.address.city}),'%')",

			"titleInsurer.dateCreated <= #{titleInsurerList.dateCreatedRange.end}",
			"titleInsurer.dateCreated >= #{titleInsurerList.dateCreatedRange.begin}",};

	public TitleInsurer getTitleInsurer() {
		return titleInsurer;
	}

	@Override
	public Class<TitleInsurer> getEntityClass() {
		return TitleInsurer.class;
	}

	@Override
	public String[] getEntityRestrictions() {
		// TODO Auto-generated method stub
		return RESTRICTIONS;
	}

	@Observer("archivedTitleInsurer")
	public void onArchive() {
		refresh();
	}
}
