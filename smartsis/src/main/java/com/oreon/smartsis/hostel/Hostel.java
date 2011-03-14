package com.oreon.smartsis.hostel;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Date;

import javax.persistence.*;
import org.hibernate.validator.*;

import org.apache.solr.analysis.LowerCaseFilterFactory;
import org.apache.solr.analysis.SnowballPorterFilterFactory;
import org.apache.solr.analysis.StandardTokenizerFactory;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.Formula;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Filter;
import org.hibernate.annotations.Cascade;

import org.hibernate.search.annotations.AnalyzerDef;
import org.hibernate.search.annotations.Analyzer;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Index;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.Parameter;
import org.hibernate.search.annotations.TokenFilterDef;
import org.hibernate.search.annotations.TokenizerDef;
import org.hibernate.search.annotations.ContainedIn;
import org.hibernate.search.annotations.IndexedEmbedded;

import org.hibernate.annotations.Filter;

import org.hibernate.validator.Length;
import org.hibernate.validator.NotNull;

import org.jboss.seam.annotations.Name;

import org.witchcraft.base.entity.BusinessEntity;
import org.witchcraft.model.support.audit.Auditable;
import org.witchcraft.base.entity.FileAttachment;

import org.witchcraft.utils.*;

@Entity
@Table(name = "hostel")
@Filter(name = "archiveFilterDef")
@Name("hostel")
@Indexed
@Cache(usage = CacheConcurrencyStrategy.NONE)
@Analyzer(definition = "entityAnalyzer")
public class Hostel extends BusinessEntity implements java.io.Serializable {
	private static final long serialVersionUID = 1428517740L;

	@Field(index = Index.TOKENIZED)
	@Analyzer(definition = "entityAnalyzer")
	protected String name;

	@OneToMany(mappedBy = "hostel", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	//@JoinColumn(name = "hostel_ID", nullable = true)
	@OrderBy("dateCreated DESC")
	@IndexedEmbedded
	private Set<Room> rooms = new HashSet<Room>();

	public void addRooms(Room rooms) {
		rooms.setHostel(this);
		this.rooms.add(rooms);
	}

	@Transient
	public List<com.oreon.smartsis.hostel.Room> getListRooms() {
		return new ArrayList<com.oreon.smartsis.hostel.Room>(rooms);
	}

	//JSF Friendly function to get count of collections
	public int getRoomsCount() {
		return rooms.size();
	}

	protected com.oreon.smartsis.Gender gender;

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {

		return name;

	}

	public void setRooms(Set<Room> rooms) {
		this.rooms = rooms;
	}

	public Set<Room> getRooms() {
		return rooms;
	}

	public void setGender(com.oreon.smartsis.Gender gender) {
		this.gender = gender;
	}

	public com.oreon.smartsis.Gender getGender() {

		return gender;

	}

	@Transient
	public String getDisplayName() {
		try {
			return name;
		} catch (Exception e) {
			return "Exception - " + e.getMessage();
		}
	}

	//Empty setter , needed for richfaces autocomplete to work 
	public void setDisplayName(String name) {
	}

	/** This method is used by hibernate full text search - override to add additional fields
	 * @see org.witchcraft.model.support.BusinessEntity#retrieveSearchableFieldsArray()
	 */
	@Override
	public List<String> listSearchableFields() {
		List<String> listSearchableFields = new ArrayList<String>();
		listSearchableFields.addAll(super.listSearchableFields());

		listSearchableFields.add("name");

		listSearchableFields.add("rooms.name");

		return listSearchableFields;
	}

	@Field(index = Index.TOKENIZED, name = "searchData")
	@Analyzer(definition = "entityAnalyzer")
	public String getSearchData() {
		StringBuilder builder = new StringBuilder();

		builder.append(getName() + " ");

		for (BusinessEntity e : rooms) {
			builder.append(e.getDisplayName() + " ");
		}

		return builder.toString();
	}

}
