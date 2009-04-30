package org.caisi;

import org.caisi.persistence.model.Demographic;
import org.springframework.security.context.SecurityContextHolder;
import org.springframework.security.providers.UsernamePasswordAuthenticationToken;

/**
 * Class to create objects for testing 
 * @author jsingh
 *
 */
public class ObjectMother {
	
	/** will create a demorpahic with minimal non null fields set
	 * @return
	 */
	public static Demographic createMinDemographic() {
		Demographic demographic = new Demographic();
		demographic.setFirstName("eric");
		demographic.setLastName("Segal22");
		demographic.setSex("M");
		return demographic;
	}
	
	public static UsernamePasswordAuthenticationToken createDoctorToken(){
	    return new UsernamePasswordAuthenticationToken("oscar_doc", "password");
	}
	
	public static UsernamePasswordAuthenticationToken createNurseToken(){
	    return new UsernamePasswordAuthenticationToken("bob_nurse", "password");
	}
	
	

}
