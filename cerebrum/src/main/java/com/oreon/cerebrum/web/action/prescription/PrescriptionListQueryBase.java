package com.oreon.cerebrum.web.action.prescription;

import com.oreon.cerebrum.prescription.Prescription;

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

import com.oreon.cerebrum.prescription.Prescription;

/**
 * 
 * @author WitchcraftMDA Seam Cartridge - 
 *
 */
public abstract class PrescriptionListQueryBase
		extends
			BaseQuery<Prescription, Long> {

	private static final String EJBQL = "select prescription from Prescription prescription";

	protected Prescription prescription = new Prescription();

	public Prescription getPrescription() {
		return prescription;
	}

	@Override
	protected String getql() {
		return EJBQL;
	}

	@Override
	public Class<Prescription> getEntityClass() {
		return Prescription.class;
	}

	@Override
	public String[] getEntityRestrictions() {
		return RESTRICTIONS;
	}

	private static final String[] RESTRICTIONS = {
			"prescription.id = #{prescriptionList.prescription.id}",

			"prescription.patient.id = #{prescriptionList.prescription.patient.id}",

			"lower(prescription.directivesForPatient) like concat(lower(#{prescriptionList.prescription.directivesForPatient}),'%')",

			"prescription.active = #{prescriptionList.prescription.active}",

			"prescription.dateCreated <= #{prescriptionList.dateCreatedRange.end}",
			"prescription.dateCreated >= #{prescriptionList.dateCreatedRange.begin}",};

	public List<Prescription> getPrescriptionsByPatient(
			com.oreon.cerebrum.patient.Patient patient) {
		//setMaxResults(10000);
		prescription.setPatient(patient);
		return getResultList();
	}

	@Observer("archivedPrescription")
	public void onArchive() {
		refresh();
	}

	/** create comma delimited row 
	 * @param builder
	 */
	//@Override
	public void createCsvString(StringBuilder builder, Prescription e) {

		builder.append("\""
				+ (e.getPatient() != null ? e.getPatient().getDisplayName()
						.replace(",", "") : "") + "\",");

		builder.append("\""
				+ (e.getDirectivesForPatient() != null ? e
						.getDirectivesForPatient().replace(",", "") : "")
				+ "\",");

		builder.append("\"" + (e.getActive() != null ? e.getActive() : "")
				+ "\",");

		builder.append("\r\n");
	}

	/** create the headings 
	 * @param builder
	 */
	//@Override
	public void createCSvTitles(StringBuilder builder) {

		builder.append("Patient" + ",");

		builder.append("DirectivesForPatient" + ",");

		builder.append("Active" + ",");

		builder.append("\r\n");
	}
}
