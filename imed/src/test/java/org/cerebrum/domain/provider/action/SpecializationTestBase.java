package org.cerebrum.domain.provider.action;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.jboss.seam.security.Identity;
import org.testng.annotations.Test;
import org.witchcraft.base.entity.*;
import org.hibernate.annotations.Filter;

import org.testng.annotations.BeforeClass;
import org.witchcraft.seam.action.BaseAction;
import org.cerebrum.domain.provider.Specialization;

public class SpecializationTestBase
		extends
			org.witchcraft.action.test.BaseTest<Specialization> {

	SpecializationAction specializationAction = new SpecializationAction();

	@BeforeClass
	public void init() {
		super.init();
	}

	@Override
	public BaseAction<Specialization> getAction() {
		return specializationAction;
	}

}
