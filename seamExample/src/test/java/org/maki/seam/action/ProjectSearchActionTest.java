package org.maki.seam.action;

import javax.faces.model.DataModel;

import org.jboss.seam.contexts.Contexts;
import org.jboss.seam.mock.SeamTest;
import org.jboss.seam.security.Identity;
import org.testng.annotations.Test;

public class ProjectSearchActionTest extends SeamTest {
  
  private static final String EMAIL_ADDRESS = "chrismaki@mac.com";
  private static final String PASSWORD = "12345678";
  private static final String SEARCH_STRING = "seam project";
  @Test
  public void login() throws Exception {
    new FacesRequest() {

      @Override
      protected void invokeApplication() throws Exception {
        assert getValue("#{identity.loggedIn}").equals(false);
        setValue("#{user.emailAddress}", EMAIL_ADDRESS);
        setValue("#{user.firstName}", "chris");
        setValue("#{user.lastName}", "maki");
        setValue("#{user.password}", PASSWORD);
        setValue("#{register.verifyPassword}", PASSWORD);
        invokeAction("#{register.register}");
        Identity.instance().setUsername(EMAIL_ADDRESS);
        Identity.instance().setPassword(PASSWORD);
        invokeAction("#{authenticator.authenticate}");
        assert getValue("#{identity.loggedIn}").equals(true);
      }
      
    }.run();
    
    new FacesRequest("/main.xhtml") {

      @Override
      protected void updateModelValues() throws Exception {
        setValue("#{projectSearch.searchCriteria}", SEARCH_STRING);
        super.updateModelValues();
      }

      @Override
      protected void invokeApplication() throws Exception {
        invokeMethod("#{projectSearch.findProjects}");
      }

      @Override
      protected void renderResponse() throws Exception {
        DataModel objects = (DataModel)Contexts.getSessionContext().get("projects");
        assert objects != null;
        assert getValue("#{projectSearch.searchCriteria}").equals(SEARCH_STRING);
      }
    }.run();
  }

  @Test
  public void parameterTest() throws Exception {
    ProjectSearchAction psa = new ProjectSearchAction();
    psa.setSearchCriteria("UJUG");
    assert psa.getSearchPattern().equals("%ujug%");
  }
  @Test
  public void parameterSubstitutionTest() throws Exception {
    ProjectSearchAction psa = new ProjectSearchAction();
    psa.setSearchCriteria("UJUG*");
    assert psa.getSearchPattern().equals("%ujug%%");
  }
  @Test
  public void parameterSubstitutionPercentTest() throws Exception {
    ProjectSearchAction psa = new ProjectSearchAction();
    psa.setSearchCriteria("UJUG%");
    assert psa.getSearchPattern().equals("%ujug%%");
  }
}
