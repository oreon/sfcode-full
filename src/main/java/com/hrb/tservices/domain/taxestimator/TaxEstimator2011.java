package com.hrb.tservices.domain.taxestimator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//import java.util.Iterator;

//import java.io.IOException;
//
//import javax.xml.parsers.DocumentBuilder;
//import javax.xml.parsers.DocumentBuilderFactory;
//import javax.xml.parsers.ParserConfigurationException;
//
//import org.w3c.dom.Document;
//import org.w3c.dom.Element;
//import org.w3c.dom.NodeList;
//import org.xml.sax.SAXException;

public class TaxEstimator2011 
{
	public static final String TAX_YEAR_2011 = "2011";  // maybe an enum?
	
	
	public static final String MARRIED = "Married";
	public static final String SINGLE = "Single";
	public static final String COMMON_LAW = "Common-Law Partner";
	
	public static final String CANNOT_BE_LESS_THAN = " cannot be lower than ";
	public static final String CANNOT_BE_MORE_THAN = " cannot be greater than ";
		
//	private List<String> warningMessages = new ArrayList<String>(100);
//	private List<String> errorMessages = new ArrayList<String>(100);

	//this is a test

    protected void calculateFederalTax(
    		TaxPayerValues2011 personal, 
    		FederalTaxValues2011 fedTax)
    {
	    // 		//        'Calculate Income
    	fedTax.Income += personal.IN_Yours_Employment_Income; 
    	fedTax.Income += personal.IN_Other_UCCB;
    	fedTax.Income += personal.IN_Other_OAS;
    	fedTax.Income += personal.IN_Other_CPP;
    	fedTax.Income += personal.IN_Other_Pension;
    	fedTax.Income += personal.IN_Other_RRSP;
    	fedTax.Income += personal.IN_Other_Other;
    	fedTax.Income += personal.IN_Investments_Eligible_Dividends;
    	fedTax.Income += personal.IN_Investments_Other_Dividends;
    	fedTax.Income += personal.IN_Investments_Interest;
    	fedTax.Income += personal.IN_Investments_Total_Capital_Gains;
    	fedTax.Income += personal.IN_Business_Net_Income;
    	fedTax.Income += personal.IN_Rental_Net_Income;
    	
    	//        //        'Calculate Net Income
    	fedTax.Net_Income += fedTax.Income;
    	fedTax.Net_Income += personal.DC_RRSP;
    	fedTax.Net_Income += personal.DC_RRSP_First60Days;
    	fedTax.Net_Income += personal.DC_RPP;
    	fedTax.Net_Income += personal.DC_Union_Dues;
    	fedTax.Net_Income += personal.DC_Moving_Expenses;
    	fedTax.Net_Income += personal.DC_Deductions_From_Net_Income;
   	
    	//            //        'Child Care
    	if (fedTax.Net_Income < personal.IN_SpouseCLP_Net_Income) 
    		fedTax.L214_ChildCare = personal.DC_Child_Care;
        else
        	fedTax.L214_ChildCare = 0;

    	if (fedTax.Net_Income < personal.IN_SpouseCLP_Net_Income) 
    		fedTax.L214_ChildCare = personal.DC_Child_Care;
        else fedTax.L214_ChildCare = 0;

    	//            //        'Update Net Income with any Child Care amount
    	fedTax.Net_Income = fedTax.Net_Income - fedTax.L214_ChildCare;

    	//            //        'OAS Repayment
        double A = ((fedTax.Net_Income - 67668) * 0.15);
        if (A < 0) A = 0;
        if (A < personal.IN_Other_OAS) 
        	fedTax.OAS_Repayment = A;
        else 
        	fedTax.OAS_Repayment = personal.IN_Other_OAS;
        
        //      	//        'Update Net Income with any OAS Repayment
        fedTax.Net_Income = fedTax.Net_Income - fedTax.OAS_Repayment;
 
        //            //        'Calculate Taxable Income
        fedTax.Taxable_Income = fedTax.Net_Income 
        			- personal.DC_Canadian_Forces 
        			- personal.DC_Security_Options 
        			- personal.DC_Northern_Residents;

        //            //        'L300 Basic credit
        fedTax.NRTC_L300_Basic = 10527;

        //            //        'L301 Age amount
        if (personal.PI_Over_65 = false) 
        	fedTax.NRTC_L301_Age = 0;
        else
        {
        	if (fedTax.Net_Income <= 32961) 
        		fedTax.NRTC_L301_Age = 6537;
        	else
        	{
        		fedTax.NRTC_L301_Age = 6537 - (fedTax.Net_Income - 32961) * 0.15;
                if (fedTax.NRTC_L301_Age < 0) 
                	fedTax.NRTC_L301_Age = 0;
        	}
        }


        //            //        'L303 Spouse or Common-Law Partner amount
		if (personal.PI_Filing_Status.equals(MARRIED) || personal.PI_Filing_Status.equals(COMMON_LAW)) 
		{
			fedTax.NRTC_L303_SpouseCLP = 10527 - personal.IN_SpouseCLP_Net_Income;
			if (fedTax.NRTC_L303_SpouseCLP < 0) 
				fedTax.NRTC_L303_SpouseCLP = 0;
		}
		else
			fedTax.NRTC_L303_SpouseCLP = 0;


		//            //        'L305 Eligible dependant amount
		if (personal.PI_Filing_Status.equals(SINGLE) && (personal.DC_Children > 0))
			fedTax.NRTC_L305_EligibleDependant = 10527;  // Assumes that they have no net income
		else
			fedTax.NRTC_L305_EligibleDependant = 0;

            
      
		//            //        'L367 Children under 18 amount
		fedTax.NRTC_L367_ChildrenUnder18 = personal.DC_Children * 2131;
		//            //        'IGNORE: L306 Infirm dependants

		//            //        'L308 CPP QPP via Employment
		if (personal.IN_Yours_Employment_Income >= 48300) 
			fedTax.NRTC_L308_CPPQPPEmployment = 2217.6;
		else
		{
			fedTax.NRTC_L308_CPPQPPEmployment = (personal.IN_Yours_Employment_Income - 3500) * 0.0495;
		    if (fedTax.NRTC_L308_CPPQPPEmployment < 0) 
		    	fedTax.NRTC_L308_CPPQPPEmployment = 0;
		}


		//      //        'L310 CPP QPP via Self-Employment
		//      //        'Assumes ONLY one of Emp Income OR Business Income
		//      //        'Note that the 9.9% has been halved for < 48300
		if (fedTax.NRTC_L308_CPPQPPEmployment == 0) 
		{
		    if (personal.IN_Business_Net_Income >= 48300) 
		        fedTax.NRTC_L310_CPPQPPSelfEmployment = 4435.2 * 0.5;
		    else if (personal.IN_Business_Net_Income < 48300) 
		    {
		        fedTax.NRTC_L310_CPPQPPSelfEmployment = ((personal.IN_Business_Net_Income - 3500) * 4.95 / 100);
		        if (fedTax.NRTC_L310_CPPQPPSelfEmployment < 0)
		      	  fedTax.NRTC_L310_CPPQPPSelfEmployment = 0;
		    }
		}

		//      //        'Update Net Income
		fedTax.Net_Income = fedTax.Net_Income - fedTax.NRTC_L310_CPPQPPSelfEmployment;		
		
		
	
		//            //        'L312 EI Premiums via Employment
		if (personal.IN_Yours_Employment_Income >= 44200) 
			fedTax.NRTC_L312_EIPremiumsEmployment = 786.76;
		else
			fedTax.NRTC_L312_EIPremiumsEmployment = personal.IN_Yours_Employment_Income * 0.0178;
		
		//		//        'IGNORE: L317 EI Premiums via Self-Employment
		//		//        'IGNORE: L362 Volunteer firefighters//        ' amount
		
		//		//        'L363 Canada Employment
		if (personal.IN_Yours_Employment_Income >= 1065) 
			fedTax.NRTC_L363_CanadaEmployment = 1065;
		else
			fedTax.NRTC_L363_CanadaEmployment = personal.IN_Yours_Employment_Income;

		//            //        'IGNORE: L364 Public transit amount
		//            //        'IGNORE: L365 Children//        's fitness amount
		//            //        'IGNORE: L370 Children//        's arts amount
		//            //        'IGNORE: L369 Home buyers//        ' amount
		//            //        'IGNORE: L313 Adoption expenses

		//            //        'L314 Pension income amount
		if (personal.PI_Over_65 == true) 
		{    
			if (personal.IN_Other_Pension >= 2000) 
				fedTax.NRTC_L314_PensionIncome = 2000;
			else
				fedTax.NRTC_L314_PensionIncome = personal.IN_Other_Pension;
		}
		else
			fedTax.NRTC_L314_PensionIncome = 0;

		//            //        'IGNORE: L315 Caregiver amount
		//            //        'IGNORE: L316 Disability amount (for self)
		//            //        'IGNORE: L318 Disability amount transferred from a dependant
		//            //        'IGNORE: L319 Interest paid on your student loans

		//            //        'L323 Tuition, education, and textbook amounts
		//            //        'Check for total months equal to 12 or under
		//            //        'CFwd ignored but could be added to vDAC_Eduction_Tuition_Paid
		fedTax.NRTC_L323_TuitionEducationTextbooks = personal.DC_Education_Tuition_Paid + (personal.DC_Education_Full_Months * 465) + (personal.DC_Education_Part_Months * 140);

		//            //        'IGNORE: L324 Tuition, education, and textbook amounts transferred from a child
		//            //        'IGNORE: L326 Amounts transferred from your spouse or common-law partner
		
		//            //        'L332 Medical expenses
		//            //        'Verify logic - perhaps break out into L330 and L331
        if ((personal.DC_Medical > 0) && (fedTax.Net_Income >= 68400)) 
        {    
        	fedTax.NRTC_L332_MedicalExpenses = personal.DC_Medical - 2052;
            if (fedTax.NRTC_L332_MedicalExpenses < 0) 
            	fedTax.NRTC_L332_MedicalExpenses = 0;
        }
        else if ((personal.DC_Medical > 0) && (fedTax.Net_Income < 68400)) 
        {
        	fedTax.NRTC_L332_MedicalExpenses = personal.DC_Medical - (0.03 * fedTax.Net_Income);
            if (fedTax.NRTC_L332_MedicalExpenses < 0) 
            	fedTax.NRTC_L332_MedicalExpenses = 0;
        }
        else
        	fedTax.NRTC_L332_MedicalExpenses = 0;

        //            //        'L335
        fedTax.NRTC_L335 = fedTax.NRTC_L300_Basic;
        fedTax.NRTC_L335 += fedTax.NRTC_L300_Basic;
        fedTax.NRTC_L335 += fedTax.NRTC_L301_Age;
        fedTax.NRTC_L335 += fedTax.NRTC_L303_SpouseCLP;
        fedTax.NRTC_L335 += fedTax.NRTC_L305_EligibleDependant;
        fedTax.NRTC_L335 += fedTax.NRTC_L367_ChildrenUnder18;
        fedTax.NRTC_L335 += fedTax.NRTC_L308_CPPQPPEmployment;
        fedTax.NRTC_L335 += fedTax.NRTC_L310_CPPQPPSelfEmployment;
        fedTax.NRTC_L335 += fedTax.NRTC_L312_EIPremiumsEmployment;
        fedTax.NRTC_L335 += fedTax.NRTC_L363_CanadaEmployment;
        fedTax.NRTC_L335 += fedTax.NRTC_L314_PensionIncome;
        fedTax.NRTC_L335 += fedTax.NRTC_L323_TuitionEducationTextbooks;
        fedTax.NRTC_L335 += fedTax.NRTC_L332_MedicalExpenses;
        fedTax.NRTC_L335 += personal.DC_Other_NRTC;  // added as of 2.5.0

        //        //        'L338 Federal non-refundable tax credit rate
        fedTax.NRTC_L338 = fedTax.NRTC_L335 * 0.15;

		//        //        'L349 Donations and gifts
		//        //        'Verify logic later
        if (personal.DC_Donations >= 200) 
        	fedTax.NRTC_L349_DonationsGifts = (200 * 0.15) + ((personal.DC_Donations - 200) * 0.29);
        else
        	fedTax.NRTC_L349_DonationsGifts = personal.DC_Donations * 0.15;

        //        //        'L350 Total federal non-refundable tax credits
        fedTax.NRTC_L350_Total_Federal_NRTC = fedTax.NRTC_L338 + fedTax.NRTC_L349_DonationsGifts;

        //        //        'Dividends ??? L425
        fedTax.Dividends = (personal.IN_Investments_Eligible_Dividends * 16.4354 / 100) + (personal.IN_Investments_Other_Dividends * 13.33 / 100);

		//        //        'Gross Federal Tax
		//        //        '   <= 41544	            15%
		//        //        '   >41544 and <=83088	    22%
		//        //        '   >83088 and <=128800	    26%
		//        //        '   >128800	                29%
        if (fedTax.Taxable_Income <= 41544) 
        	fedTax.Gross_Federal_Tax = fedTax.Taxable_Income * (15 / 100);
        else if ((fedTax.Taxable_Income > 41544) && (fedTax.Taxable_Income <= 83088))
        	fedTax.Gross_Federal_Tax = (41544 * 15 / 100) + ((fedTax.Taxable_Income - 41544) * 22 / 100);
        else if ((fedTax.Taxable_Income > 83088) && (fedTax.Taxable_Income <= 128800))
        	fedTax.Gross_Federal_Tax = (41544 * 15 / 100) 
        		+ ((83088 - 41544) * 22 / 100) 
        		+ ((fedTax.Taxable_Income - 83088) * 26 / 100);
        else if (fedTax.Taxable_Income > 128800)
        	fedTax.Gross_Federal_Tax = (41544 * 15 / 100) 
        		+ ((83088 - 41544) * 22 / 100) 
        		+ ((128800 - 83088) * 26 / 100) 
        		+ ((fedTax.Taxable_Income - 128800) * 29 / 100);
        else
        	fedTax.Gross_Federal_Tax = 0;

        //        //        'Net Federal Tax
        fedTax.L420_Net_Federal_Tax = fedTax.Gross_Federal_Tax - fedTax.NRTC_L350_Total_Federal_NRTC - fedTax.Dividends;
        if (fedTax.L420_Net_Federal_Tax < 0) 
        	fedTax.L420_Net_Federal_Tax = 0;

    }
       
    protected void calculateAlbertaTax(	
    			TaxPayerValues2011 tmpINPUTS,
				AlbertaTaxValues2011 tmpCALC_AB,
				FederalTaxValues2011 tmpCALC_Federal)
    {
    	
		//      //        '----------------------------
		//      //        'Calculates the Alberta taxes
		//      //        '----------------------------

		//            //        'Gross Provincial Tax
        tmpCALC_AB.Gross_Provincial_Tax = 0.1 * tmpCALC_Federal.Taxable_Income;

        //			//        'L5804 Basic Personal Amount
        tmpCALC_AB.L5804_Basic = 16977;

        //			//        'L5808 Age Amount
        if (tmpINPUTS.PI_Over_65 == false) 
            tmpCALC_AB.L5808_Age = 0;
        else
        {
            if (tmpCALC_Federal.Net_Income <= 35217) 
                tmpCALC_AB.L5808_Age = 4731;
            else
            {
            	tmpCALC_AB.L5808_Age = 4731 - (tmpCALC_Federal.Net_Income - 35217) * 0.15;
                if (tmpCALC_AB.L5808_Age < 0) 
                	tmpCALC_AB.L5808_Age = 0;
            }
        }
        
        
        //			//        'L5812 Spouse / CLP
        if ((tmpINPUTS.PI_Filing_Status.equals(MARRIED)) || tmpINPUTS.PI_Filing_Status.equals(COMMON_LAW))
        {   
        	tmpCALC_AB.L5812_SpouseCLP = 16977 - tmpINPUTS.IN_SpouseCLP_Net_Income;
        	if (tmpCALC_AB.L5812_SpouseCLP < 0) 
        		tmpCALC_AB.L5812_SpouseCLP = 0;
        }
        else
            tmpCALC_AB.L5812_SpouseCLP = 0;

        //		//        'L5816 Amount Eligible Dependant
        if (tmpINPUTS.PI_Filing_Status.equals(SINGLE) && (tmpINPUTS.DC_Children > 0))
            tmpCALC_AB.L5816_EligibleDependant = 16977;  ////        'Assume that they have no net income
        else
            tmpCALC_AB.L5816_EligibleDependant = 0;

        //		//        'IGNORE: L5820 Amount for infirm dependants age 18 or older

        //		//        'L5824 CPP or QPP contributions Employment
        tmpCALC_AB.L5824_CPPQPPEmployment = tmpCALC_Federal.NRTC_L308_CPPQPPEmployment;

        //		//        'L5828 CPP or QPP contributions Self-Employed
        tmpCALC_AB.L5828_CPPQPPSelfEmployment = tmpCALC_Federal.NRTC_L310_CPPQPPSelfEmployment;

        //		//        'L5832 Employment Insurance premiums Employment
        tmpCALC_AB.L5832_EIPremiumsEmployment = tmpCALC_Federal.NRTC_L312_EIPremiumsEmployment;

        //		//        'IGNORE: L5829 Employment Insurance premiums Self-Employed
        //		//        'IGNORE: L5833 Adoption expenses

        //		//        'L5836 Pension Income
        if (tmpINPUTS.PI_Over_65 == true) 
        {
            if (tmpINPUTS.IN_Other_Pension >= 1307) 
                tmpCALC_AB.L5836_PensionIncome = 1307;
            else
                tmpCALC_AB.L5836_PensionIncome = tmpINPUTS.IN_Other_Pension;
        }
        else
            tmpCALC_AB.L5836_PensionIncome = 0;

		//            //        'IGNORE: L5840 Caregiver amount
		//            //        'IGNORE: L5844 Disability amount
		//            //        'IGNORE: L5848 Disability amount transferred from a dependant
		//            //        'IGNORE: L5852 Interest paid on your student loans

		//            //        'L5856 Tuition and Education amounts
		//            //        'Tuition, education and textbook amounts
		//            //        'Check for total months equal to 12 or under
		//            //        'CFwd ignored but could be added to ???
        tmpCALC_AB.L5856_TuitionEducation = tmpINPUTS.DC_Education_Tuition_Paid 
        		+ (tmpINPUTS.DC_Education_Full_Months * 660) 
        		+ (tmpINPUTS.DC_Education_Part_Months * 198);

		//            //        'IGNORE: L5860 Tuition and education amounts transferred from a child
		//            //        'IGNORE: L5864 Amounts transferred from your spouse or common-law partner

        //            //        'L5868 Medical (Line 21 of AB428)
        if ((tmpINPUTS.DC_Medical > 0) && (tmpCALC_Federal.Net_Income >= (2194 / 3 * 100))) 
        {
        	tmpCALC_AB.L5868_Medical = tmpINPUTS.DC_Medical - 2194;
        
            if (tmpCALC_AB.L5868_Medical < 0) 
        		tmpCALC_AB.L5868_Medical = 0;
        }
        else if ((tmpINPUTS.DC_Medical > 0) && (tmpCALC_Federal.Net_Income < (2194 / 3 * 100)))
        {
        	tmpCALC_AB.L5868_Medical = tmpINPUTS.DC_Medical - (0.03 * tmpCALC_Federal.Net_Income);
            if (tmpCALC_AB.L5868_Medical < 0) 
            	tmpCALC_AB.L5868_Medical = 0;
        }
        else
            tmpCALC_AB.L5868_Medical = 0;

        //		//        'IGNORE: L5872 Allowable amount of medical expenses for other dependants

        //		//        'L5876 As we ignored L5872, calculation is simplifed
        tmpCALC_AB.L5876 = tmpCALC_AB.L5868_Medical;

        //		//        'L5880
        tmpCALC_AB.L5880 = tmpCALC_AB.L5804_Basic 
        		+ tmpCALC_AB.L5808_Age 
        		+ tmpCALC_AB.L5812_SpouseCLP 
        		+ tmpCALC_AB.L5816_EligibleDependant 
                + tmpCALC_AB.L5824_CPPQPPEmployment 
                + tmpCALC_AB.L5828_CPPQPPSelfEmployment 
                + tmpCALC_AB.L5832_EIPremiumsEmployment 
                + tmpCALC_AB.L5836_PensionIncome 
                + tmpCALC_AB.L5856_TuitionEducation 
                + tmpCALC_AB.L5876
                + tmpINPUTS.DC_Other_NRTC;   // added since 2.5.0


        //		//        'L5884
        tmpCALC_AB.L5884 = tmpCALC_AB.L5880 * 0.1;

        //		//        'L5896 Donations and Gifts
        if (tmpINPUTS.DC_Donations >= 200) 
            tmpCALC_AB.L5896_DonationsGifts = (200 * 0.1) + ((tmpINPUTS.DC_Donations - 200) * 0.21);
        else
            tmpCALC_AB.L5896_DonationsGifts = tmpINPUTS.DC_Donations * 0.1;

        //	//        'IGNORE: L5895 Amount of unclaimed donations and gifts for 2006

        //	//        'L6150 Alberta non-refundable tax credits
        tmpCALC_AB.L6150_NRTC = tmpCALC_AB.L5884 + tmpCALC_AB.L5896_DonationsGifts;

        //	//        'L6152 Dividend Tax Credit
        tmpCALC_AB.L6152_DividendTaxCredit = (tmpINPUTS.IN_Investments_Eligible_Dividends * 10 / 100) 
        		+ (tmpINPUTS.IN_Investments_Other_Dividends * 3.5 / 100);

        //	//        'AB Net Provincial Tax
        tmpCALC_AB.Net_Provincial_Tax = tmpCALC_AB.Gross_Provincial_Tax 
        								- tmpCALC_AB.L6150_NRTC 
        								- tmpCALC_AB.L6152_DividendTaxCredit;
        if (tmpCALC_AB.Net_Provincial_Tax < 0) 
        	tmpCALC_AB.Net_Provincial_Tax = 0;
    }

