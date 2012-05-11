package com.wc.shopper.domain;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Filter;
import org.hibernate.search.annotations.Analyzer;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Index;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.IndexedEmbedded;
import org.hibernate.validator.constraints.Length;

@Entity
@Table(name = "department")
//@Filter(name = "archiveFilterDef")
//@Name("department")
@Indexed
@Cache(usage = CacheConcurrencyStrategy.NONE)
@Analyzer(definition = "entityAnalyzer")
@XmlRootElement
public class Department extends BaseEntity implements java.io.Serializable {
	private static final long serialVersionUID = -1899938946L;

	@OneToMany(mappedBy = "department", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	//@JoinColumn(name = "department_ID", nullable = true)
	//@OrderBy("dateCreated DESC")
	@IndexedEmbedded
	private Set<Employee> employees = new HashSet<Employee>();

	public void addEmployee(Employee employee) {
		employee.setDepartment(this);
		this.employees.add(employee);
	}

	@Transient
	public List<Employee> getListEmployees() {
		return new ArrayList<Employee>(employees);
	}

	//JSF Friendly function to get count of collections
	public int getEmployeesCount() {
		return employees.size();
	}

	@NotNull
	@Length(min = 1, max = 250)
	@Column(unique = true)
	@Field(index = Index.YES)
	@Analyzer(definition = "entityAnalyzer")
	protected String name

	;

	public void setEmployees(Set<Employee> employees) {
		this.employees = employees;
	}

	public Set<Employee> getEmployees() {
		return employees;
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

		return listSearchableFields;
	}

	@Field(index = Index.YES, name = "searchData")
	@Analyzer(definition = "entityAnalyzer")
	public String getSearchData() {
		StringBuilder builder = new StringBuilder();

		builder.append(getName() + " ");

		for (BaseEntity e : employees) {
			builder.append(e.getDisplayName() + " ");
		}

		return builder.toString();
	}

}
