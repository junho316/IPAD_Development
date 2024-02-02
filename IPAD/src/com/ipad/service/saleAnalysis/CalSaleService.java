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

public class CalSaleService implements Service {
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		String regionCode = null;
		String regionName = request.getParameter("name");
		CalculateDto dto = new CalculateDto();

		PatientDao patientDao = new PatientDao();
		CalSaleDao calSaleDao = new CalSaleDao();

		if (regionName != null) {
			regionCode = calSaleDao.getResionCode(regionName);
		} else {
			regionCode = request.getParameter("regionCode");
		}

		int calPatient = patientDao.patientCal(regionCode);
		int employee = patientDao.employeeCal(calPatient);
		int size = patientDao.areaSizeCal(calPatient);
		int calSale = calSaleDao.calculateSale(regionCode);

		dto.setPredictPatient(calPatient);
		dto.setEmployee(employee);
		dto.setSize(size);
		dto.setPredictSale(calSale);

		String jsonResponse = new Gson().toJson(dto);

		request.setAttribute("dto", dto);

		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");

		try (PrintWriter out = response.getWriter()) {
			out.print(jsonResponse);
			out.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
