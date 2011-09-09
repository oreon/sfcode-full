package com.oreon.cerebrum.facility;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Date;
import javax.ws.rs.core.Response;

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
import org.hibernate.search.annotations.Boost;
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

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import org.jboss.seam.annotations.Name;

import org.witchcraft.base.entity.BusinessEntity;
import org.witchcraft.model.support.audit.Auditable;
import org.witchcraft.base.entity.FileAttachment;

import org.witchcraft.utils.*;

import com.oreon.cerebrum.ProjectUtils;

@Entity
@Table(name = "room")
@Filter(name = "archiveFilterDef")
@Name("room")
@Indexed
@Cache(usage = CacheConcurrencyStrategy.NONE)
@Analyzer(definition = "entityAnalyzer")
@XmlRootElement
public class Room extends BusinessEntity implements java.io.Serializable {
	private static final long serialVersionUID = 2085840692L;

	@ManyToOne(optional = false, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "facility_id", nullable = false, updatable = true)
	@ContainedIn
	protected Facility facility

	;

	@OneToMany(mappedBy = "room", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	//@JoinColumn(name = "room_ID", nullable = true)
	@OrderBy("dateCreated DESC")
	@IndexedEmbedded
	private Set<Bed> beds = new HashSet<Bed>();

	public void addBed(Bed bed) {
		bed.setRoom(this);
		this.beds.add(bed);
	}

	@Transient
	public List<com.oreon.cerebrum.facility.Bed> getListBeds() {
		return new ArrayList<com.oreon.cerebrum.facility.Bed>(beds);
	}

	//JSF Friendly function to get count of collections
	public int getBedsCount() {
		return beds.size();
	}

	@NotNull
	@Length(min = 1, max = 250)
	@Column(unique = false)
	@Field(index = Index.TOKENIZED)
	@Analyzer(definition = "entityAnalyzer")
	protected String name

	;

	public void setFacility(Facility facility) {
		this.facility = facility;
	}

	public Facility getFacility() {

		return facility;

	}

	public void setBeds(Set<Bed> beds) {
		this.beds = beds;
	}

	public Set<Bed> getBeds() {
		return beds;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {

		return name;

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

		listSearchableFields.add("beds.name");

		return listSearchableFields;
	}

	@Field(index = Index.TOKENIZED, name = "searchData")
	@Analyzer(definition = "entityAnalyzer")
	public String getSearchData() {
		StringBuilder builder = new StringBuilder();

		builder.append(getName() + " ");

		if (getFacility() != null)
			builder.append("facility:" + getFacility().getDisplayName() + " ");

		for (BusinessEntity e : beds) {
			builder.append(e.getDisplayName() + " ");
		}

		return builder.toString();
	}

}
