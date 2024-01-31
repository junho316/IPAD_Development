package com.ipad.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.ipad.dto.OptionDto;

public class OptionDao {
	Connection con;
	PreparedStatement pstmt;
	DataSource dataSource;
	ResultSet rs;

	public OptionDao() {
		try {
			Context context = new InitialContext();
			Context envContext = (Context) context.lookup("java:/comp/env");
			dataSource = (DataSource) envContext.lookup("jdbc/Oracle11g");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public int avgData(String data) {
		int average = 0;
		try {
			con = dataSource.getConnection();
			String query = "select avg(" + data + ") from region_data";
			pstmt = con.prepareStatement(query);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				average = rs.getInt("avg(" + data + ")");
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				pstmt.close();
				con.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return average;
	}

	public ArrayList<Integer> getSale(ArrayList<OptionDto> dto) {
		ArrayList<Integer> dataList = new ArrayList<>();
		for(int i=0; i<dto.size();i++) {
		dto.get(i).getSale();
		}
		return dataList;
	}
	public ArrayList<Integer> getTwenties(ArrayList<OptionDto> dto) {
		ArrayList<Integer> dataList = new ArrayList<>();
		for(int i=0; i<dto.size();i++) {
		dto.get(i).getTwenties();
		}
		return dataList;
	}
	public ArrayList<Integer> getSixties(ArrayList<OptionDto> dto) {
		ArrayList<Integer> dataList = new ArrayList<>();
		for(int i=0; i<dto.size();i++) {
		dto.get(i).getSixties();
		}
		return dataList;
	}

//	public double standardDeviation(ArrayList<Integer> dataList, String data) {
//		double sum = 0;
//		for (int i = 0; i < dataList.size(); i++) {
//			sum = sum + Math.pow((dataList.get(i) - avgData(data)), 2);
//		}
//
//		double variance = sum / dataList.size();
//		double standardDeviation = Math.sqrt(variance);
//		return standardDeviation;
//	}

	public void setSaleScore(OptionDto dto) {
		double score = 0;
		score = (dto.getSale()-minData("sale"))*10/(maxData("sale")-minData("sale"));		
		dto.setSaleScore(score);
	}
	public void setTwentiesScore(OptionDto dto) {
		double score = 0;
		score = (dto.getTwenties()-minData("twenties"))*10/(maxData("twenties")-minData("twenties"));		
		dto.setTwentiesScore(score);
	}
	public void setSixtiesScore(OptionDto dto) {
		double score = 0;
		score = (dto.getSixties()-minData("sixties"))*10/(maxData("sixties")-minData("sixties"));		
		dto.setSixtiesScore(score);
	}
	public void setTotalScore(OptionDto dto,String opt1, String opt2) {
		if(opt1!=null && opt2!=null) {
			dto.setTotalScore(dto.getSaleScore()*6+dto.getTwentiesScore()*2+dto.getSixtiesScore()*2);	
		} else if(opt1==null && opt2!=null) {
			dto.setTotalScore(dto.getSaleScore()*6+dto.getTwentiesScore()*4+dto.getSixtiesScore()*0);
		} else if(opt1!=null && opt2==null) {
			dto.setTotalScore(dto.getSaleScore()*6+dto.getTwentiesScore()*0+dto.getSixtiesScore()*4);
		} else if(opt1 ==null && opt2 == null) {
			dto.setTotalScore(dto.getSaleScore()*10+dto.getTwentiesScore()*0+dto.getSixtiesScore()*0);
		}
		
	}
	

	public int minData(String option) {
		int min = 0;
		try {
			con = dataSource.getConnection();
			String query = "select min(" + option + ") from region_data";
			pstmt = con.prepareStatement(query);
//			pstmt.setString(1, option);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				min = rs.getInt("min(" + option + ")");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				pstmt.close();
				con.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return min;
	}

	public int maxData(String option) {
		int max = 0;
		try {
			con = dataSource.getConnection();
			String query = "select max(" + option + ") from region_data";
			pstmt = con.prepareStatement(query);
//			pstmt.setString(1, option);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				max = rs.getInt("max(" + option + ")");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				pstmt.close();
				con.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return max;
	}
	public ArrayList<OptionDto> selectRegion(){
		ArrayList<OptionDto> list = new ArrayList<>();
		try {
			con=dataSource.getConnection(); 
			String query = "select data.twenties, data.sixties, region.region_name, region.adm_cd, data.sale from region_data data, region where data.adm_cd = region.adm_cd order by adm_cd";
			pstmt = con.prepareStatement(query);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				OptionDto dto = new OptionDto();
				dto.setAdm_nm(rs.getString("region_name"));
				dto.setSale(rs.getInt("sale"));
				dto.setTwenties(rs.getInt("twenties"));
				dto.setSixties(rs.getInt("sixties"));
				dto.setAdm_cd(rs.getString("adm_cd"));
				list.add(dto);
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
	
	public double getMaxScore(ArrayList<OptionDto> dtos) {
		double max = dtos.get(0).getTotalScore();
		for(int i=0; i<dtos.size(); i++) {
			if(dtos.get(i).getTotalScore()>max) {
				max = dtos.get(i).getTotalScore();
			}
		}
		return max;
	}
	public ArrayList<OptionDto> getList(ArrayList<OptionDto> dtos){
		ArrayList<OptionDto> list = new ArrayList<OptionDto>();
		double max = getMaxScore(dtos);
		for(int i=0; i<dtos.size(); i++) {
			if(dtos.get(i).getTotalScore()==max) {
				list.add(dtos.get(i));
			}
		}
		return list;
	}
}
