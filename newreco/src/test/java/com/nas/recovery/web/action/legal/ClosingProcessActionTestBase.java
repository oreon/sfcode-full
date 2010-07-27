package com.nas.recovery.web.action.legal;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.jboss.seam.security.Identity;
import org.testng.annotations.Test;
import org.witchcraft.base.entity.*;
import org.hibernate.annotations.Filter;

import org.testng.annotations.BeforeClass;
import org.witchcraft.seam.action.BaseAction;
import com.nas.recovery.domain.legal.ClosingProcess;

public class ClosingProcessActionTestBase
		extends
			org.witchcraft.action.test.BaseTest<ClosingProcess> {

	ClosingProcessAction closingProcessAction = new ClosingProcessAction();

	@BeforeClass
	public void init() {
		super.init();
	}

	@Override
	public BaseAction<ClosingProcess> getAction() {
		return closingProcessAction;
	}

}
