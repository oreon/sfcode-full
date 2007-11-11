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
	private String derived; 
	//View properties
	private String validator;
	private String inputType;
	private String access;
	
	private String containerName;
	
	private String testSeed;
	
	public String getTestSeed() {
		return testSeed;
	}
	public void setTestSeed(String testSeed) {
		this.testSeed = testSeed;
	}
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
	/** This property if not null indicates that the attirbute is transient 
	 *  and the java experession for calculating it is provided. 
	 * @return
	 */
	public String getDerived() {
		return derived;
	}
	public void setDerived(String derived) {
		this.derived = derived;
	}
	public String getValidator() {
		return validator;
	}
	public void setValidator(String validator) {
		this.validator = validator;
	}
	public String getInputType() {
		return inputType;
	}
	public void setInputType(String inputType) {
		this.inputType = inputType;
	}
	public String getAccess() {
		return access;
	}
	public void setAccess(String access) {
		this.access = access;
	} 
	

}
