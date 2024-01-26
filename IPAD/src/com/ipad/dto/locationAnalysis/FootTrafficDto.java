package com.ipad.dto.locationAnalysis;

public class FootTrafficDto {
	private String adm_cd;
	private String quater;
	private String population_total;
	private String population_mon;
	private String population_tues;
	private String population_wed;
	private String population_thur;
	private String population_fri;
	private String population_sat;
	private String population_sun;
	private String region_name_detail;

	public FootTrafficDto(String adm_cd, String quater, String population_total, String population_mon,
			String population_tues, String population_wed, String population_thur, String population_fri,
			String population_sat, String population_sun, String region_name_detail) {
		this.adm_cd = adm_cd;
		this.quater = quater;
		this.population_total = population_total;
		this.population_mon = population_mon;
		this.population_tues = population_tues;
		this.population_wed = population_wed;
		this.population_thur = population_thur;
		this.population_fri = population_fri;
		this.population_sat = population_sat;
		this.population_sun = population_sun;
		this.region_name_detail = region_name_detail;

	}

	public String getAdv_cd() {
		return adm_cd;
	}

	public void setAdv_cd(String adv_cd) {
		this.adm_cd = adv_cd;
	}

	public String getQuater() {
		return quater;
	}

	public void setQuater(String quater) {
		this.quater = quater;
	}

	public String getPopulation_total() {
		return population_total;
	}

	public void setPopulation_total(String population_total) {
		this.population_total = population_total;
	}

	public String getPopulation_mon() {
		return population_mon;
	}

	public void setPopulation_mon(String population_mon) {
		this.population_mon = population_mon;
	}

	public String getPopulation_tues() {
		return population_tues;
	}

	public void setPopulation_tues(String population_tues) {
		this.population_tues = population_tues;
	}

	public String getPopulation_wed() {
		return population_wed;
	}

	public void setPopulation_wed(String population_wed) {
		this.population_wed = population_wed;
	}

	public String getPopulation_thur() {
		return population_thur;
	}

	public void setPopulation_thur(String population_thur) {
		this.population_thur = population_thur;
	}

	public String getPopulation_fri() {
		return population_fri;
	}

	public void setPopulation_fri(String population_fri) {
		this.population_fri = population_fri;
	}

	public String getPopulation_sat() {
		return population_sat;
	}

	public void setPopulation_sat(String population_sat) {
		this.population_sat = population_sat;
	}

	public String getPopulation_sun() {
		return population_sun;
	}

	public void setPopulation_sun(String population_sun) {
		this.population_sun = population_sun;
	}

}
