package com.oreon.cerebrum.drugs;

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

import com.oreon.cerebrum.service.DrugService;

@Transactional
public class DrugTestDataFactory extends AbstractTestDataFactory<Drug> {

	private List<Drug> drugs = new ArrayList<Drug>();

	private static final Logger logger = Logger
			.getLogger(DrugTestDataFactory.class);

	private static int RECORDS_TO_CREATE = 30;

	private static SimpleDateFormat dateFormat = new SimpleDateFormat(
			"yyyy.MM.dd HH:mm:ss z");

	DrugService drugService;

	public DrugService getDrugService() {
		return drugService;
	}

	public void setDrugService(DrugService drugService) {
		this.drugService = drugService;
	}

	public void register(Drug drug) {
		drugs.add(drug);
	}

	public Drug createDrugOne() {
		Drug drug = new Drug();

		try {

			drug.setName("theta15726");
			drug.setBioavalability(5897);
			drug.setHalfLife(6927);
			drug.setExcretion(com.oreon.cerebrum.drugs.Excretion.Renal);
			drug
					.setPregnancyCategory(com.oreon.cerebrum.drugs.PregnancyCategory.D);
			drug.setText("pi");

			register(drug);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return drug;
	}

	public Drug createDrugTwo() {
		Drug drug = new Drug();

		try {

			drug.setName("pi12119");
			drug.setBioavalability(62);
			drug.setHalfLife(424);
			drug.setExcretion(com.oreon.cerebrum.drugs.Excretion.Bile);
			drug
					.setPregnancyCategory(com.oreon.cerebrum.drugs.PregnancyCategory.B);
			drug.setText("Mark");

			register(drug);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return drug;
	}

	public Drug createDrugThree() {
		Drug drug = new Drug();

		try {

			drug.setName("theta12477");
			drug.setBioavalability(9625);
			drug.setHalfLife(2783);
			drug.setExcretion(com.oreon.cerebrum.drugs.Excretion.Hepatic);
			drug
					.setPregnancyCategory(com.oreon.cerebrum.drugs.PregnancyCategory.X);
			drug.setText("epsilon");

			register(drug);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return drug;
	}

	public Drug createDrugFour() {
		Drug drug = new Drug();

		try {

			drug.setName("zeta8556");
			drug.setBioavalability(1939);
			drug.setHalfLife(8265);
			drug.setExcretion(com.oreon.cerebrum.drugs.Excretion.Renal);
			drug
					.setPregnancyCategory(com.oreon.cerebrum.drugs.PregnancyCategory.C);
			drug.setText("John");

			register(drug);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return drug;
	}

	public Drug createDrugFive() {
		Drug drug = new Drug();

		try {

			drug.setName("beta64697");
			drug.setBioavalability(2322);
			drug.setHalfLife(1573);
			drug.setExcretion(com.oreon.cerebrum.drugs.Excretion.Bile);
			drug
					.setPregnancyCategory(com.oreon.cerebrum.drugs.PregnancyCategory.X);
			drug.setText("pi");

			register(drug);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return drug;
	}

	public Drug loadOneRecord() {
		List<Drug> drugs = drugService.loadAll();

		if (drugs.isEmpty()) {
			persistAll();
			drugs = drugService.loadAll();
		}

		return drugs.get(new Random().nextInt(drugs.size()));
	}

	public List<Drug> getAllAsList() {

		if (drugs.isEmpty()) {

			createDrugOne();
			createDrugTwo();
			createDrugThree();
			createDrugFour();
			createDrugFive();

		}

		return drugs;
	}

	public void persistAll() {
		if (!isPersistable() || alreadyPersisted)
			return;

		getAllAsList();

		for (Drug drug : drugs) {
			try {
				drugService.save(drug);
			} catch (BusinessException be) {
				logger.warn(" Drug " + drug.getDisplayName()
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
			Drug drug = createRandomDrug();
			drugService.save(drug);
		}
	}

	public Drug createRandomDrug() {
		Drug drug = new Drug();

		drug.setName((String) RandomValueGeneratorFactory
				.createInstance("String"));
		drug.setBioavalability((Integer) RandomValueGeneratorFactory
				.createInstance("Integer"));
		drug.setHalfLife((Integer) RandomValueGeneratorFactory
				.createInstance("Integer"));
		drug.setExcretion((Excretion) RandomValueGeneratorFactory
				.createInstance("Excretion"));
		drug
				.setPregnancyCategory((PregnancyCategory) RandomValueGeneratorFactory
						.createInstance("PregnancyCategory"));
		drug.setText((String) RandomValueGeneratorFactory
				.createInstance("String"));

		return drug;
	}

}
