package com.hrb.tservices.domain.taxestimator;

public class ProvincialTaxBase 
{
	protected Province province;
	
	public ProvincialTaxBase(Province province)
	{
		super();
		this.province = province;
	}
	
	public Province getProvince()
    {
    	return province;
    }
}
