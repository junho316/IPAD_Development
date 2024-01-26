package com.ipad.dao.locationAnalysis;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.fasterxml.jackson.databind.JsonNode;
import com.ipad.dto.locationAnalysis.HospitalCountDto;
import com.ipad.dto.locationAnalysis.HospitalDto;

public class HospitalDao {
	private Connection con;
	private PreparedStatement pstmt;
	private ResultSet resultSet;
	private DataSource dataSource;
	private ArrayList<String> sigunName = new ArrayList<String>();

	public HospitalDao() {
		try {
			Context ctx = new InitialContext();
			Context envContext = (Context) ctx.lookup("java:/comp/env");
			dataSource = (DataSource) envContext.lookup("jdbc/Oracle11g");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 년도별 병원의 수
	public ArrayList<HospitalCountDto> getHospitalCount(int year) {
		ArrayList<HospitalCountDto> dtos = new ArrayList<HospitalCountDto>();
		try {
			con = dataSource.getConnection();
			String query = "SELECT region, COALESCE(SUM(CASE WHEN license_date <= TO_DATE(?, 'YYYY') AND business_status = '영업/정상' THEN 1 ELSE 0 END), 0) AS count FROM hospital GROUP BY region";
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, year);
			resultSet = pstmt.executeQuery();
			while (resultSet.next()) {
				String region = resultSet.getString("region");
				String count = resultSet.getString("count");
				HospitalCountDto dto = new HospitalCountDto(String.valueOf(year), region, count);
				dtos.add(dto);
			}
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
		return dtos;
	}

	// 차트용 데이터
	public ArrayList<HospitalDto> getHospitalData() {
		ArrayList<HospitalDto> dtos = new ArrayList<HospitalDto>();
		try {
			con = dataSource.getConnection();
			String query = "select hospital_name, region , address, x_coordinate, y_coordinate, business_status from hospital";
			pstmt = con.prepareStatement(query);
			resultSet = pstmt.executeQuery();
			while (resultSet.next()) {
				String hospital_name = resultSet.getString("hospital_name");
				String region = resultSet.getString("region");
				String address = resultSet.getString("address");
				String business_status = resultSet.getString("business_status");
				float x_coordinate = resultSet.getFloat("x_coordinate");
				float y_coordinate = resultSet.getFloat("y_coordinate");
				HospitalDto dto = new HospitalDto(hospital_name, region, address, business_status, x_coordinate,
						y_coordinate);
				dtos.add(dto);
			}
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
		return dtos;
	}

	// Open API를 DB에 저장
	public void saveRecord(JsonNode record) throws SQLException {
		try {
			con = dataSource.getConnection();
			String query = "INSERT INTO Hospital (region, hospital_name, license_date, business_status, address, close_date,x_coordinate, y_coordinate) VALUES (?,?,?,?,?,?,?,?)";

			if (record.get("REFINE_ROADNM_ADDR").asText().contains("위례")) {
				pstmt = con.prepareStatement(query);
				pstmt.setString(1, record.get("SIGUN_NM").asText());
				pstmt.setString(2, record.get("BIZPLC_NM").asText());
				pstmt.setString(3, record.get("LICENSG_DE").asText());
				pstmt.setString(4, record.get("BSN_STATE_NM").asText());
				pstmt.setString(5, record.get("REFINE_ROADNM_ADDR").asText());
				if (record.get("CLSBIZ_DE").asText() != "null") {
					pstmt.setString(6, record.get("CLSBIZ_DE").asText());
				} else {
					pstmt.setString(6, null);
				}
				pstmt.setString(7, record.get("REFINE_WGS84_LAT").asText());
				pstmt.setString(8, record.get("REFINE_WGS84_LOGT").asText());
				pstmt.executeUpdate();
			}

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

	// DB에서 시군 이름 조회
	public ArrayList<String> getSigunNm() {
		try {
			con = dataSource.getConnection();
			String query = "Select region_name from region where region_name != '송파구'";
			pstmt = con.prepareStatement(query);
			resultSet = pstmt.executeQuery();
			while (resultSet.next()) {
				sigunName.add(resultSet.getString("region_name"));
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
		return sigunName;
	}

	public void updateData(JsonNode record) throws SQLException {
		try {
			con = dataSource.getConnection();
			String query = "UPDATE Hospital SET region = ?, business_status = ?, address = ?, close_date = ?, x_coordinate = ?, y_coordinate = ? WHERE hospital_name=? AND license_date=?";

			if (record.get("REFINE_ROADNM_ADDR").asText().contains("위례")) {
				pstmt = con.prepareStatement(query);
				pstmt.setString(1, record.get("SIGUN_NM").asText());
				pstmt.setString(2, record.get("BSN_STATE_NM").asText());
				pstmt.setString(3, record.get("REFINE_ROADNM_ADDR").asText());
				if (record.get("CLSBIZ_DE").asText() != "null") {
					pstmt.setString(4, record.get("CLSBIZ_DE").asText());
				} else {
					pstmt.setString(4, null);
				}
				pstmt.setString(5, record.get("REFINE_WGS84_LAT").asText());
				pstmt.setString(6, record.get("REFINE_WGS84_LOGT").asText());
				pstmt.setString(7, record.get("BIZPLC_NM").asText());
				pstmt.setString(8, record.get("LICENSG_DE").asText());
				pstmt.executeUpdate();
			}

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
