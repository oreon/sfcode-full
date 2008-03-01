package oaw4.demo.classic.uml.meta;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.openarchitectureware.core.meta.core.ElementSet;
import org.witchcraft.generator.GeneratorEngineException;

public class Entity extends AbstractEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String defaultRole;
	
	// Classes like customer, student employee etc which correspond to actors
	// that will log in and use the system
	// should have this flag turned on
	private boolean systemUser;
	
	private boolean auditable = true;
	
	private String tableName;

	private String uniqueConstraints;
	
	private String inheritanceStrategy;
	
	private String testSeed;
	
	private String treeFields;
	
	private String namedQueries;
	
	private Collection nq = new ArrayList();

	// this is needed when we need to manually supply a base class
	// e.g for usermanagement the User class's base class has to be withcraft
	// supplied AbstractUser
	private String baseClass;

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public String getDefaultRole() {
		return defaultRole;
	}

	public void setDefaultRole(String defaultRole) {
		this.defaultRole = defaultRole;
	}

	

	public boolean isSystemUser() {
		return systemUser;
	}

	public void setSystemUser(boolean systemUser) {
		this.systemUser = systemUser;
	}

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

	public String getUniqueConstraints() {
		return uniqueConstraints;
	}

	public void setUniqueConstraints(String uniqueConstraints) {
		this.uniqueConstraints = uniqueConstraints;
	}

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
	 * @return if testseed is applied on any of the attribs, returns testseed of the first 
	 * attribute with non null testseed 
	 */
	public String getTestSeed() {
		ElementSet allAttribs = getAllAttributes();
		for (Object object : allAttribs) {
			if(object instanceof Column){
				Column column = (Column)object;
				if(column.getTestSeed() != null){
					setTestSeed(column.getTestSeed());
					return column.getTestSeed();
				}
					
			}
		}
		return testSeed;
	}

	public void setTestSeed(String testSeed) {
		if(testSeed != null)
			testSeed.trim();
		this.testSeed = testSeed;
	}
	
	public List<String> getTestSeedAsCollection(){
		return stringArrayAsList(testSeed);
	}

	/** Takes a string of the form {val1, val2, ...} and returns 
	 *  a list consisting of val1, val2...
	 * @param target
	 * @return
	 */
	private List<String> stringArrayAsList(String target) {
		
	
	
		//if(target.startsWith("{") && target.endsWith("}") ){
			String[] testSeedArray = target.split("[ ]*,[ ]*|\\}|\\{");
			List<String> lst = new ArrayList<String>();
			
			for (String arrArg : testSeedArray) {
				if(!StringUtils.isEmpty(arrArg))
					lst.add(arrArg);
			}
			return lst;
		//}//else
		//	throw new GeneratorEngineException(target + " should be of the form {val1, val2}");
	}

	public String getTreeFields() {
		return treeFields;
	}
	
	public List<String> getTreeFieldsAsCollection(){
		return stringArrayAsList(treeFields);
	}
	
	public String getTreeTopLevel(){
		return getTreeFieldsAsCollection().get(0);
	}

	public void setTreeFields(String treeFields) {
		this.treeFields = treeFields;
	}

	public String getNamedQueries() {
		return namedQueries;
	}

	public void setNamedQueries(String namedQueries) {
		this.namedQueries = namedQueries;
	}

	public Collection getNq() {
		return nq;
	}

	public void setNq(Collection nq) {
		this.nq = nq;
	}

	public boolean isAuditable() {
		return auditable;
	}

	public void setAuditable(boolean auditable) {
		this.auditable = auditable;
	}

}