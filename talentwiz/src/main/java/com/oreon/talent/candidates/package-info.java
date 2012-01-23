@FilterDef(name = "archiveFilterDef", defaultCondition = "archived = :aArchived", parameters = @ParamDef(name = "aArchived", type = "string"))
package com.oreon.talent.candidates;
import org.hibernate.annotations.FilterDef;
import org.hibernate.annotations.ParamDef;
