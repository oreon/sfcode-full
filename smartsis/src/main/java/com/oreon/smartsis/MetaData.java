package com.oreon.smartsis;

public class MetaData {

	public static final String[][] ARR_FIELDS = {

	{"com.oreon.smartsis.domain.Employee",

	"firstName",

	"lastName",

	"contactDetails",

	"department",

	"employeeNumber",

	"employeeType",

	},

	{"com.oreon.smartsis.domain.Department",

	"employees",

	"name",

	},

	{"com.oreon.smartsis.domain.Student",

	"firstName",

	"lastName",

	"contactDetails",

	"picture",

	"gender",

	"dateOfBirth",

	"grade",

	"age",

	},

	{"com.oreon.smartsis.domain.Sponsor",

	"firstName",

	"lastName",

	"contactDetails",

	},

	{"com.oreon.smartsis.domain.Sponsorship",

	"sponsor",

	"student",

	"amount",

	},

	{"com.oreon.smartsis.domain.Grade",

	"name",

	"students",

	"exams",

	"ordinal",

	"section",

	"gradeSubjects",

	"gradeFees",

	},

	{"com.oreon.smartsis.domain.Subject",

	"name",

	},

	{"com.oreon.smartsis.domain.GradeSubject",

	"subject",

	"employee",

	"grade",

	"courseDocumentses",

	},

	{"com.oreon.smartsis.domain.Exam",

	"name",

	"grades",

	"maxMarks",

	"passMarks",

	"ordinal",

	},

	{"com.oreon.smartsis.domain.ExamScore",

	"examName",

	"examInstance",

	"subject",

	"studentInfo",

	"marks",

	"percentage",

	"rank",

	"student",

	},

	{"com.oreon.smartsis.domain.ExamInstance",

	"examScores",

	"exam",

	"gradeSubject",

	"dateHeld",

	"average",

	},

	{"com.oreon.smartsis.domain.GradeFee",

	"grade",

	"fee",

	"amount",

	},

	{"com.oreon.smartsis.domain.Fee",

	"name",

	"defaultAmount",

	"frequency",

	},

	{"com.oreon.smartsis.domain.CourseDocuments",

	"name",

	"document",

	"gradeSubject",

	},

	{"com.oreon.smartsis.domain.PaidFee",

	"amount",

	"notes",

	"student",

	"gradeFee",

	},

	{"com.oreon.smartsis.exam.ElectronicExam",

	"name",

	"numberOfQuestions",

	"questions",

	"gradeSubject",

	"maxDuration",

	},

	{"com.oreon.smartsis.exam.Question",

	"text",

	"choices",

	"electronicExam",

	"correctChoice",

	},

	{"com.oreon.smartsis.exam.Choice",

	"text",

	"question",

	},

	{"com.oreon.smartsis.exam.ElectronicExamInstance",

	"student",

	"questionInstances",

	"score",

	"electronicExamEvent",

	"timeTaken",

	},

	{"com.oreon.smartsis.exam.QuestionInstance",

	"electronicExamInstance",

	"question",

	"selectedChoice",

	},

	{"com.oreon.smartsis.exam.ElectronicExamEvent",

	"electronicExam",

	"electronicExamInstances",

	"dateOfExam",

	"remarks",

	},

	};

}
