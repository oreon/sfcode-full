package com.hrb.tservices.domain.taxestimator;

public class PrinceEdwardIslandTaxValues2011 extends ProvincialTaxValues2011 
{
//    Dim L5823_YoungChildren As Double
//    Dim L51_PESurtax As Double
//    Dim L60_AdjustedFamilyIncome As Double
//    Dim L72_LowIncomeTaxReduction As Double
	
	protected double L5823_YoungChildren;
	protected double L51_PESurtax;
	protected double L60_AdjustedFamilyIncome;
	protected double L72_LowIncomeTaxReduction;
	
	public PrinceEdwardIslandTaxValues2011(double L5804_Basic, 
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
								double L5823_YoungChildren,			//provincial specfic element
								double L51_PESurtax,				//provincial specfic element
								double L60_AdjustedFamilyIncome,	//provincial specfic element
								double L72_LowIncomeTaxReduction) 	//provincial specfic element
	{
		super(	Province.PE,
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
		
				this.L5823_YoungChildren = L5823_YoungChildren;
				this.L51_PESurtax = L51_PESurtax;
				this.L60_AdjustedFamilyIncome = L60_AdjustedFamilyIncome;
				this.L72_LowIncomeTaxReduction = L72_LowIncomeTaxReduction;

	}

	public String toString()
	{
		return (new StringBuilder(200)
					.append(province.shortName())
					.append(" [")
					.append(super.toString())
					.append(", L5823_YoungChildren: ").append(L5823_YoungChildren)
					.append(", L51_PESurtax: ").append(L51_PESurtax)
					.append(", L60_AdjustedFamilyIncome: ").append(L60_AdjustedFamilyIncome)
					.append(", L72_LowIncomeTaxReduction: ").append(L72_LowIncomeTaxReduction)
					.append("]")).toString();	
		}
	
}
