package com.pwc.insuranceclaims.web.action.quickclaim;

import com.pwc.insuranceclaims.quickclaim.Dependent;

import org.witchcraft.seam.action.BaseAction;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;
import org.jboss.seam.framework.EntityQuery;
import org.witchcraft.base.entity.BaseQuery;
import org.witchcraft.base.entity.Range;

import org.jboss.seam.annotations.Observer;

import com.pwc.insuranceclaims.quickclaim.Dependent;

/**
 * 
 * @author WitchcraftMDA Seam Cartridge - 
 *
 */
public abstract class DependentListQueryBase extends BaseQuery<Dependent, Long> {

	private static final String EJBQL = "select dependent from Dependent dependent";

	protected Dependent dependent = new Dependent();

	public Dependent getDependent() {
		return dependent;
	}

	@Override
	protected String getql() {
		return EJBQL;
	}

	@Override
	public Class<Dependent> getEntityClass() {
		return Dependent.class;
	}

	@Override
	public String[] getEntityRestrictions() {
		return RESTRICTIONS;
	}

	private Range<Date> dependentDateofBirthRange = new Range<Date>();
	public Range<Date> getDependentDateofBirthRange() {
		return dependentDateofBirthRange;
	}
	public void setDependentDateofBirth(Range<Date> dependentDateofBirthRange) {
		this.dependentDateofBirthRange = dependentDateofBirthRange;
	}

	private static final String[] RESTRICTIONS = {
			"dependent.id = #{dependentList.dependent.id}",

			"lower(dependent.dependentName) like concat(lower(#{dependentList.dependent.dependentName}),'%')",

			"dependent.customer.id = #{dependentList.dependent.customer.id}",

			"dependent.dependentDateofBirth >= #{dependentList.dependentDateofBirthRange.begin}",
			"dependent.dependentDateofBirth <= #{dependentList.dependentDateofBirthRange.end}",

			"dependent.dependentGender = #{dependentList.dependent.dependentGender}",

			"dependent.dateCreated <= #{dependentList.dateCreatedRange.end}",
			"dependent.dateCreated >= #{dependentList.dateCreatedRange.begin}",};

	public List<Dependent> getDependentsByCustomer(
			com.pwc.insuranceclaims.quickclaim.Customer customer) {
		//setMaxResults(10000);
		dependent.setCustomer(customer);
		return getResultList();
	}

	@Observer("archivedDependent")
	public void onArchive() {
		refresh();
	}

	/** create comma delimited row 
	 * @param builder
	 */
	//@Override
	public void createCsvString(StringBuilder builder, Dependent e) {

		builder.append("\""
				+ (e.getDependentName() != null ? e.getDependentName().replace(
						",", "") : "") + "\",");

		builder.append("\""
				+ (e.getCustomer() != null ? e.getCustomer().getDisplayName()
						.replace(",", "") : "") + "\",");

		builder.append("\""
				+ (e.getDependentDateofBirth() != null ? e
						.getDependentDateofBirth() : "") + "\",");

		builder
				.append("\""
						+ (e.getDependentGender() != null ? e
								.getDependentGender() : "") + "\",");

		builder.append("\r\n");
	}

	/** create the headings 
	 * @param builder
	 */
	//@Override
	public void createCSvTitles(StringBuilder builder) {

		builder.append("DependentName" + ",");

		builder.append("Customer" + ",");

		builder.append("DependentDateofBirth" + ",");

		builder.append("DependentGender" + ",");

		builder.append("\r\n");
	}
}
