package oaw4.demo.classic.uml.meta;

public class Report extends 
 org.openarchitectureware.meta.uml.classifier.Class{
	
	private String query;
	private String queryLanguage = "ejbql";
	private String fields;
	private String params;
	private String title;
	private String defaultAlignment;
	//private String 
	
	
	//private String 
	public String getQuery() {
		return query;
	}
	public void setQuery(String query) {
		this.query = query;
	}
	public String getFields() {
		return fields;
	}
	public void setFields(String fields) {
		this.fields = fields;
	}
	public String getParams() {
		return params;
	}
	public void setParams(String params) {
		this.params = params;
	}
	public String getQueryLanguage() {
		return queryLanguage;
	}
	public void setQueryLanguage(String queryLanguage) {
		this.queryLanguage = queryLanguage;
	}

}
