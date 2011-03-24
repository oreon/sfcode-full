package com.pwc.insuranceclaims;

public class MetaData {

	public static final String[][] ARR_FIELDS = {

	{"com.pwc.insuranceclaims.domain.Employee",

	"firstName", "java.lang.String",

	"lastName", "java.lang.String",

	"contactDetails.phone", "java.lang.String",

	"contactDetails.secondaryPhone", "java.lang.String",

	"contactDetails.city", "java.lang.String",

	"department.displayName", "java.lang.String",

	"employeeNumber", "java.lang.String",

	"employeeType.name", "java.lang.String",

	"user.displayName", "java.lang.String",

	},

	{"com.pwc.insuranceclaims.domain.Department",

	"employeesCount", "java.lang.Integer",

	"name", "java.lang.String",

	},

	{"com.pwc.insuranceclaims.quickclaim.Customer",

	"firstName", "java.lang.String",

	"lastName", "java.lang.String",

	"address1", "java.lang.String",

	"address2", "java.lang.String",

	"city", "java.lang.String",

	"province", "java.lang.String",

	"postalCode", "java.lang.String",

	"policysCount", "java.lang.Integer",

	"dependentsCount", "java.lang.Integer",

	"phone", "java.lang.String",

	},

	{"com.pwc.insuranceclaims.quickclaim.Policy",

	"policyNumber", "java.lang.String",

	"policyType.name", "java.lang.String",

	"customer.displayName", "java.lang.String",

	"premium", "java.lang.Double",

	},

	{"com.pwc.insuranceclaims.quickclaim.Claim",

	"policy.displayName", "java.lang.String",

	"claimNumber", "java.lang.String",

	"summary", "java.lang.String",

	"claimDate", "java.util.Date",

	"claimAmount", "java.lang.Double",

	"claimDescription", "java.lang.String",

	"status.name", "java.lang.String",

	"claimPatient.displayName", "java.lang.String",

	"claimDocumentsCount", "java.lang.Integer",

	"primaryDocument", "FileAttachment",

	},

	{"com.pwc.insuranceclaims.quickclaim.Dependent",

	"dependentName", "java.lang.String",

	"customer.displayName", "java.lang.String",

	"dependentDateofBirth", "java.util.Date",

	"dependentGender.name", "java.lang.String",

	},

	{"com.pwc.insuranceclaims.quickclaim.ClaimDocument",

	"document", "FileAttachment",

	"documentDate", "java.util.Date",

	"documentType", "java.lang.String",

	"documentDescription", "java.lang.String",

	"claim.displayName", "java.lang.String",

	},

	{"com.pwc.insuranceclaims.users.User",

	"userName", "java.lang.String",

	"password", "java.lang.String",

	"enabled", "java.lang.Boolean",

	"rolesCount", "java.lang.Integer",

	"email", "java.lang.String",

	"lastLogin", "java.util.Date",

	},

	{"com.pwc.insuranceclaims.users.Role",

	"name", "java.lang.String",

	"usersCount", "java.lang.Integer",

	},

	};

}
