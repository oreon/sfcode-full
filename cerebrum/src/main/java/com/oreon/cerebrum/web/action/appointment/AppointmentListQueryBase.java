package com.oreon.cerebrum.web.action.appointment;

import com.oreon.cerebrum.appointment.Appointment;

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

import com.oreon.cerebrum.appointment.Appointment;

/**
 * 
 * @author WitchcraftMDA Seam Cartridge - 
 *
 */
public abstract class AppointmentListQueryBase
		extends
			BaseQuery<Appointment, Long> {

	private static final String EJBQL = "select appointment from Appointment appointment";

	protected Appointment appointment = new Appointment();

	public Appointment getAppointment() {
		return appointment;
	}

	@Override
	public Appointment getInstance() {
		return getAppointment();
	}

	@Override
	protected String getql() {
		return EJBQL;
	}

	@Override
	//@Restrict("#{s:hasPermission('appointment', 'view')}")
	public List<Appointment> getResultList() {
		return super.getResultList();
	}

	@Override
	public Class<Appointment> getEntityClass() {
		return Appointment.class;
	}

	@Override
	public String[] getEntityRestrictions() {
		return RESTRICTIONS;
	}

	private Range<Date> startRange = new Range<Date>();

	public Range<Date> getStartRange() {
		return startRange;
	}
	public void setStart(Range<Date> startRange) {
		this.startRange = startRange;
	}

	private Range<Date> endRange = new Range<Date>();

	public Range<Date> getEndRange() {
		return endRange;
	}
	public void setEnd(Range<Date> endRange) {
		this.endRange = endRange;
	}

	private Range<Integer> unitsRange = new Range<Integer>();

	public Range<Integer> getUnitsRange() {
		return unitsRange;
	}
	public void setUnits(Range<Integer> unitsRange) {
		this.unitsRange = unitsRange;
	}

	private static final String[] RESTRICTIONS = {
			"appointment.id = #{appointmentList.appointment.id}",

			"appointment.archived = #{appointmentList.appointment.archived}",

			"appointment.start >= #{appointmentList.startRange.begin}",
			"appointment.start <= #{appointmentList.startRange.end}",

			"appointment.end >= #{appointmentList.endRange.begin}",
			"appointment.end <= #{appointmentList.endRange.end}",

			"appointment.physician.id = #{appointmentList.appointment.physician.id}",

			"appointment.patient.id = #{appointmentList.appointment.patient.id}",

			"lower(appointment.remarks) like concat(lower(#{appointmentList.appointment.remarks}),'%')",

			"appointment.units >= #{appointmentList.unitsRange.begin}",
			"appointment.units <= #{appointmentList.unitsRange.end}",

			"appointment.dateCreated <= #{appointmentList.dateCreatedRange.end}",
			"appointment.dateCreated >= #{appointmentList.dateCreatedRange.begin}",};

	@Observer("archivedAppointment")
	public void onArchive() {
		refresh();
	}

	public void setPhysicianId(Long id) {
		if (appointment.getPhysician() == null) {
			appointment
					.setPhysician(new com.oreon.cerebrum.employee.Physician());
		}
		appointment.getPhysician().setId(id);
	}

	public Long getPhysicianId() {
		return appointment.getPhysician() == null ? null : appointment
				.getPhysician().getId();
	}

	public void setPatientId(Long id) {
		if (appointment.getPatient() == null) {
			appointment.setPatient(new com.oreon.cerebrum.patient.Patient());
		}
		appointment.getPatient().setId(id);
	}

	public Long getPatientId() {
		return appointment.getPatient() == null ? null : appointment
				.getPatient().getId();
	}

	/** create comma delimited row 
	 * @param builder
	 */
	//@Override
	public void createCsvString(StringBuilder builder, Appointment e) {

		builder.append("\"" + (e.getStart() != null ? e.getStart() : "")
				+ "\",");

		builder.append("\"" + (e.getEnd() != null ? e.getEnd() : "") + "\",");

		builder.append("\""
				+ (e.getPhysician() != null ? e.getPhysician().getDisplayName()
						.replace(",", "") : "") + "\",");

		builder.append("\""
				+ (e.getPatient() != null ? e.getPatient().getDisplayName()
						.replace(",", "") : "") + "\",");

		builder.append("\""
				+ (e.getRemarks() != null
						? e.getRemarks().replace(",", "")
						: "") + "\",");

		builder.append("\"" + (e.getUnits() != null ? e.getUnits() : "")
				+ "\",");

		builder.append("\r\n");
	}

	/** create the headings 
	 * @param builder
	 */
	//@Override
	public void createCSvTitles(StringBuilder builder) {

		builder.append("Start" + ",");

		builder.append("End" + ",");

		builder.append("Physician" + ",");

		builder.append("Patient" + ",");

		builder.append("Remarks" + ",");

		builder.append("Units" + ",");

		builder.append("\r\n");
	}
}
