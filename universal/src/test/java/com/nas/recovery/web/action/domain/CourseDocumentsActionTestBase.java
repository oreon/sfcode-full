package com.nas.recovery.web.action.domain;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.jboss.seam.security.Identity;
import org.testng.annotations.Test;
import org.witchcraft.base.entity.*;
import org.hibernate.annotations.Filter;

import org.testng.annotations.BeforeClass;
import org.witchcraft.seam.action.BaseAction;
import com.oreon.tapovan.domain.CourseDocuments;

public class CourseDocumentsActionTestBase
		extends
			org.witchcraft.action.test.BaseTest<CourseDocuments> {

	CourseDocumentsAction courseDocumentsAction = new CourseDocumentsAction();

	@BeforeClass
	public void init() {
		super.init();
	}

	@Override
	public BaseAction<CourseDocuments> getAction() {
		return courseDocumentsAction;
	}

}
