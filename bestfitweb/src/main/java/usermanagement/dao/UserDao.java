package usermanagement.dao;

import org.witchcraft.model.support.dao.GenericDAO;

import usermanagement.User;

public interface UserDao extends GenericDAO<User> {

	public User findByUsername(String username);

}
