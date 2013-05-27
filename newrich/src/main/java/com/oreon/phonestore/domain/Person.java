package com.oreon.phonestore.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.hibernate.search.annotations.Analyzer;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Index;
import org.hibernate.search.annotations.IndexedEmbedded;
import org.hibernate.validator.constraints.Length;
import org.witchcraft.base.entity.BaseEntity;

@MappedSuperclass
public class Person extends BaseEntity {
	private static final long serialVersionUID = -2034804195L;

	@NotNull
	@Length(min = 1, max = 250)
	@Column(name = "firstName", unique = false)
	@Field(index = Index.YES)
	@Analyzer(definition = "entityAnalyzer")
	protected String firstName

	;

	@NotNull
	@Length(min = 1, max = 250)
	@Column(name = "lastName", unique = false)
	@Field(index = Index.YES)
	@Analyzer(definition = "entityAnalyzer")
	protected String lastName

	;

	@IndexedEmbedded
	@AttributeOverrides({

			@AttributeOverride(name = "phone", column = @Column(name = "contactDetails_phone")),

			@AttributeOverride(name = "secondaryPhone", column = @Column(name = "contactDetails_secondaryPhone")),

			@AttributeOverride(name = "city", column = @Column(name = "contactDetails_city"))

	})
	protected ContactDetails contactDetails = new ContactDetails();

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getFirstName() {

		return firstName;

	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getLastName() {

		return lastName;

	}

	public void setContactDetails(ContactDetails contactDetails) {
		this.contactDetails = contactDetails;
	}

	public ContactDetails getContactDetails() {

		return contactDetails;

	}

	@Transient
	public String getDisplayName() {
		try {
			return lastName + "," + firstName;
		} catch (Exception e) {
			return "Exception - " + e.getMessage();
		}
	}

	@Transient
	public String getPopupInfo() {
		try {
			return contactDetails.city + " " + contactDetails.phone;
		} catch (Exception e) {
			return "Exception - " + e.getMessage();
		}
	}

	/** This method is used by hibernate full text search - override to add additional fields
	 * @see org.witchcraft.model.support.BaseEntity#retrieveSearchableFieldsArray()
	 */
	@Override
	public List<String> listSearchableFields() {
		List<String> listSearchableFields = new ArrayList<String>();
		listSearchableFields.addAll(super.listSearchableFields());

		listSearchableFields.add("firstName");

		listSearchableFields.add("lastName");

		listSearchableFields.add("contactDetails.phone");

		listSearchableFields.add("contactDetails.secondaryPhone");

		listSearchableFields.add("contactDetails.city");

		return listSearchableFields;
	}

	@Field(index = Index.YES, name = "searchData")
	@Analyzer(definition = "entityAnalyzer")
	public String getSearchData() {
		StringBuilder builder = new StringBuilder();

		builder.append(getFirstName() + " ");

		builder.append(getLastName() + " ");

		return builder.toString();
	}

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this,
				ToStringStyle.SHORT_PREFIX_STYLE);
	}

}
