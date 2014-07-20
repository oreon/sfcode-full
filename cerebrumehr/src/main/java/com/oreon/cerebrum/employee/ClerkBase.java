package com.oreon.cerebrum.employee;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.MappedSuperclass;

import org.hibernate.search.annotations.Analyzer;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Index;

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
public class ClerkBase extends com.oreon.cerebrum.employee.Employee {
	private static final long serialVersionUID = -2061693625L;

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

		return listSearchableFields;
	}

	@Field(index = Index.YES, name = "searchData")
	@Analyzer(definition = "entityAnalyzer")
	public String getSearchData() {
		StringBuilder builder = new StringBuilder();

		return builder.toString();
	}

	/*
	
	 */

}
