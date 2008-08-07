package oaw4.demo.classic.uml.meta;

import java.util.Collection;

import oaw4.demo.classic.uml.extend.GenericUtils;

import org.apache.commons.lang.StringUtils;
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
	private boolean repeat = false; 
	
	private String suggestionAction;
	private String fetchValue;
	private String fetchCols;
	
	//There is an issue reading initvalue in Magidraw so we use this property to hold initial 
	//value
	private String defaultValue;
	
	
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
		if(derived!= null && !derived.endsWith(";")){
			return derived + ";";
		}
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
	
	/** This property tells whether the field should be repeated e.g. password
	 * @return
	 */
	public boolean isRepeat() {
		return repeat;
	}
	public void setRepeat(boolean repeat) {
		this.repeat = repeat;
	}
	
	//AutoComplete suggestion fields 
	public String getSuggestionAction() {
		return suggestionAction;
	}
	public void setSuggestionAction(String suggestionFunction) {
		this.suggestionAction = suggestionFunction;
	}
	public String getFetchValue() {
		return fetchValue;
	}
	public void setFetchValue(String fetchValue) {
		this.fetchValue = fetchValue;
	}
	public String getFetchCols() {
		return fetchCols;
	}
	public void setFetchCols(String fetchCols) {
		this.fetchCols = fetchCols;
	} 
	
	public Collection<String> getFectchColsCollection(){
		return GenericUtils.stringArrayAsList(fetchCols);
	}
	public String getDefaultValue() {
		return defaultValue;
	}
	public void setDefaultValue(String defaultValue) {
		this.defaultValue = defaultValue;
	}
	

}
