package ${package}.biz.role;

import ${package}.domain.role.Role;

/**
 * @author Kamalpreet Singh
 *
 */
public interface IRoleManager {

	Role createRole(Role role);
	
	Role updateRole(Role role);
	
	Role findRoleById(Long roleId);
}
