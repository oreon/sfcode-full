package com.oreon.cerebrum.prescriptions;

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

import com.oreon.cerebrum.service.PrescriptionService;

@Transactional
public class PrescriptionTestDataFactory
		extends
			AbstractTestDataFactory<Prescription> {

	private List<Prescription> prescriptions = new ArrayList<Prescription>();

	private static final Logger logger = Logger
			.getLogger(PrescriptionTestDataFactory.class);

	private static int RECORDS_TO_CREATE = 30;

	private static SimpleDateFormat dateFormat = new SimpleDateFormat(
			"yyyy.MM.dd HH:mm:ss z");

	PrescriptionService prescriptionService;

	public PrescriptionService getPrescriptionService() {
		return prescriptionService;
	}

	public void setPrescriptionService(PrescriptionService prescriptionService) {
		this.prescriptionService = prescriptionService;
	}

	public void register(Prescription prescription) {
		prescriptions.add(prescription);
	}

	public Prescription createPrescriptionOne() {
		Prescription prescription = new Prescription();

		try {

			prescription.setDx("gamma");

			TestDataFactory patientTestDataFactory = (TestDataFactory) BeanHelper
					.getBean("patientTestDataFactory");

			prescription
					.setPatient((com.oreon.cerebrum.prescriptions.Patient) patientTestDataFactory
							.loadOneRecord());

			register(prescription);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return prescription;
	}

	public Prescription createPrescriptionTwo() {
		Prescription prescription = new Prescription();

		try {

			prescription.setDx("Lavendar");

			TestDataFactory patientTestDataFactory = (TestDataFactory) BeanHelper
					.getBean("patientTestDataFactory");

			prescription
					.setPatient((com.oreon.cerebrum.prescriptions.Patient) patientTestDataFactory
							.loadOneRecord());

			register(prescription);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return prescription;
	}

	public Prescription createPrescriptionThree() {
		Prescription prescription = new Prescription();

		try {

			prescription.setDx("beta");

			TestDataFactory patientTestDataFactory = (TestDataFactory) BeanHelper
					.getBean("patientTestDataFactory");

			prescription
					.setPatient((com.oreon.cerebrum.prescriptions.Patient) patientTestDataFactory
							.loadOneRecord());

			register(prescription);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return prescription;
	}

	public Prescription createPrescriptionFour() {
		Prescription prescription = new Prescription();

		try {

			prescription.setDx("Malissa");

			TestDataFactory patientTestDataFactory = (TestDataFactory) BeanHelper
					.getBean("patientTestDataFactory");

			prescription
					.setPatient((com.oreon.cerebrum.prescriptions.Patient) patientTestDataFactory
							.loadOneRecord());

			register(prescription);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return prescription;
	}

	public Prescription createPrescriptionFive() {
		Prescription prescription = new Prescription();

		try {

			prescription.setDx("Malissa");

			TestDataFactory patientTestDataFactory = (TestDataFactory) BeanHelper
					.getBean("patientTestDataFactory");

			prescription
					.setPatient((com.oreon.cerebrum.prescriptions.Patient) patientTestDataFactory
							.loadOneRecord());

			register(prescription);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return prescription;
	}

	public Prescription loadOneRecord() {
		List<Prescription> prescriptions = prescriptionService.loadAll();

		if (prescriptions.isEmpty()) {
			persistAll();
			prescriptions = prescriptionService.loadAll();
		}

		return prescriptions.get(new Random().nextInt(prescriptions.size()));
	}

	public List<Prescription> getAllAsList() {

		if (prescriptions.isEmpty()) {

			createPrescriptionOne();
			createPrescriptionTwo();
			createPrescriptionThree();
			createPrescriptionFour();
			createPrescriptionFive();

		}

		return prescriptions;
	}

	public void persistAll() {
		if (!isPersistable() || alreadyPersisted)
			return;

		getAllAsList();

		for (Prescription prescription : prescriptions) {
			try {
				prescriptionService.save(prescription);
			} catch (BusinessException be) {
				logger.warn(" Prescription " + prescription.getDisplayName()
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
			Prescription prescription = createRandomPrescription();
			prescriptionService.save(prescription);
		}
	}

	public Prescription createRandomPrescription() {
		Prescription prescription = new Prescription();

		prescription.setDx((String) RandomValueGeneratorFactory
				.createInstance("String"));

		TestDataFactory patientTestDataFactory = (TestDataFactory) BeanHelper
				.getBean("patientTestDataFactory");

		prescription
				.setPatient((com.oreon.cerebrum.prescriptions.Patient) patientTestDataFactory
						.loadOneRecord());

		return prescription;
	}

}
