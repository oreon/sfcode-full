package com.hrb.tservices.domain.taxestimator;

public class NorthwestTerritoriesTaxValues2011 extends ProvincialTaxValues2011 
{
//    Dim L6250_CostOfLivingTaxCredit As Double
//    Dim L5_AdjustedNetIncome As Double
//    Dim L6250_CostOfLivingTaxCredit_SpouseCLP As Double
//    Dim L5_AdjustedNetIncome_SpouseCLP As Double
//    Dim L6249_CostOfLivingSupplement As Double
//    Dim L6251_NorthwestTerritoriesCredit As Double

	protected double L6250_CostOfLivingTaxCredit;
	protected double L5_AdjustedNetIncome;
	protected double L6250_CostOfLivingTaxCredit_SpouseCLP;
	protected double L5_AdjustedNetIncome_SpouseCLP;
	protected double L6249_CostOfLivingSupplement;
	protected double L6251_NorthwestTerritoriesCredit;
	
	public NorthwestTerritoriesTaxValues2011(double L5804_Basic, 
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
								double L6250_CostOfLivingTaxCredit,  			//provincial specfic element
								double L5_AdjustedNetIncome,					//provincial specfic element
								double L6250_CostOfLivingTaxCredit_SpouseCLP,	//provincial specfic element
								double L5_AdjustedNetIncome_SpouseCLP,			//provincial specfic element
								double L6249_CostOfLivingSupplement,			//provincial specfic element
								double L6251_NorthwestTerritoriesCredit) 		//provincial specfic element
	{
		super(	Province.NT,
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
		
				this.L6250_CostOfLivingTaxCredit = L6250_CostOfLivingTaxCredit;
				this.L5_AdjustedNetIncome = L5_AdjustedNetIncome;
				this.L6250_CostOfLivingTaxCredit_SpouseCLP = L6250_CostOfLivingTaxCredit_SpouseCLP;
				this.L5_AdjustedNetIncome_SpouseCLP = L5_AdjustedNetIncome_SpouseCLP;
				this.L6249_CostOfLivingSupplement = L6249_CostOfLivingSupplement;
				this.L6251_NorthwestTerritoriesCredit = L6251_NorthwestTerritoriesCredit;

	}

	public String toString()
	{
		return (new StringBuilder(200)
					.append(province.shortName())
					.append(" [")
					.append(super.toString())
					.append(", L6250_CostOfLivingTaxCredit: ").append(L6250_CostOfLivingTaxCredit)
					.append(", L5_AdjustedNetIncome: ").append(L5_AdjustedNetIncome)
					.append(", L6250_CostOfLivingTaxCredit_SpouseCLP: ").append(L6250_CostOfLivingTaxCredit_SpouseCLP)
					.append(", L5_AdjustedNetIncome_SpouseCLP: ").append(L5_AdjustedNetIncome_SpouseCLP)
					.append(", L6249_CostOfLivingSupplement: ").append(L6249_CostOfLivingSupplement)
					.append(", L6251_NorthwestTerritoriesCredit: ").append(L6251_NorthwestTerritoriesCredit)
					.append("]")).toString();	
		}
	

}
