package org.cerebrum.domain.action;

import org.jboss.seam.mock.SeamTest;
import org.jboss.seam.security.Identity;
import org.testng.annotations.Test;

public class AuthenticatorTest extends SeamTest {

    @Test
    public void validateAuthenticationBadUser() throws Exception {
        new ComponentTest() {
            @Override
            protected void testComponents() throws Exception {
                Identity.instance().getCredentials().setUsername("admin");
                Identity.instance().getCredentials().setPassword("admin");
                assert invokeMethod(
                        "#{authenticator.authenticate}")
                        .equals(true);
            }
            
        }.run();
    }
}