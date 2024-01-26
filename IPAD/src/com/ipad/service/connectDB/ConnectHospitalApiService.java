package com.ipad.service.connectDB;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ipad.dao.locationAnalysis.HospitalDao;

public class ConnectHospitalApiService implements ConnectAPIService {

	private String apiKey = "b5bf0e1932654fa68ed0dfa7d6fea986";
	private String url = "https://openapi.gg.go.kr/DentistryPrivateHospital";
	HospitalDao dao = new HospitalDao();
	private ArrayList<String> sigunNameList = new ArrayList<String>();

	public ConnectHospitalApiService() {
		try {
			sigunNameList = dao.getSigunNm();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// JsonNode에 있는 데이터를 DB에 저장하는 메소드
	public void insertData() {
		try {
			for (String sigunName : sigunNameList) {
				JsonNode jsonData = parseJsonData(fetchDataFromAPI(sigunName));
				JsonNode hospitalsNode = jsonData.get("DentistryPrivateHospital");
				if (hospitalsNode.isArray() && hospitalsNode != null) {
					JsonNode hospitalArray = hospitalsNode.get(1).get("row");
					for (JsonNode item : hospitalArray) {
						dao.saveRecord(item);
						dao.updateData(item);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// url로 요청해서 json으로 데이터 가져오는 메소드
	public String fetchDataFromAPI(String sigunName) throws IOException {
		StringBuilder urlBuilder = new StringBuilder(url);
		urlBuilder.append("?Key=").append(URLEncoder.encode(apiKey, "UTF-8"));
		urlBuilder.append("&Type=json");
		urlBuilder.append("&pSize=1000");
		urlBuilder.append("&SIGUN_NM=").append(URLEncoder.encode(sigunName, "UTF-8"));

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

}
