package com.ipad.dto.saleAnalysis;

public class SimpleAsDto {
	private String adm_cd;
	   private int population;
	   private int float_pp;
	   private int countClinic;
	   
	   public SimpleAsDto() {
	      
	   }
	   
	   public SimpleAsDto(int population, int float_pp, int countClinic) {
	      this.population = population;
	      this.float_pp = float_pp;
	      this.countClinic = countClinic;
	   }
	   
	   public String getAdm_cd() {
	      return adm_cd;
	   }
	   public void setAdm_cd(String adm_cd) {
	      this.adm_cd = adm_cd;
	   }

	   public int getPopulation() {
	      return population;
	   }

	   public void setPopulation(int population) {
	      this.population = population;
	   }

	   public int getFloat_pp() {
	      return float_pp;
	   }

	   public void setFloat_pp(int float_pp) {
	      this.float_pp = float_pp;
	   }

	   public int getCountClinic() {
	      return countClinic;
	   }

	   public void setCountClinic(int countClinic) {
	      this.countClinic = countClinic;
	   }
	   

	
}
