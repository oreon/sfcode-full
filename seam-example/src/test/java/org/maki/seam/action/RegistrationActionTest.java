package org.maki.seam.action;

import org.jboss.seam.mock.SeamTest;
import org.testng.annotations.Test;

public class RegistrationActionTest extends SeamTest {

    private static final String PASSWORD = "12345678";

    @Test
    public void checkRegistration() throws Exception {
        new ComponentTest() {
            @Override
            protected void testComponents() throws Exception {
                setValue("#{user.emailAddress}", "chrismaki@mac.com");
                setValue("#{user.firstName}", "chris");
                setValue("#{user.lastName}", "maki");
                setValue("#{user.password}", PASSWORD);
                setValue("#{register.verifyPassword}", PASSWORD);
                invokeMethod("#{register.register}");
                assert getValue("#{register.registrationSuccessful}").equals(true);
            }
            
        }.run();
    }
    
    @Test(dependsOnMethods={ "checkRegistration" })
    public void checkSecondRegistration() throws Exception {
      new ComponentTest() {
        @Override
        protected void testComponents() throws Exception {
          setValue("#{user.emailAddress}", "chrismaki@mac.com");
          setValue("#{user.firstName}", "chris");
          setValue("#{user.lastName}", "maki");
          setValue("#{user.password}", PASSWORD);
          setValue("#{register.verifyPassword}", PASSWORD);
          invokeMethod("#{register.register}");
          assert getValue("#{register.registrationSuccessful}").equals(false);
        }
        
      }.run();
      
    }
}
