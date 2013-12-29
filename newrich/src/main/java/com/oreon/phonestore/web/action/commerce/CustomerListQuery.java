package com.oreon.phonestore.web.action.commerce;

import java.io.Serializable;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;

@Name("customerList")
@Scope(ScopeType.CONVERSATION)
public class CustomerListQuery extends CustomerListQueryBase implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1718680691977070726L;

}
