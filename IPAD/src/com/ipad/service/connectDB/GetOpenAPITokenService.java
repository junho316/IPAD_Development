package com.ipad.service.connectDB;

import java.io.IOException;

import com.fasterxml.jackson.databind.JsonNode;

public interface GetOpenAPITokenService {
	public String getToken();

	public String fetchDataFromAPI()throws IOException;

	public JsonNode parseJsonData(String jsonData)throws IOException;

}
