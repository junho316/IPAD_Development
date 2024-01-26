package com.ipad.dao.saleAnalysis;

import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.ipad.dto.saleAnalysis.DiseaseDto;
import com.ipad.service.connectDB.ConnectDiseaseInfoAPIService;

public class DiseaseDao {
	private Connection con;
	private PreparedStatement pstmt;
	private DataSource dataSource;
	private ResultSet rs;
	
	public DiseaseDao() {
		try {
		Context context = new InitialContext();
		Context envContext =(Context) context.lookup("java:/comp/env");
		dataSource = (DataSource) envContext.lookup("jdbc/Oracle11g");
		} catch(Exception e) {
			e.printStackTrace();
		}
	}	
	
	public void insertDB() throws UnsupportedEncodingException {
		ConnectDiseaseInfoAPIService service = new ConnectDiseaseInfoAPIService();
		ArrayList<String> json = service.getAPIData();
		
//		for(int i=0; i<json.size();i++) {
//			DiseaseDto dto = new DiseaseDto();
//		}
		
	}
	
	public ArrayList<String> getDiseaseCode() {
		ArrayList<String> list = new ArrayList<>();
		try {
			con = dataSource.getConnection();
			String query = "select diseaseCode from diseaseCode";
			pstmt = con.prepareStatement(query);
			
			rs=pstmt.executeQuery();
			
			while(rs.next()) {
				DiseaseDto dto = new DiseaseDto();
				dto.setDiseaseCode(rs.getString("diseaseCode"));
				list.add(dto.getDiseaseCode());
			}			
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				pstmt.close();
				con.close();
			} catch(Exception e2) {
				e2.printStackTrace();
			}
		}
		return list;
	}
}
