package com.ipad.dto.locationRecommand;

public class LocationRecommandDto {
	
	int teens;	
	int twenties;
	int sixties;
	int over70s;
	int sale;
	String adm_cd;
	String adm_nm;
	
	public int getTeens() {
		return teens;
	}
	public void setTeens(int teens) {
		this.teens = teens;
	}
	public int getOver70s() {
		return over70s;
	}
	public void setOver70s(int over70s) {
		this.over70s = over70s;
	}
	public String getAdm_nm() {
		return adm_nm;
	}
	public void setAdm_nm(String adm_nm) {
		this.adm_nm = adm_nm;
	}
	double saleScore;
	double teensScore;
	double twentiesScore;
	double sixtiesScore;
	double over70sScore;
	double totalScore;
	
	
	
	public double getTeensScore() {
		return teensScore;
	}
	public void setTeensScore(double teensScore) {
		this.teensScore = teensScore;
	}
	public double getOver70sScore() {
		return over70sScore;
	}
	public void setOver70sScore(double over70sScore) {
		this.over70sScore = over70sScore;
	}
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
	
}
