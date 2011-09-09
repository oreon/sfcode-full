package com.oreon.cerebrum.web.action.patient;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.jboss.seam.security.Identity;
import org.testng.annotations.Test;
import org.witchcraft.base.entity.*;
import org.hibernate.annotations.Filter;

import org.testng.annotations.BeforeClass;
import org.witchcraft.seam.action.BaseAction;
import com.oreon.cerebrum.patient.Admission;

public class AdmissionActionTestBase
		extends
			org.witchcraft.action.test.BaseTest<Admission> {

	AdmissionAction admissionAction = new AdmissionAction();

	@BeforeClass
	public void init() {
		super.init();
	}

	@Override
	public BaseAction<Admission> getAction() {
		return admissionAction;
	}

}
