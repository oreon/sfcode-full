package com.hrb.tservices.domain.taxestimator;

import java.util.Map;
import java.util.List;

public class TaxPayerValues2011 
{
	public static final String TAX_YEAR_2011 = TaxEstimator2011.TAX_YEAR_2011;
	
    protected String PI_Tax_Year = "2011";                //    '[2011]
    protected Province PI_Province = Province.AB;         //    '[AB] | BC | MB | NB | NL | NS | NT | NU | ON | PE | QC | SK | YT
    protected String PI_Filing_Status = "Single";         //    '[Single] | Married | Common-Law Partner
    protected boolean PI_Over_65 = false;                 //    'True | [False]
//    'Incomes
    protected double IN_Yours_Employment_Income = 0.0;          //    '[0.00] ... 200000.00
    protected double IN_Yours_Tax_Deducted = 0.0;               //    '[0.00] ... 140000.00
    protected double IN_Yours_CPP_Deducted = 0.0;               //    '[0.00] ... 2217.60
    protected double IN_Yours_EI_Deducted = 0.0;                //    '[0.00] ... 786.76
    protected double IN_QC_Taxable_Benefits = 0.0;			   	//    '[0.00] ... 15000.00
    protected double IN_SpouseCLP_Net_Income = 0.0;             //    '[0.00] ... 200000.00
    protected double IN_Other_UCCB = 0.0;                       //    '[0.00] ... 200000.00
    protected double IN_Other_OAS = 0.0;                     	//    '[0.00] ... 200000.00
    protected double IN_Other_CPP = 0.0;                     	//    '[0.00] ... 200000.00
    protected double IN_Other_Pension = 0.0;                    //    '[0.00] ... 200000.00
    protected double IN_Other_RRSP = 0.0;                       //    '[0.00] ... 200000.00
    protected double IN_Other_Other = 0.0;                      //    '[0.00] ... 200000.00
    protected double IN_Investments_Eligible_Dividends = 0.0;   //    '[0.00] ... 100000.00
    protected double IN_Investments_Other_Dividends = 0.0;      //    '[0.00] ... 100000.00
    protected double IN_Investments_Interest = 0.0;             //    '[0.00] ... 100000.00
    protected double IN_Investments_Total_Capital_Gains = 0.0;  //    '[0.00] ... 100000.00
    protected double IN_Business_Net_Income = 0.0;              //    '[0.00] ... 200000.00
    protected double IN_Business_Net_Income_QC = 0.0;			//    '[0.00] ... 200000.00
    protected double IN_Rental_Net_Income = 0.0;                //    '[0.00] ... 100000.00
//    'Donations and Credits
    protected double DC_Donations = 0.0;                        //    '[0.00] ... 100000.00
    protected double DC_Medical = 0.0;                          //    '[0.00] ... 100000.00
    protected double DC_RRSP = 0.0;                             //    '[0.00] ... 50000.00
    protected double DC_RRSP_First60Days = 0.0;                 //    '[0.00] ... 50000.00
    protected double DC_Children = 0;                         //    '[0] | 1 | 2 | 3 | 4 | 5+ (should stored as 5 as value used in multiplications)
//  'Additional check needed: Full Months + Part Months <= 12
  protected double DC_Education_Full_Months = 0;            //    '[0] | 1 | 2 | 3 | 4 | 5 | 6 | 7 | 8 | 9 | 10 | 11 | 12
  protected double DC_Education_Part_Months = 0;            //    '[0] | 1 | 2 | 3 | 4 | 5 | 6 | 7 | 8 | 9 | 10 | 11 | 12        
//  protected int DC_Children = 0;                         //    '[0] | 1 | 2 | 3 | 4 | 5+ (should stored as 5 as value used in multiplications)
////'Additional check needed: Full Months + Part Months <= 12
//protected int DC_Education_Full_Months = 0;            //    '[0] | 1 | 2 | 3 | 4 | 5 | 6 | 7 | 8 | 9 | 10 | 11 | 12
//protected int DC_Education_Part_Months = 0;            //    '[0] | 1 | 2 | 3 | 4 | 5 | 6 | 7 | 8 | 9 | 10 | 11 | 12        
    protected double DC_Education_Tuition_Paid = 0.0;           //    '[0.00] ... 50000.00
    protected double DC_RPP = 0.0;                              //    '[0.00] ... 50000.00
    protected double DC_Union_Dues = 0.0;                       //    '[0.00] ... 10000.00
    protected double DC_Moving_Expenses = 0.0;                  //    '[0.00] ... 50000.00
    protected double DC_Deductions_From_Net_Income = 0.0;       //    '[0.00] ... 50000.00   // replaces double DC_Deductible_Spousal_Support,  
    protected double DC_Canadian_Forces = 0.0;                  //    '[0.00] ... 50000.00
    protected double DC_Security_Options = 0.0;                 //    '[0.00] ... 50000.00
    protected double DC_Northern_Residents = 0.0;               //    '[0.00] ... 50000.00
    protected double DC_Child_Care = 0.0;						//	  '[0.00] ... 50000.00
    protected double DC_Other_NRTC = 0.0;                       // 	  '[0.00] ... 50000.00


