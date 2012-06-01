package com.pcas.datapkg.domain.inventory;

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

import java.math.BigDecimal;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import org.jboss.seam.annotations.Name;

import org.witchcraft.base.entity.BaseEntity;
import org.witchcraft.model.support.audit.Auditable;
import org.witchcraft.base.entity.FileAttachment;

import org.witchcraft.utils.*;

import com.pcas.datapkg.ProjectUtils;

@Entity
@Table(name = "customer")
@Filter(name = "archiveFilterDef")
@Name("customer")
@Indexed
@Cache(usage = CacheConcurrencyStrategy.NONE)
@Analyzer(definition = "entityAnalyzer")
@XmlRootElement
public class Customer extends BaseEntity implements java.io.Serializable {
	private static final long serialVersionUID = -1474397620L;

	@OneToMany(mappedBy = "customer", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	//@JoinColumn(name = "customer_ID", nullable = true)
	@OrderBy("dateCreated DESC")
	@IndexedEmbedded
	private Set<Machine> machines = new HashSet<Machine>();

	public void addMachine(Machine machine) {
		machine.setCustomer(this);
		this.machines.add(machine);
	}

	@Transient
	public List<com.pcas.datapkg.domain.inventory.Machine> getListMachines() {
		return new ArrayList<com.pcas.datapkg.domain.inventory.Machine>(
				machines);
	}

	//JSF Friendly function to get count of collections
	public int getMachinesCount() {
		return machines.size();
	}

	@NotNull
	@Length(min = 1, max = 250)
	@Column(unique = true)
	@Field(index = Index.TOKENIZED)
	@Analyzer(definition = "entityAnalyzer")
	protected String name

	;

	@Column(unique = false)
	@Field(index = Index.TOKENIZED)
	@Analyzer(definition = "entityAnalyzer")
	protected String country

	;

	@Column(unique = false)
	@Field(index = Index.TOKENIZED)
	@Analyzer(definition = "entityAnalyzer")
	protected String telephone

	;

	@OneToMany(mappedBy = "customer", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	//@JoinColumn(name = "customer_ID", nullable = true)
	@OrderBy("dateCreated DESC")
	@IndexedEmbedded
	private Set<com.pcas.datapkg.domain.Employee> employees = new HashSet<com.pcas.datapkg.domain.Employee>();

	public void addEmployee(com.pcas.datapkg.domain.Employee employee) {
		employee.setCustomer(this);
		this.employees.add(employee);
	}

	@Transient
	public List<com.pcas.datapkg.domain.Employee> getListEmployees() {
		return new ArrayList<com.pcas.datapkg.domain.Employee>(employees);
	}

	//JSF Friendly function to get count of collections
	public int getEmployeesCount() {
		return employees.size();
	}

	public void setMachines(Set<Machine> machines) {
		this.machines = machines;
	}

	public Set<Machine> getMachines() {
		return machines;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {

		return name;

	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getCountry() {

		return country;

	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getTelephone() {

		return telephone;

	}

	public void setEmployees(Set<com.pcas.datapkg.domain.Employee> employees) {
		this.employees = employees;
	}

	public Set<com.pcas.datapkg.domain.Employee> getEmployees() {
		return employees;
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

		listSearchableFields.add("country");

		listSearchableFields.add("telephone");

		listSearchableFields.add("employees.employeeNumber");

		return listSearchableFields;
	}

	@Field(index = Index.TOKENIZED, name = "searchData")
	@Analyzer(definition = "entityAnalyzer")
	public String getSearchData() {
		StringBuilder builder = new StringBuilder();

		builder.append(getName() + " ");

		builder.append(getCountry() + " ");

		builder.append(getTelephone() + " ");

		for (BaseEntity e : machines) {
			builder.append(e.getDisplayName() + " ");
		}

		for (BaseEntity e : employees) {
			builder.append(e.getDisplayName() + " ");
		}

		return builder.toString();
	}

}
