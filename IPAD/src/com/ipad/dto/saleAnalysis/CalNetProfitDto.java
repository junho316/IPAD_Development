package com.ipad.dto.saleAnalysis;

public class CalNetProfitDto {
	private String adm_cd;
	private String region_name;
	private int predictSale;
	private int predictPatient;
	private int rentFee;
	private int employment_cost;
	private int seniorEmployment_cost;
	private int juniorEmployment_cose;
	private int deptAmount;
	private int netProfit;
	
	public int getDeptAmount() {
		return deptAmount;
	}
	public void setDeptAmount(int deptAmount) {
		this.deptAmount = deptAmount;
	}
	public int getNetProfit() {
		return netProfit;
	}
	public void setNetProfit(int netProfit) {
		this.netProfit = netProfit;
	}
	public int getSeniorEmployment_cost() {
		return seniorEmployment_cost;
	}
	public void setSeniorEmployment_cost(int seniorEmployment_cost) {
		this.seniorEmployment_cost = seniorEmployment_cost;
	}
	public int getJuniorEmployment_cose() {
		return juniorEmployment_cose;
	}
	public void setJuniorEmployment_cose(int juniorEmployment_cose) {
		this.juniorEmployment_cose = juniorEmployment_cose;
	}
	public int getRentFee() {
		return rentFee;
	}
	public void setRentFee(int rentFee) {
		this.rentFee = rentFee;
	}
	public int getEmployment_cost() {
		return employment_cost;
	}
	public void setEmployment_cost(int employment_cost) {
		this.employment_cost = employment_cost;
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
}