    protected void calculateBritishColumbiaTax(	
    			TaxPayerValues2011 tmpINPUTS,
    			BritishColumbiaTaxValues2011 tmpCALC_BC,
				FederalTaxValues2011 tmpCALC_Federal)
	{
		//	    //        '-----------------------
		//	    //        'Calculates the BC taxes
		//	    //        '-----------------------
		//	    Private Function Calculation_BC_TY2011(ByVal tmpINPUTS As struct_USER_INPUT_TY2011, ByVal tmpCALC_Federal As struct_CALC_Federal_TY2011) As struct_CALC_BC_TY2011
		//
		//	        Dim tmpCALC_BC As struct_CALC_BC_TY2011

		//        //        'Gross Provincial Tax
		//        //        '   <= 36146	            5.06%
		//        //        '   >36146 and <=72293	    7.7%
		//        //        '   >72293 and <=83001	    10.5%
		//        //        '   >83001 and <=100787	    12.29%
		//        //        '   >100787	                14.7%
        if (tmpCALC_Federal.Taxable_Income <= 36146) 
            tmpCALC_BC.Gross_Provincial_Tax = tmpCALC_Federal.Taxable_Income * (5.06 / 100);
        else if ((tmpCALC_Federal.Taxable_Income > 36146) && (tmpCALC_Federal.Taxable_Income <= 72293)) 
            tmpCALC_BC.Gross_Provincial_Tax = (36146 * 5.06 / 100) + ((tmpCALC_Federal.Taxable_Income - 36146) * 7.7 / 100);
        else if ((tmpCALC_Federal.Taxable_Income > 72293) && (tmpCALC_Federal.Taxable_Income <= 83001))
            tmpCALC_BC.Gross_Provincial_Tax = (36146 * 5.06 / 100) 
              							+ ((72293 - 36146) * 7.7 / 100) 
              							+ ((tmpCALC_Federal.Taxable_Income - 72293) * 10.5 / 100);
        else if ((tmpCALC_Federal.Taxable_Income > 83001) && (tmpCALC_Federal.Taxable_Income <= 100787))
            tmpCALC_BC.Gross_Provincial_Tax = (36146 * 5.06 / 100) 
            	+ ((72293 - 36146) * 7.7 / 100) 
            	+ ((83001 - 72293) * 10.5 / 100) 
            	+ ((tmpCALC_Federal.Taxable_Income - 83001) * 12.29 / 100);
        else if (tmpCALC_Federal.Taxable_Income > 100787)
            tmpCALC_BC.Gross_Provincial_Tax = (36146 * 5.06 / 100) 
    									+ ((72293 - 36146) * 7.7 / 100) 
    									+ ((83001 - 72293) * 10.5 / 100) 
    									+ ((100787 - 83001) * 12.29 / 100) 
    									+ ((tmpCALC_Federal.Taxable_Income - 100787) * 14.7 / 100);
        else
            tmpCALC_BC.Gross_Provincial_Tax = 0;

        ////        'L5804 Basic Personal Amount
        tmpCALC_BC.L5804_Basic = 11088;

        //		//        'L5808 Age Amount
        if (tmpINPUTS.PI_Over_65 == false) 
            tmpCALC_BC.L5808_Age = 0;
        else
        {    
        	if (tmpCALC_Federal.Net_Income <= 31664) 
                tmpCALC_BC.L5808_Age = 4254;
            else
            {    
            	tmpCALC_BC.L5808_Age = 4254 - (tmpCALC_Federal.Net_Income - 31664) * 0.15;
                if (tmpCALC_BC.L5808_Age < 0) 
                	tmpCALC_BC.L5808_Age = 0;
            }
        }

        //		//        'L5812 Spouse / CLP
        if ((tmpINPUTS.PI_Filing_Status.equals(MARRIED)) || (tmpINPUTS.PI_Filing_Status.equals(COMMON_LAW))) 
        {    
        	if (tmpINPUTS.IN_SpouseCLP_Net_Income < 973) 
                tmpCALC_BC.L5812_SpouseCLP = 9730;
            else if ((tmpINPUTS.IN_SpouseCLP_Net_Income >= 973) && (tmpINPUTS.IN_SpouseCLP_Net_Income < 10703))
                tmpCALC_BC.L5812_SpouseCLP = 10703 - tmpINPUTS.IN_SpouseCLP_Net_Income;
            else
                tmpCALC_BC.L5812_SpouseCLP = 0;
            if (tmpCALC_BC.L5812_SpouseCLP < 0)  
            	tmpCALC_BC.L5812_SpouseCLP = 0;
        }
        else
            tmpCALC_BC.L5812_SpouseCLP = 0;

        //	//        'L5816 Amount Eligible Dependant
        if ((tmpINPUTS.PI_Filing_Status.equals(SINGLE)) && (tmpINPUTS.DC_Children > 0))
            tmpCALC_BC.L5816_EligibleDependant = 9730;
            //	//        'Assume that they have no net income
        else
            tmpCALC_BC.L5816_EligibleDependant = 0;

        //		//        'IGNORE: L5820 Amount for infirm dependants age 18 or older

        //		//        'L5824 CPP or QPP contributions Employment
        tmpCALC_BC.L5824_CPPQPPEmployment = tmpCALC_Federal.NRTC_L308_CPPQPPEmployment;

        //		//        'L5828 CPP or QPP contributions Self-Employed
        tmpCALC_BC.L5828_CPPQPPSelfEmployment = tmpCALC_Federal.NRTC_L310_CPPQPPSelfEmployment;

        //		//        'L5832 Employment Insurance premiums Employment
        tmpCALC_BC.L5832_EIPremiumsEmployment = tmpCALC_Federal.NRTC_L312_EIPremiumsEmployment;

        //		//        'IGNORE: L5829 Employment Insurance premiums Self-Employed
        //		//        'IGNORE: L5833 Adoption expenses

        //		//        'L5836 Pension Income
        if (tmpINPUTS.PI_Over_65 == true) 
        {
        	if (tmpINPUTS.IN_Other_Pension >= 1000) 
                tmpCALC_BC.L5836_PensionIncome = 1000;
            else
                tmpCALC_BC.L5836_PensionIncome = tmpINPUTS.IN_Other_Pension;
        }
        else
            tmpCALC_BC.L5836_PensionIncome = 0;

		//        //        'IGNORE: L5840 Caregiver amount
		//        //        'IGNORE: L5844 Disability amount
		//        //        'IGNORE: L5848 Disability amount transferred from a dependant
		//        //        'IGNORE: L5852 Interest paid on your student loans
		
		//        //        'L5856 Tuition and Education amounts
		//        //        'Tuition, education and textbook amounts
		//        //        'Check for total months equal to 12 or under
		//        //        'CFwd ignored but could be added to ???
        tmpCALC_BC.L5856_TuitionEducation = tmpINPUTS.DC_Education_Tuition_Paid 
        		+ (tmpINPUTS.DC_Education_Full_Months * 200) 
        		+ (tmpINPUTS.DC_Education_Part_Months * 60);

		//        //        'IGNORE: L5860 Tuition and education amounts transferred from a child
		//        //        'IGNORE: L5864 Amounts transferred from your spouse or common-law partner

        //		//        'L5868 Medical (Line 19 of BC428)
        if ((tmpINPUTS.DC_Medical > 0) && (tmpCALC_Federal.Net_Income >= (1972 / 3 * 100)))
        {    
        	tmpCALC_BC.L5868_Medical = tmpINPUTS.DC_Medical - 1972;
            if (tmpCALC_BC.L5868_Medical < 0) 
            	tmpCALC_BC.L5868_Medical = 0;
        }
        else if ((tmpINPUTS.DC_Medical > 0) && (tmpCALC_Federal.Net_Income < (1972 / 3 * 100)))
        {    
        	tmpCALC_BC.L5868_Medical = tmpINPUTS.DC_Medical - (0.03 * tmpCALC_Federal.Net_Income);
            if (tmpCALC_BC.L5868_Medical < 0) 
            	tmpCALC_BC.L5868_Medical = 0;
        }
        else
            tmpCALC_BC.L5868_Medical = 0;

        //       //        'IGNORE: L5872 Allowable amount of medical expenses for other dependants

        //		//        'L5876 As we ignored L5872, calculation is simplifed
        tmpCALC_BC.L5876 = tmpCALC_BC.L5868_Medical;

        //		//        'L5880
        tmpCALC_BC.L5880 = tmpCALC_BC.L5804_Basic 
				            + tmpCALC_BC.L5808_Age 
				            + tmpCALC_BC.L5812_SpouseCLP 
				            + tmpCALC_BC.L5816_EligibleDependant 
				            + tmpCALC_BC.L5824_CPPQPPEmployment 
				            + tmpCALC_BC.L5828_CPPQPPSelfEmployment 
				            + tmpCALC_BC.L5832_EIPremiumsEmployment 
				            + tmpCALC_BC.L5836_PensionIncome 
				            + tmpCALC_BC.L5856_TuitionEducation 
				            + tmpCALC_BC.L5876
				            + tmpINPUTS.DC_Other_NRTC;

        //		//        'L5884
        tmpCALC_BC.L5884 = tmpCALC_BC.L5880 * 0.0506;

        //		//        'L5896 Donations and Gifts
        if (tmpINPUTS.DC_Donations >= 200) 
            tmpCALC_BC.L5896_DonationsGifts = (200 * 0.0506) + ((tmpINPUTS.DC_Donations - 200) * 0.147);
        else
            tmpCALC_BC.L5896_DonationsGifts = tmpINPUTS.DC_Donations * 0.0506;

        //	//        'L6150 BC non-refundable tax credits
        tmpCALC_BC.L6150_NRTC = tmpCALC_BC.L5884 + tmpCALC_BC.L5896_DonationsGifts;

        //	//        'L6152 Dividend Tax Credit
        tmpCALC_BC.L6152_DividendTaxCredit = (tmpINPUTS.IN_Investments_Eligible_Dividends * 10.31 / 100) 
        									+ (tmpINPUTS.IN_Investments_Other_Dividends * 3.4 / 100);

        //		//        'BC Tax Reduction
        if (tmpCALC_Federal.Net_Income < 29806) 
        {
        	tmpCALC_BC.L58_BCTaxReduction = 394 - ((tmpCALC_Federal.Net_Income - 17493) * (3.2 / 100));
            if (tmpCALC_BC.L58_BCTaxReduction < 0) 
            	tmpCALC_BC.L58_BCTaxReduction = 0;
        }
        else
            tmpCALC_BC.L58_BCTaxReduction = 0;

        //		//        'BC Net Provincial Tax
        tmpCALC_BC.Net_Provincial_Tax = tmpCALC_BC.Gross_Provincial_Tax 
        							- tmpCALC_BC.L6150_NRTC 
        							- tmpCALC_BC.L6152_DividendTaxCredit 
        							- tmpCALC_BC.L58_BCTaxReduction;
        if (tmpCALC_BC.Net_Provincial_Tax < 0) 
        	tmpCALC_BC.Net_Provincial_Tax = 0;
	}
	
    protected void calculateOntarioTax(	
    			TaxPayerValues2011 tmpINPUTS,
				OntarioTaxValues2011 tmpCALC_ON,
				FederalTaxValues2011 tmpCALC_Federal)
    {
//        Private Function Calculation_ON_TY2011(ByVal tmpINPUTS As struct_USER_INPUT_TY2011, ByVal tmpCALC_Federal As struct_CALC_Federal_TY2011) As struct_CALC_ON_TY2011
//
//        Dim tmpCALC_ON As struct_CALC_ON_TY2011

		//        //        'Gross Provincial Tax
		//        //        '   <= 37774	            5.05%
		//        //        '   >37774 and <=75550	    9.15%
		//        //        '   >75550	                11.16%
        if (tmpCALC_Federal.Taxable_Income <= 37774)
            tmpCALC_ON.Gross_Provincial_Tax = tmpCALC_Federal.Taxable_Income * (5.05 / 100);
        else if ((tmpCALC_Federal.Taxable_Income > 37774) && (tmpCALC_Federal.Taxable_Income <= 75550))
        {    
        	tmpCALC_ON.Gross_Provincial_Tax = (37774 * 5.05 / 100) 
        			+ ((tmpCALC_Federal.Taxable_Income - 37774) * 9.15 / 100);
        }
        else if (tmpCALC_Federal.Taxable_Income > 75550) 
        {    
        	tmpCALC_ON.Gross_Provincial_Tax = (37774 * 5.05 / 100) 
        			+ ((75550 - 37774) * 9.15 / 100) 
        				+ ((tmpCALC_Federal.Taxable_Income - 75550) * 11.16 / 100);
        }
        else
            tmpCALC_ON.Gross_Provincial_Tax = 0;

        //	//        'L5804 Basic Personal Amount
        tmpCALC_ON.L5804_Basic = 9104;

        //	//        'L5808 Age Amount
        if (tmpINPUTS.PI_Over_65 == false) 
            tmpCALC_ON.L5808_Age = 0;
        else
        {    
        	if (tmpCALC_Federal.Net_Income <= 33091) 
                tmpCALC_ON.L5808_Age = 4445;
            else
            {    
            	tmpCALC_ON.L5808_Age = 4445 - (tmpCALC_Federal.Net_Income - 33091) * 0.15;
                if (tmpCALC_ON.L5808_Age < 0) 
                	tmpCALC_ON.L5808_Age = 0;
            }
        }

        //		//        'L5812 Spouse / CLP
        if (tmpINPUTS.PI_Filing_Status.equals(MARRIED) || tmpINPUTS.PI_Filing_Status.equals(COMMON_LAW))
        {
        	if (tmpINPUTS.IN_SpouseCLP_Net_Income < 773) 
                tmpCALC_ON.L5812_SpouseCLP = 7730;
            else if ((tmpINPUTS.IN_SpouseCLP_Net_Income >= 773) && (tmpINPUTS.IN_SpouseCLP_Net_Income < 8503)) 
                tmpCALC_ON.L5812_SpouseCLP = 8503 - tmpINPUTS.IN_SpouseCLP_Net_Income;
            else
                tmpCALC_ON.L5812_SpouseCLP = 0;
            if (tmpCALC_ON.L5812_SpouseCLP < 0) 
            	tmpCALC_ON.L5812_SpouseCLP = 0;
        }
        else
            tmpCALC_ON.L5812_SpouseCLP = 0;
 
        //		//        'L5816 Amount Eligible Dependant
        if (tmpINPUTS.PI_Filing_Status.equals(SINGLE) && (tmpINPUTS.DC_Children > 0))
            tmpCALC_ON.L5816_EligibleDependant = 7730;
            //	//        'Assume that they have no net income
        else
            tmpCALC_ON.L5816_EligibleDependant = 0;

        //		//        'IGNORE: L5820 Amount for infirm dependants age 18 or older

        //		//        'L5824 CPP or QPP contributions Employment
        tmpCALC_ON.L5824_CPPQPPEmployment = tmpCALC_Federal.NRTC_L308_CPPQPPEmployment;

        //		//        'L5828 CPP or QPP contributions Self-Employed
        tmpCALC_ON.L5828_CPPQPPSelfEmployment = tmpCALC_Federal.NRTC_L310_CPPQPPSelfEmployment;

        //		//        'L5832 Employment Insurance premiums Employment
        tmpCALC_ON.L5832_EIPremiumsEmployment = tmpCALC_Federal.NRTC_L312_EIPremiumsEmployment;

		//        //        'IGNORE: L5829 Employment Insurance premiums Self-Employed
		//        //        'IGNORE: L5833 Adoption expenses

        //		//        'L5836 Pension Income
        if (tmpINPUTS.PI_Over_65 == true) 
        {    
        	if (tmpINPUTS.IN_Other_Pension >= 1259)
                tmpCALC_ON.L5836_PensionIncome = 1259;
            else
                tmpCALC_ON.L5836_PensionIncome = tmpINPUTS.IN_Other_Pension;
        }
        else
            tmpCALC_ON.L5836_PensionIncome = 0;

		//        //        'IGNORE: L5840 Caregiver amount
		//        //        'IGNORE: L5844 Disability amount
		//        //        'IGNORE: L5848 Disability amount transferred from a dependant
		//        //        'IGNORE: L5852 Interest paid on your student loans

		//        //        'L5856 Tuition and Education amounts
		//        //        'Tuition, education and textbook amounts
		//        //        'Check for total months equal to 12 or under
		//        //        'CFwd ignored but could be added to ???
        tmpCALC_ON.L5856_TuitionEducation = tmpINPUTS.DC_Education_Tuition_Paid 
        		+ (tmpINPUTS.DC_Education_Full_Months * 490) 
        		+ (tmpINPUTS.DC_Education_Part_Months * 147);

		//        //        'IGNORE: L5860 Tuition and education amounts transferred from a child
		//        //        'IGNORE: L5864 Amounts transferred from your spouse or common-law partner

        //			//        'L5868 Medical (Line 19 of ON428)
        if ((tmpINPUTS.DC_Medical > 0) && (tmpCALC_Federal.Net_Income >= (2061 / 3 * 100))) 
        {    
        	tmpCALC_ON.L5868_Medical = tmpINPUTS.DC_Medical - 2061;
            if (tmpCALC_ON.L5868_Medical < 0) 
            	tmpCALC_ON.L5868_Medical = 0;
        }
        else if ((tmpINPUTS.DC_Medical > 0) && (tmpCALC_Federal.Net_Income < (2061 / 3 * 100)))
        {    
        	tmpCALC_ON.L5868_Medical = tmpINPUTS.DC_Medical - (0.03 * tmpCALC_Federal.Net_Income);
            if (tmpCALC_ON.L5868_Medical < 0) 
            	tmpCALC_ON.L5868_Medical = 0;
        }
        else
            tmpCALC_ON.L5868_Medical = 0;

        //		//        'IGNORE: L5872 Allowable amount of medical expenses for other dependants

        //		//        'L5876 As we ignored L5872, calculation is simplifed
        tmpCALC_ON.L5876 = tmpCALC_ON.L5868_Medical;

        //		//        'L5880
        tmpCALC_ON.L5880 = tmpCALC_ON.L5804_Basic 
						+ tmpCALC_ON.L5808_Age 
						+ tmpCALC_ON.L5812_SpouseCLP 
						+ tmpCALC_ON.L5816_EligibleDependant 
						+ tmpCALC_ON.L5824_CPPQPPEmployment 
						+ tmpCALC_ON.L5828_CPPQPPSelfEmployment 
						+ tmpCALC_ON.L5832_EIPremiumsEmployment 
						+ tmpCALC_ON.L5836_PensionIncome 
						+ tmpCALC_ON.L5856_TuitionEducation 
						+ tmpCALC_ON.L5876
        				+ tmpINPUTS.DC_Other_NRTC;

        //		//        'L5884
        tmpCALC_ON.L5884 = tmpCALC_ON.L5880 * 0.0505;

        //		//        'L5896 Donations and Gifts
        if (tmpINPUTS.DC_Donations >= 200) 
            tmpCALC_ON.L5896_DonationsGifts = (200 * 0.0505) + ((tmpINPUTS.DC_Donations - 200) * 0.1116);
        else
            tmpCALC_ON.L5896_DonationsGifts = tmpINPUTS.DC_Donations * 0.0505;

        //		//        'L6150 ON non-refundable tax credits
        tmpCALC_ON.L6150_NRTC = tmpCALC_ON.L5884 + tmpCALC_ON.L5896_DonationsGifts;

        //		//        'L6152 Dividend Tax Credit
        tmpCALC_ON.L6152_DividendTaxCredit = (tmpINPUTS.IN_Investments_Eligible_Dividends * 6.4 / 100) 
        									+ (tmpINPUTS.IN_Investments_Other_Dividends * 4.5 / 100);

        //		//        'ON Surtax
        tmpCALC_ON.Net_Provincial_Tax = tmpCALC_ON.Gross_Provincial_Tax 
        							- tmpCALC_ON.L6150_NRTC 
        							- tmpCALC_ON.L6152_DividendTaxCredit;
        if (tmpCALC_ON.Net_Provincial_Tax < 0) 
        	tmpCALC_ON.Net_Provincial_Tax = 0;
        tmpCALC_ON.L50 = (tmpCALC_ON.Net_Provincial_Tax - 4078) * 0.2;
        if (tmpCALC_ON.L50 < 0) 
        	tmpCALC_ON.L50 = 0;
        tmpCALC_ON.L51 = (tmpCALC_ON.Net_Provincial_Tax - 5219) * 0.36;
        if (tmpCALC_ON.L51 < 0) 
        	tmpCALC_ON.L51 = 0;
        tmpCALC_ON.L52_ONSurtax = tmpCALC_ON.L50 + tmpCALC_ON.L51;
        tmpCALC_ON.L53 = tmpCALC_ON.Net_Provincial_Tax + tmpCALC_ON.L52_ONSurtax;

        //		//        'ON Tax Reduction
        if (tmpINPUTS.IN_SpouseCLP_Net_Income > tmpCALC_Federal.Net_Income)
            tmpCALC_ON.L60_ONTaxReduction = 0;
        else
        {   
        	// //        'Ignores L6097
            tmpCALC_ON.L60_ONTaxReduction = (((210) + (389 * tmpINPUTS.DC_Children)) * 2) - tmpCALC_ON.L53;
            if (tmpCALC_ON.L60_ONTaxReduction < 0) 
            	tmpCALC_ON.L60_ONTaxReduction = 0;
        }
        
        //		//        'L69 Ontario Health Premium
        if (tmpCALC_Federal.Taxable_Income <= 20000) 
            tmpCALC_ON.L69_ONHealthPremium = 0;
        else if ((tmpCALC_Federal.Taxable_Income > 20000) && (tmpCALC_Federal.Taxable_Income <= 25000))
            tmpCALC_ON.L69_ONHealthPremium = (tmpCALC_Federal.Taxable_Income - 20000) * 0.06;
        else if ((tmpCALC_Federal.Taxable_Income > 25000) && (tmpCALC_Federal.Taxable_Income <= 36000))
            tmpCALC_ON.L69_ONHealthPremium = 300;
        else if ((tmpCALC_Federal.Taxable_Income > 36000) && (tmpCALC_Federal.Taxable_Income <= 38500))
            tmpCALC_ON.L69_ONHealthPremium = ((tmpCALC_Federal.Taxable_Income - 36000) * 0.06) + 300;
        else if ((tmpCALC_Federal.Taxable_Income > 38500) && (tmpCALC_Federal.Taxable_Income <= 48000))
            tmpCALC_ON.L69_ONHealthPremium = 450;
        else if ((tmpCALC_Federal.Taxable_Income > 48000) && (tmpCALC_Federal.Taxable_Income <= 48600))
            tmpCALC_ON.L69_ONHealthPremium = ((tmpCALC_Federal.Taxable_Income - 48000) * 0.25) + 450;
        else if ((tmpCALC_Federal.Taxable_Income > 48600) && (tmpCALC_Federal.Taxable_Income <= 72000))
            tmpCALC_ON.L69_ONHealthPremium = 600;
        else if ((tmpCALC_Federal.Taxable_Income > 72000) && (tmpCALC_Federal.Taxable_Income <= 72600))
            tmpCALC_ON.L69_ONHealthPremium = ((tmpCALC_Federal.Taxable_Income - 72000) * 0.25) + 600;
        else if ((tmpCALC_Federal.Taxable_Income > 72600) && (tmpCALC_Federal.Taxable_Income <= 200000))
            tmpCALC_ON.L69_ONHealthPremium = 750;
        else if ((tmpCALC_Federal.Taxable_Income > 200000) && (tmpCALC_Federal.Taxable_Income <= 200600))
            tmpCALC_ON.L69_ONHealthPremium = ((tmpCALC_Federal.Taxable_Income - 200000) * 0.25) + 750;
        else if (tmpCALC_Federal.Taxable_Income > 200600)
            tmpCALC_ON.L69_ONHealthPremium = 900;

        //		//        'ON Net Provincial Tax
        tmpCALC_ON.Net_Provincial_Tax = (((tmpCALC_ON.Gross_Provincial_Tax 
        							- (tmpCALC_ON.L6150_NRTC 
        							+ tmpCALC_ON.L6152_DividendTaxCredit)) 
        							+ tmpCALC_ON.L52_ONSurtax) 
        							- tmpCALC_ON.L60_ONTaxReduction);
        if (tmpCALC_ON.Net_Provincial_Tax < 0) 
        	tmpCALC_ON.Net_Provincial_Tax = 0;
        tmpCALC_ON.Net_Provincial_Tax = tmpCALC_ON.Net_Provincial_Tax 
        							+ tmpCALC_ON.L69_ONHealthPremium;

//        Calculation_ON_TY2011 = tmpCALC_ON
//
//    End Function
    }
    
    protected void calculateSaskatchewanTax(	
    			TaxPayerValues2011 tmpINPUTS,
				SaskatchewanTaxValues2011 tmpCALC_SK,
				FederalTaxValues2011 tmpCALC_Federal)
    {
//        Private Function Calculation_SK_TY2011(ByVal tmpINPUTS As struct_USER_INPUT_TY2011, ByVal tmpCALC_Federal As struct_CALC_Federal_TY2011) As struct_CALC_SK_TY2011
//
//        Dim tmpCALC_SK As struct_CALC_SK_TY2011

		//        //        'Gross Provincial Tax
		//        //        '   <= 40919	            11%
		//        //        '   >40919 and <=116911	    13%
		//        //        '   >116911	                15%
        if (tmpCALC_Federal.Taxable_Income <= 40919) 
            tmpCALC_SK.Gross_Provincial_Tax = tmpCALC_Federal.Taxable_Income * (11 / 100);
        else if ((tmpCALC_Federal.Taxable_Income > 40919) && (tmpCALC_Federal.Taxable_Income <= 116911))
        {    
        	tmpCALC_SK.Gross_Provincial_Tax = (40919 * 11 / 100) 
        			+ ((tmpCALC_Federal.Taxable_Income - 40919) * 13 / 100);
        }
        else if (tmpCALC_Federal.Taxable_Income > 116911)
            tmpCALC_SK.Gross_Provincial_Tax = (40919 * 11 / 100) 
            	+ ((116911 - 40919) * 13 / 100) 
            	+ ((tmpCALC_Federal.Taxable_Income - 116911) * 15 / 100);
        else
            tmpCALC_SK.Gross_Provincial_Tax = 0;

        //		//        'L5804 Basic Personal Amount
        tmpCALC_SK.L5804_Basic = 14535;

        //		//        'L5808 Age Amount
        if (tmpINPUTS.PI_Over_65 == false) 
            tmpCALC_SK.L5808_Age = 0;
        else
        {    
        	if (tmpCALC_Federal.Net_Income <= 32961)
                tmpCALC_SK.L5808_Age = 4428;
            else
            {
            	tmpCALC_SK.L5808_Age = 4428 - (tmpCALC_Federal.Net_Income - 32961) * 0.15;
                if (tmpCALC_SK.L5808_Age < 0) 
                	tmpCALC_SK.L5808_Age = 0;
            }
        }

        //		//        'L5812 Spouse / CLP
        if (tmpINPUTS.PI_Filing_Status.equals(MARRIED) || tmpINPUTS.PI_Filing_Status.equals(COMMON_LAW))
        {    
        	tmpCALC_SK.L5812_SpouseCLP = 15989 - tmpINPUTS.IN_SpouseCLP_Net_Income;
            if (tmpCALC_SK.L5812_SpouseCLP < 0) 
            	tmpCALC_SK.L5812_SpouseCLP = 0;
            //	//        'CHECK: Following line may be an easier calculation than what we implement for BC and ON
            if (tmpCALC_SK.L5812_SpouseCLP > 14535) 
            	tmpCALC_SK.L5812_SpouseCLP = 14535;
        }
        else
            tmpCALC_SK.L5812_SpouseCLP = 0;

        //		//        'L5816 Amount Eligible Dependant
        if ((tmpINPUTS.PI_Filing_Status.equals(SINGLE)) && (tmpINPUTS.DC_Children > 0)) 
            tmpCALC_SK.L5816_EligibleDependant = 14535;
            ////        'Assume that they have no net income
        else
            tmpCALC_SK.L5816_EligibleDependant = 0;

        //		//        'IGNORE: L5820 Amount for infirm dependants age 18 or older

        //		//        'L5821 Dependant Children
        tmpCALC_SK.L5821_DependantChildren = tmpINPUTS.DC_Children * 5514;

        //		//        'L5822 Senior supplementary amount
        if (tmpINPUTS.PI_Over_65 == true)
            tmpCALC_SK.L5822_SeniorSupplementary = 1169;
 
        //		//        'L5824 CPP or QPP contributions Employment
        tmpCALC_SK.L5824_CPPQPPEmployment = tmpCALC_Federal.NRTC_L308_CPPQPPEmployment;

        //		//        'L5828 CPP or QPP contributions Self-Employed
        tmpCALC_SK.L5828_CPPQPPSelfEmployment = tmpCALC_Federal.NRTC_L310_CPPQPPSelfEmployment;

        //		//        'L5832 Employment Insurance premiums Employment
        tmpCALC_SK.L5832_EIPremiumsEmployment = tmpCALC_Federal.NRTC_L312_EIPremiumsEmployment;

        //		//        'IGNORE: L5829 Employment Insurance premiums Self-Employed

        //		//        'L5836 Pension Income
        if (tmpINPUTS.PI_Over_65 == true) 
        {    
        	if (tmpINPUTS.IN_Other_Pension >= 1000) 
                tmpCALC_SK.L5836_PensionIncome = 1000;
            else
                tmpCALC_SK.L5836_PensionIncome = tmpINPUTS.IN_Other_Pension;
        }
        else
            tmpCALC_SK.L5836_PensionIncome = 0;

		//        //        'IGNORE: L5840 Caregiver amount
		//        //        'IGNORE: L5844 Disability amount
		//        //        'IGNORE: L5848 Disability amount transferred from a dependant
		//        //        'IGNORE: L5852 Interest paid on your student loans

		//        //        'L5856 Tuition and Education amounts
		//        //        'Tuition, education and textbook amounts
		//        //        'Check for total months equal to 12 or under
		//        //        'CFwd ignored but could be added to ???
        tmpCALC_SK.L5856_TuitionEducation = tmpINPUTS.DC_Education_Tuition_Paid 
        		+ (tmpINPUTS.DC_Education_Full_Months * 400) 
        		+ (tmpINPUTS.DC_Education_Part_Months * 120);

		//        //        'IGNORE: L5860 Tuition and education amounts transferred from a child
		//        //        'IGNORE: L5864 Amounts transferred from your spouse or common-law partner

        //			//        'L5868 Medical (Line 21 of SK428)
        if ((tmpINPUTS.DC_Medical > 0) && (tmpCALC_Federal.Net_Income >= (2052 / 3 * 100)))
        {
        	tmpCALC_SK.L5868_Medical = tmpINPUTS.DC_Medical - 2052;
            if (tmpCALC_SK.L5868_Medical < 0) 
            	tmpCALC_SK.L5868_Medical = 0;
        }
        else if ((tmpINPUTS.DC_Medical > 0) && (tmpCALC_Federal.Net_Income < (2052 / 3 * 100)))
        {
        	tmpCALC_SK.L5868_Medical = tmpINPUTS.DC_Medical - (0.03 * tmpCALC_Federal.Net_Income);
            if (tmpCALC_SK.L5868_Medical < 0) 
            	tmpCALC_SK.L5868_Medical = 0;
        }
        else
            tmpCALC_SK.L5868_Medical = 0;

        //		//        'IGNORE: L5872 Allowable amount of medical expenses for other dependants

        //		//        'L5876 As we ignored L5872, calculation is simplifed
        tmpCALC_SK.L5876 = tmpCALC_SK.L5868_Medical;

        //		//        'IGNORE: L5879 Graduate Tax Exemption

        //		//        'L5880
        tmpCALC_SK.L5880 = tmpCALC_SK.L5804_Basic 
        				+ tmpCALC_SK.L5808_Age 
					    + tmpCALC_SK.L5812_SpouseCLP 
					    + tmpCALC_SK.L5816_EligibleDependant 
					    + tmpCALC_SK.L5821_DependantChildren 
					    + tmpCALC_SK.L5822_SeniorSupplementary 
					    + tmpCALC_SK.L5824_CPPQPPEmployment 
					    + tmpCALC_SK.L5828_CPPQPPSelfEmployment 
					    + tmpCALC_SK.L5832_EIPremiumsEmployment 
					    + tmpCALC_SK.L5836_PensionIncome 
					    + tmpCALC_SK.L5856_TuitionEducation 
					    + tmpCALC_SK.L5876
			            + tmpINPUTS.DC_Other_NRTC;


        //		//        'L5884
        tmpCALC_SK.L5884 = tmpCALC_SK.L5880 * 0.11;

        //		//        'L5896 Donations and Gifts
        if (tmpINPUTS.DC_Donations >= 200) 
            tmpCALC_SK.L5896_DonationsGifts = (200 * 0.11) + ((tmpINPUTS.DC_Donations - 200) * 0.15);
        else
            tmpCALC_SK.L5896_DonationsGifts = tmpINPUTS.DC_Donations * 0.11;

        //		//        'L6150 Saskatchewan non-refundable tax credits
        tmpCALC_SK.L6150_NRTC = tmpCALC_SK.L5884 + tmpCALC_SK.L5896_DonationsGifts;

        //		//        'L6152 Dividend Tax Credit
        tmpCALC_SK.L6152_DividendTaxCredit = (tmpINPUTS.IN_Investments_Eligible_Dividends * 11 / 100) 
        								+ (tmpINPUTS.IN_Investments_Other_Dividends * 5 / 100);

        //		//        'SK Net Provincial Tax
        tmpCALC_SK.Net_Provincial_Tax = tmpCALC_SK.Gross_Provincial_Tax 
        						- tmpCALC_SK.L6150_NRTC 
        						- tmpCALC_SK.L6152_DividendTaxCredit;
        if (tmpCALC_SK.Net_Provincial_Tax < 0) 
        	tmpCALC_SK.Net_Provincial_Tax = 0;

//        Calculation_SK_TY2011 = tmpCALC_SK
//
//        //        'Note: Graduate Retention Program Tuition Rebate
//        //        'This is a refundable tax credit that we ignore here but for students will affect their estimate.
//        //        'Place a generate limitations note around Students.
//
//    End Function    	
    }
    
