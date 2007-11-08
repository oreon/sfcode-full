package initialsetup;

import org.witchcraft.model.support.springbeanhelpers.BeanHelper;
import org.witchcraft.model.support.testing.TestDataFactory;

import com.oreon.olympics.domain.CountryTestDataFactory;

import com.oreon.olympics.domain.TeamTestDataFactory;

import com.oreon.olympics.domain.EventTestDataFactory;

import com.oreon.olympics.domain.AthleteTestDataFactory;

import com.oreon.olympics.domain.EventInstanceTestDataFactory;

import com.oreon.olympics.domain.CategoryTestDataFactory;

import com.oreon.olympics.domain.TournamentTestDataFactory;

import com.oreon.olympics.domain.TeamEventInstanceTestDataFactory;

import com.oreon.olympics.domain.ParticipationTestDataFactory;

/** 
 * This class populates the database with some initial data
 *
 */
public class InitialDataSetup {

	public static void main(String args[]) {

		TestDataFactory countryTestDataFactory = (TestDataFactory) BeanHelper
				.getBean("countryTestDataFactory");

		countryTestDataFactory.persistAll();

		TestDataFactory teamTestDataFactory = (TestDataFactory) BeanHelper
				.getBean("teamTestDataFactory");

		teamTestDataFactory.persistAll();

		TestDataFactory eventTestDataFactory = (TestDataFactory) BeanHelper
				.getBean("eventTestDataFactory");

		eventTestDataFactory.persistAll();

		TestDataFactory athleteTestDataFactory = (TestDataFactory) BeanHelper
				.getBean("athleteTestDataFactory");

		athleteTestDataFactory.persistAll();

		TestDataFactory eventInstanceTestDataFactory = (TestDataFactory) BeanHelper
				.getBean("eventInstanceTestDataFactory");

		eventInstanceTestDataFactory.persistAll();

		TestDataFactory categoryTestDataFactory = (TestDataFactory) BeanHelper
				.getBean("categoryTestDataFactory");

		categoryTestDataFactory.persistAll();

		TestDataFactory tournamentTestDataFactory = (TestDataFactory) BeanHelper
				.getBean("tournamentTestDataFactory");

		tournamentTestDataFactory.persistAll();

		TestDataFactory teamEventInstanceTestDataFactory = (TestDataFactory) BeanHelper
				.getBean("teamEventInstanceTestDataFactory");

		teamEventInstanceTestDataFactory.persistAll();

		TestDataFactory participationTestDataFactory = (TestDataFactory) BeanHelper
				.getBean("participationTestDataFactory");

		participationTestDataFactory.persistAll();

	}
}
