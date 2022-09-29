package com.cognixia.jump.model;

import java.io.Serializable;

public class User implements Serializable {

	private static final long serialVersionUID = 1L;

	private long user_id;

	private String username;

	private String password; // Create validation

	private String first_name;

	private String last_name;

	private String email; // Create Validation

	public User() {
		this("N/A", "N/A", "N/A", "N/A", "N/A");
		this.user_id = -1L;
	}

	public User(String username, String password, String first_name, String last_name, String email) {
		super();
		this.user_id = -1L;
		this.username = username;
		this.password = password;
		this.first_name = first_name;
		this.last_name = last_name;
	}

	public long getUser_id() {
		return user_id;
	}

	public void setUser_id(long user_id) {
		this.user_id = user_id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFirst_name() {
		return first_name;
	}

	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}

	public String getLast_name() {
		return last_name;
	}

	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return 
		"User: " + username 
		+ "\nPassword: " + password
		+ "\nName: " + first_name + " " + last_name
		+ "\nEmail: " + email;
	}

}
