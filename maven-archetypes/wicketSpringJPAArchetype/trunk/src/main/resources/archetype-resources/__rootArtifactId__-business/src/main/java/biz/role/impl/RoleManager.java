package ${package}.biz.role.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import ${package}.biz.role.IRoleManager;
import ${package}.dao.role.IRoleDAO;
import ${package}.domain.role.Role;

/**
 * @author Kamalpreet Singh
 *
 */
@Service("roleManager")
@Transactional(readOnly = true)
public class RoleManager implements IRoleManager {

	@Resource(name = "roleDAO")
	private IRoleDAO roleDAO;
	
	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public Role createRole(Role role) {
		return roleDAO.create(role);
	}
	
	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public Role updateRole(Role role) {
		return roleDAO.update(role);
	}
	
	@Override
	public Role findRoleById(Long roleId) {
		return roleDAO.findById(roleId);
	}
}
