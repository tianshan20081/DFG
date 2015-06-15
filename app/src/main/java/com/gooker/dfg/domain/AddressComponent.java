package com.gooker.dfg.domain;

import java.io.Serializable;

/**
 * "addressComponent":{"city":"北京市","district":"朝阳区","province":"北京市","street":
 * "化工路","street_number":"甲18号"}
 * 
 * @author nk-aoeng
 * 
 */
public class AddressComponent implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6272985393807780060L;
	private String city;
	private String district;
	private String province;
	private String street;
	private String street_number;

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getStreet_number() {
		return street_number;
	}

	public void setStreet_number(String street_number) {
		this.street_number = street_number;
	}

	@Override
	public String toString() {
		return province + "," + city + "," + district + "," + street;
	}

}
