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
					.parse("2008.12.08 13:16:17 EST"));
			examInstance.setMaxScore(5777);
			examInstance.setCandidateScore(7117);
			examInstance.setCertified(false);
			examInstance.setPercentage(13.74);

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
					.parse("2008.11.25 15:24:35 EST"));
			examInstance.setMaxScore(8915);
			examInstance.setCandidateScore(1559);
			examInstance.setCertified(true);
			examInstance.setPercentage(18.38);

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
					.parse("2008.12.12 16:50:10 EST"));
			examInstance.setMaxScore(35);
			examInstance.setCandidateScore(1669);
			examInstance.setCertified(true);
			examInstance.setPercentage(81.87);

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
					.parse("2008.12.07 21:41:15 EST"));
			examInstance.setMaxScore(5832);
			examInstance.setCandidateScore(6824);
			examInstance.setCertified(false);
			examInstance.setPercentage(73.2);

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
					.parse("2008.12.21 11:30:43 EST"));
			examInstance.setMaxScore(8664);
			examInstance.setCandidateScore(4349);
			examInstance.setCertified(true);
			examInstance.setPercentage(46.54);

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
