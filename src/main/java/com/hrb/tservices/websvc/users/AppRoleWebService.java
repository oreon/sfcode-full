package com.hrb.tservices.websvc.users;

import javax.jws.WebService;
import com.hrb.tservices.domain.users.AppRole;
import java.util.List;

@WebService
public interface AppRoleWebService {

	public AppRole loadById(Long id);

	public List<AppRole> findByExample(AppRole exampleAppRole);

	public void save(AppRole appRole);

}
