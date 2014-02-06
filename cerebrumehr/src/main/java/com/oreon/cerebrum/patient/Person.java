package com.oreon.cerebrum.patient;

import java.util.ArrayList;
import java.util.Date;
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
import org.hibernate.validator.constraints.Length;
import org.witchcraft.base.entity.BaseEntity;

@MappedSuperclass
public class Person extends BaseEntity {
	private static final long serialVersionUID = -1283387220L;

	@NotNull
	@Length(min = 1, max = 250)
	@Column(unique = false)
	@Field(index = Index.YES)
	@Analyzer(definition = "entityAnalyzer")
	protected String firstName

	;

	@NotNull
	@Length(min = 1, max = 250)
	@Column(unique = false)
	@Field(index = Index.YES)
	@Analyzer(definition = "entityAnalyzer")
	protected String lastName

	;

	@Column(unique = false)
	protected Date dateOfBirth

	;

	@Column(unique = false)
	protected Gender gender

	;

	@IndexedEmbedded
	@AttributeOverrides({

			@AttributeOverride(name = "primaryPhone", column = @Column(name = "contactDetails_primaryPhone")),

			@AttributeOverride(name = "secondaryPhone", column = @Column(name = "contactDetails_secondaryPhone")),

			@AttributeOverride(name = "email", column = @Column(name = "contactDetails_email"))

	})
	protected ContactDetails contactDetails = new ContactDetails();

	@Transient
	protected Integer age

	;

	@Column(unique = false)
	protected Title title

	;

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

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public Date getDateOfBirth() {

		return dateOfBirth;

	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public Gender getGender() {

		return gender;

	}

	public void setContactDetails(ContactDetails contactDetails) {
		this.contactDetails = contactDetails;
	}

	public ContactDetails getContactDetails() {

		if (contactDetails == null)
			contactDetails = new com.oreon.cerebrum.patient.ContactDetails();
		return contactDetails;

	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public Integer getAge() {

		try {
			return DateUtils.calcAge(dateOfBirth);
		} catch (Exception e) {

			return 0;

		}

	}

	public void setTitle(Title title) {
		this.title = title;
	}

	public Title getTitle() {

		return title;

	}

	@Transient
	public String getDisplayName() {
		try {
			return lastName != null ? lastName + ", " + firstName : "";
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

		listSearchableFields.add("contactDetails.primaryPhone");

		listSearchableFields.add("contactDetails.secondaryPhone");

		listSearchableFields.add("contactDetails.email");

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
