package com.nas.recovery.web.action.patient;

import com.oreon.callosum.patient.Admission;

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

import com.oreon.callosum.patient.Admission;

/**
 * D
 * @author WitchcraftMDA Seam Cartridge
 *
 */
public abstract class AdmissionListQueryBase extends BaseQuery<Admission, Long> {

	private static final String EJBQL = "select admission from Admission admission";

	protected Admission admission = new Admission();

	public Admission getAdmission() {
		return admission;
	}

	@Override
	protected String getql() {
		return EJBQL;
	}

	@Override
	public Class<Admission> getEntityClass() {
		return Admission.class;
	}

	@Override
	public String[] getEntityRestrictions() {
		return RESTRICTIONS;
	}

	private Range<Date> dischargeDateRange = new Range<Date>();
	public Range<Date> getDischargeDateRange() {
		return dischargeDateRange;
	}
	public void setDischargeDate(Range<Date> dischargeDateRange) {
		this.dischargeDateRange = dischargeDateRange;
	}

	private static final String[] RESTRICTIONS = {
			"admission.id = #{admissionList.admission.id}",

			"admission.patient.id = #{admissionList.admission.patient.id}",

			"lower(admission.notes) like concat(lower(#{admissionList.admission.notes}),'%')",

			"admission.dischargeDate >= #{admissionList.dischargeDateRange.begin}",
			"admission.dischargeDate <= #{admissionList.dischargeDateRange.end}",

			"admission.bed.id = #{admissionList.admission.bed.id}",

			"admission.dateCreated <= #{admissionList.dateCreatedRange.end}",
			"admission.dateCreated >= #{admissionList.dateCreatedRange.begin}",};

	public List<Admission> getAdmissionsByPatient(
			com.oreon.callosum.patient.Patient patient) {
		//setMaxResults(10000);
		admission.setPatient(patient);
		return getResultList();
	}

	public List<Admission> getAdmissionByBed(com.oreon.callosum.facility.Bed bed) {
		//setMaxResults(10000);
		admission.setBed(bed);
		return getResultList();
	}

	@Observer("archivedAdmission")
	public void onArchive() {
		refresh();
	}

}
