package oaw4.demo.classic.uml.meta;

import org.openarchitectureware.core.meta.core.ElementSet;
import org.openarchitectureware.meta.uml.classifier.Attribute;

public class Entity extends AbstractEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// nothing to do in the simplest case
	
	private String defaultRole;
	private boolean isUser;
	
	public String getDefaultRole() {
		return defaultRole;
	}
	public void setDefaultRole(String defaultRole) {
		this.defaultRole = defaultRole;
	}
	public boolean isUser() {
		return isUser;
	}
	public void setUser(boolean isUser) {
		this.isUser = isUser;
	}
	
	
}