package com.oreon.callosum.drugs;

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

import org.hibernate.validator.Length;
import org.hibernate.validator.NotNull;
import org.jboss.seam.annotations.Name;
import org.witchcraft.base.entity.BusinessEntity;
import org.witchcraft.model.support.audit.Auditable;
import org.witchcraft.base.entity.FileAttachment;
import org.hibernate.annotations.Filter;

import org.witchcraft.utils.*;

@Entity
@Table(name = "drug")
@Filter(name = "archiveFilterDef")
@Name("drug")
@Indexed
@AnalyzerDef(name = "customanalyzer", tokenizer = @TokenizerDef(factory = StandardTokenizerFactory.class), filters = {
		@TokenFilterDef(factory = LowerCaseFilterFactory.class),
		@TokenFilterDef(factory = SnowballPorterFilterFactory.class, params = {@Parameter(name = "language", value = "English")})})
public class Drug extends BusinessEntity implements java.io.Serializable {
	private static final long serialVersionUID = -16274297L;

	@NotNull
	@Length(min = 2, max = 250)
	@Column(unique = true)
	@Field(index = Index.TOKENIZED)
	@Analyzer(definition = "customanalyzer")
	protected String name;

	@Lob
	@Field(index = Index.TOKENIZED)
	@Analyzer(definition = "customanalyzer")
	protected String absorption;

	@Lob
	@Field(index = Index.TOKENIZED)
	@Analyzer(definition = "customanalyzer")
	protected String biotransformation;

	@Field(index = Index.TOKENIZED)
	@Analyzer(definition = "customanalyzer")
	protected String atcCodes;

	@Lob
	@Field(index = Index.TOKENIZED)
	@Analyzer(definition = "customanalyzer")
	protected String contraIndication;

	@Lob
	@Field(index = Index.TOKENIZED)
	@Analyzer(definition = "customanalyzer")
	protected String description;

	@Field(index = Index.TOKENIZED)
	@Analyzer(definition = "customanalyzer")
	protected String dosageForm;

	@Lob
	@Field(index = Index.TOKENIZED)
	@Analyzer(definition = "customanalyzer")
	protected String foodInteractions;

	@Lob
	@Field(index = Index.TOKENIZED)
	@Analyzer(definition = "customanalyzer")
	protected String halfLife;

	protected Double halfLifeNumberOfHours;

	@Lob
	@Field(index = Index.TOKENIZED)
	@Analyzer(definition = "customanalyzer")
	protected String indication;

	@Lob
	@Field(index = Index.TOKENIZED)
	@Analyzer(definition = "customanalyzer")
	protected String mechanismOfAction;

	@Lob
	@Field(index = Index.TOKENIZED)
	@Analyzer(definition = "customanalyzer")
	protected String patientInfo;

	@Lob
	@Field(index = Index.TOKENIZED)
	@Analyzer(definition = "customanalyzer")
	protected String pharmacology;

