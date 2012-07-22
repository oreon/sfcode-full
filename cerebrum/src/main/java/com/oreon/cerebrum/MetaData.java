package com.oreon.cerebrum;

public class MetaData {

	public static final String[][] ARR_FIELDS = {

	{"com.oreon.cerebrum.drugs.AtcDrug",

	"code", "java.lang.String",

	"name", "java.lang.String",

	"subcategoriesCount", "java.lang.Integer",

	"drug.displayName", "java.lang.String",

	"parent.displayName", "java.lang.String",

	},

	{"com.oreon.cerebrum.drugs.Drug",

	"name", "java.lang.String",

	"absorption", "java.lang.String",

	"biotransformation", "java.lang.String",

	"atcCodes", "java.lang.String",

	"contraIndication", "java.lang.String",

	"description", "java.lang.String",

	"dosageForm", "java.lang.String",

	"foodInteractions", "java.lang.String",

	"halfLife", "java.lang.String",

	"halfLifeNumberOfHours", "java.lang.Double",

	"indication", "java.lang.String",

	"mechanismOfAction", "java.lang.String",

	"patientInfo", "java.lang.String",

	"pharmacology", "java.lang.String",

	"drugInteractionsCount", "java.lang.Integer",

	"drugCategorysCount", "java.lang.Integer",

	"toxicity", "java.lang.String",

	"routeOfElimination", "java.lang.String",

	"volumeOfDistribution", "java.lang.String",

	"drugBankId", "java.lang.String",

	"categories", "java.lang.String",

	},

	{"com.oreon.cerebrum.drugs.DrugInteraction",

	"description", "java.lang.String",

	"drug.displayName", "java.lang.String",

	"interactingDrug.displayName", "java.lang.String",

	"severity.name", "java.lang.String",

	},

	{"com.oreon.cerebrum.drugs.DrugCategory",

	"name", "java.lang.String",

	"drugsCount", "java.lang.Integer",

	},

	{"com.oreon.cerebrum.patient.Patient",

	"firstName", "java.lang.String",

	"lastName", "java.lang.String",

	"dateOfBirth", "java.util.Date",

	"gender.name", "java.lang.String",

	"contactDetails.primaryPhone", "java.lang.String",

	"contactDetails.secondaryPhone", "java.lang.String",

	"contactDetails.email", "java.lang.String",

	"contactDetails.age", "java.lang.Integer",

	"admissionsCount", "java.lang.Integer",

	"prescriptionsCount", "java.lang.Integer",

	"address.streetAddress", "java.lang.String",

	"address.city", "java.lang.String",

	"address.State", "java.lang.String",

	"address.phone", "java.lang.String",

	"unusualOccurencesCount", "java.lang.Integer",

	"documentsCount", "java.lang.Integer",

	"allergysCount", "java.lang.Integer",

	"immunizationsCount", "java.lang.Integer",

	"healthNumber", "java.lang.String",

	"vitalValuesCount", "java.lang.Integer",

	},

	{"com.oreon.cerebrum.patient.Prescription",

	"prescriptionItemsCount", "java.lang.Integer",

	"patient.displayName", "java.lang.String",

	"notes", "java.lang.String",

	"active", "java.lang.Boolean",

	},

	{"com.oreon.cerebrum.patient.PrescriptionItem",

	"drug.displayName", "java.lang.String",

	"qty", "java.lang.Double",

	"route.name", "java.lang.String",

	"prescription.displayName", "java.lang.String",

	"duration", "java.lang.Integer",

	"remarks", "java.lang.String",

	"frequecy.displayName", "java.lang.String",

	},

	{"com.oreon.cerebrum.patient.Admission",

	"patient.displayName", "java.lang.String",

	"admissionNote", "java.lang.String",

	"dischargeDate", "java.util.Date",

	"bedStaysCount", "java.lang.Integer",

	"bed.displayName", "java.lang.String",

	"dischargeNote", "java.lang.String",

	"dischargeCode.name", "java.lang.String",

	},

	{"com.oreon.cerebrum.patient.Document",

	"name", "java.lang.String",

	"file", "FileAttachment",

	"notes", "java.lang.String",

	"patient.displayName", "java.lang.String",

	},

	{"com.oreon.cerebrum.patient.Allergy",

	"patient.displayName", "java.lang.String",

	"allergen.displayName", "java.lang.String",

	"severity.name", "java.lang.String",

	},

	{"com.oreon.cerebrum.patient.Allergen",

	"name", "java.lang.String",

	},

	{"com.oreon.cerebrum.patient.Immunization",

	"date", "java.util.Date",

	"patient.displayName", "java.lang.String",

	"vaccine.displayName", "java.lang.String",

	},

	{"com.oreon.cerebrum.patient.Vaccine",

	"name", "java.lang.String",

	},

	{"com.oreon.cerebrum.patient.Frequecy",

	"name", "java.lang.String",

	"qtyPerDay", "java.lang.Integer",

	},

	{"com.oreon.cerebrum.patient.TrackedVital",

	"name", "java.lang.String",

	},

	{"com.oreon.cerebrum.patient.VitalValue",

	"remarks", "java.lang.String",

	"value", "java.lang.Integer",

	"trackedVital.displayName", "java.lang.String",

	"patient.displayName", "java.lang.String",

	},

	{"com.oreon.cerebrum.patient.BedStay",

	"fromDate", "java.util.Date",

	"toDate", "java.util.Date",

	"admission.displayName", "java.lang.String",

	"bed.displayName", "java.lang.String",

	},

	{"com.oreon.cerebrum.unusualoccurences.UnusualOccurence",

	"occurenceType.displayName", "java.lang.String",

	"category.name", "java.lang.String",

	"severity.name", "java.lang.String",

	"description", "java.lang.String",

	"patient.displayName", "java.lang.String",

	"createdBy.displayName", "java.lang.String",

	},

	{"com.oreon.cerebrum.unusualoccurences.OccurenceType",

	"name", "java.lang.String",

	},

	{"com.oreon.cerebrum.employee.Employee",

	"firstName", "java.lang.String",

	"lastName", "java.lang.String",

	"dateOfBirth", "java.util.Date",

	"gender.name", "java.lang.String",

	"contactDetails.primaryPhone", "java.lang.String",

	"contactDetails.secondaryPhone", "java.lang.String",

	"contactDetails.email", "java.lang.String",

	"contactDetails.age", "java.lang.Integer",

	"employeeNumber", "java.lang.String",

	"unusualOccurencesCount", "java.lang.Integer",

	"appUser.displayName", "java.lang.String",

	},

	{"com.oreon.cerebrum.employee.Physician",

	"employeeNumber", "java.lang.String",

	"unusualOccurencesCount", "java.lang.Integer",

	"appUser.displayName", "java.lang.String",

	"specialization.displayName", "java.lang.String",

	},

	{"com.oreon.cerebrum.employee.Nurse",

	"employeeNumber", "java.lang.String",

	"unusualOccurencesCount", "java.lang.Integer",

	"appUser.displayName", "java.lang.String",

	"nurseSpecialty.displayName", "java.lang.String",

	},

	{"com.oreon.cerebrum.employee.Technician",

	"employeeNumber", "java.lang.String",

	"unusualOccurencesCount", "java.lang.Integer",

	"appUser.displayName", "java.lang.String",

	},

	{"com.oreon.cerebrum.employee.Clerk",

	"employeeNumber", "java.lang.String",

	"unusualOccurencesCount", "java.lang.Integer",

	"appUser.displayName", "java.lang.String",

	},

	{"com.oreon.cerebrum.employee.Specialization",

	"name", "java.lang.String",

	},

	{"com.oreon.cerebrum.employee.NurseSpecialty",

	"name", "java.lang.String",

	},

	{"com.oreon.cerebrum.users.AppUser",

	"userName", "java.lang.String",

	"password", "java.lang.String",

	"enabled", "java.lang.Boolean",

	"appRolesCount", "java.lang.Integer",

	"email", "java.lang.String",

	},

	{"com.oreon.cerebrum.users.AppRole",

	"name", "java.lang.String",

	"appUsersCount", "java.lang.Integer",

	},

	{"com.oreon.cerebrum.facility.Facility",

	"name", "java.lang.String",

	"wardsCount", "java.lang.Integer",

	},

	{"com.oreon.cerebrum.facility.Room",

	"bedsCount", "java.lang.Integer",

	"name", "java.lang.String",

	"roomType.displayName", "java.lang.String",

	"ward.displayName", "java.lang.String",

	},

	{"com.oreon.cerebrum.facility.Bed",

	"room.displayName", "java.lang.String",

	"name", "java.lang.String",

	"patient.displayName", "java.lang.String",

	},

	{"com.oreon.cerebrum.facility.Ward",

	"facility.displayName", "java.lang.String",

	"roomsCount", "java.lang.Integer",

	"name", "java.lang.String",

	"gender.name", "java.lang.String",

	},

	{"com.oreon.cerebrum.facility.RoomType",

	"name", "java.lang.String",

	"description", "java.lang.String",

	"rate", "java.lang.Double",

	"numberOfRooms", "java.lang.Integer",

	},

	{"com.oreon.cerebrum.appointment.Appointment",

	"start", "java.util.Date",

	"end", "java.util.Date",

	"physician.displayName", "java.lang.String",

	"patient.displayName", "java.lang.String",

	"remarks", "java.lang.String",

	"units", "java.lang.Integer",

	},

	{"com.oreon.cerebrum.appointment.Encounter",

	"historysCount", "java.lang.Integer",

	"patient.displayName", "java.lang.String",

	"physician.displayName", "java.lang.String",

	"notes", "java.lang.String",

	"prescribedTestsCount", "java.lang.Integer",

	},

	{"com.oreon.cerebrum.appointment.History",

	"encounter.displayName", "java.lang.String",

	"history", "java.lang.String",

	},

	{"com.oreon.cerebrum.appointment.DxTest",

	"name", "java.lang.String",

	"description", "java.lang.String",

	},

	{"com.oreon.cerebrum.appointment.PrescribedTest",

	"remarks", "java.lang.String",

	"dxTest.displayName", "java.lang.String",

	"encounter.displayName", "java.lang.String",

	},

	{"com.oreon.cerebrum.billing.Invoice",

	"invoiceItemsCount", "java.lang.Integer",

	"patient.displayName", "java.lang.String",

	"notes", "java.lang.String",

	},

	{"com.oreon.cerebrum.billing.Service",

	"name", "java.lang.String",

	"price", "java.lang.Double",

	},

	{"com.oreon.cerebrum.billing.InvoiceItem",

	"units", "java.lang.Integer",

	"service.displayName", "java.lang.String",

	"invoice.displayName", "java.lang.String",

	"total", "java.lang.Double",

	},

	{"com.oreon.cerebrum.ddx.Finding",

	"name", "java.lang.String",

	"differentialDxsCount", "java.lang.Integer",

	},

	{"com.oreon.cerebrum.ddx.PhysicalFinding",

	"name", "java.lang.String",

	"differentialDxsCount", "java.lang.Integer",

	"icdCode", "java.lang.String",

	},

	{"com.oreon.cerebrum.ddx.LabFinding",

	"name", "java.lang.String",

	"differentialDxsCount", "java.lang.Integer",

	"testName", "java.lang.String",

	},

	{"com.oreon.cerebrum.ddx.Disease",

	"gender.name", "java.lang.String",

	"name", "java.lang.String",

	"differentialDiagnosesCount", "java.lang.Integer",

	"relatedDisease.displayName", "java.lang.String",

	"conditionCategory.displayName", "java.lang.String",

	},

	{"com.oreon.cerebrum.ddx.ConditionFinding",

	"disease.displayName", "java.lang.String",

	"falsePositive", "java.lang.Boolean",

	},

	{"com.oreon.cerebrum.ddx.ConditionCategory",

	"name", "java.lang.String",

	},

	{"com.oreon.cerebrum.ddx.DifferentialDx",

	"name", "java.lang.String",

	"dxCategory.displayName", "java.lang.String",

	"finding.displayName", "java.lang.String",

	},

	{"com.oreon.cerebrum.ddx.DxCategory",

	"name", "java.lang.String",

	},

	{"com.oreon.cerebrum.ddx.PatientFinding",

	"finding.displayName", "java.lang.String",

	"patientDiffDx.displayName", "java.lang.String",

	},

	{"com.oreon.cerebrum.ddx.PatientDiffDx",

	"patientFindingsCount", "java.lang.Integer",

	"patient.displayName", "java.lang.String",

	"notes", "java.lang.String",

	},

	};

}