    public TaxPayerValues2011(  String PI_Tax_Year,                
					    	    Province PI_Province,                  
					    	    String PI_Filing_Status,         
					    	    boolean PI_Over_65,                 
					    	    double IN_Yours_Employment_Income,          
					    	    double IN_Yours_Tax_Deducted,               
					    	    double IN_Yours_CPP_Deducted,               
					    	    double IN_Yours_EI_Deducted,
					    	    double IN_QC_Taxable_Benefits,
					    	    double IN_SpouseCLP_Net_Income,             
					    	    double IN_Other_UCCB,                       
					    	    double IN_Other_OAS,                     
					    	    double IN_Other_CPP,                     
					    	    double IN_Other_Pension,                    
					    	    double IN_Other_RRSP,                       
					    	    double IN_Other_Other,                      
					    	    double IN_Investments_Eligible_Dividends,   
					    	    double IN_Investments_Other_Dividends,      
					    	    double IN_Investments_Interest,             
					    	    double IN_Investments_Total_Capital_Gains,  
					    	    double IN_Business_Net_Income, 
					    	    double IN_Business_Net_Income_QC,
					    	    double IN_Rental_Net_Income,                
					    	    double DC_Donations,                        
					    	    double DC_Medical,                          
					    	    double DC_RRSP,                             
					    	    double DC_RRSP_First60Days,                 
 					    	    double DC_Children,       				//	 int DC_Children,                  
					    	    double DC_Education_Full_Months,           //	 int DC_Education_Full_Months, 
					    	    double DC_Education_Part_Months,         //	 int DC_Education_Part_Months,           
					    	    double DC_Education_Tuition_Paid,           
					    	    double DC_RPP,                              
					    	    double DC_Union_Dues,                       
					    	    double DC_Moving_Expenses,                  
					    	    double DC_Deductions_From_Net_Income,  
					    	    double DC_Canadian_Forces,                  
					    	    double DC_Security_Options,                 
					    	    double DC_Northern_Residents,               
					    	    double DC_Child_Care,
					    	    double DC_Other_NRTC)						

