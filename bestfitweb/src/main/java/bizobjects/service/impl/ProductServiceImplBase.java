package bizobjects.service.impl;

import bizobjects.Product;
import bizobjects.service.ProductService;
import bizobjects.dao.ProductDao;
import java.util.List;
import bizobjects.service.ProductService;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.annotation.Propagation;

import org.apache.log4j.Logger;

import usermanagement.Authority;
import usermanagement.service.AuthorityService;

import org.witchcraft.model.support.dao.GenericDAO;
import org.witchcraft.model.support.errorhandling.BusinessException;
import org.witchcraft.model.support.service.BaseServiceImpl;

@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
public class ProductServiceImplBase extends BaseServiceImpl<Product>
		implements
			ProductService {

	private static final Logger log = Logger
			.getLogger(ProductServiceImplBase.class);

	private ProductDao productDao;

	public void setProductDao(ProductDao productDao) {
		this.productDao = productDao;
	}

	@Override
	public GenericDAO<Product> getDao() {
		return productDao;
	}

	//// Delegate all crud operations to the Dao ////

	public Product save(Product product) {
		Long id = product.getId();

		productDao.save(product);

		return product;
	}

	public void delete(Product product) {
		productDao.delete(product);
	}

	public Product load(Long id) {
		return productDao.load(id);
	}

	public List<Product> loadAll() {
		return productDao.loadAll();
	}

	public List<Product> searchByExample(Product product) {
		return productDao.searchByExample(product);
	}

	/*
	public List query(String queryString, Object... params) {
		return basicDAO.query(queryString, params);
	}*/

}
