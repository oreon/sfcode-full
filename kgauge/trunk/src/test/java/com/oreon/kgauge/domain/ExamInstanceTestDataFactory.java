package com.oreon.kgauge.domain;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Random;
import java.util.ArrayList;
import org.witchcraft.model.support.springbeanhelpers.BeanHelper;
import org.witchcraft.model.support.testing.AbstractTestDataFactory;

import org.witchcraft.model.support.testing.TestDataFactory;
import org.witchcraft.model.support.errorhandling.BusinessException;
import org.witchcraft.model.randomgen.RandomValueGeneratorFactory;

import org.springframework.transaction.annotation.Transactional;
import org.apache.log4j.Logger;

import com.oreon.kgauge.service.ExamInstanceService;

@Transactional
public class ExamInstanceTestDataFactory
		extends
			AbstractTestDataFactory<ExamInstance> {

	private List<ExamInstance> examInstances = new ArrayList<ExamInstance>();

	private static final Logger logger = Logger
			.getLogger(ExamInstanceTestDataFactory.class);

	private static int RECORDS_TO_CREATE = 30;

	private static SimpleDateFormat dateFormat = new SimpleDateFormat(
			"yyyy.MM.dd HH:mm:ss z");

	ExamInstanceService examInstanceService;

	public ExamInstanceService getExamInstanceService() {
		return examInstanceService;
	}

	public void setExamInstanceService(ExamInstanceService examInstanceService) {
		this.examInstanceService = examInstanceService;
	}

	public void register(ExamInstance examInstance) {
		examInstances.add(examInstance);
	}

	public ExamInstance createExamInstanceOne() {
		ExamInstance examInstance = new ExamInstance();

		try {

			examInstance.setDateOfExam(dateFormat
					.parse("2008.12.21 23:36:17 EST"));
			examInstance.setMaxScore(2648);
			examInstance.setCandidateScore(394);
			examInstance.setCertified(true);
			examInstance.setPercentage(9.57);

			TestDataFactory candidateTestDataFactory = (TestDataFactory) BeanHelper
					.getBean("candidateTestDataFactory");

			examInstance
					.setCandidate((com.oreon.kgauge.domain.Candidate) candidateTestDataFactory
							.loadOneRecord());

			TestDataFactory examTestDataFactory = (TestDataFactory) BeanHelper
					.getBean("examTestDataFactory");

			examInstance
					.setExam((com.oreon.kgauge.domain.Exam) examTestDataFactory
							.loadOneRecord());

			register(examInstance);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return examInstance;
	}

	public ExamInstance createExamInstanceTwo() {
		ExamInstance examInstance = new ExamInstance();

		try {

			examInstance.setDateOfExam(dateFormat
					.parse("2008.11.26 16:33:30 EST"));
			examInstance.setMaxScore(8673);
			examInstance.setCandidateScore(4798);
			examInstance.setCertified(false);
			examInstance.setPercentage(27.28);

			TestDataFactory candidateTestDataFactory = (TestDataFactory) BeanHelper
					.getBean("candidateTestDataFactory");

			examInstance
					.setCandidate((com.oreon.kgauge.domain.Candidate) candidateTestDataFactory
							.loadOneRecord());

			TestDataFactory examTestDataFactory = (TestDataFactory) BeanHelper
					.getBean("examTestDataFactory");

			examInstance
					.setExam((com.oreon.kgauge.domain.Exam) examTestDataFactory
							.loadOneRecord());

			register(examInstance);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return examInstance;
	}

	public ExamInstance createExamInstanceThree() {
		ExamInstance examInstance = new ExamInstance();

		try {

			examInstance.setDateOfExam(dateFormat
					.parse("2008.11.27 06:39:05 EST"));
			examInstance.setMaxScore(9703);
			examInstance.setCandidateScore(723);
			examInstance.setCertified(false);
			examInstance.setPercentage(84.71);

			TestDataFactory candidateTestDataFactory = (TestDataFactory) BeanHelper
					.getBean("candidateTestDataFactory");

			examInstance
					.setCandidate((com.oreon.kgauge.domain.Candidate) candidateTestDataFactory
							.loadOneRecord());

			TestDataFactory examTestDataFactory = (TestDataFactory) BeanHelper
					.getBean("examTestDataFactory");

			examInstance
					.setExam((com.oreon.kgauge.domain.Exam) examTestDataFactory
							.loadOneRecord());

			register(examInstance);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return examInstance;
	}

	public ExamInstance createExamInstanceFour() {
		ExamInstance examInstance = new ExamInstance();

		try {

			examInstance.setDateOfExam(dateFormat
					.parse("2009.01.11 10:13:30 EST"));
			examInstance.setMaxScore(4311);
			examInstance.setCandidateScore(8122);
			examInstance.setCertified(false);
			examInstance.setPercentage(88.22);

			TestDataFactory candidateTestDataFactory = (TestDataFactory) BeanHelper
					.getBean("candidateTestDataFactory");

			examInstance
					.setCandidate((com.oreon.kgauge.domain.Candidate) candidateTestDataFactory
							.loadOneRecord());

			TestDataFactory examTestDataFactory = (TestDataFactory) BeanHelper
					.getBean("examTestDataFactory");

			examInstance
					.setExam((com.oreon.kgauge.domain.Exam) examTestDataFactory
							.loadOneRecord());

			register(examInstance);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return examInstance;
	}

	public ExamInstance createExamInstanceFive() {
		ExamInstance examInstance = new ExamInstance();

		try {

			examInstance.setDateOfExam(dateFormat
					.parse("2008.12.17 20:52:57 EST"));
			examInstance.setMaxScore(3569);
			examInstance.setCandidateScore(134);
			examInstance.setCertified(true);
			examInstance.setPercentage(3.72);

			TestDataFactory candidateTestDataFactory = (TestDataFactory) BeanHelper
					.getBean("candidateTestDataFactory");

			examInstance
					.setCandidate((com.oreon.kgauge.domain.Candidate) candidateTestDataFactory
							.loadOneRecord());

			TestDataFactory examTestDataFactory = (TestDataFactory) BeanHelper
					.getBean("examTestDataFactory");

			examInstance
					.setExam((com.oreon.kgauge.domain.Exam) examTestDataFactory
							.loadOneRecord());

			register(examInstance);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return examInstance;
	}

	public ExamInstance loadOneRecord() {
		List<ExamInstance> examInstances = examInstanceService.loadAll();

		if (examInstances.isEmpty()) {
			persistAll();
			examInstances = examInstanceService.loadAll();
		}

		return examInstances.get(new Random().nextInt(examInstances.size()));
	}

	public List<ExamInstance> getAllAsList() {

		if (examInstances.isEmpty()) {

			createExamInstanceOne();
			createExamInstanceTwo();
			createExamInstanceThree();
			createExamInstanceFour();
			createExamInstanceFive();

		}

		return examInstances;
	}

	public void persistAll() {
		if (!isPersistable() || alreadyPersisted)
			return;

		getAllAsList();

		for (ExamInstance examInstance : examInstances) {
			try {
				examInstanceService.save(examInstance);
			} catch (BusinessException be) {
				logger.warn(" ExamInstance " + examInstance.getDisplayName()
						+ "couldn't be saved " + be.getMessage());
			}
		}

		alreadyPersisted = true;
	}

	/** Execute this method to manually generate additional orders
	 * @param args
	 */
	public static void main(String args[]) {

		TestDataFactory placedOrderTestDataFactory = (TestDataFactory) BeanHelper
				.getBean("placedOrderTestDataFactory");

		placedOrderTestDataFactory.createAndSaveRecords(RECORDS_TO_CREATE);
	}

	public void createAndSaveRecords(int recordsTocreate) {
		for (int i = 0; i < recordsTocreate; i++) {
			ExamInstance examInstance = createRandomExamInstance();
			examInstanceService.save(examInstance);
		}
	}

	public ExamInstance createRandomExamInstance() {
		ExamInstance examInstance = new ExamInstance();

		examInstance.setDateOfExam((java.util.Date) RandomValueGeneratorFactory
				.createInstance("Date"));
		examInstance.setMaxScore((Integer) RandomValueGeneratorFactory
				.createInstance("Integer"));
		examInstance.setCandidateScore((Integer) RandomValueGeneratorFactory
				.createInstance("Integer"));
		examInstance.setCertified((Boolean) RandomValueGeneratorFactory
				.createInstance("Boolean"));
		examInstance.setPercentage((Double) RandomValueGeneratorFactory
				.createInstance("Double"));

		TestDataFactory candidateTestDataFactory = (TestDataFactory) BeanHelper
				.getBean("candidateTestDataFactory");

		examInstance
				.setCandidate((com.oreon.kgauge.domain.Candidate) candidateTestDataFactory
						.loadOneRecord());

		TestDataFactory examTestDataFactory = (TestDataFactory) BeanHelper
				.getBean("examTestDataFactory");

		examInstance.setExam((com.oreon.kgauge.domain.Exam) examTestDataFactory
				.loadOneRecord());

		return examInstance;
	}

}
