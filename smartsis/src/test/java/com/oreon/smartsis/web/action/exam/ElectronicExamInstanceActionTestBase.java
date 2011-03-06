package com.oreon.smartsis.web.action.exam;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.jboss.seam.security.Identity;
import org.testng.annotations.Test;
import org.witchcraft.base.entity.*;
import org.hibernate.annotations.Filter;

import org.testng.annotations.BeforeClass;
import org.witchcraft.seam.action.BaseAction;
import com.oreon.smartsis.exam.ElectronicExamInstance;

public class ElectronicExamInstanceActionTestBase
		extends
			org.witchcraft.action.test.BaseTest<ElectronicExamInstance> {

	ElectronicExamInstanceAction electronicExamInstanceAction = new ElectronicExamInstanceAction();

	@BeforeClass
	public void init() {
		super.init();
	}

	@Override
	public BaseAction<ElectronicExamInstance> getAction() {
		return electronicExamInstanceAction;
	}

	@Test
	public void testCalculateScore() throws Exception {
		new ComponentTest() {

			protected void testComponents() throws Exception {
				ElectronicExamInstanceAction electronicExamInstanceAction = (ElectronicExamInstanceAction) org.jboss.seam.Component
						.getInstance("electronicExamInstanceAction");

				// assert(electronicExamInstanceAction.calculateScore(examInstance)).equals("");
			}

		}.run();
	}

}
