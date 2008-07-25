package com.cc.civlit.domain.dao;

import com.cc.civlit.domain.courtdivisions.Jurisdiction;
import org.witchcraft.model.support.dao.GenericDAO;
import java.util.List;

import javax.jws.WebParam;
import javax.jws.WebService;

@WebService
public interface JurisdictionDao extends GenericDAO<Jurisdiction> {

	/**
	 * For tree view , this method returns top level
	 * elements (whose parent is null )
	 */
	public List<Category> findTopLevelElements();

}
