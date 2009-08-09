package org.cerebrum.domain.prescription.action;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.jboss.seam.security.Identity;
import org.testng.annotations.Test;
import org.witchcraft.base.entity.*;
import org.hibernate.annotations.Filter;

import org.testng.annotations.BeforeClass;
import org.witchcraft.seam.action.BaseAction;
import org.cerebrum.domain.prescription.Item;

public class ItemTestBase extends org.witchcraft.action.test.BaseTest<Item> {

	ItemAction itemAction = new ItemAction();

	@BeforeClass
	public void init() {
		super.init();
	}

	@Override
	public BaseAction<Item> getAction() {
		return itemAction;
	}

}
