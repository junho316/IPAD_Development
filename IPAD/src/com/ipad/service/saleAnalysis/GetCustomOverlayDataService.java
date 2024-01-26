package com.ipad.service.saleAnalysis;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.ipad.dao.saleAnalysis.SimpleAsDao;
import com.ipad.dto.saleAnalysis.OverlayDto;
import com.ipad.service.Service;

public class GetCustomOverlayDataService implements Service {
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		SimpleAsDao simpleAsDao = new SimpleAsDao();
		ArrayList<OverlayDto> overlayDto = simpleAsDao.overlayList();
		
		String jsonResponse = new Gson().toJson(overlayDto);
		
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		
		try(PrintWriter out = response.getWriter()){
			out.print(jsonResponse);
			out.flush();
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
}
