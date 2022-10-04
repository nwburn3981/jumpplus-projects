package com.cognixia.jump.model;

import java.io.Serializable;

public class User implements Serializable {

	private static final long serialVersionUID = 1L;

	private long id;

	private String name;
	private String username;
	private String password;
	private String city;
	private String street;
	private String phoneNumber;

	public User() {
		this("N/A", "N/A", "N/A", "N/A", "N/A", "N/A");
		this.id = -1L;
	}

	public User(String name, String username, String password, String city, String street, String phoneNumber) {
		super();
		this.id = -1L;
		this.name = name;
		this.username = username;
		this.password = password;
		this.city = city;
		this.street = street;
		this.phoneNumber = phoneNumber;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", username=" + username + ", password=" + password + ", city="
				+ city + ", street=" + street + ", phoneNumber=" + phoneNumber + "]";
	}

}
