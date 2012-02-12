package com.hrb.tservices.domain.taxestimator;

public class OntarioTaxValues2011 extends ProvincialTaxValues2011 
{
//    Dim L50 As Double
//    Dim L51 As Double
//    Dim L52_ONSurtax As Double
//    Dim L53 As Double
//    Dim L60_ONTaxReduction As Double
//    Dim L69_ONHealthPremium As Double
	
	protected double L50;
	protected double L51;
	protected double L52_ONSurtax;
	protected double L53;
	protected double L60_ONTaxReduction;
	protected double L69_ONHealthPremium;
	
	public OntarioTaxValues2011(double L5804_Basic, 
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
								double L50,  				//provincial specfic element
								double L51,					//provincial specfic element
								double L52_ONSurtax,		//provincial specfic element
								double L53,					//provincial specfic element
								double L60_ONTaxReduction,	//provincial specfic element
								double L69_ONHealthPremium) //provincial specfic element
	{
		super(	Province.ON,
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
		
				this.L50 = L50;
				this.L51 = L51;
				this.L52_ONSurtax = L52_ONSurtax;
				this.L53 = L53;
				this.L60_ONTaxReduction = L60_ONTaxReduction;
				this.L69_ONHealthPremium = L69_ONHealthPremium;

	}

	public String toString()
	{
		return (new StringBuilder(200)
					.append(province.shortName())
					.append(" [")
					.append(super.toString())
					.append(", L50: ").append(L50)
					.append(", L51: ").append(L51)
					.append(", L52_ONSurtax: ").append(L52_ONSurtax)
					.append(", L53: ").append(L53)
					.append(", L60_ONTaxReduction: ").append(L60_ONTaxReduction)
					.append(", L69_ONHealthPremium: ").append(L69_ONHealthPremium)
					.append("]")).toString();	
		}
	
}
