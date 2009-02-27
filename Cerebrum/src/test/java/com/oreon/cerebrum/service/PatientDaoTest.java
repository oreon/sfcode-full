package com.oreon.cerebrum.service;

import com.oreon.cerebrum.prescriptions.Patient;
import org.springframework.test.jpa.AbstractJpaTests;
import java.util.List;

import org.witchcraft.model.support.testing.TestDataFactory;
import org.witchcraft.model.support.springbeanhelpers.BeanHelper;

import java.text.SimpleDateFormat;

import javax.persistence.PersistenceException;
import org.hibernate.PropertyValueException;
import java.util.Date;

public class PatientDaoTest extends AbstractJpaTests {

	protected Patient patientInstance = new Patient();

	protected PatientService patientService;

	protected boolean bTest = true;

	private static SimpleDateFormat dateFormat = new SimpleDateFormat(
			"yyyy.MM.dd HH:mm:ss z");

	public void setPatientService(PatientService patientService) {
		this.patientService = patientService;
	}

	protected TestDataFactory patientTestDataFactory = (TestDataFactory) BeanHelper
			.getBean("patientTestDataFactory");

	@Override
	protected String[] getConfigLocations() {
		return new String[]{"classpath:/applicationContext.xml",
				"classpath:/testDataFactories.xml"};
	}

	@Override
	protected void runTest() throws Throwable {
		if (!bTest)
			return;
		super.runTest();
	}

	/**
	 * Do the setup before the test in this method
	 **/
	protected void onSetUpInTransaction() throws Exception {
		try {

			patientInstance.setFirstName("delta");
			patientInstance.setLastName("theta");
			patientInstance.setDateOfBirth(dateFormat
					.parse("2009.03.02 21:40:46 EST"));
			patientInstance
					.setGender(com.oreon.cerebrum.prescriptions.Gender.F);
			patientInstance.setPhone("pi");
			patientInstance.setAddress("zeta");
			patientInstance.setEmail("alpha");

			patientService.save(patientInstance);
		} catch (PersistenceException pe) {
			//if this instance can't be created due to back references e.g an orderItem needs an Order - 
			// - we will simply skip generated tests.
			if (pe.getCause() instanceof PropertyValueException
					&& pe.getMessage().contains("Backref")) {
				bTest = false;
			}
		} catch (Exception e) {
			fail(e.getMessage());
		}

	}

	//test saving a new record and updating an existing record;
	public void testSave() {

		try {
			Patient patient = new Patient();

			try {

				patient.setFirstName("gamma");
				patient.setLastName("alpha");
				patient.setDateOfBirth(dateFormat
						.parse("2009.03.21 05:06:21 EDT"));
				patient.setGender(com.oreon.cerebrum.prescriptions.Gender.F);
				patient.setPhone("Malissa");
				patient.setAddress("Mark");
				patient.setEmail("delta");

			} catch (Exception ex) {
				ex.printStackTrace();
			}

			patientService.save(patient);
			assertNotNull(patient.getId());
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

	public void testEdit() {

		try {
			//test saving a new record and updating an existing record;
			Patient patient = (Patient) patientTestDataFactory.loadOneRecord();

			patient.setFirstName("Mark");
			patient.setLastName("pi");
			patient.setDateOfBirth(dateFormat.parse("2009.02.13 09:31:52 EST"));
			patient.setGender(com.oreon.cerebrum.prescriptions.Gender.M);
			patient.setPhone("pi");
			patient.setAddress("John");
			patient.setEmail("Mark");

			patientService.save(patient);

		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

	public void testCount() {
		assertTrue(patientService.getCount() > 0);
	}

	//count the number of records - add one delete it - check count is same after delete
	/*
	public void testDelete() {
									
		try{
			long count,newCount,diff=0;			
			count=patientService.getCount();
			Patient patient = (Patient)patientTestDataFactory.loadOneRecord();					
			patientService.delete(patient);
			newCount=patientService.getCount();
			diff=count - newCount;
			assertEquals(diff, 1);
		}catch(Exception e){
			fail(e.getMessage());
		}
	}*/

	public void testLoad() {

		try {
			Patient patient = patientService.load(patientInstance.getId());
			assertNotNull(patient.getId());
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

	public void testSearchByExample() {
		try {
			List<Patient> patients = patientService
					.searchByExample(patientInstance);
			assertTrue(!patients.isEmpty());
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

	/////////////////// Queries //////////////////////////////////

}
