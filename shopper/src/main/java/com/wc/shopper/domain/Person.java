package com.wc.shopper.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import org.hibernate.search.annotations.Analyzer;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Index;
import org.hibernate.search.annotations.IndexedEmbedded;

@MappedSuperclass
public class Person extends BaseEntity {
	private static final long serialVersionUID = -2034804195L;

	@NotNull
	@Column(name = "firstName", unique = false)
	@Field(index = Index.YES)
	@Analyzer(definition = "entityAnalyzer")
	protected String firstName

	;

	@NotNull
	@Column(name = "lastName", unique = false)
	@Field(index = Index.YES)
	@Analyzer(definition = "entityAnalyzer")
	protected String lastName

	;

	@IndexedEmbedded
	@AttributeOverrides({

			@AttributeOverride(name = "phone", column = @Column(name = "contactDetails_phone")),

			@AttributeOverride(name = "secondaryPhone", column = @Column(name = "contactDetails_secondaryPhone")),

			@AttributeOverride(name = "city", column = @Column(name = "contactDetails_city")),

			@AttributeOverride(name = "postalCode", column = @Column(name = "contactDetails_postalCode"))

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
	 * @see org.witchcraft.model.support.BusinessEntity#retrieveSearchableFieldsArray()
	 */
	
	//@Override
	public List<String> listSearchableFields() {
		List<String> listSearchableFields = new ArrayList<String>();
		listSearchableFields.addAll(super.listSearchableFields());

		listSearchableFields.add("firstName");

		listSearchableFields.add("lastName");

		listSearchableFields.add("contactDetails.phone");

		listSearchableFields.add("contactDetails.secondaryPhone");

		listSearchableFields.add("contactDetails.city");

		listSearchableFields.add("contactDetails.postalCode");

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

}
