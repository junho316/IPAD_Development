package com.ipad.dao.locationAnalysis;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.ipad.dto.locationAnalysis.PopulationForecastDto;

public class PopulationForecastDao {
	private Connection con;
	private PreparedStatement pstmt;
	private ResultSet resultSet;
	private DataSource dataSource;

	public PopulationForecastDao() {
		try {
			Context ctx = new InitialContext();
			Context envContext = (Context) ctx.lookup("java:/comp/env");
			dataSource = (DataSource) envContext.lookup("jdbc/Oracle11g");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public ArrayList<PopulationForecastDto> getPopulation() {
		ArrayList<PopulationForecastDto> dtos = new ArrayList<PopulationForecastDto>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet resultSet = null;

		try {
			con = dataSource.getConnection();
			String query = "SELECT region, year, population FROM Population_forecast ORDER BY region ASC, year ASC";
			pstmt = con.prepareStatement(query);
			resultSet = pstmt.executeQuery();
			while (resultSet.next()) {
				int population = resultSet.getInt("population");
				int year = resultSet.getInt("year");
				String region = resultSet.getString("region");
				PopulationForecastDto dto = new PopulationForecastDto();
				dto.setPopulation(population);
				dto.setYear(year);
				dto.setRegion(region);
				dtos.add(dto);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (resultSet != null) {
					resultSet.close();
				}
				if (pstmt != null) {
					pstmt.close();
				}
				if (con != null) {
					con.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return dtos;
	}

	public void saveData(PopulationForecastDto dto) {
		int numberHouse = 0;
		try {
			con = dataSource.getConnection();
			String query = "insert into Population_forecast (year, region, population, birth, death,number_house,family) values(?,?,?,?,?,?,?)";
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, dto.getYear());
			pstmt.setString(2, dto.getRegion());
			pstmt.setInt(3, dto.getPopulation());
			pstmt.setInt(4, dto.getBirth());
			pstmt.setInt(5, dto.getDeath());
			pstmt.setInt(6, dto.getNumberHouse());
			pstmt.setInt(7, dto.getFamily());
			pstmt.executeUpdate();

		} catch (SQLException e) {
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

	public int getNumberHouse(String region) {
		int numberHouse = 0;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet resultSet = null;

		try {
			con = dataSource.getConnection();
			String query = "SELECT number_house FROM Population_forecast WHERE year = 2022 AND region = ?";
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, region);
			resultSet = pstmt.executeQuery();
			while (resultSet.next()) {
				numberHouse = resultSet.getInt("number_house");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (resultSet != null) {
					resultSet.close();
				}
				if (pstmt != null) {
					pstmt.close();
				}
				if (con != null) {
					con.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return numberHouse;
	}

	public int getFamily(String region) {
		int family = 0;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet resultSet = null;

		try {
			con = dataSource.getConnection();
			String query = "SELECT family FROM Population_forecast WHERE year = 2022 AND region = ?";
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, region);
			resultSet = pstmt.executeQuery();
			while (resultSet.next()) {
				family = resultSet.getInt("family");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (resultSet != null) {
					resultSet.close();
				}
				if (pstmt != null) {
					pstmt.close();
				}
				if (con != null) {
					con.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return family;
	}
}
