package com.oreon.cerebrum.codes;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.ManyToMany;
import javax.persistence.MappedSuperclass;
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
public class SimpleCodeBase extends BaseEntity {
	private static final long serialVersionUID = 2102937529L;

	@NotNull
	@Length(min = 1, max = 250)
	@Column(unique = false)
	@Field(index = Index.YES)
	@Analyzer(definition = "entityAnalyzer")
	protected String name

	;

	@ManyToMany(mappedBy = "simpleCodes")
	private Set<com.oreon.cerebrum.encounter.Encounter> encounters = new HashSet<com.oreon.cerebrum.encounter.Encounter>();

	public void addEncounter(com.oreon.cerebrum.encounter.Encounter encounter) {

		this.encounters.add(encounter);
	}

	@Transient
	public List<com.oreon.cerebrum.encounter.Encounter> getListEncounters() {
		return new ArrayList<com.oreon.cerebrum.encounter.Encounter>(encounters);
	}

	//JSF Friendly function to get count of collections
	public int getEncountersCount() {
		return encounters.size();
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {

		return name;

	}

	public void setEncounters(
			Set<com.oreon.cerebrum.encounter.Encounter> encounters) {
		this.encounters = encounters;
	}

	public Set<com.oreon.cerebrum.encounter.Encounter> getEncounters() {
		return encounters;
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

	/*
	
	 */

}