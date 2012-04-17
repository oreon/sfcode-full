package com.oreon.cerebrum.web.action.patient;

import com.oreon.cerebrum.patient.Immunization;

import org.witchcraft.seam.action.BaseAction;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;
import org.jboss.seam.framework.EntityQuery;
import org.witchcraft.base.entity.BaseQuery;
import org.witchcraft.base.entity.Range;

import org.jboss.seam.annotations.Observer;

import java.math.BigDecimal;

import com.oreon.cerebrum.patient.Immunization;

/**
 * 
 * @author WitchcraftMDA Seam Cartridge - 
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

	public List<Immunization> getImmunizationsByPatient(
			com.oreon.cerebrum.patient.Patient patient) {
		//setMaxResults(10000);
		immunization.setPatient(patient);
		return getResultList();
	}

	@Observer("archivedImmunization")
	public void onArchive() {
		refresh();
	}

	/** create comma delimited row 
	 * @param builder
	 */
	//@Override
	public void createCsvString(StringBuilder builder, Immunization e) {

		builder.append("\"" + (e.getDate() != null ? e.getDate() : "") + "\",");

		builder.append("\""
				+ (e.getPatient() != null ? e.getPatient().getDisplayName()
						.replace(",", "") : "") + "\",");

		builder.append("\""
				+ (e.getVaccine() != null ? e.getVaccine().getDisplayName()
						.replace(",", "") : "") + "\",");

		builder.append("\r\n");
	}

	/** create the headings 
	 * @param builder
	 */
	//@Override
	public void createCSvTitles(StringBuilder builder) {

		builder.append("Date" + ",");

		builder.append("Patient" + ",");

		builder.append("Vaccine" + ",");

		builder.append("\r\n");
	}
}
