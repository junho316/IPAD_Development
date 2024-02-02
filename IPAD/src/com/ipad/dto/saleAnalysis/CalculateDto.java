package com.ipad.dto.saleAnalysis;

public class CalculateDto {
	private String adm_cd;
	private String region_name;
	private int predictSale;
	private int predictPatient;
	private int size;
	private int employee;
	private int netProfit;
	
	public int getNetProfit() {
		return netProfit;
	}
	public void setNetProfit(int netProfit) {
		this.netProfit = netProfit;
	}
	public String getAdm_cd() {
		return adm_cd;
	}
	public void setAdm_cd(String adm_cd) {
		this.adm_cd = adm_cd;
	}
	public String getRegion_name() {
		return region_name;
	}
	public void setRegion_name(String region_name) {
		this.region_name = region_name;
	}
	public int getPredictSale() {
		return predictSale;
	}
	public void setPredictSale(int predictSale) {
		this.predictSale = predictSale;
	}
	public int getPredictPatient() {
		return predictPatient;
	}
	public void setPredictPatient(int predictPatient) {
		this.predictPatient = predictPatient;
	}
	public int getSize() {
		return size;
	}
	public void setSize(int size) {
		this.size = size;
	}
	public int getEmployee() {
		return employee;
	}
	public void setEmployee(int employee) {
		this.employee = employee;
	}
	
	
}
