package aero.sita.ads.framework.apis2;

import java.util.Date;



/**
 * @author jsingh
 *
 */
public class PersonalInfo extends AbstractAPISInfoComponent  {

	private static final String PERSONAL_INFO = "Personal Info";
	private String firstName;
	private String lastName;
	private Date dob;
	private int gender;
	
	
	
	public Date getDob() {
		return dob;
	}



	public void setDob(Date dob) {
		this.dob = dob;
	}



	public String getFirstName() {
		return firstName;
	}



	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}



	public int getGender() {
		return gender;
	}



	public void setGender(int gender) {
		this.gender = gender;
	}



	public String getLastName() {
		return lastName;
	}



	public void setLastName(String lastName) {
		this.lastName = lastName;
	}



	public String getPrefix() {
		return PERSONAL_INFO;
	}

}
