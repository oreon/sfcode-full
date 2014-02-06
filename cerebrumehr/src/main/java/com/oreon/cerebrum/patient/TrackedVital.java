package com.oreon.cerebrum.patient;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.search.annotations.Analyzer;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Index;
import org.hibernate.validator.constraints.Length;
import org.witchcraft.base.entity.BaseEntity;

@Entity
@Table(name = "trackedvital")
@Cache(usage = CacheConcurrencyStrategy.NONE)
@XmlRootElement
public class TrackedVital extends BaseEntity implements java.io.Serializable {
	private static final long serialVersionUID = 700076891L;

	@NotNull
	@Length(min = 1, max = 250)
	@Column(unique = true)
	@Field(index = Index.YES)
	@Analyzer(definition = "entityAnalyzer")
	protected String name

	;

	@Column(unique = false)
	protected Double minVal

	;

	@Column(unique = false)
	protected Double maxVal

	;

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {

		return name;

	}

	public void setMinVal(Double minVal) {
		this.minVal = minVal;
	}

	public Double getMinVal() {

		return minVal;

	}

	public void setMaxVal(Double maxVal) {
		this.maxVal = maxVal;
	}

	public Double getMaxVal() {

		return maxVal;

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
	 * @see org.witchcraft.model.support.BaseEntity#retrieveSearchableFieldsArray()
	 */
	@Override
	public List<String> listSearchableFields() {
		List<String> listSearchableFields = new ArrayList<String>();
		listSearchableFields.addAll(super.listSearchableFields());

		listSearchableFields.add("name");

		return listSearchableFields;
	}

	@Field(index = Index.YES, name = "searchData")
	@Analyzer(definition = "entityAnalyzer")
	public String getSearchData() {
		StringBuilder builder = new StringBuilder();

		builder.append(getName() + " ");

		return builder.toString();
	}

}
