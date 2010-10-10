package org.wc.trackrite.onepack.testdata;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Random;
import java.util.ArrayList;
import org.jboss.seam.Component;
import org.witchcraft.seam.action.AbstractTestDataFactory; //import org.witchcraft.model.support.testing.AbstractTestDataFactory;
//import org.witchcraft.model.support.testing.TestDataFactory;
//import org.witchcraft.model.support.errorhandling.BusinessException;
//import org.witchcraft.model.randomgen.RandomValueGeneratorFactory;

import org.apache.log4j.Logger;

public class CandidateTestDataFactory
		extends
			AbstractTestDataFactory<org.wc.trackrite.onepack.Candidate> {

	private List<org.wc.trackrite.onepack.Candidate> candidates = new ArrayList<org.wc.trackrite.onepack.Candidate>();

	private static final Logger logger = Logger
			.getLogger(CandidateTestDataFactory.class);

	private static int RECORDS_TO_CREATE = 30;

	private static SimpleDateFormat dateFormat = new SimpleDateFormat(
			"yyyy.MM.dd HH:mm:ss z");

	com.nas.recovery.web.action.onepack.CandidateAction candidateAction;

	public void register(org.wc.trackrite.onepack.Candidate candidate) {
		candidates.add(candidate);
	}

	public org.wc.trackrite.onepack.Candidate createCandidateOne() {
		org.wc.trackrite.onepack.Candidate candidate = new org.wc.trackrite.onepack.Candidate();

		try {

			register(candidate);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return candidate;
	}

	public org.wc.trackrite.onepack.Candidate createCandidateTwo() {
		org.wc.trackrite.onepack.Candidate candidate = new org.wc.trackrite.onepack.Candidate();

		try {

			register(candidate);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return candidate;
	}

	public org.wc.trackrite.onepack.Candidate createCandidateThree() {
		org.wc.trackrite.onepack.Candidate candidate = new org.wc.trackrite.onepack.Candidate();

		try {

			register(candidate);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return candidate;
	}

	public org.wc.trackrite.onepack.Candidate createCandidateFour() {
		org.wc.trackrite.onepack.Candidate candidate = new org.wc.trackrite.onepack.Candidate();

		try {

			register(candidate);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return candidate;
	}

	public org.wc.trackrite.onepack.Candidate createCandidateFive() {
		org.wc.trackrite.onepack.Candidate candidate = new org.wc.trackrite.onepack.Candidate();

		try {

			register(candidate);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return candidate;
	}

	public org.wc.trackrite.onepack.Candidate getRandomRecord() {

		if (candidates.isEmpty()) {
			createAll();
		}

		return candidates.get(new Random().nextInt(candidates.size()));
	}

	public List<org.wc.trackrite.onepack.Candidate> createAll() {
		createCandidateOne();
		createCandidateTwo();
		createCandidateThree();
		createCandidateFour();
		createCandidateFive();

		return candidates;
	}

	public void persistAll() {
		//if (!isPersistable() || alreadyPersisted)
		//	return;

		createAll();

		if (candidateAction == null)
			candidateAction = (com.nas.recovery.web.action.onepack.CandidateAction) Component
					.getInstance("candidateAction");

		for (org.wc.trackrite.onepack.Candidate candidate : candidates) {
			//try {
			candidateAction.setInstance(candidate);
			candidateAction.save();
			//} catch (BusinessException be) {
			//logger.warn(" Candidate " + candidate.getDisplayName()
			//		+ "couldn't be saved " + be.getMessage());
			//}
		}

		//alreadyPersisted = true;
	}

	/** Execute this method to manually generate objects
	 * @param args
	 */
	public static void main(String args[]) {
		new CandidateTestDataFactory().persistAll();
	}

}
