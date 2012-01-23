package com.oreon.talent;

public class MetaData {

	public static final String[][] ARR_FIELDS = {

	{"com.oreon.talent.domain.Employee",

	"firstName", "java.lang.String",

	"lastName", "java.lang.String",

	"contactDetails.phone", "java.lang.String",

	"contactDetails.secondaryPhone", "java.lang.String",

	"contactDetails.city", "java.lang.String",

	"department.displayName", "java.lang.String",

	"employeeNumber", "java.lang.String",

	"employeeType.name", "java.lang.String",

	},

	{"com.oreon.talent.domain.Department",

	"employeesCount", "java.lang.Integer",

	"name", "java.lang.String",

	},

	{"com.oreon.talent.domain.Exam",

	"title", "java.lang.String",

	"description", "java.lang.String",

	"questionsCount", "java.lang.Integer",

	},

	{"com.oreon.talent.domain.Question",

	"text", "java.lang.String",

	"exam.displayName", "java.lang.String",

	},

	{"com.oreon.talent.candidates.Candidate",

	"firstName", "java.lang.String",

	"lastName", "java.lang.String",

	"contactDetails.phone", "java.lang.String",

	"contactDetails.secondaryPhone", "java.lang.String",

	"contactDetails.city", "java.lang.String",

	"availibility.name", "java.lang.String",

	"preferredJobType.name", "java.lang.String",

	"chiefExpertiese.name", "java.lang.String",

	"educationLevel.name", "java.lang.String",

	"resumeFile", "FileAttachment",

	"textResume", "java.lang.String",

	"appUser.displayName", "java.lang.String",

	},

	{"com.oreon.talent.candidates.Job",

	"title", "java.lang.String",

	"description", "java.lang.String",

	"client.displayName", "java.lang.String",

	},

	{"com.oreon.talent.candidates.Client",

	"name", "java.lang.String",

	},

	{"com.oreon.talent.candidates.JobApplication",

	"candidate.displayName", "java.lang.String",

	"job.displayName", "java.lang.String",

	},

	{"com.oreon.talent.users.AppUser",

	"userName", "java.lang.String",

	"password", "java.lang.String",

	"enabled", "java.lang.Boolean",

	"appRolesCount", "java.lang.Integer",

	"email", "java.lang.String",

	"lastLogin", "java.util.Date",

	},

	{"com.oreon.talent.users.AppRole",

	"name", "java.lang.String",

	"appUsersCount", "java.lang.Integer",

	},

	};

}
