package com.nas.recovery.web.action.exams;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.jboss.seam.security.Identity;
import org.testng.annotations.Test;
import org.witchcraft.base.entity.*;
import org.hibernate.annotations.Filter;

import org.testng.annotations.BeforeClass;
import org.witchcraft.seam.action.BaseAction;
import org.wc.trackrite.exams.Exam;

public class ExamActionTestBase
		extends
			org.witchcraft.action.test.BaseTest<Exam> {

	ExamAction examAction = new ExamAction();

	@BeforeClass
	public void init() {
		super.init();
	}

	@Override
	public BaseAction<Exam> getAction() {
		return examAction;
	}

}
