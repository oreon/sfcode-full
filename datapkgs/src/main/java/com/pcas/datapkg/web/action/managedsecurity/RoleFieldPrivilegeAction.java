package com.pcas.datapkg.web.action.managedsecurity;

import java.util.List;

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
	
	public static final String findQry = "Select r from RoleFieldPrivilege r where r.appRole = ? and r.metaField = ?";

	@Override
	public String save() {

		AccountPermission permission = findPermission("edit");

		if (instance.getWriteAccess()) {
			if (isNewPermission(permission))
				createAccountPermission(permission);
		} else {

			if (!isNewPermission(permission))
				removeAccountPermission(permission);
		}
		
		permission = findPermission("view");

		if (instance.getReadAccess()) {
			if (isNewPermission(permission))
				createAccountPermission(permission);
		} else {

			if (!isNewPermission(permission))
				removeAccountPermission(permission);
		}
		
		if ( instance.getId() != null )
			entityManager.merge( instance );
		else
			entityManager.persist( instance );

		
		return "success";
	}

	private boolean isNewPermission(AccountPermission permission) {
		return permission.getPermissionId() == null
				|| permission.getPermissionId() == 0;
	}

	private void createAccountPermission(AccountPermission accountPermission) {

		getEntityManager().persist(accountPermission);
	}
	
	private void removeAccountPermission(AccountPermission accountPermission) {

		getEntityManager().remove(accountPermission);
	}

	
	private AccountPermission findPermission(String action) {

		AccountPermission accountPermission = new AccountPermission();
		accountPermission.setAction(action
				+ WordUtils.capitalize(instance.getMetaField().getName()));
		accountPermission.setTarget(WordUtils.uncapitalize(ProjectUtils
				.getSimpleName(instance.getMetaField().getMetaEntity()
						.getDisplayName())));
		accountPermission.setDiscriminator(ROLE);
		accountPermission.setRecipient(instance.getAppRole().getName());

		List<AccountPermission> permissions = executeNamedQuery(
				"AccountPermission.findByPermission", accountPermission
						.getRecipient(), accountPermission.getTarget(),
				accountPermission.getAction(), accountPermission
						.getDiscriminator());

		if (!permissions.isEmpty())
			return permissions.get(0);

		return accountPermission;
	}
	
	public List<AccountPermission> getAccountPermissions(){
		List<AccountPermission> accountPermissions =  executeQuery( "Select a from AccountPermission a" );
		return accountPermissions;
	}
}
