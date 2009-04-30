package org.caisi;

import org.caisi.persistence.base.ValidationException;
import org.caisi.persistence.base.exceptions.BusinessException;
import org.caisi.persistence.model.Demographic;
import org.caisi.sessionbeans.DemographicSessionBean;
import org.caisi.sessionbeans.ServiceFacade;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.security.AccessDeniedException;
import org.springframework.security.context.SecurityContextHolder;
import org.springframework.test.jpa.AbstractJpaTests;

public class DemographicDaoTest extends AbstractJpaTests {

	// @Autowired
	private DemographicSessionBean demographicSessionBean;

	
	public DemographicSessionBean getDemographicSessionBean() {
		return demographicSessionBean;
	}

	public void setDemographicSessionBean(
			DemographicSessionBean demographicSessionBean) {
		this.demographicSessionBean = demographicSessionBean;
	}

	@Override
	protected String[] getConfigLocations() {
		return new String[] { "classpath:/applicationContext.xml",
				"classpath:/securityContext.xml" , "classpath:/persistenceContext.xml" };
	}
	
	@BeforeClass
	protected void before() {	   
	    SecurityContextHolder.getContext().setAuthentication(ObjectMother.createDoctorToken());
	}
	

	@Test
	public void testSave() {
		before();
		Demographic demographic = ObjectMother.createMinDemographic();
		demographicSessionBean.save(demographic);
		assertNotNull(demographic.getDemographicNo());
		System.out.println(demographic);
	}

	
	
	@Test
	public void testSearch(){
		Demographic demographic = new Demographic();
		demographic.setHcType("ON");
		demographicSessionBean.getSearchByExampleCount(demographic, null);
	}
	
	@Test(expected= AccessDeniedException.class) 
	public void testSaveWithoutPrevilige() {
		try {
		    SecurityContextHolder.getContext().setAuthentication(ObjectMother.createNurseToken());
		    
		    DemographicSessionBean dsb = ServiceFacade.getInstance().getDemographicSessionBean();
			Demographic dem = new Demographic("access","test","M");
			dsb.save(dem);
			fail("should have thrown ade");
		    
			Demographic demographic = ObjectMother.createMinDemographic();
			demographicSessionBean.save(demographic);
			fail("should have thrown ade");
		}catch(BusinessException ade){
			System.out.println("Got an access denined exception ");
			//throw(ade);
		}		
		catch(Exception e){
			e.printStackTrace();
			fail(e.getMessage());
		}finally{
		    SecurityContextHolder.getContext().setAuthentication(ObjectMother.createDoctorToken());
		}
	}
	
	
	@Test(expected= AccessDeniedException.class) 
	public void testLoadWithoutPrevilige() {
		try {
		   
		    Demographic demographic = ObjectMother.createMinDemographic();
		    demographic.setCity("ajax");
			demographic = demographicSessionBean.save(demographic);
			
			 SecurityContextHolder.getContext().setAuthentication(ObjectMother.createNurseToken());
			
			Demographic newDem = demographicSessionBean.load(demographic.getEntityId());
			String city = newDem.getCity();
			
			//fail("should have thrown ade");
			
			
		}catch(BusinessException ade){
			System.out.println("Got an access denined exception accessing city");
			//throw(ade);
		}		
		catch(Exception e){
			e.printStackTrace();
			fail(e.getMessage());
		}finally{
		    SecurityContextHolder.getContext().setAuthentication(ObjectMother.createDoctorToken());
		}
	}

	@Test(expected=ValidationException.class)
	public void testSaveWithNullValues() {
		try {
			Demographic demographic = ObjectMother.createMinDemographic();
			demographic.setFirstName(null);
			demographicSessionBean.save(demographic);

			fail("saving invalid demogrpahic didnt throw ex" + demographic.getDisplayName());
		
		} catch (ValidationException e) {
			System.out.println("EB: saving invalid demogrpahic  threw ex: " + e.getMessage());
		}catch(Exception e){
			fail(e.getMessage());
		}
	}

}



