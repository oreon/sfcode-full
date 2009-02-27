package initialsetup;

import org.witchcraft.model.support.springbeanhelpers.BeanHelper;
import org.witchcraft.model.support.errorhandling.BusinessException;
import org.witchcraft.model.support.testing.TestDataFactory;

import com.oreon.cerebrum.drugs.CategoryTestDataFactory;

import com.oreon.cerebrum.drugs.DrugTestDataFactory;

import com.oreon.cerebrum.drugs.DrugCodeTestDataFactory;

import com.oreon.cerebrum.prescriptions.PrescriptionTestDataFactory;

import com.oreon.cerebrum.prescriptions.ItemTestDataFactory;

import com.oreon.cerebrum.prescriptions.PatientTestDataFactory;

/** 
 * This class populates the database with some initial data
 *
 */
public class InitialDataSetup {
	/*
		public BusinessEntity[] entities = {
				
					categoryTestDataFactory.persistAll(),
				
				
					drugTestDataFactory.persistAll(),
				
				
					drugCodeTestDataFactory.persistAll(),
				
				
					prescriptionTestDataFactory.persistAll(),
				
				
					itemTestDataFactory.persistAll(),
				
				
					patientTestDataFactory.persistAll(),
				
			
		};
	 */

	private int recordsToCreate;

	public int getRecordsToCreate() {
		return recordsToCreate;
	}

	public void setRecordsToCreate(int recordsToCreate) {
		this.recordsToCreate = recordsToCreate;
	}

	public static void main(String args[]) {
		InitialDataSetup ids = (InitialDataSetup) BeanHelper
				.getBean("initialDataSetup");
		ids.createRecords();
	}

	public void createRecords() {

		TestDataFactory categoryTestDataFactory = (TestDataFactory) BeanHelper
				.getBean("categoryTestDataFactory");

		categoryTestDataFactory.persistAll();

		TestDataFactory drugTestDataFactory = (TestDataFactory) BeanHelper
				.getBean("drugTestDataFactory");

		drugTestDataFactory.persistAll();

		TestDataFactory drugCodeTestDataFactory = (TestDataFactory) BeanHelper
				.getBean("drugCodeTestDataFactory");

		drugCodeTestDataFactory.persistAll();

		TestDataFactory prescriptionTestDataFactory = (TestDataFactory) BeanHelper
				.getBean("prescriptionTestDataFactory");

		prescriptionTestDataFactory.persistAll();

		TestDataFactory itemTestDataFactory = (TestDataFactory) BeanHelper
				.getBean("itemTestDataFactory");

		itemTestDataFactory.persistAll();

		TestDataFactory patientTestDataFactory = (TestDataFactory) BeanHelper
				.getBean("patientTestDataFactory");

		patientTestDataFactory.persistAll();

	}
}
