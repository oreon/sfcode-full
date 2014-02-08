@FilterDefs({
		@FilterDef(name = "archiveFilterDef", defaultCondition = "archived = :aArchived", parameters = @ParamDef(name = "aArchived", type = "string")),

		@FilterDef(name = "tenantFilterDef", defaultCondition = "(tenant is null or tenant = 0 or tenant = :tenantId  )", parameters = @ParamDef(name = "tenantId", type = "long"))

})
package com.oreon.phonestore;
import org.hibernate.annotations.FilterDef;
import org.hibernate.annotations.ParamDef;
import org.hibernate.annotations.FilterDefs;
