package com.hrb.tservices.domain.taxestimator;

public class ProvincialTaxValues2011 extends ProvincialTaxBase
{
    protected double L5804_Basic = 0.0;
    protected double L5808_Age = 0.0;
    protected double L5812_SpouseCLP = 0.0;
    protected double L5816_EligibleDependant = 0.0;
    protected double L5824_CPPQPPEmployment = 0.0;
    protected double L5828_CPPQPPSelfEmployment = 0.0;
    protected double L5832_EIPremiumsEmployment = 0.0;
    protected double L5836_PensionIncome = 0.0;
    protected double L5856_TuitionEducation = 0.0;
    protected double L5868_Medical = 0.0;
    protected double L5876 = 0.0;
    protected double L5880 = 0.0;
    protected double L5884 = 0.0;
    protected double L5896_DonationsGifts = 0.0;
    protected double L6150_NRTC = 0.0;
    protected double L6152_DividendTaxCredit = 0.0;
    protected double Gross_Provincial_Tax = 0.0;
    protected double Net_Provincial_Tax = 0.0;

    public ProvincialTaxValues2011( Province province,
    								double L5804_Basic,
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
								    double Net_Provincial_Tax)
    {
        super(province);
    	
    	this.L5804_Basic = L5804_Basic;
        this.L5808_Age = L5808_Age;
        this.L5812_SpouseCLP = L5812_SpouseCLP;
        this.L5816_EligibleDependant = L5816_EligibleDependant;
        this.L5824_CPPQPPEmployment = L5824_CPPQPPEmployment;
        this.L5828_CPPQPPSelfEmployment = L5828_CPPQPPSelfEmployment;
        this.L5832_EIPremiumsEmployment = L5832_EIPremiumsEmployment;
        this.L5836_PensionIncome = L5836_PensionIncome;
        this.L5856_TuitionEducation = L5856_TuitionEducation;
        this.L5868_Medical = L5868_Medical;
        this.L5876 = L5876;
        this.L5880 = L5880;
        this.L5884 = L5884;
        this.L5896_DonationsGifts = L5896_DonationsGifts;
        this.L6150_NRTC = L6150_NRTC;
        this.L6152_DividendTaxCredit = L6152_DividendTaxCredit;
        this.Gross_Provincial_Tax = Gross_Provincial_Tax;
        this.Net_Provincial_Tax = Net_Provincial_Tax;    	
    }
    
    public String toString()
    {
    	StringBuilder buff = new StringBuilder(200);
    	buff.append("L5804_Basic: ").append(L5804_Basic).append(", ")
	    	.append("L5808_Age: ").append(L5808_Age).append(", ")
	    	.append("L5812_SpouseCLP: ").append(L5812_SpouseCLP).append(", ")
	    	.append("L5816_EligibleDependant: ").append(L5816_EligibleDependant).append(", ")
	    	.append("L5824_CPPQPPEmployment: ").append(L5824_CPPQPPEmployment).append(", ")
	    	.append("L5828_CPPQPPSelfEmployment: ").append(L5828_CPPQPPSelfEmployment).append(", ")
	    	.append("L5832_EIPremiumsEmployment: ").append(L5832_EIPremiumsEmployment).append(", ")
	    	.append("L5836_PensionIncome: ").append(L5836_PensionIncome).append(", ")
	    	.append("L5856_TuitionEducation: ").append(L5856_TuitionEducation).append(", ")
	    	.append("L5868_Medical: ").append(L5868_Medical).append(", ")
	    	.append("L5876: ").append(L5876).append(", ")
	    	.append("L5880: ").append(L5880).append(", ")
	    	.append("L5884: ").append(L5884).append(", ")
	    	.append("L5896_DonationsGifts: ").append(L5896_DonationsGifts).append(", ")
	    	.append("L6150_NRTC: ").append(L6150_NRTC).append(", ")
	    	.append("L6152_DividendTaxCredit: ").append(L6152_DividendTaxCredit).append(", ")
	    	.append("Gross_Provincial_Tax: ").append(Gross_Provincial_Tax).append(", ")
	    	.append("Net_Provincial_Tax: ").append(Net_Provincial_Tax);
    	return buff.toString();
    }
}
