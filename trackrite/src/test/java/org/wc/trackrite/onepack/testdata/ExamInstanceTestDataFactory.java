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

public class ExamInstanceTestDataFactory
		extends
			AbstractTestDataFactory<org.wc.trackrite.onepack.ExamInstance> {

	private List<org.wc.trackrite.onepack.ExamInstance> examInstances = new ArrayList<org.wc.trackrite.onepack.ExamInstance>();

	private static final Logger logger = Logger
			.getLogger(ExamInstanceTestDataFactory.class);

	private static int RECORDS_TO_CREATE = 30;

	private static SimpleDateFormat dateFormat = new SimpleDateFormat(
			"yyyy.MM.dd HH:mm:ss z");

	com.nas.recovery.web.action.onepack.ExamInstanceAction examInstanceAction;

	org.wc.trackrite.onepack.testdata.ExamTestDataFactory examTestDataFactory = new org.wc.trackrite.onepack.testdata.ExamTestDataFactory();

	org.wc.trackrite.onepack.testdata.CandidateTestDataFactory candidateTestDataFactory = new org.wc.trackrite.onepack.testdata.CandidateTestDataFactory();

	public void register(org.wc.trackrite.onepack.ExamInstance examInstance) {
		examInstances.add(examInstance);
	}

	public org.wc.trackrite.onepack.ExamInstance createExamInstanceOne() {
		org.wc.trackrite.onepack.ExamInstance examInstance = new org.wc.trackrite.onepack.ExamInstance();

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

	public org.wc.trackrite.onepack.ExamInstance createExamInstanceTwo() {
		org.wc.trackrite.onepack.ExamInstance examInstance = new org.wc.trackrite.onepack.ExamInstance();

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

	public org.wc.trackrite.onepack.ExamInstance createExamInstanceThree() {
		org.wc.trackrite.onepack.ExamInstance examInstance = new org.wc.trackrite.onepack.ExamInstance();

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

	public org.wc.trackrite.onepack.ExamInstance createExamInstanceFour() {
		org.wc.trackrite.onepack.ExamInstance examInstance = new org.wc.trackrite.onepack.ExamInstance();

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

	public org.wc.trackrite.onepack.ExamInstance createExamInstanceFive() {
		org.wc.trackrite.onepack.ExamInstance examInstance = new org.wc.trackrite.onepack.ExamInstance();

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

	public org.wc.trackrite.onepack.ExamInstance getRandomRecord() {

		if (examInstances.isEmpty()) {
			createAll();
		}

		return examInstances.get(new Random().nextInt(examInstances.size()));
	}

	public List<org.wc.trackrite.onepack.ExamInstance> createAll() {
		createExamInstanceOne();
		createExamInstanceTwo();
		createExamInstanceThree();
		createExamInstanceFour();
		createExamInstanceFive();

		return examInstances;
	}

	public void persistAll() {
		//if (!isPersistable() || alreadyPersisted)
		//	return;

		createAll();

		if (examInstanceAction == null)
			examInstanceAction = (com.nas.recovery.web.action.onepack.ExamInstanceAction) Component
					.getInstance("examInstanceAction");

		for (org.wc.trackrite.onepack.ExamInstance examInstance : examInstances) {
			//try {
			examInstanceAction.setInstance(examInstance);
			examInstanceAction.save();
			//} catch (BusinessException be) {
			//logger.warn(" ExamInstance " + examInstance.getDisplayName()
			//		+ "couldn't be saved " + be.getMessage());
			//}
		}

		//alreadyPersisted = true;
	}

	/** Execute this method to manually generate objects
	 * @param args
	 */
	public static void main(String args[]) {
		new ExamInstanceTestDataFactory().persistAll();
	}

}
