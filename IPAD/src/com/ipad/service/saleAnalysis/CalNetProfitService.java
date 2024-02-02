package com.ipad.service.saleAnalysis;

import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.ipad.dao.saleAnalysis.CalNetProfitDao;
import com.ipad.dao.saleAnalysis.CalSaleDao;
import com.ipad.dao.saleAnalysis.PatientDao;
import com.ipad.dto.saleAnalysis.CalNetProfitDto;
import com.ipad.service.Service;

public class CalNetProfitService implements Service {
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		String regionCode = request.getParameter("rgCode");
		String areaSize = request.getParameter("arSize");
		String seniorEmployeeCount = request.getParameter("seEmple");
		String juniorEmployeeCount = request.getParameter("juEmple");
		String deptamount = request.getParameter("deptAm");

		CalNetProfitDto dto = new CalNetProfitDto();
		CalSaleDao calSaleDao = new CalSaleDao();
		PatientDao patientDao = new PatientDao();
		CalNetProfitDao calNetProfitDao = new CalNetProfitDao();

		ArrayList<Integer> a = calNetProfitDao.CalEmploymentFee(regionCode, seniorEmployeeCount, juniorEmployeeCount);
		int predictSale = calSaleDao.calculateSale(regionCode);
		int predictPatient = patientDao.patientCal(regionCode);
		int rentFee = calNetProfitDao.CalRentFee(regionCode, areaSize);
		int employment_cost = a.get(0);
		int seniorEmployment_cost = a.get(1);
		int juniorEmployment_cost = a.get(2);
		int deptAmount = Integer.parseInt(deptamount) * 10000;
		int netProfit = predictSale - rentFee - employment_cost - deptAmount;

		dto.setPredictPatient(predictPatient);
		dto.setPredictSale(predictSale);
		dto.setRentFee(rentFee);
		dto.setEmployment_cost(employment_cost);
		dto.setSeniorEmployment_cost(seniorEmployment_cost);
		dto.setJuniorEmployment_cose(juniorEmployment_cost);
		dto.setDeptAmount(deptAmount);
		dto.setNetProfit(netProfit);

		String jsonResponse = new Gson().toJson(dto);

		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");

		try (PrintWriter out = response.getWriter()) {
			out.print(jsonResponse);
			out.flush();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
