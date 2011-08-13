package com.oreon.smartsis;

public class MetaData {

	public static final String[][] ARR_FIELDS = {

	{"com.oreon.smartsis.domain.Employee",

	"firstName", "java.lang.String",

	"lastName", "java.lang.String",

	"contactDetails.primaryPhone", "java.lang.String",

	"contactDetails.secondaryPhone", "java.lang.String",

	"contactDetails.email", "java.lang.String",

	"department.displayName", "java.lang.String",

	"employeeNumber", "java.lang.String",

	"employeeType.name", "java.lang.String",

	"manager.displayName", "java.lang.String",

	"user.displayName", "java.lang.String",

	},

	{"com.oreon.smartsis.domain.Department",

	"employeesCount", "java.lang.Integer",

	"name", "java.lang.String",

	},

	{"com.oreon.smartsis.domain.Student",

	"firstName", "java.lang.String",

	"lastName", "java.lang.String",

	"contactDetails.primaryPhone", "java.lang.String",

	"contactDetails.secondaryPhone", "java.lang.String",

	"contactDetails.email", "java.lang.String",

	"picture", "FileAttachment",

	"gender.name", "java.lang.String",

	"dateOfBirth", "java.util.Date",

	"grade.displayName", "java.lang.String",

	"age", "java.lang.Integer",

	"scholarship", "java.lang.Double",

	"studentVitalInfosCount", "java.lang.Integer",

	"rollNumber", "java.lang.Integer",

	"discontinueDate", "java.util.Date",

	"discontinueReason.name", "java.lang.String",

	"parentGroup.displayName", "java.lang.String",

	},

	{"com.oreon.smartsis.domain.Parent",

	"firstName", "java.lang.String",

	"lastName", "java.lang.String",

	"contactDetails.primaryPhone", "java.lang.String",

	"contactDetails.secondaryPhone", "java.lang.String",

	"contactDetails.email", "java.lang.String",

	"user.displayName", "java.lang.String",

	"parentGroup.displayName", "java.lang.String",

	},

	{"com.oreon.smartsis.domain.Grade",

	"name", "java.lang.String",

	"studentsCount", "java.lang.Integer",

	"examsCount", "java.lang.Integer",

	"ordinal", "java.lang.Integer",

	"section", "java.lang.String",

	"gradeSubjectsCount", "java.lang.Integer",

	},

	{"com.oreon.smartsis.domain.Subject",

	"name", "java.lang.String",

	},

	{"com.oreon.smartsis.domain.GradeSubject",

	"subject.displayName", "java.lang.String",

	"employee.displayName", "java.lang.String",

	"grade.displayName", "java.lang.String",

	"courseDocumentsesCount", "java.lang.Integer",

	},

	{"com.oreon.smartsis.domain.Exam",

	"name", "java.lang.String",

	"gradesCount", "java.lang.Integer",

	"maxMarks", "java.lang.Integer",

	"passMarks", "java.lang.Integer",

	"ordinal", "java.lang.Integer",

	"weight", "java.lang.Double",

	},

	{"com.oreon.smartsis.domain.ExamScore",

	"examName", "java.lang.String",

	"examInstance.displayName", "java.lang.String",

	"subject", "java.lang.String",

	"marks", "java.lang.Integer",

	"percentage", "java.lang.Double",

	"rank", "java.lang.Integer",

	"student.displayName", "java.lang.String",

	},

	{"com.oreon.smartsis.domain.ExamInstance",

	"examScoresCount", "java.lang.Integer",

	"exam.displayName", "java.lang.String",

	"gradeSubject.displayName", "java.lang.String",

	"dateHeld", "java.util.Date",

	"average", "java.lang.Integer",

	},

	{"com.oreon.smartsis.domain.CourseDocuments",

	"name", "java.lang.String",

	"document", "FileAttachment",

	"gradeSubject.displayName", "java.lang.String",

	},

	{"com.oreon.smartsis.domain.DisciplinaryAction",

	"student.displayName", "java.lang.String",

	"title", "java.lang.String",

	"description", "java.lang.String",

	"employee.displayName", "java.lang.String",

	},

	{"com.oreon.smartsis.domain.StudentVitalInfo",

	"height", "java.lang.Long",

	"weight", "java.lang.Long",

	"student.displayName", "java.lang.String",

	},

	{"com.oreon.smartsis.domain.Settings",

	"name", "java.lang.String",

	"gradeCapacity", "java.lang.Integer",

	"defaultGender.name", "java.lang.String",

	},

	{"com.oreon.smartsis.domain.ParentGroup",

	"studentsCount", "java.lang.Integer",

	"parentsCount", "java.lang.Integer",

	},

	{"com.oreon.smartsis.exam.ElectronicExam",

	"name", "java.lang.String",

	"numberOfQuestions", "java.lang.Integer",

	"questionsCount", "java.lang.Integer",

	"gradeSubject.displayName", "java.lang.String",

	"maxDuration", "java.lang.Integer",

	},

	{"com.oreon.smartsis.exam.Question",

	"text", "java.lang.String",

	"choicesCount", "java.lang.Integer",

	"electronicExam.displayName", "java.lang.String",

	"correctChoice.name", "java.lang.String",

	},

	{"com.oreon.smartsis.exam.Choice",

	"text", "java.lang.String",

	"question.displayName", "java.lang.String",

	},

	{"com.oreon.smartsis.exam.ElectronicExamInstance",

	"student.displayName", "java.lang.String",

	"questionInstancesCount", "java.lang.Integer",

	"score", "java.lang.Integer",

	"electronicExamEvent.displayName", "java.lang.String",

	"timeTaken", "java.lang.Integer",

	},

	{"com.oreon.smartsis.exam.QuestionInstance",

	"electronicExamInstance.displayName", "java.lang.String",

	"question.displayName", "java.lang.String",

	"selectedChoice.displayName", "java.lang.String",

	},

	{"com.oreon.smartsis.exam.ElectronicExamEvent",

	"electronicExam.displayName", "java.lang.String",

	"electronicExamInstancesCount", "java.lang.Integer",

	"dateOfExam", "java.util.Date",

	"remarks", "java.lang.String",

	},

	{"com.oreon.smartsis.attendance.Attendance",

	"student.displayName", "java.lang.String",

	"date", "java.util.Date",

	"gradeSubject.displayName", "java.lang.String",

	"absenceCode.name", "java.lang.String",

	"gradeAttendance.displayName", "java.lang.String",

	},

	{"com.oreon.smartsis.attendance.GradeAttendance",

	"attendancesCount", "java.lang.Integer",

	"date", "java.util.Date",

	"grade.displayName", "java.lang.String",

	},

	{"com.oreon.smartsis.hostel.Room",

	"bedsCount", "java.lang.Integer",

	"name", "java.lang.String",

	"hostel.displayName", "java.lang.String",

	"charges", "java.lang.Double",

	},

	{"com.oreon.smartsis.hostel.Bed",

	"room.displayName", "java.lang.String",

	"name", "java.lang.String",

	},

	{"com.oreon.smartsis.hostel.Hostel",

	"name", "java.lang.String",

	"roomsCount", "java.lang.Integer",

	"gender.name", "java.lang.String",

	},

	{"com.oreon.smartsis.hostel.BedAllocation",

	"startDate", "java.util.Date",

	"endDate", "java.util.Date",

	"bed.displayName", "java.lang.String",

	"student.displayName", "java.lang.String",

	"remarks", "java.lang.String",

	},

	{"com.oreon.smartsis.users.User",

	"userName", "java.lang.String",

	"password", "java.lang.String",

	"rolesCount", "java.lang.Integer",

	"email", "java.lang.String",

	"enabled", "java.lang.Boolean",

	},

	{"com.oreon.smartsis.users.Role",

	"name", "java.lang.String",

	"usersCount", "java.lang.Integer",

	},

	{"com.oreon.smartsis.fees.MonthlyFee",

	"grade.displayName", "java.lang.String",

	"gradeFeeItemsCount", "java.lang.Integer",

	"month.name", "java.lang.String",

	"total", "java.lang.Double",

	},

	{"com.oreon.smartsis.fees.FeeItem",

	"name", "java.lang.String",

	"defaultAmount", "java.lang.Double",

	},

	{"com.oreon.smartsis.fees.GradeFeeItem",

	"monthlyFee.displayName", "java.lang.String",

	"feeItem.displayName", "java.lang.String",

	"amount", "java.lang.Double",

	"applyToAllMonths", "java.lang.Boolean",

	},

	{"com.oreon.smartsis.fees.StudentPaidFee",

	"amountOwed", "java.lang.Double",

	"amountPaid", "java.lang.Double",

	"dueAmount", "java.lang.Double",

	"dateOfPayment", "java.util.Date",

	"student.displayName", "java.lang.String",

	"monthlyFee.displayName", "java.lang.String",

	"year", "java.lang.Integer",

	"month.name", "java.lang.String",

	},

	};

}
