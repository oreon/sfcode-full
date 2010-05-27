package tes; 

import cntrls.ControlType;

public class ManualRiskRequestData {
	
	static String[][] flds = {
{			"REFNBR",            "1",        "11",   "ReferenceNumber",            },

{			"NEWCLINT",         "12",         "1",   "NewClient",                  },

{			"CLINTNAME",        "13",        "60",   "ClientName",                 },

{			"CLINTCID",         "73",        "16",   "ClientCID",                  },

{			"ACCTNO",           "89",        "12",   "AccountNumber",              },

{			"CLINTSEG",        "101",         "2",   "ClientSegment",               },

{			"BUSNATURE",       "103",         "2",   "BusinessNature",              },

{			"PRMGEOOPR",       "105",       "600",   "PrimaryGeographyOperation",  },

{			"CASHDPVAL",       "705",         "2",   "CashDepostValueMnth",       },

{			"WIRETRVAL",       "707",         "2",   "WireTransferValueMnth",     },

{			"AGNTNAME",        "709",        "40",   "AgentName",                  },

{			"TRNSTNBR",        "749",         "5",   "TransitNumber",              },

{			"CUSTTYPE",        "754",         "2",   "CustomerType",                },

{			"NAFTARGN",        "756",         "1",   "NAFTARegion",                 },

{			"EURORGN",         "757",         "1",   "EuropeanRegion",              },

{			"INIRATE",         "758",         "2",   "InitialRating",            },
			
{			"FNLRATE",         "760",         "2",   "FinalRating",                },

{			"SOURCE",          "762",        "40",   "SourceofReview",               },

{			"RELATSHP",        "802",        "40",   "Relationship",                   },

{			"UTRRAISED",       "842",         "3",   "UTRRaised",              },
	};
	
	
	public static  int getFieldSize(String fld){
	
		
		for(int i = 0; i < flds.length; i++){
			//System.out.println(flds[i][3] + " "  +  fld);
			String a = new String( flds[i][3] );
			String b = new String( fld );
			if(a.equals(b))
				return Integer.parseInt( flds[i][2] );
		}
		
		return -1;
	}
	
	public static void main(String[] args) {
		
		System.out.println( getFieldSize("SourceofReview") );
	}
	

}
