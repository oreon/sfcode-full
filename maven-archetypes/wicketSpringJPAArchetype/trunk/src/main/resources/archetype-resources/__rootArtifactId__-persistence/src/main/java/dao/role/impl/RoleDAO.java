package ${package}.dao.role.impl;

import org.springframework.stereotype.Repository;

import ${package}.dao.impl.GenericDAO;
import ${package}.dao.role.IRoleDAO;
import ${package}.domain.role.Role;

/**
 * @author Kamalpreet Singh
 *
 */
@Repository("roleDAO")
public class RoleDAO extends GenericDAO<Role, Long> implements IRoleDAO {

}
