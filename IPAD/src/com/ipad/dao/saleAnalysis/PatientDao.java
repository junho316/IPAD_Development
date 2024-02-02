package com.ipad.dao.saleAnalysis;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.ipad.dto.saleAnalysis.CalculateDto;



public class PatientDao {
   private Connection con;
   private PreparedStatement pstmt;
   private ResultSet resultSet, coefResultSet;
   private DataSource dataSource;
   List<Float> coefficient = new ArrayList<Float>();
   ArrayList<String> admCode = new ArrayList<String>();

   public PatientDao() {
      try {
         Context ctx = new InitialContext();
         Context envContext = (Context) ctx.lookup("java:/comp/env");
         dataSource = (DataSource) envContext.lookup("jdbc/Oracle11g");
      } catch (Exception e) {
         e.printStackTrace();
      }
   }

   public ArrayList<String> getAdmCode() {
      try {
         con = dataSource.getConnection();
         String query = "Select adm_cd from region";
         pstmt = con.prepareStatement(query);
         resultSet = pstmt.executeQuery();
         while (resultSet.next()) {
            admCode.add(resultSet.getString("adm_cd"));
         }
      } catch (SQLException e) {
         e.printStackTrace();
      } finally {
         try {
            resultSet.close();
            pstmt.close();
            con.close();
         } catch (SQLException e) {
            e.printStackTrace();
         }
      }
      return admCode;
   }

   public int patientCal(String adm_cd) {
      int patient = 0;
      try {
         con = dataSource.getConnection();
         String query = "SELECT population, floatpp, income, dentalclinic FROM region_data WHERE adm_cd=?";
         pstmt = con.prepareStatement(query);
         pstmt.setString(1, adm_cd);
         resultSet = pstmt.executeQuery();

         while (resultSet.next()) {
            int population = resultSet.getInt("population");
            int floatpp = resultSet.getInt("floatpp");
            int income = resultSet.getInt("income");
            int dentalclinic = resultSet.getInt("dentalclinic");

            String query2 = "SELECT * FROM patient_point";
            PreparedStatement pstmt2 = con.prepareStatement(query2);
            coefResultSet = pstmt2.executeQuery();

            while (coefResultSet.next()) {
            	 patient = (int) ((int) Math.round(coefResultSet.getInt("constant") 
                         + Math.log10(population) * coefResultSet.getInt("population_point")
                         + Math.log10(floatpp) * coefResultSet.getInt("float_point")
                         + Math.log10(income) * coefResultSet.getInt("income_point")
                         + Math.log10(dentalclinic) * coefResultSet.getInt("dentist_point")) / dentalclinic);

            }

            coefResultSet.close();
            pstmt2.close();
         }

      } catch (SQLException e) {
         e.printStackTrace();
      } finally {
         try {
            pstmt.close();
            resultSet.close();
            con.close();
         } catch (SQLException e) {
            e.printStackTrace();
         }
      }
      return patient;
   }

   public int employeeCal(int patient) {
	   	  patient = (int) Math.round(patient / 30.0);
	      int employee = 0;
	      employee = (int) Math.ceil(((double)patient / 12)/2.5)+1;
	      return employee;
	   }


   public CalculateDto getPatientDto(String adm_cd) {
      CalculateDto dto = new CalculateDto();
      int patient = this.patientCal(adm_cd);
      dto.setAdm_cd(adm_cd);
      dto.setPredictPatient(patient);
      return dto;
   }
   
   public int areaSizeCal(int patient) {
	      int areaSize = 0;
//	      areaSize = (int) Math.round(((employee+1)*28.2)/3.3);
	      areaSize = (int) Math.round(((double)patient/30)*1.56);
	      if (areaSize < 15) {
	         areaSize = 15;
	      }
	      return areaSize;
	   }
}
