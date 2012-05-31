
	
package com.pcas.datapkg.web.action.customReports;
	

import java.util.List;
import java.util.Set;

import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;

import com.pcas.datapkg.customReports.MetaField;
import com.pcas.datapkg.managedsecurity.RoleFieldPrivilege;
import com.pcas.datapkg.users.AppRole;
import com.pcas.datapkg.web.action.managedsecurity.RoleFieldPrivilegeAction;
import com.pcas.datapkg.web.action.users.AppRoleListQuery;

	
//@Scope(ScopeType.CONVERSATION)
@Name("metaEntityAction")
public class MetaEntityAction extends MetaEntityActionBase implements java.io.Serializable{
	
	@In(create = true)
	AppRoleListQuery appRoleList;
	
	@In(create = true)
	RoleFieldPrivilegeAction roleFieldPrivilegeAction;
	
	@Override
	public void prePopulateListMetaFields() {
		
		listMetaFields.clear();
		
		List<AppRole> roles = appRoleList.getResultList();
		
		if(instance == null ) return;
		
		List<MetaField> flds = instance.getListMetaFields();

		for (MetaField metaField : flds) {

			for (AppRole appRole : roles) {
				if (!(containsRole(metaField, appRole))) {
					RoleFieldPrivilege roleFieldPrivilege = new RoleFieldPrivilege();
					roleFieldPrivilege.setAppRole(appRole);
					// roleFieldPrivilege.set
					metaField.addRoleFieldPrivilege(roleFieldPrivilege);
				}
			}
			
			listMetaFields.add(metaField);
		}
	}
	
	private boolean containsRole(MetaField metaField, AppRole appRole) {
		Set<RoleFieldPrivilege> roleFields = metaField.getRoleFieldPrivileges();
		for (RoleFieldPrivilege roleFieldPrivilege : roleFields) {
			if(roleFieldPrivilege.getAppRole().getId().equals(appRole.getId()))
				return true;
		}
		return false;
	}
	
	@Override
	public String save() {
		

		List<MetaField> flds = listMetaFields;

		for (MetaField metaField : flds) {
			
			List<RoleFieldPrivilege> roleFlds = metaField.getListRoleFieldPrivileges();
			
			for (RoleFieldPrivilege roleFieldPrivilege : roleFlds) {
				//if (!(containsRole(metaField, appRole))) {
					
				//}
				entityManager.merge ( roleFieldPrivilege );
				//roleFieldPrivilegeAction.setInstance(roleFieldPrivilege);
				//roleFieldPrivilegeAction.save();
				
			}
			
			
		}
		
		
		return super.save();
	}
}
	