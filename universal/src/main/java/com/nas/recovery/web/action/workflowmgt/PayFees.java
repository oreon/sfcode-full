package com.nas.recovery.web.action.workflowmgt;

import java.util.List;

import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.bpm.CreateProcess;

import com.nas.recovery.web.action.domain.StudentListQuery;
import com.oreon.tapovan.domain.Student;

@Name("payFeesProcessAction")
// @Scope(ScopeType.CONVERSATION)
public class PayFees extends PayFeesBase {
	


	@CreateProcess(definition = "payFees", processKey = "#{paidFeeToken.id}")
	public void startProcess() {

	}
	
	
}
