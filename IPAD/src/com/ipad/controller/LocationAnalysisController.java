package com.ipad.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ipad.service.Service;
import com.ipad.service.locationAnalysis.HospitalService;
import com.ipad.service.locationAnalysis.PopulationService;

public class LocationAnalysisController implements Controller {

	public void execute(HttpServletRequest request, HttpServletResponse response, String com)
			throws ServletException, IOException {
		String viewPage = null;
		Service service = null;

		if (com.equals("/locationAnalysis/hospital.do")) {
			service = new HospitalService();
			service.execute(request, response);
			viewPage = "/jsp/locationAnalysis/hospitalStatus.jsp";
		} else if (com.equals("/locationAnalysis/population.do")) {
			service = new PopulationService();
			service.execute(request, response);
			viewPage = "/jsp/locationAnalysis/populationStatus.jsp";
		} else if (com.equals("/locationAnalysis/map.do")) {

			viewPage = "/jsp/locationAnalysis/map.jsp";
		}

		RequestDispatcher dispatcher = request.getRequestDispatcher(viewPage);
		dispatcher.forward(request, response);
	}
}
