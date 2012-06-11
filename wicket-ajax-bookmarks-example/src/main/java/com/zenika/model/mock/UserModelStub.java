package com.zenika.model.mock;

import com.zenika.model.Address;
import com.zenika.model.UserModel;


/**
 * Modèle d'une offre.
 * 
 * @author Steria
 * 
 */
public class UserModelStub extends UserModel {

	public UserModelStub() {
		Address address = new AddressStub();
		this.setAddress(address);
		this.setEmail("some.email@zenika.com");
		this.setUserId("userId");
	}

	@Override
	public String toString() {
		return getUserId() + " - " + getEmail() + " - " + getAddress();
	}

}
