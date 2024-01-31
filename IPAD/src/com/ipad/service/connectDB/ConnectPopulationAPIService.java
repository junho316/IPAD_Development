package com.ipad.service.connectDB;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ipad.dao.saleAnalysis.SearchDao;
import com.ipad.dto.saleAnalysis.SearchDto;

public class ConnectPopulationAPIService implements ConnectAPIService {

	String token;
	String url = "https://sgisapi.kostat.go.kr/OpenAPI3/stats/searchpopulation.json";
	SearchDao dao = new SearchDao();
	int year = LocalDate.now().getYear();
	private ArrayList<SearchDto> dtos = new ArrayList<>();
	private ArrayList<Integer> ageTypeList = new ArrayList<>();
	private ArrayList<String> code = dao.selectAdm();
	
	

	public ConnectPopulationAPIService() {
		GetOpenAPITokenServiceIMPL getToken = new GetOpenAPITokenServiceIMPL();
		token = getToken.getToken();
		dtos = dao.getDtoS();
		setAgeType();
		for (int i = 0; i < code.size(); i++) {
			dao.updateSale(code.get(i));
		}
	}

	@Override
	public void insertData() {
		int checkNum = dao.checkDataDB();
		JsonNode jsonData = null;
		try {
			if(checkNum<dtos.size()) {
				for (SearchDto dto : dtos) {
					for (int age : ageTypeList) {

						try {
							jsonData = parseJsonData(fetchDataFromAPI(dto.getAdm_cd(), age));
							if (jsonData.get("errMsg").asText().equals("Success")) {
								JsonNode resultNode = jsonData.get("result");
								if (resultNode.isArray() && resultNode.size() > 0) {
									JsonNode populationNode = resultNode.get(0);
									dao.insert(populationNode, age, dto);
								}
							}
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				}
			} else if(checkNum==dtos.size()) {
				for (SearchDto dto : dtos) {
					for (int age : ageTypeList) {

						try {
							jsonData = parseJsonData(fetchDataFromAPI(dto.getAdm_cd(), age));
							if (jsonData.get("errMsg").asText().equals("Success")) {
								JsonNode resultNode = jsonData.get("result");
								if (resultNode.isArray() && resultNode.size() > 0) {
									JsonNode populationNode = resultNode.get(0);
									dao.update(populationNode, age);
								}
							}
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			
		}
	}

	public String fetchDataFromAPI(String code, int age) throws IOException {
		StringBuilder urlBuilder = new StringBuilder(url);
		urlBuilder.append("?year=").append(year-2);
		urlBuilder.append("&adm_cd=").append(code);
		urlBuilder.append("&accessToken=").append(token);
		if (age != 41) {
			urlBuilder.append("&age_type=").append(age);
		}
		try (BufferedReader reader = new BufferedReader(
				new InputStreamReader(new URL(urlBuilder.toString()).openStream()))) {
			StringBuilder apiResponse = new StringBuilder();
			String line;
			while ((line = reader.readLine()) != null) {
				apiResponse.append(line);
			}
			return apiResponse.toString();
		}
	}

	@Override
	public JsonNode parseJsonData(String jsonData) throws IOException {
		ObjectMapper objectMapper = new ObjectMapper();
		return objectMapper.readTree(jsonData);
	}

	public void setAgeType() {
		ageTypeList.add(41);
		for (int i = 30; i <= 40; i++) {
			if (i != 37 && i != 38 && i != 39) {
				ageTypeList.add(i);
			}
		}

	}

}
