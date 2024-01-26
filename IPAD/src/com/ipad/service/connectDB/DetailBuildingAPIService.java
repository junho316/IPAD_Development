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

public class DetailBuildingAPIService implements ConnectAPIService {

	private String token;
	private String url = "https://sgisapi.kostat.go.kr/OpenAPI3/stats/house.json";

	DetailDao dao = new DetailDao();
	private ArrayList<String> admCodeList = new ArrayList<String>();
	private ArrayList<String> yearList = new ArrayList<String>();

	public DetailBuildingAPIService() {
		try {
			GetOpenAPITokenServiceIMPL getPopulationOpenAPIToken = new GetOpenAPITokenServiceIMPL();
			token = getPopulationOpenAPIToken.getToken();
			admCodeList = dao.getAdmCode();
			setYear();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void insertData() {
		// TODO Auto-generated method stub
//		dao.deleteEveryData();
		try {
			for (String admCode : admCodeList) {
				for (String year : yearList) {
					try {
						JsonNode jsonData = parseJsonData(fetchDataFromAPI(admCode, year));
						if (jsonData.get("errMsg").asText().equals("Success")) {
							JsonNode resultNode = jsonData.get("result");
							if (resultNode.isArray() && resultNode.size() > 0) {
								JsonNode populationNode = resultNode.get(0);
								dao.saveBuildingRecord(populationNode, year);
							}
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String fetchDataFromAPI(String admCode, String year) throws IOException {
		StringBuilder urlBuilder = new StringBuilder(url);
		urlBuilder.append("?accessToken=").append(URLEncoder.encode(token, "UTF-8"));
		urlBuilder.append("&year=").append(URLEncoder.encode(year, "UTF-8"));
		urlBuilder.append("&adm_cd=").append(URLEncoder.encode(admCode, "UTF-8"));
		urlBuilder.append("&low_search=0");

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

	private void setYear() {
		int currentYear = LocalDate.now().getYear();
		for (int i = 2015; i < currentYear; i++) {
			yearList.add(String.valueOf(i));
		}

	}

	@Override
	public JsonNode parseJsonData(String jsonData) throws IOException {
		ObjectMapper objectMapper = new ObjectMapper();
		return objectMapper.readTree(jsonData);
	}

}
