package org.witchcraft.action.test;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.witchcraft.seam.action.BaseAction;

import com.shan.customermgt.domain.Customer;
import com.shan.customermgt.domain.action.CustomerAction;

public class TemplateTest extends BaseTest<Customer> {
	
	CustomerAction action = new CustomerAction();
	
	@BeforeClass
	public void init(){
		super.init();
	}
	
	@Override
	public BaseAction<Customer> getAction() {
		return action;
	}

    @Test
    public void testTemplates() throws Exception {
        new ComponentTest() {
            
            protected void testComponents() throws Exception {
            	//setValue("#{patient.}", "chrismaki@mac.com");
                setValue("#{customer.firstName}", "chris");
                setValue("#{customer.lastName}", "maki");
                setValue("#{customerAction.entityTemplate.templateName}", "mytempl");
                invokeMethod("#{customerAction.saveTemplate}");
               // assert getValue("#{register.registrationSuccessful}").equals(false);

            }
            
        }.run();
    }
    
    
}