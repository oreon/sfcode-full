package org.cerebrum.domain.drug.action;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.jboss.seam.security.Identity;
import org.testng.annotations.Test;
import org.witchcraft.base.entity.*;
import org.hibernate.annotations.Filter;

import org.testng.annotations.BeforeClass;
import org.witchcraft.seam.action.BaseAction;
import org.cerebrum.domain.drug.Drug;

public class DrugTestBase extends org.witchcraft.action.test.BaseTest<Drug> {

	DrugAction drugAction = new DrugAction();

	@BeforeClass
	public void init() {
		super.init();
	}

	@Override
	public BaseAction<Drug> getAction() {
		return drugAction;
	}

}
