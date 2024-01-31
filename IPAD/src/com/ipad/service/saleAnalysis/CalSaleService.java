package com.ipad.service.saleAnalysis;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.ipad.dao.saleAnalysis.CalSaleDao;
import com.ipad.service.Service;

public class CalSaleService	implements Service {
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		
		String regionCode = request.getParameter("areaCode");
		System.out.println("받아온 regionCode11 : " + regionCode);
		CalSaleDao calSaleDao = new CalSaleDao();
		String calSale = calSaleDao.calculateSale(regionCode);
		System.out.println(calSale);
		String jsonResponse = new Gson().toJson(calSale);
		
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