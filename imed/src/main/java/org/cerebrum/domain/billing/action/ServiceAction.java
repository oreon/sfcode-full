package org.cerebrum.domain.billing.action;

import javax.faces.event.PhaseId;
import javax.faces.event.ValueChangeEvent;

import org.cerebrum.domain.billing.ProcedureCode;
import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;

@Scope(ScopeType.CONVERSATION)
@Name("serviceAction")
public class ServiceAction extends ServiceActionBase
		implements
			java.io.Serializable {

	public void onProcedureCodeChange(ValueChangeEvent event) {
		PhaseId phaseId = event.getPhaseId();
		if(event.getNewValue() instanceof ProcedureCode){
			getEntity().setProcedureCode((ProcedureCode)event.getNewValue());
		}
		//if(phase)
		//String oldValue = (String) event.getOldValue();
		//String newValue = (String) event.getNewValue();
		
		//FacesContext.getCurrentInstance().renderResponse();
	}
}
