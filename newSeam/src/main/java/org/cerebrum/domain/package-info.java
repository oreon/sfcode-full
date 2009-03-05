@FilterDef(name = "current", defaultCondition = "archived = :arch", 
		parameters = @ParamDef(name = "arch", type = "Boolean")) 
		
package org.cerebrum.domain; 
import org.hibernate.annotations.FilterDef; 
import org.hibernate.annotations.ParamDef;