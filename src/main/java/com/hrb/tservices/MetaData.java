package com.hrb.tservices;

public class MetaData {

	public static final String[][] ARR_FIELDS = {

	{"com.hrb.tservices.domain.message.MarketingMessage",

	"messageTranslationsCount", "java.lang.Integer",

	"messageTitle", "java.lang.String",

	},

	{"com.hrb.tservices.domain.message.MessageTranslation",

	"buttonText", "java.lang.String",

	"message", "java.lang.String",

	"hyperLink", "java.lang.String",

	"marketingMessage.displayName", "java.lang.String",

	"language.name", "java.lang.String",

	},

	{"com.hrb.tservices.domain.message.MarketingMessageMetrics",

	"restMethod.displayName", "java.lang.String",

	"partner.displayName", "java.lang.String",

	"clientType.displayName", "java.lang.String",

	"date", "java.util.Date",

	"language.name", "java.lang.String",

	"sessionId", "java.lang.String",

	"dateClicked", "java.util.Date",

	"messageTranslation.displayName", "java.lang.String",

	},

	{"com.hrb.tservices.domain.taxnews.NewsCategory",

	"name", "java.lang.String",

	"nameFrench", "java.lang.String",

	},

	{"com.hrb.tservices.domain.taxnews.TaxNews",

	"title", "java.lang.String",

	"newsCategory.displayName", "java.lang.String",

	"inactive", "java.lang.Boolean",

	"taxNewsTranslationsCount", "java.lang.Integer",

	},

	{"com.hrb.tservices.domain.taxnews.TaxNewsMetrics",

	"restMethod.displayName", "java.lang.String",

	"partner.displayName", "java.lang.String",

	"clientType.displayName", "java.lang.String",

	"date", "java.util.Date",

	"language.name", "java.lang.String",

	"sessionId", "java.lang.String",

	"taxNews.displayName", "java.lang.String",

	"dateViewed", "java.util.Date",

	},

	{"com.hrb.tservices.domain.taxnews.TaxNewsTranslation",

	"taxNews.displayName", "java.lang.String",

	"title", "java.lang.String",

	"link", "java.lang.String",

	"text", "java.lang.String",

	"language.name", "java.lang.String",

	},

	{"com.hrb.tservices.domain.users.AppUser",

	"userName", "java.lang.String",

	"password", "java.lang.String",

	"enabled", "java.lang.Boolean",

	"appRolesCount", "java.lang.Integer",

	"email", "java.lang.String",

	"lastLogin", "java.util.Date",

	},

	{"com.hrb.tservices.domain.users.AppRole",

	"name", "java.lang.String",

	"appUsersCount", "java.lang.Integer",

	},

	{"com.hrb.tservices.domain.department.Employee",

	"firstName", "java.lang.String",

	"lastName", "java.lang.String",

	"contactDetails.phone", "java.lang.String",

	"contactDetails.secondaryPhone", "java.lang.String",

	"contactDetails.city", "java.lang.String",

	"department.displayName", "java.lang.String",

	"employeeNumber", "java.lang.String",

	"employeeType.name", "java.lang.String",

	},

	{"com.hrb.tservices.domain.department.Department",

	"employeesCount", "java.lang.Integer",

	"name", "java.lang.String",

	},

	{"com.hrb.tservices.domain.department.Partner",

	"name", "java.lang.String",

	"partnerId", "java.lang.String",

	"marketingMessage.displayName", "java.lang.String",

	"appUser.displayName", "java.lang.String",

	"defaultLanguage.name", "java.lang.String",

	},

	{"com.hrb.tservices.domain.department.AuthenticationMetrics",

	"restMethod.displayName", "java.lang.String",

	"partner.displayName", "java.lang.String",

	"clientType.displayName", "java.lang.String",

	"date", "java.util.Date",

	"language.name", "java.lang.String",

	"sessionId", "java.lang.String",

	"succeeded", "java.lang.Boolean",

	"logoutDate", "java.util.Date",

	},

	{"com.hrb.tservices.domain.metrics.RestService",

	"name", "java.lang.String",

	"restMethodsCount", "java.lang.Integer",

	},

	{"com.hrb.tservices.domain.metrics.RestMethod",

	"restService.displayName", "java.lang.String",

	"name", "java.lang.String",

	},

	{"com.hrb.tservices.domain.metrics.ClientType",

	"name", "java.lang.String",

	},

	{"com.hrb.tservices.domain.faq.FaqCategory",

	"faqQuestionsCount", "java.lang.Integer",

	"name", "java.lang.String",

	"frenchName", "java.lang.String",

	},

	{"com.hrb.tservices.domain.faq.FaqQuestion",

	"faqCategory.displayName", "java.lang.String",

	"title", "java.lang.String",

	"questionTranslationsCount", "java.lang.Integer",

	"ratingsCount", "java.lang.Integer",

	"avgRating", "java.lang.Integer",

	"inactive", "java.lang.Boolean",

	"views", "java.lang.Integer",

	},

	{"com.hrb.tservices.domain.faq.QuestionTranslation",

	"language.name", "java.lang.String",

	"title", "java.lang.String",

	"text", "java.lang.String",

	"link", "java.lang.String",

	"faqQuestion.displayName", "java.lang.String",

	"answer", "java.lang.String",

	},

	{"com.hrb.tservices.domain.faq.Rating",

	"rating", "java.lang.Integer",

	"faqQuestion.displayName", "java.lang.String",

	},

	{"com.hrb.tservices.domain.faq.FaqQuestionMetrics",

	"restMethod.displayName", "java.lang.String",

	"partner.displayName", "java.lang.String",

	"clientType.displayName", "java.lang.String",

	"date", "java.util.Date",

	"language.name", "java.lang.String",

	"sessionId", "java.lang.String",

	"faqQuestion.displayName", "java.lang.String",

	"dateViewed", "java.util.Date",

	},

	{"com.hrb.tservices.domain.offices.Office",

	"officeId", "java.lang.String",

	"headingEN", "java.lang.String",

	"headingFR", "java.lang.String",

	"address", "java.lang.String",

	"city", "java.lang.String",

	"province", "java.lang.String",

	"postalCode", "java.lang.String",

	"latitude", "java.lang.String",

	"longitude", "java.lang.String",

	"phone", "java.lang.String",

	"fax", "java.lang.String",

	"enInfo", "java.lang.String",

	"frInfo", "java.lang.String",

	"enHours", "java.lang.String",

	"frHours", "java.lang.String",

	},

	{"com.hrb.tservices.domain.offices.OfficeLocatorMetrics",

	"restMethod.displayName", "java.lang.String",

	"partner.displayName", "java.lang.String",

	"clientType.displayName", "java.lang.String",

	"date", "java.util.Date",

	"language.name", "java.lang.String",

	"sessionId", "java.lang.String",

	"lookupCity", "java.lang.String",

	"lookupPostalCode", "java.lang.String",

	},

	};

}
