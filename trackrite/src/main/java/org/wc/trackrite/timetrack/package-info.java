@FilterDef(name = "archiveFilterDef", defaultCondition = "archived = :aArchived", parameters = @ParamDef(name = "aArchived", type = "string"))
package org.wc.trackrite.timetrack;
import org.hibernate.annotations.FilterDef;
import org.hibernate.annotations.ParamDef;
