package com.ipad.dto.locationRecommand;

public class LocationRecommandDto {
	String implant;
	String braces;
	int option;
	int twenties;
	int sixties;
	int sale;
	String adm_cd;
	String adm_nm;
	public String getAdm_nm() {
		return adm_nm;
	}
	public void setAdm_nm(String adm_nm) {
		this.adm_nm = adm_nm;
	}
	double saleScore;
	double twentiesScore;
	double sixtiesScore;
	double totalScore;
	
	public double getTotalScore() {
		return totalScore;
	}
	public void setTotalScore(double totalScore) {
		this.totalScore = totalScore;
	}
	public double getSaleScore() {
		return saleScore;
	}
	public void setSaleScore(double saleScore) {
		this.saleScore = saleScore;
	}
	public double getTwentiesScore() {
		return twentiesScore;
	}
	public void setTwentiesScore(double twentiesScore) {
		this.twentiesScore = twentiesScore;
	}
	public double getSixtiesScore() {
		return sixtiesScore;
	}
	public void setSixtiesScore(double sixtiesScore) {
		this.sixtiesScore = sixtiesScore;
	}
	public String getAdm_cd() {
		return adm_cd;
	}
	public void setAdm_cd(String adm_cd) {
		this.adm_cd = adm_cd;
	}
	public int getTwenties() {
		return twenties;
	}
	public void setTwenties(int twenties) {
		this.twenties = twenties;
	}
	public int getSixties() {
		return sixties;
	}
	public void setSixties(int sixties) {
		this.sixties = sixties;
	}
	public int getSale() {
		return sale;
	}
	public void setSale(int sale) {
		this.sale = sale;
	}
	public int getOption() {
		return option;
	}
	public void setOption(int option) {
		this.option = option;
	}
	public String getImplant() {
		return implant;
	}
	public void setImplant(String implant) {
		this.implant = implant;
	}
	public String getBraces() {
		return braces;
	}
	public void setBraces(String braces) {
		this.braces = braces;
	}	
}
