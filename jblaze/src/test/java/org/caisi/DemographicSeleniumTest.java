package org.caisi;
import junit.framework.TestCase;

import com.thoughtworks.selenium.DefaultSelenium;
import com.thoughtworks.selenium.Selenium;


public class DemographicSeleniumTest extends TestCase {
    private Selenium browser;
    public void setUp() {
        browser = new DefaultSelenium("localhost",
            4444, "*firefox", "http://localhost:8080/persistence");
        browser.start();
      
    }
    
    public void testSuccessUpdate() {
    	browser.open("http://localhost:8080/persistence/spring_security_login");
    	browser.type("j_username", "oscar_doc");
    	browser.type("j_password", "password");
    	browser.click("submit");
    	browser.waitForPageToLoad("5000");
    	
        browser.open("http://localhost:8080/persistence/index.jsp");
        browser.type("firstName", "XXX");
        browser.type("lastName", "YYY");
        browser.type("sex", "F");
       
        browser.click("update");
        browser.waitForPageToLoad("5000");
        
        String message = browser.getText("//div[@id='infoMessage']");
        //System.out.println("from serv " + message);
        assertTrue( message.contains("successfully saved"));
    }
    
    public void tearDown() {
        browser.stop();
    }
}
