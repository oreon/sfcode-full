package com.hrb.tservices.domain.taxestimator;

public class YukonTerritoriesTaxValues2011 extends ProvincialTaxValues2011 
{
	protected double L5825_Children;
	protected double L5835_CanadaEmployment;
	protected double L58_YTSurtax;
	protected double L61_AdjustedYukonTax;
	protected double L67_AdjustedNetIncome;
	protected double L76_LowIncomeFamilyTaxCredit;
	
	public YukonTerritoriesTaxValues2011(double L5804_Basic, 
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
								double L5825_Children,  				//provincial specfic element
								double L5835_CanadaEmployment,			//provincial specfic element
								double L58_YTSurtax,					//provincial specfic element
								double L61_AdjustedYukonTax,			//provincial specfic element
								double L67_AdjustedNetIncome,			//provincial specfic element
								double L76_LowIncomeFamilyTaxCredit) 	//provincial specfic element
	{
		super(	Province.YT,
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
		
				this.L5825_Children = L5825_Children;
				this.L5835_CanadaEmployment = L5835_CanadaEmployment;
				this.L58_YTSurtax = L58_YTSurtax;
				this.L61_AdjustedYukonTax = L61_AdjustedYukonTax;
				this.L67_AdjustedNetIncome = L67_AdjustedNetIncome;
				this.L76_LowIncomeFamilyTaxCredit = L76_LowIncomeFamilyTaxCredit;

	}

	public String toString()
	{
		return (new StringBuilder(200)
					.append(province.shortName())
					.append(" [")
					.append(super.toString())
					.append(", L5825_Children: ").append(L5825_Children)
					.append(", L5835_CanadaEmployment: ").append(L5835_CanadaEmployment)
					.append(", L58_YTSurtax: ").append(L58_YTSurtax)
					.append(", L61_AdjustedYukonTax: ").append(L61_AdjustedYukonTax)
					.append(", L67_AdjustedNetIncome: ").append(L67_AdjustedNetIncome)
					.append(", L76_LowIncomeFamilyTaxCredit: ").append(L76_LowIncomeFamilyTaxCredit)
					.append("]")).toString();	
	}
}
