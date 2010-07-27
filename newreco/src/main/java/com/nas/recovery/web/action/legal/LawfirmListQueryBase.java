package com.nas.recovery.web.action.legal;

import com.nas.recovery.domain.legal.Lawfirm;

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

import com.nas.recovery.domain.legal.Lawfirm;

/**
 * @author WitchcraftMDA Seam Cartridge
 *
 */
public abstract class LawfirmListQueryBase extends BaseQuery<Lawfirm, Long> {

	//private static final String EJBQL = "select lawfirm from Lawfirm lawfirm";

	private Lawfirm lawfirm = new Lawfirm();

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
			"lawfirm.id = #{lawfirmList.lawfirm.id}",

			"lower(lawfirm.brand) like concat(lower(#{lawfirmList.lawfirm.brand}),'%')",

			"lower(lawfirm.name) like concat(lower(#{lawfirmList.lawfirm.name}),'%')",

			"lower(lawfirm.companyType) like concat(lower(#{lawfirmList.lawfirm.companyType}),'%')",

			"lower(lawfirm.address.streetDirection) like concat(lower(#{lawfirmList.lawfirm.address.streetDirection}),'%')",

			"lawfirm.address.streetNumber >= #{lawfirmList.address_streetNumberRange.begin}",
			"lawfirm.address.streetNumber <= #{lawfirmList.address_streetNumberRange.end}",

			"lower(lawfirm.address.streetName) like concat(lower(#{lawfirmList.lawfirm.address.streetName}),'%')",

			"lower(lawfirm.address.province) like concat(lower(#{lawfirmList.lawfirm.address.province}),'%')",

			"lower(lawfirm.address.streetType) like concat(lower(#{lawfirmList.lawfirm.address.streetType}),'%')",

			"lower(lawfirm.address.postalCode) like concat(lower(#{lawfirmList.lawfirm.address.postalCode}),'%')",

			"lawfirm.address.unitNumber >= #{lawfirmList.address_unitNumberRange.begin}",
			"lawfirm.address.unitNumber <= #{lawfirmList.address_unitNumberRange.end}",

			"lower(lawfirm.address.city) like concat(lower(#{lawfirmList.lawfirm.address.city}),'%')",

			"lawfirm.dateCreated <= #{lawfirmList.dateCreatedRange.end}",
			"lawfirm.dateCreated >= #{lawfirmList.dateCreatedRange.begin}",};

	public Lawfirm getLawfirm() {
		return lawfirm;
	}

	@Override
	public Class<Lawfirm> getEntityClass() {
		return Lawfirm.class;
	}

	@Override
	public String[] getEntityRestrictions() {
		// TODO Auto-generated method stub
		return RESTRICTIONS;
	}

	@Observer("archivedLawfirm")
	public void onArchive() {
		refresh();
	}
}