    protected void calculateManitobaTax(	
    			TaxPayerValues2011 tmpINPUTS,
    			ManitobaTaxValues2011 tmpCALC_MB,
    			FederalTaxValues2011 tmpCALC_Federal)
    {
//        //        '-----------------------------
//        //        'Calculates the Manitoba taxes
//        //        '-----------------------------
//        Private Function Calculation_MB_TY2011(ByVal tmpINPUTS As struct_USER_INPUT_TY2011, ByVal tmpCALC_Federal As struct_CALC_Federal_TY2011) As struct_CALC_MB_TY2011
//
//        Dim tmpCALC_MB As struct_CALC_MB_TY2011
//
//        //        'Gross Provincial Tax
//        //        '   <=31000	                10.8%
//        //        '   >31000 and <=67000	    12.75%
//        //        '   >67000	                17.4%
         
    	if (tmpCALC_Federal.Taxable_Income <= 31000) 
            tmpCALC_MB.Gross_Provincial_Tax = tmpCALC_Federal.Taxable_Income * (10.8 / 100);
        else if ((tmpCALC_Federal.Taxable_Income > 31000) && (tmpCALC_Federal.Taxable_Income <= 67000)) 
            tmpCALC_MB.Gross_Provincial_Tax = (31000 * 10.8 / 100) 
            		+ ((tmpCALC_Federal.Taxable_Income - 31000) * 12.75 / 100);
        else if (tmpCALC_Federal.Taxable_Income > 67000) 
            tmpCALC_MB.Gross_Provincial_Tax = (31000 * 10.8 / 100) 
            	+ ((67000 - 31000) * 12.75 / 100) 
            	+ ((tmpCALC_Federal.Taxable_Income - 67000) * 17.4 / 100);
        else
            tmpCALC_MB.Gross_Provincial_Tax = 0;

        //        L5804 Basic Personal Amount
        tmpCALC_MB.L5804_Basic = 8384;

        //        L5808 Age Amount
        if (tmpINPUTS.PI_Over_65 == false) 
            tmpCALC_MB.L5808_Age = 0;
        else
        {    
        	if (tmpCALC_Federal.Net_Income <= 27749) 
                tmpCALC_MB.L5808_Age = 3728;
            else
            {
            	tmpCALC_MB.L5808_Age = 3728 - (tmpCALC_Federal.Net_Income - 27749) * 0.15;
                if (tmpCALC_MB.L5808_Age < 0)  
                	tmpCALC_MB.L5808_Age = 0;
            }
        }

        //        L5812 Spouse / CLP
        if ((tmpINPUTS.PI_Filing_Status.equals(MARRIED)) || (tmpINPUTS.PI_Filing_Status.equals(COMMON_LAW))) 
        {    
        	tmpCALC_MB.L5812_SpouseCLP = 8384 - tmpINPUTS.IN_SpouseCLP_Net_Income;
            if (tmpCALC_MB.L5812_SpouseCLP < 0)  
            	tmpCALC_MB.L5812_SpouseCLP = 0;
        }
        else
            tmpCALC_MB.L5812_SpouseCLP = 0;
        // End If

        //        L5816 Amount Eligible Dependant
        if ((tmpINPUTS.PI_Filing_Status.equals(SINGLE)) && (tmpINPUTS.DC_Children > 0)) 
            tmpCALC_MB.L5816_EligibleDependant = 8384;
            //        Assume that they have no net income
        else
            tmpCALC_MB.L5816_EligibleDependant = 0;
        // End If

        //        IGNORE: L5820 Amount for infirm dependants age 18 or older

        //        L5822 Senior supplementary amount
        //        tmpCALC_MN.L5822_SeniorSupplementary

        //        L5824 CPP or QPP contributions Employment
        tmpCALC_MB.L5824_CPPQPPEmployment = tmpCALC_Federal.NRTC_L308_CPPQPPEmployment;

        //        L5828 CPP or QPP contributions Self-Employed
        tmpCALC_MB.L5828_CPPQPPSelfEmployment = tmpCALC_Federal.NRTC_L310_CPPQPPSelfEmployment;

        //        L5832 Employment Insurance premiums Employment
        tmpCALC_MB.L5832_EIPremiumsEmployment = tmpCALC_Federal.NRTC_L312_EIPremiumsEmployment;

        //        IGNORE: L5829 Employment Insurance premiums Self-Employed
        //        IGNORE: L5833 Adoption expenses

        //        L5836 Pension Income
        if (tmpINPUTS.PI_Over_65 == true) 
            if (tmpINPUTS.IN_Other_Pension >= 1000) 
                tmpCALC_MB.L5836_PensionIncome = 1000;
            else
                tmpCALC_MB.L5836_PensionIncome = tmpINPUTS.IN_Other_Pension;
            // End If
        else
            tmpCALC_MB.L5836_PensionIncome = 0;
        // End If

        //        IGNORE: L5838 Children//        s fitness amount
        //        IGNORE: L5840 Caregiver amount
        //        IGNORE: L5844 Disability amount
        //        IGNORE: L5848 Disability amount transferred from a dependant
        //        IGNORE: L5852 Interest paid on your student loans

        //        L5856 Tuition and Education amounts
        //        Tuition, education and textbook amounts
        //        Check for total months equal to 12 or under
        //        CFwd ignored but could be added to ???
        tmpCALC_MB.L5856_TuitionEducation = tmpINPUTS.DC_Education_Tuition_Paid 
        		+ (tmpINPUTS.DC_Education_Full_Months * 400) 
        		+ (tmpINPUTS.DC_Education_Part_Months * 120);

        //        L6147 Family tax benefit (MB428-A)
        tmpCALC_MB.L6147_FamilyTaxBenefit = 0;
        if (tmpCALC_MB.L5804_Basic > 0)  
        	tmpCALC_MB.L6147_FamilyTaxBenefit = tmpCALC_MB.L6147_FamilyTaxBenefit + 2065;
        if (tmpCALC_MB.L5812_SpouseCLP > 0)  
        	tmpCALC_MB.L6147_FamilyTaxBenefit = tmpCALC_MB.L6147_FamilyTaxBenefit + 2065;
        if (tmpCALC_MB.L5816_EligibleDependant > 0)  
        	tmpCALC_MB.L6147_FamilyTaxBenefit = tmpCALC_MB.L6147_FamilyTaxBenefit + 2065;
        if (tmpINPUTS.PI_Over_65 == true)  
        	tmpCALC_MB.L6147_FamilyTaxBenefit = tmpCALC_MB.L6147_FamilyTaxBenefit + 2065;
        //        Assume Spouse / CLP is same age
        if ((tmpINPUTS.PI_Over_65 == true) 
        		&& ((tmpINPUTS.PI_Filing_Status.equals(MARRIED)) 
        				|| (tmpINPUTS.PI_Filing_Status.equals(COMMON_LAW))))  
        	tmpCALC_MB.L6147_FamilyTaxBenefit = tmpCALC_MB.L6147_FamilyTaxBenefit + 2065;
        //        Ignore disabilities
        if (tmpINPUTS.DC_Children > 0)  
        	tmpCALC_MB.L6147_FamilyTaxBenefit = tmpCALC_MB.L6147_FamilyTaxBenefit 
        									+ (tmpINPUTS.DC_Children * 2752);
        tmpCALC_MB.L6147_FamilyTaxBenefit = tmpCALC_MB.L6147_FamilyTaxBenefit 
        									- (0.09 * tmpCALC_Federal.Net_Income);
        if (tmpCALC_MB.L6147_FamilyTaxBenefit < 0)  
        	tmpCALC_MB.L6147_FamilyTaxBenefit = 0;

        //        IGNORE: L5860 Tuition and education amounts transferred from a child
        //        IGNORE: L5864 Amounts transferred from your spouse or common-law partner

        //        L5868 Medical (Line 24 of MB428)
        if ((tmpINPUTS.DC_Medical > 0) && (tmpCALC_Federal.Net_Income >= (1728 / 3 * 100))) 
        {    
        	tmpCALC_MB.L5868_Medical = tmpINPUTS.DC_Medical - 1728;
            if (tmpCALC_MB.L5868_Medical < 0)  
            	tmpCALC_MB.L5868_Medical = 0;
        }
        else if ((tmpINPUTS.DC_Medical > 0) && (tmpCALC_Federal.Net_Income < (1728 / 3 * 100))) 
        {
        	tmpCALC_MB.L5868_Medical = tmpINPUTS.DC_Medical - (0.03 * tmpCALC_Federal.Net_Income);
            if (tmpCALC_MB.L5868_Medical < 0)  
            	tmpCALC_MB.L5868_Medical = 0;
        }
        else
            tmpCALC_MB.L5868_Medical = 0;
        // End If

        //        IGNORE: L5872 Allowable amount of medical expenses for other dependants

        //        L5876 As we ignored L5872, calculation is simplifed
        tmpCALC_MB.L5876 = tmpCALC_MB.L5868_Medical;

        //        L5880
        tmpCALC_MB.L5880 =
                tmpCALC_MB.L5804_Basic +
                tmpCALC_MB.L5808_Age +
                tmpCALC_MB.L5812_SpouseCLP +
                tmpCALC_MB.L5816_EligibleDependant +
                tmpCALC_MB.L5824_CPPQPPEmployment +
                tmpCALC_MB.L5828_CPPQPPSelfEmployment +
                tmpCALC_MB.L5832_EIPremiumsEmployment +
                tmpCALC_MB.L5836_PensionIncome +
                tmpCALC_MB.L5856_TuitionEducation +
                tmpCALC_MB.L6147_FamilyTaxBenefit +
                tmpCALC_MB.L5876 +
	            tmpINPUTS.DC_Other_NRTC;


        //        L5884
        tmpCALC_MB.L5884 = tmpCALC_MB.L5880 * 0.108;

        //        L5896 Donations and Gifts
        if (tmpINPUTS.DC_Donations >= 200) 
            tmpCALC_MB.L5896_DonationsGifts = (200 * 0.108) + ((tmpINPUTS.DC_Donations - 200) * 0.174);
        else
            tmpCALC_MB.L5896_DonationsGifts = tmpINPUTS.DC_Donations * 0.108;
        // End If

        //        L6150 Saskatchewan non-refundable tax credits
        tmpCALC_MB.L6150_NRTC = tmpCALC_MB.L5884 + tmpCALC_MB.L5896_DonationsGifts;

        //        L6152 Dividend Tax Credit
        tmpCALC_MB.L6152_DividendTaxCredit = (tmpINPUTS.IN_Investments_Eligible_Dividends * 11 / 100) 
        								+ (tmpINPUTS.IN_Investments_Other_Dividends * 1.75 / 100);

        //        MB Net Provincial Tax
        tmpCALC_MB.Net_Provincial_Tax = tmpCALC_MB.Gross_Provincial_Tax 
        						- tmpCALC_MB.L6150_NRTC 
        						- tmpCALC_MB.L6152_DividendTaxCredit;
        if (tmpCALC_MB.Net_Provincial_Tax < 0)  
        	tmpCALC_MB.Net_Provincial_Tax = 0;

        //        MB479 L6 Family Income
        double C1, C2;
        C1 = tmpCALC_Federal.Net_Income - tmpINPUTS.IN_Other_UCCB;
        if (C1 < 0)  
        	C1 = 0;
        C2 = tmpINPUTS.IN_SpouseCLP_Net_Income - tmpINPUTS.IN_Other_UCCB;
        if (C2 < 0)  
        	C2 = 0;
        tmpCALC_MB.MB479_FamilyIncome = C1 + C2;

        //        MB479 L19 Personal Tax Credit
        tmpCALC_MB.MB479_PersonalTaxCredit = 195;
        if (tmpINPUTS.PI_Over_65 == true)  
        	tmpCALC_MB.MB479_PersonalTaxCredit = tmpCALC_MB.MB479_PersonalTaxCredit + 113;
        if ((tmpINPUTS.PI_Filing_Status.equals(MARRIED)) || (tmpINPUTS.PI_Filing_Status.equals(COMMON_LAW)))  
        	tmpCALC_MB.MB479_PersonalTaxCredit = tmpCALC_MB.MB479_PersonalTaxCredit + 195;
        //        Assume Spouse / CLP is same age
        if ((tmpINPUTS.PI_Over_65 == true) 
        		&& ((tmpINPUTS.PI_Filing_Status.equals(MARRIED)) 
        				|| (tmpINPUTS.PI_Filing_Status.equals(COMMON_LAW))))  
        	tmpCALC_MB.MB479_PersonalTaxCredit = tmpCALC_MB.MB479_PersonalTaxCredit + 113;
        //        Ignore disabilities
        if (tmpCALC_MB.L5816_EligibleDependant > 0)  
        	tmpCALC_MB.MB479_PersonalTaxCredit = tmpCALC_MB.MB479_PersonalTaxCredit + 195;
        if (tmpINPUTS.DC_Children > 0)  
        	tmpCALC_MB.MB479_PersonalTaxCredit = tmpCALC_MB.MB479_PersonalTaxCredit + (tmpINPUTS.DC_Children * 26);
        tmpCALC_MB.MB479_PersonalTaxCredit = tmpCALC_MB.MB479_PersonalTaxCredit - (0.01 * tmpCALC_MB.MB479_FamilyIncome);
        if (tmpCALC_MB.MB479_PersonalTaxCredit < 0)  
        	tmpCALC_MB.MB479_PersonalTaxCredit = 0;

//        Calculation_MB_TY2011 = tmpCALC_MB
//
//    End Function
    	
    }
    
    protected void calculateNewBrunswickTax(
    			TaxPayerValues2011 tmpINPUTS,
				NewBrunswickTaxValues2011 tmpCALC_NB,
				FederalTaxValues2011 tmpCALC_Federal)
    {
//        //        '----------------------------------
//        //        'Calculates the New Brunswick taxes
//        //        '----------------------------------
//        Private Function Calculation_NB_TY2011(ByVal tmpINPUTS As struct_USER_INPUT_TY2011, ByVal tmpCALC_Federal As struct_CALC_Federal_TY2011) As struct_CALC_NB_TY2011
//
//        Dim tmpCALC_NB As struct_CALC_NB_TY2011
//
//        //        'Gross Provincial Tax
//        //        '   <=37150	                9.1%
//        //        '   >37150 and <=74300	    12.1%
//        //        '   >74300 and <=120796	    12.4%
//        //        '   >120796	                14.3%
        if (tmpCALC_Federal.Taxable_Income <= 37150) 
            tmpCALC_NB.Gross_Provincial_Tax = tmpCALC_Federal.Taxable_Income * (9.1 / 100);
        else if ((tmpCALC_Federal.Taxable_Income > 37150) && (tmpCALC_Federal.Taxable_Income <= 74300)) 
            tmpCALC_NB.Gross_Provincial_Tax = (37150 * 9.1 / 100) + ((tmpCALC_Federal.Taxable_Income - 37150) * 12.1 / 100);
        else if ((tmpCALC_Federal.Taxable_Income > 74300) && (tmpCALC_Federal.Taxable_Income <= 120796)) 
            tmpCALC_NB.Gross_Provincial_Tax = (37150 * 9.1 / 100) 
            		+ ((74300 - 37150) * 12.1 / 100) 
            		+ ((tmpCALC_Federal.Taxable_Income - 74300) * 12.4 / 100);
        else if (tmpCALC_Federal.Taxable_Income > 120796) 
            tmpCALC_NB.Gross_Provincial_Tax = (37150 * 9.1 / 100) 
            + ((74300 - 37150) * 12.1 / 100) 
            + ((120796 - 74300) * 12.4 / 100) 
            + ((tmpCALC_Federal.Taxable_Income - 120796) * 14.3 / 100);
        else
            tmpCALC_NB.Gross_Provincial_Tax = 0;
        //        End if

        //        //        'L5804 Basic Personal Amount
        tmpCALC_NB.L5804_Basic = 8953;

        //        //        'L5808 Age Amount
        if (tmpINPUTS.PI_Over_65 == false) 
            tmpCALC_NB.L5808_Age = 0;
        else
        {    
        	if (tmpCALC_Federal.Net_Income <= 32543) 
                tmpCALC_NB.L5808_Age = 4371;
            else
            {
            	tmpCALC_NB.L5808_Age = 4371 - (tmpCALC_Federal.Net_Income - 32543) * 0.15;
                if (tmpCALC_NB.L5808_Age < 0)  
                	tmpCALC_NB.L5808_Age = 0;
            }
        }

        //        //        'L5812 Spouse / CLP
        if ((tmpINPUTS.PI_Filing_Status.equals(MARRIED)) || (tmpINPUTS.PI_Filing_Status.equals(COMMON_LAW))) 
        {
        	tmpCALC_NB.L5812_SpouseCLP = 8363 - tmpINPUTS.IN_SpouseCLP_Net_Income;
            if (tmpCALC_NB.L5812_SpouseCLP < 0)  
            	tmpCALC_NB.L5812_SpouseCLP = 0;
            //        //        'CHECK: Following line may be an easier calculation than what we implemented for BC and ON
            if (tmpCALC_NB.L5812_SpouseCLP > 7602)  
            	tmpCALC_NB.L5812_SpouseCLP = 7602;
        }
        else
            tmpCALC_NB.L5812_SpouseCLP = 0;
        //        End if

        //        //        'L5816 Amount Eligible Dependant
        if ((tmpINPUTS.PI_Filing_Status.equals(SINGLE)) && (tmpINPUTS.DC_Children > 0)) 
        {
        	tmpCALC_NB.L5816_EligibleDependant = 8363;
            //        //        'Assume that they have no net income
            if (tmpCALC_NB.L5816_EligibleDependant > 7602)  
            	tmpCALC_NB.L5816_EligibleDependant = 7602;
        }
        else
            tmpCALC_NB.L5816_EligibleDependant = 0;
        //        End if

        //        //        'IGNORE: L5820 Amount for infirm dependants age 18 or older

        //        //        'L5824 CPP or QPP contributions Employment
        tmpCALC_NB.L5824_CPPQPPEmployment = tmpCALC_Federal.NRTC_L308_CPPQPPEmployment;

        //        //        'L5828 CPP or QPP contributions Self-Employed
        tmpCALC_NB.L5828_CPPQPPSelfEmployment = tmpCALC_Federal.NRTC_L310_CPPQPPSelfEmployment;

        //        //        'L5832 Employment Insurance premiums Employment
        tmpCALC_NB.L5832_EIPremiumsEmployment = tmpCALC_Federal.NRTC_L312_EIPremiumsEmployment;

        //        //        'IGNORE: L5829 Employment Insurance premiums Self-Employed

        //        //        'L5836 Pension Income
        if (tmpINPUTS.PI_Over_65 = true) 
            if (tmpINPUTS.IN_Other_Pension >= 1000) 
                tmpCALC_NB.L5836_PensionIncome = 1000;
            else
                tmpCALC_NB.L5836_PensionIncome = tmpINPUTS.IN_Other_Pension;
            //        End if
        else
            tmpCALC_NB.L5836_PensionIncome = 0;
        //        End if

        //        //        'IGNORE: L5840 Caregiver amount
        //        //        'IGNORE: L5844 Disability amount
        //        //        'IGNORE: L5848 Disability amount transferred from a dependant
        //        //        'IGNORE: L5852 Interest paid on your student loans

        //        //        'L5856 Tuition and Education amounts
        //        //        'Tuition, education and textbook amounts
        //        //        'Check for total months equal to 12 or under
        //        //        'CFwd ignored but could be added to ???
        tmpCALC_NB.L5856_TuitionEducation = tmpINPUTS.DC_Education_Tuition_Paid 
        								+ (tmpINPUTS.DC_Education_Full_Months * 400) 
        								+ (tmpINPUTS.DC_Education_Part_Months * 120);

        //        //        'IGNORE: L5860 Tuition and education amounts transferred from a child
        //        //        'IGNORE: L5864 Amounts transferred from your spouse or common-law partner

        //        //        'L5868 Medical (Line 20 of NB428)
        if ((tmpINPUTS.DC_Medical > 0) && (tmpCALC_Federal.Net_Income >= (2026 / 3 * 100))) 
        {
        	tmpCALC_NB.L5868_Medical = tmpINPUTS.DC_Medical - 2026;
            if (tmpCALC_NB.L5868_Medical < 0)  
            	tmpCALC_NB.L5868_Medical = 0;
        }
        else if ((tmpINPUTS.DC_Medical > 0) && (tmpCALC_Federal.Net_Income < (2026 / 3 * 100))) 
        {
        	tmpCALC_NB.L5868_Medical = tmpINPUTS.DC_Medical - (0.03 * tmpCALC_Federal.Net_Income);
            if (tmpCALC_NB.L5868_Medical < 0)  
            	tmpCALC_NB.L5868_Medical = 0;
        }
        else
            tmpCALC_NB.L5868_Medical = 0;
        //        End if

        //        //        'IGNORE: L5872 Allowable amount of medical expenses for other dependants

        //        //        'L5876 As we ignored L5872, calculation is simplifed
        tmpCALC_NB.L5876 = tmpCALC_NB.L5868_Medical;

        //        //        'IGNORE: L5879 Graduate Tax Exemption

        //        //        'L5880
        tmpCALC_NB.L5880 = 
            tmpCALC_NB.L5804_Basic +
            tmpCALC_NB.L5808_Age +
            tmpCALC_NB.L5812_SpouseCLP +
            tmpCALC_NB.L5816_EligibleDependant +
            tmpCALC_NB.L5824_CPPQPPEmployment +
            tmpCALC_NB.L5828_CPPQPPSelfEmployment +
            tmpCALC_NB.L5832_EIPremiumsEmployment +
            tmpCALC_NB.L5836_PensionIncome +
            tmpCALC_NB.L5856_TuitionEducation +
            tmpCALC_NB.L5876 +
            tmpINPUTS.DC_Other_NRTC;


        //        //        'L5884
        tmpCALC_NB.L5884 = tmpCALC_NB.L5880 * 0.091;

        //        //        'L5896 Donations and Gifts
        if (tmpINPUTS.DC_Donations >= 200) 
            tmpCALC_NB.L5896_DonationsGifts = (200 * 0.091) + ((tmpINPUTS.DC_Donations - 200) * 0.1795);
        else
            tmpCALC_NB.L5896_DonationsGifts = tmpINPUTS.DC_Donations * 0.091;
        //        End if

        //        //        'L6150 Saskatchewan non-refundable tax credits
        tmpCALC_NB.L6150_NRTC = tmpCALC_NB.L5884 + tmpCALC_NB.L5896_DonationsGifts;

        //        //        'L6152 Dividend Tax Credit
        tmpCALC_NB.L6152_DividendTaxCredit = 
        		(tmpINPUTS.IN_Investments_Eligible_Dividends * 12 / 100) + 
        		(tmpINPUTS.IN_Investments_Other_Dividends * 5.3 / 100);

        //        //        'L58 Adjusted Family Income
        tmpCALC_NB.NB479_AdjustedFamilyIncome = (tmpCALC_Federal.Net_Income + tmpINPUTS.IN_SpouseCLP_Net_Income) 
        									- tmpINPUTS.IN_Other_UCCB;

        //        //        'New Brunswick low-income tax reduction
        //        //        'Assumes the person is claiming and not the spouse - perhaps an overall toggle later on
        tmpCALC_NB.NB479_LowIncomeTaxReduction = 545;
        if (tmpINPUTS.PI_Filing_Status.equals(MARRIED) || (tmpINPUTS.PI_Filing_Status.equals(COMMON_LAW)))  
        		tmpCALC_NB.NB479_LowIncomeTaxReduction = tmpCALC_NB.NB479_LowIncomeTaxReduction + 545;
        if (tmpCALC_Federal.NRTC_L305_EligibleDependant > 0)  
        	tmpCALC_NB.NB479_LowIncomeTaxReduction = tmpCALC_NB.NB479_LowIncomeTaxReduction + 545;
        if (tmpCALC_NB.NB479_LowIncomeTaxReduction > 1092)  tmpCALC_NB.NB479_LowIncomeTaxReduction = 1090;
        double T;
        T = tmpCALC_NB.NB479_AdjustedFamilyIncome - 14941;
        if (T < 0)  
        	T = 0;
        tmpCALC_NB.NB479_LowIncomeTaxReduction = tmpCALC_NB.NB479_LowIncomeTaxReduction - ((T) * 0.03);
        if (tmpCALC_NB.NB479_LowIncomeTaxReduction < 0)  
        	tmpCALC_NB.NB479_LowIncomeTaxReduction = 0;

        //        //        'NB Net Provincial Tax
        tmpCALC_NB.Net_Provincial_Tax = tmpCALC_NB.Gross_Provincial_Tax 
        							- tmpCALC_NB.L6150_NRTC 
        							- tmpCALC_NB.L6152_DividendTaxCredit 
        							- tmpCALC_NB.NB479_LowIncomeTaxReduction;
        if (tmpCALC_NB.Net_Provincial_Tax < 0)  
        	tmpCALC_NB.Net_Provincial_Tax = 0;

//        Calculation_NB_TY2011 = tmpCALC_NB
//
//        End Function   	
    }
    
