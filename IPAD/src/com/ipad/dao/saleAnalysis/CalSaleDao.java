package com.ipad.dao.saleAnalysis;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class CalSaleDao {
	private DataSource dataSource;
	Connection con;
	PreparedStatement pstmt;
	ResultSet rs,calRs;
	
	public CalSaleDao() {
		try {
			Context ctx = new InitialContext();
			dataSource = (DataSource) ctx.lookup("java:comp/env/jdbc/Oracle11g");
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public String calculateSale(String adm_cd) {
		
		
		int sale = 0;
		try {
			con = dataSource.getConnection();
			String query = "select twenties, thirties, sixties, over70s, floatPp, income, dentalClinic, subway from region_data where adm_cd=?";
			
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, adm_cd);
			rs = pstmt.executeQuery();
			
			String query2 = "select * from sale_point";
			pstmt = con.prepareStatement(query2);
			calRs = pstmt.executeQuery();
			while(rs.next()) {
				while(calRs.next()) {
					if(rs.getInt("subway") != 0) {
						sale = (int) Math.round(((calRs.getInt("constant")
								+ Math.log10(rs.getInt("twenties")) * calRs.getInt("population_20_point") 
								+ Math.log10(rs.getInt("thirties")) * calRs.getInt("population_30_point")
								+ Math.log10(rs.getInt("sixties")) * calRs.getInt("population_60_point")
								+ Math.log10(rs.getInt("over70s")) * calRs.getInt("population_over70_point")
								+ Math.log10(rs.getInt("floatPp")) * calRs.getInt("floatPp_point")
								+ Math.log10(rs.getInt("income")) * calRs.getInt("income_point")
								+ Math.log10(rs.getInt("dentalClinic")) * calRs.getInt("dentalClinic_point")
								+ Math.log10(rs.getInt("subway")) * calRs.getInt("subway_point"))
								/ (rs.getInt("dentalClinic") + 1)));
					} else {
						sale = (int) Math.round(((calRs.getInt("constant")
								+ Math.log10(rs.getInt("twenties")) * calRs.getInt("population_20_point")
								+ Math.log10(rs.getInt("thirties")) * calRs.getInt("population_30_point")
								+ Math.log10(rs.getInt("sixties")) * calRs.getInt("population_60_point")
								+ Math.log10(rs.getInt("over70s")) * calRs.getInt("population_over70_point")
								+ Math.log10(rs.getInt("floatPp")) * calRs.getInt("floatPp_point")
								+ Math.log10(rs.getInt("income")) * calRs.getInt("income_point")
								+ Math.log10(rs.getInt("dentalClinic")) * calRs.getInt("dentalClinic_point")
								+ rs.getInt("subway") * calRs.getInt("subway_point")) 
								/ (rs.getInt("dentalClinic") +1)));
					}
				}
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(rs != null) rs.close();
				if(calRs != null) calRs.close();
				if(pstmt != null) pstmt.close();
				if(con != null) con.close();
			} catch(Exception e2) {
				e2.printStackTrace();
			}
		}
		
		String calSale = Integer.toString(sale);
		return calSale;
	}
}
