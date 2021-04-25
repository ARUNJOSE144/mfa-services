/**
 * SixDEE Telecom Solutions Pvt. Ltd.
 * Copyright 2017
 * All Rights Reserved.
 */
package com.sixdee.magik.services.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * @author Rajesh
 * @version 1.0.0.0
 * @since 16-Sep-2020 : 6:44:54 PM
 */
@Embeddable
public class Address {

	/**
	 * 
	 */
	@Column(name = "ADDRESS_1")
	public String addressLine1;
	/**
	 * 
	 */
	@Column(name = "ADDRESS_2")
	public String addressLine2;
	/**
	 * 
	 */
	@Column(name = "CITY")
	public String city;
	/**
	 * 
	 */
	@Column(name = "ZIP")
	public String zip;
	
	@Column(name = "REGION")
	public String region;

	/**
	 * 
	 */
	public Address() {
	}


	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getZip() {
		return zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }


	public String getAddressLine1() {
		return addressLine1;
	}


	public void setAddressLine1(String addressLine1) {
		this.addressLine1 = addressLine1;
	}


	public String getAddressLine2() {
		return addressLine2;
	}


	public void setAddressLine2(String addressLine2) {
		this.addressLine2 = addressLine2;
	}
	

}