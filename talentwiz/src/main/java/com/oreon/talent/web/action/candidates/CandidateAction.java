
	
package com.oreon.talent.web.action.candidates;
	

import org.jboss.seam.annotations.Name;

	
//@Scope(ScopeType.CONVERSATION)
@Name("candidateAction")
public class CandidateAction extends CandidateActionBase implements java.io.Serializable{
	
	
	public String register(){
		save();
		return SUCCESS;
	}
	
	public String editProfile(){
		setId(instance.getId());
		save();
		return SUCCESS;
	}
}
	