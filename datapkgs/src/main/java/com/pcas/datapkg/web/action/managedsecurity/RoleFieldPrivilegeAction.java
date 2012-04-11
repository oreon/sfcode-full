package com.pcas.datapkg.web.action.managedsecurity;

import org.apache.commons.lang.WordUtils;
import org.jboss.seam.annotations.Name;
import org.witchcraft.seam.security.AccountPermission;

import com.pcas.datapkg.ProjectUtils;

//@Scope(ScopeType.CONVERSATION)
@Name("roleFieldPrivilegeAction")
public class RoleFieldPrivilegeAction extends RoleFieldPrivilegeActionBase
		implements java.io.Serializable {

	public static final String USER = "user";
	public static final String ROLE = "role";

	@Override
	public String save() {

		if (instance.getWriteAccess()) {
			createAccountPermission("edit");
		}
		if (instance.getReadAccess()) {
			createAccountPermission("view");
		}

		return super.save();
	}

	private void createAccountPermission(String action) {
		AccountPermission accountPermission = new AccountPermission();

		accountPermission.setAction(action + WordUtils.capitalize( instance.getMetaField().getName() ) );
		accountPermission.setTarget(
				WordUtils.uncapitalize(ProjectUtils.getSimpleName(instance.getMetaField().getMetaEntity().getDisplayName()) ) );
		accountPermission.setDiscriminator(ROLE);
		accountPermission.setRecipient(instance.getAppRole().getName());

		getEntityManager().persist(accountPermission);
	}
}
