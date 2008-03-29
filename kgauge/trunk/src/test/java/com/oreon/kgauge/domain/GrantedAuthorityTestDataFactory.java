package com.oreon.kgauge.domain;

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

import com.oreon.kgauge.service.GrantedAuthorityService;

@Transactional
public class GrantedAuthorityTestDataFactory
		extends
			AbstractTestDataFactory<GrantedAuthority> {

	private List<GrantedAuthority> grantedAuthoritys = new ArrayList<GrantedAuthority>();

	private static final Logger logger = Logger
			.getLogger(GrantedAuthorityTestDataFactory.class);

	private static int RECORDS_TO_CREATE = 30;

	private static SimpleDateFormat dateFormat = new SimpleDateFormat(
			"yyyy.MM.dd HH:mm:ss z");

	GrantedAuthorityService grantedAuthorityService;

	public GrantedAuthorityService getGrantedAuthorityService() {
		return grantedAuthorityService;
	}

	public void setGrantedAuthorityService(
			GrantedAuthorityService grantedAuthorityService) {
		this.grantedAuthorityService = grantedAuthorityService;
	}

	public void register(GrantedAuthority grantedAuthority) {
		grantedAuthoritys.add(grantedAuthority);
	}

	public GrantedAuthority createGrantedAuthorityOne() {
		GrantedAuthority grantedAuthority = new GrantedAuthority();

		try {

			grantedAuthority.setName("John");

			TestDataFactory userTestDataFactory = (TestDataFactory) BeanHelper
					.getBean("userTestDataFactory");

			grantedAuthority
					.setUser((com.oreon.kgauge.domain.User) userTestDataFactory
							.loadOneRecord());

			register(grantedAuthority);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return grantedAuthority;
	}

	public GrantedAuthority createGrantedAuthorityTwo() {
		GrantedAuthority grantedAuthority = new GrantedAuthority();

		try {

			grantedAuthority.setName("theta");

			TestDataFactory userTestDataFactory = (TestDataFactory) BeanHelper
					.getBean("userTestDataFactory");

			grantedAuthority
					.setUser((com.oreon.kgauge.domain.User) userTestDataFactory
							.loadOneRecord());

			register(grantedAuthority);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return grantedAuthority;
	}

	public GrantedAuthority createGrantedAuthorityThree() {
		GrantedAuthority grantedAuthority = new GrantedAuthority();

		try {

			grantedAuthority.setName("gamma");

			TestDataFactory userTestDataFactory = (TestDataFactory) BeanHelper
					.getBean("userTestDataFactory");

			grantedAuthority
					.setUser((com.oreon.kgauge.domain.User) userTestDataFactory
							.loadOneRecord());

			register(grantedAuthority);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return grantedAuthority;
	}

	public GrantedAuthority createGrantedAuthorityFour() {
		GrantedAuthority grantedAuthority = new GrantedAuthority();

		try {

			grantedAuthority.setName("Eric");

			TestDataFactory userTestDataFactory = (TestDataFactory) BeanHelper
					.getBean("userTestDataFactory");

			grantedAuthority
					.setUser((com.oreon.kgauge.domain.User) userTestDataFactory
							.loadOneRecord());

			register(grantedAuthority);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return grantedAuthority;
	}

	public GrantedAuthority createGrantedAuthorityFive() {
		GrantedAuthority grantedAuthority = new GrantedAuthority();

		try {

			grantedAuthority.setName("Lavendar");

			TestDataFactory userTestDataFactory = (TestDataFactory) BeanHelper
					.getBean("userTestDataFactory");

			grantedAuthority
					.setUser((com.oreon.kgauge.domain.User) userTestDataFactory
							.loadOneRecord());

			register(grantedAuthority);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return grantedAuthority;
	}

	public GrantedAuthority loadOneRecord() {
		List<GrantedAuthority> grantedAuthoritys = grantedAuthorityService
				.loadAll();

		if (grantedAuthoritys.isEmpty()) {
			persistAll();
			grantedAuthoritys = grantedAuthorityService.loadAll();
		}

		return grantedAuthoritys.get(new Random().nextInt(grantedAuthoritys
				.size()));
	}

	public List<GrantedAuthority> getAllAsList() {

		if (grantedAuthoritys.isEmpty()) {

			createGrantedAuthorityOne();
			createGrantedAuthorityTwo();
			createGrantedAuthorityThree();
			createGrantedAuthorityFour();
			createGrantedAuthorityFive();

		}

		return grantedAuthoritys;
	}

	public void persistAll() {
		if (!isPersistable() || alreadyPersisted)
			return;

		getAllAsList();

		for (GrantedAuthority grantedAuthority : grantedAuthoritys) {
			try {
				grantedAuthorityService.save(grantedAuthority);
			} catch (BusinessException be) {
				logger.warn(" GrantedAuthority "
						+ grantedAuthority.getDisplayName()
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
			GrantedAuthority grantedAuthority = createRandomGrantedAuthority();
			grantedAuthorityService.save(grantedAuthority);
		}
	}

	public GrantedAuthority createRandomGrantedAuthority() {
		GrantedAuthority grantedAuthority = new GrantedAuthority();

		grantedAuthority.setName((String) RandomValueGeneratorFactory
				.createInstance("String"));

		TestDataFactory userTestDataFactory = (TestDataFactory) BeanHelper
				.getBean("userTestDataFactory");

		grantedAuthority
				.setUser((com.oreon.kgauge.domain.User) userTestDataFactory
						.loadOneRecord());

		return grantedAuthority;
	}

}
