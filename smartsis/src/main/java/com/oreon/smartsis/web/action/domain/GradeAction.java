
	
package com.oreon.smartsis.web.action.domain;
	

import java.util.List;

import org.jboss.seam.Component;
import org.jboss.seam.annotations.Name;

import com.oreon.smartsis.domain.ExamScore;
import com.oreon.smartsis.domain.Fee;
import com.oreon.smartsis.domain.GradeFee;
import com.oreon.smartsis.domain.Student;

	
//@Scope(ScopeType.CONVERSATION)
@Name("gradeAction")
public class GradeAction extends GradeActionBase implements java.io.Serializable{
	
	@Override
	public void addGradeFees() {
	
		super.addGradeFees();
		GradeFee gradeFee = getListGradeFees().get(listGradeFees.size() - 1);
		//gradeFee.setAmount(gradeFee.getFee().getDefaultAmount());
	}
	
	@Override
	void initListGradeFees() {
		if (getInstance().getId() == null && listGradeFees.isEmpty()) {
			
			/*
			GradeSubject gradeSubject = getInstance().getGradeSubject();
			
			if (gradeSubject == null)
				return;*/
			
			FeeListQuery feeListQuery = (FeeListQuery) Component.getInstance("feeList");
			
			List<Fee> fees = feeListQuery.getResultList();
			
			for (Fee fee : fees) {
				GradeFee gradeFee = new GradeFee();
				gradeFee.setFee(fee);
				gradeFee.setAmount(gradeFee.getFee().getDefaultAmount());
				gradeFee.setGrade(instance);
				listGradeFees.add(gradeFee);
			}
			
			
		}
	}
}
	