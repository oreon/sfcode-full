package com.oreon.cerebrum.ddx;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import org.hibernate.search.annotations.Analyzer;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Index;
import org.hibernate.validator.constraints.Length;
import org.witchcraft.base.entity.BaseEntity;

//Impl 

/**
 * This file is generated by Witchcraftmda.
 * DO NOT MODIFY any changes will be overwritten with the next run of the code generator.
 *
 */

/**
 * 
 *
 */

@MappedSuperclass
public class FindingBase extends BaseEntity {
	private static final long serialVersionUID = -1511370859L;

	@NotNull
	@Length(min = 1, max = 250)
	@Column(unique = true)
	@Field(index = Index.YES)
	@Analyzer(definition = "entityAnalyzer")
	protected String name

	;

	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	//@JoinColumn(name = "finding_ID", nullable = true)
	@OrderBy("id DESC")
	private Set<DifferentialDx> differentialDxs = new HashSet<DifferentialDx>();

	public void addDifferentialDx(DifferentialDx differentialDx) {

		differentialDx.setFinding((Finding) this);

		this.differentialDxs.add(differentialDx);
	}

	@Transient
	public List<com.oreon.cerebrum.ddx.DifferentialDx> getListDifferentialDxs() {
		return new ArrayList<com.oreon.cerebrum.ddx.DifferentialDx>(
				differentialDxs);
	}

	//JSF Friendly function to get count of collections
	public int getDifferentialDxsCount() {
		return differentialDxs.size();
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {

		return name;

	}

	public void setDifferentialDxs(Set<DifferentialDx> differentialDxs) {
		this.differentialDxs = differentialDxs;
	}

	public Set<DifferentialDx> getDifferentialDxs() {
		return differentialDxs;
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

		listSearchableFields.add("differentialDxs.name");

		return listSearchableFields;
	}

	@Field(index = Index.YES, name = "searchData")
	@Analyzer(definition = "entityAnalyzer")
	public String getSearchData() {
		StringBuilder builder = new StringBuilder();

		builder.append(getName() + " ");

		for (BaseEntity e : differentialDxs) {
			builder.append(e.getDisplayName() + " ");
		}

		return builder.toString();
	}

	/*
	
	 */

}
