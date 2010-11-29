package com.nas.recovery.web.action.patient;

import com.oreon.callosum.patient.Immunization;

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

import com.oreon.callosum.patient.Immunization;

/**
 * @author WitchcraftMDA Seam Cartridge
 *
 */
public abstract class ImmunizationListQueryBase
		extends
			BaseQuery<Immunization, Long> {

	private static final String EJBQL = "select immunization from Immunization immunization";

	protected Immunization immunization = new Immunization();

	public Immunization getImmunization() {
		return immunization;
	}

	@Override
	protected String getql() {
		return EJBQL;
	}

	@Override
	public Class<Immunization> getEntityClass() {
		return Immunization.class;
	}

	@Override
	public String[] getEntityRestrictions() {
		return RESTRICTIONS;
	}

	private Range<Date> dateRange = new Range<Date>();
	public Range<Date> getDateRange() {
		return dateRange;
	}
	public void setDate(Range<Date> dateRange) {
		this.dateRange = dateRange;
	}

	private static final String[] RESTRICTIONS = {
			"immunization.id = #{immunizationList.immunization.id}",

			"immunization.date >= #{immunizationList.dateRange.begin}",
			"immunization.date <= #{immunizationList.dateRange.end}",

			"immunization.patient.id = #{immunizationList.immunization.patient.id}",

			"immunization.vaccine.id = #{immunizationList.immunization.vaccine.id}",

			"immunization.dateCreated <= #{immunizationList.dateCreatedRange.end}",
			"immunization.dateCreated >= #{immunizationList.dateCreatedRange.begin}",};

	@Observer("archivedImmunization")
	public void onArchive() {
		refresh();
	}

}
