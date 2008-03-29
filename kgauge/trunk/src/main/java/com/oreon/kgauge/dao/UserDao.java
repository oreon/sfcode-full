package com.oreon.kgauge.dao;

import com.oreon.kgauge.domain.User;
import org.witchcraft.model.support.dao.GenericDAO;
import java.util.List;

import javax.jws.WebParam;
import javax.jws.WebService;

@WebService
public interface UserDao extends GenericDAO<User> {

	public User findByUsername(String username);

}
