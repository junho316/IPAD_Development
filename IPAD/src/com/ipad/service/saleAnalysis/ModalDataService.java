package com.ipad.service.saleAnalysis;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import com.google.gson.Gson;
import com.ipad.dto.saleAnalysis.ModalDataDto;
import com.ipad.service.Service;

public class ModalDataService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		ModalDataDto dto = new ModalDataDto();
		BufferedReader reader;
		
		try {
			reader = request.getReader();
			StringBuilder sb = new StringBuilder();
			String line;
			
			while((line = reader.readLine()) !=null) {
				sb.append(line);
			}
			JSONObject jsonData = new JSONObject(sb.toString());
			String area = jsonData.getString("areaSize");
			String employee = jsonData.getString("employeeCount");
			String proEmployee = jsonData.getString("proemployeeCount");
			String dept = jsonData.getString("deptAmount");
			
			dto.setArea(area);
			dto.setDept(dept);
			dto.setEmployee(employee);
			dto.setProEmployee(proEmployee);
			
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
