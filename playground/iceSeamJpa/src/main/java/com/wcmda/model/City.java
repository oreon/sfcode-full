package com.wcmda.model;

public class City {
	// attributes of each entry
    private String city;
    private String state;
    private String zip;
    private String areaCode;
    private String country;
    private String stateCode;
    private int image;

    public City(String city, String state, String zip, String areaCode,
                String country, String stateCode, int image) {
        this.city = city;
        this.state = state;
        this.zip = zip;
        this.areaCode = areaCode;
        this.country = country;
        this.stateCode = stateCode;
        this.image = image;
    }

    public City() {
    }

    public void setImage(int image) {
        this.image = image;
    }

    public int getImage() {

        return image;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getState() {
        return state;
    }

    public String getAreaCode() {
        return areaCode;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getStateCode() {
        //only add parentheses if the two letter state code is present
        if (stateCode == null) {
            return stateCode;
        } else if (stateCode.equals("")) {
            return stateCode;
        } else {
            return "(" + stateCode + ")";
        }
    }

    public void setStateCode(String stateCode) {
        this.stateCode = stateCode;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }
}