    protected void calculateNovaScotiaTax(	
    				TaxPayerValues2011 tmpINPUTS,
					NovaScotiaTaxValues2011 tmpCALC_NS,
					FederalTaxValues2011 tmpCALC_Federal)
    {
//       //        //        '--------------------------------
//	   //        //        'Calculates the Nova Scotia taxes
//	   //        //        '--------------------------------
//	   Private Function Calculation_NS_TY2011(ByVal tmpINPUTS As
//	struct_USER_INPUT_TY2011, ByVal tmpCALC_Federal As
//	struct_CALC_Federal_TY2011) As struct_CALC_NS_TY2011
//
//	       Dim tmpCALC_NS As struct_CALC_NS_TY2011
//
//	       //        //        'Gross Provincial Tax
//	       //        //        '   <= 29590                8.79%
//	       //        //        '   >29590 and <=59180      14.95%
//	       //        //        '   >59180 and <=93000      16.67%
//	       //        //        '   >93000 and <=150000     17.5%
//	       //        //        '   >150000                     21%
       if (tmpCALC_Federal.Taxable_Income <= 29590) // Then
           tmpCALC_NS.Gross_Provincial_Tax = tmpCALC_Federal.Taxable_Income * (8.79 / 100);
       else if ((tmpCALC_Federal.Taxable_Income > 29590) && (tmpCALC_Federal.Taxable_Income <= 59180)) // Then
           tmpCALC_NS.Gross_Provincial_Tax = (29590 * 8.79 / 100) + 
           ((tmpCALC_Federal.Taxable_Income - 29590) * 14.95 / 100);
       else if ((tmpCALC_Federal.Taxable_Income > 59180) && (tmpCALC_Federal.Taxable_Income <= 93000)) // Then
           tmpCALC_NS.Gross_Provincial_Tax = (29590 * 8.79 / 100) + 
           									((59180 - 29590) * 14.95 / 100) + 
           									((tmpCALC_Federal.Taxable_Income - 59180) * 16.67 / 100);
       else if ((tmpCALC_Federal.Taxable_Income > 93000) && (tmpCALC_Federal.Taxable_Income <= 150000)) // Then
           								tmpCALC_NS.Gross_Provincial_Tax = (29590 * 8.79 / 100) + 
           								((59180 - 29590) * 14.95 / 100) + 
           								((93000 - 59180) * 16.67 / 100) + 
           								((tmpCALC_Federal.Taxable_Income - 93000) * 17.5 / 100);
       else if (tmpCALC_Federal.Taxable_Income > 150000) // Then
           tmpCALC_NS.Gross_Provincial_Tax = (29590 * 8.79 / 100) + 
           ((59180 - 29590) * 14.95 / 100) + 
           ((93000 - 59180) * 16.67 / 100) + 
           ((150000 - 93000) * 17.5 / 100) + 
           ((tmpCALC_Federal.Taxable_Income - 150000) * 21 / 100);
       else
           tmpCALC_NS.Gross_Provincial_Tax = 0;
       // End If

       //        //        'L5804 Basic Personal Amount
       tmpCALC_NS.L5804_Basic = 8481;

       //        //        'L5808 Age Amount
       if (tmpINPUTS.PI_Over_65 == false) // Then
           tmpCALC_NS.L5808_Age = 0;
       else
       {    
    	   if (tmpCALC_Federal.Net_Income <= 30828) // Then
               tmpCALC_NS.L5808_Age = 4141;
           else
           {
        	   tmpCALC_NS.L5808_Age = 4141 - (tmpCALC_Federal.Net_Income - 30828) * 0.15;
               if (tmpCALC_NS.L5808_Age < 0) // Then 
            	   tmpCALC_NS.L5808_Age = 0;
           }// End If
       }// End If

       //        //        'L5812 Spouse / CLP
       if ((tmpINPUTS.PI_Filing_Status.equals(MARRIED)) || (tmpINPUTS.PI_Filing_Status.equals(COMMON_LAW))) // Then
           tmpCALC_NS.L5812_SpouseCLP = 7921 - tmpINPUTS.IN_SpouseCLP_Net_Income;
           if (tmpCALC_NS.L5812_SpouseCLP < 0) // Then
        	   tmpCALC_NS.L5812_SpouseCLP = 0;
           //        //        'CHECK: Following line may be an easier calculation than what we implement for BC and ON
           if (tmpCALC_NS.L5812_SpouseCLP > 7201) // Then
        	   tmpCALC_NS.L5812_SpouseCLP = 7201;
           else
        	   tmpCALC_NS.L5812_SpouseCLP = 0;
       // End If

       //        //        'L5816 Amount Eligible Dependant
       if ((tmpINPUTS.PI_Filing_Status.equals(SINGLE)) && (tmpINPUTS.DC_Children > 0)) // Then
           tmpCALC_NS.L5816_EligibleDependant = 7921;
           //        //        'Assume that they have no net income
       if (tmpCALC_NS.L5816_EligibleDependant > 7201) // Then
    	   tmpCALC_NS.L5816_EligibleDependant = 7201;
       else
    	   tmpCALC_NS.L5816_EligibleDependant = 0;
       // End If

       //        //        'IGNORE: L5820 Amount for infirm dependants age 18 or older

       //        //        'IGNORE: L5823 Amount for young children
       tmpCALC_NS.L5823_YoungChildren = 0;

       //        //        'L5824 CPP or QPP contributions Employment
       tmpCALC_NS.L5824_CPPQPPEmployment = tmpCALC_Federal.NRTC_L308_CPPQPPEmployment;

       //        //        'L5828 CPP or QPP contributions Self-Employed
       tmpCALC_NS.L5828_CPPQPPSelfEmployment = tmpCALC_Federal.NRTC_L310_CPPQPPSelfEmployment;

       //        //        'L5832 Employment Insurance premiums Employment
       tmpCALC_NS.L5832_EIPremiumsEmployment = tmpCALC_Federal.NRTC_L312_EIPremiumsEmployment;

       //        //        'IGNORE: L5829 Employment Insurance premiums Self-Employed

       //        //        'L5836 Pension Income
       if (tmpINPUTS.PI_Over_65 == true) // Then
       {   if (tmpINPUTS.IN_Other_Pension >= 1173) // Then
               tmpCALC_NS.L5836_PensionIncome = 1173;
           else
               tmpCALC_NS.L5836_PensionIncome = tmpINPUTS.IN_Other_Pension;
       }    // End If
       else
           tmpCALC_NS.L5836_PensionIncome = 0;
       // End If

       //        //        'IGNORE: L5840 Caregiver amount
       //        //        'IGNORE: L5844 Disability amount
       //        //        'IGNORE: L5848 Disability amount transferred from a dependant
       //        //        'IGNORE: L5849 Sport and recreational expenses for children
       //        //        'IGNORE: L5852 Interest paid on your student loans

       //        //        'L5856 Tuition and Education amounts
       //        //        'Tuition, education and textbook amounts
       //        //        'Check for total months equal to 12 or under
       //        //        'CFwd ignored but could be added to ???
       tmpCALC_NS.L5856_TuitionEducation = tmpINPUTS.DC_Education_Tuition_Paid + 
    		   				(tmpINPUTS.DC_Education_Full_Months * 200) + 
    		   				(tmpINPUTS.DC_Education_Part_Months * 60);

       //        //        'IGNORE: L5860 Tuition and education amounts transferred from a child
       //        //        'IGNORE: L5864 Amounts transferred from your spouse or common-law partner

       //        //        'L5868 Medical (Line 22 of NS428)
       if ((tmpINPUTS.DC_Medical > 0) && (tmpCALC_Federal.Net_Income >= (1637 / 3 * 100))) // Then
           tmpCALC_NS.L5868_Medical = tmpINPUTS.DC_Medical - 1637;
       if (tmpCALC_NS.L5868_Medical < 0) //Then 
    	   tmpCALC_NS.L5868_Medical = 0;
       else if ((tmpINPUTS.DC_Medical > 0) && (tmpCALC_Federal.Net_Income < (1637 / 3 * 100))) // Then
       {
    	   tmpCALC_NS.L5868_Medical = tmpINPUTS.DC_Medical - (0.03 * tmpCALC_Federal.Net_Income);
           if (tmpCALC_NS.L5868_Medical < 0) // Then 
        	   tmpCALC_NS.L5868_Medical = 0;
       }
       else
           tmpCALC_NS.L5868_Medical = 0;
       // End If

       //        //        'IGNORE: L5872 Allowable amount of medical expenses for other dependants

       //        //        'L5876 As we ignored L5872, calculation is simplifed
       tmpCALC_NS.L5876 = tmpCALC_NS.L5868_Medical;

       //        //        'IGNORE: L5879 Graduate Tax Exemption

       //        //        'L5880
       tmpCALC_NS.L5880 = 
           tmpCALC_NS.L5804_Basic + 
           tmpCALC_NS.L5808_Age + 
           tmpCALC_NS.L5812_SpouseCLP + 
           tmpCALC_NS.L5816_EligibleDependant + 
           tmpCALC_NS.L5823_YoungChildren + 
           tmpCALC_NS.L5824_CPPQPPEmployment + 
           tmpCALC_NS.L5828_CPPQPPSelfEmployment + 
           tmpCALC_NS.L5832_EIPremiumsEmployment + 
           tmpCALC_NS.L5836_PensionIncome + 
           tmpCALC_NS.L5856_TuitionEducation + 
           tmpCALC_NS.L5876 +
           tmpINPUTS.DC_Other_NRTC;


       //        //        'L5884
       tmpCALC_NS.L5884 = tmpCALC_NS.L5880 * 0.0879;

       //        //        'L5896 Donations and Gifts
       if (tmpINPUTS.DC_Donations >= 200) // Then
           tmpCALC_NS.L5896_DonationsGifts = (200 * 0.0879) + ((tmpINPUTS.DC_Donations - 200) * 0.21);
       else
           tmpCALC_NS.L5896_DonationsGifts = tmpINPUTS.DC_Donations * 0.0879;
       // End If

       //        //        'L6150 Saskatchewan non-refundable tax credits
       tmpCALC_NS.L6150_NRTC = tmpCALC_NS.L5884 + tmpCALC_NS.L5896_DonationsGifts;

       //        //        'L6152 Dividend Tax Credit
       tmpCALC_NS.L6152_DividendTaxCredit =
           (tmpINPUTS.IN_Investments_Eligible_Dividends * 8.85 / 100) + 
           (tmpINPUTS.IN_Investments_Other_Dividends * 7.7 / 100);

       //        //        'L59 Adjusted Family Income
       tmpCALC_NS.NS479_AdjustedFamilyIncome = (tmpCALC_Federal.Net_Income 
    		   + tmpINPUTS.IN_SpouseCLP_Net_Income) 
    		   - tmpINPUTS.IN_Other_UCCB;

       //        //        'Nova Scotia low-income tax reduction
       //        //        'Assumes the person is claiming and not the spouse - perhaps an overall toggle later on
       tmpCALC_NS.NS479_LowIncomeTaxReduction = 300;
       if ((tmpINPUTS.PI_Filing_Status.equals(MARRIED)) || (tmpINPUTS.PI_Filing_Status.equals(COMMON_LAW))) // Then
    	   tmpCALC_NS.NS479_LowIncomeTaxReduction = tmpCALC_NS.NS479_LowIncomeTaxReduction + 300;
       //        //        'L6199 ...
       if (tmpCALC_Federal.NRTC_L305_EligibleDependant > 0) // Then
    	   tmpCALC_NS.NS479_LowIncomeTaxReduction = tmpCALC_NS.NS479_LowIncomeTaxReduction + 300;
       //        //        'L6099 ...
       if ((tmpCALC_Federal.NRTC_L305_EligibleDependant > 0) && (tmpINPUTS.DC_Children > 1)) // Then
           //        //        'Handles scenario when one child was claimed via L6199 and there is more than 1 child
           tmpCALC_NS.NS479_LowIncomeTaxReduction = tmpCALC_NS.NS479_LowIncomeTaxReduction + ((tmpINPUTS.DC_Children - 1) * 165);
       else if ((tmpCALC_Federal.NRTC_L305_EligibleDependant == 0) && (tmpINPUTS.DC_Children > 0)) // Then
           //        //        'Handles scenario when no child was claimed via L6199
           tmpCALC_NS.NS479_LowIncomeTaxReduction = tmpCALC_NS.NS479_LowIncomeTaxReduction + (tmpINPUTS.DC_Children * 165);
       // End If

       double T;
       T = tmpCALC_NS.NS479_AdjustedFamilyIncome - 15000;
       if (T < 0) // Then T = 0
    	   tmpCALC_NS.NS479_LowIncomeTaxReduction = tmpCALC_NS.NS479_LowIncomeTaxReduction - ((T) * 0.05);
       if (tmpCALC_NS.NS479_LowIncomeTaxReduction < 0) // Then
    	   tmpCALC_NS.NS479_LowIncomeTaxReduction = 0;

       //        //        'NS Net Provincial Tax
       tmpCALC_NS.Net_Provincial_Tax = tmpCALC_NS.Gross_Provincial_Tax 
    		   - tmpCALC_NS.L6150_NRTC 
    		   - tmpCALC_NS.L6152_DividendTaxCredit 
    		   - tmpCALC_NS.NS479_LowIncomeTaxReduction;
       if (tmpCALC_NS.Net_Provincial_Tax < 0) // Then
    	   	tmpCALC_NS.Net_Provincial_Tax = 0;

//	       Calculation_NS_TY2011 = tmpCALC_NS
//
//	   End Function
    }
    
    protected void calculateNewfoundlandTax(	
    				TaxPayerValues2011 tmpINPUTS,
    				NewfoundlandTaxValues2011 tmpCALC_NL,
    				FederalTaxValues2011 tmpCALC_Federal)
    {

    
	   //        //        '--------------------------------------------
	   //        //        'Calculates the Newfoundland & Labrador taxes
	   //        //        '--------------------------------------------
//	   Private Function Calculation_NL_TY2011(ByVal tmpINPUTS As
//	struct_USER_INPUT_TY2011, ByVal tmpCALC_Federal As
//	struct_CALC_Federal_TY2011) As struct_CALC_NL_TY2011
//
//	       Dim tmpCALC_NL As struct_CALC_NL_TY2011
//
//	       //        //        'Gross Provincial Tax
//	       //        //        '   <=31904                     7.7%
//	       //        //        '   >31904 and <=63807      12.5%
//	       //        //        '   >63807                      13.3%
       if (tmpCALC_Federal.Taxable_Income <= 31904) // Then
           tmpCALC_NL.Gross_Provincial_Tax = tmpCALC_Federal.Taxable_Income * (7.7 / 100);
       else if ((tmpCALC_Federal.Taxable_Income > 31904) && (tmpCALC_Federal.Taxable_Income <= 63807)) // Then
           tmpCALC_NL.Gross_Provincial_Tax = (31904 * 7.7 / 100) + 
               ((tmpCALC_Federal.Taxable_Income - 31904) * 12.5 / 100);
       else if (tmpCALC_Federal.Taxable_Income > 63807) // Then
           tmpCALC_NL.Gross_Provincial_Tax = (31904 * 7.7 / 100) + 
               ((63807 - 31904) * 12.5 / 100) + 
               ((tmpCALC_Federal.Taxable_Income - 63807) * 13.3 / 100);
       else
           tmpCALC_NL.Gross_Provincial_Tax = 0;
       // End If

       //        //        'L5804 Basic Personal Amount
       tmpCALC_NL.L5804_Basic = 7989;

       //        //        'L5808 Age Amount
       if (tmpINPUTS.PI_Over_65 == false) // Then
           tmpCALC_NL.L5808_Age = 0;
       else
       {	   if (tmpCALC_Federal.Net_Income <= 27948) // Then
               tmpCALC_NL.L5808_Age = 5100;
           else
           {    
        	   tmpCALC_NL.L5808_Age = 5100 - (tmpCALC_Federal.Net_Income - 27948) * 0.15;
               if (tmpCALC_NL.L5808_Age < 0) 
            	   tmpCALC_NL.L5808_Age = 0;
           }// End If
       }// End If

       //        //        'L5812 Spouse / CLP
       if ((tmpINPUTS.PI_Filing_Status.equals(MARRIED)) || (tmpINPUTS.PI_Filing_Status.equals(COMMON_LAW))) // Then
       {
    	   tmpCALC_NL.L5812_SpouseCLP = 7181 - tmpINPUTS.IN_SpouseCLP_Net_Income;
           if (tmpCALC_NL.L5812_SpouseCLP < 0) // Then
        	   tmpCALC_NL.L5812_SpouseCLP = 0;
           //        //        'CHECK: Following line may be an easier calculation than what we implement for BC and ON
           if (tmpCALC_NL.L5812_SpouseCLP > 6528) // Then
        	   tmpCALC_NL.L5812_SpouseCLP = 6528;
           else
        	   tmpCALC_NL.L5812_SpouseCLP = 0;
       }// End If

       //        //        'L5816 Amount Eligible Dependant
       if ((tmpINPUTS.PI_Filing_Status.equals(SINGLE)) && (tmpINPUTS.DC_Children > 0)) // Then
       {    
    	   tmpCALC_NL.L5816_EligibleDependant = 7181;	       
           //        //        'Assume that they have no net income
           if (tmpCALC_NL.L5816_EligibleDependant > 6528) // Then
        	   tmpCALC_NL.L5816_EligibleDependant = 6528;
       }
       else
           tmpCALC_NL.L5816_EligibleDependant = 0;
       // End If

       //        //        'IGNORE: L5820 Amount for infirm dependants age 18 or older

       //        //        'L5824 CPP or QPP contributions Employment
       tmpCALC_NL.L5824_CPPQPPEmployment = tmpCALC_Federal.NRTC_L308_CPPQPPEmployment;

       //        //        'L5828 CPP or QPP contributions Self-Employed
       tmpCALC_NL.L5828_CPPQPPSelfEmployment = tmpCALC_Federal.NRTC_L310_CPPQPPSelfEmployment;

       //        //        'L5832 Employment Insurance premiums Employment
       tmpCALC_NL.L5832_EIPremiumsEmployment = tmpCALC_Federal.NRTC_L312_EIPremiumsEmployment;

       //        //        'IGNORE: L5829 Employment Insurance premiums Self-Employed
       //        //        'IGNORE: L5833 Adoption expenses

       //        //        'L5836 Child Care
       tmpCALC_NL.L5836_ChildCare = tmpCALC_Federal.L214_ChildCare;

       //        //        'L5836 Pension Income
       if (tmpINPUTS.PI_Over_65 == true) // Then
       {
    	   if (tmpINPUTS.IN_Other_Pension >= 1000) // Then	     
               tmpCALC_NL.L5836_PensionIncome = 1000;
           else
               tmpCALC_NL.L5836_PensionIncome = tmpINPUTS.IN_Other_Pension;
           // End If
       }
       else
           tmpCALC_NL.L5836_PensionIncome = 0;
       // End If

       //        //        'IGNORE: L5840 Caregiver amount
       //        //        'IGNORE: L5844 Disability amount
       //        //        'IGNORE: L5848 Disability amount transferred from a dependant
       //        //        'IGNORE: L5852 Interest paid on your student loans

       //        //        'L5856 Tuition and Education amounts
       //        //        'Tuition, education and textbook amounts
       //        //        'Check for total months equal to 12 or under
       //        //        'CFwd ignored but could be added to ???
       tmpCALC_NL.L5856_TuitionEducation = tmpINPUTS.DC_Education_Tuition_Paid + 
    		   								(tmpINPUTS.DC_Education_Full_Months * 200) + 
    		   								(tmpINPUTS.DC_Education_Part_Months * 60);

       //        //        'IGNORE: L5860 Tuition and education amounts transferred from a child
       //        //        'IGNORE: L5864 Amounts transferred from your spouse or common-law partner

       //        //        'L5868 Medical (Line 21 of NL428)
       if ((tmpINPUTS.DC_Medical > 0) && (tmpCALC_Federal.Net_Income >= (1740 / 3 * 100))) // Then
       {
    	   tmpCALC_NL.L5868_Medical = tmpINPUTS.DC_Medical - 1740;	    
           if (tmpCALC_NL.L5868_Medical < 0) // Then 
        	   tmpCALC_NL.L5868_Medical = 0;
       }
       else if ((tmpINPUTS.DC_Medical > 0) && (tmpCALC_Federal.Net_Income < (1740 / 3 * 100))) // Then
           tmpCALC_NL.L5868_Medical = tmpINPUTS.DC_Medical - (0.03 * tmpCALC_Federal.Net_Income);
           if (tmpCALC_NL.L5868_Medical < 0) // Then 
        	   tmpCALC_NL.L5868_Medical = 0;
       else
           tmpCALC_NL.L5868_Medical = 0;
       // End If

       //        //        'IGNORE: L5872 Allowable amount of medical expenses for other dependants

       //        //        'L5876 As we ignored L5872, calculation is simplifed
       tmpCALC_NL.L5876 = tmpCALC_NL.L5868_Medical;

       //        //        'L5880
       tmpCALC_NL.L5880 = 
           tmpCALC_NL.L5804_Basic + 
           tmpCALC_NL.L5808_Age + 
           tmpCALC_NL.L5812_SpouseCLP + 
           tmpCALC_NL.L5816_EligibleDependant + 
           tmpCALC_NL.L5824_CPPQPPEmployment + 
           tmpCALC_NL.L5828_CPPQPPSelfEmployment + 
           tmpCALC_NL.L5832_EIPremiumsEmployment + 
           tmpCALC_NL.L5836_ChildCare + 
           tmpCALC_NL.L5836_PensionIncome + 
           tmpCALC_NL.L5856_TuitionEducation + 
           tmpCALC_NL.L5876 +
           tmpINPUTS.DC_Other_NRTC;


       //        //        'L5884
       tmpCALC_NL.L5884 = tmpCALC_NL.L5880 * 0.077;

       //        //        'L5896 Donations and Gifts
       if (tmpINPUTS.DC_Donations >= 200) // Then
           tmpCALC_NL.L5896_DonationsGifts = (200 * 0.077) + ((tmpINPUTS.DC_Donations - 200) * 0.133);
       else
           tmpCALC_NL.L5896_DonationsGifts = tmpINPUTS.DC_Donations * 0.077;
       // End If

       //        //        'L6150 Saskatchewan non-refundable tax credits
       tmpCALC_NL.L6150_NRTC = tmpCALC_NL.L5884 + tmpCALC_NL.L5896_DonationsGifts;

       //        //        'L6152 Dividend Tax Credit
       //        //        'Igonores the 9.6% for first half of 2010 update
       tmpCALC_NL.L6152_DividendTaxCredit = 
           (tmpINPUTS.IN_Investments_Eligible_Dividends * 11 / 100) + 
           (tmpINPUTS.IN_Investments_Other_Dividends * 5 / 100);

       //        //        'L58 Adjusted Family Income
       tmpCALC_NL.NL479_AdjustedFamilyIncome = tmpCALC_Federal.Net_Income 
    		   + tmpINPUTS.IN_SpouseCLP_Net_Income 
    		   - tmpINPUTS.IN_Other_UCCB;

       //        //        'Newfoundland and Labrador low-income tax reduction
       //        //        'Assumes the person is claiming and not the spouse - perhaps an overall toggle later on
       tmpCALC_NL.NL479_LowIncomeTaxReduction = 572;
       if ((tmpINPUTS.PI_Filing_Status.equals(MARRIED)) || (tmpINPUTS.PI_Filing_Status.equals(COMMON_LAW))) // Then
       		tmpCALC_NL.NL479_LowIncomeTaxReduction = tmpCALC_NL.NL479_LowIncomeTaxReduction + 288;
       if (tmpCALC_Federal.NRTC_L305_EligibleDependant > 0) // Then
    	   tmpCALC_NL.NL479_LowIncomeTaxReduction = tmpCALC_NL.NL479_LowIncomeTaxReduction + 288;
       if (tmpCALC_NL.NL479_LowIncomeTaxReduction > 860) // Then
    	   tmpCALC_NL.NL479_LowIncomeTaxReduction = 860;
       double T;
       if (((tmpINPUTS.PI_Filing_Status.equals(MARRIED)) 
    		   || (tmpINPUTS.PI_Filing_Status.equals(COMMON_LAW))) 
    	   			|| (tmpCALC_Federal.NRTC_L305_EligibleDependant > 0)) // Then
           T = tmpCALC_NL.NL479_AdjustedFamilyIncome - 27348;
       else
           T = tmpCALC_NL.NL479_AdjustedFamilyIncome - 16343;
       // End If
       if (T < 0) // Then T = 0
    	   tmpCALC_NL.NL479_LowIncomeTaxReduction = tmpCALC_NL.NL479_LowIncomeTaxReduction - ((T) * 0.16);
       if (tmpCALC_NL.NL479_LowIncomeTaxReduction < 0) // Then
    	   tmpCALC_NL.NL479_LowIncomeTaxReduction = 0;

       //        //        'NL Net Provincial Tax
       tmpCALC_NL.Net_Provincial_Tax = tmpCALC_NL.Gross_Provincial_Tax 
    		   					- tmpCALC_NL.L6150_NRTC 
    		   					- tmpCALC_NL.L6152_DividendTaxCredit 
    		   					- tmpCALC_NL.NL479_LowIncomeTaxReduction;
       if (tmpCALC_NL.Net_Provincial_Tax < 0) // Then
    	   tmpCALC_NL.Net_Provincial_Tax = 0;

//	       Calculation_NL_TY2011 = tmpCALC_NL
//
//	   End Function
    }
    
