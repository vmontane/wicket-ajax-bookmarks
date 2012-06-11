package com.zenika.model.mock;

import com.zenika.model.Address;

public class AddressStub extends Address {

	public AddressStub() {
		this.setStreet("some street");
		this.setTown("Some town");
	}

	@Override
	public String toString() {
		return getStreet() + " - " + getTown();
	}

}
