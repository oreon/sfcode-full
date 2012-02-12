package com.hrb.tservices.domain.taxestimator;

public enum FilingStatus 
{
	SINGLE("Single"),
	MARRIED("Married"),
	COMMON_LAW("Common-Law Partner");
	
	private String type;
	
	FilingStatus( String type)
    {
        this.type = type;
    }
	
	public String toString()
	{
	   	return type; 
	}
	
	// returns SINGLE if any errors occur
	public static FilingStatus getFilingStatus(String candidate)
	{
		if ( candidate == null )
    	{
    		return SINGLE;
    	}

		if (candidate.equals(MARRIED)) 
    		return MARRIED;
		else if (candidate.equals(COMMON_LAW)) 
    		return COMMON_LAW;
		else 
			return SINGLE;
	}
}