    protected void calculatePrinceEdwardIslandTax(	
    				TaxPayerValues2011 tmpINPUTS,
    				PrinceEdwardIslandTaxValues2011 tmpCALC_PE,
    				FederalTaxValues2011 tmpCALC_Federal)
    {
//	   //        //        '-----------------------------------------
//	   //        //        'Calculates the Prince Edward Island taxes
//	   //        //        '-----------------------------------------
//	   Private Function Calculation_PE_TY2011(ByVal tmpINPUTS As
//	struct_USER_INPUT_TY2011, ByVal tmpCALC_Federal As
//	struct_CALC_Federal_TY2011) As struct_CALC_PE_TY2011
//
//	       Dim tmpCALC_PE As struct_CALC_PE_TY2011
//
//	       //        //        'Gross Provincial Tax
//	       //        //        '   <= 31984                9.8%
//	       //        //        '   >31984 and <=63969      13.8%
//	       //        //        '   >63969                      16.7%
	       if (tmpCALC_Federal.Taxable_Income <= 31984) // Then
	           tmpCALC_PE.Gross_Provincial_Tax = tmpCALC_Federal.Taxable_Income * (9.8 / 100);
	       else if ((tmpCALC_Federal.Taxable_Income > 31984) && (tmpCALC_Federal.Taxable_Income <= 63969)) // Then
	           tmpCALC_PE.Gross_Provincial_Tax = (31984 * 9.8 / 100) + ((tmpCALC_Federal.Taxable_Income - 31984) * 13.8 / 100);
	       else if (tmpCALC_Federal.Taxable_Income > 63969) // Then
	           tmpCALC_PE.Gross_Provincial_Tax = (31984 * 9.8 / 100) + 
	           								((63969 - 31984) * 13.8 / 100) + 
	           								((tmpCALC_Federal.Taxable_Income - 63969) * 16.7 / 100);
	       else
	           tmpCALC_PE.Gross_Provincial_Tax = 0;
	       // End If

	       //        //        'L5804 Basic Personal Amount
	       tmpCALC_PE.L5804_Basic = 7708;

	       //        //        'L5808 Age Amount
	       if (tmpINPUTS.PI_Over_65 == false) // Then
	           tmpCALC_PE.L5808_Age = 0;
	       else
	       {
	    	   if (tmpCALC_Federal.Net_Income <= 28019) // Then
	               tmpCALC_PE.L5808_Age = 3764;
	           else
	           {
	        	   tmpCALC_PE.L5808_Age = 3764 - (tmpCALC_Federal.Net_Income - 28019) * 0.15;
	               if (tmpCALC_PE.L5808_Age < 0) // Then 
	            	   tmpCALC_PE.L5808_Age = 0;
	           }// End If
	       }// End If

	       //        //        'L5812 Spouse / CLP
	       if ((tmpINPUTS.PI_Filing_Status.equals(MARRIED)) || (tmpINPUTS.PI_Filing_Status.equals(COMMON_LAW))) // Then
	           tmpCALC_PE.L5812_SpouseCLP = 7201 - tmpINPUTS.IN_SpouseCLP_Net_Income;
	       if (tmpCALC_PE.L5812_SpouseCLP < 0) // Then
	    	   tmpCALC_PE.L5812_SpouseCLP = 0;
	           //        //        'CHECK: Following line may be an easier calculation that what we implement for BC and ON
	           if (tmpCALC_PE.L5812_SpouseCLP > 6546) // Then
	        	   tmpCALC_PE.L5812_SpouseCLP = 6546;
	           else
	        	   tmpCALC_PE.L5812_SpouseCLP = 0;
	       // End If

	       //        //        'L5816 Amount Eligible Dependant
	       if ((tmpINPUTS.PI_Filing_Status.equals(SINGLE)) && (tmpINPUTS.DC_Children > 0)) // Then
	       {   tmpCALC_PE.L5816_EligibleDependant = 6924;
	           //        //        'Assume that they have no net income
	           if (tmpCALC_PE.L5816_EligibleDependant > 6294) // Then
	        	   tmpCALC_PE.L5816_EligibleDependant = 6294;
	           else
	        	   tmpCALC_PE.L5816_EligibleDependant = 0;
	       }// End If

	       //        //        'IGNORE: L5820 Amount for infirm dependants age 18 or older

	       //        //        'L5823 Amount for young children
	       tmpCALC_PE.L5823_YoungChildren = 0;

	       //        //        'L5824 CPP or QPP contributions Employment
	       tmpCALC_PE.L5824_CPPQPPEmployment = tmpCALC_Federal.NRTC_L308_CPPQPPEmployment;

	       //        //        'L5828 CPP or QPP contributions Self-Employed
	       tmpCALC_PE.L5828_CPPQPPSelfEmployment = tmpCALC_Federal.NRTC_L310_CPPQPPSelfEmployment;

	       //        //        'L5832 Employment Insurance premiums Employment
	       tmpCALC_PE.L5832_EIPremiumsEmployment = tmpCALC_Federal.NRTC_L312_EIPremiumsEmployment;

	       //        //        'IGNORE: L5829 Employment Insurance premiums Self-Employed

	       //        //        'L5836 Pension Income
	       if (tmpINPUTS.PI_Over_65 == true) // Then
	       {
	    	   if (tmpINPUTS.IN_Other_Pension >= 1000) // Then
	               tmpCALC_PE.L5836_PensionIncome = 1000;
	           else
	               tmpCALC_PE.L5836_PensionIncome = tmpINPUTS.IN_Other_Pension;
	           // End If
	       }
	       else
	           tmpCALC_PE.L5836_PensionIncome = 0;
	       // End If

	       //        //        'IGNORE: L5840 Caregiver amount
	       //        //        'IGNORE: L5844 Disability amount
	       //        //        'IGNORE: L5848 Disability amount transferred from a dependant
	       //        //        'IGNORE: L5850 Tacher school supply amount
	       //        //        'IGNORE: L5852 Interest paid on your student loans

	       //        //        'L5856 Tuition and Education amounts
	       //        //        'Tuition, education and textbook amounts
	       //        //        'Check for total months equal to 12 or under
	       //        //        'CFwd ignored but could be added to ???
	       tmpCALC_PE.L5856_TuitionEducation = tmpINPUTS.DC_Education_Tuition_Paid 
	    		   + (tmpINPUTS.DC_Education_Full_Months * 400) 
	    		   + (tmpINPUTS.DC_Education_Part_Months * 120);

	       //        //        'IGNORE: L5860 Tuition and education amounts transferred from a child
	       //        //        'IGNORE: L5864 Amounts transferred from your spouse or common-law partner

	       //        //        'L5868 Medical (Line 22 of PE428)
	       if ((tmpINPUTS.DC_Medical > 0) && (tmpCALC_Federal.Net_Income >= (1678 / 3 * 100))) // Then
	       {    
	    	   tmpCALC_PE.L5868_Medical = tmpINPUTS.DC_Medical - 1678;
	           	if (tmpCALC_PE.L5868_Medical < 0) // Then 
	           		tmpCALC_PE.L5868_Medical = 0;
	       }
	       else if ((tmpINPUTS.DC_Medical > 0) && (tmpCALC_Federal.Net_Income < (1678 / 3 * 100))) // Then
	       {
	    	   tmpCALC_PE.L5868_Medical = tmpINPUTS.DC_Medical - (0.03 * tmpCALC_Federal.Net_Income);
	           if (tmpCALC_PE.L5868_Medical < 0) // Then 
	        	   tmpCALC_PE.L5868_Medical = 0;
	       }
	       else
	           tmpCALC_PE.L5868_Medical = 0;
	       // End If

	       //        //        'IGNORE: L5872 Allowable amount of medical expenses for other dependants

	       //        //        'L5876 As we ignored L5872, calculation is simplifed
	       tmpCALC_PE.L5876 = tmpCALC_PE.L5868_Medical;

	       //        //        'IGNORE: L5879 Graduate Tax Exemption

	       //        //        'L5880
	       tmpCALC_PE.L5880 = 
	           tmpCALC_PE.L5804_Basic + 
	           tmpCALC_PE.L5808_Age + 
	           tmpCALC_PE.L5812_SpouseCLP + 
	           tmpCALC_PE.L5816_EligibleDependant + 
	           tmpCALC_PE.L5823_YoungChildren + 
	           tmpCALC_PE.L5824_CPPQPPEmployment + 
	           tmpCALC_PE.L5828_CPPQPPSelfEmployment + 
	           tmpCALC_PE.L5832_EIPremiumsEmployment + 
	           tmpCALC_PE.L5836_PensionIncome + 
	           tmpCALC_PE.L5856_TuitionEducation + 
	           tmpCALC_PE.L5876 +
	           tmpINPUTS.DC_Other_NRTC;


	       //        //        'L5884
	       tmpCALC_PE.L5884 = tmpCALC_PE.L5880 * 0.098;

	       //        //        'L5896 Donations and Gifts
	       if (tmpINPUTS.DC_Donations >= 200) // Then
	           tmpCALC_PE.L5896_DonationsGifts = (200 * 0.098) + ((tmpINPUTS.DC_Donations - 200) * 0.167);
	       else
	           tmpCALC_PE.L5896_DonationsGifts = tmpINPUTS.DC_Donations * 0.098;
	       // End If

	       //        //        'L6150 Prince Edward Island non-refundable tax credits
	       tmpCALC_PE.L6150_NRTC = tmpCALC_PE.L5884 + tmpCALC_PE.L5896_DonationsGifts;

	       //        //        'L6152 Dividend Tax Credit
	       tmpCALC_PE.L6152_DividendTaxCredit = 
	           (tmpINPUTS.IN_Investments_Eligible_Dividends * 10.5 / 100) + 
	           (tmpINPUTS.IN_Investments_Other_Dividends * 1 / 100);

	       //        //        'L51 Surtax
	       tmpCALC_PE.L51_PESurtax = ((tmpCALC_PE.Gross_Provincial_Tax 
	    		   - tmpCALC_PE.L6150_NRTC 
	    		   - tmpCALC_PE.L6152_DividendTaxCredit) - 12500) * 0.1;
	       if (tmpCALC_PE.L51_PESurtax < 0) // Then 
	    	   tmpCALC_PE.L51_PESurtax = 0;

	       //        //        'L60 Adjusted Family Income
	       tmpCALC_PE.L60_AdjustedFamilyIncome = tmpCALC_Federal.Net_Income 
	    		   + tmpINPUTS.IN_SpouseCLP_Net_Income 
	    		   - tmpINPUTS.IN_Other_UCCB;

	       //        //        'PEI low-income tax reduction
	       //        //        'Assumes the person is claiming and not the spouse - perhaps an overall toggle later on
	       tmpCALC_PE.L72_LowIncomeTaxReduction = 250;
	       if ((tmpINPUTS.PI_Filing_Status.equals(MARRIED)) || (tmpINPUTS.PI_Filing_Status.equals(COMMON_LAW))) // Then
	    	   tmpCALC_PE.L72_LowIncomeTaxReduction = tmpCALC_PE.L72_LowIncomeTaxReduction + 250;
	       //        //        'L6199 ...
	       if (tmpCALC_Federal.NRTC_L305_EligibleDependant > 0) // Then
	    	   tmpCALC_PE.L72_LowIncomeTaxReduction = tmpCALC_PE.L72_LowIncomeTaxReduction + 250;
	       //        //        'L6099 ...
	       if ((tmpCALC_Federal.NRTC_L305_EligibleDependant > 0) && (tmpINPUTS.DC_Children > 1)) // Then
	           //        //        'Handles scenario when one child was claimed via L6199 and there is more than 1 child
	           tmpCALC_PE.L72_LowIncomeTaxReduction = tmpCALC_PE.L72_LowIncomeTaxReduction + ((tmpINPUTS.DC_Children - 1) * 200);
	       else if ((tmpCALC_Federal.NRTC_L305_EligibleDependant == 0) && (tmpINPUTS.DC_Children > 0)) // Then
	           //        //        'Handles scenario when no child was claimed via L6199
	           tmpCALC_PE.L72_LowIncomeTaxReduction = tmpCALC_PE.L72_LowIncomeTaxReduction + (tmpINPUTS.DC_Children * 200);
	       // End If

	       double T;
	       T = tmpCALC_PE.L60_AdjustedFamilyIncome - 15000;
	       if (T < 0) // Then T = 0
	       tmpCALC_PE.L72_LowIncomeTaxReduction = tmpCALC_PE.L72_LowIncomeTaxReduction - ((T) * 0.05);
	       if (tmpCALC_PE.L72_LowIncomeTaxReduction < 0) // Then
	    	   tmpCALC_PE.L72_LowIncomeTaxReduction = 0;


	       //        //        'PE Net Provincial Tax
	       tmpCALC_PE.Net_Provincial_Tax = tmpCALC_PE.Gross_Provincial_Tax 
	    		   							- tmpCALC_PE.L6150_NRTC 
	    		   							- tmpCALC_PE.L6152_DividendTaxCredit 
	    		   							+ tmpCALC_PE.L51_PESurtax 
	    		   							- tmpCALC_PE.L72_LowIncomeTaxReduction;
	       if (tmpCALC_PE.Net_Provincial_Tax < 0) // Then
	    	   tmpCALC_PE.Net_Provincial_Tax = 0;

//	       Calculation_PE_TY2011 = tmpCALC_PE
//
//	   End Function
    }
	       
    protected void calculateYukonTerritoriesTax(
    				TaxPayerValues2011 tmpINPUTS,
    				YukonTerritoriesTaxValues2011 tmpCALC_YT,
    				FederalTaxValues2011 tmpCALC_Federal)
    {
//	   //        //        '--------------------------
//	   //        //        'Calculates the Yukon taxes
//	   //        //        '--------------------------
//	   Private Function Calculation_YT_TY2011(ByVal tmpINPUTS As
//	struct_USER_INPUT_TY2011, ByVal tmpCALC_Federal As
//	struct_CALC_Federal_TY2011) As struct_CALC_YT_TY2011
//
//	       Dim tmpCALC_YT As struct_CALC_YT_TY2011
//
//	       //        //        'Gross Provincial Tax
//	       //        //        '   <=41544                     7.04%
//	       //        //        '   >41544 and <=83088      9.68%
//	       //        //        '   >83088 and <=128800     11.44%
//	       //        //        '   >128800                     12.76%
       if (tmpCALC_Federal.Taxable_Income <= 41544) // Then
           tmpCALC_YT.Gross_Provincial_Tax = tmpCALC_Federal.Taxable_Income * (7.04 / 100);

       else if ((tmpCALC_Federal.Taxable_Income > 41544) && (tmpCALC_Federal.Taxable_Income <= 83088)) // Then
           tmpCALC_YT.Gross_Provincial_Tax = (41544 * 7.04 / 100) + 
           									((tmpCALC_Federal.Taxable_Income - 41544) * 9.68 / 100);

       else if ((tmpCALC_Federal.Taxable_Income > 83088) && (tmpCALC_Federal.Taxable_Income <= 128800)) // Then
           tmpCALC_YT.Gross_Provincial_Tax = (41544 * 7.04 / 100) + 
           								((83088 - 41544) * 9.68 / 100) + 
           								((tmpCALC_Federal.Taxable_Income - 83088) * 11.44 / 100);

       else if (tmpCALC_Federal.Taxable_Income > 128800) // Then
           tmpCALC_YT.Gross_Provincial_Tax = (41544 * 7.04 / 100) + 
           								((83088 - 41544) * 9.68 / 100) + 
           								((128800 - 83088) * 11.44 / 100) + 
           								((tmpCALC_Federal.Taxable_Income - 128800) * 12.76 / 100);

       else
           tmpCALC_YT.Gross_Provincial_Tax = 0;
       // End If

       //        //        'L5804 Basic Personal Amount
       tmpCALC_YT.L5804_Basic = 10527;

       //        //        'L5808 Age Amount
       if (tmpINPUTS.PI_Over_65 == false) // Then
           tmpCALC_YT.L5808_Age = 0;
       else
       {    
    	   if (tmpCALC_Federal.Net_Income <= 32961) // Then
               tmpCALC_YT.L5808_Age = 6537;
           else
           {
        	   tmpCALC_YT.L5808_Age = 6537 - (tmpCALC_Federal.Net_Income - 32961) * 0.15;
               if (tmpCALC_YT.L5808_Age < 0) // Then 
            	   tmpCALC_YT.L5808_Age = 0;
           }// End If
       }// End If

       //        //        'L5812 Spouse / CLP
       tmpCALC_YT.L5812_SpouseCLP = tmpCALC_Federal.NRTC_L303_SpouseCLP;

       //        //        'L5816 Amount Eligible Dependant
       tmpCALC_YT.L5816_EligibleDependant = tmpCALC_Federal.NRTC_L305_EligibleDependant;

       //        //        'L5825 Amount for children born in 1994 or later
       tmpCALC_YT.L5825_Children = tmpCALC_Federal.NRTC_L367_ChildrenUnder18;

       //        //        'IGNORE: L5820 Amount for infirm dependants age 18 or older

       //        //        'L5824 CPP or QPP contributions Employment
       tmpCALC_YT.L5824_CPPQPPEmployment = tmpCALC_Federal.NRTC_L308_CPPQPPEmployment;

       //        //        'L5828 CPP or QPP contributions Self-Employed
       tmpCALC_YT.L5828_CPPQPPSelfEmployment =	tmpCALC_Federal.NRTC_L310_CPPQPPSelfEmployment;

       //        //        'L5832 Employment Insurance premiums Employment
       tmpCALC_YT.L5832_EIPremiumsEmployment = tmpCALC_Federal.NRTC_L312_EIPremiumsEmployment;

       //        //        'IGNORE: L5829 Employment Insurance premiums Self-Employed

       //        //        'L5835 Canada Employment amount
       tmpCALC_YT.L5835_CanadaEmployment = tmpCALC_Federal.NRTC_L363_CanadaEmployment;

       //        //        'IGNORE: L5838 Children//        //        's fitness amount
       //        //        'IGNORE: L5833 Adoption expenses

       //        //        'L5836 Pension Income
       if (tmpINPUTS.PI_Over_65 == true) // Then
       {
    	   if (tmpINPUTS.IN_Other_Pension >= 2000) // Then
               tmpCALC_YT.L5836_PensionIncome = 2000;
           else
               tmpCALC_YT.L5836_PensionIncome = tmpINPUTS.IN_Other_Pension;
       }    // End If
       else
           tmpCALC_YT.L5836_PensionIncome = 0;
       // End If

       //        //        'IGNORE: L5840 Caregiver amount
       //        //        'IGNORE: L5844 Disability amount
       //        //        'IGNORE: L5848 Disability amount transferred from a dependant
       //        //        'IGNORE: L5852 Interest paid on your student loans

       //        //        'L5856 Tuition and Education amounts
       //        //        'Tuition, education and textbook amounts
       //        //        'Check for total months equal to 12 or under
       //        //        'CFwd ignored but could be added to ???
       //        //        'Text book amounts added to education amounts, e.g. 400+65 and 120+20 per month
       tmpCALC_YT.L5856_TuitionEducation = tmpINPUTS.DC_Education_Tuition_Paid 
    		   + (tmpINPUTS.DC_Education_Full_Months * 465) 
    		   + (tmpINPUTS.DC_Education_Part_Months * 140);

       //        //        'IGNORE: L5860 Tuition and education amounts transferred from a child
       //        //        'IGNORE: L5864 Amounts transferred from your spouse or common-law partner

       //        //        'L5868 Medical (Line 25 of YT428)
       if ((tmpINPUTS.DC_Medical > 0) && (tmpCALC_Federal.Net_Income >= (2052 / 3 * 100)))
       {    
    	   tmpCALC_YT.L5868_Medical = tmpINPUTS.DC_Medical - 2052;
           if (tmpCALC_YT.L5868_Medical < 0) // Then 
        	   tmpCALC_YT.L5868_Medical = 0;
       }
       else if ((tmpINPUTS.DC_Medical > 0) && (tmpCALC_Federal.Net_Income < (2052 / 3 * 100))) // Then
       {
    	   tmpCALC_YT.L5868_Medical = tmpINPUTS.DC_Medical - (0.03 * tmpCALC_Federal.Net_Income);
           if (tmpCALC_YT.L5868_Medical < 0) // Then 
        	   tmpCALC_YT.L5868_Medical = 0;
       }
       else
           tmpCALC_YT.L5868_Medical = 0;
       // End If

       //        //        'IGNORE: L5872 Allowable amount of medical expenses for other dependants

       //        //        'L5876 As we ignored L5872, calculation is simplifed
       tmpCALC_YT.L5876 = tmpCALC_YT.L5868_Medical;

       //        //        'IGNORE: L5879 Graduate Tax Exemption

       //        //        'L5880
       tmpCALC_YT.L5880 = 
           tmpCALC_YT.L5804_Basic + 
           tmpCALC_YT.L5808_Age + 
           tmpCALC_YT.L5812_SpouseCLP + 
           tmpCALC_YT.L5816_EligibleDependant + 
           tmpCALC_YT.L5825_Children + 
           tmpCALC_YT.L5824_CPPQPPEmployment + 
           tmpCALC_YT.L5828_CPPQPPSelfEmployment + 
           tmpCALC_YT.L5832_EIPremiumsEmployment + 
           tmpCALC_YT.L5835_CanadaEmployment + 
           tmpCALC_YT.L5836_PensionIncome + 
           tmpCALC_YT.L5856_TuitionEducation + 
           tmpCALC_YT.L5876 +
           tmpINPUTS.DC_Other_NRTC;


       //        //        'L5884
       tmpCALC_YT.L5884 = tmpCALC_YT.L5880 * 0.0704;

       //        //        'L5896 Donations and Gifts
       if (tmpINPUTS.DC_Donations >= 200) // Then
           tmpCALC_YT.L5896_DonationsGifts = (200 * 0.0704) + ((tmpINPUTS.DC_Donations - 200) * 0.1276);
       else
           tmpCALC_YT.L5896_DonationsGifts = tmpINPUTS.DC_Donations * 0.0704;
       // End If

       //        //        'L6150 Saskatchewan non-refundable tax credits
       tmpCALC_YT.L6150_NRTC = tmpCALC_YT.L5884 + tmpCALC_YT.L5896_DonationsGifts;

       //        //        'L6152 Dividend Tax Credit
       tmpCALC_YT.L6152_DividendTaxCredit = 
           (tmpINPUTS.IN_Investments_Eligible_Dividends * 15.08 / 100) + 
           (tmpINPUTS.IN_Investments_Other_Dividends * 4.51 / 100);

       //        //        'L58 Surtax
       tmpCALC_YT.L58_YTSurtax = ((tmpCALC_YT.Gross_Provincial_Tax - tmpCALC_YT.L6150_NRTC - tmpCALC_YT.L6152_DividendTaxCredit) - 6000) * 0.05;
       if (tmpCALC_YT.L58_YTSurtax < 0) // Then 
    	   tmpCALC_YT.L58_YTSurtax = 0;

       //        //        'L61
       tmpCALC_YT.L61_AdjustedYukonTax = (tmpCALC_YT.Gross_Provincial_Tax - tmpCALC_YT.L6150_NRTC - tmpCALC_YT.L6152_DividendTaxCredit) + tmpCALC_YT.L58_YTSurtax;

       //        //        'L67 Adjusted Net Income
       tmpCALC_YT.L67_AdjustedNetIncome = tmpCALC_Federal.Net_Income - tmpINPUTS.IN_Other_UCCB;

       //        //        'L76 Yukon Low Income Family Tax Credit
       if (tmpCALC_YT.L67_AdjustedNetIncome < 25000) // Then
       {
           //        //        'Only higher net income person can claim this credit
           if (tmpCALC_YT.L67_AdjustedNetIncome >= tmpINPUTS.IN_SpouseCLP_Net_Income) // Then
           {    //        //        'Perform credit calculation
               tmpCALC_YT.L76_LowIncomeFamilyTaxCredit = 300;

               double T;
               T = tmpCALC_YT.L67_AdjustedNetIncome - 15000;
               if (T < 0) // Then T = 0
            	   tmpCALC_YT.L76_LowIncomeFamilyTaxCredit = tmpCALC_YT.L76_LowIncomeFamilyTaxCredit - ((T) * 0.03);
               if (tmpCALC_YT.L76_LowIncomeFamilyTaxCredit < 0) // Then
            	   tmpCALC_YT.L76_LowIncomeFamilyTaxCredit = 0;

               if ((tmpCALC_YT.L61_AdjustedYukonTax * 0.8) < tmpCALC_YT.L76_LowIncomeFamilyTaxCredit) // Then
                   tmpCALC_YT.L76_LowIncomeFamilyTaxCredit = tmpCALC_YT.L61_AdjustedYukonTax * 0.8;
               // End If

           }
           else
               tmpCALC_YT.L76_LowIncomeFamilyTaxCredit = 0;
           // End If
       }
       else
           tmpCALC_YT.L76_LowIncomeFamilyTaxCredit = 0;
       // End If


       //        //        'YT Net Provincial Tax
       tmpCALC_YT.Net_Provincial_Tax = tmpCALC_YT.Gross_Provincial_Tax 
    		   						- tmpCALC_YT.L6150_NRTC 
    		   						- tmpCALC_YT.L6152_DividendTaxCredit 
    		   						+ tmpCALC_YT.L58_YTSurtax 
    		   						- tmpCALC_YT.L76_LowIncomeFamilyTaxCredit;
       if (tmpCALC_YT.Net_Provincial_Tax < 0) // Then
    	   tmpCALC_YT.Net_Provincial_Tax = 0;

//	       Calculation_YT_TY2011 = tmpCALC_YT
//
//	   End Function
    }

