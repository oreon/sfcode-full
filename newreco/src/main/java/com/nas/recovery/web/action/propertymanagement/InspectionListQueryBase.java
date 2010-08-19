package com.nas.recovery.web.action.propertymanagement;

import com.nas.recovery.domain.propertymanagement.Inspection;

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

import com.nas.recovery.domain.propertymanagement.Inspection;

/**
 * @author WitchcraftMDA Seam Cartridge
 *
 */
public abstract class InspectionListQueryBase
		extends
			BaseQuery<Inspection, Long> {

	//private static final String EJBQL = "select inspection from Inspection inspection";

	private Inspection inspection = new Inspection();

	private Range<Date> inspection_dateRange = new Range<Date>();
	public Range<Date> getInspection_dateRange() {
		return inspection_dateRange;
	}
	public void setInspection_date(Range<Date> inspection_dateRange) {
		this.inspection_dateRange = inspection_dateRange;
	}

	private static final String[] RESTRICTIONS = {
			"inspection.id = #{inspectionList.inspection.id}",

			"inspection.type = #{inspectionList.inspection.type}",

			"inspection.date >= #{inspectionList.inspection_dateRange.begin}",
			"inspection.date <= #{inspectionList.inspection_dateRange.end}",

			"lower(inspection.observation) like concat(lower(#{inspectionList.inspection.observation}),'%')",

			"inspection.propertyManager.id = #{inspectionList.inspection.propertyManager.id}",

			"inspection.actionRequired = #{inspectionList.inspection.actionRequired}",

			"inspection.dateCreated <= #{inspectionList.dateCreatedRange.end}",
			"inspection.dateCreated >= #{inspectionList.dateCreatedRange.begin}",};

	public Inspection getInspection() {
		return inspection;
	}

	@Override
	public Class<Inspection> getEntityClass() {
		return Inspection.class;
	}

	@Override
	public String[] getEntityRestrictions() {
		// TODO Auto-generated method stub
		return RESTRICTIONS;
	}

	@Observer("archivedInspection")
	public void onArchive() {
		refresh();
	}
}
