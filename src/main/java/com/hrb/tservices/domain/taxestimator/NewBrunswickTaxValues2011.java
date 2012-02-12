package com.hrb.tservices.domain.taxestimator;

public class NewBrunswickTaxValues2011 extends ProvincialTaxValues2011 
{
	protected double NB479_AdjustedFamilyIncome;
	protected double NB479_LowIncomeTaxReduction;
	
	public NewBrunswickTaxValues2011(double L5804_Basic, 
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
								double NB479_AdjustedFamilyIncome,  //New Brunswick  specfic element
								double NB479_LowIncomeTaxReduction) 	//New Brunswick specfic element
	{
		super(	Province.NB,
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
		
				this.NB479_AdjustedFamilyIncome = NB479_AdjustedFamilyIncome;
				this.NB479_LowIncomeTaxReduction = NB479_LowIncomeTaxReduction;
	}

	public String toString()
	{
		return (new StringBuilder(200)
					.append(province.shortName())
					.append(" [")
					.append(super.toString())
					.append(", NB479_AdjustedFamilyIncome: ").append(NB479_AdjustedFamilyIncome)
					.append(", NB479_LowIncomeTaxReduction: ").append(NB479_LowIncomeTaxReduction)
					.append("]")).toString();	
		}
 }
