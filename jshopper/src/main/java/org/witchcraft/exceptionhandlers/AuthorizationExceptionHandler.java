package org.witchcraft.exceptionhandlers;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject; import javax.ejb.Stateful;

import org.jboss.seam.security.AuthorizationException;
import org.jboss.solder.exception.control.CaughtException;
import org.jboss.solder.exception.control.Handles;
import org.jboss.solder.exception.control.HandlesExceptions;

@HandlesExceptions
public class AuthorizationExceptionHandler {
   @Inject FacesContext facesContext;
   
   
   public void handleAuthorizationException(@Handles CaughtException<AuthorizationException> evt) {
      facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
            "You do not have the necessary permissions to perform that operation", ""));
     // evt.handled();      
   }
}