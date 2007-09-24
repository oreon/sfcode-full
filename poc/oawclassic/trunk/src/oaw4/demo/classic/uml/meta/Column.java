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
	private int minLength;
	private boolean searchable;
	
	
	private String containerName;
	
	public boolean isSearchable() {
		return searchable;
	}
	public void setSearchable(boolean searchable) {
		this.searchable = searchable;
	}
	public int getMinLength() {
		return minLength;
	}
	public void setMinLength(int minLength) {
		this.minLength = minLength;
	}
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
	public String getContainerName() {
		return containerName;
	}
	public void setContainerName(String containerName) {
		this.containerName = containerName;
	} 
	

}
