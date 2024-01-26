package com.ipad.service.saleAnalysis;

import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.ipad.dao.saleAnalysis.SearchDao;
import com.ipad.dto.saleAnalysis.SearchDto;
import com.ipad.service.Service;

public class InfoService implements Service {
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		SearchDao dao = new SearchDao();

		ArrayList<SearchDto> dto = dao.select();

		String jsonResponse = new Gson().toJson(dto);

		response.setContentType("application/json; charset=utf-8");
		response.setCharacterEncoding("UTF-8");
		request.setAttribute("info", dto);
		try (PrintWriter out = response.getWriter()) {
			out.print(jsonResponse);
			out.flush();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
