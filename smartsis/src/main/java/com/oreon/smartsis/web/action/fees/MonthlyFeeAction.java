package com.oreon.smartsis.web.action.fees;

import org.jboss.seam.annotations.Name;

import com.oreon.smartsis.domain.FeeMonth;
import com.oreon.smartsis.fees.GradeFeeItem;
import com.oreon.smartsis.fees.MonthlyFee;

//@Scope(ScopeType.CONVERSATION)
@Name("monthlyFeeAction")
public class MonthlyFeeAction extends MonthlyFeeActionBase implements
		java.io.Serializable {
	
	
	@Override
	public String save() {
		String result = super.save();
		
		FeeMonth[] feeMonths = FeeMonth.values();
		
		for(GradeFeeItem gradeFeeItem: listGradeFeeItems){
			if(gradeFeeItem.getApplyToAllMonths()){
				
				for (FeeMonth feeMonth : feeMonths) {
					
					if(!feeMonth.equals( getInstance().getMonth() ) ){
						
						GradeFeeItem aGradeFeeItem = new GradeFeeItem();
						MonthlyFee monthlyfee = new MonthlyFee();
						monthlyfee.setGrade(getInstance().getGrade());
						monthlyfee.setMonth(feeMonth);
						aGradeFeeItem.setFeeItem(gradeFeeItem.getFeeItem());
						aGradeFeeItem.setAmount(gradeFeeItem.getAmount());
						monthlyfee.addGradeFeeItems(aGradeFeeItem);
						persist(monthlyfee);
					}
				}
				
			}
		}
		return result;
	}
	
	

}
