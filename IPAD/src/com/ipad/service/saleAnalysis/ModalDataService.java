package com.ipad.service.saleAnalysis;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;

import com.google.gson.Gson;
import com.ipad.dao.saleAnalysis.PatientDao;
import com.ipad.dto.saleAnalysis.ModalDataDto;
import com.ipad.service.Service;

public class ModalDataService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
//		HttpSession session = request.getSession();
		ModalDataDto dto = new ModalDataDto();
		PatientDao dao = new PatientDao();
		BufferedReader reader;
		
		try {
			reader = request.getReader();
			StringBuilder sb = new StringBuilder();
			String line;
			
			while((line = reader.readLine()) !=null) {
				sb.append(line);
			}
//			String adm_cd = (String) session.getAttribute("regionCode");
			
			JSONObject jsonData = new JSONObject(sb.toString());
//			int adm_cd = jsonData.get("regionCode");
			String areaSize = jsonData.getString("areaSize");
			String juniorEmployeeCount = jsonData.getString("juniorEmployeeCount");
			String seniorEmployeeCount = jsonData.getString("seniorEmployeeCount");
			String deptamount = jsonData.getString("deptamount");
			
			dto.setAreaSize(areaSize);
			dto.setJuniorEmployeeCount(juniorEmployeeCount);
			dto.setSeniorEmployeeCount(seniorEmployeeCount);
			dto.setDeptamount(deptamount);
			
			
			String jsonResponse = new Gson().toJson(dto);
			
			PrintWriter out = response.getWriter();
			response.setContentType("application/json; charset=utf-8");
			response.setCharacterEncoding("UTF-8");
			out.print(jsonResponse);
			out.flush();
		}catch (IOException e) {
			e.printStackTrace();
		}
	}

}
