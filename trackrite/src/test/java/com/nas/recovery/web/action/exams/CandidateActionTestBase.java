package com.nas.recovery.web.action.exams;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.jboss.seam.security.Identity;
import org.testng.annotations.Test;
import org.witchcraft.base.entity.*;
import org.hibernate.annotations.Filter;

import org.testng.annotations.BeforeClass;
import org.witchcraft.seam.action.BaseAction;
import org.wc.trackrite.exams.Candidate;

public class CandidateActionTestBase
		extends
			org.witchcraft.action.test.BaseTest<Candidate> {

	CandidateAction candidateAction = new CandidateAction();

	@BeforeClass
	public void init() {
		super.init();
	}

	@Override
	public BaseAction<Candidate> getAction() {
		return candidateAction;
	}

}
