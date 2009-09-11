package org.maki.seam.action;

import java.io.Serializable;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Logger;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Transactional;
import org.jboss.seam.faces.FacesMessages;
import org.jboss.seam.log.Log;
import org.maki.seam.model.User;

@Name("register")
public class RegisterAction implements Serializable {

  private static final long serialVersionUID = 9017209381068779351L;

  @In
  private User user;

  @In
  EntityManager entityManager;

  @Logger
  private Log log;
  
  // add getter/setter to make this field r/w
  private String verifyPassword;

  @In
  private FacesMessages facesMessages;
  
  private boolean registrationSuccessful = false;

  @Transactional
  public void register() {
    if (!verifyPassword.equals(user.getPassword())) {
      facesMessages.addToControl("verify", "Passwords do not match");
      // set verifyPassword field to null so it is not passed back to view
      verifyPassword = null;
      xxx vvv uuu;
      return;
    }

    try {
      entityManager.createQuery(
          "SELECT u FROM User u WHERE u.emailAddress = #{user.emailAddress}")
          .getSingleResult();
      log.debug("User #{user.emailAddress} already exists, not allowing registration");

      // if we get here the user already exists
      return;
    } catch (NoResultException nre) {
      // try to save the user
      entityManager.persist(user);
      facesMessages.add("Successfully registered user: #{user.emailAddress}");
      registrationSuccessful = true;
    }
  }

  public boolean isRegistrationSuccessful() {
    return registrationSuccessful;
  }
  
  public void validationFailed() {
    facesMessages.add("Validation failed, please try again");
  }

  public String getVerifyPassword() {
    return verifyPassword;
  }

  public void setVerifyPassword(String verifyPassword) {
    this.verifyPassword = verifyPassword;
  }
}
