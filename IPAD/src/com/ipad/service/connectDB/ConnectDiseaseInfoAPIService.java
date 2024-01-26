package com.ipad.service.connectDB;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ipad.dao.saleAnalysis.DiseaseDao;



public class ConnectDiseaseInfoAPIService implements ConnectAPIService {

	private String url = "https://apis.data.go.kr/B551182/diseaseInfoService/getDissByGenderAgeStats?serviceKey=XNy7q4d/4UUq9aXkSZpj9fu3S/SZW7mT3ncWoLsZ/QfDhHqd4TUKAweR1x7kyzoVrwI6D4UaPHL/r4FmxOtTdw==";
	DiseaseDao dao = new DiseaseDao();

	public void execute(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
		getAPIData();
		dao.insertDB();
	}

	@Override
	public void insertData() {

	}

	public ArrayList<String> getAPIData() throws UnsupportedEncodingException {
		ArrayList<String> json = new ArrayList<>();
		try {
			ArrayList<String> list = dao.getDiseaseCode();
			for (String code : list) {
				StringBuilder urlBuilder = new StringBuilder(url);
				urlBuilder.append("&numOfRows=20&pageNo=1&year=2022&sickCd=" + code + "&sickType=1&medTp=1");
				URL urlDisease = new URL(urlBuilder.toString());
				HttpURLConnection conn = (HttpURLConnection) urlDisease.openConnection();
				conn.setRequestMethod("GET");
				conn.setRequestProperty("Content-type", "application/json");

				BufferedReader rd;
				rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
				StringBuilder sb = new StringBuilder();
				String line;
				while ((line = rd.readLine()) != null) {
					sb.append(line);
				}
				String a = sb.toString().substring(55);
				String age = "<age>";
				String ageL = "</age>";
				
				int startIndex = a.indexOf(age);
				int endIndex = a.indexOf(ageL);
				
//				System.out.println(a.substring(startIndex+age.length(), endIndex));
//				startIndex = sb.toString().substring(55+endIndex+ageL.length()).indexOf("<age>");
//				endIndex = sb.toString().substring(55+endIndex+"</age>".length()).indexOf("</age>");
				
//				System.out.println(sb.toString().substring(55+endIndex));
//				System.out.println(startIndex+", "+endIndex);
//				System.out.println(sb.toString().substring(55+endIndex).substring(startIndex+"<age>".length(), endIndex));
//				System.out.println(sb.toString().substring(55).substring());
				
//				json.add(convertXmlToJson(sb.toString()));
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return json;
	}
	

	private static String extractValue(String xmlString, String tagName) {
		String startTag = "<" + tagName + ">";
		String endTag = "</" + tagName + ">";

		int startIndex = xmlString.indexOf(startTag);
		int endIndex = xmlString.indexOf(endTag);

		if (startIndex != -1 && endIndex != -1) {
			return xmlString.substring(startIndex + startTag.length(), endIndex);
		} else {
			return null;
		}
	}

	@Override
	public JsonNode parseJsonData(String jsonData) throws IOException {
		ObjectMapper objectMapper = new ObjectMapper();
		return objectMapper.readTree(jsonData);
	}
}