    protected void calculateNanuvutTax(	
    				TaxPayerValues2011 tmpINPUTS,
    				NunavutTaxValues2011 tmpCALC_NU,
					FederalTaxValues2011 tmpCALC_Federal)
    {
//
//    	//        //        '----------------------------
//	   //        //        'Calculates the Nunavut taxes
//	   //        //        '----------------------------
//	   Private Function Calculation_NU_TY2011(ByVal tmpINPUTS As
//	struct_USER_INPUT_TY2011, ByVal tmpCALC_Federal As
//	struct_CALC_Federal_TY2011) As struct_CALC_NU_TY2011
//
//	       Dim tmpCALC_NU As struct_CALC_NU_TY2011
//
//	       //        //        'Gross Provincial Tax
//	       //        //        '   <=39612                     4%
//	       //        //        '   >39612 and <=79224      7%
//	       //        //        '   >79224 and <=128800     9%
//	       //        //        '   >128800                     11.5%
	       if (tmpCALC_Federal.Taxable_Income <= 39612) // Then
	           tmpCALC_NU.Gross_Provincial_Tax = tmpCALC_Federal.Taxable_Income * (4 / 100);

	       else if ((tmpCALC_Federal.Taxable_Income > 39612) && (tmpCALC_Federal.Taxable_Income <= 79224)) // Then
	    	   							tmpCALC_NU.Gross_Provincial_Tax = (39612 * 4 / 100) + 
	    	   							((tmpCALC_Federal.Taxable_Income - 39612) * 7 / 100);

	       else if ((tmpCALC_Federal.Taxable_Income > 79224) && (tmpCALC_Federal.Taxable_Income <= 128800)) // Then
	           tmpCALC_NU.Gross_Provincial_Tax = (39612 * 4 / 100) + 
	           ((79224 - 39612) * 7 / 100) + 
	           ((tmpCALC_Federal.Taxable_Income - 79224) * 9 / 100);

	       else if (tmpCALC_Federal.Taxable_Income > 128800) // Then
	           tmpCALC_NU.Gross_Provincial_Tax = (39612 * 4 / 100) + 
	           ((79224 - 39612) * 7 / 100) + 
	           ((128800 - 79224) * 8 / 100) + 
	           ((tmpCALC_Federal.Taxable_Income - 128800) * 11.5 / 100);

	       else
	           tmpCALC_NU.Gross_Provincial_Tax = 0;
	       // End If

	       //        //        'L5804 Basic Personal Amount
	       tmpCALC_NU.L5804_Basic = 11878;

	       //        //        'L5808 Age Amount
	       if (tmpINPUTS.PI_Over_65 ==  false) // Then
	           tmpCALC_NU.L5808_Age = 0;
	       else
	       {    if (tmpCALC_Federal.Net_Income <= 32961) // Then
	               tmpCALC_NU.L5808_Age = 8909;
	           else
	           {    
	        	   tmpCALC_NU.L5808_Age = 8909 - (tmpCALC_Federal.Net_Income - 32961) * 0.15;
	               if (tmpCALC_NU.L5808_Age < 0) // Then 
	            	   tmpCALC_NU.L5808_Age = 0;
	           }// End If
	       }// End If

	       //        //        'L5812 Spouse / CLP
	       if ((tmpINPUTS.PI_Filing_Status.equals(MARRIED)) || (tmpINPUTS.PI_Filing_Status.equals(COMMON_LAW))) // Then
	           tmpCALC_NU.L5812_SpouseCLP = 11878 - tmpINPUTS.IN_SpouseCLP_Net_Income;
	           if (tmpCALC_NU.L5812_SpouseCLP < 0) // Then
	        	   tmpCALC_NU.L5812_SpouseCLP = 0;
	       else
	           tmpCALC_NU.L5812_SpouseCLP = 0;
	       // End If

	       //        //        'L5816 Amount Eligible Dependant
	       if ((tmpINPUTS.PI_Filing_Status.equals(SINGLE)) && (tmpINPUTS.DC_Children > 0)) // Then
	           tmpCALC_NU.L5816_EligibleDependant = 11878;
	           //        //        'Assume that they have no net income
	       else
	           tmpCALC_NU.L5816_EligibleDependant = 0;
	       // End If

	       //        //        'IGNORE: L5820 Amount for infirm dependants age 18 or older

	       //        //        'L5823 Amount for Children Less Than 6 Years Old
	       //        //        'IGNORE? **** Like NS and PEI - add a UI field, e.g. <18 and of which X are <6
	       tmpCALC_NU.L5823_ChildrenLessThan6 = 0;

	       //        //        'L5824 CPP or QPP contributions Employment
	       tmpCALC_NU.L5824_CPPQPPEmployment = tmpCALC_Federal.NRTC_L308_CPPQPPEmployment;

	       //        //        'L5828 CPP or QPP contributions Self-Employed
	       tmpCALC_NU.L5828_CPPQPPSelfEmployment = tmpCALC_Federal.NRTC_L310_CPPQPPSelfEmployment;

	       //        //        'L5832 Employment Insurance premiums Employment
	       tmpCALC_NU.L5832_EIPremiumsEmployment = tmpCALC_Federal.NRTC_L312_EIPremiumsEmployment;

	       //        //        'IGNORE: L5829 Employment Insurance premiums Self-Employed

	       //        //        'L5836 Pension Income
	       if (tmpINPUTS.PI_Over_65 == true) // Then
	       {    
	    	   if (tmpINPUTS.IN_Other_Pension >= 2000) // Then
	               tmpCALC_NU.L5836_PensionIncome = 2000;
	           else
	               tmpCALC_NU.L5836_PensionIncome = tmpINPUTS.IN_Other_Pension;
	           // End If
	       }
	       else
	           tmpCALC_NU.L5836_PensionIncome = 0;
	       // End If

	       //        //        'IGNORE: L5840 Caregiver amount
	       //        //        'IGNORE: L5844 Disability amount
	       //        //        'IGNORE: L5848 Disability amount transferred from a dependant
	       //        //        'IGNORE: L5852 Interest paid on your student loans

	       //        //        'L5856 Tuition and Education amounts
	       //        //        'Tuition, education and textbook amounts
	       //        //        'Check for total months equal to 12 or under
	       //        //        'CFwd ignored but could be added to ???
	       //        //        'Textbooks included here.
	       tmpCALC_NU.L5856_TuitionEducation = tmpINPUTS.DC_Education_Tuition_Paid + 
	    		   							(tmpINPUTS.DC_Education_Full_Months * 465) + 
	    		   							(tmpINPUTS.DC_Education_Part_Months * 140);

	       //        //        'IGNORE: L5860 Tuition and education amounts transferred from a child
	       //        //        'IGNORE: L5864 Amounts transferred from your spouse or common-law partner

	       //        //        'L5868 Medical (Line 21 of NU428)
	       if ((tmpINPUTS.DC_Medical > 0) && (tmpCALC_Federal.Net_Income >= (2052 / 3 * 100))) // Then
	           tmpCALC_NU.L5868_Medical = tmpINPUTS.DC_Medical - 2052;
	           if (tmpCALC_NU.L5868_Medical < 0) // Then 
	        	   tmpCALC_NU.L5868_Medical = 0;
	           else if ((tmpINPUTS.DC_Medical > 0) && (tmpCALC_Federal.Net_Income < (2052 / 3 * 100))) // Then
	        	   tmpCALC_NU.L5868_Medical = tmpINPUTS.DC_Medical - (0.03 * tmpCALC_Federal.Net_Income);
	           if (tmpCALC_NU.L5868_Medical < 0) // Then 
	        	   tmpCALC_NU.L5868_Medical = 0;
	       else
	           tmpCALC_NU.L5868_Medical = 0;
	       // End If

	       //        //        'IGNORE: L5872 Allowable amount of medical expenses for other dependants

	       //        //        'L5876 As we ignored L5872, calculation is simplifed
	       tmpCALC_NU.L5876 = tmpCALC_NU.L5868_Medical;

	       //        //        'L5880
	       tmpCALC_NU.L5880 =
	           tmpCALC_NU.L5804_Basic + 
	           tmpCALC_NU.L5808_Age + 
	           tmpCALC_NU.L5812_SpouseCLP + 
	           tmpCALC_NU.L5816_EligibleDependant + 
	           tmpCALC_NU.L5823_ChildrenLessThan6 + 
	           tmpCALC_NU.L5824_CPPQPPEmployment + 
	           tmpCALC_NU.L5828_CPPQPPSelfEmployment + 
	           tmpCALC_NU.L5832_EIPremiumsEmployment + 
	           tmpCALC_NU.L5836_PensionIncome + 
	           tmpCALC_NU.L5856_TuitionEducation + 
	           tmpCALC_NU.L5876 +
	           tmpINPUTS.DC_Other_NRTC;

	       //        //        'L5884
	       tmpCALC_NU.L5884 = tmpCALC_NU.L5880 * 0.04;

	       //        //        'L5896 Donations and Gifts
	       if (tmpINPUTS.DC_Donations >= 200) // Then
	           tmpCALC_NU.L5896_DonationsGifts = (200 * 0.04) + ((tmpINPUTS.DC_Donations - 200) * 0.115);
	       else
	           tmpCALC_NU.L5896_DonationsGifts = tmpINPUTS.DC_Donations * 0.04;
	       // End If

	       //        //        'L6150 Saskatchewan non-refundable tax credits
	       tmpCALC_NU.L6150_NRTC = tmpCALC_NU.L5884 + tmpCALC_NU.L5896_DonationsGifts;

	       //        //        'L6152 Dividend Tax Credit
	       tmpCALC_NU.L6152_DividendTaxCredit =
	           (tmpINPUTS.IN_Investments_Eligible_Dividends * 5.8 / 100) + 
	           (tmpINPUTS.IN_Investments_Other_Dividends * 4 / 100);

	       //        //        'NU Net Provincial Tax
	       tmpCALC_NU.Net_Provincial_Tax = tmpCALC_NU.Gross_Provincial_Tax 
	    		   - tmpCALC_NU.L6150_NRTC 
	    		   - tmpCALC_NU.L6152_DividendTaxCredit;
	       if (tmpCALC_NU.Net_Provincial_Tax < 0) // Then
	    	   	tmpCALC_NU.Net_Provincial_Tax = 0;

	       //        //        'L5 on NU479 - Adjusted Net Income
	       tmpCALC_NU.L5_AdjustedNetIncome = tmpCALC_Federal.Net_Income 
	    		   - tmpINPUTS.DC_Canadian_Forces 
	    		   - tmpINPUTS.DC_Security_Options;
	       if (tmpCALC_NU.L5_AdjustedNetIncome < 0) // Then
	    	   tmpCALC_NU.L5_AdjustedNetIncome = 0;

	       //        //        'L8 on NU479 - Basic Credit
	       tmpCALC_NU.L8_BasicCredit = tmpCALC_NU.L5_AdjustedNetIncome * 0.02;
	       if (tmpCALC_NU.L8_BasicCredit > 1200) // Then
	    	   tmpCALC_NU.L8_BasicCredit = 1200;

	       //        //        'L6394 Cost Of Living Supplement
	       if ((tmpINPUTS.PI_Filing_Status.equals(SINGLE)) && (tmpINPUTS.DC_Children > 0) && (tmpCALC_NU.L5_AdjustedNetIncome > 60000)) // Then
	       {    tmpCALC_NU.L6394_CostOfLivingSupplement = tmpCALC_NU.L5_AdjustedNetIncome - 60000;
	           if (tmpCALC_NU.L6394_CostOfLivingSupplement < 0) // Then
	        	   tmpCALC_NU.L6394_CostOfLivingSupplement = 0;
	           tmpCALC_NU.L6394_CostOfLivingSupplement = tmpCALC_NU.L6394_CostOfLivingSupplement * 0.02;
	       }
	       else
	           tmpCALC_NU.L6394_CostOfLivingSupplement = 0;
	       // End If

	       //        //        'L6390 Total Cost Of Living Tax Credit
	       tmpCALC_NU.L6390_TotalCostOfLivingTaxCredit = tmpCALC_NU.L6394_CostOfLivingSupplement + tmpCALC_NU.L8_BasicCredit;

	       //        //        'L27_NunavutCredits
	       tmpCALC_NU.L27_NunavutCredits = tmpCALC_NU.L6390_TotalCostOfLivingTaxCredit;

	}
    
    protected void calculateNorthwestTerritoriesTax(
    				TaxPayerValues2011 tmpINPUTS,
    				NorthwestTerritoriesTaxValues2011 tmpCALC_NT,
    				FederalTaxValues2011 tmpCALC_Federal)
    {
			//	   //        //        '------------------------------------------
			//	   //        //        'Calculates the Northwest Territories taxes
			//	   //        //        '------------------------------------------
			//	   Private Function Calculation_NT_TY2011(ByVal tmpINPUTS As
			//	struct_USER_INPUT_TY2011, ByVal tmpCALC_Federal As
			//	struct_CALC_Federal_TY2011) As struct_CALC_NT_TY2011
			//
			//	       Dim tmpCALC_NT As struct_CALC_NT_TY2011
			//
			//	       //        //        'Gross Provincial Tax
			//	       //        //        '   <=37626                     5.9%
			//	       //        //        '   >37626 and <=75253      8.6%
			//	       //        //        '   >75253 and <=122345     12.2%
			//	       //        //        '   >122345                     14.05%
	       if (tmpCALC_Federal.Taxable_Income <= 37626) // Then
	           tmpCALC_NT.Gross_Provincial_Tax = tmpCALC_Federal.Taxable_Income * (5.9 / 100);

	       else if ((tmpCALC_Federal.Taxable_Income > 37626) && (tmpCALC_Federal.Taxable_Income <= 75253)) // Then
	           tmpCALC_NT.Gross_Provincial_Tax = (37626 * 5.9 / 100) + 
	           ((tmpCALC_Federal.Taxable_Income - 37626) * 8.6 / 100);

	       else if ((tmpCALC_Federal.Taxable_Income > 75253) && (tmpCALC_Federal.Taxable_Income <= 122345)) // Then
	           tmpCALC_NT.Gross_Provincial_Tax = (37626 * 5.9 / 100) + 
	           ((75253 - 37626) * 8.6 / 100) + 
	           ((tmpCALC_Federal.Taxable_Income - 75253) * 12.2 / 100);

	       else if (tmpCALC_Federal.Taxable_Income > 122345) // Then
	           tmpCALC_NT.Gross_Provincial_Tax = (37626 * 5.9 / 100) + 
	           ((75253 - 37626) * 8.6 / 100) + 
	           ((122345 - 75253) * 12.2 / 100) + 
	           ((tmpCALC_Federal.Taxable_Income - 122345) * 14.05 / 100);

	       else
	           tmpCALC_NT.Gross_Provincial_Tax = 0;
	       // End If

	       //        //        'L5804 Basic Personal Amount
	       tmpCALC_NT.L5804_Basic = 12919;

	       //        //        'L5808 Age Amount
	       if (tmpINPUTS.PI_Over_65 ==  false) // Then
	           tmpCALC_NT.L5808_Age = 0;
	       else
	       {    if (tmpCALC_Federal.Net_Income <= 32961) // Then
	               tmpCALC_NT.L5808_Age = 6319;
	           else
	           {    tmpCALC_NT.L5808_Age = 6319 - (tmpCALC_Federal.Net_Income - 32961) * 0.15;
	               if (tmpCALC_NT.L5808_Age < 0) // Then 
	            	   tmpCALC_NT.L5808_Age = 0;
	           }// End If
	       }// End If

	       //        //        'L5812 Spouse / CLP
	       if ((tmpINPUTS.PI_Filing_Status.equals(MARRIED)) || (tmpINPUTS.PI_Filing_Status.equals(COMMON_LAW))) // Then
	       {    
	    	   	tmpCALC_NT.L5812_SpouseCLP = 12919 - tmpINPUTS.IN_SpouseCLP_Net_Income;
	         	if (tmpCALC_NT.L5812_SpouseCLP < 0) // Then
	         		tmpCALC_NT.L5812_SpouseCLP = 0;
	       }
	       else
	           tmpCALC_NT.L5812_SpouseCLP = 0;
	       // End If

	       //        //        'L5816 Amount Eligible Dependant
	       if ((tmpINPUTS.PI_Filing_Status.equals(SINGLE)) && (tmpINPUTS.DC_Children > 0)) // Then
	           tmpCALC_NT.L5816_EligibleDependant = 12919;
	           //        //        'Assume that they have no net income
	       else
	           tmpCALC_NT.L5816_EligibleDependant = 0;
	       // End If

	       //        //        'IGNORE: L5820 Amount for infirm dependants age 18 or older

	       //        //        'L5824 CPP or QPP contributions Employment
	       tmpCALC_NT.L5824_CPPQPPEmployment = tmpCALC_Federal.NRTC_L308_CPPQPPEmployment;

	       //        //        'L5828 CPP or QPP contributions Self-Employed
	       tmpCALC_NT.L5828_CPPQPPSelfEmployment = tmpCALC_Federal.NRTC_L310_CPPQPPSelfEmployment;

	       //        //        'L5832 Employment Insurance premiums Employment
	       tmpCALC_NT.L5832_EIPremiumsEmployment = tmpCALC_Federal.NRTC_L312_EIPremiumsEmployment;

	       //        //        'IGNORE: L5829 Employment Insurance premiums Self-Employed

	       //        //        'L5836 Pension Income
	       if (tmpINPUTS.PI_Over_65 == true) // Then
	       {    if (tmpINPUTS.IN_Other_Pension >= 1000) // Then
	               tmpCALC_NT.L5836_PensionIncome = 1000;
	           else
	               tmpCALC_NT.L5836_PensionIncome = tmpINPUTS.IN_Other_Pension;
	           // End If
	       }
	       else
	           tmpCALC_NT.L5836_PensionIncome = 0;
	       // End If

	       //        //        'IGNORE: L5840 Caregiver amount
	       //        //        'IGNORE: L5844 Disability amount
	       //        //        'IGNORE: L5848 Disability amount transferred from a dependant
	       //        //        'IGNORE: L5852 Interest paid on your student loans

	       //        //        'L5856 Tuition and Education amounts
	       //        //        'Tuition, education and textbook amounts
	       //        //        'Check for total months equal to 12 or under
	       //        //        'CFwd ignored but could be added to ???
	       tmpCALC_NT.L5856_TuitionEducation = tmpINPUTS.DC_Education_Tuition_Paid + 
	    		   							(tmpINPUTS.DC_Education_Full_Months * 400) + 
	    		   							(tmpINPUTS.DC_Education_Part_Months * 120);

	       //        //        'IGNORE: L5860 Tuition and education amounts transferred from a child
	       //        //        'IGNORE: L5864 Amounts transferred from your spouse or common-law partner

	       //        //        'L5868 Medical (Line 20 of NT428)
	       if ((tmpINPUTS.DC_Medical > 0) && (tmpCALC_Federal.Net_Income >= (2052 / 3 * 100))) // Then
	       {    
	    	   tmpCALC_NT.L5868_Medical = tmpINPUTS.DC_Medical - 2052;
	           if (tmpCALC_NT.L5868_Medical < 0) // Then 
	        	   tmpCALC_NT.L5868_Medical = 0;
	       }
	       else if ((tmpINPUTS.DC_Medical > 0) && (tmpCALC_Federal.Net_Income < (2052 / 3 * 100))) // Then
	       {
	    	   tmpCALC_NT.L5868_Medical = tmpINPUTS.DC_Medical - (0.03 * tmpCALC_Federal.Net_Income);
	           if (tmpCALC_NT.L5868_Medical < 0) // Then 
	        	   tmpCALC_NT.L5868_Medical = 0;
	       }else
	           tmpCALC_NT.L5868_Medical = 0;
	       // End If

	       //        //        'IGNORE: L5872 Allowable amount of medical expenses for other dependants

	       //        //        'L5876 As we ignored L5872, calculation is simplifed
	       tmpCALC_NT.L5876 = tmpCALC_NT.L5868_Medical;

	       //        //        'L5880
	       tmpCALC_NT.L5880 =
	           tmpCALC_NT.L5804_Basic + 
	           tmpCALC_NT.L5808_Age + 
	           tmpCALC_NT.L5812_SpouseCLP + 
	           tmpCALC_NT.L5816_EligibleDependant + 
	           tmpCALC_NT.L5824_CPPQPPEmployment + 
	           tmpCALC_NT.L5828_CPPQPPSelfEmployment + 
	           tmpCALC_NT.L5832_EIPremiumsEmployment + 
	           tmpCALC_NT.L5836_PensionIncome + 
	           tmpCALC_NT.L5856_TuitionEducation + 
	           tmpCALC_NT.L5876 +
	           tmpINPUTS.DC_Other_NRTC;


	       //        //        'L5884
	       tmpCALC_NT.L5884 = tmpCALC_NT.L5880 * 0.059;

	       //        //        'L5896 Donations and Gifts
	       if (tmpINPUTS.DC_Donations >= 200) // Then
	           tmpCALC_NT.L5896_DonationsGifts = (200 * 0.059) + ((tmpINPUTS.DC_Donations - 200) * 0.1405);
	       else
	           tmpCALC_NT.L5896_DonationsGifts = tmpINPUTS.DC_Donations * 0.059;
	       // End If

	       //        //        'L6150 Saskatchewan non-refundable tax credits
	       tmpCALC_NT.L6150_NRTC = tmpCALC_NT.L5884 + tmpCALC_NT.L5896_DonationsGifts;

	       //        //        'L6152 Dividend Tax Credit
	       tmpCALC_NT.L6152_DividendTaxCredit = 
	           (tmpINPUTS.IN_Investments_Eligible_Dividends * 11.5 / 100) + 
	           (tmpINPUTS.IN_Investments_Other_Dividends * 6 / 100);

	       //        //        'NT Net Provincial Tax
	       tmpCALC_NT.Net_Provincial_Tax =
	    		   					tmpCALC_NT.Gross_Provincial_Tax - 
	    		   					tmpCALC_NT.L6150_NRTC -
	    		   					tmpCALC_NT.L6152_DividendTaxCredit;
	       if (tmpCALC_NT.Net_Provincial_Tax < 0) // Then
	    	   tmpCALC_NT.Net_Provincial_Tax = 0;

	       //        //        'L5 on NT479 - Adjusted Net Income
	       tmpCALC_NT.L5_AdjustedNetIncome = tmpCALC_Federal.Net_Income -
	    		   						tmpINPUTS.DC_Canadian_Forces - 
	    		   						tmpINPUTS.DC_Security_Options;	
	       if (tmpCALC_NT.L5_AdjustedNetIncome < 0) // Then
	    	   tmpCALC_NT.L5_AdjustedNetIncome = 0;

	       //        //        'L5 on NT479 - Adjusted Net Income APPROXIMATION FOR SPOUSE
	       tmpCALC_NT.L5_AdjustedNetIncome_SpouseCLP = tmpINPUTS.IN_SpouseCLP_Net_Income;
	       if (tmpCALC_NT.L5_AdjustedNetIncome_SpouseCLP < 0) // Then
	    	   tmpCALC_NT.L5_AdjustedNetIncome_SpouseCLP = 0;

	       //        //        'L6250 Cost of living tax credit
	       if (tmpCALC_NT.L5_AdjustedNetIncome >= 66000) // Then
	           tmpCALC_NT.L6250_CostOfLivingTaxCredit = 942;
	       else
	           //        //        '   <=12000                 2.6%
	           //        //        '   >12000 and <=48000          1.25%
	           //        //        '   >48000 and >66000       1%
	           if (tmpCALC_NT.L5_AdjustedNetIncome <= 12000) // Then
	               tmpCALC_NT.L6250_CostOfLivingTaxCredit = tmpCALC_NT.L5_AdjustedNetIncome * (2.6 / 100);

	           else if ((tmpCALC_NT.L5_AdjustedNetIncome > 12000) && (tmpCALC_NT.L5_AdjustedNetIncome <= 48000)) // Then
	               tmpCALC_NT.L6250_CostOfLivingTaxCredit = (12000 * 2.6 / 100) + ((tmpCALC_NT.L5_AdjustedNetIncome - 12000) * 1.25 / 100);

	           else if ((tmpCALC_NT.L5_AdjustedNetIncome > 48000) && (tmpCALC_NT.L5_AdjustedNetIncome <= 66000)) // Then
	               tmpCALC_NT.L6250_CostOfLivingTaxCredit = (12000 * 2.6 / 100) + 
	               ((48000 - 12000) * 1.25 / 100) + 
	               ((tmpCALC_NT.L5_AdjustedNetIncome - 48000) * 1 / 100);

	           // End If
	       // End If

	       //        //        'L6250 Cost of living tax credit APPROXIMATION FOR SPOUSE
	       if (tmpCALC_NT.L5_AdjustedNetIncome_SpouseCLP >= 66000) // Then
	           tmpCALC_NT.L6250_CostOfLivingTaxCredit_SpouseCLP = 942;
	       else
	       {    //        //        '   <=12000                 2.6%
	           //        //        '   >12000 and <=48000          1.25%
	           //        //        '   >48000 and >66000       1%
	           if (tmpCALC_NT.L5_AdjustedNetIncome_SpouseCLP <= 12000) // Then
	               tmpCALC_NT.L6250_CostOfLivingTaxCredit_SpouseCLP = tmpCALC_NT.L5_AdjustedNetIncome_SpouseCLP * (2.6 / 100);

	           else if ((tmpCALC_NT.L5_AdjustedNetIncome_SpouseCLP > 12000) && (tmpCALC_NT.L5_AdjustedNetIncome_SpouseCLP <= 48000)) // Then
	               tmpCALC_NT.L6250_CostOfLivingTaxCredit_SpouseCLP = (12000 * 2.6 / 100) + 
	               ((tmpCALC_NT.L5_AdjustedNetIncome_SpouseCLP - 12000) * 1.25 / 100);

	           else if ((tmpCALC_NT.L5_AdjustedNetIncome_SpouseCLP > 48000) && (tmpCALC_NT.L5_AdjustedNetIncome_SpouseCLP <= 66000)) // Then
	               tmpCALC_NT.L6250_CostOfLivingTaxCredit_SpouseCLP = (12000 * 2.6 / 100) + 
	               						((48000 - 12000) * 1.25 / 100) + 
	               						((tmpCALC_NT.L5_AdjustedNetIncome_SpouseCLP - 48000) * 1 / 100);
	       }
	           // End If
	       // End If

	       //        //        'L6249 Cost Of Living Supplement
	       tmpCALC_NT.L6249_CostOfLivingSupplement = 350;
	       if (tmpINPUTS.PI_Filing_Status.equals(MARRIED) || (tmpINPUTS.PI_Filing_Status.equals(COMMON_LAW))) // Then
	       		tmpCALC_NT.L6249_CostOfLivingSupplement = tmpCALC_NT.L6249_CostOfLivingSupplement + 350;
	       tmpCALC_NT.L6249_CostOfLivingSupplement = tmpCALC_NT.L6249_CostOfLivingSupplement 
	    		   - tmpCALC_NT.L6250_CostOfLivingTaxCredit 
	    		   - tmpCALC_NT.L6250_CostOfLivingTaxCredit_SpouseCLP;
	       if (tmpCALC_NT.L6249_CostOfLivingSupplement < 0) // Then
	    	   tmpCALC_NT.L6249_CostOfLivingSupplement = 0;

	       //        //        'L6251 Northwest Territories Credit
	       tmpCALC_NT.L6251_NorthwestTerritoriesCredit = tmpCALC_NT.L6249_CostOfLivingSupplement 
	    		   + tmpCALC_NT.L6250_CostOfLivingTaxCredit;

//	       Calculation_NT_TY2011 = tmpCALC_NT
//
//	   End Function
   	
    }
       
