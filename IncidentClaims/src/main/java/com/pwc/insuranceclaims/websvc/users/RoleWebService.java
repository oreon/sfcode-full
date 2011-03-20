package com.pwc.insuranceclaims.websvc.users;

import javax.jws.WebService;
import com.pwc.insuranceclaims.users.Role;
import java.util.List;

@WebService
public interface RoleWebService {

	public Role loadById(Long id);

	public List<Role> findByExample(Role exampleRole);

	public void save(Role role);

}
