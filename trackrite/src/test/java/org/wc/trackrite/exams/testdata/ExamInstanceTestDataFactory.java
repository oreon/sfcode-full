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

public class ExamInstanceTestDataFactory
		extends
			AbstractTestDataFactory<org.wc.trackrite.exams.ExamInstance> {

	private List<org.wc.trackrite.exams.ExamInstance> examInstances = new ArrayList<org.wc.trackrite.exams.ExamInstance>();

	private static final Logger logger = Logger
			.getLogger(ExamInstanceTestDataFactory.class);

	private static int RECORDS_TO_CREATE = 30;

	private static SimpleDateFormat dateFormat = new SimpleDateFormat(
			"yyyy.MM.dd HH:mm:ss z");

	com.nas.recovery.web.action.exams.ExamInstanceAction examInstanceAction;

	org.wc.trackrite.exams.testdata.ExamTestDataFactory examTestDataFactory = new org.wc.trackrite.exams.testdata.ExamTestDataFactory();

	org.wc.trackrite.exams.testdata.CandidateTestDataFactory candidateTestDataFactory = new org.wc.trackrite.exams.testdata.CandidateTestDataFactory();

	public void register(org.wc.trackrite.exams.ExamInstance examInstance) {
		examInstances.add(examInstance);
	}

	public org.wc.trackrite.exams.ExamInstance createExamInstanceOne() {
		org.wc.trackrite.exams.ExamInstance examInstance = new org.wc.trackrite.exams.ExamInstance();

		try {

			examInstance.setExam(examTestDataFactory.getRandomRecord());

			examInstance.setCandidate(candidateTestDataFactory
					.getRandomRecord());

			register(examInstance);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return examInstance;
	}

	public org.wc.trackrite.exams.ExamInstance createExamInstanceTwo() {
		org.wc.trackrite.exams.ExamInstance examInstance = new org.wc.trackrite.exams.ExamInstance();

		try {

			examInstance.setExam(examTestDataFactory.getRandomRecord());

			examInstance.setCandidate(candidateTestDataFactory
					.getRandomRecord());

			register(examInstance);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return examInstance;
	}

	public org.wc.trackrite.exams.ExamInstance createExamInstanceThree() {
		org.wc.trackrite.exams.ExamInstance examInstance = new org.wc.trackrite.exams.ExamInstance();

		try {

			examInstance.setExam(examTestDataFactory.getRandomRecord());

			examInstance.setCandidate(candidateTestDataFactory
					.getRandomRecord());

			register(examInstance);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return examInstance;
	}

	public org.wc.trackrite.exams.ExamInstance createExamInstanceFour() {
		org.wc.trackrite.exams.ExamInstance examInstance = new org.wc.trackrite.exams.ExamInstance();

		try {

			examInstance.setExam(examTestDataFactory.getRandomRecord());

			examInstance.setCandidate(candidateTestDataFactory
					.getRandomRecord());

			register(examInstance);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return examInstance;
	}

	public org.wc.trackrite.exams.ExamInstance createExamInstanceFive() {
		org.wc.trackrite.exams.ExamInstance examInstance = new org.wc.trackrite.exams.ExamInstance();

		try {

			examInstance.setExam(examTestDataFactory.getRandomRecord());

			examInstance.setCandidate(candidateTestDataFactory
					.getRandomRecord());

			register(examInstance);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return examInstance;
	}

	public List<org.wc.trackrite.exams.ExamInstance> createAll() {
		createExamInstanceOne();
		createExamInstanceTwo();
		createExamInstanceThree();
		createExamInstanceFour();
		createExamInstanceFive();

		return examInstances;
	}

	@Override
	public List<org.wc.trackrite.exams.ExamInstance> getListOfRecords() {
		return examInstances;
	}

	@Override
	public String getQuery() {
		return "Select e from org.wc.trackrite.exams.ExamInstance e ";
	}

	public void persistAll() {
		init();
		createAll();

		for (org.wc.trackrite.exams.ExamInstance examInstance : examInstances) {
			persist(examInstance);
		}
	}

	/** Execute this method to manually generate objects
	 * @param args
	 */
	public static void main(String args[]) {
		new ExamInstanceTestDataFactory().persistAll();
	}

}
