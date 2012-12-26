package org.witchcraft.action.test;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.jboss.seam.security.Identity;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.witchcraft.seam.action.BaseAction;

import com.oreon.cerebrum.drugs.Drug;
import com.oreon.cerebrum.prescription.Frequecy;
import com.oreon.cerebrum.users.AppRole;
import com.oreon.cerebrum.users.AppUser;
import com.oreon.cerebrum.web.action.users.AppUserAction;

public class AuthenticatorTest extends BaseTest<AppUser> {

	AppUserAction action = new AppUserAction();

	@BeforeClass
	public void init() {
		super.init();
	}

	@Override
	public BaseAction<AppUser> getAction() {
		return action;
	}

	@Test(dependsOnMethods = { "testRegisterAction" })
	public void validateAuthenticationBadUser() throws Exception {
		new ComponentTest() {

			protected void testComponents() throws Exception {
				Identity.instance().getCredentials().setUsername("admin");
				Identity.instance().getCredentials().setPassword("admin");

				assert invokeMethod("#{authenticator.authenticate}").equals(
						true);

			}

		}.run();
	}

	@Test
	public void testRegisterAction() throws Exception {
		EntityManager em = getEntityManagerFactory().createEntityManager();
		em.getTransaction().begin();

		Query query = em
				.createQuery("Select u From AppUser u where u.userName = ?1 ");
		query.setParameter(1, "admin");
		if (!query.getResultList().isEmpty())
			return;

		new ComponentTest() {

			protected void testComponents() throws Exception {
				Identity.instance().getCredentials().setUsername("lender");
				Identity.instance().getCredentials().setPassword("lender");

				createUserAndRole("admin", "admin", "admin");
				createUserAndRole("jim", "jim", "support");
				createUserAndRole("roger", "roger", "physician");
				createUserAndRole("erica", "erica", "nurse");

				createDrugs();
				createFrequencies();
			}

		}.run();

		// em.getTransaction().commit();
		em.close();
	}

	private void createFrequencies() {
		if (!em.getTransaction().isActive())
			em.getTransaction().begin();

		createFrequency("oid", 1);
		createFrequency("bid", 2);
		createFrequency("tid", 3);
		// createDrug("Glipizide", "34");
		// createDrug("Metformin", "39");

		em.getTransaction().commit();
	}

	private void createDrugs() {
		if (!em.getTransaction().isActive())
			em.getTransaction().begin();

		createDrug("Ibuprofen", "3");
		createDrug("Methotrexate", "32");
		createDrug("Atorvastatin", "33");
		createDrug("Glipizide", "34");
		createDrug("Metformin", "39");

		createDrug("Captopril", "29");
		createDrug("Hydrocholorothiazide", "219");

		em.getTransaction().commit();
	}

	private void createDrug(String name, String id) {
		Drug ibu = new Drug();
		ibu.setName(name);
		ibu.setDrugBankId(id);
		em.persist(ibu);
	}

	private void createFrequency(String name, int qtyPerDay) {
		Frequecy frequecy = new Frequecy();
		frequecy.setName(name);
		frequecy.setQtyPerDay(qtyPerDay);
		em.persist(frequecy);
	}

	private AppUser createUserAndRole(String username, String password,
			String role) {
		EntityManager em = getEntityManagerFactory().createEntityManager();
		em.getTransaction().begin();

		AppUser admin = new AppUser();
		admin.setUserName(username);
		admin.setPassword(password);

		AppRole adminRole = new AppRole();
		adminRole.setName(role);
		admin.getAppRoles().add(adminRole);
		admin.setEmail(username + "@gmail.com");
		admin.setEnabled(true);

		// setValue("#{userAction.instance}", admin);
		// invokeMethod("#{userAction.save}");
		em.persist(admin);
		em.getTransaction().commit();
		return admin;
	}

}