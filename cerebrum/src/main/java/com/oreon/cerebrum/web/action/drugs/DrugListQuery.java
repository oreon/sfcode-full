
package com.oreon.cerebrum.web.action.drugs;



import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;
import org.jboss.seam.framework.EntityQuery;
import org.witchcraft.base.entity.BaseQuery;
import org.witchcraft.base.entity.Range;

import org.jboss.seam.annotations.Observer;
	
	
@Name("drugList")
//@Scope(ScopeType.CONVERSATION)
public class DrugListQuery extends DrugListQueryBase implements java.io.Serializable{
	
	public DrugListQuery() {
		setOrder("drug.name");
	}
	
}
