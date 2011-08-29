package com.jonah.mentormatcher;

public class MetaData {

	public static final String[][] ARR_FIELDS = {

	{"com.jonah.mentormatcher.domain.Employee",

	"firstName", "java.lang.String",

	"lastName", "java.lang.String",

	"contactDetails.phone", "java.lang.String",

	"contactDetails.secondaryPhone", "java.lang.String",

	"contactDetails.city", "java.lang.String",

	"department.displayName", "java.lang.String",

	"employeeNumber", "java.lang.String",

	"employeeType.name", "java.lang.String",

	"picture", "FileAttachment",

	"testimonialsCount", "java.lang.Integer",

	"appUser.displayName", "java.lang.String",

	"designation.displayName", "java.lang.String",

	"bio", "java.lang.String",

	},

	{"com.jonah.mentormatcher.domain.Department",

	"employeesCount", "java.lang.Integer",

	"name", "java.lang.String",

	},

	{"com.jonah.mentormatcher.domain.mentorship.MentorshipOffering",

	"title", "java.lang.String",

	"description", "java.lang.String",

	"keywords", "java.lang.String",

	"inactive", "java.lang.Boolean",

	"scope.name", "java.lang.String",

	"category.displayName", "java.lang.String",

	"mentor.displayName", "java.lang.String",

	},

	{"com.jonah.mentormatcher.domain.mentorship.Mentorship",

	"mentor.displayName", "java.lang.String",

	"startDate", "java.util.Date",

	"endDate", "java.util.Date",

	"menteesCount", "java.lang.Integer",

	},

	{"com.jonah.mentormatcher.domain.mentorship.Testimonial",

	"employee.displayName", "java.lang.String",

	"description", "java.lang.String",

	},

	{"com.jonah.mentormatcher.domain.mentorship.MentorSearch",

	"category.displayName", "java.lang.String",

	"title", "java.lang.String",

	"employee.displayName", "java.lang.String",

	"scope.name", "java.lang.String",

	},

	{"com.jonah.mentormatcher.domain.mentorship.Category",

	"name", "java.lang.String",

	},

	{"com.jonah.mentormatcher.domain.mentorship.MentorshipMember",

	"mentorship.displayName", "java.lang.String",

	"employee.displayName", "java.lang.String",

	},

	{"com.jonah.mentormatcher.domain.mentorship.JoinRequest",

	"mentorshipOffering.displayName", "java.lang.String",

	"requestText", "java.lang.String",

	"prospectiveMentee.displayName", "java.lang.String",

	},

	{"com.jonah.mentormatcher.domain.users.AppUser",

	"userName", "java.lang.String",

	"password", "java.lang.String",

	"enabled", "java.lang.Boolean",

	"appRolesCount", "java.lang.Integer",

	"email", "java.lang.String",

	"lastLogin", "java.util.Date",

	},

	{"com.jonah.mentormatcher.domain.users.AppRole",

	"name", "java.lang.String",

	"appUsersCount", "java.lang.Integer",

	},

	{"com.jonah.mentormatcher.domain.Designation",

	"name", "java.lang.String",

	},

	};

}
