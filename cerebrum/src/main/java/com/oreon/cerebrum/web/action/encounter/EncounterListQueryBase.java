package com.oreon.cerebrum.web.action.encounter;

import com.oreon.cerebrum.encounter.Encounter;

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

import org.jboss.seam.annotations.security.Restrict;

import com.oreon.cerebrum.encounter.Encounter;

/**
 * 
 * @author WitchcraftMDA Seam Cartridge - 
 *
 */
public abstract class EncounterListQueryBase extends BaseQuery<Encounter, Long> {

	private static final String EJBQL = "select encounter from Encounter encounter";

	protected Encounter encounter = new Encounter();

	public Encounter getEncounter() {
		return encounter;
	}

	@Override
	public Encounter getInstance() {
		return getEncounter();
	}

	@Override
	protected String getql() {
		return EJBQL;
	}

	@Override
	//@Restrict("#{s:hasPermission('encounter', 'view')}")
	public List<Encounter> getResultList() {
		return super.getResultList();
	}

	@Override
	public Class<Encounter> getEntityClass() {
		return Encounter.class;
	}

	@Override
	public String[] getEntityRestrictions() {
		return RESTRICTIONS;
	}

	private Range<Integer> vitals_SysBPRange = new Range<Integer>();

	public Range<Integer> getVitals_SysBPRange() {
		return vitals_SysBPRange;
	}
	public void setVitals_SysBP(Range<Integer> vitals_SysBPRange) {
		this.vitals_SysBPRange = vitals_SysBPRange;
	}

	private Range<Integer> vitals_DiasBPRange = new Range<Integer>();

	public Range<Integer> getVitals_DiasBPRange() {
		return vitals_DiasBPRange;
	}
	public void setVitals_DiasBP(Range<Integer> vitals_DiasBPRange) {
		this.vitals_DiasBPRange = vitals_DiasBPRange;
	}

	private Range<Double> vitals_TemperatureRange = new Range<Double>();

	public Range<Double> getVitals_TemperatureRange() {
		return vitals_TemperatureRange;
	}
	public void setVitals_Temperature(Range<Double> vitals_TemperatureRange) {
		this.vitals_TemperatureRange = vitals_TemperatureRange;
	}

	private Range<Integer> vitals_PulseRange = new Range<Integer>();

	public Range<Integer> getVitals_PulseRange() {
		return vitals_PulseRange;
	}
	public void setVitals_Pulse(Range<Integer> vitals_PulseRange) {
		this.vitals_PulseRange = vitals_PulseRange;
	}

	private Range<Integer> vitals_RespirationRateRange = new Range<Integer>();

	public Range<Integer> getVitals_RespirationRateRange() {
		return vitals_RespirationRateRange;
	}
	public void setVitals_RespirationRate(
			Range<Integer> vitals_RespirationRateRange) {
		this.vitals_RespirationRateRange = vitals_RespirationRateRange;
	}

	private static final String[] RESTRICTIONS = {
			"encounter.id = #{encounterList.encounter.id}",

			"encounter.archived = #{encounterList.encounter.archived}",

			"encounter.physician.id = #{encounterList.encounter.physician.id}",

			"lower(encounter.chiefComplaint) like concat(lower(#{encounterList.encounter.chiefComplaint}),'%')",

			"lower(encounter.progressNotes) like concat(lower(#{encounterList.encounter.progressNotes}),'%')",

			"encounter.vitals.sysBP >= #{encounterList.vitals_SysBPRange.begin}",
			"encounter.vitals.sysBP <= #{encounterList.vitals_SysBPRange.end}",

			"encounter.vitals.diasBP >= #{encounterList.vitals_DiasBPRange.begin}",
			"encounter.vitals.diasBP <= #{encounterList.vitals_DiasBPRange.end}",

			"encounter.vitals.temperature >= #{encounterList.vitals_TemperatureRange.begin}",
			"encounter.vitals.temperature <= #{encounterList.vitals_TemperatureRange.end}",

			"encounter.vitals.pulse >= #{encounterList.vitals_PulseRange.begin}",
			"encounter.vitals.pulse <= #{encounterList.vitals_PulseRange.end}",

			"encounter.vitals.respirationRate >= #{encounterList.vitals_RespirationRateRange.begin}",
			"encounter.vitals.respirationRate <= #{encounterList.vitals_RespirationRateRange.end}",

			"encounter.prescription.id = #{encounterList.encounter.prescription.id}",

			"lower(encounter.physicalExamFindings) like concat(lower(#{encounterList.encounter.physicalExamFindings}),'%')",

			"encounter.patient.id = #{encounterList.encounter.patient.id}",

			"encounter.dateCreated <= #{encounterList.dateCreatedRange.end}",
			"encounter.dateCreated >= #{encounterList.dateCreatedRange.begin}",};

	public List<Encounter> getEncountersByPatient(
			com.oreon.cerebrum.patient.Patient patient) {
		//setMaxResults(10000);
		encounter.setPatient(patient);
		return getResultList();
	}

	@Observer("archivedEncounter")
	public void onArchive() {
		refresh();
	}

	/** create comma delimited row 
	 * @param builder
	 */
	//@Override
	public void createCsvString(StringBuilder builder, Encounter e) {

		builder.append("\""
				+ (e.getPhysician() != null ? e.getPhysician().getDisplayName()
						.replace(",", "") : "") + "\",");

		builder.append("\""
				+ (e.getChiefComplaint() != null ? e.getChiefComplaint()
						.replace(",", "") : "") + "\",");

		builder.append("\""
				+ (e.getProgressNotes() != null ? e.getProgressNotes().replace(
						",", "") : "") + "\",");

		builder.append("\"" + (e.getVitals() != null ? e.getVitals() : "")
				+ "\",");

		builder.append("\""
				+ (e.getPrescription() != null ? e.getPrescription()
						.getDisplayName().replace(",", "") : "") + "\",");

		builder.append("\""
				+ (e.getPhysicalExamFindings() != null ? e
						.getPhysicalExamFindings().replace(",", "") : "")
				+ "\",");

		builder.append("\""
				+ (e.getPatient() != null ? e.getPatient().getDisplayName()
						.replace(",", "") : "") + "\",");

		builder.append("\r\n");
	}

	/** create the headings 
	 * @param builder
	 */
	//@Override
	public void createCSvTitles(StringBuilder builder) {

		builder.append("Physician" + ",");

		builder.append("ChiefComplaint" + ",");

		builder.append("ProgressNotes" + ",");

		builder.append("Vitals" + ",");

		builder.append("Prescription" + ",");

		builder.append("PhysicalExamFindings" + ",");

		builder.append("Patient" + ",");

		builder.append("\r\n");
	}
}
