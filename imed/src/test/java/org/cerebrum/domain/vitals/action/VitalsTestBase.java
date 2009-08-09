package org.cerebrum.domain.vitals.action;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.jboss.seam.security.Identity;
import org.testng.annotations.Test;
import org.witchcraft.base.entity.*;
import org.hibernate.annotations.Filter;

import org.testng.annotations.BeforeClass;
import org.witchcraft.seam.action.BaseAction;
import org.cerebrum.domain.vitals.Vitals;

public class VitalsTestBase extends org.witchcraft.action.test.BaseTest<Vitals> {

	VitalsAction vitalsAction = new VitalsAction();

	@BeforeClass
	public void init() {
		super.init();
	}

	@Override
	public BaseAction<Vitals> getAction() {
		return vitalsAction;
	}

}
