package com.oreon.smartsis.web.action.domain;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.jboss.seam.security.Identity;
import org.testng.annotations.Test;
import org.witchcraft.base.entity.*;
import org.hibernate.annotations.Filter;

import org.testng.annotations.BeforeClass;
import org.witchcraft.seam.action.BaseAction;
import com.oreon.smartsis.domain.Student;

public class StudentActionTestBase
		extends
			org.witchcraft.action.test.BaseTest<Student> {

	StudentAction studentAction = new StudentAction();

	@BeforeClass
	public void init() {
		super.init();
	}

	@Override
	public BaseAction<Student> getAction() {
		return studentAction;
	}

	@Test
	public void testEExamsForStudent() throws Exception {
		new ComponentTest() {

			protected void testComponents() throws Exception {
				StudentAction studentAction = (StudentAction) org.jboss.seam.Component
						.getInstance("studentAction");

				// assert(studentAction.eExamsForStudent()).equals("");
			}

		}.run();
	}

}
