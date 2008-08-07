package oaw4.demo.classic.uml.meta;

import org.openarchitectureware.meta.uml.classifier.Association;

/**
 * This class represents an associaton which can be customized using 
 * attributes such as whether it is immutable or it has a defaul value (e.g the 
 * current logged in user will be the default for a looged in employee creating a pruchase 
 * order ). 
 * @author jsingh
 *
 */
public class CustomAssociation extends Association{

	private String defaultValue;
	private boolean mutable = true;
	private String access;
	
	
	public String getAccess() {
		return access;
	}

	public void setAccess(String access) {
		this.access = access;
	}

	public boolean isMutable() {
		
		return mutable;
	}

	public void setMutable(boolean mutable) {
		this.mutable = mutable;
	}
	
	public String getDefaultValue() {
		return defaultValue;
	}

	public void setDefaultValue(String defaultValue) {
		this.defaultValue = defaultValue;
	}
	
	public boolean isValueForLoggedInUser(){
		if(getDefaultValue() == null) 
			return false;
		System.out.println("comparing " + getDefaultValue());
		return getDefaultValue().trim().equalsIgnoreCase("${LOGGED_IN_USER}");
	}
}
