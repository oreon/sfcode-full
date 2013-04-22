package com.oreon.cerebrum;

import java.util.Set;

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

}
