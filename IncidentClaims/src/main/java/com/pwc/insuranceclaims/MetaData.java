package com.pwc.insuranceclaims;

public class MetaData {

	public static final String[][] ARR_FIELDS = {

	{"com.pwc.insuranceclaims.domain.Employee",

	"firstName",

	"lastName",

	"contactDetails",

	"department",

	"employeeNumber",

	"employeeType",

	},

	{"com.pwc.insuranceclaims.domain.Department",

	"employees",

	"name",

	},

	{"com.pwc.insuranceclaims.quickclaim.Customer",

	"firstName",

	"lastName",

	"address1",

	"address2",

	"city",

	"state",

	"zipCode",

	"policys",

	"dependents",

	},

	{"com.pwc.insuranceclaims.quickclaim.Policy",

	"policyNumber",

	"policyType",

	"customer",

	},

	{"com.pwc.insuranceclaims.quickclaim.Claim",

	"policy",

	},

	{"com.pwc.insuranceclaims.quickclaim.Dependent",

	"dependentName",

	"customer",

	},

	};

}
