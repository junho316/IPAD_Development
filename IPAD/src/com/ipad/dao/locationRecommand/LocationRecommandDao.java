package com.ipad.dao.locationRecommand;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.ipad.dto.locationRecommand.LocationRecommandDto;

public class LocationRecommandDao {
	Connection con;
	PreparedStatement pstmt;
	DataSource dataSource;
	ResultSet rs;

	public LocationRecommandDao() {
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

	public ArrayList<Integer> getSale(ArrayList<LocationRecommandDto> dto) {
		ArrayList<Integer> dataList = new ArrayList<>();
		for (int i = 0; i < dto.size(); i++) {
			dto.get(i).getSale();
		}
		return dataList;
	}

	public ArrayList<Integer> getTeens(ArrayList<LocationRecommandDto> dto) {
		ArrayList<Integer> dataList = new ArrayList<>();
		for (int i = 0; i < dto.size(); i++) {
			dto.get(i).getTeens();
		}
		return dataList;
	}
	public ArrayList<Integer> getTwenties(ArrayList<LocationRecommandDto> dto) {
		ArrayList<Integer> dataList = new ArrayList<>();
		for (int i = 0; i < dto.size(); i++) {
			dto.get(i).getTwenties();
		}
		return dataList;
	}

	public ArrayList<Integer> getSixties(ArrayList<LocationRecommandDto> dto) {
		ArrayList<Integer> dataList = new ArrayList<>();
		for (int i = 0; i < dto.size(); i++) {
			dto.get(i).getSixties();
		}
		return dataList;
	}
	public ArrayList<Integer> getOver70s(ArrayList<LocationRecommandDto> dto) {
		ArrayList<Integer> dataList = new ArrayList<>();
		for (int i = 0; i < dto.size(); i++) {
			dto.get(i).getOver70s();
		}
		return dataList;
	}

	public void setSaleScore(LocationRecommandDto dto) {
		double score = 0;
		score = (dto.getSale() - minData("sale")) * 10 / (maxData("sale") - minData("sale"));
		dto.setSaleScore(score);
	}
	public void setTeensScore(LocationRecommandDto dto) {
		double score = 0;
		score = (dto.getTeens() - minData("teens")) * 10 / (maxData("teens") - minData("teens"));
		dto.setTeensScore(score);
	}

	public void setTwentiesScore(LocationRecommandDto dto) {
		double score = 0;
		score = (dto.getTwenties() - minData("twenties")) * 10 / (maxData("twenties") - minData("twenties"));
		dto.setTwentiesScore(score);
	}

	public void setSixtiesScore(LocationRecommandDto dto) {
		double score = 0;
		score = (dto.getSixties() - minData("sixties")) * 10 / (maxData("sixties") - minData("sixties"));
		dto.setSixtiesScore(score);
	}
	public void setOver70sScore(LocationRecommandDto dto) {
		double score = 0;
		score = (dto.getOver70s() - minData("over70s")) * 10 / (maxData("over70s") - minData("over70s"));
		dto.setOver70sScore(score);
	}

	public void setTotalScore(LocationRecommandDto dto, boolean opt1, boolean opt2) {
		if (opt1 == true && opt2 == true) {
			dto.setTotalScore(dto.getSaleScore() * 6 + dto.getTwentiesScore() * 1+dto.getTeensScore()*1 + dto.getSixtiesScore() * 1+dto.getOver70sScore()*1);
		} else if (opt1 == false && opt2 == true) {
			dto.setTotalScore(dto.getSaleScore() * 6 + dto.getTwentiesScore() * 0+dto.getTeensScore()*0 + dto.getSixtiesScore() * 2+dto.getOver70sScore()*2);
		} else if (opt1 == true && opt2 == false) {
			dto.setTotalScore(dto.getSaleScore() * 6 + dto.getTwentiesScore() * 2+dto.getTeensScore()*2 + dto.getSixtiesScore() * 0+dto.getOver70sScore()*0);
		} else if (opt1 == false && opt2 == false) {
			dto.setTotalScore(dto.getSaleScore() * 10 + dto.getTwentiesScore() * 0+dto.getTeensScore()*0 + dto.getSixtiesScore() * 0+dto.getOver70sScore()*0);
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

	public ArrayList<LocationRecommandDto> selectRegion() {
		ArrayList<LocationRecommandDto> list = new ArrayList<>();
		try {
			con = dataSource.getConnection();

			String query = "select data.twenties, data.teens, data.over70s, data.sixties, region.region_name_detail, region.adm_cd, data.sale from region_data data, region where data.adm_cd = region.adm_cd order by adm_cd";

			pstmt = con.prepareStatement(query);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				LocationRecommandDto dto = new LocationRecommandDto();
				dto.setAdm_nm(rs.getString("region_name_detail"));
				dto.setSale(rs.getInt("sale"));
				dto.setTwenties(rs.getInt("twenties"));
				dto.setSixties(rs.getInt("sixties"));
				dto.setAdm_cd(rs.getString("adm_cd"));
				dto.setOver70s(rs.getInt("over70s"));
				dto.setTeens(rs.getInt("teens"));
				list.add(dto);
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
		return list;
	}

	public double getMaxScore(ArrayList<LocationRecommandDto> dtos) {
		double max = dtos.get(0).getTotalScore();
		for (int i = 0; i < dtos.size(); i++) {
			if (dtos.get(i).getTotalScore() > max) {
				max = dtos.get(i).getTotalScore();
			}
		}
		return max;
	}

	public ArrayList<LocationRecommandDto> getList(ArrayList<LocationRecommandDto> dtos) {
		ArrayList<LocationRecommandDto> list = new ArrayList<LocationRecommandDto>();
		double max = getMaxScore(dtos);
		for (int i = 0; i < dtos.size(); i++) {
			if (dtos.get(i).getTotalScore() == max) {
				list.add(dtos.get(i));
			}
		}
		return list;
	}

	public List<LocationRecommandDto> getTop3List(List<LocationRecommandDto> dtos) {
		return dtos.stream().sorted(Comparator.comparingDouble(LocationRecommandDto::getTotalScore).reversed()).limit(3)
				.collect(Collectors.toList());
	}

}
