package com.nas.recovery.web.action.realestate;

import com.nas.recovery.domain.realestate.RealEstateFirm;

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

import com.nas.recovery.domain.realestate.RealEstateFirm;

/**
 * @author WitchcraftMDA Seam Cartridge
 *
 */
public abstract class RealEstateFirmListQueryBase
		extends
			BaseQuery<RealEstateFirm, Long> {

	//private static final String EJBQL = "select realEstateFirm from RealEstateFirm realEstateFirm";

	private RealEstateFirm realEstateFirm = new RealEstateFirm();

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
			"realEstateFirm.id = #{realEstateFirmList.realEstateFirm.id}",

			"lower(realEstateFirm.brand) like concat(lower(#{realEstateFirmList.realEstateFirm.brand}),'%')",

			"lower(realEstateFirm.name) like concat(lower(#{realEstateFirmList.realEstateFirm.name}),'%')",

			"lower(realEstateFirm.companyType) like concat(lower(#{realEstateFirmList.realEstateFirm.companyType}),'%')",

			"lower(realEstateFirm.address.streetDirection) like concat(lower(#{realEstateFirmList.realEstateFirm.address.streetDirection}),'%')",

			"realEstateFirm.address.streetNumber >= #{realEstateFirmList.address_streetNumberRange.begin}",
			"realEstateFirm.address.streetNumber <= #{realEstateFirmList.address_streetNumberRange.end}",

			"lower(realEstateFirm.address.streetName) like concat(lower(#{realEstateFirmList.realEstateFirm.address.streetName}),'%')",

			"lower(realEstateFirm.address.province) like concat(lower(#{realEstateFirmList.realEstateFirm.address.province}),'%')",

			"lower(realEstateFirm.address.streetType) like concat(lower(#{realEstateFirmList.realEstateFirm.address.streetType}),'%')",

			"lower(realEstateFirm.address.postalCode) like concat(lower(#{realEstateFirmList.realEstateFirm.address.postalCode}),'%')",

			"realEstateFirm.address.unitNumber >= #{realEstateFirmList.address_unitNumberRange.begin}",
			"realEstateFirm.address.unitNumber <= #{realEstateFirmList.address_unitNumberRange.end}",

			"lower(realEstateFirm.address.city) like concat(lower(#{realEstateFirmList.realEstateFirm.address.city}),'%')",

			"realEstateFirm.dateCreated <= #{realEstateFirmList.dateCreatedRange.end}",
			"realEstateFirm.dateCreated >= #{realEstateFirmList.dateCreatedRange.begin}",};

	public RealEstateFirm getRealEstateFirm() {
		return realEstateFirm;
	}

	@Override
	public Class<RealEstateFirm> getEntityClass() {
		return RealEstateFirm.class;
	}

	@Override
	public String[] getEntityRestrictions() {
		// TODO Auto-generated method stub
		return RESTRICTIONS;
	}

	@Observer("archivedRealEstateFirm")
	public void onArchive() {
		refresh();
	}
}
