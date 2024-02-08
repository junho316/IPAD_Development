package com.ipad.service.locationRecommand;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import com.google.gson.Gson;
import com.ipad.dao.saleAnalysis.CalNetProfitDao;
import com.ipad.dao.saleAnalysis.CalSaleDao;
import com.ipad.dao.saleAnalysis.PatientDao;
import com.ipad.dto.saleAnalysis.CalculateDto;
import com.ipad.service.Service;

public class GetPredictDataService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		String regionCode = null;
		BufferedReader reader;
		try {
			reader = request.getReader();

			StringBuilder sb = new StringBuilder();
			String line;

			while ((line = reader.readLine()) != null) {
				sb.append(line);

			}
			JSONObject jsonData = new JSONObject(sb.toString());
			String regionName = jsonData.getString("name");		
			
			CalSaleDao calSaleDao = new CalSaleDao();
			CalNetProfitDao calNetProfitDao = new CalNetProfitDao();

			regionCode = calSaleDao.getResionCode(regionName);

			CalculateDto dto = calNetProfitDao.calNetProfit(regionCode);
			String jsonResponse = new Gson().toJson(dto);

			PrintWriter out = response.getWriter();
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			out.print(jsonResponse);
			out.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
