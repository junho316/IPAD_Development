package com.ipad.service.locationRecommand;


import java.io.PrintWriter;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.ipad.dao.locationRecommand.LocationRecommandDao;
import com.ipad.dto.locationRecommand.LocationRecommandDto;
import com.ipad.service.Service;

public class LocationRecommandService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {		
		LocationRecommandDao dao = new LocationRecommandDao();
		System.out.println(request.getParameter("checkImpl"));
		System.out.println(request.getParameter("checkOrth"));
		boolean opt1 = Boolean.parseBoolean(request.getParameter("checkImpl"));
		boolean opt2 = Boolean.parseBoolean(request.getParameter("checkOrth"));
		System.out.println(opt1+" " + opt2);
//		System.out.println(request.getParameter("checkImpl").getClass());
		ArrayList<LocationRecommandDto> dtos = dao.selectRegion();
		
		for(int i=0; i<dtos.size();i++) {
			dao.setSaleScore(dtos.get(i));
			dao.setTwentiesScore(dtos.get(i));
			dao.setSixtiesScore(dtos.get(i));
			dao.setTotalScore(dtos.get(i),opt1,opt2);
		}
		List<LocationRecommandDto> rankList = dao.getTop3List(dtos);
		String jsonResponse = new Gson().toJson(rankList);

		response.setContentType("application/json; charset=utf-8");
		response.setCharacterEncoding("UTF-8");
		request.setAttribute("rank", rankList);
		try(PrintWriter out = response.getWriter()){
			out.print(jsonResponse);
			out.flush();
		} catch(Exception e) {
			e.printStackTrace();
		}
		

	}

}
