package com.hrb.tservices.dtos;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Date;
import javax.ws.rs.core.Response;

@javax.xml.bind.annotation.XmlRootElement
public class OfficeLocation {

	private String officeId;

	private String headingEN;

	private String headingFR;

	private String address;

	private String city;

	private String province;

	private String postalCode;

	private String latitude;

	private String longitude;

	private String phone;

	private String fax;

	private String enInfo;

	private String frInfo;

	private String enHours;

	private String frHours;

	private Integer distance;

	private String url;

	private OfficeLocationList officeLocationList;

	public void setOfficeId(String officeId) {
		this.officeId = officeId;
	}

	public String getOfficeId() {
		return officeId;
	}

	public void setHeadingEN(String headingEN) {
		this.headingEN = headingEN;
	}

	public String getHeadingEN() {
		return headingEN;
	}

	public void setHeadingFR(String headingFR) {
		this.headingFR = headingFR;
	}

	public String getHeadingFR() {
		return headingFR;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getAddress() {
		return address;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCity() {
		return city;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getProvince() {
		return province;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getPhone() {
		return phone;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public String getFax() {
		return fax;
	}

	public void setEnInfo(String enInfo) {
		this.enInfo = enInfo;
	}

	public String getEnInfo() {
		return enInfo;
	}

	public void setFrInfo(String frInfo) {
		this.frInfo = frInfo;
	}

	public String getFrInfo() {
		return frInfo;
	}

	public void setEnHours(String enHours) {
		this.enHours = enHours;
	}

	public String getEnHours() {
		return enHours;
	}

	public void setFrHours(String frHours) {
		this.frHours = frHours;
	}

	public String getFrHours() {
		return frHours;
	}

	public void setDistance(Integer distance) {
		this.distance = distance;
	}

	public Integer getDistance() {
		return distance;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getUrl() {
		return url;
	}

	public void setOfficeLocationList(OfficeLocationList officeLocationList) {
		this.officeLocationList = officeLocationList;
	}

	public OfficeLocationList getOfficeLocationList() {
		return officeLocationList;
	}

}
