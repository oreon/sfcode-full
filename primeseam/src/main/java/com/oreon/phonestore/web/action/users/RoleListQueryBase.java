package com.oreon.phonestore.web.action.users;

import com.oreon.phonestore.users.Role;

import org.witchcraft.seam.action.BaseAction;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;
import org.jboss.seam.framework.EntityQuery;

import org.witchcraft.seam.action.BaseQuery;

import org.witchcraft.base.entity.Range;

import org.primefaces.model.SortOrder;
import org.witchcraft.seam.action.EntityLazyDataModel;
import org.primefaces.model.LazyDataModel;
import java.util.Map;

import org.jboss.seam.annotations.Observer;

import java.math.BigDecimal;
import javax.faces.model.DataModel;

import org.jboss.seam.annotations.security.Restrict;

import org.jboss.seam.annotations.In;
import org.jboss.seam.Component;

import com.oreon.phonestore.users.Role;

/**
 * 
 * @author WitchcraftMDA Seam Cartridge - 
 *
 */
public abstract class RoleListQueryBase extends BaseQuery<Role, Long> {

	private static final String EJBQL = "select role from Role role";

	protected Role role = new Role();

	@In(create = true)
	RoleAction roleAction;

	public RoleListQueryBase() {
		super();
		setOrderColumn("id");
		setOrderDirection("desc");
	}

	public Role getRole() {
		return role;
	}

	@Override
	public Role getInstance() {
		return getRole();
	}

	private com.oreon.phonestore.users.User usersToSearch;

	public void setUsersToSearch(com.oreon.phonestore.users.User userToSearch) {
		this.usersToSearch = userToSearch;
	}

	public com.oreon.phonestore.users.User getUsersToSearch() {
		return usersToSearch;
	}

	@Override
	protected String getql() {
		return EJBQL;
	}

	@Override
	//@Restrict("#{s:hasPermission('role', 'view')}")
	public List<Role> getResultList() {
		return super.getResultList();
	}

	@Override
	public Class<Role> getEntityClass() {
		return Role.class;
	}

	@Override
	public String[] getEntityRestrictions() {
		return RESTRICTIONS;
	}

	private static final String[] RESTRICTIONS = {
			"role.id = #{roleList.role.id}",

			"role.archived = #{roleList.role.archived}",

			"lower(role.name) like concat(lower(#{roleList.role.name}),'%')",

			"#{roleList.usersToSearch} in elements(role.users)",

			"role.dateCreated <= #{roleList.dateCreatedRange.end}",
			"role.dateCreated >= #{roleList.dateCreatedRange.begin}",};

	@Observer("archivedRole")
	public void onArchive() {
		refresh();
	}

	//@Restrict("#{s:hasPermission('role', 'delete')}")
	public void archiveById(Long id) {
		roleAction.archiveById(id);
		refresh();
	}

}
