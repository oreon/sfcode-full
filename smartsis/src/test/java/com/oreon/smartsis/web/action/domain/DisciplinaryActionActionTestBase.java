package com.oreon.smartsis.web.action.domain;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.jboss.seam.security.Identity;
import org.testng.annotations.Test;
import org.witchcraft.base.entity.*;
import org.hibernate.annotations.Filter;

import org.testng.annotations.BeforeClass;
import org.witchcraft.seam.action.BaseAction;
import com.oreon.smartsis.domain.DisciplinaryAction;

public class DisciplinaryActionActionTestBase
		extends
			org.witchcraft.action.test.BaseTest<DisciplinaryAction> {

	DisciplinaryActionAction disciplinaryActionAction = new DisciplinaryActionAction();

	@BeforeClass
	public void init() {
		super.init();
	}

	@Override
	public BaseAction<DisciplinaryAction> getAction() {
		return disciplinaryActionAction;
	}

}
