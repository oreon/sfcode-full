package oaw4.demo.classic.uml.meta;

import org.openarchitectureware.meta.uml.classifier.Parameter;

public class ConstrainedParameter extends Parameter {
	
	private Boolean nullable;
	private String constraints;
	
	public Boolean getNullable() {
		return nullable;
	}
	public void setNullable(Boolean nullable) {
		this.nullable = nullable;
	}
	public String getConstraints() {
		return constraints;
	}
	public void setConstraints(String constraints) {
		this.constraints = constraints;
	}
	
	

}
