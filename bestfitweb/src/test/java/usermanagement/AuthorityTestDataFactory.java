package usermanagement;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Random;
import java.util.ArrayList;
import org.witchcraft.model.support.springbeanhelpers.BeanHelper;
import org.witchcraft.model.support.AbstractTestDataFactory;

import usermanagement.service.AuthorityService;

public class AuthorityTestDataFactory extends AbstractTestDataFactory {

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

			authority.setAuthority("zeta");

			UserTestDataFactory userTestDataFactory = (UserTestDataFactory) BeanHelper
					.getBean("userTestDataFactory");

			authority.setUser(userTestDataFactory.loadUser());

			register(authority);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return authority;
	}

	public Authority createAuthorityTwo() {
		Authority authority = new Authority();

		try {

			authority.setAuthority("delta");

			UserTestDataFactory userTestDataFactory = (UserTestDataFactory) BeanHelper
					.getBean("userTestDataFactory");

			authority.setUser(userTestDataFactory.loadUser());

			register(authority);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return authority;
	}

	public Authority createAuthorityThree() {
		Authority authority = new Authority();

		try {

			authority.setAuthority("epsilon");

			UserTestDataFactory userTestDataFactory = (UserTestDataFactory) BeanHelper
					.getBean("userTestDataFactory");

			authority.setUser(userTestDataFactory.loadUser());

			register(authority);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return authority;
	}

	public Authority createAuthorityFour() {
		Authority authority = new Authority();

		try {

			authority.setAuthority("delta");

			UserTestDataFactory userTestDataFactory = (UserTestDataFactory) BeanHelper
					.getBean("userTestDataFactory");

			authority.setUser(userTestDataFactory.loadUser());

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

			UserTestDataFactory userTestDataFactory = (UserTestDataFactory) BeanHelper
					.getBean("userTestDataFactory");

			authority.setUser(userTestDataFactory.loadUser());

			register(authority);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return authority;
	}

	public Authority loadAuthority() {
		List<Authority> authoritys = authorityService.loadAll();

		if (authoritys.isEmpty()) {
			persistAll();
			authoritys = authorityService.loadAll();
		}

		return authoritys.get(new Random().nextInt(authoritys.size()));
	}

	List<Authority> getAllAsList() {

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
		if (!isPersistable())
			return;

		for (Authority authority : authoritys) {
			authorityService.save(authority);
		}
	}

	/** Will return a random number of PlacedOrders
	 * @return
	 */
	List<Authority> getFew() {
		List<Authority> all = getAllAsList();
		int numToChoose = new Random(1212343).nextInt(all.size() - 1) + 1;

		List allClone = new ArrayList<Authority>();
		List returnList = new ArrayList<Authority>();

		allClone.addAll(all);

		while (returnList.size() < numToChoose) {
			int indexToAdd = new Random(1212343).nextInt(allClone.size());
			returnList.add(allClone.get(indexToAdd));
			allClone.remove(numToChoose);
		}

		return returnList;
	}

}
