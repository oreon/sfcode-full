package org.witchcraft.model.support;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="users")
public class User  implements Serializable {
 
    private String username;
 
    private String password;
   
    private int enabled;
 
    public User(String username, String password, int enabled) {
    this.username = username;
    this.password = password;
    this.enabled = enabled;
    }
 
    public User() {
    }
    //getters and setters

	public int getEnabled() {
		return enabled;
	}

	public void setEnabled(int enabled) {
		this.enabled = enabled;
	}

	@Column(nullable = false)
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	@Id
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
}
