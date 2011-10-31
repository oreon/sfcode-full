
	
package com.oreon.smartsis.web.action.exam;
	

import org.jboss.seam.annotations.Name;

import com.oreon.smartsis.exam.Choice;
import com.oreon.smartsis.exam.ChoiceIndex;
import com.oreon.smartsis.exam.Question;

	
//@Scope(ScopeType.CONVERSATION)
@Name("questionAction")
public class QuestionAction extends QuestionActionBase implements java.io.Serializable{
	
	public String getChoiceText(Question question, ChoiceIndex ci){
		return ((Choice) question.getChoices().toArray()[ci.ordinal()]).getChoice();
	}
	
}
	