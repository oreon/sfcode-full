package org.wc.trackrite.exams.testdata;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Random;
import java.util.ArrayList;
import org.jboss.seam.Component;
import org.witchcraft.action.test.AbstractTestDataFactory;

//import org.witchcraft.model.support.errorhandling.BusinessException;
//import org.witchcraft.model.randomgen.RandomValueGeneratorFactory;

import org.apache.log4j.Logger;

public class CandidateTestDataFactory
		extends
			AbstractTestDataFactory<org.wc.trackrite.exams.Candidate> {

	private List<org.wc.trackrite.exams.Candidate> candidates = new ArrayList<org.wc.trackrite.exams.Candidate>();

	private static final Logger logger = Logger
			.getLogger(CandidateTestDataFactory.class);

	private static int RECORDS_TO_CREATE = 30;

	private static SimpleDateFormat dateFormat = new SimpleDateFormat(
			"yyyy.MM.dd HH:mm:ss z");

	com.nas.recovery.web.action.exams.CandidateAction candidateAction;

	public void register(org.wc.trackrite.exams.Candidate candidate) {
		candidates.add(candidate);
	}

	public org.wc.trackrite.exams.Candidate createCandidateOne() {
		org.wc.trackrite.exams.Candidate candidate = new org.wc.trackrite.exams.Candidate();

		try {

			register(candidate);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return candidate;
	}

	public org.wc.trackrite.exams.Candidate createCandidateTwo() {
		org.wc.trackrite.exams.Candidate candidate = new org.wc.trackrite.exams.Candidate();

		try {

			register(candidate);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return candidate;
	}

	public org.wc.trackrite.exams.Candidate createCandidateThree() {
		org.wc.trackrite.exams.Candidate candidate = new org.wc.trackrite.exams.Candidate();

		try {

			register(candidate);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return candidate;
	}

	public org.wc.trackrite.exams.Candidate createCandidateFour() {
		org.wc.trackrite.exams.Candidate candidate = new org.wc.trackrite.exams.Candidate();

		try {

			register(candidate);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return candidate;
	}

	public org.wc.trackrite.exams.Candidate createCandidateFive() {
		org.wc.trackrite.exams.Candidate candidate = new org.wc.trackrite.exams.Candidate();

		try {

			register(candidate);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return candidate;
	}

	public List<org.wc.trackrite.exams.Candidate> createAll() {
		createCandidateOne();
		createCandidateTwo();
		createCandidateThree();
		createCandidateFour();
		createCandidateFive();

		return candidates;
	}

	@Override
	public List<org.wc.trackrite.exams.Candidate> getListOfRecords() {
		return candidates;
	}

	@Override
	public String getQuery() {
		return "Select e from org.wc.trackrite.exams.Candidate e ";
	}

	public void persistAll() {
		init();
		createAll();

		for (org.wc.trackrite.exams.Candidate candidate : candidates) {
			persist(candidate);
		}
	}

	/** Execute this method to manually generate objects
	 * @param args
	 */
	public static void main(String args[]) {
		new CandidateTestDataFactory().persistAll();
	}

}
