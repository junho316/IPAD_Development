package com.ipad.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.ipad.dto.OverlayDto;
import com.ipad.dto.RoundChartDto;
import com.ipad.dto.SimpleAsDto;

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

	public RoundChartDto totalFloat(String regionCode, String year) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = dataSource.getConnection();
			String query = "select sum(?),sum(?),sum(?),sum(?),sum(?),sum(?),sum(?),sum(?),sum(?) from float_table where adm_cd = ? and year like 'y2019%'";
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, "TOTAL_COL");
			pstmt.setString(2, "MAN_FLOAT");
			pstmt.setString(3, "woman_float");
			pstmt.setString(4, "float10");
			pstmt.setString(5, "float20");
			pstmt.setString(6, "float30");
			pstmt.setString(7, "float40");
			pstmt.setString(8, "float50");
			pstmt.setString(9, "float60");
			pstmt.setString(10, regionCode);
			pstmt.setString(11, year);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				RoundChartDto roundChartDto = new RoundChartDto();
				roundChartDto.setAdm_cd(regionCode);
				roundChartDto.setTotal_float(rs.getInt("sum(total_col)"));
				roundChartDto.setMan_float(rs.getInt("sum(man_float)"));
				roundChartDto.setWoman_float(rs.getInt("sum(woman_float"));
				roundChartDto.setFloat_ten(rs.getInt("sum(float10)"));
				roundChartDto.setFloat_twenty(rs.getInt("sum(float20)"));
				roundChartDto.setFloat_thirty(rs.getInt("sum(float30)"));
				roundChartDto.setFloat_forty(rs.getInt("sum(float40)"));
				roundChartDto.setFloat_fifty(rs.getInt("sum(float50)"));
				roundChartDto.setFloat_overSixty(rs.getInt("sum(float60)"));

				System.out.println(roundChartDto.getFloat_forty());
				return roundChartDto;
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
		return null;
	}

}
