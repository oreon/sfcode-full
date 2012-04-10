package com.pcas.datapkg.web.action.customReports;

import java.util.List;
import java.util.Set;

import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;

import com.pcas.datapkg.customReports.MetaEntity;
import com.pcas.datapkg.customReports.MetaField;
import com.pcas.datapkg.managedsecurity.RoleFieldPrivilege;
import com.pcas.datapkg.users.AppRole;
import com.pcas.datapkg.web.action.users.AppRoleListQuery;

@Name("metaEntityList")
// @Scope(ScopeType.CONVERSATION)
public class MetaEntityListQuery extends MetaEntityListQueryBase implements
		java.io.Serializable {

	@In(create = true)
	AppRoleListQuery appRoleList;

	// @Override
	public List<MetaEntity> getResultListWithRoleFields() {

		List<MetaEntity> list = super.getResultList();
		List<AppRole> roles = appRoleList.getResultList();

		for (MetaEntity metaEntity : list) {
			Set<MetaField> flds = metaEntity.getMetaFields();

			for (MetaField metaField : flds) {

				for (AppRole appRole : roles) {
					if (!(containsRole(metaField, appRole))) {
						RoleFieldPrivilege roleFieldPrivilege = new RoleFieldPrivilege();
						roleFieldPrivilege.setAppRole(appRole);
						// roleFieldPrivilege.set
						metaField.addRoleFieldPrivilege(roleFieldPrivilege);
					}
				}
			}
		}

		return list;

	}

	private boolean containsRole(MetaField metaField, AppRole appRole) {
		Set<RoleFieldPrivilege> roleFields = metaField.getRoleFieldPrivileges();
		for (RoleFieldPrivilege roleFieldPrivilege : roleFields) {
			if(roleFieldPrivilege.getAppRole().getId().equals(appRole.getId()))
				return true;
		}
		return false;
	}

}
