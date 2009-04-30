package org.caisi.sessionbeans;

import org.caisi.dao.DemographicDao;
import org.caisi.persistence.base.GenericDao;
import org.caisi.persistence.base.web.BaseSessionBeanImpl;
import org.caisi.persistence.model.Demographic;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author jsingh
 * 
 * @param <T>
 */
@Transactional
public class DemographicSessionBeanImpl extends BaseSessionBeanImpl<Demographic> implements
		DemographicSessionBean {

	public static final String DEMOGRAPHIC_COLLECTION = "demographics";
	String listUrl = "listDemographic.jsp";
	String editUrl = "index.jsp";
	String viewUrl = "viewDemographic.jsp";
	
	private DemographicDao demographicDao;
	
	public DemographicDao getDemographicDao() {
		return demographicDao;
	}
	public void setDemographicDao(DemographicDao demographicDao) {
		this.demographicDao = demographicDao;
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
	public GenericDao<Demographic> getBaseDao() {
		return getDemographicDao(); 
	}
	
	
	/*
	@Override
	protected List<Range> getRangeList() {
		Range<java.util.Date> range = 
			RangeFactory.createDateRange(request, "dateJoined", "fromDateJoined", "toDateJoined");
		List<Range> ranges = super.getRangeList(request);
		ranges.add(range);
		return ranges;
	}*/
	
	
	
}
