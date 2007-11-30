package com.oreon.jshoppingcart.dao;

import com.oreon.jshoppingcart.domain.Category;
import org.witchcraft.model.support.dao.GenericDAO;
import java.util.List;

public interface CategoryDao extends GenericDAO<Category> {

	/**
	 * For tree view , this method returns top level
	 * elements (whose parent is null )
	 */
	public List<Category> findTopLevelElements();

}
