package org.witchcraft.model.xmlutils;

import org.apache.log4j.Logger;

public class Repository {
	private static final Logger logger = Logger.getLogger(Repository.class);
	
	private String database;
	private String driver;
	
	public String getDatabase() {
		return database;
	}
	public void setDatabase(String database) {
		this.database = database;
	}
	public Repository(String database, String driver) {
		super();
		this.database = database;
		this.driver = driver;
	}
	public String getDriver() {
		return driver;
	}
	public void setDriver(String driver) {
		this.driver = driver;
	}
	public static Logger getLogger() {
		return logger;
	}
	
	
}
