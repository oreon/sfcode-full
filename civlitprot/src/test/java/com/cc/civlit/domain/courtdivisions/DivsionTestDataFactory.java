package com.cc.civlit.domain.courtdivisions;

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

import com.cc.civlit.domain.service.DivsionService;

@Transactional
public class DivsionTestDataFactory extends AbstractTestDataFactory<Divsion> {

	private List<Divsion> divsions = new ArrayList<Divsion>();

	private static final Logger logger = Logger
			.getLogger(DivsionTestDataFactory.class);

	private static int RECORDS_TO_CREATE = 30;

	private static SimpleDateFormat dateFormat = new SimpleDateFormat(
			"yyyy.MM.dd HH:mm:ss z");

	DivsionService divsionService;

	public DivsionService getDivsionService() {
		return divsionService;
	}

	public void setDivsionService(DivsionService divsionService) {
		this.divsionService = divsionService;
	}

	public void register(Divsion divsion) {
		divsions.add(divsion);
	}

	public Divsion createDivsionOne() {
		Divsion divsion = new Divsion();

		try {

			divsion.setName("epsilon");

			TestDataFactory filingOfficeTestDataFactory = (TestDataFactory) BeanHelper
					.getBean("filingOfficeTestDataFactory");

			divsion
					.setFilingOffice((com.cc.civlit.domain.courtdivisions.FilingOffice) filingOfficeTestDataFactory
							.loadOneRecord());

			register(divsion);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return divsion;
	}

	public Divsion createDivsionTwo() {
		Divsion divsion = new Divsion();

		try {

			divsion.setName("beta");

			TestDataFactory filingOfficeTestDataFactory = (TestDataFactory) BeanHelper
					.getBean("filingOfficeTestDataFactory");

			divsion
					.setFilingOffice((com.cc.civlit.domain.courtdivisions.FilingOffice) filingOfficeTestDataFactory
							.loadOneRecord());

			register(divsion);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return divsion;
	}

	public Divsion createDivsionThree() {
		Divsion divsion = new Divsion();

		try {

			divsion.setName("Mark");

			TestDataFactory filingOfficeTestDataFactory = (TestDataFactory) BeanHelper
					.getBean("filingOfficeTestDataFactory");

			divsion
					.setFilingOffice((com.cc.civlit.domain.courtdivisions.FilingOffice) filingOfficeTestDataFactory
							.loadOneRecord());

			register(divsion);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return divsion;
	}

	public Divsion createDivsionFour() {
		Divsion divsion = new Divsion();

		try {

			divsion.setName("Wilson");

			TestDataFactory filingOfficeTestDataFactory = (TestDataFactory) BeanHelper
					.getBean("filingOfficeTestDataFactory");

			divsion
					.setFilingOffice((com.cc.civlit.domain.courtdivisions.FilingOffice) filingOfficeTestDataFactory
							.loadOneRecord());

			register(divsion);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return divsion;
	}

	public Divsion createDivsionFive() {
		Divsion divsion = new Divsion();

		try {

			divsion.setName("alpha");

			TestDataFactory filingOfficeTestDataFactory = (TestDataFactory) BeanHelper
					.getBean("filingOfficeTestDataFactory");

			divsion
					.setFilingOffice((com.cc.civlit.domain.courtdivisions.FilingOffice) filingOfficeTestDataFactory
							.loadOneRecord());

			register(divsion);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return divsion;
	}

	public Divsion loadOneRecord() {
		List<Divsion> divsions = divsionService.loadAll();

		if (divsions.isEmpty()) {
			persistAll();
			divsions = divsionService.loadAll();
		}

		return divsions.get(new Random().nextInt(divsions.size()));
	}

	public List<Divsion> getAllAsList() {

		if (divsions.isEmpty()) {

			createDivsionOne();
			createDivsionTwo();
			createDivsionThree();
			createDivsionFour();
			createDivsionFive();

		}

		return divsions;
	}

	public void persistAll() {
		if (!isPersistable() || alreadyPersisted)
			return;

		getAllAsList();

		for (Divsion divsion : divsions) {
			try {
				divsionService.save(divsion);
			} catch (BusinessException be) {
				logger.warn(" Divsion " + divsion.getDisplayName()
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
			Divsion divsion = createRandomDivsion();
			divsionService.save(divsion);
		}
	}

	public Divsion createRandomDivsion() {
		Divsion divsion = new Divsion();

		divsion.setName((String) RandomValueGeneratorFactory
				.createInstance("String"));

		TestDataFactory filingOfficeTestDataFactory = (TestDataFactory) BeanHelper
				.getBean("filingOfficeTestDataFactory");

		divsion
				.setFilingOffice((com.cc.civlit.domain.courtdivisions.FilingOffice) filingOfficeTestDataFactory
						.loadOneRecord());

		return divsion;
	}

}
