package com.oreon.talent.candidates;

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

import com.oreon.talent.ProjectUtils;

@Entity
@Table(name = "candidate")
@Filter(name = "archiveFilterDef")
@Name("candidate")
@Indexed
@Cache(usage = CacheConcurrencyStrategy.NONE)
@Analyzer(definition = "entityAnalyzer")
@XmlRootElement
public class Candidate extends com.oreon.talent.domain.Person
		implements
			java.io.Serializable {
	private static final long serialVersionUID = -1374640645L;

	@Column(unique = false)
	protected Availibility availibility

	;

	@Column(unique = false)
	protected PreferredJobType preferredJobType

	;

	@Column(unique = false)
	protected ChiefExpertise chiefExpertiese

	;

	@Column(unique = false)
	protected EducationLevel educationLevel

	;

	@Column(unique = false)
	protected Boolean willingToRelocate

	;

	@Column(unique = false)
	@Embedded
	@AttributeOverrides({
			@AttributeOverride(name = "name", column = @Column(name = "resumeFile_name")),
			@AttributeOverride(name = "contentType", column = @Column(name = "resumeFile_contentType")),
			@AttributeOverride(name = "data", column = @Column(name = "resumeFile_data", length = 4194304))})
	protected FileAttachment resumeFile = new FileAttachment();

	@OneToOne(optional = false, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "appUser_id", nullable = false, updatable = true)
	@ContainedIn
	protected com.oreon.talent.users.AppUser appUser = new com.oreon.talent.users.AppUser();

	@Lob
	@Column(unique = false)
	@Field(index = Index.TOKENIZED)
	@Analyzer(definition = "entityAnalyzer")
	protected String textResume

	;

	public void setAvailibility(Availibility availibility) {
		this.availibility = availibility;
	}

	public Availibility getAvailibility() {

		return availibility;

	}

	public void setPreferredJobType(PreferredJobType preferredJobType) {
		this.preferredJobType = preferredJobType;
	}

	public PreferredJobType getPreferredJobType() {

		return preferredJobType;

	}

	public void setChiefExpertiese(ChiefExpertise chiefExpertiese) {
		this.chiefExpertiese = chiefExpertiese;
	}

	public ChiefExpertise getChiefExpertiese() {

		return chiefExpertiese;

	}

	public void setEducationLevel(EducationLevel educationLevel) {
		this.educationLevel = educationLevel;
	}

	public EducationLevel getEducationLevel() {

		return educationLevel;

	}

	public void setWillingToRelocate(Boolean willingToRelocate) {
		this.willingToRelocate = willingToRelocate;
	}

	public Boolean getWillingToRelocate() {

		return willingToRelocate;

	}

	public void setResumeFile(FileAttachment resumeFile) {
		this.resumeFile = resumeFile;
	}

	public FileAttachment getResumeFile() {

		return resumeFile;

	}

	public void setAppUser(com.oreon.talent.users.AppUser appUser) {
		this.appUser = appUser;
	}

	public com.oreon.talent.users.AppUser getAppUser() {

		return appUser;

	}

	public void setTextResume(String textResume) {
		this.textResume = textResume;
	}

	public String getTextResume() {

		return textResume;

	}

	@Transient
	public String getDisplayName() {
		try {
			return super.getDisplayName();
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

		listSearchableFields.add("textResume");

		return listSearchableFields;
	}

	@Field(index = Index.TOKENIZED, name = "searchData")
	@Analyzer(definition = "entityAnalyzer")
	public String getSearchData() {
		StringBuilder builder = new StringBuilder();

		builder.append(getTextResume() + " ");

		if (getAppUser() != null)
			builder.append("appUser:" + getAppUser().getDisplayName() + " ");

		return builder.toString();
	}

}
