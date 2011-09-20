package com.oreon.cerebrum.web.action.patient;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;

import com.oreon.cerebrum.drugs.Drug;
import com.oreon.cerebrum.drugs.DrugInteraction;
import com.oreon.cerebrum.patient.PrescriptionItem;

@Scope(ScopeType.CONVERSATION)
@Name("prescriptionAction")
public class PrescriptionAction extends PrescriptionActionBase implements
		java.io.Serializable {
	
	private List<DrugInteraction> interactions = new ArrayList<DrugInteraction>();
	
	private Drug newDrug;

	public String addDrug(Drug drug){
		StringBuilder warnings = new StringBuilder();
		
		List<PrescriptionItem> items = getListPrescriptionItems();
	
		for (PrescriptionItem prescriptionItem : items) {
			DrugInteraction interaction = findInteraction(prescriptionItem.getDrug(), drug);
			if(interaction != null){
				warnings.append(interaction.getDescription());
				interactions.add(interaction);
			}
		}
		
		if(!StringUtils.isEmpty(warnings.toString()) ){
			addErrorMessage(warnings.toString());
			System.out.println(warnings.toString());
		}
		return "success";
	}
	
	public DrugInteraction findInteraction(Drug one, Drug two){
		Set<DrugInteraction> interactions = one.getDrugInteractions();
		for (DrugInteraction drugInteraction : interactions) {
			if(drugInteraction.getInteractingDrug().getId().equals(two.getId())){
				return drugInteraction;
			}
		}
		return null;
	}

	public void setNewDrug(Drug newDrug) {
		addDrug(newDrug);
		this.newDrug = newDrug;
	}

	public Drug getNewDrug() {
		return newDrug;
	}

	public void setInteractions(List<DrugInteraction> interactions) {
		this.interactions = interactions;
	}

	public List<DrugInteraction> getInteractions() {
		System.out.println("elements " + interactions.size());
		return interactions;
	}
}
