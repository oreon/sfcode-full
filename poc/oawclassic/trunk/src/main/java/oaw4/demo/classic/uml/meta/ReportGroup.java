package oaw4.demo.classic.uml.meta;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import oaw4.demo.classic.uml.extend.Constants;

import org.openarchitectureware.core.meta.core.ElementSet;
import org.openarchitectureware.meta.uml.classifier.Attribute;

/** Meant to be used within a Report Task
 * @author jsingh
 *
 */
public class ReportGroup extends Attribute{
	private String summaryFields ;

	public String getSummaryFields() {
		return summaryFields;
	}

	public void setSummaryFields(String summaryFields) {
		this.summaryFields = summaryFields;
	}
	
	/**
	 * @return a set of summary fields obtained by tokenizing on ";"
	 */
	public  ElementSet getSummaryFieldsList(){
		ElementSet fields = new ElementSet();
		
		if(summaryFields == null || summaryFields.length() == 0)
			return fields;
		
		String fldsArray[] = summaryFields.split(Constants.STD_CONCAT);
		for (String field : fldsArray) {
			GroupSummaryField groupSummaryField = new GroupSummaryField(field.trim());
			fields.add(groupSummaryField);
		}
		return fields;
	}
	
	public static void main(String args[]){
		String s = "[wow]";
		
		System.out.println(s);
		ReportGroup grp = new ReportGroup();
		grp.setSummaryFields("Average(age); count");
		ElementSet set = grp.getSummaryFieldsList();
	}
	
	
	
}
