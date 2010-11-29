package com.nas.recovery.web.action.patient;

import com.oreon.callosum.patient.Prescription;

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

import com.oreon.callosum.patient.Prescription;

/**
 * @author WitchcraftMDA Seam Cartridge
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

			"lower(prescription.notes) like concat(lower(#{prescriptionList.prescription.notes}),'%')",

			"prescription.dateCreated <= #{prescriptionList.dateCreatedRange.end}",
			"prescription.dateCreated >= #{prescriptionList.dateCreatedRange.begin}",};

	@Observer("archivedPrescription")
	public void onArchive() {
		refresh();
	}

}
