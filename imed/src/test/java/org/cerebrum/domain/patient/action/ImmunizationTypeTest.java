package org.cerebrum.domain.patient.action;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.jboss.seam.security.Identity;
import org.testng.annotations.Test;
import org.witchcraft.base.entity.*;
import org.hibernate.annotations.Filter;

import org.testng.annotations.BeforeClass;
import org.witchcraft.seam.action.BaseAction;
import org.cerebrum.domain.patient.ImmunizationType;

public class ImmunizationTypeTest
		extends
			org.witchcraft.action.test.BaseTest<ImmunizationType> {

	ImmunizationTypeAction immunizationTypeAction = new ImmunizationTypeAction();

	@BeforeClass
	public void init() {
		super.init();
	}

	@Override
	public BaseAction<ImmunizationType> getAction() {
		return immunizationTypeAction;
	}
}
