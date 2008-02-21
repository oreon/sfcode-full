package com.oreon.kgauge.domain;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Random;
import java.util.ArrayList;
import org.witchcraft.model.support.springbeanhelpers.BeanHelper;
import org.witchcraft.model.support.testing.AbstractTestDataFactory;

import org.witchcraft.model.support.testing.TestDataFactory;

import org.witchcraft.model.randomgen.RandomValueGeneratorFactory;

import org.springframework.transaction.annotation.Transactional;

import com.oreon.kgauge.service.ExamInstanceService;

@Transactional
public class ExamInstanceTestDataFactory
		extends
			AbstractTestDataFactory<ExamInstance> {

	private List<ExamInstance> examInstances = new ArrayList<ExamInstance>();

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
			examInstanceService.save(examInstance);
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
