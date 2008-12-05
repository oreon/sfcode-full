package oaw4.demo.classic.uml.meta;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import oaw4.demo.classic.uml.extend.GenericUtils;

import org.apache.log4j.Logger;
import org.openarchitectureware.core.meta.core.ElementSet;

import org.openarchitectureware.meta.uml.classifier.Operation;

import com.thoughtworks.xstream.XStream;

public class Entity extends AbstractEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String defaultRole;

	Logger log = Logger.getLogger(Entity.class);

	// Classes like customer, student employee etc which correspond to actors
	// that will log in and use the system
	// should have this flag turned on
	private boolean systemUser;

	private boolean auditable = true;

	private boolean createSampleData = true;

	private String tableName;

	private String uniqueConstraints;

	private String inheritanceStrategy;

	private String testSeed;

	private String treeFields;

	private String namedQueries;
	
	private String defaultValue;

	public String getDefaultValue() {
		return defaultValue;
	}

	public void setDefaultValue(String defaultValue) {
		this.defaultValue = defaultValue;
	}

	private Collection nq = new ArrayList();

	
	private String baseClass;

	/** The tablename that this entity will be mapped to - if blank it will default to the name of entity. 
	 * @return
	 */
	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	/** Default role for an entity for which systemUser flag is true - e.g for a customer 
	 * we can set it to ROLE_CUSTOMER
	 * @return
	 */
	public String getDefaultRole() {
		return defaultRole;
	}

	public void setDefaultRole(String defaultRole) {
		this.defaultRole = defaultRole;
	}

	/** An attribute that indicates whether this entity is also a user of the system e.g. 
	 * entities like Customer, Employee in an e-commerce system should have this attribute
	 * set to true. Additionally they need to have a one to one relationship with a class
	 * derived from AbstractUser.
	 * @return
	 */
	public boolean isSystemUser() {
	//	org.openarchitectureware.meta.uml.classifier.AssociationEnd ae = new org.openarchitectureware.meta.uml.classifier.AssociationEnd();
		
		return systemUser;
		
	}

	public void setSystemUser(boolean systemUser) {
		this.systemUser = systemUser;
	}

	/** Returns all columns for this entity 
	 * @see oaw4.demo.classic.uml.meta.AbstractEntity#Column()
	 */
	@Override
	public ElementSet Column() {

		ElementSet columns = super.Column();
		addUserIdIfApplies(columns);
		return columns;
	}

	/**
	 * If this entity is a user of the system we need to add ensure unique by
	 * userid
	 * 
	 * @param columns
	 */
	private void addUserIdIfApplies(ElementSet columns) {

		/*
		 * if(isUser || getDefaultRole() != null){ Column column = new Column();
		 * column.setName("username"); Type type = new Class();
		 * type.setName("String"); column.setType(type );
		 * 
		 * column.setContainerName("userAccount"); column.setUnique(true);
		 * column.setSearchable(true); columns.add(column); }
		 */

	}

	/**
	 * this is needed when we need to manually supply a base class e.g for
	 * usermanagement the User class's base class has to be withcraft supplied
	 * AbstractUser
	 * 
	 * @return
	 */
	public String getBaseClass() {
		return baseClass;
	}

	public void setBaseClass(String baseClass) {
		this.baseClass = baseClass;
	}

	/** Unique constraints for this entity e.g. in order items table we might want to enforce 
	 * that every orderid and orderitemid pair be unique.
	 * @return
	 */
	public String getUniqueConstraints() {
		return uniqueConstraints;
	}

	public List<String> getUniqueConstraintsAsCollection() {
		return GenericUtils.tokenizeString(uniqueConstraints, " *, *");
	}

	public void setUniqueConstraints(String uniqueConstraints) {
		this.uniqueConstraints = uniqueConstraints;
	}

	/** Inheritance strategy to be used for this class - corresponds one to one with JPA inheritance 
	 * strategies.
	 * @return
	 */
	public String getInheritanceStrategy() {
		System.out.println("inheritance " + inheritanceStrategy);
		return inheritanceStrategy;
	}

	public void setInheritanceStrategy(String inheritanceStrategy) {
		this.inheritanceStrategy = inheritanceStrategy;
		// System.out.println("inheritance " + inheritanceStrategy != null
		// ?inheritanceStrategy.toString():" is null ");
	}

	/**
	 * Will return all query operations - operations that have name begining
	 * with "find" are considered query operations.
	 * 
	 * @return
	 */
	public ElementSet getQueryOperations() {
		ElementSet operations = Operation();
		ElementSet queryOps = new ElementSet();
		for (Object object : operations) {
			Operation operation = (Operation) object;
			if (operation.NameS().startsWith("find"))
				queryOps.add(operation);
		}

		return queryOps;
	}

	public NamedQuery getNamedQuery(Operation operation) {
		
		if(operation.Documentation() == null) {
			log.warn("The documentation for " + operation
					+ " does not exist");
			return null;
		}
		
		List<String> docList = GenericUtils.tokenizeString(operation
				.Documentation(), " *\\[\\[ *| *\\]\\] *");
		if (docList.size() < 2) {
			log.warn("The documentation for " + operation
					+ " does not include any named query generation info");
			return null;
		}

		String qryTxt = docList.get(1);
		//firstStereoType
		

		NamedQuery namedQuery = createQryFromXML(qryTxt);
		log.info("Found namedQuery " + docList.get(1));
		return namedQuery;
	}

	protected NamedQuery createQryFromXML(String qryTxt) {
		XStream xstream = new XStream();
		xstream.alias("query", oaw4.demo.classic.uml.meta.NamedQuery.class);
		xstream.useAttributeFor(NamedQuery.class, "name");
		xstream.useAttributeFor(NamedQuery.class, "text");
		
		//xstream.useAttributeFor("genericReturnType", NamedQuery.class);
		NamedQuery namedQuery = (NamedQuery) xstream.fromXML(qryTxt);
		return namedQuery;
	}

	/**
	 * @return if testseed is applied on any of the attribs, returns testseed of
	 *         the first attribute with non null testseed
	 */
	public String getTestSeed() {
		ElementSet allAttribs = getAllAttributes();
		for (Object object : allAttribs) {
			if (object instanceof Column) {
				Column column = (Column) object;
				if (column.getTestSeed() != null) {
					setTestSeed(column.getTestSeed());
					return column.getTestSeed();
				}

			}
		}
		return testSeed;
	}

	public void setTestSeed(String testSeed) {
		if (testSeed != null)
			testSeed.trim();
		this.testSeed = testSeed;
	}

	public List<String> getTestSeedAsCollection() {
		return stringArrayAsList(testSeed);
	}

	/**
	 * Takes a string of the form {val1, val2, ...} and returns a list
	 * consisting of val1, val2...
	 * 
	 * @param target
	 * @return
	 */
	private List<String> stringArrayAsList(String target) {
		return GenericUtils.stringArrayAsList(target);
	}

	/** The fields which need to be displayed as a hirerchical tree e.g. category and subcategory.
	 * @return
	 */
	public String getTreeFields() {
		return treeFields;
	}

	public List<String> getTreeFieldsAsCollection() {
		return stringArrayAsList(treeFields);
	}

	public String getTreeTopLevel() {
		if(getTreeFieldsAsCollection().size() > 0)
			return getTreeFieldsAsCollection().get(0);
		else{
			log.error("There is an error with your tree specification feilds: " + getTreeFields());
			return "INVALID TREEFLDS SPEC";
		}
	}

	public void setTreeFields(String treeFields) {
		this.treeFields = treeFields;
	}

	/** A collection of named queries for this entity.
	 * @return
	 */
	public String getNamedQueries() {
		return namedQueries;
	}

	public void setNamedQueries(String namedQueries) {
		this.namedQueries = namedQueries;
	}

	

	/** The entities which need auditing should have this flag set. All actions will be automatically logged. 
	 * @return
	 */
	public boolean isAuditable() {
		return auditable;
	}

	public void setAuditable(boolean auditable) {
		this.auditable = auditable;
	}

}