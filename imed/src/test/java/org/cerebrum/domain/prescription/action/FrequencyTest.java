package org.cerebrum.domain.prescription.action;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.jboss.seam.security.Identity;
import org.testng.annotations.Test;
import org.witchcraft.base.entity.*;
import org.hibernate.annotations.Filter;

import org.testng.annotations.BeforeClass;
import org.witchcraft.seam.action.BaseAction;
import org.cerebrum.domain.prescription.Frequency;

public class FrequencyTest
		extends
			org.witchcraft.action.test.BaseTest<Frequency> {

	FrequencyAction frequencyAction = new FrequencyAction();

	@BeforeClass
	public void init() {
		super.init();
	}

	@Override
	public BaseAction<Frequency> getAction() {
		return frequencyAction;
	}
}
