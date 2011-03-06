package com.oreon.smartsis.web.action.exam;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.jboss.seam.security.Identity;
import org.testng.annotations.Test;
import org.witchcraft.base.entity.*;
import org.hibernate.annotations.Filter;

import org.testng.annotations.BeforeClass;
import org.witchcraft.seam.action.BaseAction;
import com.oreon.smartsis.exam.ElectronicExam;

public class ElectronicExamActionTestBase
		extends
			org.witchcraft.action.test.BaseTest<ElectronicExam> {

	ElectronicExamAction electronicExamAction = new ElectronicExamAction();

	@BeforeClass
	public void init() {
		super.init();
	}

	@Override
	public BaseAction<ElectronicExam> getAction() {
		return electronicExamAction;
	}

}
