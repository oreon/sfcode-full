package com.oreon.cerebrum.dao;

import com.oreon.cerebrum.drugs.DrugCode;
import org.witchcraft.model.support.dao.GenericDAO;
import java.util.List;

import javax.jws.WebParam;
import javax.jws.WebService;

@WebService
public interface DrugCodeDao extends GenericDAO<DrugCode> {

	public DrugCode findByCode(String code);

}
