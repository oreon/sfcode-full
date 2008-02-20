package com.oreon.kgauge.dao;

import com.oreon.kgauge.domain.Authority;
import org.witchcraft.model.support.dao.GenericDAO;
import java.util.List;

import javax.jws.WebParam;
import javax.jws.WebService;

@WebService
public interface AuthorityDao extends GenericDAO<Authority> {

	public Authority findByName(String name);

}
