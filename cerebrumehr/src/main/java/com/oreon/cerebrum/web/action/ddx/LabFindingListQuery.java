package com.oreon.cerebrum.web.action.ddx;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;

@Name("labFindingList")
@Scope(ScopeType.CONVERSATION)
public class LabFindingListQuery extends LabFindingListQueryBase
		implements
			java.io.Serializable {

}