    protected void calculateQuebecFederalTax(	
    			TaxPayerValues2011 tmpINPUTS,
    			QuebecTaxValues2011 tmpCALC_QC,
    			QuebecFederalTaxValues2011 tmpCALC_Federal_QC,
    			FederalTaxValues2011 tmpCALC_Federal)
    {
    
    
    	//      '-----------------------------------
    	//        'Calculates the Federal Quebec taxes
    	//        '-----------------------------------
		//  Private Function Calculation_Federal_QC_TY2011(ByVal tmpINPUTS As struct_USER_INPUT_TY2011) As struct_CALC_Federal_QC_TY2011
		//
		//      Dim tmpCALC_Federal_QC As struct_CALC_Federal_QC_TY2011
		//
		//      //        'Calculate Income
    	tmpCALC_Federal_QC.Income = tmpINPUTS.IN_Yours_Employment_Income +
    	tmpINPUTS.IN_Other_UCCB +
    	tmpINPUTS.IN_Other_OAS +
    	tmpINPUTS.IN_Other_CPP +
	    tmpINPUTS.IN_Other_Pension +
	    tmpINPUTS.IN_Other_RRSP +
	    tmpINPUTS.IN_Other_Other +
	    tmpINPUTS.IN_Investments_Eligible_Dividends +
	    tmpINPUTS.IN_Investments_Other_Dividends +
	    tmpINPUTS.IN_Investments_Interest +
	    tmpINPUTS.IN_Investments_Total_Capital_Gains +
	    tmpINPUTS.IN_Business_Net_Income +
	    tmpINPUTS.IN_Rental_Net_Income;

  //        'Calculate Net Income
    	tmpCALC_Federal_QC.Net_Income = tmpCALC_Federal_QC.Income -
    	tmpINPUTS.DC_RRSP -
    	tmpINPUTS.DC_RRSP_First60Days -
    	tmpINPUTS.DC_RPP -
    	tmpINPUTS.DC_Union_Dues -
    	tmpINPUTS.DC_Moving_Expenses -
	  	tmpINPUTS.DC_Deductions_From_Net_Income;

    	//       'Child Care
    	if (tmpCALC_Federal_QC.Net_Income < tmpINPUTS.IN_SpouseCLP_Net_Income)  
    		tmpCALC_Federal_QC.L214_ChildCare = tmpINPUTS.DC_Child_Care;
    	else 
    		tmpCALC_Federal_QC.L214_ChildCare = 0;

		//        'Update Net Income with any Child Care amount
    	tmpCALC_Federal_QC.Net_Income = tmpCALC_Federal_QC.Net_Income - tmpCALC_Federal_QC.L214_ChildCare;

    	//        'OAS Repayment
		double A;
		A = (tmpCALC_Federal_QC.Net_Income - 67668) * 0.15;
		if (A < 0)  
			A = 0;
		if (A < tmpINPUTS.IN_Other_OAS)  
			tmpCALC_Federal_QC.OAS_Repayment = A;
		else 
			tmpCALC_Federal_QC.OAS_Repayment = tmpINPUTS.IN_Other_OAS;

		//        'Update Net Income with any OAS Repayment
		tmpCALC_Federal_QC.Net_Income = tmpCALC_Federal_QC.Net_Income - tmpCALC_Federal_QC.OAS_Repayment;

		//        'Calculate Taxable Income
		tmpCALC_Federal_QC.Taxable_Income = tmpCALC_Federal_QC.Net_Income  
										- tmpINPUTS.DC_Canadian_Forces 
										- tmpINPUTS.DC_Security_Options
										- tmpINPUTS.DC_Northern_Residents;

		//        'L300 Basic credit
		tmpCALC_Federal_QC.NRTC_L300_Basic = 10527;

		//        'L301 Age amount
		if (tmpINPUTS.PI_Over_65 == false) 
			tmpCALC_Federal_QC.NRTC_L301_Age = 0;
		else
		{	if (tmpCALC_Federal_QC.Net_Income <= 32961) 
				tmpCALC_Federal_QC.NRTC_L301_Age = 6537;
			else
				tmpCALC_Federal_QC.NRTC_L301_Age = 6537 - (tmpCALC_Federal_QC.Net_Income - 32961) * 0.15;
          	if (tmpCALC_Federal_QC.NRTC_L301_Age < 0)  
          		tmpCALC_Federal_QC.NRTC_L301_Age = 0;
		}
          

		//        'L303 Spouse or Common-Law Partner amount
		if ((tmpINPUTS.PI_Filing_Status.equals(MARRIED)) || (tmpINPUTS.PI_Filing_Status.equals(COMMON_LAW))) 
			tmpCALC_Federal_QC.NRTC_L303_SpouseCLP = 10527 - tmpINPUTS.IN_SpouseCLP_Net_Income;
		if (tmpCALC_Federal_QC.NRTC_L303_SpouseCLP < 0)  
			tmpCALC_Federal_QC.NRTC_L303_SpouseCLP = 0;
		else
			tmpCALC_Federal_QC.NRTC_L303_SpouseCLP = 0;
  

		//        'L305 Eligible dependant amount
		if ((tmpINPUTS.PI_Filing_Status.equals(SINGLE)) && (tmpINPUTS.DC_Children > 0)) 
			tmpCALC_Federal_QC.NRTC_L305_EligibleDependant = 10527;
		//        'Assumes that they have no net income
		else
			tmpCALC_Federal_QC.NRTC_L305_EligibleDependant = 0;
  

		//        'L367 Children under 18 amount
		tmpCALC_Federal_QC.NRTC_L367_ChildrenUnder18 = tmpINPUTS.DC_Children * 2131;

		//        'IGNORE: L306 Infirm dependants

		//        'L308 CPP QPP via Employment
		if (tmpINPUTS.IN_Yours_Employment_Income >= 48300) 
			tmpCALC_Federal_QC.NRTC_L308_CPPQPPEmployment = 2217.6;
		else
			tmpCALC_Federal_QC.NRTC_L308_CPPQPPEmployment = (tmpINPUTS.IN_Yours_Employment_Income - 3500) * 0.0495;
		if (tmpCALC_Federal_QC.NRTC_L308_CPPQPPEmployment < 0)  
			tmpCALC_Federal_QC.NRTC_L308_CPPQPPEmployment = 0;
  

		//        'L310 CPP QPP via Self-Employment
		//        'Assumes ONLY one of Emp Income OR Business Income
		//        'Note that the 9.9% has been halved for < 48300
		if (tmpCALC_Federal_QC.NRTC_L308_CPPQPPEmployment == 0) 
		{
			if (tmpINPUTS.IN_Business_Net_Income_QC >= 48300) 
				tmpCALC_Federal_QC.NRTC_L310_CPPQPPSelfEmployment = 4435.2 * 0.5;
			else if (tmpINPUTS.IN_Business_Net_Income_QC < 48300) 
				tmpCALC_Federal_QC.NRTC_L310_CPPQPPSelfEmployment = ((tmpINPUTS.IN_Business_Net_Income_QC - 3500) * 4.95 / 100);
			if (tmpCALC_Federal_QC.NRTC_L310_CPPQPPSelfEmployment < 0)  
				tmpCALC_Federal_QC.NRTC_L310_CPPQPPSelfEmployment = 0;
 		}

		//        'Update Net Income
		tmpCALC_Federal_QC.Net_Income = tmpCALC_Federal_QC.Net_Income - tmpCALC_Federal_QC.NRTC_L310_CPPQPPSelfEmployment;

		//        'L312 EI Premiums via Employment
		if (tmpINPUTS.IN_Yours_Employment_Income >= 44200) 
			tmpCALC_Federal_QC.NRTC_L312_EIPremiumsEmployment = 623.22;
		else
			tmpCALC_Federal_QC.NRTC_L312_EIPremiumsEmployment = tmpINPUTS.IN_Yours_Employment_Income * 0.0141;
  

		//        'IGNORE: L317 EI Premiums via Self-Employment
		//        'IGNORE: L362 Volunteer firefighters//        ' amount

		//        'L375 Quebec Parental Insurance Plan
		tmpCALC_Federal_QC.NRTC_L375_ParentalInsurancePlan = tmpINPUTS.IN_Yours_Employment_Income * 0.537 / 100;
		if (tmpCALC_Federal_QC.NRTC_L375_ParentalInsurancePlan > 343.68)  
			tmpCALC_Federal_QC.NRTC_L375_ParentalInsurancePlan = 343.68;

		//        'IGNORE: L376

		//        'L378
		if (tmpCALC_Federal_QC.NRTC_L308_CPPQPPEmployment == 0) 
			tmpCALC_Federal_QC.NRTC_L378 = tmpINPUTS.IN_Business_Net_Income_QC * 0.537 / 100;
		if (tmpCALC_Federal_QC.NRTC_L378 > 343.68)  
			tmpCALC_Federal_QC.NRTC_L378 = 343.68;
  
		//        'Nathan: Clean up later
		
		double C1 = tmpINPUTS.IN_Business_Net_Income_QC * 0.955 / 100;
		if (C1 > 611.2)  
			C1 = 611.2;
		double C2 = C1 - tmpCALC_Federal_QC.NRTC_L378;

		//        'Adjust Federal Quebec Net Income
		tmpCALC_Federal_QC.Net_Income = tmpCALC_Federal_QC.Net_Income - C2;

		//        'L363 Canada Employment
		if (tmpINPUTS.IN_Yours_Employment_Income >= 1065) 
			tmpCALC_Federal_QC.NRTC_L363_CanadaEmployment = 1065;
		else
			tmpCALC_Federal_QC.NRTC_L363_CanadaEmployment = tmpINPUTS.IN_Yours_Employment_Income;
  

		//        'IGNORE: L364 Public transit amount
		//        'IGNORE: L365 Children//        's fitness amount
		//        'IGNORE: L370 Children//        's arts amount
		//        'IGNORE: L369 Home buyers//        ' amount
		//        'IGNORE: L313 Adoption expenses

		//        'L314 Pension income amount
		//        'Ignoring RIF
		if (tmpINPUTS.PI_Over_65 == true) 
		{
			if (tmpINPUTS.IN_Other_Pension >= 2000) 
				tmpCALC_Federal_QC.NRTC_L314_PensionIncome = 2000;
			else
				tmpCALC_Federal_QC.NRTC_L314_PensionIncome = tmpINPUTS.IN_Other_Pension;
		}
		else
			tmpCALC_Federal_QC.NRTC_L314_PensionIncome = 0;
      
		

		//        'IGNORE: L315 Caregiver amount
		//        'IGNORE: L316 Disability amount (for self)
		//        'IGNORE: L318 Disability amount transferred from a dependant
		//        'IGNORE: L319 Interest paid on your student loans

		//        'L323 Tuition, education, and textbook amounts
		//        'Check for total months equal to 12 or under
		//        'CFwd ignored but could be added to vDAC_Eduction_Tuition_Paid
		tmpCALC_Federal_QC.NRTC_L323_TuitionEducationTextbooks = tmpINPUTS.DC_Education_Tuition_Paid
									+ (tmpINPUTS.DC_Education_Full_Months * 465)
									+ (tmpINPUTS.DC_Education_Part_Months * 140);

		//        'IGNORE: L324 Tuition, education, and textbook amounts transferred from a child
		//        'IGNORE: L326 Amounts transferred from your spouse or common-law partner

		//        'L332 Medical expenses
		if ((tmpINPUTS.DC_Medical > 0) && (tmpCALC_Federal_QC.Net_Income >= 68400)) 
			tmpCALC_Federal_QC.NRTC_L332_MedicalExpenses = tmpINPUTS.DC_Medical - 2052;
		if (tmpCALC_Federal_QC.NRTC_L332_MedicalExpenses < 0)  
			tmpCALC_Federal_QC.NRTC_L332_MedicalExpenses = 0;
		else if ((tmpINPUTS.DC_Medical > 0) && (tmpCALC_Federal_QC.Net_Income < 68400)) 
			tmpCALC_Federal_QC.NRTC_L332_MedicalExpenses = tmpINPUTS.DC_Medical 
							- (0.03 * tmpCALC_Federal_QC.Net_Income);
		if (tmpCALC_Federal_QC.NRTC_L332_MedicalExpenses < 0)  
			tmpCALC_Federal_QC.NRTC_L332_MedicalExpenses = 0;
		else
			tmpCALC_Federal_QC.NRTC_L332_MedicalExpenses = 0;
  

		//        'L335
		tmpCALC_Federal_QC.NRTC_L335 = 
				tmpCALC_Federal_QC.NRTC_L300_Basic +
				tmpCALC_Federal_QC.NRTC_L301_Age +
				tmpCALC_Federal_QC.NRTC_L303_SpouseCLP +
				tmpCALC_Federal_QC.NRTC_L305_EligibleDependant +
				tmpCALC_Federal_QC.NRTC_L367_ChildrenUnder18 +
				tmpCALC_Federal_QC.NRTC_L308_CPPQPPEmployment +
				tmpCALC_Federal_QC.NRTC_L310_CPPQPPSelfEmployment +
				tmpCALC_Federal_QC.NRTC_L312_EIPremiumsEmployment +
				tmpCALC_Federal_QC.NRTC_L375_ParentalInsurancePlan +
				tmpCALC_Federal_QC.NRTC_L378 +
				tmpCALC_Federal_QC.NRTC_L363_CanadaEmployment +
				tmpCALC_Federal_QC.NRTC_L314_PensionIncome +
				tmpCALC_Federal_QC.NRTC_L323_TuitionEducationTextbooks +
				tmpCALC_Federal_QC.NRTC_L332_MedicalExpenses +
				tmpINPUTS.DC_Other_NRTC;

		//        'L338 Federal non-refundable tax credit rate
		tmpCALC_Federal_QC.NRTC_L338 = tmpCALC_Federal_QC.NRTC_L335 * 0.15;

		//        'L349 Donations and gifts
		if (tmpINPUTS.DC_Donations >= 200) 
			tmpCALC_Federal_QC.NRTC_L349_DonationsGifts = (200 * 0.15) + ((tmpINPUTS.DC_Donations - 200) * 0.29);
		else
			tmpCALC_Federal_QC.NRTC_L349_DonationsGifts = tmpINPUTS.DC_Donations * 0.15;
  

		//        'L350 Total federal non-refundable tax credits
		tmpCALC_Federal_QC.NRTC_L350_Total_Federal_NRTC = 
				+ tmpCALC_Federal_QC.NRTC_L338
				+ tmpCALC_Federal_QC.NRTC_L349_DonationsGifts;

		//        'Dividends ??? L425
		tmpCALC_Federal_QC.Dividends = (tmpINPUTS.IN_Investments_Eligible_Dividends * 16.4354 / 100) 
				+ (tmpINPUTS.IN_Investments_Other_Dividends * 13.33 / 100);

		//        'Gross Federal Tax
		//        '   <= 41544	            15%
		//        '   >41544 and <=83088	    22%
		//        '   >83088 and <=128800	    26%
		//        '   >128800	                29%
		if (tmpCALC_Federal_QC.Taxable_Income <= 41544) 
			tmpCALC_Federal_QC.Gross_Federal_Tax = tmpCALC_Federal_QC.Taxable_Income * (15 / 100);
		else if ((tmpCALC_Federal_QC.Taxable_Income > 41544) && (tmpCALC_Federal_QC.Taxable_Income <= 83088)) 
			tmpCALC_Federal_QC.Gross_Federal_Tax = (41544 * 15 / 100) + ((tmpCALC_Federal_QC.Taxable_Income - 41544) * 22 / 100);
		else if ((tmpCALC_Federal_QC.Taxable_Income > 83088) && (tmpCALC_Federal_QC.Taxable_Income <= 128800)) 
			tmpCALC_Federal_QC.Gross_Federal_Tax = (41544 * 15 / 100) + ((83088 - 41544) * 22 / 100) 
							+ ((tmpCALC_Federal_QC.Taxable_Income - 83088) * 26 / 100);
		else if (tmpCALC_Federal_QC.Taxable_Income > 128800)
			tmpCALC_Federal_QC.Gross_Federal_Tax = (41544 * 15 / 100) 
										+ ((83088 - 41544) * 22 / 100) 
										+ ((128800 - 83088) * 26 / 100) 
										+ ((tmpCALC_Federal_QC.Taxable_Income - 128800) * 29 / 100);
		else
			tmpCALC_Federal_QC.Gross_Federal_Tax = 0;
  
		//        'Net Federal Tax
		tmpCALC_Federal_QC.L420_Net_Federal_Tax = tmpCALC_Federal_QC.Gross_Federal_Tax 
								- tmpCALC_Federal_QC.NRTC_L350_Total_Federal_NRTC 
								- tmpCALC_Federal_QC.Dividends;
		if (tmpCALC_Federal_QC.L420_Net_Federal_Tax < 0)  
			tmpCALC_Federal_QC.L420_Net_Federal_Tax = 0;

    }
    
    protected void calculateQuebecTax(
    				TaxPayerValues2011 tmpINPUTS,
    				QuebecFederalTaxValues2011 tmpCALC_Federal_QC,
    				QuebecTaxValues2011 tmpCALC_QC,
    				FederalTaxValues2011 tmpCALC_Federal)
    {

    	//        '---------------------------
    	//        'Calculates the Quebec taxes
    	//        '---------------------------
//	Private Function Calculation_QC_TY2011(ByVal tmpINPUTS As struct_USER_INPUT_TY2011, ByVal tmpCALC_Federal_QC As struct_CALC_Federal_QC_TY2011) As struct_CALC_QC_TY2011
//
//      Dim tmpCALC_QC As struct_CALC_QC_TY2011
//
      //        'Total income
    	//        '------------

    	//        'Taxable benefits for Quebec residents
    	tmpCALC_QC.L102_TaxableBenefits = tmpINPUTS.IN_QC_Taxable_Benefits;

    	//        'L101 Employment Income
    	tmpCALC_QC.L101_EmpIncome = tmpCALC_Federal_QC.Income + tmpCALC_QC.L102_TaxableBenefits;

    	//        'L105 Employment Income Correction - RL22 Box A
    	tmpCALC_QC.L105_EmpIncomeCorrection = 0 - tmpCALC_QC.L102_TaxableBenefits;

    	//        'L107 Other Employment Income
    	tmpCALC_QC.L107_OtherEmpIncome = 0;

    	//        'Nathan: Have to let user know to add PIB into here
    	//        'L110 Parental insurance benefits
    	//        'L111 Employment Insurance benefits
    	//        'L142 Support payments received (taxable amount)
    	//        'L154 Other income
    	tmpCALC_QC.L110_L111_L142_L154 = tmpINPUTS.IN_Other_Other;

    	//        'L114 Old Age Security pension
    	tmpCALC_QC.L114_OldAgeSecurityPension = tmpINPUTS.IN_Other_OAS;

    	//        'L119 QPP or CPP benefits
    	tmpCALC_QC.L119_QPPorCPPBenefits = tmpINPUTS.IN_Other_CPP;

    	//        'L122_Pension Plan Payments: RRSP, RRIF, DPSP, annuities
    	tmpCALC_QC.L122_PensionPlanPayments = tmpINPUTS.IN_Other_Pension;

    	//        'IGNORE: L123 Retirement income transferred by your spouse

    	//        'L128 Dividends from taxable Canadian corporations
    	tmpCALC_QC.L128_DividendsTaxableCanadianCorps = tmpINPUTS.IN_Investments_Eligible_Dividends 
    												+ tmpINPUTS.IN_Investments_Other_Dividends;

    	//        'L130 Interest and other investment income
    	tmpCALC_QC.L130_InterestandOtherInvestmentInc = tmpINPUTS.IN_Investments_Interest;

    	//        'L136 Rental Net Income
    	tmpCALC_QC.L136_RentalNetIncome = tmpINPUTS.IN_Rental_Net_Income;

    	//        'L139 Taxable capital gains
    	tmpCALC_QC.L139_TaxableCapitalGains = tmpINPUTS.IN_Investments_Total_Capital_Gains;

		 //        'IGNORE: L147 Social assistance payments
    	//        'IGNORE: L148 Income replacement indemnities and net federal supplements

    	//        'L164 Business Net Income
    	tmpCALC_QC.L164_BusinessNetIncome = tmpINPUTS.IN_Business_Net_Income_QC;

    	//        'L199 Total Income
    	tmpCALC_QC.L199_Total_Income = tmpCALC_QC.L101_EmpIncome +
    			tmpCALC_QC.L105_EmpIncomeCorrection +
    			tmpCALC_QC.L107_OtherEmpIncome +
    			tmpCALC_QC.L110_L111_L142_L154 +
    			tmpCALC_QC.L114_OldAgeSecurityPension +
    			tmpCALC_QC.L119_QPPorCPPBenefits +
    			tmpCALC_QC.L122_PensionPlanPayments +
    			tmpCALC_QC.L128_DividendsTaxableCanadianCorps +
    			tmpCALC_QC.L130_InterestandOtherInvestmentInc +
    			tmpCALC_QC.L136_RentalNetIncome +
    			tmpCALC_QC.L139_TaxableCapitalGains +
    			tmpCALC_QC.L164_BusinessNetIncome;

    	//        'Net income
    	//        '----------

    	//        'L201 Deductions for workers
    	//        'Nathan: Limits needs updating for 2011, 2010 used for now
    	tmpCALC_QC.L201_DeductionsWorkers = 0.06 * (tmpINPUTS.IN_Yours_Employment_Income 
    									+ tmpINPUTS.IN_Business_Net_Income_QC);
    	if (tmpCALC_QC.L201_DeductionsWorkers > 1030)  
    		tmpCALC_QC.L201_DeductionsWorkers = 1030;

    	//        'L205 RPP deduction
    	tmpCALC_QC.L205_RPPDeduction = tmpINPUTS.DC_RPP;

    	//        'L207 Exployment expenses and deductions
    	//        'L225 Support payments made (deductible amount)
    	//        'L231 Carrying charges and interest expenses
    	//        'L250 Other deductions
    	tmpCALC_QC.L207_L225_L231_L250 = tmpINPUTS.DC_Deductions_From_Net_Income;

    	//        'L214 RRSP deduction
    	tmpCALC_QC.L214_RRSPDeduction = tmpINPUTS.DC_RRSP + tmpINPUTS.DC_RRSP_First60Days;

    	//        'L228 Moving expenses
    	tmpCALC_QC.L228_MovingExpenses = tmpINPUTS.DC_Moving_Expenses;

    	//        'IGNORE: L234 Business investment loss

    	//        'L236 Deduction for residents of designated remote areas
    	tmpCALC_QC.L236_DeductionRemoteAreas = tmpINPUTS.DC_Northern_Residents;

    	//        'IGNORE: L241 Deduction for exploration and development expenses

    	//        'L248 Deduction amounts contributed  to the QPP and QPIP on income from self-employed

    	double A = (tmpINPUTS.IN_Business_Net_Income_QC * 0.955 / 100);
		double B = 611.2;
		double C;
		if (A < B)
			C = A; 
		else 
			C = B;
		C = C * 43.77 / 100;
		tmpCALC_QC.L248_DeductionAmtsQPPQPIPIncomeSelfEmployed = tmpCALC_Federal_QC.NRTC_L310_CPPQPPSelfEmployment + C;

		//        'IGNORE: L252 Carry-over of the adjustment of investment expenses

		//        'L275 Net Income
		tmpCALC_QC.L275_Net_Income = tmpCALC_QC.L199_Total_Income -
						(tmpCALC_QC.L201_DeductionsWorkers +
						tmpCALC_QC.L205_RPPDeduction +
						tmpCALC_QC.L207_L225_L231_L250 +
						tmpCALC_QC.L214_RRSPDeduction +
						tmpCALC_QC.L228_MovingExpenses +
						tmpCALC_QC.L236_DeductionRemoteAreas +
						tmpCALC_QC.L248_DeductionAmtsQPPQPIPIncomeSelfEmployed +
						tmpCALC_Federal_QC.OAS_Repayment);
		
		if (tmpCALC_QC.L275_Net_Income < 0)  tmpCALC_QC.L275_Net_Income = 0;


		//        'Taxable income
		//        '--------------

		//        'L279
		tmpCALC_QC.L279 = tmpCALC_QC.L275_Net_Income + tmpINPUTS.IN_Other_UCCB;

		//        'L298 Total Deductions
		tmpCALC_QC.L298_TotalDeductions = tmpINPUTS.DC_Canadian_Forces + (tmpINPUTS.DC_Security_Options * 0.5);

		//        'L299 Taxable Income
		tmpCALC_QC.L299_Taxable_Income = tmpCALC_QC.L279 - tmpCALC_QC.L298_TotalDeductions;
		if (tmpCALC_QC.L299_Taxable_Income < 0)  
			tmpCALC_QC.L299_Taxable_Income = 0;

		//        'Non-refundable tax credits
		//        '--------------------------

		//        'L350 Basic Personal Amount
		tmpCALC_QC.L350_BasicPersonalAmount = 10640;

		//        'IGNORE: L358 Adjustment for income replacement indemnities

		//        'L359
		tmpCALC_QC.L359 = tmpCALC_QC.L350_BasicPersonalAmount;

		//        'L361 Age Amount
		double C11 = 0;
		double C10 = ((tmpCALC_QC.L275_Net_Income + tmpINPUTS.IN_SpouseCLP_Net_Income) - 30875) * 0.15;
		//        'Assume if single, then living alone
		if (tmpINPUTS.PI_Filing_Status.equals(SINGLE))  
			C11 = C11 + 1245;
		//        'Over 65
		if (tmpINPUTS.PI_Over_65 == true)  
			C11 = C11 + 2290;
		//        'Assume if they are 65 or over then there spouse is as well
		if (tmpINPUTS.PI_Over_65 == true)  
			C11 = C11 + 2290;
		//        'Qualifying Pension income
		//        'Spouse ignored
		if (tmpINPUTS.IN_Other_Pension > 2035) 
			C11 = C11 + 2035;
		else
			C11 = C11 + tmpINPUTS.IN_Other_Pension;
  
		tmpCALC_QC.L361_AgeAmount = C10 - C11;
		if (tmpCALC_QC.L361_AgeAmount < 0)  
			tmpCALC_QC.L361_AgeAmount = 0;

		//        'IGNORE: L367 Amounts for dependants and amounts transferred by a child 18 or over

		//        'L373 Union, professional or other dues
		tmpCALC_QC.L373_UnionOrOtherDues = tmpINPUTS.DC_Union_Dues;

		//        'IGNORE: L376 Impairment

		//        'L378 Medical Expenses note available in your area
		//        'Assumed any value is lumped with input for L381
		tmpCALC_QC.L378 = 0;

		//        'L381 Medical Expenses
		double C20 = tmpCALC_Federal_QC.NRTC_L332_MedicalExpenses + tmpCALC_QC.L102_TaxableBenefits;
		double C21 = (tmpCALC_QC.L275_Net_Income + tmpINPUTS.IN_SpouseCLP_Net_Income) * 0.03;
		tmpCALC_QC.L381_MedicalExpenses = C20 - C21;
		if (tmpCALC_QC.L381_MedicalExpenses < 0)  
			tmpCALC_QC.L381_MedicalExpenses = 0;

		//        'L384 Tuition or examination fees
		tmpCALC_QC.L384_Tuition = tmpINPUTS.DC_Education_Tuition_Paid;

		//        'IGNORE: L385 Interest paid on a student loan
		//        'IGNORE: L387 Tuition or examination fees transferred by a child
		
		//        'L390
		tmpCALC_QC.L390 = tmpCALC_QC.L359 +
				tmpCALC_QC.L361_AgeAmount +
				tmpCALC_QC.L373_UnionOrOtherDues +
				tmpCALC_QC.L378 +
				tmpCALC_QC.L381_MedicalExpenses +
				tmpCALC_QC.L384_Tuition +
				tmpINPUTS.DC_Other_NRTC;

		//        'L391
		tmpCALC_QC.L391 = tmpCALC_QC.L390 * 0.2;

		//        'IGNORE: L392 Tax credit for recent graduates working in remote resource regions

		//        'L395 and L397 Donations and gifts
		if (tmpINPUTS.DC_Donations >= 200) 
			tmpCALC_QC.L395_L397_DonationsGifts = (200 * 0.2) + ((tmpINPUTS.DC_Donations - 200) * 0.24);
		else
			tmpCALC_QC.L395_L397_DonationsGifts = tmpINPUTS.DC_Donations * 0.2;
  

		//        'L399 Non-refundable tax credits
		tmpCALC_QC.L399_NRTC = tmpCALC_QC.L391 + tmpCALC_QC.L395_L397_DonationsGifts;


	 	//        'Gross Provincial Tax
		//        '--------------------
		//        'Gross Federal Tax
		//        '   <= 39060	            16%
		//        '   >39060 and <=78120	    20%
		//        '   >78120	                24%
		if (tmpCALC_QC.L299_Taxable_Income <= 39060) 
			tmpCALC_QC.Gross_Provincial_Tax = tmpCALC_QC.L299_Taxable_Income * (16 / 100);
		else if ((tmpCALC_QC.L299_Taxable_Income > 39060) && (tmpCALC_QC.L299_Taxable_Income <= 78120)) 
			tmpCALC_QC.Gross_Provincial_Tax = (39060 * 16 / 100) + ((tmpCALC_QC.L299_Taxable_Income - 39060) * 20 / 100);
		else if (tmpCALC_QC.L299_Taxable_Income > 78120) 
			tmpCALC_QC.Gross_Provincial_Tax = (39060 * 16 / 100) + ((78120 - 39060) * 20 / 100) + ((tmpCALC_QC.L299_Taxable_Income - 78120) * 24 / 100);
		else
			tmpCALC_QC.Gross_Provincial_Tax = 0;
  

		//        'Net Provincial Tax
		//        '------------------
		tmpCALC_QC.Net_Provincial_Tax = tmpCALC_QC.Gross_Provincial_Tax - tmpCALC_QC.L399_NRTC;
		if (tmpCALC_QC.Net_Provincial_Tax < 0)  
			tmpCALC_QC.Net_Provincial_Tax = 0;

		//        'Income tax and contributions
		//        '----------------------------

		//        'L415 Dividend Tax Credit
		//        'Nathan: Check as may change later in the year
		tmpCALC_QC.L415_DividendTaxCredit = 
				+ (tmpINPUTS.IN_Investments_Eligible_Dividends * 11.9 / 100) 
				+ (tmpINPUTS.IN_Investments_Other_Dividends * 8 / 100);

		//        'L425
		//        'Simplified as other things ignored, e.g. Labour-Sponsored fund
		tmpCALC_QC.L425 = tmpCALC_QC.L415_DividendTaxCredit;

		//        'L430
		tmpCALC_QC.L430 = tmpCALC_QC.Net_Provincial_Tax - tmpCALC_QC.L425;

		//        'L431 Credits transferred from one spouse to the other
		double C31 = tmpINPUTS.IN_SpouseCLP_Net_Income * 0.16;
		double C32 = 10640 * 0.2;
		tmpCALC_QC.L431_CreditsTransferredFromSpouse = C31 - C32;
		//        'Check if -ve or +ve
		if (tmpCALC_QC.L431_CreditsTransferredFromSpouse > 0)  
			tmpCALC_QC.L431_CreditsTransferredFromSpouse = 0;

		//        'L432
		tmpCALC_QC.L432 = tmpCALC_QC.L430 - tmpCALC_QC.L431_CreditsTransferredFromSpouse;

		//        'IGNORE: L438 Enterprise registration fee

		//        'L439 QPIP premium on income from self-employment or employment outside Quebec
		tmpCALC_QC.L439_QPIPPremiumSEOutsideQuebec = (tmpINPUTS.IN_Business_Net_Income * 0.955 / 100);
		if (tmpCALC_QC.L439_QPIPPremiumSEOutsideQuebec > 611.2)  
			tmpCALC_QC.L439_QPIPPremiumSEOutsideQuebec = 611.2;

		//        'IGNORE: L441 Advance payment of tax credits
		//        'IGNORE: L443 Special taxes

		//        'L445 QPP contribution on income from self-employment
		tmpCALC_QC.L445_QPPContributionSE = (tmpINPUTS.IN_Business_Net_Income_QC - 3500) * (9.9 / 100);
		if (tmpCALC_QC.L445_QPPContributionSE > 4326.3)  
			tmpCALC_QC.L445_QPPContributionSE = 4326.3;

//        'L446 Contribution to the health services fund
//        'Nathan: Tidy up code later
        double F1 = 0;
        double F2 = 0;
        double F3 = 0;
        double F4 = 0;
        double F5 = 0;
        double F6 = 0;
        if (tmpCALC_QC.L199_Total_Income > 13140) 
        {    
        	F1 = tmpCALC_QC.L199_Total_Income 
        			- tmpCALC_QC.L101_EmpIncome 
        			- tmpINPUTS.IN_QC_Taxable_Benefits;
            if (tmpCALC_QC.L105_EmpIncomeCorrection < 0) 
                F1 = F1 + tmpCALC_QC.L105_EmpIncomeCorrection;
            else
                F1 = F1 - tmpCALC_QC.L105_EmpIncomeCorrection;
            

            F2 = tmpCALC_QC.L199_Total_Income - F1;
            if (F2 > 13140)                 
            {
        		F3 = tmpINPUTS.IN_Other_OAS;
                F4 = tmpCALC_QC.L128_DividendsTaxableCanadianCorps - 
                    (tmpINPUTS.IN_Investments_Eligible_Dividends / 1.41) - 
                    (tmpINPUTS.IN_Investments_Other_Dividends / 1.25);
                F3 = F3 + F4;
                F5 = F2 - F3;
                if (F5 > 13140) 
                {   
                	F6 = F5 - (tmpCALC_QC.L248_DeductionAmtsQPPQPIPIncomeSelfEmployed 
                			+ tmpCALC_QC.L207_L225_L231_L250);
                    if (F6 > 13140) 
                    {
        				if ((F6 > 13140) && (F6 <= 45680)) 
                        {    
        					tmpCALC_QC.L446_HealthServicesFund = (F6 - 13140) * 0.01;
                            if (tmpCALC_QC.L446_HealthServicesFund >= 150)
        						tmpCALC_QC.L446_HealthServicesFund = 150;
                        }
        				else
        				{    
        					tmpCALC_QC.L446_HealthServicesFund = ((F6 - 45680) * 0.01) + 150;
                            if (tmpCALC_QC.L446_HealthServicesFund >= 1000) 
                            	tmpCALC_QC.L446_HealthServicesFund = 1000;
        				}  
                    }
        			else
        			{
        				tmpCALC_QC.L446_HealthServicesFund = 0;
                    } 
                }
        		else
        		{
        			tmpCALC_QC.L446_HealthServicesFund = 0;
        		}
    		}
    		else
    		{
    			tmpCALC_QC.L446_HealthServicesFund = 0;
    		} 
		}
        else
        {
        	tmpCALC_QC.L446_HealthServicesFund = 0;
		} 

		//        'L447 Premium payable under the Quebec prescription drug insurance plan
		//        'Schedule K
        if (tmpINPUTS.IN_QC_Taxable_Benefits > 0) 
        {
        	tmpCALC_QC.L447_DrugInsurancePlan = 0;
        }
        else
        {
        	if (tmpINPUTS.PI_Filing_Status.equals(SINGLE)) 
        	{    
        		tmpCALC_QC.L447_DrugInsurancePlan = tmpCALC_QC.L275_Net_Income - 14080;
                if (tmpINPUTS.DC_Children == 1) 
                	tmpCALC_QC.L447_DrugInsurancePlan = tmpCALC_QC.L447_DrugInsurancePlan - 8740;
                if (tmpINPUTS.DC_Children > 1) 
                	tmpCALC_QC.L447_DrugInsurancePlan = tmpCALC_QC.L447_DrugInsurancePlan - 11795;

                if (tmpCALC_QC.L447_DrugInsurancePlan < 5000) 
                {
                	tmpCALC_QC.L447_DrugInsurancePlan = tmpCALC_QC.L447_DrugInsurancePlan * 6.13 / 100;
                    if (tmpCALC_QC.L447_DrugInsurancePlan > 592.5) 
                    	tmpCALC_QC.L447_DrugInsurancePlan = 592.5;
                }
                else
                {    tmpCALC_QC.L447_DrugInsurancePlan = ((tmpCALC_QC.L447_DrugInsurancePlan - 5000) * 9.23 / 100) + 306.5;
                    if (tmpCALC_QC.L447_DrugInsurancePlan > 592.5) 
                    	tmpCALC_QC.L447_DrugInsurancePlan = 592.5;
                }//End if
        	}
        	else
        	{
        		tmpCALC_QC.L447_DrugInsurancePlan = tmpCALC_QC.L275_Net_Income + tmpINPUTS.IN_SpouseCLP_Net_Income - 22820;
                if (tmpINPUTS.DC_Children == 1) 
                	tmpCALC_QC.L447_DrugInsurancePlan = tmpCALC_QC.L447_DrugInsurancePlan - 3055;
                if (tmpINPUTS.DC_Children > 1) 
                	tmpCALC_QC.L447_DrugInsurancePlan = tmpCALC_QC.L447_DrugInsurancePlan - 5875;

                if (tmpCALC_QC.L447_DrugInsurancePlan < 5000) 
                {
                    tmpCALC_QC.L447_DrugInsurancePlan = tmpCALC_QC.L447_DrugInsurancePlan * 3.09 / 100;
                    if (tmpCALC_QC.L447_DrugInsurancePlan > 592.5) 
                    	tmpCALC_QC.L447_DrugInsurancePlan = 592.5;
                }
                else
                {    
                	tmpCALC_QC.L447_DrugInsurancePlan = ((tmpCALC_QC.L447_DrugInsurancePlan - 5000) * 4.63 / 100) + 154.5;
                    if (tmpCALC_QC.L447_DrugInsurancePlan > 592.5) 
                    	tmpCALC_QC.L447_DrugInsurancePlan = 592.5;
                }//End if
        	} //End if
            if (tmpCALC_QC.L447_DrugInsurancePlan < 0) 
            	tmpCALC_QC.L447_DrugInsurancePlan = 0;
        }// End if

        //		'L448 Health contribution
        tmpCALC_QC.L448_HealthContribution = 0;
        if (tmpINPUTS.PI_Filing_Status.equals(SINGLE)) 
        {
        	if (tmpINPUTS.DC_Children == 0) 
        	{
        		if (tmpCALC_QC.L275_Net_Income <= 14080)  
        			tmpCALC_QC.L448_HealthContribution = 0;
                if (tmpCALC_QC.L275_Net_Income > 14080)  
                	tmpCALC_QC.L448_HealthContribution = 100;
        	}
        	else if (tmpINPUTS.DC_Children == 1) 
        	{
        		if (tmpCALC_QC.L275_Net_Income <= 22820)  
        			tmpCALC_QC.L448_HealthContribution = 0;
                if (tmpCALC_QC.L275_Net_Income > 22820)  
                	tmpCALC_QC.L448_HealthContribution = 100;
        	}
        	else if (tmpINPUTS.DC_Children > 1) 
        	{
        		if (tmpCALC_QC.L275_Net_Income <= 25875)  
        			tmpCALC_QC.L448_HealthContribution = 0;
                if (tmpCALC_QC.L275_Net_Income > 25875)  
                	tmpCALC_QC.L448_HealthContribution = 100;
            } // End if
    	}
    	else
    	{
    		if (tmpINPUTS.DC_Children == 0) 
    		{
    			if ((tmpCALC_QC.L275_Net_Income + tmpINPUTS.IN_SpouseCLP_Net_Income) <= 22820)  
    				tmpCALC_QC.L448_HealthContribution = 0;
                if ((tmpCALC_QC.L275_Net_Income + tmpINPUTS.IN_SpouseCLP_Net_Income) > 22820)  
                	tmpCALC_QC.L448_HealthContribution = 100;
    		}
    		else if (tmpINPUTS.DC_Children == 1) 
    		{
    			if ((tmpCALC_QC.L275_Net_Income + tmpINPUTS.IN_SpouseCLP_Net_Income) <= 25875)  
    				tmpCALC_QC.L448_HealthContribution = 0;
                if ((tmpCALC_QC.L275_Net_Income + tmpINPUTS.IN_SpouseCLP_Net_Income) > 25875)  
                	tmpCALC_QC.L448_HealthContribution = 100;
    		}
    		else if (tmpINPUTS.DC_Children > 1) 
    		{
    			if ((tmpCALC_QC.L275_Net_Income + tmpINPUTS.IN_SpouseCLP_Net_Income) <= 28695)  
    				tmpCALC_QC.L448_HealthContribution = 0;
                if ((tmpCALC_QC.L275_Net_Income + tmpINPUTS.IN_SpouseCLP_Net_Income) > 28695)  
                	tmpCALC_QC.L448_HealthContribution = 100;
            } // End if
        } // End If
		
          //        'L450 Income tax and contributions
          tmpCALC_QC.L450_Income_Tax_And_Contributions = tmpCALC_QC.L432 +
	          tmpCALC_QC.L439_QPIPPremiumSEOutsideQuebec +
	          tmpCALC_QC.L445_QPPContributionSE +
	          tmpCALC_QC.L446_HealthServicesFund +
	          tmpCALC_QC.L447_DrugInsurancePlan +
	          tmpCALC_QC.L448_HealthContribution;


      }

