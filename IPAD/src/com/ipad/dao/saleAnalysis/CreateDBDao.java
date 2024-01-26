package com.ipad.dao.saleAnalysis;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class CreateDBDao {

	private Connection con;
	private DataSource dataSource;
	private PreparedStatement pstmt;
	private ResultSet rs;

	public CreateDBDao() {
		try {
			Context context = new InitialContext();
			Context envContext = (Context) context.lookup("java:/comp/env");
			dataSource = (DataSource) envContext.lookup("jdbc/Oracle11g");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void createDataDB() {
		try {
			con = dataSource.getConnection();
			String query = "create table data(\r\n" + "adm_cd varchar(20),\r\n" + "population number,\r\n"
					+ "under10s number,\r\n" + "teens number,\r\n" + "twenties number,\r\n" + "thirties number,\r\n"
					+ "forties number,\r\n" + "fifties number,\r\n" + "sixties number,\r\n" + "over70s number,\r\n"
					+ "household number,\r\n" + "houseprice number,\r\n" + "dentalclinic number,\r\n"
					+ "income number,\r\n" + "subway number,\r\n" + "bus number,\r\n" + "resident number,\r\n"
					+ "floatpp number,\r\n" + "sale number,\r\n" + "foreign key (adm_cd) references region(adm_cd)\r\n"
					+ ");";

			con.prepareStatement(query);
			pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public int checkDataDB() {
		int checkNum = 0;
		try {
			con = dataSource.getConnection();
			String query = "select count(*) from all_tables where table_name = 'DATA';";
			con.prepareStatement(query);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				checkNum = 1;
			} else {
				checkNum = 0;
			}

		} catch (Exception e) {
			e.printStackTrace();

		}
		return checkNum;
	}

	public int checkRegionDB() {
		int checkNum = 0;
		try {
			con = dataSource.getConnection();
			String query = "select count(*) from all_tables where table_name = 'REGION';";
			con.prepareStatement(query);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				checkNum = 1;
			} else {
				checkNum = 0;
			}

		} catch (Exception e) {
			e.printStackTrace();

		}
		return checkNum;
	}

	public void createRegionDB() {
		try {
			con = dataSource.getConnection();
			String query = "CREATE TABLE Region (\r\n" + 
					"    adm_cd VARCHAR2(30) not null PRIMARY KEY,\r\n" + 
					"    region_name VARCHAR2(20) not null UNIQUE\r\n" + 
					");\r\n" + 
					"insert into region values('11240820', '송파구');\r\n" + 
					"insert into region values('31021680', '성남시');\r\n" + 
					"insert into region values('31180650', '하남시');";

			con.prepareStatement(query);
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
