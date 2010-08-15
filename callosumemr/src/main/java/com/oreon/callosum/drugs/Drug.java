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
import org.witchcraft.base.entity.BusinessEntity;
import org.witchcraft.model.support.audit.Auditable;
import org.witchcraft.base.entity.FileAttachment;
import org.hibernate.annotations.Filter;

@Entity
@Table(name = "drug")
@Name("drug")
@Filter(name = "archiveFilterDef")
@Indexed
@AnalyzerDef(name = "customanalyzer", tokenizer = @TokenizerDef(factory = StandardTokenizerFactory.class), filters = {
		@TokenFilterDef(factory = LowerCaseFilterFactory.class),
		@TokenFilterDef(factory = SnowballPorterFilterFactory.class, params = {@Parameter(name = "language", value = "English")})})
public class Drug extends BusinessEntity implements java.io.Serializable {
	private static final long serialVersionUID = -16274297L;

	@Lob
	protected String absorption;

	@Lob
	protected String biotransformation;

	@Field(index = Index.TOKENIZED)
	protected String atcCodes;

	@Lob
	protected String contraIndication;

	@Lob
	protected String description;

	@Field(index = Index.TOKENIZED)
	protected String dosageForm;

	@Field(index = Index.TOKENIZED)
	protected String drugCategory;

	@Lob
	protected String foodInteractions;

	@Lob
	protected String halfLife;

	@Lob
	protected String indication;

	@Lob
	protected String interactions;

	@Lob
	protected String mechanism;

	@Field(index = Index.TOKENIZED)
	protected String name;

	@Lob
	protected String patientInfo;

	@Lob
	protected String pharmacology;

	@Lob
	protected String toxicity;

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

	public void setDrugCategory(String drugCategory) {
		this.drugCategory = drugCategory;
	}

	public String getDrugCategory() {

		return drugCategory;
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

	public void setIndication(String indication) {
		this.indication = indication;
	}

	public String getIndication() {

		return indication;
	}

	public void setInteractions(String interactions) {
		this.interactions = interactions;
	}

	public String getInteractions() {

		return interactions;
	}

	public void setMechanism(String mechanism) {
		this.mechanism = mechanism;
	}

	public String getMechanism() {

		return mechanism;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {

		return name;
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

	public void setToxicity(String toxicity) {
		this.toxicity = toxicity;
	}

	public String getToxicity() {

		return toxicity;
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

		listSearchableFields.add("atcCodes");

		listSearchableFields.add("dosageForm");

		listSearchableFields.add("drugCategory");

		listSearchableFields.add("name");

		return listSearchableFields;
	}

}
