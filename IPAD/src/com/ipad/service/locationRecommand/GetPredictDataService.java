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
			
			CalculateDto dto = new CalculateDto();

			PatientDao patientDao = new PatientDao();
			CalSaleDao calSaleDao = new CalSaleDao();
			CalNetProfitDao calNetProfit = new CalNetProfitDao();

			regionCode = calSaleDao.getResionCode(regionName);

			
			int calPatient = patientDao.patientCal(regionCode);
			int employee = patientDao.employeeCal(calPatient);
			int size = patientDao.areaSizeCal(calPatient);
			int calSale = calSaleDao.calculateSale(regionCode);
			int rentFee = calNetProfit.CalRentFee(regionCode, Integer.toString(size));
			int employment_cost = calNetProfit.CalEmploymentAvgFee(regionCode);
			int netProfit = calSale - rentFee - employment_cost;

			dto.setPredictPatient(calPatient);
			dto.setEmployee(employee);
			dto.setSize(size);
			dto.setPredictSale(calSale);
			dto.setNetProfit(netProfit);

			String jsonResponse = new Gson().toJson(dto);

			request.setAttribute("dto", dto);

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
