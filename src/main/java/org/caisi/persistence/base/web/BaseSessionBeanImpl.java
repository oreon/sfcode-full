package org.caisi.persistence.base.web;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.WordUtils;
import org.apache.log4j.Logger;
import org.caisi.persistence.base.BusinessEntity;
import org.caisi.persistence.base.GenericDao;
import org.caisi.persistence.base.Range;
import org.caisi.persistence.base.exceptions.BusinessException;
import org.caisi.persistence.viewhelper.ViewHelper;
import org.hibernate.Criteria;
import org.oscar.servlets.BaseServlet;
import org.oscar.servlets.ReflectionBasedPopulator;

public abstract class BaseSessionBeanImpl<T> {

	private int defaultPageSize = 20;
	
	private static final Logger logger = Logger.getLogger(BaseSessionBean.class);

	/**
	 * Url when the save succeds for this entity
	 * 
	 * @return
	 */
	public abstract String getSaveSuccessUrl();

	/**
	 * Url for editing/creating new record
	 * 
	 * @return
	 */
	public abstract String getEditUrl();

	/**
	 * @return
	 */
	public abstract String getListUrl();

	public abstract String getViewUrl();

	public void save(HttpServletRequest request, HttpServletResponse response) {

		request.getSession().setAttribute(BaseServlet.REFERER,
				"/" + getEditUrl());
		T t = (T) ReflectionBasedPopulator.createBeanFromMap(request
				.getParameterMap(), getBaseDao().getPersistentClass());
		// String savedOrUpdated = T.getPKValue() == null
		// ?" saved ":" updated";
		getBaseDao().save(t);
		ViewHelper.createMessage(request, t.getClass().getSimpleName() + ":"
				+ ((BusinessEntity) t).getDisplayName()
				+ " was successfully saved");
		forwardDispatcher(getSaveSuccessUrl(), request, response);
		
		/*
		try {
			response.sendRedirect(getSaveSuccessUrl());
		} catch (IOException e) {
			throw new BusinessException("error redirecting to "
					+ getSaveSuccessUrl(), e);
		} // , request, response); */
	}

	public void load(HttpServletRequest request, HttpServletResponse response) {
		String strId = request.getParameter("entityId");

		if (strId != null) {

			try {
				Integer id = Integer.parseInt(strId);
				T t = getBaseDao().load(id);
				request.setAttribute( WordUtils.uncapitalize(getPersistentClass().getSimpleName()) , t );
				request.getSession().setAttribute(BaseServlet.REFERER,
						"/" + getListUrl());
				forwardDispatcher(getEditUrl(), request, response);
			} catch (Exception e) {
				throw new BusinessException("error forwarding to "
						+ getListUrl());
			}
		}
	}

	public List<T> getRecords(HttpServletRequest request,
			HttpServletResponse response) {
		try {
			return loadRecords(request, response);
		} catch (BusinessException be) {
			ViewHelper.createMessage(request, be.getMessage(), true);
		}
		List lstResult = new ArrayList();
		return getBaseDao().loadAll();
	}

	/**
	 * This method is to be overridden by classes that want to perform range
	 * based searches
	 * 
	 * @return
	 */
	protected List<Range> getRangeList(HttpServletRequest request) {
		return new ArrayList<Range>();
	}

	protected List<T> loadRecords(HttpServletRequest request,
			HttpServletResponse response) {

		T t = (T) ReflectionBasedPopulator.createBeanFromMap(request
				.getParameterMap(), getBaseDao().getPersistentClass());

		int currentPage = getIntValue(request, "currentPage");
		int pageSize = getIntValue(request, "recordsPerPage");
		if (pageSize == 0)
			pageSize = getDefaultPageSize();

		return getBaseDao().searchByExample(t, getRangeList(request),
				getRecordCountStart(currentPage, pageSize), pageSize);

	}

	public Integer getSearchByExampleCount(HttpServletRequest request,
			HttpServletResponse response) {
		T t = (T) ReflectionBasedPopulator.createBeanFromMap(request
				.getParameterMap(), getBaseDao().getPersistentClass());
		return getBaseDao().getSearchByExampleCount(t, getRangeList(request));
	}

	protected int getDefaultPageSize() {
		return defaultPageSize;
	}

	private int getIntValue(HttpServletRequest request, String param) {
		String currentPageStr = request.getParameter(param);
		if (currentPageStr != null){
			try {
				return Integer.parseInt(currentPageStr);
			} catch (NumberFormatException nfe) {
				logger.warn("nfe while parsing value " + param);
				//return 0;
			}
		}
		return 0;
	}

	private static int getRecordCountStart(int currentPage, int pageSize) {
		return (currentPage - 1) * pageSize + 1;
	}

	private void forwardDispatcher(String pageAddress,
			HttpServletRequest request, HttpServletResponse response) {
		try {
			request.getRequestDispatcher("/" + pageAddress).forward(request,
					response);
		} catch (Exception e) {
			throw new BusinessException("Error forwarding to " + pageAddress, e);
		}
	}

	/**
	 * Get records with pk as key and displayname as value
	 * 
	 * @return fs
	 */
	public Map<String, String> getAsMap() {
		List<T> records = getBaseDao().loadAll();
		Map<String, String> mapToReturn = new HashMap<String, String>();
		for (T t : records) {
			BusinessEntity be = (BusinessEntity) t;
			mapToReturn.put(String.valueOf(be.getEntityId()), be
					.getDisplayName());
		}

		return mapToReturn;
	}

	public void setDefaultPageSize(int defaultPageSize) {
		this.defaultPageSize = defaultPageSize;
	}

	public abstract GenericDao<T> getBaseDao();

	// delegate methods
	public Criteria createExampleCriteria(T exampleInstance) {
		return getBaseDao().createExampleCriteria(exampleInstance);
	}

	public T save(T t) {
		GenericDao<T> genDao = getBaseDao();
		return genDao.save(t);
	}

	public void delete(T entity) {
		getBaseDao().delete(entity);
	}

	public long getCount() {
		return getBaseDao().getCount();
	}

	public long getCount(Date fromDate, Date toDate) {
		return getBaseDao().getCount(fromDate, toDate);
	}

	public Class getPersistentClass() {
		return getBaseDao().getPersistentClass();
	}

	public Integer getSearchByExampleCount(T exampleInstance,
			List<Range> rangeObjects) {
		return getBaseDao().getSearchByExampleCount(exampleInstance,
				rangeObjects);
	}

	public T load(Integer id) {
		return getBaseDao().load(id);
	}

	public List<T> loadAll() {
		return getBaseDao().loadAll();
	}

	public List<T> performTextSearch(String searchText) {
		return getBaseDao().performTextSearch(searchText);
	}

	public List<T> searchByExample(T exampleInstance) {
		return getBaseDao().searchByExample(exampleInstance);
	}

	public List<T> searchByExample(T exampleInstance, List<Range> rangeObjects) {
		return getBaseDao().searchByExample(exampleInstance, rangeObjects);
	}

	public List<T> searchByExample(T exampleInstance, List<Range> rangeObjects,
			int start, int pageSize) {
		return getBaseDao().searchByExample(exampleInstance, rangeObjects,
				start, pageSize);
	}

}
