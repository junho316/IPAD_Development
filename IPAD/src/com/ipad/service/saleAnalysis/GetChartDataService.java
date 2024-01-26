package com.ipad.service.saleAnalysis;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.ipad.dao.saleAnalysis.SimpleAsDao;
import com.ipad.dto.saleAnalysis.SimpleAsDto;
import com.ipad.service.Service;

public class GetChartDataService implements Service {
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		String regionCode = request.getParameter("regionCode");
//		System.out.println(regionCode);
		SimpleAsDao simpleAsDao = new SimpleAsDao();
		ArrayList<SimpleAsDto> simpleAsDto = simpleAsDao.list(regionCode);

		String jsonResponse = new Gson().toJson(simpleAsDto);

		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
//		request.setAttribute("chartData", simpleAsDto);

		try (PrintWriter out = response.getWriter()) {
			out.print(jsonResponse);
			out.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
