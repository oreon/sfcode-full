@FilterDef(name = "archive", defaultCondition = "archived = :aArchived", 
		parameters = @ParamDef(name = "aArchived", type = "string")) 
		
package org.cerebrum.domain; 
import org.hibernate.annotations.FilterDef; 
import org.hibernate.annotations.ParamDef;