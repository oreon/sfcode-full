package org.maki.seam.extensions;

import org.testng.annotations.Test;

import org.jboss.seam.mock.SeamTest;
import org.jboss.seam.security.Identity;

public class AuthenticatorTest extends SeamTest {

    @Test
    public void validateAuthenticationBadUser() throws Exception {
        new ComponentTest() {
            @Override
            protected void testComponents() throws Exception {
                Identity.instance().setUsername("cmaki@foo.com");
                Identity.instance().setPassword("12345678");
                assert invokeMethod(
                        "#{authenticator.authenticate}")
                        .equals(false);
            }
            
        }.run();
    }
//    @Test
//    public void validateAuthenticationGoodUser() throws Exception {
//        new ComponentTest() {
//            @Override
//            protected void testComponents() throws Exception {
//                Identity.instance().setUsername("cmaki@foo.com");
//                Identity.instance().setPassword("12345678");
//                assert invokeMethod(
//                "#{authenticator.authenticate}")
//                .equals(true);
//            }
//            
//        }.run();
//    }
}
