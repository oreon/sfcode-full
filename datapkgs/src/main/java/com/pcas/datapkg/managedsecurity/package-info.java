@FilterDef(name = "archiveFilterDef", defaultCondition = "archived = :aArchived", parameters = @ParamDef(name = "aArchived", type = "string"))
package com.pcas.datapkg.managedsecurity;
import org.hibernate.annotations.FilterDef;
import org.hibernate.annotations.ParamDef;
