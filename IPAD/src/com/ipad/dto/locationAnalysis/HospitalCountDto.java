package com.ipad.dto.locationAnalysis;

public class HospitalCountDto {
	private String year;
	private String region;
	private String count;

	public HospitalCountDto(String year, String region, String count) {
		this.region = region;
		this.year = year;
		this.count = count;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getCount() {
		return count;
	}

	public void setCount(String count) {
		this.count = count;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

}
