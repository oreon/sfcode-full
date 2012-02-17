package com.pcas.datapkg.web.action.inventory;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.jboss.seam.security.Identity;
import org.testng.annotations.Test;
import org.witchcraft.base.entity.*;
import org.hibernate.annotations.Filter;

import org.testng.annotations.BeforeClass;
import org.witchcraft.seam.action.BaseAction;
import com.pcas.datapkg.domain.inventory.Location;

public class LocationActionTestBase
		extends
			org.witchcraft.action.test.BaseTest<Location> {

	LocationAction locationAction = new LocationAction();

	@BeforeClass
	public void init() {
		super.init();
	}

	@Override
	public BaseAction<Location> getAction() {
		return locationAction;
	}

}
