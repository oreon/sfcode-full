package usermanagement.dao;

import usermanagement.User;
import org.witchcraft.model.support.dao.GenericDAO;
import java.util.List;

public interface UserDao extends GenericDAO<User> {

	public User findByUsername(String username);

}