    {
	    this.PI_Tax_Year = PI_Tax_Year;                
	    this.PI_Province = PI_Province;                  
	    this.PI_Filing_Status = PI_Filing_Status;         
	    this.PI_Over_65 = PI_Over_65;                 
	    this.IN_Yours_Employment_Income = IN_Yours_Employment_Income;          
	    this.IN_Yours_Tax_Deducted = IN_Yours_Tax_Deducted;               
	    this.IN_Yours_CPP_Deducted = IN_Yours_CPP_Deducted;               
	    this.IN_Yours_EI_Deducted = IN_Yours_EI_Deducted;     
	    this.IN_QC_Taxable_Benefits = IN_QC_Taxable_Benefits;
	    this.IN_SpouseCLP_Net_Income = IN_SpouseCLP_Net_Income;             
	    this.IN_Other_UCCB = IN_Other_UCCB;                       
	    this.IN_Other_OAS = IN_Other_OAS;                     
	    this.IN_Other_CPP = IN_Other_CPP;                     
	    this.IN_Other_Pension = IN_Other_Pension;                    
	    this.IN_Other_RRSP = IN_Other_RRSP;                       
	    this.IN_Other_Other = IN_Other_Other;                      
	    this.IN_Investments_Eligible_Dividends = IN_Investments_Eligible_Dividends;   
	    this.IN_Investments_Other_Dividends = IN_Investments_Other_Dividends;      
	    this.IN_Investments_Interest = IN_Investments_Interest;             
	    this.IN_Investments_Total_Capital_Gains = IN_Investments_Total_Capital_Gains;  
	    this.IN_Business_Net_Income = IN_Business_Net_Income;              
	    this.IN_Business_Net_Income_QC = IN_Business_Net_Income_QC;
	    this.DC_Donations = DC_Donations;                        
	    this.DC_Medical = DC_Medical;                          
	    this.DC_RRSP = DC_RRSP;                             
	    this.DC_RRSP_First60Days = DC_RRSP_First60Days;                 
	    this.DC_Children = DC_Children;                         
	    this.DC_Education_Full_Months = DC_Education_Full_Months;            
	    this.DC_Education_Part_Months = DC_Education_Part_Months;                    
	    this.DC_Education_Tuition_Paid = DC_Education_Tuition_Paid;           
	    this.DC_RPP = DC_RPP;                              
	    this.DC_Union_Dues = DC_Union_Dues;                       
	    this.DC_Moving_Expenses = DC_Moving_Expenses;                  
	    this.DC_Deductions_From_Net_Income = DC_Deductions_From_Net_Income;       
	    this.DC_Canadian_Forces = DC_Canadian_Forces;                  
	    this.DC_Security_Options = DC_Security_Options;                 
	    this.DC_Northern_Residents = DC_Northern_Residents;               
	    this.DC_Child_Care = DC_Child_Care;
        this.DC_Other_NRTC = DC_Other_NRTC;                     
    }
    
    public String toString()
    {
    	StringBuilder buff = new StringBuilder(500);
	    buff.append("PI [")
		    .append("PI_Tax_Year: ").append(PI_Tax_Year).append(", ")                
		    .append("PI_Province: ").append(PI_Province).append(", ")                  
		    .append("PI_Filing_Status: ").append(PI_Filing_Status).append(", ")         
		    .append("PI_Over_65: ").append(PI_Over_65).append(", ")                 
		    .append("IN_Yours_Employment_Income: ").append(IN_Yours_Employment_Income).append(", ")          
		    .append("IN_Yours_Tax_Deducted: ").append(IN_Yours_Tax_Deducted).append(", ")               
		    .append("IN_Yours_CPP_Deducted: ").append(IN_Yours_CPP_Deducted).append(", ")               
		    .append("IN_Yours_EI_Deducted: ").append(IN_Yours_EI_Deducted).append(", ")   
		    .append("IN_QC_Taxable_Benefits: ").append(IN_QC_Taxable_Benefits).append(", ")   
		    .append("IN_SpouseCLP_Net_Income: ").append(IN_SpouseCLP_Net_Income).append(", ")             
		    .append("IN_Other_UCCB: ").append(IN_Other_UCCB).append(", ")                       
		    .append("IN_Other_OAS: ").append(IN_Other_OAS).append(", ")                     
		    .append("IN_Other_CPP: ").append(IN_Other_CPP).append(", ")                     
		    .append("IN_Other_Pension: ").append(IN_Other_Pension).append(", ")                    
		    .append("IN_Other_RRSP: ").append(IN_Other_RRSP).append(", ")                       
		    .append("IN_Other_Other: ").append(IN_Other_Other).append(", ")                      
		    .append("IN_Investments_Eligible_Dividends: ").append(IN_Investments_Eligible_Dividends).append(", ")   
		    .append("IN_Investments_Other_Dividends: ").append(IN_Investments_Other_Dividends).append(", ")      
		    .append("IN_Investments_Interest: ").append(IN_Investments_Interest).append(", ")             
		    .append("IN_Investments_Total_Capital_Gains: ").append(IN_Investments_Total_Capital_Gains).append(", ")  
		    .append("IN_Business_Net_Income: ").append(IN_Business_Net_Income).append(", ")  
		    .append("IN_Business_Net_Income_QC: ").append(IN_Business_Net_Income_QC).append(", ")  
		    .append("IN_Rental_Net_Income: ").append(IN_Rental_Net_Income).append(", ")                
		    .append("DC_Donations: ").append(DC_Donations).append(", ")                        
		    .append("DC_Medical: ").append(DC_Medical).append(", ")                          
		    .append("DC_RRSP: ").append(DC_RRSP).append(", ")                             
		    .append("DC_RRSP_First60Days: ").append(DC_RRSP_First60Days).append(", ")                 
		    .append("DC_Children: ").append(DC_Children).append(", ")                         
		    .append("DC_Education_Full_Months: ").append(DC_Education_Full_Months).append(", ")            
		    .append("DC_Education_Part_Months: ").append(DC_Education_Part_Months).append(", ")                    
		    .append("DC_Education_Tuition_Paid: ").append(DC_Education_Tuition_Paid).append(", ")           
		    .append("DC_RPP: ").append(DC_RPP).append(", ")                              
		    .append("DC_Union_Dues: ").append(DC_Union_Dues).append(", ")                       
		    .append("DC_Moving_Expenses: ").append(DC_Moving_Expenses).append(", ")                  
		    .append("DC_Deductions_From_Net_Income: ").append(DC_Deductions_From_Net_Income).append(", ")       
		    .append("DC_Canadian_Forces: ").append(DC_Canadian_Forces).append(", ")                  
		    .append("DC_Security_Options: ").append(DC_Security_Options).append(", ")                 
		    .append("DC_Northern_Residents: ").append(DC_Northern_Residents).append(", ")               
		    .append("DC_Child_Care: ").append(DC_Child_Care).append(" ]")
	    	.append("DC_Other_NRTC: ").append(DC_Other_NRTC).append(" ]");
	    
    	return buff.toString();
    }
    
