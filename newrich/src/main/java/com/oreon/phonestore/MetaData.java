package com.oreon.phonestore;

public class MetaData {

	public static final String[][] ARR_FIELDS = {

	{"com.oreon.phonestore.domain.Employee",

	"firstName", "java.lang.String",

	"lastName", "java.lang.String",

	"contactDetails.phone", "java.lang.String",

	"contactDetails.secondaryPhone", "java.lang.String",

	"contactDetails.city", "java.lang.String",

	"department.displayName", "java.lang.String",

	"employeeNumber", "java.lang.String",

	"employeeType.name", "java.lang.String",

	},

	{"com.oreon.phonestore.domain.Department",

	"employeesCount", "java.lang.Integer",

	"name", "java.lang.String",

	},

	{"com.oreon.phonestore.domain.Exam",

	"title", "java.lang.String",

	"description", "java.lang.String",

	"questionsCount", "java.lang.Integer",

	},

	{"com.oreon.phonestore.domain.Question",

	"text", "java.lang.String",

	"exam.displayName", "java.lang.String",

	},

	{"com.oreon.phonestore.domain.commerce.Product",

	"name", "java.lang.String",

	"image", "FileAttachment",

	"price", "BigDecimal",

	"description", "java.lang.String",

	},

	{"com.oreon.phonestore.domain.commerce.CustomerOrder",

	"orderItemsCount", "java.lang.Integer",

	"remarks", "java.lang.String",

	"customer.displayName", "java.lang.String",

	"total", "BigDecimal",

	},

	{"com.oreon.phonestore.domain.commerce.OrderItem",

	"remarks", "java.lang.String",

	"customerOrder.displayName", "java.lang.String",

	"product.displayName", "java.lang.String",

	"units", "java.lang.Integer",

	"salePrice", "BigDecimal",

	},

	{"com.oreon.phonestore.domain.commerce.Customer",

	"firstName", "java.lang.String",

	"lastName", "java.lang.String",

	"contactDetails.phone", "java.lang.String",

	"contactDetails.secondaryPhone", "java.lang.String",

	"contactDetails.city", "java.lang.String",

	"type.name", "java.lang.String",

	},

	{"com.oreon.phonestore.users.User",

	"userName", "java.lang.String",

	"password", "java.lang.String",

	"enabled", "java.lang.Boolean",

	"rolesCount", "java.lang.Integer",

	"email", "java.lang.String",

	"lastLogin", "java.util.Date",

	},

	{"com.oreon.phonestore.users.Role",

	"name", "java.lang.String",

	"usersCount", "java.lang.Integer",

	},

	};

}
