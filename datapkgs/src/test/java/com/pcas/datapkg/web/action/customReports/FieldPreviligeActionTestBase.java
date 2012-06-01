package com.pcas.datapkg.web.action.customReports;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.jboss.seam.security.Identity;
import org.testng.annotations.Test;
import org.witchcraft.base.entity.*;
import org.hibernate.annotations.Filter;

import org.testng.annotations.BeforeClass;
import org.witchcraft.seam.action.BaseAction;
import com.pcas.datapkg.customReports.FieldPrevilige;

public class FieldPreviligeActionTestBase
		extends
			org.witchcraft.action.test.BaseTest<FieldPrevilige> {

	FieldPreviligeAction fieldPreviligeAction = new FieldPreviligeAction();

	@BeforeClass
	public void init() {
		super.init();
	}

	@Override
	public BaseAction<FieldPrevilige> getAction() {
		return fieldPreviligeAction;
	}

}
