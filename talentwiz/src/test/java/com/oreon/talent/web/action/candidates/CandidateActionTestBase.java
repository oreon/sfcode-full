package com.oreon.talent.web.action.candidates;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.jboss.seam.security.Identity;
import org.testng.annotations.Test;
import org.witchcraft.base.entity.*;
import org.hibernate.annotations.Filter;

import org.testng.annotations.BeforeClass;
import org.witchcraft.seam.action.BaseAction;
import com.oreon.talent.candidates.Candidate;

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

	@Test
	public void testRegister() throws Exception {
		new ComponentTest() {

			protected void testComponents() throws Exception {
				CandidateAction candidateAction = (CandidateAction) org.jboss.seam.Component
						.getInstance("candidateAction");

				// assert(candidateAction.register()).equals("");
			}

		}.run();
	}

	@Test
	public void testLogin() throws Exception {
		new ComponentTest() {

			protected void testComponents() throws Exception {
				CandidateAction candidateAction = (CandidateAction) org.jboss.seam.Component
						.getInstance("candidateAction");

				// assert(candidateAction.login()).equals("");
			}

		}.run();
	}

	@Test
	public void testRetrieveCredentials() throws Exception {
		new ComponentTest() {

			protected void testComponents() throws Exception {
				CandidateAction candidateAction = (CandidateAction) org.jboss.seam.Component
						.getInstance("candidateAction");

				// assert(candidateAction.retrieveCredentials()).equals("");
			}

		}.run();
	}

}
