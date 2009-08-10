package org.witchcraft.action.test;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.jboss.seam.security.Identity;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.witchcraft.seam.action.BaseAction;
import org.witchcraft.users.Role;
import org.witchcraft.users.User;
import org.witchcraft.users.action.UserAction;

public class TemplateTest extends BaseTest<User> {
	
	UserAction action = new UserAction();
	
	@BeforeClass
	public void init(){
		super.init();
	}
	
	@Override
	public BaseAction<User> getAction() {
		return action;
	}

    @Test
    public void testTemplates() throws Exception {
        new ComponentTest() {
            
            protected void testComponents() throws Exception {
            	//setValue("#{patient.}", "chrismaki@mac.com");
                setValue("#{patient.firstName}", "chris");
                setValue("#{patient.lastName}", "maki");
                setValue("#{patientAction.entityTemplate.templateName}", "mytempl");
                invokeMethod("#{patientAction.saveTemplate}");
               // assert getValue("#{register.registrationSuccessful}").equals(false);

            }
            
        }.run();
    }
    
    
}