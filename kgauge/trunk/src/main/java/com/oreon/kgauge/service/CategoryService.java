package com.oreon.kgauge.service;

import com.oreon.kgauge.domain.Category;
import com.oreon.kgauge.dao.CategoryDao;
import org.witchcraft.model.support.service.BaseService;

import javax.jws.WebParam;
import javax.jws.WebService;

/** The Service interface for entity - Category
 * @author - Witchcraft Generated {Do not Modify } 
 * 
 */
@WebService
public interface CategoryService extends CategoryDao, BaseService<Category> {

}
