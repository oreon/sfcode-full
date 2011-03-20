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

	"user",

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

	"province",

	"postalCode",

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

	"claimNumber",

	"claimDate",

	"claimAmount",

	"claimDescription",

	"claimPatient",

	"claimDocuments",

	},

	{"com.pwc.insuranceclaims.quickclaim.Dependent",

	"dependentName",

	"customer",

	"dependentDateofBirth",

	"dependentGender",

	},

	{"com.pwc.insuranceclaims.quickclaim.ClaimDocument",

	"claimNumber",

	"documentDate",

	"documentType",

	"documentDescription",

	"claim",

	},

	{"com.pwc.insuranceclaims.users.User",

	"userName",

	"password",

	"enabled",

	"roles",

	"email",

	"lastLogin",

	},

	{"com.pwc.insuranceclaims.users.Role",

	"name",

	"users",

	},

	};

}
