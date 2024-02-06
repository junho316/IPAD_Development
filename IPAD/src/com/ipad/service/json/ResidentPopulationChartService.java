package com.ipad.service.json;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ipad.dao.locationAnalysis.FootTrafficDao;
import com.ipad.dao.locationAnalysis.ResidentPopulationDao;
import com.ipad.dto.locationAnalysis.ResidentPopulationDto;
import com.ipad.service.Service;

public class ResidentPopulationChartService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {

		ObjectMapper objectMapper = new ObjectMapper();

		ResidentPopulationDao residentPopulationDao = new ResidentPopulationDao();
		FootTrafficDao footTrafficDao = new FootTrafficDao();

		ArrayList<ResidentPopulationDto> dtos = new ArrayList<ResidentPopulationDto>();
		ArrayList<String> admCds = residentPopulationDao.getAdmCode();

		try {
			// 전체 데이터를 담을 리스트
			ArrayList<ResidentPopulationDto> allData = new ArrayList<>();

			// 각 지역 코드에 대한 데이터를 전체 데이터에 추가
			for (String admCd : admCds) {
				ArrayList<ResidentPopulationDto> residentPopulationDtosdtos = residentPopulationDao
						.selectPopulationData(admCd);
				allData.addAll(residentPopulationDtosdtos);
			}

			// 전체 데이터를 JSON으로 변환하여 응답
			String json = objectMapper.writeValueAsString(allData);
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			response.getWriter().write(json);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
