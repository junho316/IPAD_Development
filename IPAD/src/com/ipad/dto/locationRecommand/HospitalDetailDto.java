package com.ipad.dto.locationRecommand;

import java.sql.Date;

public class HospitalDetailDto {
	private String hospital_name;
	private String region;
	private String license_date;
	private String business_status;
	private String region_name_detail;
	private String address;
	private String close_date;
	private float x_coordinate;
	private float y_coordinate;

	public HospitalDetailDto(String hospital_name, String region, String address, String region_name_detail,
			String business_status, float x_coordinate, float y_coordinate, String license_date, String close_date) {
		this.hospital_name = hospital_name;
		this.region = region;
		this.address = address;
		this.business_status = business_status;
		this.x_coordinate = x_coordinate;
		this.y_coordinate = y_coordinate;
		this.region_name_detail = region_name_detail;
		this.license_date = license_date;
		this.close_date = close_date;

	}

	public String getAdm_cd() {
		return region_name_detail;
	}

	public void setAdm_cd(String adm_cd) {
		this.region_name_detail = adm_cd;
	}

	public String getHospital_name() {
		return hospital_name;
	}

	public void setHospital_name(String hospital_name) {
		this.hospital_name = hospital_name;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public String getLicense_date() {
		return license_date;
	}

	public void setLicense_date(String license_date) {
		this.license_date = license_date;
	}

	public String getBusiness_status() {
		return business_status;
	}

	public void setBusiness_status(String business_status) {
		this.business_status = business_status;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getClose_date() {
		return close_date;
	}

	public void setClose_date(String close_date) {
		this.close_date = close_date;
	}

	public float getX_coordinate() {
		return x_coordinate;
	}

	public void setX_coordinate(float x_coordinate) {
		this.x_coordinate = x_coordinate;
	}

	public float getY_coordinate() {
		return y_coordinate;
	}

	public void setY_coordinate(float y_coordinate) {
		this.y_coordinate = y_coordinate;
	}

}
