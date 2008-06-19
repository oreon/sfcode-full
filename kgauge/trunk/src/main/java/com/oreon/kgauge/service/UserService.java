package com.oreon.kgauge.service;

import com.oreon.kgauge.domain.User;
import com.oreon.kgauge.dao.UserDao;
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
