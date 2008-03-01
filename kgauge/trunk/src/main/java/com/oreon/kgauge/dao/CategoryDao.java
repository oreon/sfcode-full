package com.oreon.kgauge.dao;

import com.oreon.kgauge.domain.Category;
import org.witchcraft.model.support.dao.GenericDAO;
import java.util.List;

import javax.jws.WebParam;
import javax.jws.WebService;

@WebService
public interface CategoryDao extends GenericDAO<Category> {

	/**
	 * For tree view , this method returns top level
	 * elements (whose parent is null )
	 */
	public List<Category> findTopLevelElements();

}
