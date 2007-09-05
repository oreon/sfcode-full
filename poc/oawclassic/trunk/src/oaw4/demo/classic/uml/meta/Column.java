package oaw4.demo.classic.uml.meta;

import org.openarchitectureware.meta.uml.classifier.Attribute;

/** Represents a database column 
 * @author jsingh
 *
 */
public class Column extends Attribute{
	
	private boolean unique;
	private boolean nullable;
	private String name;
	private int maxLength;
	
	public int getMaxLength() {
		return maxLength;
	}
	public void setMaxLength(int maxLength) {
		this.maxLength = maxLength;
	}
	public String getName() {
		return name;
	}
	/*
	public void setName(String name) {
		this.name = name;
	}*/
	public boolean isNullable() {
		return nullable;
	}
	public void setNullable(boolean nullable) {
		this.nullable = nullable;
	}
	public boolean isUnique() {
		return unique;
	}
	public void setUnique(boolean unique) {
		this.unique = unique;
	} 
	

}
