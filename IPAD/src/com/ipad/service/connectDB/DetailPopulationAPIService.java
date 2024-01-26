package com.ipad.service.connectDB;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLEncoder;
import java.time.LocalDate;
import java.util.ArrayList;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ipad.dao.saleAnalysis.DetailDao;

public class DetailPopulationAPIService implements ConnectAPIService {
	
	private String token;
	private String url = "https://sgisapi.kostat.go.kr/OpenAPI3/stats/searchpopulation.json";
	
	DetailDao dao = new DetailDao();
	private ArrayList<String> admCodeList = new ArrayList<String>();
	private ArrayList<String> yearList = new ArrayList<String>();
	private ArrayList<String> ageTypeList = new ArrayList<String>();
	private String[] genderTypeList = {"0", "1", "2"};
	
	public DetailPopulationAPIService() {
		try {
			GetOpenAPITokenServiceIMPL getPopulationOpenAPIToken = new GetOpenAPITokenServiceIMPL();
			token = getPopulationOpenAPIToken.getToken();
			admCodeList = dao.getAdmCode();
			setYear();
			setAgeType();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	@Override
	public void insertData() {
		dao.deleteEveryData();
		try {
			for (String admCode : admCodeList) {
				for (String year : yearList) {
					for (String gender : genderTypeList) {
						try {
							JsonNode jsonData = parseJsonData(fetchDataFromAPI(admCode, year, gender));
							if (jsonData.get("errMsg").asText().equals("Success")) {
								JsonNode resultNode = jsonData.get("result");
								if (resultNode.isArray() && resultNode.size() > 0) {
									JsonNode populationNode = resultNode.get(0);
									dao.saveRecord(populationNode, year, gender);
								}
							}
						} catch (Exception e) {
							e.printStackTrace();
						}
					}

					for (String ageType : ageTypeList) {
						try {
							JsonNode jsonData = parseJsonData(fetchDataFromAPI(admCode, year, ageType));
							if (jsonData.get("errMsg").asText().equals("Success")) {
								JsonNode resultNode = jsonData.get("result");
								if (resultNode.isArray() && resultNode.size() > 0) {
									JsonNode populationNode = resultNode.get(0);
									dao.saveRecord(populationNode, year, ageType);
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
		}
	}

	// url로 요청해서 json으로 데이터 가져오는 메소드
	public String fetchDataFromAPI(String admCode, String year, String option) throws IOException {
		StringBuilder urlBuilder = new StringBuilder(url);
		urlBuilder.append("?accessToken=").append(URLEncoder.encode(token, "UTF-8"));
		urlBuilder.append("&year=").append(URLEncoder.encode(year, "UTF-8"));
		urlBuilder.append("&adm_cd=").append(URLEncoder.encode(admCode, "UTF-8"));

		if (Integer.parseInt(option) > 3) {
			urlBuilder.append("&age_type=").append(URLEncoder.encode(option, "UTF-8"));
		} else {
			urlBuilder.append("&gender=").append(URLEncoder.encode(option, "UTF-8"));
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

	// json 형식의 string을 JsonNode로 변경하는 메소드
	public JsonNode parseJsonData(String jsonData) throws IOException {
		ObjectMapper objectMapper = new ObjectMapper();
		return objectMapper.readTree(jsonData);
	}

	// 2015년부터 전년도 까지 데이터를 ArrayList에 저장하는 메소드
	private void setYear() {
		int currentYear = LocalDate.now().getYear();
		for (int i = 2015; i < currentYear; i++) {
			yearList.add(String.valueOf(i));
		}
	}

	// Open API에 요청할때 필요한 나이 타입을 ArrayList에 저장하는 메소드
	private void setAgeType() {
		for (int i = 31; i <= 37; i++) {
			ageTypeList.add(String.valueOf(i));
		}
	}

}