	@OneToMany(mappedBy = "drug", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@Cascade(value = org.hibernate.annotations.CascadeType.DELETE_ORPHAN)
	@JoinColumn(name = "drug_ID", nullable = true)
	@OrderBy("dateCreated DESC")
	@IndexedEmbedded
	private Set<DrugInteraction> drugInteractions = new HashSet<DrugInteraction>();

	@ManyToMany(mappedBy = "drugs")
	private Set<DrugCategory> drugCategorys = new HashSet<DrugCategory>();

	@Lob
	@Field(index = Index.TOKENIZED)
	@Analyzer(definition = "customanalyzer")
	protected String toxicity;

	@Lob
	@Field(index = Index.TOKENIZED)
	@Analyzer(definition = "customanalyzer")
	protected String routeOfElimination;

	@Field(index = Index.TOKENIZED)
	@Analyzer(definition = "customanalyzer")
	protected String volumeOfDistribution;

	@NotNull
	@Length(min = 2, max = 250)
	@Column(unique = true)
	@Field(index = Index.TOKENIZED)
	@Analyzer(definition = "customanalyzer")
	protected String drugBankId;

	@Column(name = "categories", unique = false)
	@Transient
	@Field(index = Index.TOKENIZED)
	@Analyzer(definition = "customanalyzer")
	protected String categories;

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setAbsorption(String absorption) {
		this.absorption = absorption;
	}

	public String getAbsorption() {
		return absorption;
	}

	public void setBiotransformation(String biotransformation) {
		this.biotransformation = biotransformation;
	}

	public String getBiotransformation() {
		return biotransformation;
	}

	public void setAtcCodes(String atcCodes) {
		this.atcCodes = atcCodes;
	}

	public String getAtcCodes() {
		return atcCodes;
	}

	public void setContraIndication(String contraIndication) {
		this.contraIndication = contraIndication;
	}

	public String getContraIndication() {
		return contraIndication;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDescription() {
		return description;
	}

	public void setDosageForm(String dosageForm) {
		this.dosageForm = dosageForm;
	}

	public String getDosageForm() {
		return dosageForm;
	}

	public void setFoodInteractions(String foodInteractions) {
		this.foodInteractions = foodInteractions;
	}

	public String getFoodInteractions() {
		return foodInteractions;
	}

	public void setHalfLife(String halfLife) {
		this.halfLife = halfLife;
	}

	public String getHalfLife() {
		return halfLife;
	}

	public void setHalfLifeNumberOfHours(Double halfLifeNumberOfHours) {
		this.halfLifeNumberOfHours = halfLifeNumberOfHours;
	}

	public Double getHalfLifeNumberOfHours() {
		return halfLifeNumberOfHours;
	}

	public void setIndication(String indication) {
		this.indication = indication;
	}

	public String getIndication() {
		return indication;
	}

	public void setMechanismOfAction(String mechanismOfAction) {
		this.mechanismOfAction = mechanismOfAction;
	}

	public String getMechanismOfAction() {
		return mechanismOfAction;
	}

	public void setPatientInfo(String patientInfo) {
		this.patientInfo = patientInfo;
	}

	public String getPatientInfo() {
		return patientInfo;
	}

	public void setPharmacology(String pharmacology) {
		this.pharmacology = pharmacology;
	}

	public String getPharmacology() {
		return pharmacology;
	}

	public void setDrugInteractions(Set<DrugInteraction> drugInteractions) {
		this.drugInteractions = drugInteractions;
	}

	public Set<DrugInteraction> getDrugInteractions() {
		return drugInteractions;
	}

	public void setDrugCategorys(Set<DrugCategory> drugCategorys) {
		this.drugCategorys = drugCategorys;
	}

	public Set<DrugCategory> getDrugCategorys() {
		return drugCategorys;
	}

	public void setToxicity(String toxicity) {
		this.toxicity = toxicity;
	}

	public String getToxicity() {
		return toxicity;
	}

	public void setRouteOfElimination(String routeOfElimination) {
		this.routeOfElimination = routeOfElimination;
	}

	public String getRouteOfElimination() {
		return routeOfElimination;
	}

	public void setVolumeOfDistribution(String volumeOfDistribution) {
		this.volumeOfDistribution = volumeOfDistribution;
	}

	public String getVolumeOfDistribution() {
		return volumeOfDistribution;
	}

	public void setDrugBankId(String drugBankId) {
		this.drugBankId = drugBankId;
	}

	public String getDrugBankId() {
		return drugBankId;
	}

	public void setCategories(String categories) {
		this.categories = categories;
	}

	public String getCategories() {
		return getCollectionAsString(drugCategorys);
	}

	@Transient
	public String getDisplayName() {
		return name;
	}

	@Transient
	public String getPopupInfo() {
		return description;
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

		listSearchableFields.add("absorption");

		listSearchableFields.add("biotransformation");

		listSearchableFields.add("atcCodes");

		listSearchableFields.add("contraIndication");

		listSearchableFields.add("description");

		listSearchableFields.add("dosageForm");

		listSearchableFields.add("foodInteractions");

		listSearchableFields.add("halfLife");

		listSearchableFields.add("indication");

		listSearchableFields.add("mechanismOfAction");

		listSearchableFields.add("patientInfo");

		listSearchableFields.add("pharmacology");

		listSearchableFields.add("toxicity");

		listSearchableFields.add("routeOfElimination");

		listSearchableFields.add("volumeOfDistribution");

		listSearchableFields.add("drugBankId");

		listSearchableFields.add("categories");

		listSearchableFields.add("drugInteractions.description");

		return listSearchableFields;
	}

}
