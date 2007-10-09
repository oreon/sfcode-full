package bizobjects.dao;

import bizobjects.RegisteredUser;
import org.witchcraft.model.support.dao.GenericDAO;
import java.util.List;

public interface RegisteredUserDao extends GenericDAO<RegisteredUser> {

	public List<RegisteredUser> findByLastName(String lastName);

	public RegisteredUser findByUsername(String username);

	public RegisteredUser findByEmail(String email);

}
