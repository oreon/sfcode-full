package com.oreon.cerebrum.dao;

import com.oreon.cerebrum.drugs.Category;
import org.witchcraft.model.support.dao.GenericDAO;
import java.util.List;

import javax.jws.WebParam;
import javax.jws.WebService;

@WebService
public interface CategoryDao extends GenericDAO<Category> {

	public Category findByCode(String code);

	/**
	 * For tree view , this method returns top level
	 * elements (whose parent is null )
	 */
	public List<Category> findTopLevelElements();

}
