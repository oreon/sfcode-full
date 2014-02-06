
package com.oreon.cerebrum.web.action.drugs;



import org.jboss.seam.annotations.Name;
	
	
@Name("drugList")
//@Scope(ScopeType.CONVERSATION)
public class DrugListQuery extends DrugListQueryBase implements java.io.Serializable{
	
	public DrugListQuery() {
		setOrder("drug.name");
	}
	
}
