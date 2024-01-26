package com.ipad.service.connectDB;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ipad.dao.locationAnalysis.FootTrafficDao;

import oracle.net.aso.d;

public class ConnectFootTrafficAPIService implements ConnectAPIService {

	private String url = "http://openapi.seoul.go.kr:8088/4b45426a4e6a756e353463624e574c/json/VwsmAdstrdFlpopW";
	FootTrafficDao dao = new FootTrafficDao();

	// JsonNode에 있는 데이터를 DB에 저장하는 메소드
	public void insertData() {
		try {
			int start = 1;
			int end = 1000;
			int max;
			do {
				JsonNode jsonData = parseJsonData(fetchDataFromAPI(start, end));
				JsonNode resultNode = jsonData.get("VwsmAdstrdFlpopW").get("row");
				max = jsonData.get("VwsmAdstrdFlpopW").get("list_total_count").asInt();
				for (JsonNode populationNode : resultNode) {
					dao.saveRecord(populationNode);
					dao.updateDate(populationNode);
				}
				start = start + 1000;
				end = end + 1000;
			} while (start <= max);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// url로 요청해서 json으로 데이터 가져오는 메소드
	public String fetchDataFromAPI(int start, int end) throws IOException {
		StringBuilder urlBuilder = new StringBuilder(url);
		urlBuilder.append("/" + start);
		urlBuilder.append("/" + end);

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
