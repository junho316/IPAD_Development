package com.ipad.service.locationAnalysis;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ipad.dao.locationAnalysis.FootTrafficDao;
import com.ipad.dao.locationAnalysis.PopulationForecastDao;
import com.ipad.dao.locationAnalysis.ResidentPopulationDao;
import com.ipad.dto.locationAnalysis.FootTrafficDto;
import com.ipad.dto.locationAnalysis.PopulationForecastDto;
import com.ipad.dto.locationAnalysis.ResidentPopulationDto;
import com.ipad.service.Service;

public class PopulationService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		ResidentPopulationDao residentPopulationDaodao = new ResidentPopulationDao();
		FootTrafficDao footTrafficDao = new FootTrafficDao();

		ArrayList<String> admCds = residentPopulationDaodao.getAdmCode();
		for (String admCd : admCds) {
			ArrayList<ResidentPopulationDto> residentPopulationDtosdtos = residentPopulationDaodao
					.selectPopulationData(admCd);
			request.setAttribute(admCd, residentPopulationDtosdtos);
		}

		ArrayList<FootTrafficDto> footTrafficDtos = footTrafficDao.selectFootTrafficData();
		request.setAttribute("FootTraffic", footTrafficDtos);
		request.setAttribute("admCd", admCds);

		PopulationForecastDao populationForecastDao = new PopulationForecastDao();
		ArrayList<PopulationForecastDto> populationForecastDtos = populationForecastDao.getPopulation();
		request.setAttribute("PopulationForecast", populationForecastDtos);
	}

}
