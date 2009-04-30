package org.caisi.persistence.base.web;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.caisi.persistence.base.Range;

public interface BaseSessionBean<T>  {

	/**
	 * @param request
	 * @param response
	 */
	public void save(HttpServletRequest request, HttpServletResponse response);

	/**
	 * @param request
	 * @param response
	 */
	public void load(HttpServletRequest request, HttpServletResponse response);

	/**
	 * @param request
	 * @param response
	 * @return
	 */
	public List<T> getRecords(HttpServletRequest request,
			HttpServletResponse response);
	
	
	/** Gets record count for a search by example query
	 * @param request
	 * @param exampleInstance
	 * @param rangeObjects
	 * @return
	 */
	public Integer getSearchByExampleCount(HttpServletRequest request, HttpServletResponse response) ;

	/** Get records with pk as key and displayname as value
	 * useful for - populating drop downs
	 * @return
	 */
	public Map<String, String> getAsMap();
}