@FilterDefs({
		@FilterDef(name = "archiveFilterDef", defaultCondition = "archived = :aArchived", parameters = @ParamDef(name = "aArchived", type = "string")),

		@FilterDef(name = "tenantFilterDef", defaultCondition = "(tenant is null or tenant = :tenantId )", parameters = @ParamDef(name = "tenantId", type = "long"))

})
package com.pcas.datapkg.users;
import org.hibernate.annotations.FilterDef;
import org.hibernate.annotations.ParamDef;
import org.hibernate.annotations.FilterDefs;
