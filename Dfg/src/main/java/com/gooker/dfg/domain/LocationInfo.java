package com.gooker.dfg.domain;


import java.io.Serializable;


public class LocationInfo implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1684497092277007005L;

    private Location location;

    private String formatted_address;
    private String business;
    private AddressComponent addressComponent;
    private int cityCode;

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public String getFormatted_address() {
        return formatted_address;
    }

    public void setFormatted_address(String formatted_address) {
        this.formatted_address = formatted_address;
    }

    public String getBusiness() {
        return business;
    }

    public void setBusiness(String business) {
        this.business = business;
    }

    public AddressComponent getAddressComponent() {
        return addressComponent;
    }

    public void setAddressComponent(AddressComponent addressComponent) {
        this.addressComponent = addressComponent;
    }

    public int getCityCode() {
        return cityCode;
    }

    public void setCityCode(int cityCode) {
        this.cityCode = cityCode;
    }

    @Override
    public String toString() {
        return "LocationInfo [location=" + location + ", formatted_address=" + formatted_address + ", business=" + business + ", addressComponent="
                + addressComponent + ", cityCode=" + cityCode + "]";
    }


}
