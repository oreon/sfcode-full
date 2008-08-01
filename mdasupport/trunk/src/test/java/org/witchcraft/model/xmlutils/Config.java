package org.witchcraft.model.xmlutils;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

public class Config {
	private static final Logger logger = Logger.getLogger(Config.class);
	
	private String name;
	private String version;
	private List<Repository> repositories = new ArrayList<Repository>();
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Config(String name, String version ,List<Repository> repositories ) {
		super();
		this.name = name;
		this.repositories = repositories;
		this.version = version;
	}
	public String getVersion() {
		return version;
	}
	public List<Repository> getRepositories() {
		return repositories;
	}
	public void setRepositories(List<Repository> repositories) {
		this.repositories = repositories;
	}
	public void setVersion(String version) {
		this.version = version;
	}
}
