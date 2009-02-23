package com.wcmda.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import static org.jboss.seam.ScopeType.SESSION;

import org.hibernate.validator.Length;
import org.hibernate.validator.NotNull;
import org.hibernate.validator.Pattern;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;

import com.wcmda.validator.Unique;
//import com.wcmda.validator.PasswordPolicyRestricted;
import com.wcmda.validator.UniqueUser;

@Entity
@Name("user")
@Scope(SESSION)
@Table(name = "Customer")
public class User implements Serializable {

	private String username;
	private String password;
	private String name;

	public User(String username, String password, String name) {
		super();
		this.username = username;
		this.password = password;
		this.name = name;
	}

	public User() {
	}

	@NotNull
	@Length(max = 100)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@NotNull
	@Length(min = 5, max = 15)
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Id
	@Length(min = 5, max = 15)
	@Pattern(regex = "^\\w*$", message = "not a valid username")
	@UniqueUser(Unique.FIRSTNAME)
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Override
	public String toString() {
		return "User(" + username + ")";
	}
}
