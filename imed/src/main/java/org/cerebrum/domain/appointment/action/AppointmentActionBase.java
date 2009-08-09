package org.cerebrum.domain.appointment.action;

import org.cerebrum.domain.appointment.Appointment;
import java.util.ArrayList;
import java.util.List;

import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;
import javax.persistence.EntityManager;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import org.apache.commons.lang.StringUtils;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Scope;

import org.jboss.seam.Component;
import org.jboss.seam.annotations.Begin;
import org.jboss.seam.annotations.End;
import org.jboss.seam.annotations.Factory;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Logger;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Out;

import org.jboss.seam.annotations.datamodel.DataModel;
import org.jboss.seam.annotations.datamodel.DataModelSelection;
import org.jboss.seam.faces.FacesMessages;
import org.jboss.seam.log.Log;

import org.witchcraft.seam.action.BaseAction;
import org.jboss.seam.annotations.Observer;

public class AppointmentActionBase extends BaseAction<Appointment>
		implements
			java.io.Serializable {

	@In(create = true)
	@Out(required = false)
	@DataModelSelection
	private Appointment appointment;

	@DataModel
	private List<Appointment> appointmentList;

	@Factory("appointmentList")
	@Observer("archivedAppointment")
	public void findRecords() {
		search();
	}

	public Appointment getEntity() {
		return appointment;
	}

	@Override
	public void setEntity(Appointment t) {
		this.appointment = t;
	}

	@Override
	public void setEntityList(List<Appointment> list) {
		this.appointmentList = list;
	}

	/** This function adds associated entities to an example criterion
	 * @see org.witchcraft.model.support.dao.BaseAction#createExampleCriteria(java.lang.Object)
	 */
	public void addAssoications(Criteria criteria) {

		if (appointment.getPatient() != null) {
			criteria = criteria.add(Restrictions.eq("patient.id", appointment
					.getPatient().getId()));
		}

		if (appointment.getPhysician() != null) {
			criteria = criteria.add(Restrictions.eq("physician.id", appointment
					.getPhysician().getId()));
		}

	}

	public void updateAssociations() {

	}

	public List<Appointment> getEntityList() {
		if (appointmentList == null) {
			findRecords();
		}
		return appointmentList;
	}

	public Boolean cancelAppointment() {

		return null;

	}

	public List appointmentsByPhysician(
			org.cerebrum.domain.provider.Physician physician) {
		return executeNamedQuery("queryFindAppointmentsForPhysician", physician);
	}

	public org.cerebrum.domain.appointment.Appointment findAvailableAppointment(
			org.cerebrum.domain.provider.Physician physician) {

		return null;

	}

}
