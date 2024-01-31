package com.ipad.service.saleAnalysis;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.ipad.dao.saleAnalysis.CalSaleDao;
import com.ipad.dao.saleAnalysis.PatientDao;
import com.ipad.dto.saleAnalysis.CalculateDto;
import com.ipad.service.Service;

public class CalSaleService	implements Service {
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		
		String regionCode = request.getParameter("areacode");
		CalculateDto dto = new CalculateDto();
		CalSaleDao calSaleDao = new CalSaleDao();
		PatientDao patientDao = new PatientDao();
		
		System.out.println("받아온 regionCode11 : " + regionCode);
		
		int calSale = calSaleDao.calculateSale(regionCode);
		int calPatient = patientDao.patientCal(regionCode);
		int employee = patientDao.employeeCal(calPatient);
		
		
		dto.setPredictSale(calSale);
		dto.setPredictPatient(calPatient);
		dto.setEmployee(employee);
		
		System.out.println(dto);
		
		String jsonResponse = new Gson().toJson(dto);
		
		
		request.setAttribute("dto", dto);
		
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		
		try(PrintWriter out = response.getWriter()){
			out.print(jsonResponse);
			out.flush();
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
}
