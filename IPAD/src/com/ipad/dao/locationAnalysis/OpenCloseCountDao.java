package com.ipad.dao.locationAnalysis;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.ipad.dto.locationAnalysis.OpenCloseCountDto;

public class OpenCloseCountDao {
	private Connection con;
	private PreparedStatement pstmt;
	private ResultSet resultSet;
	private DataSource dataSource;

	public OpenCloseCountDao() {
		try {
			Context ctx = new InitialContext();
			Context envContext = (Context) ctx.lookup("java:/comp/env");
			dataSource = (DataSource) envContext.lookup("jdbc/Oracle11g");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 개업 폐업 데이터
	public ArrayList<OpenCloseCountDto> getOpenData() {
		ArrayList<OpenCloseCountDto> dtos = new ArrayList<OpenCloseCountDto>();
		try {
			con = dataSource.getConnection();
			String query = "SELECT * FROM ( SELECT * FROM openclose_count ORDER BY year DESC) WHERE ROWNUM <= 4 order by year asc";
			pstmt = con.prepareStatement(query);
			resultSet = pstmt.executeQuery();
			while (resultSet.next()) {
				int year = resultSet.getInt("year");
				int openings = resultSet.getInt("openings");
				int closures = resultSet.getInt("closures");
				int count = resultSet.getInt("count");
				OpenCloseCountDto dto = new OpenCloseCountDto(year, openings, closures, count);
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

	public void saveOpenData(int year, int count) {
		try {
			con = dataSource.getConnection();
			String query = "insert into openclose_count (year,openings) values(?,?) ";
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, year);
			pstmt.setInt(2, count);
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

	public void updateOpenData(int year, int count) {
		try {
			con = dataSource.getConnection();
			String query = "UPDATE openclose_count SET openings = ? WHERE year = ?";
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, count);
			pstmt.setInt(2, year);
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

	public void saveCloseData(int year, int count) {
		try {
			con = dataSource.getConnection();
			String query = "UPDATE openclose_count SET closures = ? WHERE year = ?";
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, count);
			pstmt.setInt(2, year);
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

	public void saveHospitalCountData(int year, int count) {
		try {
			con = dataSource.getConnection();
			String query = "UPDATE openclose_count SET count = ? WHERE year = ?";
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, count);
			pstmt.setInt(2, year);
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

	public int getOpenData(int year) {
		int data = 0;
		try {
			con = dataSource.getConnection();
			String query = "SELECT COUNT(*) FROM hospital WHERE license_date BETWEEN TO_DATE(?, 'YYYY') AND TO_DATE(?, 'YYYY')";
			pstmt = con.prepareStatement(query);
			pstmt.setLong(1, year);
			pstmt.setLong(2, year + 1);

			resultSet = pstmt.executeQuery();
			while (resultSet.next()) {
				data = resultSet.getInt("count(*)");
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

		return data;
	}

	public int getCloseData(int year) {
		int data = 0;
		try {
			con = dataSource.getConnection();
			String query = "SELECT COUNT(*) FROM hospital WHERE close_date BETWEEN TO_DATE(?, 'YYYY') AND TO_DATE(?, 'YYYY')";
			pstmt = con.prepareStatement(query);
			pstmt.setLong(1, year);
			pstmt.setLong(2, year + 1);

			resultSet = pstmt.executeQuery();
			while (resultSet.next()) {
				data = resultSet.getInt("count(*)");
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

		return data;
	}

	public int getHospitalCount(int year) {
		int data = 0;
		try {
			con = dataSource.getConnection();
			String query = "SELECT COUNT(*) FROM hospital WHERE license_date <= TO_DATE(?, 'YYYY') AND business_status ='영업/정상'";
			pstmt = con.prepareStatement(query);
			pstmt.setLong(1, year);

			resultSet = pstmt.executeQuery();
			while (resultSet.next()) {
				data = resultSet.getInt("count(*)");
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

		return data;
	}
}
