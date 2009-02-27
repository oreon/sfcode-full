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

import com.oreon.cerebrum.service.PatientService;

@Transactional
public class PatientTestDataFactory extends AbstractTestDataFactory<Patient> {

	private List<Patient> patients = new ArrayList<Patient>();

	private static final Logger logger = Logger
			.getLogger(PatientTestDataFactory.class);

	private static int RECORDS_TO_CREATE = 30;

	private static SimpleDateFormat dateFormat = new SimpleDateFormat(
			"yyyy.MM.dd HH:mm:ss z");

	PatientService patientService;

	public PatientService getPatientService() {
		return patientService;
	}

	public void setPatientService(PatientService patientService) {
		this.patientService = patientService;
	}

	public void register(Patient patient) {
		patients.add(patient);
	}

	public Patient createPatientOne() {
		Patient patient = new Patient();

		try {

			patient.setFirstName("epsilon");
			patient.setLastName("zeta");
			patient.setDateOfBirth(dateFormat.parse("2009.03.18 01:50:14 EDT"));
			patient.setGender(com.oreon.cerebrum.prescriptions.Gender.F);
			patient.setPhone("Mark");
			patient.setAddress("theta");
			patient.setEmail("theta");

			register(patient);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return patient;
	}

	public Patient createPatientTwo() {
		Patient patient = new Patient();

		try {

			patient.setFirstName("Malissa");
			patient.setLastName("delta");
			patient.setDateOfBirth(dateFormat.parse("2009.03.20 10:37:26 EDT"));
			patient.setGender(com.oreon.cerebrum.prescriptions.Gender.F);
			patient.setPhone("Wilson");
			patient.setAddress("pi");
			patient.setEmail("alpha");

			register(patient);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return patient;
	}

	public Patient createPatientThree() {
		Patient patient = new Patient();

		try {

			patient.setFirstName("beta");
			patient.setLastName("alpha");
			patient.setDateOfBirth(dateFormat.parse("2009.03.12 20:40:14 EDT"));
			patient.setGender(com.oreon.cerebrum.prescriptions.Gender.F);
			patient.setPhone("beta");
			patient.setAddress("gamma");
			patient.setEmail("gamma");

			register(patient);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return patient;
	}

	public Patient createPatientFour() {
		Patient patient = new Patient();

		try {

			patient.setFirstName("gamma");
			patient.setLastName("zeta");
			patient.setDateOfBirth(dateFormat.parse("2009.03.18 02:36:21 EDT"));
			patient.setGender(com.oreon.cerebrum.prescriptions.Gender.M);
			patient.setPhone("Wilson");
			patient.setAddress("Malissa");
			patient.setEmail("John");

			register(patient);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return patient;
	}

	public Patient createPatientFive() {
		Patient patient = new Patient();

		try {

			patient.setFirstName("epsilon");
			patient.setLastName("Eric");
			patient.setDateOfBirth(dateFormat.parse("2009.03.20 07:36:54 EDT"));
			patient.setGender(com.oreon.cerebrum.prescriptions.Gender.M);
			patient.setPhone("Lavendar");
			patient.setAddress("delta");
			patient.setEmail("pi");

			register(patient);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return patient;
	}

	public Patient loadOneRecord() {
		List<Patient> patients = patientService.loadAll();

		if (patients.isEmpty()) {
			persistAll();
			patients = patientService.loadAll();
		}

		return patients.get(new Random().nextInt(patients.size()));
	}

	public List<Patient> getAllAsList() {

		if (patients.isEmpty()) {

			createPatientOne();
			createPatientTwo();
			createPatientThree();
			createPatientFour();
			createPatientFive();

		}

		return patients;
	}

	public void persistAll() {
		if (!isPersistable() || alreadyPersisted)
			return;

		getAllAsList();

		for (Patient patient : patients) {
			try {
				patientService.save(patient);
			} catch (BusinessException be) {
				logger.warn(" Patient " + patient.getDisplayName()
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
			Patient patient = createRandomPatient();
			patientService.save(patient);
		}
	}

	public Patient createRandomPatient() {
		Patient patient = new Patient();

		patient.setFirstName((String) RandomValueGeneratorFactory
				.createInstance("String"));
		patient.setLastName((String) RandomValueGeneratorFactory
				.createInstance("String"));
		patient.setDateOfBirth((java.util.Date) RandomValueGeneratorFactory
				.createInstance("Date"));
		patient.setGender((Gender) RandomValueGeneratorFactory
				.createInstance("Gender"));
		patient.setPhone((String) RandomValueGeneratorFactory
				.createInstance("String"));
		patient.setAddress((String) RandomValueGeneratorFactory
				.createInstance("String"));
		patient.setEmail((String) RandomValueGeneratorFactory
				.createInstance("String"));

		return patient;
	}

}
