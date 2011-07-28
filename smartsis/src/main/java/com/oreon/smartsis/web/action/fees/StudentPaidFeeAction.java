
	
package com.oreon.smartsis.web.action.fees;
	

import org.jboss.seam.annotations.Name;

import com.oreon.smartsis.domain.FeeMonth;
import com.oreon.smartsis.domain.Student;
import com.oreon.smartsis.fees.MonthlyFee;

	
//@Scope(ScopeType.CONVERSATION)
@Name("studentPaidFeeAction")
public class StudentPaidFeeAction extends StudentPaidFeeActionBase implements java.io.Serializable{

	private FeeMonth month;
	
	public FeeMonth getMonth() {
		return month;
	}

	public void setMonth(FeeMonth month) {
		this.month = month;
	}

	public void updateFee(){
		updateMonthlyFee();
		System.out.println("amt owed" + getInstance().getAmountOwed());
		//return "success";	
	}
	
	private void updateMonthlyFee(){
		try{
			String qry = "Select mf from MonthlyFee mf where mf.grade.id = ?1 and mf.month = ?2";
			MonthlyFee monthlyFee =  executeSingleResultQuery(qry, getInstance().getStudent().getGrade().getId(), month);
			getInstance().setMonthlyFee(monthlyFee);
		}catch(NullPointerException npe){
			log.error("null pointer trying to update fee");
		}catch(Exception e){
			log.error("an error occured");
		}
	}
	
	//@Override
	public String recieveFee() {
		updateMonthlyFee();
		return super.save();
	}
	
	
	

}
	