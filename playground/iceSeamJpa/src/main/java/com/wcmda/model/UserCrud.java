package com.wcmda.model;

import java.util.List;


import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;

import org.jboss.seam.faces.FacesMessages;
import org.jboss.seam.ui.validator.ModelValidator;

import com.icesoft.faces.component.ext.HtmlInputText;

@Scope(ScopeType.EVENT)
@Name("registerUser")
public class UserCrud {

@In
private User user;

@In
EntityManager entityManager;

@In
private FacesMessages facesMessages;

private String verify;

private boolean registered;

public void register()
{
   if ( user.getPassword().equals(verify) )
   {
      List existing = entityManager.createQuery("select u.username from User u where u.username=#{user.username}")
         .getResultList();
      if (existing.size()==0)
      {
    	  entityManager.persist(user);
         facesMessages.add("Successfully registered as #{user.username}");
         registered = true;
      }
      else
      {
         facesMessages.addToControl("username", "Username #{user.username} already exists");
      }
   }
   else 
   {
      facesMessages.addToControl("verify", "Re-enter your password");
      verify=null;
   }
}

public void validateUserName( FacesContext context, UIComponent validate, Object value)
{
	
	
	
	 org.jboss.seam.ui.validator.ModelValidator mv = new ModelValidator();
	 mv.validate(context,validate, value);
	 
	 String userName = (String)value;
	 
	 if(isUserNameRegistered(userName))
	 {
		 FacesMessage msg = new FacesMessage("user name already taken, please use another one");	 
		 context.addMessage(validate.getClientId(context), msg);
	 }
	 
	
	   HtmlInputText inputText = (HtmlInputText)validate;
	   inputText.setFocus(true);

}


public boolean isUserNameRegistered(String userName) {
		return entityManager.createQuery(
			"select u.username from User u where u.username= ?1")
			.setParameter(1, userName).getResultList().size() > 0;
	}


public void invalid()
{
   facesMessages.add("Please try again");
}

public boolean isRegistered()
{
   return registered;
}
public String getVerify()
{
   return verify;
}
public void setVerify(String verify)
{
   this.verify = verify;
}




	
}
