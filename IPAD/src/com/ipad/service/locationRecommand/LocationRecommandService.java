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
			System.out.println(checkImpl);
			System.out.println(checkOrth);
			ArrayList<LocationRecommandDto> dtos = dao.selectRegion();

			for (int i = 0; i < dtos.size(); i++) {
				dao.setSaleScore(dtos.get(i));
				dao.setTwentiesScore(dtos.get(i));
				dao.setSixtiesScore(dtos.get(i));
				dao.setTotalScore(dtos.get(i), checkOrth, checkImpl);
			}
			List<LocationRecommandDto> rankList = dao.getTop3List(dtos);
			String jsonResponse = new Gson().toJson(rankList);

			response.setContentType("application/json; charset=utf-8");
			response.setCharacterEncoding("UTF-8");
			request.setAttribute("rank", rankList);
			
			PrintWriter out = response.getWriter();
			out.print(jsonResponse);
			out.flush();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		// JSON 형식의 데이터를 객체로 파싱
		

		// 값 읽어오기
		

//		System.out.println(request.getParameter("checkImpl"));
//		System.out.println(request.getParameter("checkOrth"));
//		boolean opt1 = Boolean.parseBoolean(request.getParameter("checkImpl"));
//		boolean opt2 = Boolean.parseBoolean(request.getParameter("checkOrth"));
//		System.out.println(opt1 + " " + opt2);
//		System.out.println(request.getParameter("checkImpl").getClass());
//		ArrayList<LocationRecommandDto> dtos = dao.selectRegion();
//
//		for (int i = 0; i < dtos.size(); i++) {
//			dao.setSaleScore(dtos.get(i));
//			dao.setTwentiesScore(dtos.get(i));
//			dao.setSixtiesScore(dtos.get(i));
//			dao.setTotalScore(dtos.get(i), checkImpl, checkOrth);
//		}
//		List<LocationRecommandDto> rankList = dao.getTop3List(dtos);
//		String jsonResponse = new Gson().toJson(rankList);
//
//		response.setContentType("application/json; charset=utf-8");
//		response.setCharacterEncoding("UTF-8");
//		request.setAttribute("rank", rankList);
//		try (PrintWriter out = response.getWriter()) {
//			out.print(jsonResponse);
//			out.flush();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}

	}

}
