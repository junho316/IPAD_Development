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
import com.ipad.dto.locationAnalysis.FootTrafficDto;
import com.ipad.dto.locationAnalysis.ResidentPopulationDto;

public class FootTrafficDao {
	private Connection con;
	private PreparedStatement pstmt;
	private ResultSet resultSet;
	private DataSource dataSource;

	public FootTrafficDao() {
		try {
			Context ctx = new InitialContext();
			Context envContext = (Context) ctx.lookup("java:/comp/env");
			dataSource = (DataSource) envContext.lookup("jdbc/Oracle11g");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public ArrayList<FootTrafficDto> selectFootTrafficData() {
		ArrayList<FootTrafficDto> dtos = new ArrayList<FootTrafficDto>();
		try {
			con = dataSource.getConnection();
			String query = "SELECT ft.*, region.region_name_detail FROM ( SELECT * FROM ( SELECT * FROM Foot_Traffic WHERE adm_cd = '11240820' ORDER BY quater DESC ) ft WHERE ROWNUM <= 7 ) ft JOIN region ON ft.adm_cd = region.adm_cd ORDER BY ft.quater ASC";
			pstmt = con.prepareStatement(query);

			resultSet = pstmt.executeQuery();
			while (resultSet.next()) {
				String adm_cd = resultSet.getString("adm_cd");
				String quater = transformString(resultSet.getString("quater"));
				String population_total = String
						.valueOf((int) Double.parseDouble(resultSet.getString("population_total")));
				String population_mon = String.valueOf((int) Double.parseDouble(resultSet.getString("population_mon")));
				String population_tues = String
						.valueOf((int) Double.parseDouble(resultSet.getString("population_tues")));
				String population_wed = String.valueOf((int) Double.parseDouble(resultSet.getString("population_wed")));
				String population_thur = String
						.valueOf((int) Double.parseDouble(resultSet.getString("population_thur")));
				String population_fri = String.valueOf((int) Double.parseDouble(resultSet.getString("population_fri")));
				String population_sat = String.valueOf((int) Double.parseDouble(resultSet.getString("population_sat")));
				String population_sun = String.valueOf((int) Double.parseDouble(resultSet.getString("population_sun")));
				String region_name_detail = resultSet.getString("region_name_detail");
				FootTrafficDto dto = new FootTrafficDto(adm_cd, quater, population_total, population_mon,
						population_tues, population_wed, population_thur, population_fri, population_sat,
						population_sun, region_name_detail);
				dtos.add(dto);
			}
		} catch (

		Exception e) {
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
			String query = "INSERT INTO FOOT_TRAFFIC (ADM_CD, QUATER, POPULATION_TOTAL, POPULATION_MON, POPULATION_TUES, POPULATION_WED, POPULATION_THUR, POPULATION_FRI, POPULATION_SAT, POPULATION_SUN) VALUES (?,?,?,?,?,?,?,?,?,?)";
			pstmt = con.prepareStatement(query);
			if (record.get("ADSTRD_CD_NM").asText().equals("위례동")) {
				pstmt.setString(1, "11240820");
				pstmt.setString(2, record.get("STDR_YYQU_CD").asText());
				pstmt.setString(3, record.get("TOT_FLPOP_CO").asText());
				pstmt.setString(4, record.get("MON_FLPOP_CO").asText());
				pstmt.setString(5, record.get("TUES_FLPOP_CO").asText());
				pstmt.setString(6, record.get("WED_FLPOP_CO").asText());
				pstmt.setString(7, record.get("THUR_FLPOP_CO").asText());
				pstmt.setString(8, record.get("FRI_FLPOP_CO").asText());
				pstmt.setString(9, record.get("SAT_FLPOP_CO").asText());
				pstmt.setString(10, record.get("SUN_FLPOP_CO").asText());
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

	public void updateDate(JsonNode record) throws SQLException {
		try {
			con = dataSource.getConnection();
			String query = "UPDATE FOOT_TRAFFIC SET POPULATION_TOTAL = ?, POPULATION_MON = ?, POPULATION_TUES = ?, POPULATION_WED = ?, POPULATION_THUR = ?, POPULATION_FRI = ?, POPULATION_SAT = ?, POPULATION_SUN = ? WHERE ADM_CD=? AND QUATER=?";
			pstmt = con.prepareStatement(query);
			if (record.get("ADSTRD_CD_NM").asText().equals("위례동")) {
				pstmt.setString(1, record.get("TOT_FLPOP_CO").asText());
				pstmt.setString(2, record.get("MON_FLPOP_CO").asText());
				pstmt.setString(3, record.get("TUES_FLPOP_CO").asText());
				pstmt.setString(4, record.get("WED_FLPOP_CO").asText());
				pstmt.setString(5, record.get("THUR_FLPOP_CO").asText());
				pstmt.setString(6, record.get("FRI_FLPOP_CO").asText());
				pstmt.setString(7, record.get("SAT_FLPOP_CO").asText());
				pstmt.setString(8, record.get("SUN_FLPOP_CO").asText());
				pstmt.setString(9, "11240820");
				pstmt.setString(10, record.get("STDR_YYQU_CD").asText());
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

	private static String transformString(String originalString) {
		String year = originalString.substring(0, 4);
		String quarter = originalString.substring(4);
		return year + "년 " + quarter + "분기";
	}
}
