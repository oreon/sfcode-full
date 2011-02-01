package com.nas.recovery.web.action.domain;

import java.util.List;

import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.bpm.ProcessInstance;

import com.nas.recovery.web.action.workflowmgt.PayFees;
import com.oreon.tapovan.domain.PaidFee;
import com.oreon.tapovan.domain.Student;

//@Scope(ScopeType.CONVERSATION)
@Name("paidFeeAction")
public class PaidFeeAction extends PaidFeeActionBase implements
		java.io.Serializable {
	
	@In(create=true)
	StudentListQuery studentListQuery;
	
	@In(create=true, value="payFeesProcessAction")
	PayFees payFees;

	public String createFeePayTasks() {
		List<Student> students = studentListQuery.getResultList();
		for (Student student : students) {
			PaidFee paidFee = new PaidFee();
			paidFee.setStudent(student);
			save(paidFee);
			payFees.setPaidFeeToken(paidFee);
			payFees.startProcess();
			paidFee.setProcessId(ProcessInstance.instance().getId());
			save(paidFee);
		}
		return "success";
	}

	

}
