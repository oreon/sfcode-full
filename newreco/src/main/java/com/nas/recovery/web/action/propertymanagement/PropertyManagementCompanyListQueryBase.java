package com.nas.recovery.web.action.propertymanagement;

import com.nas.recovery.domain.propertymanagement.PropertyManagementCompany;

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

import com.nas.recovery.domain.propertymanagement.PropertyManagementCompany;

/**
 * @author WitchcraftMDA Seam Cartridge
 *
 */
public abstract class PropertyManagementCompanyListQueryBase
		extends
			BaseQuery<PropertyManagementCompany, Long> {

	//private static final String EJBQL = "select propertyManagementCompany from PropertyManagementCompany propertyManagementCompany";

	private PropertyManagementCompany propertyManagementCompany = new PropertyManagementCompany();

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
			"propertyManagementCompany.id = #{propertyManagementCompanyList.propertyManagementCompany.id}",

			"lower(propertyManagementCompany.brand) like concat(lower(#{propertyManagementCompanyList.propertyManagementCompany.brand}),'%')",

			"lower(propertyManagementCompany.name) like concat(lower(#{propertyManagementCompanyList.propertyManagementCompany.name}),'%')",

			"lower(propertyManagementCompany.companyType) like concat(lower(#{propertyManagementCompanyList.propertyManagementCompany.companyType}),'%')",

			"lower(propertyManagementCompany.address.streetDirection) like concat(lower(#{propertyManagementCompanyList.propertyManagementCompany.address.streetDirection}),'%')",

			"propertyManagementCompany.address.streetNumber >= #{propertyManagementCompanyList.address_streetNumberRange.begin}",
			"propertyManagementCompany.address.streetNumber <= #{propertyManagementCompanyList.address_streetNumberRange.end}",

			"lower(propertyManagementCompany.address.streetName) like concat(lower(#{propertyManagementCompanyList.propertyManagementCompany.address.streetName}),'%')",

			"lower(propertyManagementCompany.address.province) like concat(lower(#{propertyManagementCompanyList.propertyManagementCompany.address.province}),'%')",

			"lower(propertyManagementCompany.address.streetType) like concat(lower(#{propertyManagementCompanyList.propertyManagementCompany.address.streetType}),'%')",

			"lower(propertyManagementCompany.address.postalCode) like concat(lower(#{propertyManagementCompanyList.propertyManagementCompany.address.postalCode}),'%')",

			"propertyManagementCompany.address.unitNumber >= #{propertyManagementCompanyList.address_unitNumberRange.begin}",
			"propertyManagementCompany.address.unitNumber <= #{propertyManagementCompanyList.address_unitNumberRange.end}",

			"lower(propertyManagementCompany.address.city) like concat(lower(#{propertyManagementCompanyList.propertyManagementCompany.address.city}),'%')",

			"propertyManagementCompany.dateCreated <= #{propertyManagementCompanyList.dateCreatedRange.end}",
			"propertyManagementCompany.dateCreated >= #{propertyManagementCompanyList.dateCreatedRange.begin}",};

	public PropertyManagementCompany getPropertyManagementCompany() {
		return propertyManagementCompany;
	}

	@Override
	public Class<PropertyManagementCompany> getEntityClass() {
		return PropertyManagementCompany.class;
	}

	@Override
	public String[] getEntityRestrictions() {
		// TODO Auto-generated method stub
		return RESTRICTIONS;
	}

	@Observer("archivedPropertyManagementCompany")
	public void onArchive() {
		refresh();
	}
}
