package com.oreon.smartsis.web.action.domain;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.jboss.seam.security.Identity;
import org.testng.annotations.Test;
import org.witchcraft.base.entity.*;
import org.hibernate.annotations.Filter;

import org.testng.annotations.BeforeClass;
import org.witchcraft.seam.action.BaseAction;
import com.oreon.smartsis.domain.Parent;

public class ParentActionTestBase
		extends
			org.witchcraft.action.test.BaseTest<Parent> {

	ParentAction parentAction = new ParentAction();

	@BeforeClass
	public void init() {
		super.init();
	}

	@Override
	public BaseAction<Parent> getAction() {
		return parentAction;
	}

}
