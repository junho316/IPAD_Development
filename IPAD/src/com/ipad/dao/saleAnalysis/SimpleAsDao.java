package com.ipad.dao.saleAnalysis;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.ipad.dto.saleAnalysis.OverlayDto;
import com.ipad.dto.saleAnalysis.SimpleAsDto;

public class SimpleAsDao {
	private DataSource dataSource;
	

	public SimpleAsDao() {
		try {
			Context ctx = new InitialContext();
			dataSource = (DataSource) ctx.lookup("java:comp/env/jdbc/Oracle11g");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("생성자 오류");
		}
	}

	public ArrayList<SimpleAsDto> list(String adm_cd) {
		System.out.println("DAO LiST 입장 ========================");
		System.out.println("DAO 리전코드 : " + adm_cd + "=====================");
		ArrayList<SimpleAsDto> dtos = new ArrayList<>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = dataSource.getConnection();

			String query = "select population,float_pp,countclinic from s_chart_data where adm_cd=?";

			pstmt = con.prepareStatement(query);
			pstmt.setString(1, adm_cd);
			rs = pstmt.executeQuery();


			while (rs.next()) {

				int population = rs.getInt("population");
				int float_pp = rs.getInt("float_pp");
				int countClinic = rs.getInt("countclinic");
				
				SimpleAsDto simpleAsDto = new SimpleAsDto(population, float_pp, countClinic);
				dtos.add(simpleAsDto);
				
			}

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("dataList 삽입 오류");
		} finally {

			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (con != null)
					con.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return dtos;
	}

	public ArrayList<OverlayDto> overlayList() {
		ArrayList<OverlayDto> dtos = new ArrayList<>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = dataSource.getConnection();
			String query = "select name,lat,lng from overlay";
			pstmt = con.prepareStatement(query);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				String name = rs.getString("name");
				double lat = rs.getDouble("lat");
				double lng = rs.getDouble("lng");

				OverlayDto overlayDto = new OverlayDto(name, lat, lng);
				dtos.add(overlayDto);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (con != null)
					con.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}

		return dtos;
	}
}
