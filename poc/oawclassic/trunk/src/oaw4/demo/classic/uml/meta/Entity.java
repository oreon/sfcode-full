package oaw4.demo.classic.uml.meta;

import org.openarchitectureware.core.meta.core.ElementSet;
import org.openarchitectureware.meta.uml.Type;
import org.openarchitectureware.meta.uml.classifier.Class;

public class Entity extends AbstractEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// nothing to do in the simplest case

	private String defaultRole;
	// Classes like customer, student employee etc which correspond to actors
	// that will log in and use the system
	// should have this flag turned on
	private boolean isUser;
	private String tableName;

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

	public boolean isUser() {
		return isUser;
	}

	public void setUser(boolean isUser) {
		this.isUser = isUser;
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

}