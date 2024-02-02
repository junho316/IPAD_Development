package com.ipad.dao.saleAnalysis;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class CalNetProfitDao {

	private DataSource dataSource;
	Connection con;
	PreparedStatement pstmt;
	ResultSet rs;

	public CalNetProfitDao() {
		try {
			Context ctx = new InitialContext();
			dataSource = (DataSource) ctx.lookup("java:comp/env/jdbc/Oracle11g");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public int CalEmploymentAvgFee(String adm_cd) {
		int employmentFee =0;
		try {
			con=dataSource.getConnection();
			String query = "select average from employmentFee where adm_cd = ?";
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, adm_cd);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				employmentFee = (int) Math.round((double)rs.getInt("average")/12 * 10000);
			}
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				pstmt.close();
				con.close();
			} catch(Exception e2) {
				e2.printStackTrace();
			}
		}
		
		return employmentFee;
	}

	public int CalRentFee(String adm_cd, String areaSize) {
		int rentFee = 0;
		int size = Integer.parseInt(areaSize);

		try {
			con = dataSource.getConnection();
			String query = "SELECT rent_per FROM rentfee WHERE adm_cd=?";

			pstmt = con.prepareStatement(query);
			pstmt.setString(1, adm_cd);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				rentFee = rs.getInt("rent_per") * size;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) rs.close();
				if (pstmt != null) pstmt.close();
				if (con != null) con.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return rentFee;
	}
	
	public ArrayList<Integer> CalEmploymentFee(String adm_cd, String seniorEmployeeCount, String juniorEmployeeCount) {
		ArrayList<Integer> empArray = new ArrayList<>();
		
		int employmentFee = 0;
		int seniorEmploymentFee = 0;
		int juniorEmploymentFee = 0;
		int seniorCount = Integer.parseInt(seniorEmployeeCount);
		int juniorCount = Integer.parseInt(juniorEmployeeCount);
		
		try {
			con = dataSource.getConnection();
			String query = "SELECT under_three_year, over_ten_year FROM employmentFee WHERE adm_cd=?";
			
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, adm_cd);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				seniorEmploymentFee = (int) Math.round((double)rs.getInt("over_ten_year")/12 * 10000 * seniorCount);
				juniorEmploymentFee = (int) Math.round((double)rs.getInt("under_three_year")/12 * 10000 * juniorCount);
				employmentFee = seniorEmploymentFee + juniorEmploymentFee;
				
				empArray.add(employmentFee);
				empArray.add(seniorEmploymentFee);
				empArray.add(juniorEmploymentFee);
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(rs != null) rs.close();
				if(pstmt != null) pstmt.close();
				if(con != null) con.close();
			} catch(Exception e2) {
				e2.printStackTrace();
			}
		}
		
		return empArray;
	}
}