    public static double parseDouble(String key, String candidate, float floor, float ceiling, List<String> errorMessages) 
    		throws InvalidException
    {
    	double result = 0.0;
    	if (candidate != null)
    	{
    		try
    		{
    			result = Double.parseDouble(candidate);
    		}
    		catch(NumberFormatException nfe)
    		{
    			String msg = (new StringBuilder(40)
								.append(key)
								.append(" cannot be parsed from ")
								.append(candidate)
								.append(", setting to ")
								.append(floor))
									.toString();
    			errorMessages.add(msg);
    			throw new InvalidException(msg);
    		}
    		
    		if (result < floor) 
    		{
    			result = floor;
    			errorMessages.add((new StringBuilder(40)
    							.append(key)
    							.append(" cannot be less than ")
    							.append(floor)
    							.append(", setting to ")
    							.append(floor))
    								.toString());
    		}
    		if (result > ceiling) 
    		{
    			result = ceiling;
    			errorMessages.add((new StringBuilder(40)
    							.append(key)
    							.append(" cannot be greater than ")
    							.append(ceiling)
    							.append(", setting to: ")
    							.append(ceiling))
    								.toString());
    		}
    	}
    	return result;
    }
    
    
    // parses and validates TaxPayerValues for 2011
    // two throwables exit this method
    // 1. TaxExceptionList - which contains messages that can bbe processed later.  this is not a critical stop
    // 2. InvalidException - which contains a message wich represents a critical stop
    public static TaxPayerValues2011 parseAndValidateTaxPayerValues2011(Map<String, String> rawValues, 
    																	List<String> errorMessages) 
    																				throws 	TaxExceptionList, 
    																						InvalidException
    {
    	// two Critrical Errors to stop
    	// if province is bad or tax year is not 2011
    	Province PI_Province = Province.getProvince(rawValues.get("PI_Province"));  // may throw InvalidException
    	
    	String PI_Tax_Year = rawValues.get("PI_Tax_Year");
    	if (PI_Tax_Year == null) throw new InvalidException("tax year cannot be null");
    	if (!PI_Tax_Year.equals(TAX_YEAR_2011)) throw new InvalidException("tax year must be 2011");
    	
    	String PI_Filing_Status = rawValues.get("PI_Filing_Status");
    	if ( PI_Filing_Status == null )
    	{
    		PI_Filing_Status = TaxEstimator2011.SINGLE;
    	}

    	boolean single = PI_Filing_Status.equals(TaxEstimator2011.SINGLE);
    	boolean married = PI_Filing_Status.equals(TaxEstimator2011.MARRIED);
    	boolean common_law = PI_Filing_Status.equals(TaxEstimator2011.COMMON_LAW);
    	
    	if ( !single || !married || !common_law)
    	{
    		errorMessages.add(((
    					new StringBuilder(100)
								.append("PI_Filing_Status is incorrect at '")
								.append(PI_Filing_Status)
								.append("', it must be ")
								.append(TaxEstimator2011.SINGLE)
								.append(", or ")
								.append(TaxEstimator2011.MARRIED))
								.append(", or ")
								.append(TaxEstimator2011.COMMON_LAW)
								.append(" - setting PI_Filing_Status to: ")
								.append(TaxEstimator2011.SINGLE)).toString());
    		PI_Filing_Status = TaxEstimator2011.SINGLE;
    	}
    	
    	
    	
//this.PI_Over_65 = PI_Over_65;                 
//this.IN_Yours_Employment_Income = IN_Yours_Employment_Income;          
//this.IN_Yours_Tax_Deducted = IN_Yours_Tax_Deducted;               
//this.IN_Yours_CPP_Deducted = IN_Yours_CPP_Deducted;               
//this.IN_Yours_EI_Deducted = IN_Yours_EI_Deducted;     
//this.IN_QC_Taxable_Benefits = IN_QC_Taxable_Benefits;
//this.IN_SpouseCLP_Net_Income = IN_SpouseCLP_Net_Income;             
//this.IN_Other_UCCB = IN_Other_UCCB;                       
//this.IN_Other_OAS = IN_Other_OAS;                     
//this.IN_Other_CPP = IN_Other_CPP;                     
//this.IN_Other_Pension = IN_Other_Pension;                    
//this.IN_Other_RRSP = IN_Other_RRSP;                       
//this.IN_Other_Other = IN_Other_Other;                      
//this.IN_Investments_Eligible_Dividends = IN_Investments_Eligible_Dividends;   
//this.IN_Investments_Other_Dividends = IN_Investments_Other_Dividends;      
//this.IN_Investments_Interest = IN_Investments_Interest;             
//this.IN_Investments_Total_Capital_Gains = IN_Investments_Total_Capital_Gains;  
//this.IN_Business_Net_Income = IN_Business_Net_Income;              
//this.IN_Business_Net_Income_QC = IN_Business_Net_Income_QC;
//this.DC_Donations = DC_Donations;                        
//this.DC_Medical = DC_Medical;                          
//this.DC_RRSP = DC_RRSP;                             
//this.DC_RRSP_First60Days = DC_RRSP_First60Days;                 
//this.DC_Children = DC_Children;                         
//this.DC_Education_Full_Months = DC_Education_Full_Months;            
//this.DC_Education_Part_Months = DC_Education_Part_Months;                    
//this.DC_Education_Tuition_Paid = DC_Education_Tuition_Paid;           
//this.DC_RPP = DC_RPP;                              
//this.DC_Union_Dues = DC_Union_Dues;                       
//this.DC_Moving_Expenses = DC_Moving_Expenses;                  
//this.DC_Deductions_From_Net_Income = DC_Deductions_From_Net_Income;       
//this.DC_Canadian_Forces = DC_Canadian_Forces;                  
//this.DC_Security_Options = DC_Security_Options;                 
//this.DC_Northern_Residents = DC_Northern_Residents;               
//this.DC_Child_Care = DC_Child_Care;
//this.DC_Other_NRTC = DC_Other_NRTC;     						
//    	}
//    	catch(InvalidException ie)
//    	{
//    		
//    	}
    	
    	return null;
    }
    
}
