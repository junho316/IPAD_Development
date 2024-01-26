package com.ipad.service.connectDB;

import java.io.IOException;

import com.fasterxml.jackson.databind.JsonNode;

public interface ConnectAPIService {
	public void insertData();

//	public String fetchDataFromAPI() throws IOException;

	public JsonNode parseJsonData(String jsonData) throws IOException;
}
