package usermanagement;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Random;
import java.util.ArrayList;
import org.witchcraft.model.support.springbeanhelpers.BeanHelper;
import org.witchcraft.model.support.testing.AbstractTestDataFactory;

import org.witchcraft.model.support.testing.TestDataFactory;

import org.springframework.transaction.annotation.Transactional;

import usermanagement.service.AuthorityService;

@Transactional
public class AuthorityTestDataFactory
		extends
			AbstractTestDataFactory<Authority> {

	List<Authority> authoritys = new ArrayList<Authority>();

	private static SimpleDateFormat dateFormat = new SimpleDateFormat(
			"yyyy.MM.dd HH:mm:ss z");

	AuthorityService authorityService;

	public AuthorityService getAuthorityService() {
		return authorityService;
	}

	public void setAuthorityService(AuthorityService authorityService) {
		this.authorityService = authorityService;
	}

	public void register(Authority authority) {
		authoritys.add(authority);
	}

	public Authority createAuthorityOne() {
		Authority authority = new Authority();

		try {

			authority.setAuthority("Mark");

			TestDataFactory userTestDataFactory = (TestDataFactory) BeanHelper
					.getBean("userTestDataFactory");

			authority.setUser((usermanagement.User) userTestDataFactory
					.loadOneRecord());

			register(authority);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return authority;
	}

	public Authority createAuthorityTwo() {
		Authority authority = new Authority();

		try {

			authority.setAuthority("theta");

			TestDataFactory userTestDataFactory = (TestDataFactory) BeanHelper
					.getBean("userTestDataFactory");

			authority.setUser((usermanagement.User) userTestDataFactory
					.loadOneRecord());

			register(authority);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return authority;
	}

	public Authority createAuthorityThree() {
		Authority authority = new Authority();

		try {

			authority.setAuthority("delta");

			TestDataFactory userTestDataFactory = (TestDataFactory) BeanHelper
					.getBean("userTestDataFactory");

			authority.setUser((usermanagement.User) userTestDataFactory
					.loadOneRecord());

			register(authority);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return authority;
	}

	public Authority createAuthorityFour() {
		Authority authority = new Authority();

		try {

			authority.setAuthority("Lavendar");

			TestDataFactory userTestDataFactory = (TestDataFactory) BeanHelper
					.getBean("userTestDataFactory");

			authority.setUser((usermanagement.User) userTestDataFactory
					.loadOneRecord());

			register(authority);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return authority;
	}

	public Authority createAuthorityFive() {
		Authority authority = new Authority();

		try {

			authority.setAuthority("pi");

			TestDataFactory userTestDataFactory = (TestDataFactory) BeanHelper
					.getBean("userTestDataFactory");

			authority.setUser((usermanagement.User) userTestDataFactory
					.loadOneRecord());

			register(authority);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return authority;
	}

	public Authority loadOneRecord() {
		List<Authority> authoritys = authorityService.loadAll();

		if (authoritys.isEmpty()) {
			persistAll();
			authoritys = authorityService.loadAll();
		}

		return authoritys.get(new Random().nextInt(authoritys.size()));
	}

	public List<Authority> getAllAsList() {

		if (authoritys.isEmpty()) {
			createAuthorityOne();
			createAuthorityTwo();
			createAuthorityThree();
			createAuthorityFour();
			createAuthorityFive();

		}

		return authoritys;
	}

	public void persistAll() {
		if (!isPersistable() || alreadyPersisted)
			return;

		getAllAsList();

		for (Authority authority : authoritys) {
			authorityService.save(authority);
		}

		alreadyPersisted = true;
	}

}
