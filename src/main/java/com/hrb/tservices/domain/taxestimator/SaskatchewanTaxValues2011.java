package com.hrb.tservices.domain.taxestimator;

public class SaskatchewanTaxValues2011 extends ProvincialTaxValues2011 
{
	protected double L5822_SeniorSupplementary;
	protected double L5821_DependantChildren;
	
	public SaskatchewanTaxValues2011(double L5804_Basic, 
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
								double L5822_SeniorSupplementary,	//provincial specfic element
								double L5821_DependantChildren) 	//provincial specfic element
	{
		super(	Province.SK,
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
		
				this.L5822_SeniorSupplementary = L5822_SeniorSupplementary;
				this.L5821_DependantChildren = L5821_DependantChildren;
	}

	public String toString()
	{
		return (new StringBuilder(200)
					.append(province.shortName())
					.append(" [")
					.append(super.toString())
					.append(", L5822_SeniorSupplementary: ").append(L5822_SeniorSupplementary)
					.append(", L5821_DependantChildren: ").append(L5821_DependantChildren)
					.append("]")).toString();	
	}
}
