package com.nas.recovery.web.action.realestate;

import com.nas.recovery.domain.realestate.RealEstateProperty;

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

import com.nas.recovery.domain.realestate.RealEstateProperty;

/**
 * @author WitchcraftMDA Seam Cartridge
 *
 */
public abstract class RealEstatePropertyListQueryBase
		extends
			BaseQuery<RealEstateProperty, Long> {

	//private static final String EJBQL = "select realEstateProperty from RealEstateProperty realEstateProperty";

	private RealEstateProperty realEstateProperty = new RealEstateProperty();

	private static final String[] RESTRICTIONS = {
			"realEstateProperty.id = #{realEstatePropertyList.realEstateProperty.id}",

			"lower(realEstateProperty.streetAddress) like concat(lower(#{realEstatePropertyList.realEstateProperty.streetAddress}),'%')",

			"realEstateProperty.state = #{realEstatePropertyList.realEstateProperty.state}",

			"lower(realEstateProperty.zip) like concat(lower(#{realEstatePropertyList.realEstateProperty.zip}),'%')",

			"lower(realEstateProperty.city) like concat(lower(#{realEstatePropertyList.realEstateProperty.city}),'%')",

			"realEstateProperty.insurer = #{realEstatePropertyList.realEstateProperty.insurer}",

			"realEstateProperty.dateCreated <= #{realEstatePropertyList.dateCreatedRange.end}",
			"realEstateProperty.dateCreated >= #{realEstatePropertyList.dateCreatedRange.begin}",};

	public RealEstateProperty getRealEstateProperty() {
		return realEstateProperty;
	}

	@Override
	public Class<RealEstateProperty> getEntityClass() {
		return RealEstateProperty.class;
	}

	@Override
	public String[] getEntityRestrictions() {
		// TODO Auto-generated method stub
		return RESTRICTIONS;
	}

	@Observer("archivedRealEstateProperty")
	public void onArchive() {
		refresh();
	}
}
