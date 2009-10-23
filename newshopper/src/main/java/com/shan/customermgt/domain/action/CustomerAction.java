package com.shan.customermgt.domain.action;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;
import org.jboss.seam.annotations.web.RequestParameter;

@Scope(ScopeType.CONVERSATION)
@Name("customerAction")
public class CustomerAction extends CustomerActionBase implements
		java.io.Serializable {
	

}
