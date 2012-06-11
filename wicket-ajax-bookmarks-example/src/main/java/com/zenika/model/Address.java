package com.zenika.model;

import java.io.Serializable;

public class Address implements Serializable {

	private String street;

	private String town;

	public String getTown() {
		return town;
	}

	public void setTown(String town) {
		this.town = town;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

}
