package com.pcas.datapkg;

public class MetaData {

	public static final String[][] ARR_FIELDS = {

	{"com.pcas.datapkg.domain.Employee",

	"firstName", "java.lang.String",

	"lastName", "java.lang.String",

	"contactDetails.phone", "java.lang.String",

	"contactDetails.secondaryPhone", "java.lang.String",

	"contactDetails.city", "java.lang.String",

	"department.displayName", "java.lang.String",

	"employeeNumber", "java.lang.String",

	"employeeType.name", "java.lang.String",

	"salary", "BigDecimal",

	"customer.displayName", "java.lang.String",

	"appUser.displayName", "java.lang.String",

	},

	{"com.pcas.datapkg.domain.Department",

	"employeesCount", "java.lang.Integer",

	"name", "java.lang.String",

	},

	{"com.pcas.datapkg.domain.DataPackage",

	"name", "java.lang.String",

	},

	{"com.pcas.datapkg.domain.inventory.DrugAbstract",

	"name", "java.lang.String",

	"price", "BigDecimal",

	},

	{"com.pcas.datapkg.domain.inventory.DrugInventory",

	"drugAbstract.displayName", "java.lang.String",

	"machine.displayName", "java.lang.String",

	"qty", "java.lang.Integer",

	},

	{"com.pcas.datapkg.domain.inventory.Machine",

	"drugInventorysCount", "java.lang.Integer",

	"customer.displayName", "java.lang.String",

	"name", "java.lang.String",

	"location.displayName", "java.lang.String",

	},

	{"com.pcas.datapkg.domain.inventory.Customer",

	"machinesCount", "java.lang.Integer",

	"name", "java.lang.String",

	"country", "java.lang.String",

	"telephone", "java.lang.String",

	"employeesCount", "java.lang.Integer",

	},

	{"com.pcas.datapkg.domain.inventory.Location",

	"name", "java.lang.String",

	"machinesCount", "java.lang.Integer",

	},

	{"com.pcas.datapkg.domain.inventory.InventoryHistory",

	"drugInventory.displayName", "java.lang.String",

	"date", "java.util.Date",

	"qty", "java.lang.Integer",

	},

	{"com.pcas.datapkg.customReports.CustomReport",

	"fieldsCount", "java.lang.Integer",

	"groupFieldsCount", "java.lang.Integer",

	"name", "java.lang.String",

	"metaEntity.displayName", "java.lang.String",

	"reportParametersCount", "java.lang.Integer",

	},

	{"com.pcas.datapkg.customReports.MetaEntity",

	"name", "java.lang.String",

	"metaFieldsCount", "java.lang.Integer",

	},

	{"com.pcas.datapkg.customReports.MetaField",

	"name", "java.lang.String",

	"metaEntity.displayName", "java.lang.String",

	"customReportsCount", "java.lang.Integer",

	"groupReportCount", "java.lang.Integer",

	"type", "java.lang.String",

	},

	{"com.pcas.datapkg.customReports.ReportParameter",

	"customReport.displayName", "java.lang.String",

	"metaField.displayName", "java.lang.String",

	"comparison.name", "java.lang.String",

	"mandatory", "java.lang.Boolean",

	"defaultValue", "java.lang.String",

	"name", "java.lang.String",

	},

	{"com.pcas.datapkg.users.AppUser",

	"userName", "java.lang.String",

	"password", "java.lang.String",

	"enabled", "java.lang.Boolean",

	"appRolesCount", "java.lang.Integer",

	"email", "java.lang.String",

	"lastLogin", "java.util.Date",

	},

	{"com.pcas.datapkg.users.AppRole",

	"name", "java.lang.String",

	"appUsersCount", "java.lang.Integer",

	},

	};

}
