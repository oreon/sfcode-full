package com.cc.civlit.dao;

import com.cc.civlit.domain.Firm;
import org.witchcraft.model.support.dao.GenericDAO;
import java.util.List;

import javax.jws.WebParam;
import javax.jws.WebService;

@WebService
public interface FirmDao extends GenericDAO<Firm> {

	public Firm findByEmail(String email);

}
