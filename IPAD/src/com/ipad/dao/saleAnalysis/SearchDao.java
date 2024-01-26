package com.ipad.dao.saleAnalysis;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.fasterxml.jackson.databind.JsonNode;
import com.ipad.dto.saleAnalysis.SearchDto;

public class SearchDao {
	private Connection con;
	private DataSource dataSource;
	private PreparedStatement pstmt;
	private ResultSet rs, pointRs;

	private ArrayList<Integer> household = new ArrayList<>();
	private ArrayList<Integer> houseprice = new ArrayList<>();
	private ArrayList<Integer> dentalClinic = new ArrayList<>();
	private ArrayList<Integer> income = new ArrayList<>();
	private ArrayList<Integer> subway = new ArrayList<>();
	private ArrayList<Integer> bus = new ArrayList<>();
	private ArrayList<Integer> resident = new ArrayList<>();
	private ArrayList<Integer> floatpp = new ArrayList<>();
	private ArrayList<SearchDto> dtoS = new ArrayList<>();
	ArrayList<String> code;

	public ArrayList<SearchDto> getDtoS() {
		return dtoS;
	}

	public SearchDao() {
		try {
			Context context = new InitialContext();
			Context envContext = (Context) context.lookup("java:/comp/env");
			dataSource = (DataSource) envContext.lookup("jdbc/Oracle11g");
			code = selectAdm();
			setBus();
			setDentalClinic();
			setFloatpp();
			setHousehold();
			setHouseprice();
			setIncome();
			setResident();
			setSubway();
			for (int i = 0; i < code.size(); i++) {
				SearchDto dto = new SearchDto();
				dto.setAdm_cd(code.get(i));
				dto.setBus(bus.get(i));
				dto.setDentalClinic(dentalClinic.get(i));
				dto.setFloatingPp(floatpp.get(i));
				dto.setHouseHold(household.get(i));
				dto.setHousePrice(houseprice.get(i));
				dto.setIncome(income.get(i));
				dto.setSubway(subway.get(i));
				dto.setResident(resident.get(i));
				dtoS.add(dto);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void insert(JsonNode record, int age, SearchDto dto) {

		try {
			con = dataSource.getConnection();

			if (age == 41) {

				String query = "insert into region_data (adm_cd, POPULATION, HOUSEHOLD, HOUSEPRICE, DENTALCLINIC, INCOME, SUBWAY, BUS, RESIDENT, FLOATPP) values (?,?,?,?,?,?,?,?,?,?)";
				pstmt = con.prepareStatement(query);
				pstmt.setString(1, dto.getAdm_cd());
				pstmt.setInt(2, record.get("population").asInt());
				pstmt.setInt(3, dto.getHouseHold());
				pstmt.setInt(4, dto.getHousePrice());
				pstmt.setInt(5, dto.getDentalClinic());
				pstmt.setInt(6, dto.getIncome());
				pstmt.setInt(7, dto.getSubway());
				pstmt.setInt(8, dto.getBus());
				pstmt.setInt(9, dto.getResident());
				pstmt.setInt(10, dto.getFloatingPp());
				pstmt.executeUpdate();

			} else {

				String query = "UPDATE region_DATA SET ";
				switch (age) {
				case 30:
					query += "UNDER10S";
					break;
				case 31:
					query += "TEENS";
					break;
				case 32:
					query += "TWENTIES";
					break;
				case 33:
					query += "THIRTIES";
					break;
				case 34:
					query += "FORTIES";
					break;
				case 35:
					query += "FIFTIES";
					break;
				case 36:
					query += "SIXTIES";
					break;
				case 40:
					query += "OVER70S";
					break;
				}
				query += "=? where ADM_CD=?";

				pstmt = con.prepareStatement(query);
				pstmt.setInt(1, record.get("population").asInt());
				pstmt.setString(2, record.get("adm_cd").asText());
				pstmt.executeUpdate();

			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null) {
					pstmt.close();
				}
				if (con != null) {
					con.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}		
	
	public void update(JsonNode record, int age) {
		try {
			con = dataSource.getConnection();
			String query = "update region_data set ";
			switch(age) {
			case 41:
				query += "population";
				break;
			case 30:
				query += "UNDER10S";
				break;
			case 31:
				query += "TEENS";
				break;
			case 32:
				query += "TWENTIES";
				break;
			case 33:
				query += "THIRTIES";
				break;
			case 34:
				query += "FORTIES";
				break;
			case 35:
				query += "FIFTIES";
				break;
			case 36:
				query += "SIXTIES";
				break;
			case 40:
				query += "OVER70S";
				break;
			}
			query += "=? where adm_cd=?";
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, record.get("population").asInt());
			pstmt.setString(2, record.get("adm_cd").asText());
			pstmt.executeUpdate();
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {
				pstmt.close();
				con.close();
			} catch(Exception e2) {
				e2.printStackTrace();
			}
		}
	}

	public ArrayList<SearchDto> select() {
		ArrayList<SearchDto> dtos = new ArrayList<>();
		try {
			con = dataSource.getConnection();
			String query = "select * from region_data order by adm_cd";
			pstmt = con.prepareStatement(query);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				SearchDto dto = new SearchDto();
				dto.setAdm_cd(rs.getString("adm_cd"));
				dto.setPopulation(rs.getInt("population"));
				dto.setFloatingPp(rs.getInt("floatPp"));
				dto.setHouseHold(rs.getInt("household"));
				dto.setIncome(rs.getInt("income"));
				dto.setDentalClinic(rs.getInt("dentalclinic"));
				dto.setSale(rs.getInt("sale"));
				dtos.add(dto);

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

		return dtos;
	}

	public void updateSale(String code) {
		
		pstmt = null;

		try {
			con = dataSource.getConnection();
			String query = "update region_data set sale = " + calculateSale(code) + " where adm_cd = " + code;
			Statement stmt = con.createStatement();
			stmt.executeUpdate(query);

//			String query = "update data set sale = ? where adm_cd = ? ";
//			pstmt = con.prepareStatement(query);
//			pstmt.setInt(1, calculateSale(code));
//			pstmt.setString(2, code);
//			pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("sqlException : " + e.getMessage());
		} finally {
			try {
				con.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		
	}

	public ArrayList<String> selectAdm() {
		ArrayList<String> list = new ArrayList<>();

		try {
			con = dataSource.getConnection();
			String query = "select adm_cd from region order by adm_cd";
			pstmt = con.prepareStatement(query);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				SearchDto dto = new SearchDto();
				dto.setAdm_cd(rs.getString("adm_cd"));
				list.add(dto.getAdm_cd());
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("sqlException : " + e.getMessage());
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

	public int calculateSale(String adm_cd) {
		int sale = 0;
		try {
			con = dataSource.getConnection();
			String query = "select twenties, thirties, sixties, over70s, floatPp, income, dentalClinic, subway from region_data where adm_cd=?";

			pstmt = con.prepareStatement(query);
			pstmt.setString(1, adm_cd);
			rs = pstmt.executeQuery();

			String query2 = "select * from sale_point";
			pstmt = con.prepareStatement(query2);
			pointRs = pstmt.executeQuery();
			while (rs.next()) {
				while (pointRs.next()) {
					if (rs.getInt("subway") != 0) {
						sale = (int) Math.round(((pointRs.getInt("constant")
								+ Math.log10(rs.getInt("twenties")) * pointRs.getInt("population_20_point")
								+ Math.log10(rs.getInt("thirties")) * pointRs.getInt("population_30_point")
								+ Math.log10(rs.getInt("sixties")) * pointRs.getInt("population_60_point")
								+ Math.log10(rs.getInt("over70s")) * pointRs.getInt("population_over70_point")
								+ Math.log10(rs.getInt("floatpp")) * pointRs.getInt("floatpp_point")
								+ Math.log10(rs.getInt("income")) * pointRs.getInt("income_point")
								+ Math.log10(rs.getInt("dentalClinic")) * pointRs.getInt("dentalClinic_point")
								+ Math.log10(rs.getInt("subway")) * pointRs.getInt("subway_point"))
								/ (rs.getInt("dentalClinic") + 1)));
					} else {
						sale = (int) Math.round(((pointRs.getInt("constant")
								+ Math.log10(rs.getInt("twenties")) * pointRs.getInt("population_20_point")
								+ Math.log10(rs.getInt("thirties")) * pointRs.getInt("population_30_point")
								+ Math.log10(rs.getInt("sixties")) * pointRs.getInt("population_60_point")
								+ Math.log10(rs.getInt("over70s")) * pointRs.getInt("population_over70_point")
								+ Math.log10(rs.getInt("floatpp")) * pointRs.getInt("floatpp_point")
								+ Math.log10(rs.getInt("income")) * pointRs.getInt("income_point")
								+ Math.log10(rs.getInt("dentalClinic")) * pointRs.getInt("dentalClinic_point")
								+ rs.getInt("subway") * pointRs.getInt("subway_point")) / (rs.getInt("dentalClinic") + 1)));
					}
				}

			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("sqlException : " + e.getMessage());
		}

		return sale;
	}

	public void deleteData() {
		try {
			con = dataSource.getConnection();
			String query = "delete from region_data";
			pstmt = con.prepareStatement(query);
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				pstmt.close();
				con.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}

	public int checkDataDB() {
		try {
			con = dataSource.getConnection();
			String query = "select count(*) from region_data";
			pstmt = con.prepareStatement(query);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				if(rs.getInt("count(*)")>=1) {
					return rs.getInt("count(*)");
				} else {
					return 0;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				pstmt.close();
				con.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return 0;
	}

	public void setHousehold() {
		household.add(14453);
		household.add(17258);
		household.add(11322);
	}

	public void setHouseprice() {
		houseprice.add(775064459);
		houseprice.add(1465657143);
		houseprice.add(1398833333);
	}

	public void setDentalClinic() {
		dentalClinic.add(6);
		dentalClinic.add(29);
		dentalClinic.add(5);
	}

	public void setIncome() {
		income.add(3920833);
		income.add(3802500);
		income.add(4405000);
	}

	public void setSubway() {
		subway.add(0);
		subway.add(1);
		subway.add(0);
	}

	public void setBus() {
		bus.add(32);
		bus.add(49);
		bus.add(13);
	}

	public void setResident() {
		resident.add(44466);
		resident.add(32487);
		resident.add(43003);

	}

	public void setFloatpp() {
		floatpp.add(3414090);
		floatpp.add(2240641);
		floatpp.add(664044);
	}

}
