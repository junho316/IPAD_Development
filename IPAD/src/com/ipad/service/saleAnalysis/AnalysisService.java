package com.ipad.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AnalysisService implements Service{
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		String regionCode = request.getParameter("regionCode");
		System.out.println("받은 리전코드 : " + regionCode);
		
		request.getSession().setAttribute("regionCode", regionCode);
		
		System.out.println("세션저장확인 : " + request.getSession().getAttribute("regionCode"));
	}
}
