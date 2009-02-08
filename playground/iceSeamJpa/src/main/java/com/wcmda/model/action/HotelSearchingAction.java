//$Id: HotelSearchingAction.java 9636 2008-11-25 04:51:13Z dan.j.allen $
package com.wcmda.model.action;

import java.io.Serializable;

import java.util.List;

import javax.faces.event.ValueChangeEvent;
import javax.persistence.EntityManager;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Factory;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;
import org.jboss.seam.annotations.datamodel.DataModel;
import org.jboss.seam.framework.EntityQuery;
import org.jboss.seam.servlet.SeamListener;

import com.wcmda.model.Hotel;

@Name("hotelSearch")
@Scope(ScopeType.SESSION)
public class HotelSearchingAction extends EntityQuery<Hotel> implements
		Serializable {
	// com.icesoft.faces.util.event.servlet.ContextEventRepeater
	// SeamListener
	@In
	private EntityManager entityManager;

	private String searchString;
	private int pageSize = 10;
	private int page;

	@DataModel
	private List<Hotel> hotels;

	public void find() {
		page = 0;
		queryHotels();
	}

	public void nextPage() {
		page++;
		queryHotels();
	}

	private void queryHotels() {
		hotels = entityManager
				.createQuery(
						"select h from Hotel h where lower(h.name) like #{pattern} or lower(h.city) like #{pattern} or lower(h.zip) like #{pattern} or lower(h.address) like #{pattern}")
				.setMaxResults(pageSize).setFirstResult(page * pageSize)
				.getResultList();
	}

	@Factory(value = "pattern", scope = ScopeType.EVENT)
	public String getSearchPattern() {
		return searchString == null ? "%" : '%' + searchString.toLowerCase()
				.replace('*', '%') + '%';
	}

	public boolean isNextPageAvailable() {
		return hotels != null && hotels.size() == pageSize;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public String getSearchString() {
		return searchString;
	}

	public void setSearchString(String searchString) {
		this.searchString = searchString;
	}

	public void handleSearchStringChange(ValueChangeEvent e) {
		page = 0;
		setSearchString((String) e.getNewValue());
		queryHotels();
	}
}
