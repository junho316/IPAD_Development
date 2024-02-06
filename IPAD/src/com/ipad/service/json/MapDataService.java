package com.ipad.service.json;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ipad.dao.locationAnalysis.HospitalDao;
import com.ipad.dto.locationRecommand.HospitalDetailDto;
import com.ipad.service.Service;

public class MapDataService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		ObjectMapper objectMapper = new ObjectMapper();
		HospitalDao dao = new HospitalDao();
		ArrayList<HospitalDetailDto> dtos = dao.getHospitalData();
		try {
			String json = objectMapper.writeValueAsString(dtos);
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			response.getWriter().write(json);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
