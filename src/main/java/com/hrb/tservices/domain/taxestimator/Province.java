package com.hrb.tservices.domain.taxestimator;

public enum Province 
{
	AB( "AB", "Alberta", 				1),
	BC( "BC", "British Columbia",  		2),
	ON( "ON", "Ontario", 				3),
	SK( "SK", "Saskatchewan", 			4),
	MB( "MB", "Manitoba", 				5),
	NB( "NB", "New Brunswick", 			6),
	NS( "NS", "Nova Scotia", 			7),
	NL( "NL", "Newfoundland/Labrador", 	8),
	PE( "PE", "Prince Edward Island", 	9),
	YT( "YT", "Yukon", 					10),
	NU( "NU", "Nunavut", 				11),
	NT( "NT", "North West Territories", 12),
	QC( "QC", "Quebec", 				13);

    private final int id;
    private final String shortName;
    private final String longName;

    public String toString()
	{
    	return longName; 
	}

    public String shortName()
    {
    	return shortName;
    }
    
    public String longName()
    {
    	return longName;
    }
    
    public int id()
    {
    	return id;
    }

    public static Province getProvince(String shortName) throws InvalidException
    {
    	if (shortName == null ) throw new InvalidException("province cannot be null");
    	
    	if (shortName.equals((Province.AB).shortName())) return Province.AB;
    	if (shortName.equals((Province.BC).shortName())) return Province.BC;
    	if (shortName.equals((Province.ON).shortName())) return Province.ON;
    	if (shortName.equals((Province.SK).shortName())) return Province.SK;
    	if (shortName.equals((Province.MB).shortName())) return Province.MB;
    	if (shortName.equals((Province.NB).shortName())) return Province.NB;
    	if (shortName.equals((Province.NS).shortName())) return Province.NS;
    	if (shortName.equals((Province.NL).shortName())) return Province.NL;
    	if (shortName.equals((Province.PE).shortName())) return Province.PE;
    	if (shortName.equals((Province.YT).shortName())) return Province.YT;
    	if (shortName.equals((Province.NU).shortName())) return Province.NU;
    	if (shortName.equals((Province.NT).shortName())) return Province.NT;
    	if (shortName.equals((Province.QC).shortName())) return Province.QC;
    	
    	throw new InvalidException("not a valid province - " + shortName);
    }
    
    Province( String shortName, String longName, int id )
    {
        this.shortName = shortName;
        this.longName = longName;
        this.id = id;
    }
}
