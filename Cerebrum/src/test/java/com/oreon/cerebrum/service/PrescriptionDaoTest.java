package com.oreon.cerebrum.service;

import com.oreon.cerebrum.prescriptions.Prescription;
import org.springframework.test.jpa.AbstractJpaTests;
import java.util.List;

import org.witchcraft.model.support.testing.TestDataFactory;
import org.witchcraft.model.support.springbeanhelpers.BeanHelper;

import java.text.SimpleDateFormat;

import javax.persistence.PersistenceException;
import org.hibernate.PropertyValueException;
import java.util.Date;

public class PrescriptionDaoTest extends AbstractJpaTests {

	protected Prescription prescriptionInstance = new Prescription();

	protected PrescriptionService prescriptionService;

	protected boolean bTest = true;

	private static SimpleDateFormat dateFormat = new SimpleDateFormat(
			"yyyy.MM.dd HH:mm:ss z");

	public void setPrescriptionService(PrescriptionService prescriptionService) {
		this.prescriptionService = prescriptionService;
	}

	protected TestDataFactory prescriptionTestDataFactory = (TestDataFactory) BeanHelper
			.getBean("prescriptionTestDataFactory");

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

			prescriptionInstance.setDx("alpha");

			TestDataFactory patientTestDataFactory = (TestDataFactory) BeanHelper
					.getBean("patientTestDataFactory");

			prescriptionInstance
					.setPatient((com.oreon.cerebrum.prescriptions.Patient) patientTestDataFactory
							.loadOneRecord());

			prescriptionService.save(prescriptionInstance);
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
			Prescription prescription = new Prescription();

			try {

				prescription.setDx("alpha");

				TestDataFactory patientTestDataFactory = (TestDataFactory) BeanHelper
						.getBean("patientTestDataFactory");

				prescription
						.setPatient((com.oreon.cerebrum.prescriptions.Patient) patientTestDataFactory
								.loadOneRecord());

			} catch (Exception ex) {
				ex.printStackTrace();
			}

			prescriptionService.save(prescription);
			assertNotNull(prescription.getId());
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

	public void testEdit() {

		try {
			//test saving a new record and updating an existing record;
			Prescription prescription = (Prescription) prescriptionTestDataFactory
					.loadOneRecord();

			prescription.setDx("delta");

			prescriptionService.save(prescription);

		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

	public void testCount() {
		assertTrue(prescriptionService.getCount() > 0);
	}

	//count the number of records - add one delete it - check count is same after delete
	/*
	public void testDelete() {
									
		try{
			long count,newCount,diff=0;			
			count=prescriptionService.getCount();
			Prescription prescription = (Prescription)prescriptionTestDataFactory.loadOneRecord();					
			prescriptionService.delete(prescription);
			newCount=prescriptionService.getCount();
			diff=count - newCount;
			assertEquals(diff, 1);
		}catch(Exception e){
			fail(e.getMessage());
		}
	}*/

	public void testLoad() {

		try {
			Prescription prescription = prescriptionService
					.load(prescriptionInstance.getId());
			assertNotNull(prescription.getId());
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

	public void testSearchByExample() {
		try {
			List<Prescription> prescriptions = prescriptionService
					.searchByExample(prescriptionInstance);
			assertTrue(!prescriptions.isEmpty());
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

	/////////////////// Queries //////////////////////////////////

}
