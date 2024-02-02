package com.ipad.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ipad.service.JsonService;
import com.ipad.service.Service;
import com.ipad.service.locationRecommand.GetPredictDataService;
import com.ipad.service.locationRecommand.LocationRecommandService;
import com.ipad.service.saleAnalysis.CalSaleService;
import com.ipad.service.saleAnalysis.InfoService;

public class JsonController implements Controller {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response, String com)
			throws ServletException, IOException {
		JsonService chartService = new JsonService();
		Service service;
		if (com.equals("/json/hospital.do")) {
			chartService.hospitalChart(request, response);
		} else if (com.equals("/json/residentPopulation.do")) {
			chartService.residentPopulationChart(request, response);
		} else if (com.equals("/json/footTraffic.do")) {

		} else if (com.equals("/json/populationFrocast.do")) {

		} else if (com.equals("/json/map.do")) {
			chartService.mapData(request, response);
		} else if (com.equals("/json/mapRegion.do")) {
			chartService.mapRegionData(request, response);
		} else if (com.equals("/json/info.do")) {
			service = new InfoService();
			service.execute(request, response);
			
		} else if(com.equals("/json/locationRecommand.do")) {
			service = new LocationRecommandService();
			service.execute(request, response);
		} else if(com.equals("/json/predict.do")) {
			service = new GetPredictDataService();
			service.execute(request, response);
		}
	}

}
