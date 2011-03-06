package com.oreon.smartsis.web.action.domain;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.jboss.seam.security.Identity;
import org.testng.annotations.Test;
import org.witchcraft.base.entity.*;
import org.hibernate.annotations.Filter;

import org.testng.annotations.BeforeClass;
import org.witchcraft.seam.action.BaseAction;
import com.oreon.smartsis.domain.ExamInstance;

public class ExamInstanceActionTestBase
		extends
			org.witchcraft.action.test.BaseTest<ExamInstance> {

	ExamInstanceAction examInstanceAction = new ExamInstanceAction();

	@BeforeClass
	public void init() {
		super.init();
	}

	@Override
	public BaseAction<ExamInstance> getAction() {
		return examInstanceAction;
	}

}
