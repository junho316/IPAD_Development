package com.ipad.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ipad.service.Service;
import com.ipad.service.saleAnalysis.AnalysisService;
import com.ipad.service.saleAnalysis.CalSaleService;
import com.ipad.service.saleAnalysis.GetCustomOverlayDataService;

public class SaleAnalysisController implements Controller {

	public void execute(HttpServletRequest request, HttpServletResponse response, String com)
			throws ServletException, IOException {
		String viewPage = null;
		Service service = null;
		System.out.println("SA 컨트롤러 입장 @@@@@@@@@@@@@@@@");
		System.out.println("세일어날컨트롤러 com : " + com);
		if (com.equals("/SaleAnalysis/search.do")) {
			viewPage = "/jsp/saleAnalysis/search.jsp";
		} else if (com.equals("/SaleAnalysis/customOverlay.do")) {
			System.out.println("커스텀 오버레이 @@");
			service = new GetCustomOverlayDataService();
			service.execute(request, response);
			return;
		} else if(com.equals("/SaleAnalysis/calSale.do")) {
			System.out.println("매출 계산 쩜두@@@@@@@@@@@@@@@");
			service = new CalSaleService();
			service.execute(request, response);
			return;
		} else if (com.equals("/SaleAnalysis/analyze.do")) {
			System.out.println("분석 화면 이동 쩜두 @@@@@@@@@@@@@");
			service = new AnalysisService();
			service.execute(request, response);
			viewPage = "/jsp/saleAnalysis/analyze.jsp";
			System.out.println(viewPage);
			return;
		} else if(com.equals("/SaleAnalysis/submit.do")) {
			System.out.println("모달데이터 전송@@@@");
			return;
		}

		RequestDispatcher dispatcher = request.getRequestDispatcher(viewPage);
		dispatcher.forward(request, response);
	}
}
