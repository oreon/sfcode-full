package com.pcas.datapkg.web.action.domain;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.jboss.seam.security.Identity;
import org.testng.annotations.Test;
import org.witchcraft.base.entity.*;
import org.hibernate.annotations.Filter;

import org.testng.annotations.BeforeClass;
import org.witchcraft.seam.action.BaseAction;
import com.pcas.datapkg.domain.DataPackage;

public class DataPackageActionTestBase
		extends
			org.witchcraft.action.test.BaseTest<DataPackage> {

	DataPackageAction dataPackageAction = new DataPackageAction();

	@BeforeClass
	public void init() {
		super.init();
	}

	@Override
	public BaseAction<DataPackage> getAction() {
		return dataPackageAction;
	}

}
