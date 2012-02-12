package com.hrb.tservices.domain.taxestimator;

public class NewfoundlandTaxValues2011 extends ProvincialTaxValues2011 
{

	protected double NL479_AdjustedFamilyIncome;
	protected double NL479_LowIncomeTaxReduction;
	protected double L5836_ChildCare;
    
	
	public NewfoundlandTaxValues2011(double L5804_Basic, 
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
								double NL479_AdjustedFamilyIncome,		//provincial specfic element
								double NL479_LowIncomeTaxReduction, //provincial specfic element
								double L5836_ChildCare) //provincial specfic element
	{
		super(	Province.NL,
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
		
				this.NL479_AdjustedFamilyIncome = NL479_AdjustedFamilyIncome;
				this.NL479_LowIncomeTaxReduction = NL479_LowIncomeTaxReduction;
				this.L5836_ChildCare = L5836_ChildCare;
	}

	public String toString()
	{
		return (new StringBuilder(200)
					.append(province.shortName())
					.append(" [")
					.append(super.toString())
					.append(", NL479_AdjustedFamilyIncome: ").append(NL479_AdjustedFamilyIncome)
					.append(", NL479_LowIncomeTaxReduction: ").append(NL479_LowIncomeTaxReduction)
					.append(", L5836_ChildCare: ").append(L5836_ChildCare)
					.append("]")).toString();	
	}
}
