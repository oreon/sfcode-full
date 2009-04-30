package org.caisi.sessionbeans;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


public class ServiceFacade {
	private static ServiceFacade instance;

	private static ApplicationContext applicationContext;

	static {
		applicationContext = new ClassPathXmlApplicationContext(
				new String[]{"classpath:/persistenceContext.xml","classpath:/applicationContext.xml", 
						"classpath:/securityContext.xml"});
	}

	public static ServiceFacade getInstance() {
		if (instance == null)
			instance = (ServiceFacade) applicationContext
					.getBean("serviceFacade");
		return instance;
	}

	// Construction is disabled
	public ServiceFacade() {
		//getInstance();
	}
	
	private DemographicSessionBean demographicSessionBean;
	
	private ProviderSessionBean providerSessionBean;

	
	@Autowired
	public ProviderSessionBean getProviderSessionBean() {
		return providerSessionBean;
	}

	public void setProviderSessionBean(ProviderSessionBean providerSessionBean) {
		this.providerSessionBean = providerSessionBean;
	}

	@Autowired
	public DemographicSessionBean getDemographicSessionBean() {
		return demographicSessionBean;
	}

	public void setDemographicSessionBean(
			DemographicSessionBean demographicSessionBean) {
		this.demographicSessionBean = demographicSessionBean;
	}

	public Object getBeanByName(String beanName) {
		return applicationContext.getBean(beanName);
	} 
	
}
