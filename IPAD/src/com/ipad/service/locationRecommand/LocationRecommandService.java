package com.ipad.service.locationRecommand;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import com.google.gson.Gson;
import com.ipad.dao.locationRecommand.LocationRecommandDao;
import com.ipad.dto.locationRecommand.LocationRecommandDto;
import com.ipad.service.Service;

public class LocationRecommandService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		LocationRecommandDao dao = new LocationRecommandDao();

		BufferedReader reader;
		try {
			reader = request.getReader();

			StringBuilder sb = new StringBuilder();
			String line;

			while ((line = reader.readLine()) != null) {
				sb.append(line);

			}
			JSONObject jsonData = new JSONObject(sb.toString());
			boolean checkImpl = jsonData.getBoolean("checkImpl");
			boolean checkOrth = jsonData.getBoolean("checkOrth");
			ArrayList<LocationRecommandDto> dtos = dao.selectRegion();

			for (int i = 0; i < dtos.size(); i++) {
				dao.setSaleScore(dtos.get(i));
				dao.setTeensScore(dtos.get(i));
				dao.setTwentiesScore(dtos.get(i));
				dao.setSixtiesScore(dtos.get(i));
				dao.setOver70sScore(dtos.get(i));
				dao.setTotalScore(dtos.get(i), checkOrth, checkImpl);
			}
			List<LocationRecommandDto> rankList = dao.getTop3List(dtos);
			String jsonResponse = new Gson().toJson(rankList);
			
			PrintWriter out = response.getWriter();
			response.setContentType("application/json; charset=utf-8");
			response.setCharacterEncoding("UTF-8");
			out.print(jsonResponse);
			out.flush();
		} catch (IOException e1) {
			 
			e1.printStackTrace();
		}

	}

}
