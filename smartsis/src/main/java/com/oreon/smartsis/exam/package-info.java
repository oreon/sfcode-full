@FilterDef(name = "archiveFilterDef", defaultCondition = "archived = :aArchived", parameters = @ParamDef(name = "aArchived", type = "string"))
package com.oreon.smartsis.exam;
import org.hibernate.annotations.FilterDef;
import org.hibernate.annotations.ParamDef;
