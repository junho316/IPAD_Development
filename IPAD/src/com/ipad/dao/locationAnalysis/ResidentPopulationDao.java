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
import com.ipad.dto.locationAnalysis.ResidentPopulationDto;

import oracle.net.aso.i;

public class ResidentPopulationDao {

	private Connection con;
	private PreparedStatement pstmt;
	private ResultSet resultSet;
	private DataSource dataSource;
	ArrayList<String> admCode = new ArrayList<String>();

	public ResidentPopulationDao() {
		try {
			Context ctx = new InitialContext();
			Context envContext = (Context) ctx.lookup("java:/comp/env");
			dataSource = (DataSource) envContext.lookup("jdbc/Oracle11g");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 연령 인구
	public ArrayList<ResidentPopulationDto> getTotalPopulation() {
		ArrayList<ResidentPopulationDto> dtos = new ArrayList<ResidentPopulationDto>();
		try {
			con = dataSource.getConnection();
			String query = "SELECT r.region_name, p.population_total FROM region r JOIN resident_population p ON r.adm_cd = p.adm_cd WHERE p.year = (SELECT MAX(year) FROM resident_population)";
			pstmt = con.prepareStatement(query);
			resultSet = pstmt.executeQuery();
			while (resultSet.next()) {
				String region_name = resultSet.getString("region_name");
				String population_total = resultSet.getString("population_total");
				ResidentPopulationDto dto = new ResidentPopulationDto();
				dto.setRegion_name_detail(region_name);
				dto.setPopulation_total(population_total);
				dtos.add(dto);
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
		return dtos;
	}

	// 총인구getAgeGroup
	public ArrayList<ResidentPopulationDto> getAgeGroup() {
		ArrayList<ResidentPopulationDto> dtos = new ArrayList<ResidentPopulationDto>();
		try {
			con = dataSource.getConnection();
			String query = "SELECT r.region_name, p.population_10, p.population_20, p.population_30, p.population_40, p.population_50, p.population_60, p.population_70 FROM Region r JOIN Resident_Population p ON r.adm_cd = p.adm_cd WHERE p.year = (SELECT MAX(year) FROM Resident_Population)";
			pstmt = con.prepareStatement(query);
			resultSet = pstmt.executeQuery();
			while (resultSet.next()) {
				String region_name = resultSet.getString("region_name");
				String population_10 = resultSet.getString("population_10");
				String population_20 = resultSet.getString("population_20");
				String population_30 = resultSet.getString("population_30");
				String population_40 = resultSet.getString("population_40");
				String population_50 = resultSet.getString("population_50");
				String population_60 = resultSet.getString("population_60");
				String population_70 = resultSet.getString("population_70");
				ResidentPopulationDto dto = new ResidentPopulationDto();
				dto.setRegion_name_detail(region_name);
				dto.setPopulation_10(population_10);
				dto.setPopulation_20(population_20);
				dto.setPopulation_30(population_30);
				dto.setPopulation_40(population_40);
				dto.setPopulation_50(population_50);
				dto.setPopulation_60(population_60);
				dto.setPopulation_70(population_70);
				dtos.add(dto);
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
		return dtos;
	}

	// 주거인구 데이터
	public ArrayList<ResidentPopulationDto> selectPopulationData(String admCd) {
		ArrayList<ResidentPopulationDto> dtos = new ArrayList<ResidentPopulationDto>();
		try {
			con = dataSource.getConnection();
			String query = "SELECT r.*, region.region_name_detail FROM ( SELECT * FROM ( SELECT * FROM resident_population WHERE adm_cd = ? ORDER BY year DESC ) r WHERE ROWNUM <= 5 ) r JOIN region ON r.adm_cd = region.adm_cd ORDER BY r.year ASC";
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, admCd);

			resultSet = pstmt.executeQuery();
			while (resultSet.next()) {
				String adm_cd = resultSet.getString("adm_cd");
				String year = resultSet.getString("year");
				String population_total = resultSet.getString("population_total");
				String population_male = resultSet.getString("population_male");
				String population_female = resultSet.getString("population_female");
				String population_10 = resultSet.getString("population_10");
				String population_20 = resultSet.getString("population_20");
				String population_30 = resultSet.getString("population_30");
				String population_40 = resultSet.getString("population_40");
				String population_50 = resultSet.getString("population_50");
				String population_60 = resultSet.getString("population_60");
				String population_70 = resultSet.getString("population_70");
				String region_name_detail = resultSet.getString("region_name_detail");
				ResidentPopulationDto dto = new ResidentPopulationDto(adm_cd, year, population_total, population_male,
						population_female, population_10, population_20, population_30, population_40, population_50,
						population_60, population_70, region_name_detail);
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
	public void saveRecord(JsonNode record, String year, String option) throws SQLException {
		try {
			con = dataSource.getConnection();
			if (option.equals("0")) {
				String query = "INSERT INTO Resident_Population (adm_cd, year, Population_total) VALUES (?,?,?)";
				pstmt = con.prepareStatement(query);
				pstmt.setString(1, record.get("adm_cd").asText());
				pstmt.setString(2, year);
				pstmt.setString(3, record.get("population").asText());
			} else if (option.equals("1")) {
				String query = "UPdate Resident_Population SET Population_male = ? WHERE adm_cd=? and year=?";
				pstmt = con.prepareStatement(query);
				pstmt.setString(1, record.get("population").asText());
				pstmt.setString(2, record.get("adm_cd").asText());
				pstmt.setString(3, year);
			} else if (option.equals("2")) {
				String query = "UPdate Resident_Population SET Population_female = ? WHERE adm_cd=? and year=?";
				pstmt = con.prepareStatement(query);
				pstmt.setString(1, record.get("population").asText());
				pstmt.setString(2, record.get("adm_cd").asText());
				pstmt.setString(3, year);
			} else if (option.equals("31")) {
				String query = "UPdate Resident_Population SET Population_10 = ? WHERE adm_cd=? and year=?";
				pstmt = con.prepareStatement(query);
				pstmt.setString(1, record.get("population").asText());
				pstmt.setString(2, record.get("adm_cd").asText());
				pstmt.setString(3, year);
			} else if (option.equals("32")) {
				String query = "UPdate Resident_Population SET Population_20 = ? WHERE adm_cd=? and year=?";
				pstmt = con.prepareStatement(query);
				pstmt.setString(1, record.get("population").asText());
				pstmt.setString(2, record.get("adm_cd").asText());
				pstmt.setString(3, year);
			} else if (option.equals("33")) {
				String query = "UPdate Resident_Population SET Population_30 = ? WHERE adm_cd=? and year=?";
				pstmt = con.prepareStatement(query);
				pstmt.setString(1, record.get("population").asText());
				pstmt.setString(2, record.get("adm_cd").asText());
				pstmt.setString(3, year);
			} else if (option.equals("34")) {
				String query = "UPdate Resident_Population SET Population_40 = ? WHERE adm_cd=? and year=?";
				pstmt = con.prepareStatement(query);
				pstmt.setString(1, record.get("population").asText());
				pstmt.setString(2, record.get("adm_cd").asText());
				pstmt.setString(3, year);
			} else if (option.equals("35")) {
				String query = "UPdate Resident_Population SET Population_50 = ? WHERE adm_cd=? and year=?";
				pstmt = con.prepareStatement(query);
				pstmt.setString(1, record.get("population").asText());
				pstmt.setString(2, record.get("adm_cd").asText());
				pstmt.setString(3, year);
			} else if (option.equals("36")) {
				String query = "UPdate Resident_Population SET Population_60 = ? WHERE adm_cd=? and year=?";
				pstmt = con.prepareStatement(query);
				pstmt.setString(1, record.get("population").asText());
				pstmt.setString(2, record.get("adm_cd").asText());
				pstmt.setString(3, year);
			} else if (option.equals("37")) {
				String query = "UPdate Resident_Population SET Population_70 = ? WHERE adm_cd=? and year=?";
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

	// DB에서 시군 cd 조회
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

	public void UpdateData(JsonNode record, String year, String option) throws SQLException {
		try {
			con = dataSource.getConnection();
			if (option.equals("0")) {
				String query = "UPDATE Resident_Population SET Population_total = ? WHERE adm_cd=? and year=? ";
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

	public String getResentYear() {
		String year = null;
		try {
			con = dataSource.getConnection();
			String query = "SELECT MAX(year) AS year FROM resident_population";
			pstmt = con.prepareStatement(query);
			resultSet = pstmt.executeQuery();
			while (resultSet.next()) {
				year = resultSet.getString("year");
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
		return year;
	}
}
