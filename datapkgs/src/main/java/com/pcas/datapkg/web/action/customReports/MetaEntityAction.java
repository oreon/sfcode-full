package com.pcas.datapkg.web.action.customReports;

import java.util.List;
import java.util.Set;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Begin;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;

import com.pcas.datapkg.customReports.EntityFieldPrivilege;
import com.pcas.datapkg.customReports.MetaEntity;
import com.pcas.datapkg.customReports.MetaField;
import com.pcas.datapkg.managedsecurity.RoleFieldPrivilege;
import com.pcas.datapkg.users.AppRole;
import com.pcas.datapkg.web.action.managedsecurity.RoleFieldPrivilegeAction;
import com.pcas.datapkg.web.action.users.AppRoleListQuery;

@Scope( ScopeType.CONVERSATION )
@Name( "metaEntityAction" )
public class MetaEntityAction extends MetaEntityActionBase implements java.io.Serializable {

	@In( create = true )
	AppRoleListQuery appRoleList;

	@In( create = true )
	RoleFieldPrivilegeAction roleFieldPrivilegeAction;

	@Override
	@Begin( join = true )
	public void setMetaEntityId( Long id ) {
		// TODO Auto-generated method stub
		super.setMetaEntityId( id );
	}

	@Override
	void initListMetaFields() {
		prePopulateListMetaFields();
		 if ( listMetaFields.isEmpty() )
		listMetaFields.addAll( getInstance().getMetaFields() );
	}

	@Override
	void initListEntityFieldPrivileges() {
		prePopulateListEntityFieldPrivileges();

		// if (listEntityFieldPrivileges.isEmpty())
		listEntityFieldPrivileges.addAll( getInstance().getEntityFieldPrivileges() );

	}

	@Override
	public void prePopulateListMetaFields() {

		listMetaFields.clear();

		List<AppRole> roles = appRoleList.getResultList();

		if ( instance == null ) {
			if ( getId() == null )
				return;
			instance = loadFromId( (Long) getId() );
		}

		List<MetaField> flds = instance.getListMetaFields();

		for ( MetaField metaField : flds ) {

			for ( AppRole appRole : roles ) {
				if ( !( containsRole( metaField, appRole ) ) ) {
					RoleFieldPrivilege roleFieldPrivilege = new RoleFieldPrivilege();
					roleFieldPrivilege.setAppRole( appRole );
					// roleFieldPrivilege.set
					metaField.addRoleFieldPrivilege( roleFieldPrivilege );
				}
			}

			listMetaFields.add( metaField );
		}

	}

	@Override
	public void prePopulateListEntityFieldPrivileges() {
		// if(isPostBack())
		// return;

		listEntityFieldPrivileges.clear();

		List<AppRole> roles = appRoleList.getResultList();

		if ( instance == null )
			instance = createInstance();

		List<EntityFieldPrivilege> flds = instance.getListEntityFieldPrivileges();

		// for ( EntityFieldPrivilege entityPriv : flds ) {

		for ( AppRole appRole : roles ) {
			if ( !containsRole( instance, appRole ) ) {
				EntityFieldPrivilege entityFieldPrivilege = new EntityFieldPrivilege();
				entityFieldPrivilege.setMetaEntity( getInstance() );
				entityFieldPrivilege.setAppRole( appRole );
				listEntityFieldPrivileges.add( entityFieldPrivilege );
			}
		}

		// }

	}

	private boolean containsRole( MetaField metaField, AppRole appRole ) {
		Set<RoleFieldPrivilege> roleFields = metaField.getRoleFieldPrivileges();
		for ( RoleFieldPrivilege roleFieldPrivilege : roleFields ) {
			if ( roleFieldPrivilege.getAppRole().getId().equals( appRole.getId() ) )
				return true;
		}
		return false;
	}

	private boolean containsRole( MetaEntity metaEntity, AppRole appRole ) {
		Set<EntityFieldPrivilege> roleFields = metaEntity.getEntityFieldPrivileges();

		for ( EntityFieldPrivilege roleFieldPrivilege : roleFields ) {
			if ( roleFieldPrivilege.getAppRole().getId().equals( appRole.getId() ) )
				return true;
		}
		return false;
	}

	@Override
	public String save() {

		List<MetaField> flds = listMetaFields;

		for ( MetaField metaField : flds ) {

			List<RoleFieldPrivilege> roleFlds = metaField.getListRoleFieldPrivileges();

			for ( RoleFieldPrivilege roleFieldPrivilege : roleFlds ) {
				// if (!(containsRole(metaField, appRole))) {

				// }
				
				 roleFieldPrivilegeAction.setInstance(roleFieldPrivilege);
				 roleFieldPrivilegeAction.save();

			}

		}

		return super.save();
	}
}
