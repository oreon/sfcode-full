package com.oreon.smartsis;

public class MetaData {

	public static final String[][] ARR_FIELDS = {

	{"com.oreon.smartsis.domain.Employee",

	"firstName", "java.lang.String",

	"lastName", "java.lang.String",

	"contactDetails.displayName", "java.lang.String",

	"primaryPhone", "java.lang.String",

	"secondaryPhone", "java.lang.String",

	"email", "java.lang.String",

	"department.displayName", "java.lang.String",

	"employees", "java.lang.Employee",

	"name", "java.lang.uniqueNameType",

	"employeeNumber", "java.lang.String",

	"employeeType", "java.lang.EmployeeType",

	"manager.displayName", "java.lang.String",

	},

	{"com.oreon.smartsis.domain.Department",

	"employees", "java.lang.Employee",

	"name", "java.lang.uniqueNameType",

	},

	{"com.oreon.smartsis.domain.Student",

	"firstName", "java.lang.String",

	"lastName", "java.lang.String",

	"contactDetails.displayName", "java.lang.String",

	"primaryPhone", "java.lang.String",

	"secondaryPhone", "java.lang.String",

	"email", "java.lang.String",

	"picture", "java.lang.imageFile",

	"gender", "java.lang.Gender",

	"dateOfBirth", "java.lang.Date",

	"grade.displayName", "java.lang.String",

	"name", "java.lang.uniqueNameType",

	"students", "java.lang.Student",

	"exams", "java.lang.Exam",

	"ordinal", "java.lang.Integer",

	"section", "java.lang.String",

	"gradeSubjects", "java.lang.GradeSubject",

	"gradeFees", "java.lang.GradeFee",

	"age", "java.lang.Integer",

	},

	{"com.oreon.smartsis.domain.Sponsor",

	"firstName", "java.lang.String",

	"lastName", "java.lang.String",

	"contactDetails.displayName", "java.lang.String",

	"primaryPhone", "java.lang.String",

	"secondaryPhone", "java.lang.String",

	"email", "java.lang.String",

	},

	{"com.oreon.smartsis.domain.Sponsorship",

	"sponsor.displayName", "java.lang.String",

	"sponsor.firstName", "java.lang.String",

	"sponsor.lastName", "java.lang.String",

	"contactDetails.displayName", "java.lang.String",

	"primaryPhone", "java.lang.String",

	"secondaryPhone", "java.lang.String",

	"email", "java.lang.String",

	"student.displayName", "java.lang.String",

	"student.firstName", "java.lang.String",

	"student.lastName", "java.lang.String",

	"contactDetails.displayName", "java.lang.String",

	"primaryPhone", "java.lang.String",

	"secondaryPhone", "java.lang.String",

	"email", "java.lang.String",

	"picture", "java.lang.imageFile",

	"gender", "java.lang.Gender",

	"dateOfBirth", "java.lang.Date",

	"grade.displayName", "java.lang.String",

	"name", "java.lang.uniqueNameType",

	"students", "java.lang.Student",

	"exams", "java.lang.Exam",

	"ordinal", "java.lang.Integer",

	"section", "java.lang.String",

	"gradeSubjects", "java.lang.GradeSubject",

	"gradeFees", "java.lang.GradeFee",

	"age", "java.lang.Integer",

	"amount", "java.lang.Double",

	},

	{"com.oreon.smartsis.domain.Grade",

	"name", "java.lang.uniqueNameType",

	"students", "java.lang.Student",

	"exams", "java.lang.Exam",

	"ordinal", "java.lang.Integer",

	"section", "java.lang.String",

	"gradeSubjects", "java.lang.GradeSubject",

	"gradeFees", "java.lang.GradeFee",

	},

	{"com.oreon.smartsis.domain.Subject",

	"name", "java.lang.uniqueNameType",

	},

	{"com.oreon.smartsis.domain.GradeSubject",

	"subject.displayName", "java.lang.String",

	"name", "java.lang.uniqueNameType",

	"employee.displayName", "java.lang.String",

	"employee.firstName", "java.lang.String",

	"employee.lastName", "java.lang.String",

	"contactDetails.displayName", "java.lang.String",

	"primaryPhone", "java.lang.String",

	"secondaryPhone", "java.lang.String",

	"email", "java.lang.String",

	"department.displayName", "java.lang.String",

	"employees", "java.lang.Employee",

	"name", "java.lang.uniqueNameType",

	"employeeNumber", "java.lang.String",

	"employeeType", "java.lang.EmployeeType",

	"manager.displayName", "java.lang.String",

	"grade.displayName", "java.lang.String",

	"name", "java.lang.uniqueNameType",

	"students", "java.lang.Student",

	"exams", "java.lang.Exam",

	"ordinal", "java.lang.Integer",

	"section", "java.lang.String",

	"gradeSubjects", "java.lang.GradeSubject",

	"gradeFees", "java.lang.GradeFee",

	"courseDocumentses", "java.lang.CourseDocuments",

	},

	{"com.oreon.smartsis.domain.Exam",

	"name", "java.lang.uniqueNameType",

	"grades", "java.lang.Grade",

	"maxMarks", "java.lang.Integer",

	"passMarks", "java.lang.Integer",

	"ordinal", "java.lang.Integer",

	},

	{"com.oreon.smartsis.domain.ExamScore",

	"examName", "java.lang.String",

	"examInstance.displayName", "java.lang.String",

	"examScores", "java.lang.ExamScore",

	"exam.displayName", "java.lang.String",

	"name", "java.lang.uniqueNameType",

	"grades", "java.lang.Grade",

	"maxMarks", "java.lang.Integer",

	"passMarks", "java.lang.Integer",

	"ordinal", "java.lang.Integer",

	"gradeSubject.displayName", "java.lang.String",

	"subject.displayName", "java.lang.String",

	"name", "java.lang.uniqueNameType",

	"employee.displayName", "java.lang.String",

	"employee.firstName", "java.lang.String",

	"employee.lastName", "java.lang.String",

	"contactDetails.displayName", "java.lang.String",

	"primaryPhone", "java.lang.String",

	"secondaryPhone", "java.lang.String",

	"email", "java.lang.String",

	"department.displayName", "java.lang.String",

	"employees", "java.lang.Employee",

	"name", "java.lang.uniqueNameType",

	"employeeNumber", "java.lang.String",

	"employeeType", "java.lang.EmployeeType",

	"manager.displayName", "java.lang.String",

	"grade.displayName", "java.lang.String",

	"name", "java.lang.uniqueNameType",

	"students", "java.lang.Student",

	"exams", "java.lang.Exam",

	"ordinal", "java.lang.Integer",

	"section", "java.lang.String",

	"gradeSubjects", "java.lang.GradeSubject",

	"gradeFees", "java.lang.GradeFee",

	"courseDocumentses", "java.lang.CourseDocuments",

	"dateHeld", "java.lang.Date",

	"average", "java.lang.Integer",

	"subject", "java.lang.String",

	"marks", "java.lang.Integer",

	"percentage", "java.lang.Double",

	"rank", "java.lang.Integer",

	"student.displayName", "java.lang.String",

	"student.firstName", "java.lang.String",

	"student.lastName", "java.lang.String",

	"contactDetails.displayName", "java.lang.String",

	"primaryPhone", "java.lang.String",

	"secondaryPhone", "java.lang.String",

	"email", "java.lang.String",

	"picture", "java.lang.imageFile",

	"gender", "java.lang.Gender",

	"dateOfBirth", "java.lang.Date",

	"grade.displayName", "java.lang.String",

	"name", "java.lang.uniqueNameType",

	"students", "java.lang.Student",

	"exams", "java.lang.Exam",

	"ordinal", "java.lang.Integer",

	"section", "java.lang.String",

	"gradeSubjects", "java.lang.GradeSubject",

	"gradeFees", "java.lang.GradeFee",

	"age", "java.lang.Integer",

	},

	{"com.oreon.smartsis.domain.ExamInstance",

	"examScores", "java.lang.ExamScore",

	"exam.displayName", "java.lang.String",

	"name", "java.lang.uniqueNameType",

	"grades", "java.lang.Grade",

	"maxMarks", "java.lang.Integer",

	"passMarks", "java.lang.Integer",

	"ordinal", "java.lang.Integer",

	"gradeSubject.displayName", "java.lang.String",

	"subject.displayName", "java.lang.String",

	"name", "java.lang.uniqueNameType",

	"employee.displayName", "java.lang.String",

	"employee.firstName", "java.lang.String",

	"employee.lastName", "java.lang.String",

	"contactDetails.displayName", "java.lang.String",

	"primaryPhone", "java.lang.String",

	"secondaryPhone", "java.lang.String",

	"email", "java.lang.String",

	"department.displayName", "java.lang.String",

	"employees", "java.lang.Employee",

	"name", "java.lang.uniqueNameType",

	"employeeNumber", "java.lang.String",

	"employeeType", "java.lang.EmployeeType",

	"manager.displayName", "java.lang.String",

	"grade.displayName", "java.lang.String",

	"name", "java.lang.uniqueNameType",

	"students", "java.lang.Student",

	"exams", "java.lang.Exam",

	"ordinal", "java.lang.Integer",

	"section", "java.lang.String",

	"gradeSubjects", "java.lang.GradeSubject",

	"gradeFees", "java.lang.GradeFee",

	"courseDocumentses", "java.lang.CourseDocuments",

	"dateHeld", "java.lang.Date",

	"average", "java.lang.Integer",

	},

	{"com.oreon.smartsis.domain.GradeFee",

	"grade.displayName", "java.lang.String",

	"name", "java.lang.uniqueNameType",

	"students", "java.lang.Student",

	"exams", "java.lang.Exam",

	"ordinal", "java.lang.Integer",

	"section", "java.lang.String",

	"gradeSubjects", "java.lang.GradeSubject",

	"gradeFees", "java.lang.GradeFee",

	"fee.displayName", "java.lang.String",

	"name", "java.lang.uniqueNameType",

	"defaultAmount", "java.lang.Double",

	"frequency", "java.lang.Frequency",

	"amount", "java.lang.Double",

	},

	{"com.oreon.smartsis.domain.Fee",

	"name", "java.lang.uniqueNameType",

	"defaultAmount", "java.lang.Double",

	"frequency", "java.lang.Frequency",

	},

	{"com.oreon.smartsis.domain.CourseDocuments",

	"name", "java.lang.String",

	"document", "java.lang.imageFile",

	"gradeSubject.displayName", "java.lang.String",

	"subject.displayName", "java.lang.String",

	"name", "java.lang.uniqueNameType",

	"employee.displayName", "java.lang.String",

	"employee.firstName", "java.lang.String",

	"employee.lastName", "java.lang.String",

	"contactDetails.displayName", "java.lang.String",

	"primaryPhone", "java.lang.String",

	"secondaryPhone", "java.lang.String",

	"email", "java.lang.String",

	"department.displayName", "java.lang.String",

	"employees", "java.lang.Employee",

	"name", "java.lang.uniqueNameType",

	"employeeNumber", "java.lang.String",

	"employeeType", "java.lang.EmployeeType",

	"manager.displayName", "java.lang.String",

	"grade.displayName", "java.lang.String",

	"name", "java.lang.uniqueNameType",

	"students", "java.lang.Student",

	"exams", "java.lang.Exam",

	"ordinal", "java.lang.Integer",

	"section", "java.lang.String",

	"gradeSubjects", "java.lang.GradeSubject",

	"gradeFees", "java.lang.GradeFee",

	"courseDocumentses", "java.lang.CourseDocuments",

	},

	{"com.oreon.smartsis.domain.PaidFee",

	"amount", "java.lang.Double",

	"notes", "java.lang.largeText",

	"student.displayName", "java.lang.String",

	"student.firstName", "java.lang.String",

	"student.lastName", "java.lang.String",

	"contactDetails.displayName", "java.lang.String",

	"primaryPhone", "java.lang.String",

	"secondaryPhone", "java.lang.String",

	"email", "java.lang.String",

	"picture", "java.lang.imageFile",

	"gender", "java.lang.Gender",

	"dateOfBirth", "java.lang.Date",

	"grade.displayName", "java.lang.String",

	"name", "java.lang.uniqueNameType",

	"students", "java.lang.Student",

	"exams", "java.lang.Exam",

	"ordinal", "java.lang.Integer",

	"section", "java.lang.String",

	"gradeSubjects", "java.lang.GradeSubject",

	"gradeFees", "java.lang.GradeFee",

	"age", "java.lang.Integer",

	"gradeFee.displayName", "java.lang.String",

	"grade.displayName", "java.lang.String",

	"name", "java.lang.uniqueNameType",

	"students", "java.lang.Student",

	"exams", "java.lang.Exam",

	"ordinal", "java.lang.Integer",

	"section", "java.lang.String",

	"gradeSubjects", "java.lang.GradeSubject",

	"gradeFees", "java.lang.GradeFee",

	"fee.displayName", "java.lang.String",

	"name", "java.lang.uniqueNameType",

	"defaultAmount", "java.lang.Double",

	"frequency", "java.lang.Frequency",

	"amount", "java.lang.Double",

	},

	{"com.oreon.smartsis.exam.ElectronicExam",

	"name", "java.lang.uniqueNameType",

	"numberOfQuestions", "java.lang.Integer",

	"questions", "java.lang.Question",

	"gradeSubject.displayName", "java.lang.String",

	"subject.displayName", "java.lang.String",

	"name", "java.lang.uniqueNameType",

	"employee.displayName", "java.lang.String",

	"employee.firstName", "java.lang.String",

	"employee.lastName", "java.lang.String",

	"contactDetails.displayName", "java.lang.String",

	"primaryPhone", "java.lang.String",

	"secondaryPhone", "java.lang.String",

	"email", "java.lang.String",

	"department.displayName", "java.lang.String",

	"employees", "java.lang.Employee",

	"name", "java.lang.uniqueNameType",

	"employeeNumber", "java.lang.String",

	"employeeType", "java.lang.EmployeeType",

	"manager.displayName", "java.lang.String",

	"grade.displayName", "java.lang.String",

	"name", "java.lang.uniqueNameType",

	"students", "java.lang.Student",

	"exams", "java.lang.Exam",

	"ordinal", "java.lang.Integer",

	"section", "java.lang.String",

	"gradeSubjects", "java.lang.GradeSubject",

	"gradeFees", "java.lang.GradeFee",

	"courseDocumentses", "java.lang.CourseDocuments",

	"maxDuration", "java.lang.Integer",

	},

	{"com.oreon.smartsis.exam.Question",

	"text", "java.lang.largeText",

	"choices", "java.lang.Choice",

	"electronicExam.displayName", "java.lang.String",

	"name", "java.lang.uniqueNameType",

	"numberOfQuestions", "java.lang.Integer",

	"questions", "java.lang.Question",

	"gradeSubject.displayName", "java.lang.String",

	"subject.displayName", "java.lang.String",

	"name", "java.lang.uniqueNameType",

	"employee.displayName", "java.lang.String",

	"employee.firstName", "java.lang.String",

	"employee.lastName", "java.lang.String",

	"contactDetails.displayName", "java.lang.String",

	"primaryPhone", "java.lang.String",

	"secondaryPhone", "java.lang.String",

	"email", "java.lang.String",

	"department.displayName", "java.lang.String",

	"employees", "java.lang.Employee",

	"name", "java.lang.uniqueNameType",

	"employeeNumber", "java.lang.String",

	"employeeType", "java.lang.EmployeeType",

	"manager.displayName", "java.lang.String",

	"grade.displayName", "java.lang.String",

	"name", "java.lang.uniqueNameType",

	"students", "java.lang.Student",

	"exams", "java.lang.Exam",

	"ordinal", "java.lang.Integer",

	"section", "java.lang.String",

	"gradeSubjects", "java.lang.GradeSubject",

	"gradeFees", "java.lang.GradeFee",

	"courseDocumentses", "java.lang.CourseDocuments",

	"maxDuration", "java.lang.Integer",

	"correctChoice", "java.lang.ChoiceIndex",

	},

	{"com.oreon.smartsis.exam.Choice",

	"text", "java.lang.largeText",

	"question.displayName", "java.lang.String",

	"text", "java.lang.largeText",

	"choices", "java.lang.Choice",

	"electronicExam.displayName", "java.lang.String",

	"name", "java.lang.uniqueNameType",

	"numberOfQuestions", "java.lang.Integer",

	"questions", "java.lang.Question",

	"gradeSubject.displayName", "java.lang.String",

	"subject.displayName", "java.lang.String",

	"name", "java.lang.uniqueNameType",

	"employee.displayName", "java.lang.String",

	"employee.firstName", "java.lang.String",

	"employee.lastName", "java.lang.String",

	"contactDetails.displayName", "java.lang.String",

	"primaryPhone", "java.lang.String",

	"secondaryPhone", "java.lang.String",

	"email", "java.lang.String",

	"department.displayName", "java.lang.String",

	"employees", "java.lang.Employee",

	"name", "java.lang.uniqueNameType",

	"employeeNumber", "java.lang.String",

	"employeeType", "java.lang.EmployeeType",

	"manager.displayName", "java.lang.String",

	"grade.displayName", "java.lang.String",

	"name", "java.lang.uniqueNameType",

	"students", "java.lang.Student",

	"exams", "java.lang.Exam",

	"ordinal", "java.lang.Integer",

	"section", "java.lang.String",

	"gradeSubjects", "java.lang.GradeSubject",

	"gradeFees", "java.lang.GradeFee",

	"courseDocumentses", "java.lang.CourseDocuments",

	"maxDuration", "java.lang.Integer",

	"correctChoice", "java.lang.ChoiceIndex",

	},

	{"com.oreon.smartsis.exam.ElectronicExamInstance",

	"student.displayName", "java.lang.String",

	"student.firstName", "java.lang.String",

	"student.lastName", "java.lang.String",

	"contactDetails.displayName", "java.lang.String",

	"primaryPhone", "java.lang.String",

	"secondaryPhone", "java.lang.String",

	"email", "java.lang.String",

	"picture", "java.lang.imageFile",

	"gender", "java.lang.Gender",

	"dateOfBirth", "java.lang.Date",

	"grade.displayName", "java.lang.String",

	"name", "java.lang.uniqueNameType",

	"students", "java.lang.Student",

	"exams", "java.lang.Exam",

	"ordinal", "java.lang.Integer",

	"section", "java.lang.String",

	"gradeSubjects", "java.lang.GradeSubject",

	"gradeFees", "java.lang.GradeFee",

	"age", "java.lang.Integer",

	"questionInstances", "java.lang.QuestionInstance",

	"score", "java.lang.Integer",

	"electronicExamEvent.displayName", "java.lang.String",

	"electronicExam.displayName", "java.lang.String",

	"name", "java.lang.uniqueNameType",

	"numberOfQuestions", "java.lang.Integer",

	"questions", "java.lang.Question",

	"gradeSubject.displayName", "java.lang.String",

	"subject.displayName", "java.lang.String",

	"name", "java.lang.uniqueNameType",

	"employee.displayName", "java.lang.String",

	"employee.firstName", "java.lang.String",

	"employee.lastName", "java.lang.String",

	"contactDetails.displayName", "java.lang.String",

	"primaryPhone", "java.lang.String",

	"secondaryPhone", "java.lang.String",

	"email", "java.lang.String",

	"department.displayName", "java.lang.String",

	"employees", "java.lang.Employee",

	"name", "java.lang.uniqueNameType",

	"employeeNumber", "java.lang.String",

	"employeeType", "java.lang.EmployeeType",

	"manager.displayName", "java.lang.String",

	"grade.displayName", "java.lang.String",

	"name", "java.lang.uniqueNameType",

	"students", "java.lang.Student",

	"exams", "java.lang.Exam",

	"ordinal", "java.lang.Integer",

	"section", "java.lang.String",

	"gradeSubjects", "java.lang.GradeSubject",

	"gradeFees", "java.lang.GradeFee",

	"courseDocumentses", "java.lang.CourseDocuments",

	"maxDuration", "java.lang.Integer",

	"electronicExamInstances", "java.lang.ElectronicExamInstance",

	"dateOfExam", "java.lang.Date",

	"remarks", "java.lang.largeText",

	"timeTaken", "java.lang.Integer",

	},

	{"com.oreon.smartsis.exam.QuestionInstance",

	"electronicExamInstance.displayName", "java.lang.String",

	"student.displayName", "java.lang.String",

	"student.firstName", "java.lang.String",

	"student.lastName", "java.lang.String",

	"contactDetails.displayName", "java.lang.String",

	"primaryPhone", "java.lang.String",

	"secondaryPhone", "java.lang.String",

	"email", "java.lang.String",

	"picture", "java.lang.imageFile",

	"gender", "java.lang.Gender",

	"dateOfBirth", "java.lang.Date",

	"grade.displayName", "java.lang.String",

	"name", "java.lang.uniqueNameType",

	"students", "java.lang.Student",

	"exams", "java.lang.Exam",

	"ordinal", "java.lang.Integer",

	"section", "java.lang.String",

	"gradeSubjects", "java.lang.GradeSubject",

	"gradeFees", "java.lang.GradeFee",

	"age", "java.lang.Integer",

	"questionInstances", "java.lang.QuestionInstance",

	"score", "java.lang.Integer",

	"electronicExamEvent.displayName", "java.lang.String",

	"electronicExam.displayName", "java.lang.String",

	"name", "java.lang.uniqueNameType",

	"numberOfQuestions", "java.lang.Integer",

	"questions", "java.lang.Question",

	"gradeSubject.displayName", "java.lang.String",

	"subject.displayName", "java.lang.String",

	"name", "java.lang.uniqueNameType",

	"employee.displayName", "java.lang.String",

	"employee.firstName", "java.lang.String",

	"employee.lastName", "java.lang.String",

	"contactDetails.displayName", "java.lang.String",

	"primaryPhone", "java.lang.String",

	"secondaryPhone", "java.lang.String",

	"email", "java.lang.String",

	"department.displayName", "java.lang.String",

	"employees", "java.lang.Employee",

	"name", "java.lang.uniqueNameType",

	"employeeNumber", "java.lang.String",

	"employeeType", "java.lang.EmployeeType",

	"manager.displayName", "java.lang.String",

	"grade.displayName", "java.lang.String",

	"name", "java.lang.uniqueNameType",

	"students", "java.lang.Student",

	"exams", "java.lang.Exam",

	"ordinal", "java.lang.Integer",

	"section", "java.lang.String",

	"gradeSubjects", "java.lang.GradeSubject",

	"gradeFees", "java.lang.GradeFee",

	"courseDocumentses", "java.lang.CourseDocuments",

	"maxDuration", "java.lang.Integer",

	"electronicExamInstances", "java.lang.ElectronicExamInstance",

	"dateOfExam", "java.lang.Date",

	"remarks", "java.lang.largeText",

	"timeTaken", "java.lang.Integer",

	"question.displayName", "java.lang.String",

	"text", "java.lang.largeText",

	"choices", "java.lang.Choice",

	"electronicExam.displayName", "java.lang.String",

	"name", "java.lang.uniqueNameType",

	"numberOfQuestions", "java.lang.Integer",

	"questions", "java.lang.Question",

	"gradeSubject.displayName", "java.lang.String",

	"subject.displayName", "java.lang.String",

	"name", "java.lang.uniqueNameType",

	"employee.displayName", "java.lang.String",

	"employee.firstName", "java.lang.String",

	"employee.lastName", "java.lang.String",

	"contactDetails.displayName", "java.lang.String",

	"primaryPhone", "java.lang.String",

	"secondaryPhone", "java.lang.String",

	"email", "java.lang.String",

	"department.displayName", "java.lang.String",

	"employees", "java.lang.Employee",

	"name", "java.lang.uniqueNameType",

	"employeeNumber", "java.lang.String",

	"employeeType", "java.lang.EmployeeType",

	"manager.displayName", "java.lang.String",

	"grade.displayName", "java.lang.String",

	"name", "java.lang.uniqueNameType",

	"students", "java.lang.Student",

	"exams", "java.lang.Exam",

	"ordinal", "java.lang.Integer",

	"section", "java.lang.String",

	"gradeSubjects", "java.lang.GradeSubject",

	"gradeFees", "java.lang.GradeFee",

	"courseDocumentses", "java.lang.CourseDocuments",

	"maxDuration", "java.lang.Integer",

	"correctChoice", "java.lang.ChoiceIndex",

	"selectedChoice.displayName", "java.lang.String",

	"text", "java.lang.largeText",

	"question.displayName", "java.lang.String",

	"text", "java.lang.largeText",

	"choices", "java.lang.Choice",

	"electronicExam.displayName", "java.lang.String",

	"name", "java.lang.uniqueNameType",

	"numberOfQuestions", "java.lang.Integer",

	"questions", "java.lang.Question",

	"gradeSubject.displayName", "java.lang.String",

	"subject.displayName", "java.lang.String",

	"name", "java.lang.uniqueNameType",

	"employee.displayName", "java.lang.String",

	"employee.firstName", "java.lang.String",

	"employee.lastName", "java.lang.String",

	"contactDetails.displayName", "java.lang.String",

	"primaryPhone", "java.lang.String",

	"secondaryPhone", "java.lang.String",

	"email", "java.lang.String",

	"department.displayName", "java.lang.String",

	"employees", "java.lang.Employee",

	"name", "java.lang.uniqueNameType",

	"employeeNumber", "java.lang.String",

	"employeeType", "java.lang.EmployeeType",

	"manager.displayName", "java.lang.String",

	"grade.displayName", "java.lang.String",

	"name", "java.lang.uniqueNameType",

	"students", "java.lang.Student",

	"exams", "java.lang.Exam",

	"ordinal", "java.lang.Integer",

	"section", "java.lang.String",

	"gradeSubjects", "java.lang.GradeSubject",

	"gradeFees", "java.lang.GradeFee",

	"courseDocumentses", "java.lang.CourseDocuments",

	"maxDuration", "java.lang.Integer",

	"correctChoice", "java.lang.ChoiceIndex",

	},

	{"com.oreon.smartsis.exam.ElectronicExamEvent",

	"electronicExam.displayName", "java.lang.String",

	"name", "java.lang.uniqueNameType",

	"numberOfQuestions", "java.lang.Integer",

	"questions", "java.lang.Question",

	"gradeSubject.displayName", "java.lang.String",

	"subject.displayName", "java.lang.String",

	"name", "java.lang.uniqueNameType",

	"employee.displayName", "java.lang.String",

	"employee.firstName", "java.lang.String",

	"employee.lastName", "java.lang.String",

	"contactDetails.displayName", "java.lang.String",

	"primaryPhone", "java.lang.String",

	"secondaryPhone", "java.lang.String",

	"email", "java.lang.String",

	"department.displayName", "java.lang.String",

	"employees", "java.lang.Employee",

	"name", "java.lang.uniqueNameType",

	"employeeNumber", "java.lang.String",

	"employeeType", "java.lang.EmployeeType",

	"manager.displayName", "java.lang.String",

	"grade.displayName", "java.lang.String",

	"name", "java.lang.uniqueNameType",

	"students", "java.lang.Student",

	"exams", "java.lang.Exam",

	"ordinal", "java.lang.Integer",

	"section", "java.lang.String",

	"gradeSubjects", "java.lang.GradeSubject",

	"gradeFees", "java.lang.GradeFee",

	"courseDocumentses", "java.lang.CourseDocuments",

	"maxDuration", "java.lang.Integer",

	"electronicExamInstances", "java.lang.ElectronicExamInstance",

	"dateOfExam", "java.lang.Date",

	"remarks", "java.lang.largeText",

	},

	{"com.oreon.smartsis.attendance.Attendance",

	"student.displayName", "java.lang.String",

	"student.firstName", "java.lang.String",

	"student.lastName", "java.lang.String",

	"contactDetails.displayName", "java.lang.String",

	"primaryPhone", "java.lang.String",

	"secondaryPhone", "java.lang.String",

	"email", "java.lang.String",

	"picture", "java.lang.imageFile",

	"gender", "java.lang.Gender",

	"dateOfBirth", "java.lang.Date",

	"grade.displayName", "java.lang.String",

	"name", "java.lang.uniqueNameType",

	"students", "java.lang.Student",

	"exams", "java.lang.Exam",

	"ordinal", "java.lang.Integer",

	"section", "java.lang.String",

	"gradeSubjects", "java.lang.GradeSubject",

	"gradeFees", "java.lang.GradeFee",

	"age", "java.lang.Integer",

	"date", "java.lang.Date",

	"gradeSubject.displayName", "java.lang.String",

	"subject.displayName", "java.lang.String",

	"name", "java.lang.uniqueNameType",

	"employee.displayName", "java.lang.String",

	"employee.firstName", "java.lang.String",

	"employee.lastName", "java.lang.String",

	"contactDetails.displayName", "java.lang.String",

	"primaryPhone", "java.lang.String",

	"secondaryPhone", "java.lang.String",

	"email", "java.lang.String",

	"department.displayName", "java.lang.String",

	"employees", "java.lang.Employee",

	"name", "java.lang.uniqueNameType",

	"employeeNumber", "java.lang.String",

	"employeeType", "java.lang.EmployeeType",

	"manager.displayName", "java.lang.String",

	"grade.displayName", "java.lang.String",

	"name", "java.lang.uniqueNameType",

	"students", "java.lang.Student",

	"exams", "java.lang.Exam",

	"ordinal", "java.lang.Integer",

	"section", "java.lang.String",

	"gradeSubjects", "java.lang.GradeSubject",

	"gradeFees", "java.lang.GradeFee",

	"courseDocumentses", "java.lang.CourseDocuments",

	"absenceCode", "java.lang.AbsenceCode",

	"gradeAttendance.displayName", "java.lang.String",

	"attendances", "java.lang.Attendance",

	"date", "java.lang.Date",

	"grade.displayName", "java.lang.String",

	"name", "java.lang.uniqueNameType",

	"students", "java.lang.Student",

	"exams", "java.lang.Exam",

	"ordinal", "java.lang.Integer",

	"section", "java.lang.String",

	"gradeSubjects", "java.lang.GradeSubject",

	"gradeFees", "java.lang.GradeFee",

	},

	{"com.oreon.smartsis.attendance.GradeAttendance",

	"attendances", "java.lang.Attendance",

	"date", "java.lang.Date",

	"grade.displayName", "java.lang.String",

	"name", "java.lang.uniqueNameType",

	"students", "java.lang.Student",

	"exams", "java.lang.Exam",

	"ordinal", "java.lang.Integer",

	"section", "java.lang.String",

	"gradeSubjects", "java.lang.GradeSubject",

	"gradeFees", "java.lang.GradeFee",

	},

	{"com.oreon.smartsis.hostel.Room",

	"beds", "java.lang.Bed",

	"name", "java.lang.nameType",

	"hostel.displayName", "java.lang.String",

	"name", "java.lang.String",

	"rooms", "java.lang.Room",

	"gender", "java.lang.Gender",

	"charges", "java.lang.Double",

	},

	{"com.oreon.smartsis.hostel.Bed",

	"room.displayName", "java.lang.String",

	"beds", "java.lang.Bed",

	"name", "java.lang.nameType",

	"hostel.displayName", "java.lang.String",

	"name", "java.lang.String",

	"rooms", "java.lang.Room",

	"gender", "java.lang.Gender",

	"charges", "java.lang.Double",

	"name", "java.lang.nameType",

	},

	{"com.oreon.smartsis.hostel.Hostel",

	"name", "java.lang.String",

	"rooms", "java.lang.Room",

	"gender", "java.lang.Gender",

	},

	{"com.oreon.smartsis.hostel.BedAllocation",

	"startDate", "java.lang.Date",

	"endDate", "java.lang.Date",

	"bed.displayName", "java.lang.String",

	"room.displayName", "java.lang.String",

	"beds", "java.lang.Bed",

	"name", "java.lang.nameType",

	"hostel.displayName", "java.lang.String",

	"name", "java.lang.String",

	"rooms", "java.lang.Room",

	"gender", "java.lang.Gender",

	"charges", "java.lang.Double",

	"name", "java.lang.nameType",

	"student.displayName", "java.lang.String",

	"student.firstName", "java.lang.String",

	"student.lastName", "java.lang.String",

	"contactDetails.displayName", "java.lang.String",

	"primaryPhone", "java.lang.String",

	"secondaryPhone", "java.lang.String",

	"email", "java.lang.String",

	"picture", "java.lang.imageFile",

	"gender", "java.lang.Gender",

	"dateOfBirth", "java.lang.Date",

	"grade.displayName", "java.lang.String",

	"name", "java.lang.uniqueNameType",

	"students", "java.lang.Student",

	"exams", "java.lang.Exam",

	"ordinal", "java.lang.Integer",

	"section", "java.lang.String",

	"gradeSubjects", "java.lang.GradeSubject",

	"gradeFees", "java.lang.GradeFee",

	"age", "java.lang.Integer",

	"remarks", "java.lang.largeText",

	},

	};

}
