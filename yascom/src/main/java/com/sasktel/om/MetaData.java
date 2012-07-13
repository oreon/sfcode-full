package com.sasktel.om;

public class MetaData {

	public static final String[][] ARR_FIELDS = {

	{"com.sasktel.om.domain.Employee",

	"firstName", "java.lang.String",

	"lastName", "java.lang.String",

	"contactDetails.phone", "java.lang.String",

	"contactDetails.secondaryPhone", "java.lang.String",

	"contactDetails.city", "java.lang.String",

	"department.displayName", "java.lang.String",

	"employeeNumber", "java.lang.String",

	"employeeType.name", "java.lang.String",

	},

	{"com.sasktel.om.domain.Department",

	"employeesCount", "java.lang.Integer",

	"name", "java.lang.String",

	},

	{"com.sasktel.om.domain.Exam",

	"title", "java.lang.String",

	"description", "java.lang.String",

	"questionsCount", "java.lang.Integer",

	},

	{"com.sasktel.om.domain.Question",

	"text", "java.lang.String",

	"exam.displayName", "java.lang.String",

	},

	{"com.sasktel.om.users.AppUser",

	"userName", "java.lang.String",

	"password", "java.lang.String",

	"enabled", "java.lang.Boolean",

	"appRolesCount", "java.lang.Integer",

	"email", "java.lang.String",

	"lastLogin", "java.util.Date",

	},

	{"com.sasktel.om.users.AppRole",

	"name", "java.lang.String",

	"appUsersCount", "java.lang.Integer",

	},

	{"com.sasktel.om.omdomain.ServiceOrder",

	"serviceOrderItemsCount", "java.lang.Integer",

	"subscriber.displayName", "java.lang.String",

	"dateRequested", "java.util.Date",

	"location", "java.lang.String",

	"comments", "java.lang.String",

	"currentStatus", "java.lang.String",

	"serviceOrderTrailsCount", "java.lang.Integer",

	"status.name", "java.lang.String",

	},

	{"com.sasktel.om.omdomain.CustomerServiceSpec",

	"name", "java.lang.String",

	"price", "java.lang.Double",

	"description", "java.lang.String",

	"resourceServiceSpecsCount", "java.lang.Integer",

	"workflow.displayName", "java.lang.String",

	},

	{"com.sasktel.om.omdomain.ServiceOrderItem",

	"serviceOrder.displayName", "java.lang.String",

	"additionalInfo", "java.lang.String",

	"customerServiceSpec.displayName", "java.lang.String",

	},

	{"com.sasktel.om.omdomain.Subscriber",

	"firstName", "java.lang.String",

	"lastName", "java.lang.String",

	"contactDetails.phone", "java.lang.String",

	"contactDetails.secondaryPhone", "java.lang.String",

	"contactDetails.city", "java.lang.String",

	},

	{"com.sasktel.om.omdomain.TelecomResource",

	"name", "java.lang.String",

	},

	{"com.sasktel.om.omdomain.ResourceServiceSpec",

	"customerServiceSpec.displayName", "java.lang.String",

	"name", "java.lang.String",

	"telecomResource.displayName", "java.lang.String",

	},

	{"com.sasktel.om.omdomain.CustomerService",

	"customerServiceSpec.displayName", "java.lang.String",

	"description", "java.lang.String",

	},

	{"com.sasktel.om.omdomain.ServiceOrderTrail",

	"serviceOrder.displayName", "java.lang.String",

	},

	{"com.sasktel.om.omdomain.Workflow",

	"name", "java.lang.String",

	},

	};

}
