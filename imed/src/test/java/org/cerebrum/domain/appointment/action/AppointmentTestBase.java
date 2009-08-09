package org.cerebrum.domain.appointment.action;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.jboss.seam.security.Identity;
import org.testng.annotations.Test;
import org.witchcraft.base.entity.*;
import org.hibernate.annotations.Filter;

import org.testng.annotations.BeforeClass;
import org.witchcraft.seam.action.BaseAction;
import org.cerebrum.domain.appointment.Appointment;

public class AppointmentTestBase
		extends
			org.witchcraft.action.test.BaseTest<Appointment> {

	AppointmentAction appointmentAction = new AppointmentAction();

	@BeforeClass
	public void init() {
		super.init();
	}

	@Override
	public BaseAction<Appointment> getAction() {
		return appointmentAction;
	}

	@Test
	public void testcancelAppointment() {
		//appointmentAction.cancelAppointment();
	}

	@Test
	public void testappointmentsByPhysician() {
		//appointmentAction.appointmentsByPhysician(physician);
	}

	@Test
	public void testfindAvailableAppointment() {
		//appointmentAction.findAvailableAppointment(physician);
	}

}
