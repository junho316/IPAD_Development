package com.ipad.dto.locationAnalysis;

public class ResidentPopulationDto {
	private String adm_cd;
	private String year;
	private String population_total;
	private String population_male;
	private String population_female;
	private String population_10;
	private String population_20;
	private String population_30;
	private String population_40;
	private String population_50;
	private String population_60;
	private String population_70;
	private String region_name_detail;

	public ResidentPopulationDto() {
		// TODO Auto-generated constructor stub
	}

	public ResidentPopulationDto(String adm_cd, String year, String population_total, String population_male,
			String population_female, String population_10, String population_20, String population_30,
			String population_40, String population_50, String population_60, String population_70,
			String region_name_detail) {
		this.adm_cd = adm_cd;
		this.year = year;
		this.population_total = population_total;
		this.population_male = population_male;
		this.population_female = population_female;
		this.population_10 = population_10;
		this.population_20 = population_20;
		this.population_30 = population_30;
		this.population_40 = population_40;
		this.population_50 = population_50;
		this.population_60 = population_60;
		this.population_70 = population_70;
		this.region_name_detail = region_name_detail;
	}

	public String getAdm_cd() {
		return adm_cd;
	}

	public void setAdm_cd(String adm_cd) {
		this.adm_cd = adm_cd;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getPopulation_total() {
		return population_total;
	}

	public void setPopulation_total(String population_total) {
		this.population_total = population_total;
	}

	public String getPopulation_male() {
		return population_male;
	}

	public void setPopulation_male(String population_male) {
		this.population_male = population_male;
	}

	public String getPopulation_female() {
		return population_female;
	}

	public void setPopulation_female(String population_female) {
		this.population_female = population_female;
	}

	public String getPopulation_10() {
		return population_10;
	}

	public void setPopulation_10(String population_10) {
		this.population_10 = population_10;
	}

	public String getPopulation_20() {
		return population_20;
	}

	public void setPopulation_20(String population_20) {
		this.population_20 = population_20;
	}

	public String getPopulation_30() {
		return population_30;
	}

	public void setPopulation_30(String population_30) {
		this.population_30 = population_30;
	}

	public String getPopulation_40() {
		return population_40;
	}

	public void setPopulation_40(String population_40) {
		this.population_40 = population_40;
	}

	public String getPopulation_50() {
		return population_50;
	}

	public void setPopulation_50(String population_50) {
		this.population_50 = population_50;
	}

	public String getPopulation_60() {
		return population_60;
	}

	public void setPopulation_60(String population_60) {
		this.population_60 = population_60;
	}

	public String getPopulation_70() {
		return population_70;
	}

	public void setPopulation_70(String population_70) {
		this.population_70 = population_70;
	}

	public String getRegion_name_detail() {
		return region_name_detail;
	}

	public void setRegion_name_detail(String region_name_detail) {
		this.region_name_detail = region_name_detail;
	}

}