    public TaxPayerValues2011 getTaxPayerValues2011(Map<String, String> values)
    {
    	return null;
    	
    	
    }

//    protected boolean calculateTaxEstimate(, Map values)
//    {
//    	boolean success = true;
//    	
//    	TaxPayerValues2011 personal = getTaxPayerValues(values);
//    	
//    	
//    	String province = personal.PI_Province;    	
////    	if (province.equals(Province.QC))
////    	{
////            //		'Federal for Quebec
////            Dim CALC_Federal_QC_TY2011 As struct_CALC_Federal_QC_TY2011
////            CALC_Federal_QC_TY2011 = Calculation_Federal_QC_TY2011(personal.)
////            TEE_Debug.Show_Federal_QC_Results_TY2011(CALC_Federal_QC_TY2011)
////
////            'TAX ESTIMATOR - REFUND or BALANCE        
////            Dim Estimation As Double
////            Dim C1 As Double
////            Dim C2 As Double
////            C1 = personal..IN_Yours_CPP_Deducted - (CALC_Federal_QC_TY2011.NRTC_L308_CPPQPPEmployment + CALC_Federal_QC_TY2011.NRTC_L310_CPPQPPSelfEmployment)
////            If C1 <= 0 Then C1 = 0
////            C2 = personal..IN_Yours_EI_Deducted - CALC_Federal_QC_TY2011.NRTC_L312_EIPremiumsEmployment
////            If C2 <= 0 Then C2 = 0
////
////            'Quebec Provincial
////            Dim CALC_QC_TY2011 As struct_CALC_QC_TY2011
////            CALC_QC_TY2011 = Calculation_QC_TY2011(personal., CALC_Federal_QC_TY2011)
////            TEE_Debug.Show_QC_Results_TY2011(CALC_QC_TY2011)
////
////            Estimation = 0
////
////            'TEE_Debug.LVA("TAX ESTIMATION", "", "", "", Color.White, Color.Crimson)
////            'TEE_Debug.LVA("Estimation", "", Estimation, "", Color.Black, Color.LightSalmon)
////            'TEE_Debug.LVA("(-ve is a REFUND; +ve is OWING)", "", "", "", Color.Black, Color.LightSalmon)
////            'TEE_Debug.LVA("", "", "", "", Color.Black, Color.White)
////            TEE_Debug.LVA("TEE Version", "", TEE_VERSION, "", Color.Black, Color.White)
////            TEE_Debug.LVA("Date / Time", "", Now, "", Color.Black, Color.White)
//
//    	if (province.equals(Province.QC))
//
//            'Federal
//            Dim CALC_Federal_TY2011 As struct_CALC_Federal_TY2011
//            CALC_Federal_TY2011 = Calculation_Federal_TY2011(personal.)
//            TEE_Debug.Show_Federal_Results_TY2011(CALC_Federal_TY2011)
//
//            'TAX ESTIMATOR - REFUND or BALANCE        
//            Dim Estimation As Double
//            Dim C1 As Double
//            Dim C2 As Double
//            C1 = personal..IN_Yours_CPP_Deducted - (CALC_Federal_TY2011.NRTC_L308_CPPQPPEmployment + CALC_Federal_TY2011.NRTC_L310_CPPQPPSelfEmployment)
//            If C1 <= 0 Then C1 = 0
//            C2 = personal..IN_Yours_EI_Deducted - CALC_Federal_TY2011.NRTC_L312_EIPremiumsEmployment
//            If C2 <= 0 Then C2 = 0
//
//            Select Case personal..PI_Province
//                Case Is = "AB"
//                    'Alberta
//                    Dim CALC_AB_TY2011 As struct_CALC_AB_TY2011
//                    CALC_AB_TY2011 = Calculation_AB_TY2011(personal., CALC_Federal_TY2011)
//                    TEE_Debug.Show_AB_Results_TY2011(CALC_AB_TY2011)
//                    Estimation = (CALC_Federal_TY2011.L420_Net_Federal_Tax + CALC_AB_TY2011.Net_Provincial_Tax + CALC_Federal_TY2011.NRTC_L310_CPPQPPSelfEmployment + CALC_Federal_TY2011.OAS_Repayment) - (personal..IN_Yours_Tax_Deducted + C1 + C2)
//                Case Is = "BC"
//                    'British Columbia
//                    Dim CALC_BC_TY2011 As struct_CALC_BC_TY2011
//                    CALC_BC_TY2011 = Calculation_BC_TY2011(personal., CALC_Federal_TY2011)
//                    TEE_Debug.Show_BC_Results_TY2011(CALC_BC_TY2011)
//                    Estimation = (CALC_Federal_TY2011.L420_Net_Federal_Tax + CALC_BC_TY2011.Net_Provincial_Tax + CALC_Federal_TY2011.NRTC_L310_CPPQPPSelfEmployment + CALC_Federal_TY2011.OAS_Repayment) - (personal..IN_Yours_Tax_Deducted + C1 + C2)
//                Case Is = "ON"
//                    'Ontario
//                    Dim CALC_ON_TY2011 As struct_CALC_ON_TY2011
//                    CALC_ON_TY2011 = Calculation_ON_TY2011(personal., CALC_Federal_TY2011)
//                    TEE_Debug.Show_ON_Results_TY2011(CALC_ON_TY2011)
//                    Estimation = (CALC_Federal_TY2011.L420_Net_Federal_Tax + CALC_ON_TY2011.Net_Provincial_Tax + CALC_Federal_TY2011.NRTC_L310_CPPQPPSelfEmployment + CALC_Federal_TY2011.OAS_Repayment) - (personal..IN_Yours_Tax_Deducted + C1 + C2)
//                Case Is = "SK"
//                    'Saskatchewan
//                    Dim CALC_SK_TY2011 As struct_CALC_SK_TY2011
//                    CALC_SK_TY2011 = Calculation_SK_TY2011(personal., CALC_Federal_TY2011)
//                    TEE_Debug.Show_SK_Results_TY2011(CALC_SK_TY2011)
//                    Estimation = (CALC_Federal_TY2011.L420_Net_Federal_Tax + CALC_SK_TY2011.Net_Provincial_Tax + CALC_Federal_TY2011.NRTC_L310_CPPQPPSelfEmployment + CALC_Federal_TY2011.OAS_Repayment) - (personal..IN_Yours_Tax_Deducted + C1 + C2)
//                Case Is = "MB"
//                    'Manitoba
//                    Dim CALC_MB_TY2011 As struct_CALC_MB_TY2011
//                    CALC_MB_TY2011 = Calculation_MB_TY2011(personal., CALC_Federal_TY2011)
//                    TEE_Debug.Show_MB_Results_TY2011(CALC_MB_TY2011)
//                    Estimation = (CALC_Federal_TY2011.L420_Net_Federal_Tax + CALC_MB_TY2011.Net_Provincial_Tax + CALC_Federal_TY2011.NRTC_L310_CPPQPPSelfEmployment + CALC_Federal_TY2011.OAS_Repayment) - (personal..IN_Yours_Tax_Deducted + C1 + C2 + CALC_MB_TY2011.MB479_PersonalTaxCredit)
//                Case Is = "NB"
//                    'New Brunswick
//                    Dim CALC_NB_TY2011 As struct_CALC_NB_TY2011
//                    CALC_NB_TY2011 = Calculation_NB_TY2011(personal., CALC_Federal_TY2011)
//                    TEE_Debug.Show_NB_Results_TY2011(CALC_NB_TY2011)
//                    Estimation = (CALC_Federal_TY2011.L420_Net_Federal_Tax + CALC_NB_TY2011.Net_Provincial_Tax + CALC_Federal_TY2011.NRTC_L310_CPPQPPSelfEmployment + CALC_Federal_TY2011.OAS_Repayment) - (personal..IN_Yours_Tax_Deducted + C1 + C2)
//                Case Is = "NS"
//                    'Nova Scotia
//                    Dim CALC_NS_TY2011 As struct_CALC_NS_TY2011
//                    CALC_NS_TY2011 = Calculation_NS_TY2011(personal., CALC_Federal_TY2011)
//                    TEE_Debug.Show_NS_Results_TY2011(CALC_NS_TY2011)
//                    Estimation = (CALC_Federal_TY2011.L420_Net_Federal_Tax + CALC_NS_TY2011.Net_Provincial_Tax + CALC_Federal_TY2011.NRTC_L310_CPPQPPSelfEmployment + CALC_Federal_TY2011.OAS_Repayment) - (personal..IN_Yours_Tax_Deducted + C1 + C2)
//                Case Is = "NL"
//                    'Newfoundland and Labrador
//                    Dim CALC_NL_TY2011 As struct_CALC_NL_TY2011
//                    CALC_NL_TY2011 = Calculation_NL_TY2011(personal., CALC_Federal_TY2011)
//                    TEE_Debug.Show_NL_Results_TY2011(CALC_NL_TY2011)
//                    Estimation = (CALC_Federal_TY2011.L420_Net_Federal_Tax + CALC_NL_TY2011.Net_Provincial_Tax + CALC_Federal_TY2011.NRTC_L310_CPPQPPSelfEmployment + CALC_Federal_TY2011.OAS_Repayment) - (personal..IN_Yours_Tax_Deducted + C1 + C2)
//                Case Is = "PE"
//                    'Prince Edward Island
//                    Dim CALC_PE_TY2011 As struct_CALC_PE_TY2011
//                    CALC_PE_TY2011 = Calculation_PE_TY2011(personal., CALC_Federal_TY2011)
//                    TEE_Debug.Show_PE_Results_TY2011(CALC_PE_TY2011)
//                    Estimation = (CALC_Federal_TY2011.L420_Net_Federal_Tax + CALC_PE_TY2011.Net_Provincial_Tax + CALC_Federal_TY2011.NRTC_L310_CPPQPPSelfEmployment + CALC_Federal_TY2011.OAS_Repayment) - (personal..IN_Yours_Tax_Deducted + C1 + C2)
//                Case Is = "NT"
//                    'Northwest Territories
//                    Dim CALC_NT_TY2011 As struct_CALC_NT_TY2011
//                    CALC_NT_TY2011 = Calculation_NT_TY2011(personal., CALC_Federal_TY2011)
//                    TEE_Debug.Show_NT_Results_TY2011(CALC_NT_TY2011)
//                    Estimation = (CALC_Federal_TY2011.L420_Net_Federal_Tax + CALC_NT_TY2011.Net_Provincial_Tax + CALC_Federal_TY2011.NRTC_L310_CPPQPPSelfEmployment + CALC_Federal_TY2011.OAS_Repayment) - (personal..IN_Yours_Tax_Deducted + C1 + C2 + CALC_NT_TY2011.L6251_NorthwestTerritoriesCredit)
//                Case Is = "YT"
//                    'Yukon
//                    Dim CALC_YT_TY2011 As struct_CALC_YT_TY2011
//                    CALC_YT_TY2011 = Calculation_YT_TY2011(personal., CALC_Federal_TY2011)
//                    TEE_Debug.Show_YT_Results_TY2011(CALC_YT_TY2011)
//                    Estimation = (CALC_Federal_TY2011.L420_Net_Federal_Tax + CALC_YT_TY2011.Net_Provincial_Tax + CALC_Federal_TY2011.NRTC_L310_CPPQPPSelfEmployment + CALC_Federal_TY2011.OAS_Repayment) - (personal..IN_Yours_Tax_Deducted + C1 + C2)
//                Case Is = "NU"
//                    'Nunavut
//                    Dim CALC_NU_TY2011 As struct_CALC_NU_TY2011
//                    CALC_NU_TY2011 = Calculation_NU_TY2011(personal., CALC_Federal_TY2011)
//                    TEE_Debug.Show_NU_Results_TY2011(CALC_NU_TY2011)
//                    Estimation = (CALC_Federal_TY2011.L420_Net_Federal_Tax + CALC_NU_TY2011.Net_Provincial_Tax + CALC_Federal_TY2011.NRTC_L310_CPPQPPSelfEmployment + CALC_Federal_TY2011.OAS_Repayment) - (personal..IN_Yours_Tax_Deducted + C1 + C2 + CALC_NU_TY2011.L27_NunavutCredits)
//            End Select
//
//            TEE_Debug.LVA("TAX ESTIMATION", "", "", "", Color.White, Color.Crimson)
//            TEE_Debug.LVA("Estimation", "", Estimation, "", Color.Black, Color.LightSalmon)
//            TEE_Debug.LVA("(-ve is a REFUND; +ve is OWING)", "", "", "", Color.Black, Color.LightSalmon)
//            TEE_Debug.LVA("", "", "", "", Color.Black, Color.White)
//            TEE_Debug.LVA("TEE Version", "", TEE_VERSION, "", Color.Black, Color.White)
//            TEE_Debug.LVA("Date / Time", "", Now, "", Color.Black, Color.White)
//            TEE_Debug.LVA("", "", "", "", Color.Black, Color.White)
//            TEE_Debug.Show_Inputs_TY2011(personal.)
//
//
//    	
//    	
//    	return success;
//    }
    
//    public boolean validateZeroTo(String variableName, double candidate, double limit)
//	{
//		boolean success = true;
//		if ( candidate < 0.0 ) 
//		{
//			 errorMessages.add(variableName + " @ " + candidate + CANNOT_BE_LESS_THAN + 0.0);
//			 success = false;
//		}
//		if ( candidate > limit ) 
//		{
//			 warningMessages.add(variableName + " @ " + candidate + CANNOT_BE_MORE_THAN + limit);
//			 success = false;
//		}		
//		return success;
//	}
//	
//	public boolean validateLowerLimit(String variableName, double candidate, double limit)
//	{
//		if ( candidate < limit ) 
//		{
//			 errorMessages.add(variableName + " @ " + candidate + CANNOT_BE_LESS_THAN + limit);
//			 return false;
//		}		
//		return true;
//	}
//	
//	public boolean validateUpperLimit(String variableName, double candidate, double limit)
//	{
//		if ( candidate > limit ) 
//		{
//			 warningMessages.add(variableName + " @ " + candidate + CANNOT_BE_MORE_THAN + limit);
//			 return false;
//		}		
//		return true;
//	}
//	
//	private Map<String, Double> loadValues(String xmlResource)
//	{
//		Map<String, Double> values = new HashMap<String, Double>();
//		
//		try 
//		{
//			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
//			//Using factory get an instance of document builder
//			DocumentBuilder db = dbf.newDocumentBuilder();
//
//			//parse using builder to get DOM representation of the XML file
//			Document dom = db.parse(xmlResource);
//		}
//		catch(ParserConfigurationException pce) 
//		{
//			pce.printStackTrace();
//		}
//		catch(SAXException se) 
//		{
//			se.printStackTrace();
//		}
//		catch(IOException ioe) 
//		{
//			ioe.printStackTrace();
//		}
//		return values;
//	}

	public void loadVariables()
	{
		
	}
	
	
	public static void main(String[] args) 
	{
//		// loadValues  "employees.xml"
//		String province = "AB";
//		FullTaxEstimator2011 taxEstimate = new FullTaxEstimator2011();
//		TaxPayerValues2011 personal = null;
//		FederalTaxValues2011 fedTax = null;
//		ProvincialTaxValues2011 provincialTax = null;		

//		if (province.equalsIgnoreCase("AB"))
//			provincialTax = new AlbertaProvincialTaxes2011();
//		else if (province.equalsIgnoreCase("BC"))
//			provincialTax = new BritishColumbiaProvincialTaxes2011();
//		else if (province.equalsIgnoreCase("ON"))
//			provincialTax = new OntarioProvincialTaxes2011();
//		else if (province.equalsIgnoreCase("NB"))
//			provincialTax = new NewBrunswickProvincialTaxes2011();
//		else if (province.equalsIgnoreCase("NS"))
//			provincialTax = new NovaScotiaProvincialTaxes2011();
//		else if (province.equalsIgnoreCase("NL"))
//			provincialTax = new NewfoundlandProvincialTaxes2011();
//		else if (province.equalsIgnoreCase("PI"))
//			provincialTax = new PrinceEdwardIslandProvincialTaxes2011();
//		else if (province.equalsIgnoreCase("SK"))
//			provincialTax = new SaskatchewanProvincialTaxes2011();
//		else if (province.equalsIgnoreCase("MB"))
//			provincialTax = new ManitobaProvincialTaxes2011();
//		else if (province.equalsIgnoreCase("NT"))
//			provincialTax = new NothwestTerritoriesProvincialTaxes2011();
//		else if (province.equalsIgnoreCase("YK"))
//			provincialTax = new QuebecProvincialTaxes2011();
//		else if (province.equalsIgnoreCase("QC"))
//			provincialTax = new BritishColumbiaProvincialTaxes2011();
//		else  //this should never happen as values have been validated.
//			throw new RuntimeException("No valid Province");
		
//		taxEstimate.loadVariables();
//		taxEstimate.calculateFederalTax(personal, fedTax);
//		taxEstimate.calculateAlbertaTax(personal, provincialTax, fedTax);
//		
//		displayResults(personal, provincialTax, fedTax);
		
		
		
		
	}
}
