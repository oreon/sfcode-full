package com.nas.recovery.websvc.users;

import javax.jws.WebService;
import org.wc.trackrite.users.Role;
import java.util.List;

@WebService
public interface RoleWebService {

	public Role loadById(Long id);

	public List<Role> findByExample(Role exampleRole);

	public void save(Role role);

}
