package com.ipad.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ipad.service.Service;

import com.ipad.service.saleAnalysis.CalNetProfitService;

import com.ipad.service.saleAnalysis.CalSaleService;
import com.ipad.service.saleAnalysis.GetCustomOverlayDataService;

public class SaleAnalysisController implements Controller {

	public void execute(HttpServletRequest request, HttpServletResponse response, String com)
			throws ServletException, IOException {
		String viewPage = null;
		Service service = null;
		
		if (com.equals("/SaleAnalysis/search.do")) {
			viewPage = "/jsp/saleAnalysis/search.jsp";
		} else if (com.equals("/SaleAnalysis/customOverlay.do")) {

			service = new GetCustomOverlayDataService();
			service.execute(request, response);
			return;
		} else if(com.equals("/SaleAnalysis/calculate.do")) {
			service = new CalSaleService();
			service.execute(request, response);
			return;
		} else if (com.equals("/SaleAnalysis/analyze.do")) {
			viewPage = "/jsp/saleAnalysis/analyze.jsp";
		} else if(com.equals("/SaleAnalysis/netprofit.do")) {
			service = new CalNetProfitService();
			service.execute(request, response);
			return;
		}


		RequestDispatcher dispatcher = request.getRequestDispatcher(viewPage);
		dispatcher.forward(request, response);
	}
}
