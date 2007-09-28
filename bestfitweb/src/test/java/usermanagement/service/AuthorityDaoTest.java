package usermanagement.service;

import usermanagement.Authority;
import org.springframework.test.jpa.AbstractJpaTests;
import java.util.List;

public class AuthorityDaoTest extends AbstractJpaTests {

	private AuthorityService authorityService;

	public void setAuthorityService(AuthorityService authorityService) {
		this.authorityService = authorityService;
	}

	@Override
	protected String[] getConfigLocations() {
		return new String[]{"classpath:/applicationContext.xml"};
	}

	/**
	 * Do the setup before the test in this method
	 **/
	protected void onSetUpInTransaction() throws Exception {

	}

	public void testSave() {
		//test saving a new record and updating an existing record;
	}

	public void testDelete() {
		//return false;
	}

	public void testLoad() {
		//return null;
	}

	public void testSearchByExample() {
		Authority authority = new Authority();
		//authority.setFirstName("Eri");
		List<Authority> authoritys = authorityService
				.searchByExample(authority);
		assertTrue(!authoritys.isEmpty());
	}

}
