package com.ipad.dao.saleAnalysis;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.fasterxml.jackson.databind.JsonNode;

public class DetailDao {
	private Connection con;
	private PreparedStatement pstmt;
	private ResultSet resultSet;
	private DataSource dataSource;
	ArrayList<String> admCode = new ArrayList<String>();

	public DetailDao() {
		try {
			Context ctx = new InitialContext();
			Context envContext = (Context) ctx.lookup("java:/comp/env");
			dataSource = (DataSource) envContext.lookup("jdbc/Oracle11g");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	// Open API를 DB에 저장
	public void saveRecord(JsonNode record, String year, String option) throws SQLException {
		try {
			con = dataSource.getConnection();
			if (option.equals("0")) {
				String query = "INSERT INTO Detail_data (adm_cd, year, Population_total) VALUES (?,?,?)";
				pstmt = con.prepareStatement(query);
				pstmt.setString(1, record.get("adm_cd").asText());
				pstmt.setString(2, year);
				pstmt.setString(3, record.get("population").asText());
			} else if (option.equals("1")) {
				String query = "UPdate Detail_data SET Population_male = ? WHERE adm_cd=? and year=?";
				pstmt = con.prepareStatement(query);
				pstmt.setString(1, record.get("population").asText());
				pstmt.setString(2, record.get("adm_cd").asText());
				pstmt.setString(3, year);
			} else if (option.equals("2")) {
				String query = "UPdate Detail_data SET Population_female = ? WHERE adm_cd=? and year=?";
				pstmt = con.prepareStatement(query);
				pstmt.setString(1, record.get("population").asText());
				pstmt.setString(2, record.get("adm_cd").asText());
				pstmt.setString(3, year);
			} else if (option.equals("31")) {
				String query = "UPdate Detail_data SET Population_10 = ? WHERE adm_cd=? and year=?";
				pstmt = con.prepareStatement(query);
				pstmt.setString(1, record.get("population").asText());
				pstmt.setString(2, record.get("adm_cd").asText());
				pstmt.setString(3, year);
			} else if (option.equals("32")) {
				String query = "UPdate Detail_data SET Population_20 = ? WHERE adm_cd=? and year=?";
				pstmt = con.prepareStatement(query);
				pstmt.setString(1, record.get("population").asText());
				pstmt.setString(2, record.get("adm_cd").asText());
				pstmt.setString(3, year);
			} else if (option.equals("33")) {
				String query = "UPdate Detail_data SET Population_30 = ? WHERE adm_cd=? and year=?";
				pstmt = con.prepareStatement(query);
				pstmt.setString(1, record.get("population").asText());
				pstmt.setString(2, record.get("adm_cd").asText());
				pstmt.setString(3, year);
			} else if (option.equals("34")) {
				String query = "UPdate Detail_data SET Population_40 = ? WHERE adm_cd=? and year=?";
				pstmt = con.prepareStatement(query);
				pstmt.setString(1, record.get("population").asText());
				pstmt.setString(2, record.get("adm_cd").asText());
				pstmt.setString(3, year);
			} else if (option.equals("35")) {
				String query = "UPdate Detail_data SET Population_50 = ? WHERE adm_cd=? and year=?";
				pstmt = con.prepareStatement(query);
				pstmt.setString(1, record.get("population").asText());
				pstmt.setString(2, record.get("adm_cd").asText());
				pstmt.setString(3, year);
			} else if (option.equals("36")) {
				String query = "UPdate Detail_data SET Population_60 = ? WHERE adm_cd=? and year=?";
				pstmt = con.prepareStatement(query);
				pstmt.setString(1, record.get("population").asText());
				pstmt.setString(2, record.get("adm_cd").asText());
				pstmt.setString(3, year);
			} else if (option.equals("37")) {
				String query = "UPdate Detail_data SET Population_70 = ? WHERE adm_cd=? and year=?";
				pstmt = con.prepareStatement(query);
				pstmt.setString(1, record.get("population").asText());
				pstmt.setString(2, record.get("adm_cd").asText());
				pstmt.setString(3, year);
			}
			
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				pstmt.close();
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	public void saveBuildingRecord(JsonNode record, String year) {
		try {
			con = dataSource.getConnection();
			String query = "UPDATE DETAIL_DATA SET HOUSEHOLD = ? WHERE ADM_CD=? AND YEAR=?";
			pstmt = con.prepareStatement(query);
			
			pstmt.setString(1, record.get("house_cnt").asText());
			pstmt.setString(2, record.get("adm_cd").asText());
			pstmt.setString(3, year);
			pstmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				pstmt.close();
				con.close();
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	// DB에서 시군 이름 조회
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
	
	public void deleteEveryData() {
		try {
			con = dataSource.getConnection();
			String query = "DELETE FROM Detail_data";
			pstmt = con.prepareStatement(query);
			pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				pstmt.close();
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
}
