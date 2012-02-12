package com.hrb.tservices.domain.taxestimator;

public class FederalTaxValues2011 
{
    protected double Income = 0.0;
    protected double Net_Income = 0.0;
    protected double Taxable_Income = 0.0;
//    'Deduction
    protected double L214_ChildCare = 0.0;
//    'NRTC = Non Refundable Tax Credit
    protected double NRTC_L300_Basic = 0.0;
    protected double NRTC_L301_Age = 0.0;
    protected double NRTC_L303_SpouseCLP = 0.0;
    protected double NRTC_L305_EligibleDependant = 0.0;
    protected double NRTC_L367_ChildrenUnder18 = 0.0;
    protected double NRTC_L308_CPPQPPEmployment = 0.0;
    protected double NRTC_L310_CPPQPPSelfEmployment = 0.0;
    protected double NRTC_L312_EIPremiumsEmployment = 0.0;
    protected double NRTC_L363_CanadaEmployment = 0.0;
    protected double NRTC_L314_PensionIncome = 0.0;
    protected double NRTC_L323_TuitionEducationTextbooks = 0.0;
    protected double NRTC_L332_MedicalExpenses = 0.0;
    protected double NRTC_L335 = 0.0;
    protected double NRTC_L338 = 0.0;
    protected double NRTC_L349_DonationsGifts = 0.0;
    protected double NRTC_L350_Total_Federal_NRTC = 0.0;
    protected double Dividends = 0.0;
    protected double Gross_Federal_Tax = 0.0;
    protected double L420_Net_Federal_Tax = 0.0;
    protected double OAS_Repayment = 0.0;
    
    public FederalTaxValues2011(double Income,
					    	    double Net_Income,
					    	    double Taxable_Income,
					//    	    'Deduction
					    	    double L214_ChildCare,
					//    	    'NRTC = Non Refundable Tax Credit
					    	    double NRTC_L300_Basic,
					    	    double NRTC_L301_Age,
					    	    double NRTC_L303_SpouseCLP,
					    	    double NRTC_L305_EligibleDependant,
					    	    double NRTC_L367_ChildrenUnder18,
					    	    double NRTC_L308_CPPQPPEmployment,
					    	    double NRTC_L310_CPPQPPSelfEmployment,
					    	    double NRTC_L312_EIPremiumsEmployment,
					    	    double NRTC_L363_CanadaEmployment,
					    	    double NRTC_L314_PensionIncome,
					    	    double NRTC_L323_TuitionEducationTextbooks,
					    	    double NRTC_L332_MedicalExpenses,
					    	    double NRTC_L335,
					    	    double NRTC_L338,
					    	    double NRTC_L349_DonationsGifts,
					    	    double NRTC_L350_Total_Federal_NRTC,
					    	    double Dividends,
					    	    double Gross_Federal_Tax,
					    	    double L420_Net_Federal_Tax,
					    	    double OAS_Repayment)
    {
    	     this.Income = Income;
    	     this.Net_Income = Net_Income;
    	     this.Taxable_Income = Taxable_Income;
//    	    'Deduction
    	     this.L214_ChildCare = L214_ChildCare;
//    	    'NRTC = Non Refundable Tax Credit
    	     this.NRTC_L300_Basic = NRTC_L300_Basic;
    	     this.NRTC_L301_Age = NRTC_L301_Age;
    	     this.NRTC_L303_SpouseCLP = NRTC_L303_SpouseCLP;
    	     this.NRTC_L305_EligibleDependant = NRTC_L305_EligibleDependant;
    	     this.NRTC_L367_ChildrenUnder18 = NRTC_L367_ChildrenUnder18;
    	     this.NRTC_L308_CPPQPPEmployment = NRTC_L308_CPPQPPEmployment;
    	     this.NRTC_L310_CPPQPPSelfEmployment = NRTC_L310_CPPQPPSelfEmployment;
    	     this.NRTC_L312_EIPremiumsEmployment = NRTC_L312_EIPremiumsEmployment;
    	     this.NRTC_L363_CanadaEmployment = NRTC_L363_CanadaEmployment;
    	     this.NRTC_L314_PensionIncome = NRTC_L314_PensionIncome;
    	     this.NRTC_L323_TuitionEducationTextbooks = NRTC_L323_TuitionEducationTextbooks;
    	     this.NRTC_L332_MedicalExpenses = NRTC_L332_MedicalExpenses;
    	     this.NRTC_L335 = NRTC_L335;
    	     this.NRTC_L338 = NRTC_L338;
    	     this.NRTC_L349_DonationsGifts = NRTC_L349_DonationsGifts;
    	     this.NRTC_L350_Total_Federal_NRTC = NRTC_L350_Total_Federal_NRTC;
    	     this.Dividends = Dividends;
    	     this.Gross_Federal_Tax = Gross_Federal_Tax;
    	     this.L420_Net_Federal_Tax = L420_Net_Federal_Tax;
    	     this.OAS_Repayment = OAS_Repayment;
    }
    
    public String toString()
    {
    	StringBuilder buff = new StringBuilder(200);
	    buff.append("FED [")
	    	.append("Income: ").append(Income).append(", ")
			.append("Net_Income: ").append(Net_Income).append(", ")
			.append("Taxable_Income: ").append(Taxable_Income).append(", ")
			.append("L214_ChildCare: ").append(L214_ChildCare).append(", ")
			.append("NRTC_L300_Basic: ").append(NRTC_L300_Basic).append(", ")
			.append("NRTC_L301_Age: ").append(NRTC_L301_Age).append(", ")
			.append("NRTC_L303_SpouseCLP: ").append(NRTC_L303_SpouseCLP).append(", ")
			.append("NRTC_L305_EligibleDependant: ").append(NRTC_L305_EligibleDependant).append(", ")
			.append("NRTC_L367_ChildrenUnder18: ").append(NRTC_L367_ChildrenUnder18).append(", ")
			.append("NRTC_L308_CPPQPPEmployment: ").append(NRTC_L308_CPPQPPEmployment).append(", ")
			.append("NRTC_L310_CPPQPPSelfEmployment: ").append(NRTC_L310_CPPQPPSelfEmployment).append(", ")
			.append("NRTC_L312_EIPremiumsEmployment: ").append(NRTC_L312_EIPremiumsEmployment).append(", ")
			.append("NRTC_L363_CanadaEmployment: ").append(NRTC_L363_CanadaEmployment).append(", ")
			.append("NRTC_L314_PensionIncome: ").append(NRTC_L314_PensionIncome).append(", ")
			.append("NRTC_L323_TuitionEducationTextbooks: ").append(NRTC_L323_TuitionEducationTextbooks).append(", ")
			.append("NRTC_L332_MedicalExpenses: ").append(NRTC_L332_MedicalExpenses).append(", ")
			.append("NRTC_L335: ").append(NRTC_L335).append(", ")
			.append("NRTC_L338: ").append(NRTC_L338).append(", ")
			.append("NRTC_L349_DonationsGifts: ").append(NRTC_L349_DonationsGifts).append(", ")
			.append("NRTC_L350_Total_Federal_NRTC: ").append(NRTC_L350_Total_Federal_NRTC).append(", ")
			.append("Dividends: ").append(Dividends).append(", ")
			.append("Gross_Federal_Tax: ").append(Gross_Federal_Tax).append(", ")
			.append("L420_Net_Federal_Tax: ").append(L420_Net_Federal_Tax).append(", ")
			.append("OAS_Repayment: ").append(OAS_Repayment).append(" ]");

    	return buff.toString();
    }
    
}
