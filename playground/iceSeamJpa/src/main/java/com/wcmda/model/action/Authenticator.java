package com.wcmda.model.action;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Logger;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Out;
import org.jboss.seam.log.Log;
import org.jboss.seam.security.Identity;

import com.wcmda.model.User;


@Name("authenticator")
public class Authenticator
{
    @Logger Log log;
    
    @In Identity identity;
    
    @In
    EntityManager entityManager;

    @Out(required=false, scope=ScopeType.SESSION)
    User user;
    
    public boolean authenticate()
    {
    	
    	 Query q = entityManager.createQuery("SELECT u FROM User u " +
    	    		"WHERE u.username = #{identity.username} " +
    	    		"AND u.password = #{identity.password}");
    	 
        log.info("authenticating #0", identity.getUsername());
        try {
            user = (User) q.getSingleResult();
            
            return true;
          } catch (NoResultException nre) {
            return false;
          }
    }
}
