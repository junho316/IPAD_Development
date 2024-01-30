package com.ipad.dto.locationAnalysis;

public class PopulationForecastDto {
	private int year;
	private String region;
	private int population;
	private int birth;
	private int death;
	private int numberHouse;
	private int family;

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public int getPopulation() {
		return population;
	}

	public void setPopulation(int population) {
		this.population = population;
	}

	public int getBirth() {
		return birth;
	}

	public void setBirth(int birth) {
		this.birth = birth;
	}

	public int getDeath() {
		return death;
	}

	public void setDeath(int death) {
		this.death = death;
	}

	public int getNumberHouse() {
		return numberHouse;
	}

	public void setNumberHouse(int numberHouse) {
		this.numberHouse = numberHouse;
	}

	public int getFamily() {
		return family;
	}

	public void setFamily(int family) {
		this.family = family;
	}

}
