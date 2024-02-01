package com.ipad.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ipad.service.Service;
import com.ipad.service.locationRecommand.LocationRecommandService;

public class LocationRecommandController implements Controller {

	public void execute(HttpServletRequest request, HttpServletResponse response, String com)
			throws ServletException, IOException {
		String viewPage = null;
		Service service = null;

		if (com.equals("/locationRecommand/recommand.do")) {			
			viewPage = "/jsp/locationRecommand/recommand.jsp";
		} 
//		else if(com.equals("/locationRecommand/submit.do")) {
//			
//			service = new LocationRecommandService();
//			service.execute(request, response);
//				
//			return;
//			
//		}
		RequestDispatcher dispatcher = request.getRequestDispatcher(viewPage);
		dispatcher.forward(request, response);
	}
}
