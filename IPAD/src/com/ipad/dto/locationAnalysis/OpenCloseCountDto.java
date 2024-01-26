package com.ipad.dto.locationAnalysis;

public class OpenCloseCountDto {
	private int year;
	private int openings;
	private int closures;
	private int count;

	public OpenCloseCountDto(int year, int openings, int closures, int count) {
		this.year = year;
		this.openings = openings;
		this.closures = closures;
		this.count = count;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public int getOpenings() {
		return openings;
	}

	public void setOpenings(int openings) {
		this.openings = openings;
	}

	public int getClosures() {
		return closures;
	}

	public void setClosures(int closures) {
		this.closures = closures;
	}

}
