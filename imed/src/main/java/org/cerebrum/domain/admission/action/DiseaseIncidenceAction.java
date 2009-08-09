package org.cerebrum.domain.admission.action;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Scope;

import org.jboss.seam.Component;
import org.jboss.seam.annotations.Begin;
import org.jboss.seam.annotations.End;
import org.jboss.seam.annotations.Factory;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Logger;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Out;

@Scope(ScopeType.CONVERSATION)
@Name("diseaseIncidenceAction")
public class DiseaseIncidenceAction extends DiseaseIncidenceActionBase
		implements
			java.io.Serializable {

}
