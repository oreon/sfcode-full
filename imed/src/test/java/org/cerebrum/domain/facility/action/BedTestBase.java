package org.cerebrum.domain.facility.action;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.jboss.seam.security.Identity;
import org.testng.annotations.Test;
import org.witchcraft.base.entity.*;
import org.hibernate.annotations.Filter;

import org.testng.annotations.BeforeClass;
import org.witchcraft.seam.action.BaseAction;
import org.cerebrum.domain.facility.Bed;

public class BedTestBase extends org.witchcraft.action.test.BaseTest<Bed> {

	BedAction bedAction = new BedAction();

	@BeforeClass
	public void init() {
		super.init();
	}

	@Override
	public BaseAction<Bed> getAction() {
		return bedAction;
	}

}
