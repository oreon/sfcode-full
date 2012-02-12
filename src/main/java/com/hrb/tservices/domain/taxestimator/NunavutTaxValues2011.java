package com.hrb.tservices.domain.taxestimator;

public class NunavutTaxValues2011 extends ProvincialTaxValues2011 
{
//    Dim L5823_ChildrenLessThan6 As Double
//    Dim L5_AdjustedNetIncome As Double
//    Dim L8_BasicCredit As Double
//    Dim L6394_CostOfLivingSupplement As Double
//    Dim L6390_TotalCostOfLivingTaxCredit As Double
//    Dim L27_NunavutCredits As Double

	protected double L5823_ChildrenLessThan6;
	protected double L5_AdjustedNetIncome;
	protected double L8_BasicCredit;
	protected double L6394_CostOfLivingSupplement;
	protected double L6390_TotalCostOfLivingTaxCredit;
	protected double L27_NunavutCredits;
	
	public NunavutTaxValues2011(double L5804_Basic, 
								double L5808_Age,
								double L5812_SpouseCLP, 
								double L5816_EligibleDependant,
								double L5824_CPPQPPEmployment, 
								double L5828_CPPQPPSelfEmployment,
								double L5832_EIPremiumsEmployment, 
								double L5836_PensionIncome,
								double L5856_TuitionEducation, 
								double L5868_Medical, 
								double L5876,
								double L5880, 
								double L5884, 
								double L5896_DonationsGifts,
								double L6150_NRTC, 
								double L6152_DividendTaxCredit,
								double Gross_Provincial_Tax, 
								double Net_Provincial_Tax,
								double L5823_ChildrenLessThan6,  			//provincial specfic element
								double L5_AdjustedNetIncome,				//provincial specfic element
								double L8_BasicCredit,						//provincial specfic element
								double L6394_CostOfLivingSupplement,		//provincial specfic element
								double L6390_TotalCostOfLivingTaxCredit,	//provincial specfic element
								double L27_NunavutCredits) 					//provincial specfic element
	{
		super(	Province.NU,
				L5804_Basic, 
				L5808_Age, 
				L5812_SpouseCLP, 
				L5816_EligibleDependant,
				L5824_CPPQPPEmployment, 
				L5828_CPPQPPSelfEmployment,
				L5832_EIPremiumsEmployment, 
				L5836_PensionIncome,
				L5856_TuitionEducation, 
				L5868_Medical, L5876, 
				L5880, L5884,
				L5896_DonationsGifts, 
				L6150_NRTC, 
				L6152_DividendTaxCredit,
				Gross_Provincial_Tax, 
				Net_Provincial_Tax);
		
				this.L5823_ChildrenLessThan6 = L5823_ChildrenLessThan6;
				this.L5_AdjustedNetIncome = L5_AdjustedNetIncome;
				this.L8_BasicCredit = L8_BasicCredit;
				this.L6394_CostOfLivingSupplement = L6394_CostOfLivingSupplement;
				this.L6390_TotalCostOfLivingTaxCredit = L6390_TotalCostOfLivingTaxCredit;
				this.L27_NunavutCredits = L27_NunavutCredits;

	}

	public String toString()
	{
		return (new StringBuilder(200)
					.append(province.shortName())
					.append(" [")
					.append(super.toString())
					.append(", L5823_ChildrenLessThan6: ").append(L5823_ChildrenLessThan6)
					.append(", L5_AdjustedNetIncome: ").append(L5_AdjustedNetIncome)
					.append(", L8_BasicCredit: ").append(L8_BasicCredit)
					.append(", L6394_CostOfLivingSupplement: ").append(L6394_CostOfLivingSupplement)
					.append(", L6390_TotalCostOfLivingTaxCredit: ").append(L6390_TotalCostOfLivingTaxCredit)
					.append(", L27_NunavutCredits: ").append(L27_NunavutCredits)
					.append("]")).toString();	
		}
}
