package com.cc.civlit.domain.service;

import com.cc.civlit.domain.auth.User;
import com.cc.civlit.domain.dao.UserDao;
import org.witchcraft.model.support.service.BaseService;

import javax.jws.WebParam;
import javax.jws.WebService;

/** The Service interface for entity - User
 * @author - Witchcraft Generated {Do not Modify } 
 * 
 */
@WebService
public interface UserService extends UserDao, BaseService<User> {

}
