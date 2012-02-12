package org.witchcraft.action.test;

import java.security.MessageDigest;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.jboss.seam.security.Identity;
import org.jboss.seam.util.Base64;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.witchcraft.base.entity.BusinessEntity;
import org.witchcraft.seam.action.BaseAction;

import com.hrb.tservices.domain.department.Partner;
import com.hrb.tservices.domain.message.MarketingMessage;
import com.hrb.tservices.domain.message.MessageTranslation;
import com.hrb.tservices.domain.taxnews.Language;
import com.hrb.tservices.domain.taxnews.NewsCategory;
import com.hrb.tservices.domain.taxnews.TaxNews;
import com.hrb.tservices.domain.users.AppRole;
import com.hrb.tservices.domain.users.AppUser;
import com.hrb.tservices.web.action.users.AppUserAction;
;

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
				Identity.instance().addRole("admin");
	
				
				  assert invokeMethod( "#{authenticator.authenticate}")
				  .equals(true);
				 
			}

		}.run();
	}

	@Test
	public void testRegisterAction() throws Exception {
	
		Query query = em
				.createQuery("Select u From AppUser u where u.userName = ?1 ");
		query.setParameter(1, "admin");
		if (!query.getResultList().isEmpty())
			return;

		new ComponentTest() {

			protected void testComponents() throws Exception {
				Identity.instance().getCredentials().setUsername("admin");
				Identity.instance().getCredentials().setPassword("admin");

				createUserAndRole( "admin", "admin", "admin");
				createUserAndRole( "jim", "jim", "support");
				createUserAndRole( "roger", "roger", "manager");
				createUserAndRole( "erica", "erica", "clerk");
				
				createMessage();
				createPartner();
				createTaxNews();
			}
			
		}.run();

		// em.getTransaction().commit();
		em.close();
	}
	
	private AppUser createUserAndRole(String username, String password, String role) {
		EntityManager em = getEntityManagerFactory().createEntityManager();
		em.getTransaction().begin();
		
		
		AppUser admin = new AppUser();
		admin.setUserName(username);
		try {
			admin.setPassword(getHash(username, password));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		
		}
		
		AppRole adminRole = new AppRole();
		adminRole.setName(role);
		admin.getAppRoles().add(adminRole);
		admin.setEmail(username + "@gmail.com");
		admin.setEnabled(true);
		
		txPersist(admin);
		//em.getTransaction().commit();
		return admin;
	}
	

	public void createPartner(){
		Partner partner = new Partner();
		partner.setName("TD");
		partner.setPartnerId("TD");
		partner.setAppUser(createUserAndRole("td", "tdpass", "partner"));
		partner.setMarketingMessage(em.find(MarketingMessage.class, 1L));
		
		txPersist(partner);
	}
	
	private String getHash(String password, String saltPhrase) throws Exception {
		MessageDigest md = MessageDigest.getInstance("MD5");

		md.update(saltPhrase.getBytes());

		byte[] salt = md.digest();

		md.reset();

		md.update(password.getBytes("UTF-8"));

		md.update(salt);

		byte[] raw = md.digest();

		return new String(Base64.encodeBytes(raw));
	}
	
	public void createMessage(){
		MarketingMessage message = new MarketingMessage();
		message.setMessageTitle("default");
		
		MessageTranslation msgEng = new MessageTranslation();
		msgEng.setButtonText("File My Taxes");
		msgEng.setLanguage(Language.ENGLISH);
		msgEng.setMessage("You can now file your taxes with HR Block today ...");
		msgEng.setHyperLink("hrb.ca/message.asp");
		
		MessageTranslation msgFr = new MessageTranslation();
		msgFr.setButtonText("Fichier mes impôts");
		msgFr.setLanguage(Language.FRENCH);
		msgFr.setMessage("Vous pouvez maintenant déposer vos impôts avec le bloc RH aujourd'hui...");
		msgFr.setHyperLink("hrb.ca/messageFrench.asp");
		
		message.addMessageTranslation(msgFr);
		message.addMessageTranslation(msgEng);
		
		txPersist(message);
	}
	
	private void createTaxNews(){
		NewsCategory category = new NewsCategory();
		category.setName("Seniors ");
		category.setNameFrench("Senor");
		TaxNews news = new TaxNews();
		news.setNewsCategory(category);
		
		news.setTitle("great news for seniors");
		//news.setText("seniors can now enjoy an additional rebate of ...");
	//	news.setLink("hrb.ca/45.asp");
	//	news.setActive(true);
		txPersist(news);
	}

	private void txPersist(BusinessEntity be) {
		if(!em.getTransaction().isActive())
			em.getTransaction().begin();
		em.persist(be);
		em.getTransaction().commit();
	}

}