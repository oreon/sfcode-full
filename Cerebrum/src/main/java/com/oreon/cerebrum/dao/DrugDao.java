package com.oreon.cerebrum.dao;

import com.oreon.cerebrum.drugs.Drug;
import org.witchcraft.model.support.dao.GenericDAO;
import java.util.List;

import javax.jws.WebParam;
import javax.jws.WebService;

@WebService
public interface DrugDao extends GenericDAO<Drug> {

	public Drug findByName(String name);

}
