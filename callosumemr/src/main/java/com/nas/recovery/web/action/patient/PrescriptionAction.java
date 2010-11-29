package com.nas.recovery.web.action.patient;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;
import javax.persistence.EntityManager;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import org.apache.commons.lang.StringUtils;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Scope;

import org.jboss.seam.annotations.Begin;
import org.jboss.seam.annotations.End;
import org.jboss.seam.annotations.Factory;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Logger;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Out;
import org.jboss.seam.Component;
import org.jboss.seam.security.Identity;

import org.jboss.seam.annotations.datamodel.DataModel;
import org.jboss.seam.annotations.datamodel.DataModelSelection;
import org.jboss.seam.faces.FacesMessages;
import org.jboss.seam.log.Log;
import org.jboss.seam.annotations.Observer;

import com.oreon.callosum.patient.PrescriptionItem;

//@Scope(ScopeType.CONVERSATION)
@Name("prescriptionAction")
public class PrescriptionAction extends PrescriptionActionBase implements
		java.io.Serializable {
	
	
	@Override
	public void loadFromTemplate() {
		super.loadFromTemplate();
			
		if(getEntityTemplate() != null){
			Set<PrescriptionItem> items = getInstance().getPrescriptionItems();			
			for (PrescriptionItem prescriptionItem : items) {
				prescriptionItem.setDrug(getEntityManager().merge(prescriptionItem.getDrug()));
			}
			//return;
		}
	}

}
