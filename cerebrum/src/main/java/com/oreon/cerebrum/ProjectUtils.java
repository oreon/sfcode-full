package com.oreon.cerebrum;

import java.util.Set;

import com.oreon.cerebrum.encounter.Encounter;
import com.oreon.cerebrum.encounter.PrescribedTest;
import com.oreon.cerebrum.prescription.Prescription;
import com.oreon.cerebrum.prescription.PrescriptionItem;

public class ProjectUtils {
	
	public static String getPrescripitonItems(Prescription prescription){
		Set<PrescriptionItem> items = prescription.getPrescriptionItems();
		StringBuilder builder = new StringBuilder();
		for (PrescriptionItem prescriptionItem : items) {
			builder.append(prescriptionItem.getDrug().getName() + " " + prescriptionItem.getStrength() + " " +  prescriptionItem.getFrequency().getName() + "<br/>");
		}
		return builder.toString();
	}

	public static String getTests(Encounter encounter) {
		StringBuilder builder = new StringBuilder();
		for (PrescribedTest prescribedTest : encounter.getPrescribedTests()){
			builder.append(prescribedTest.getDxTest().getName());
		}
		return builder.toString();
	}

}
