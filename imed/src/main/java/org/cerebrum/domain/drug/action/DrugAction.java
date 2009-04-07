package org.cerebrum.domain.drug.action;

import java.util.ArrayList;
import java.util.List;

import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;

import org.apache.commons.lang.StringUtils;
import org.cerebrum.domain.drug.Drug;
import org.hibernate.criterion.Order;
import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Factory;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Observer;
import org.jboss.seam.annotations.Out;
import org.jboss.seam.annotations.Scope;
import org.jboss.seam.annotations.datamodel.DataModel;
import org.jboss.seam.annotations.datamodel.DataModelSelection;
import org.witchcraft.seam.action.BaseAction;

import com.icesoft.faces.component.selectinputtext.SelectInputText;


@Scope(ScopeType.CONVERSATION)
@Name("drugAction")
public class DrugAction extends BaseAction<Drug> implements
		java.io.Serializable {

	@In(create = true)
	@Out(required = false)
	@DataModelSelection
	private Drug drug;

	@DataModel
	private List<Drug> drugList;

	private String typedDrugName;

	@Factory("drugList")
	@Observer("archivedDrug")
	public void findRecords() {
		search();
	}

	public Drug getEntity() {
		return drug;
	}

	public String getTypedDrugName() {
		return typedDrugName;
	}

	public void setTypedDrugName(String typedDrugName) {
		this.typedDrugName = typedDrugName;
	}

	@Override
	public void setEntity(Drug t) {
		this.drug = t;
	}

	@Override
	public void setEntityList(List<Drug> list) {
		this.drugList = list;
	}

	public void updateAssociations() {

	}
	

	public Order getOrder() {
		return Order.asc("name");
	}

	public List<Drug> getEntityList() {
		if (drugList == null) {
			findRecords();
		}
		return drugList;
	}

	public List<SelectItem> getAutoCompleteSuggestions() {
		List<SelectItem> suggestions = new ArrayList<SelectItem>();
		
		if(drugList == null)
			search();

		if (StringUtils.length(typedDrugName) >= 3) {

			for (Drug drug : drugList) {
				
				String drugName = drug.getName().toUpperCase();
				typedDrugName = typedDrugName.toUpperCase();

				if (drugName.startsWith(typedDrugName)) {
					SelectItem item = new SelectItem();
					StringBuffer buffer = new StringBuffer( drug.getName() );
					//buffer.insert(0, "<b>");
					//buffer.insert(typedDrugName.length() + 3, "</b>");
					item.setLabel(buffer.toString());
					item.setEscape(false);
					item.setValue(drug);
					suggestions.add(item);
				}
				
				/*
				if (  typedDrugName.charAt(0) > drugName.charAt(0) ){
					break;
				}*/
			}

		}
		return suggestions;
	}

	public void selectInputValueChanged(ValueChangeEvent event) {

		if (event.getComponent() instanceof SelectInputText) {

			// get the number of displayable records from the component
			//SelectInputText autoComplete = (SelectInputText) event.getComponent();
			
			
			// get the new value typed by component user.
			if(!StringUtils.isEmpty((String)event.getNewValue()) )
				typedDrugName = (String) event.getNewValue();
			
			//if ( StringUtils.length(typedDrugName) < 3 )
			//	autoComplete.se
			System.out.println("typedDrugName: " + typedDrugName);
		}
	}
	
	


}
