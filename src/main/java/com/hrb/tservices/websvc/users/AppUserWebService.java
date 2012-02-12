package com.hrb.tservices.websvc.users;

import javax.jws.WebService;
import com.hrb.tservices.domain.users.AppUser;
import java.util.List;

@WebService
public interface AppUserWebService {

	public AppUser loadById(Long id);

	public List<AppUser> findByExample(AppUser exampleAppUser);

	public void save(AppUser appUser);

}
