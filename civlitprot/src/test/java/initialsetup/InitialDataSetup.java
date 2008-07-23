package initialsetup;

import org.witchcraft.model.support.springbeanhelpers.BeanHelper;
import org.witchcraft.model.support.errorhandling.BusinessException;
import org.witchcraft.model.support.testing.TestDataFactory;

import com.cc.civlit.domain.FirmTestDataFactory;

import com.cc.civlit.domain.FirmAdministratorTestDataFactory;

import com.cc.civlit.domain.LitigationCaseTestDataFactory;

import com.cc.civlit.domain.CaseAdministratorTestDataFactory;

import com.cc.civlit.domain.courtdivisions.LevelOfCourtTestDataFactory;

import com.cc.civlit.domain.courtdivisions.JurisdictionTestDataFactory;

import com.cc.civlit.domain.courtdivisions.FilingOfficeTestDataFactory;

import com.cc.civlit.domain.courtdivisions.DivsionTestDataFactory;

/** 
 * This class populates the database with some initial data
 *
 */
public class InitialDataSetup {

	public static void main(String args[]) {

		TestDataFactory firmTestDataFactory = (TestDataFactory) BeanHelper
				.getBean("firmTestDataFactory");

		firmTestDataFactory.persistAll();

		TestDataFactory firmAdministratorTestDataFactory = (TestDataFactory) BeanHelper
				.getBean("firmAdministratorTestDataFactory");

		firmAdministratorTestDataFactory.persistAll();

		TestDataFactory litigationCaseTestDataFactory = (TestDataFactory) BeanHelper
				.getBean("litigationCaseTestDataFactory");

		litigationCaseTestDataFactory.persistAll();

		TestDataFactory caseAdministratorTestDataFactory = (TestDataFactory) BeanHelper
				.getBean("caseAdministratorTestDataFactory");

		caseAdministratorTestDataFactory.persistAll();

		TestDataFactory levelOfCourtTestDataFactory = (TestDataFactory) BeanHelper
				.getBean("levelOfCourtTestDataFactory");

		levelOfCourtTestDataFactory.persistAll();

		TestDataFactory jurisdictionTestDataFactory = (TestDataFactory) BeanHelper
				.getBean("jurisdictionTestDataFactory");

		jurisdictionTestDataFactory.persistAll();

		TestDataFactory filingOfficeTestDataFactory = (TestDataFactory) BeanHelper
				.getBean("filingOfficeTestDataFactory");

		filingOfficeTestDataFactory.persistAll();

		TestDataFactory divsionTestDataFactory = (TestDataFactory) BeanHelper
				.getBean("divsionTestDataFactory");

		divsionTestDataFactory.persistAll();

	}
}
