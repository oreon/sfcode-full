package org.cerebrum.domain.facility;

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
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Filter;
import org.hibernate.search.annotations.AnalyzerDef;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Index;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.Parameter;
import org.hibernate.search.annotations.TokenFilterDef;
import org.hibernate.search.annotations.TokenizerDef;
import org.hibernate.search.annotations.ContainedIn;
import org.hibernate.search.annotations.IndexedEmbedded;

import org.hibernate.validator.Length;
import org.hibernate.validator.NotNull;
import org.jboss.seam.annotations.Name;
import org.witchcraft.base.entity.*;
import org.hibernate.annotations.Filter;

@Entity
@Table(name = "ward")
@Name("ward")
@Filter(name = "archiveFilterDef")
@Indexed
@AnalyzerDef(name = "customanalyzer", tokenizer = @TokenizerDef(factory = StandardTokenizerFactory.class), filters = {
		@TokenFilterDef(factory = LowerCaseFilterFactory.class),
		@TokenFilterDef(factory = SnowballPorterFilterFactory.class, params = {@Parameter(name = "language", value = "English")})})
public class Ward extends BusinessEntity {

	//beds->ward ->Ward->Bed->Bed

	@OneToMany(mappedBy = "ward", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "Ward_ID", nullable = true)
	@IndexedEmbedded
	private Set<Bed> beds = new HashSet<Bed>();

	@NotNull
	@Length(min = 2, max = 50)
	@Field(index = Index.TOKENIZED)
	protected String name;

	protected org.cerebrum.domain.demographics.Gender allowedGender;

	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	@JoinColumn(name = "floor_id", nullable = false, updatable = true)
	@ContainedIn
	protected Floor floor;

	protected Double price;

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

	public void setAllowedGender(
			org.cerebrum.domain.demographics.Gender allowedGender) {
		this.allowedGender = allowedGender;
	}

	public org.cerebrum.domain.demographics.Gender getAllowedGender() {
		return allowedGender;
	}

	public void setFloor(Floor floor) {
		this.floor = floor;
	}

	public Floor getFloor() {
		return floor;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Double getPrice() {
		return price;
	}

	@Transient
	public String getDisplayName() {
		return name;
	}

	/** This method is used by hibernate full text search - override to add additional fields
	 * @see org.witchcraft.model.support.BusinessEntity#retrieveSearchableFieldsArray()
	 */
	@Override
	public List<String> listSearchableFields() {
		List<String> listSearchableFields = new ArrayList<String>();
		listSearchableFields.addAll(super.listSearchableFields());

		listSearchableFields.add("name");

		listSearchableFields.add("beds.number");

		return listSearchableFields;
	}

}
