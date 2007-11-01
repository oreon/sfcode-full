package oaw4.demo.classic.uml.meta;

import oaw4.demo.classic.uml.extend.GenericUtils;

import org.openarchitectureware.core.meta.core.Element;

/** Represents a group summary field
 * @author jsingh
 *
 */
public class GroupSummaryField extends Element{
	private String operation; //e.g Average, sum etc 
	private String variable; // name of the variable
	private boolean isCount = false;
	
	/** Constructor to create a groupsummaryfield from a string 
	 * @param field e.g Average(age) or count 
	 */
	public GroupSummaryField(String field) {
		field = field.replace(')', ' ');
		field = field.trim();
		String arr[] = field.split("\\(");
		if(arr.length == 2){
			operation = arr[0];
			variable = arr[1];
		}else if(field.equalsIgnoreCase("count")){
			operation = field.toUpperCase();
			variable = field.toUpperCase();
			isCount = true;
		}else
			throw new RuntimeException(
					"Field must be of type - count or Operation(field)");
		
	}
	
	public String getOperation() {
		return operation;
	}
	public void setOperation(String operation) {
		this.operation = operation;
	}
	public String getVariable() {
		return variable;
	}
	public void setVariable(String variable) {
		this.variable = variable;
	}
	
	public String getMessage(){
		if(isCount)
			return operation;
		else	
			return operation + " " + variable;  
	}
	
	public String getOperationName(){
		if(isCount)
			return operation;
		else	
			return operation + "_" + GenericUtils.toFirstUpper(variable); 
		
	}

}
