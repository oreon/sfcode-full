package org.caisi.sessionbeans;

import org.caisi.dao.ProviderDao;
import org.caisi.persistence.base.GenericDao;
import org.caisi.persistence.base.web.BaseSessionBeanImpl;
import org.caisi.persistence.model.Provider;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author jsingh
 * 
 * @param <T>
 */
@Transactional
public class ProviderSessionBeanImpl extends BaseSessionBeanImpl<Provider> implements
		ProviderSessionBean {
	
	public static final String DEMOGRAPHIC_COLLECTION = "demographics";
	String listUrl = "listDemographic.jsp";
	String editUrl = "index.jsp";
	String viewUrl = "viewDemographic.jsp";
	
	ProviderDao providerDao;
	
	public ProviderDao getProviderDao() {
		return providerDao;
	}
	public void setProviderDao(ProviderDao providerDao) {
		this.providerDao = providerDao;
	}
	@Override
	public String getEditUrl() {
		// TODO Auto-generated method stub
		return editUrl;
	}
	@Override
	public String getListUrl() {
		// TODO Auto-generated method stub
		return listUrl;
	}
	@Override
	public String getSaveSuccessUrl() {
		// TODO Auto-generated method stub
		return listUrl;
	}
	@Override
	public String getViewUrl() {
		// TODO Auto-generated method stub
		return viewUrl;
	}
	@Override
	public GenericDao<Provider> getBaseDao() {
		return getProviderDao();
	}

	

	
}
