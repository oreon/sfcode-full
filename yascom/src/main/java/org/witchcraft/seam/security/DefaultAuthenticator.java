/*
 * JBoss, Home of Professional Open Source
 * Copyright 2010, Red Hat Middleware LLC, and individual contributors
 * by the @authors tag. See the copyright.txt in the distribution for a
 * full listing of individual contributors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.witchcraft.seam.security;

import javax.ejb.Stateful;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.inject.Named;

import org.jboss.seam.international.status.Messages;
import org.jboss.seam.security.Authenticator;
import org.jboss.seam.security.BaseAuthenticator;
import org.jboss.seam.security.Credentials;
import org.jboss.solder.logging.Logger;
import org.picketlink.idm.impl.api.PasswordCredential;
import org.picketlink.idm.impl.api.model.SimpleUser;
import org.witchcraft.utils.DefaultBundleKey;

import com.sasktel.om.users.AppUser;
import com.sasktel.om.web.action.users.AppUserAction;

/**
 * This implementation of a <strong>Authenticator</strong> that uses Seam security.
 *
 * @author <a href="http://community.jboss.org/people/spinner)">Jose Rodolfo freitas</a>
 */
@Stateful
@Named
public class DefaultAuthenticator extends BaseAuthenticator implements Authenticator {

    @Inject
    private Logger log;

   // @PersistenceContext
   // private EntityManager em;
    
    @Inject
    AppUserAction appUserAction;

    @Inject
    private Credentials credentials;

    @Inject
    private Messages messages;

    @Inject
    @Authenticated
    private Event<AppUser> loginEventSrc;

    public void authenticate() {
        log.info("Logging in " + credentials.getUsername());
        
        if ((credentials.getUsername() == null) || (credentials.getCredential() == null)) {
            messages.error(new DefaultBundleKey("identity_loginFailed")).defaults("Blank username or password");
            setStatus(AuthenticationStatus.FAILURE);
        }
        
        
        AppUser user = appUserAction.findByUnqUserName(  credentials.getUsername());
        if (user != null && credentials.getCredential() instanceof PasswordCredential && 
            user.getPassword().equals(((PasswordCredential) credentials.getCredential()).getValue())) {
            loginEventSrc.fire(user);
            messages.info(new DefaultBundleKey("identity_loggedIn"), user.getUserName()).defaults("You're signed in as {0}")
                    .params(user.getUserName());
            setStatus(AuthenticationStatus.SUCCESS);
            setUser(new SimpleUser(user.getUserName())); //TODO confirm the need for this set method
            return;
        }

        messages.error(new DefaultBundleKey("identity_loginFailed")).defaults("Invalid username or password");
        setStatus(AuthenticationStatus.FAILURE);

    }

}