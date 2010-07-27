package com.nas.recovery.web.action.propertymanagement;

import com.nas.recovery.domain.propertymanagement.PropertyManager;

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

import com.nas.recovery.domain.propertymanagement.PropertyManager;

/**
 * @author WitchcraftMDA Seam Cartridge
 *
 */
public abstract class PropertyManagerListQueryBase
		extends
			BaseQuery<PropertyManager, Long> {

	//private static final String EJBQL = "select propertyManager from PropertyManager propertyManager";

	private PropertyManager propertyManager = new PropertyManager();

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
			"propertyManager.id = #{propertyManagerList.propertyManager.id}",

			"lower(propertyManager.lastName) like concat(lower(#{propertyManagerList.propertyManager.lastName}),'%')",

			"lower(propertyManager.firstName) like concat(lower(#{propertyManagerList.propertyManager.firstName}),'%')",

			"lower(propertyManager.address.streetDirection) like concat(lower(#{propertyManagerList.propertyManager.address.streetDirection}),'%')",

			"propertyManager.address.streetNumber >= #{propertyManagerList.address_streetNumberRange.begin}",
			"propertyManager.address.streetNumber <= #{propertyManagerList.address_streetNumberRange.end}",

			"lower(propertyManager.address.streetName) like concat(lower(#{propertyManagerList.propertyManager.address.streetName}),'%')",

			"lower(propertyManager.address.province) like concat(lower(#{propertyManagerList.propertyManager.address.province}),'%')",

			"lower(propertyManager.address.streetType) like concat(lower(#{propertyManagerList.propertyManager.address.streetType}),'%')",

			"lower(propertyManager.address.postalCode) like concat(lower(#{propertyManagerList.propertyManager.address.postalCode}),'%')",

			"propertyManager.address.unitNumber >= #{propertyManagerList.address_unitNumberRange.begin}",
			"propertyManager.address.unitNumber <= #{propertyManagerList.address_unitNumberRange.end}",

			"lower(propertyManager.address.city) like concat(lower(#{propertyManagerList.propertyManager.address.city}),'%')",

			"lower(propertyManager.contactDetails.primaryPhone) like concat(lower(#{propertyManagerList.propertyManager.contactDetails.primaryPhone}),'%')",

			"lower(propertyManager.contactDetails.secondaryPhone) like concat(lower(#{propertyManagerList.propertyManager.contactDetails.secondaryPhone}),'%')",

			"lower(propertyManager.user.userName) like concat(lower(#{propertyManagerList.propertyManager.user.userName}),'%')",

			"propertyManager.user.enabled = #{propertyManagerList.propertyManager.user.enabled}",

			"lower(propertyManager.user.email) like concat(lower(#{propertyManagerList.propertyManager.user.email}),'%')",

			"propertyManager.dateCreated <= #{propertyManagerList.dateCreatedRange.end}",
			"propertyManager.dateCreated >= #{propertyManagerList.dateCreatedRange.begin}",};

	public PropertyManager getPropertyManager() {
		return propertyManager;
	}

	@Override
	public Class<PropertyManager> getEntityClass() {
		return PropertyManager.class;
	}

	@Override
	public String[] getEntityRestrictions() {
		// TODO Auto-generated method stub
		return RESTRICTIONS;
	}

	@Observer("archivedPropertyManager")
	public void onArchive() {
		refresh();
	}
}
