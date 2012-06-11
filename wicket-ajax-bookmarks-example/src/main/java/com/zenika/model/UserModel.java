package com.zenika.model;

import java.io.Serializable;


/**
 * Modèle d'une offre.
 * 
 * @author Steria
 * 
 */
public class UserModel implements Serializable {

	private String userId;

	private String email;

	private Address address;

	public UserModel() {

	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}
	
}
