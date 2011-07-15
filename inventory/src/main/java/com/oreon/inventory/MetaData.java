package com.oreon.inventory;

public class MetaData {

	public static final String[][] ARR_FIELDS = {

	{"com.oreon.inventory.domain.Employee",

	"firstName", "java.lang.String",

	"lastName", "java.lang.String",

	"contactDetails.phone", "java.lang.String",

	"contactDetails.secondaryPhone", "java.lang.String",

	"contactDetails.city", "java.lang.String",

	"department.displayName", "java.lang.String",

	"employeeNumber", "java.lang.String",

	"employeeType.name", "java.lang.String",

	},

	{"com.oreon.inventory.domain.Department",

	"employeesCount", "java.lang.Integer",

	"name", "java.lang.String",

	},

	{"com.oreon.inventory.domain.Exam",

	"title", "java.lang.String",

	"description", "java.lang.String",

	"questionsCount", "java.lang.Integer",

	},

	{"com.oreon.inventory.domain.Question",

	"text", "java.lang.String",

	"exam.displayName", "java.lang.String",

	},

	{"com.oreon.inventory.inventory.Product",

	"productQuantitysCount", "java.lang.Integer",

	"name", "java.lang.String",

	"barcode", "java.lang.Long",

	"lowStockLevel", "java.lang.Integer",

	"suppliersCount", "java.lang.Integer",

	"currentLevel", "java.lang.Integer",

	},

	{"com.oreon.inventory.inventory.Godown",

	"name", "java.lang.String",

	"address.primaryPhone", "java.lang.String",

	"address.address", "java.lang.String",

	"address.city", "java.lang.String",

	"address.state", "java.lang.String",

	},

	{"com.oreon.inventory.inventory.ProductQuantity",

	"product.displayName", "java.lang.String",

	"godown.displayName", "java.lang.String",

	"quantity", "java.lang.Integer",

	},

	{"com.oreon.inventory.inventory.Supplier",

	"productsCount", "java.lang.Integer",

	"name", "java.lang.String",

	"address.primaryPhone", "java.lang.String",

	"address.address", "java.lang.String",

	"address.city", "java.lang.String",

	"address.state", "java.lang.String",

	},

	{"com.oreon.inventory.inventory.Purchase",

	"quantity", "java.lang.Integer",

	"product.displayName", "java.lang.String",

	"supplier.displayName", "java.lang.String",

	"price", "java.lang.Double",

	},

	};

}
