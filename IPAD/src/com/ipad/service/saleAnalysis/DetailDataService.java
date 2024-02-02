package com.ipad.service.saleAnalysis;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ipad.dao.saleAnalysis.PatientDao;
import com.ipad.dto.saleAnalysis.PatientDto;
import com.ipad.service.Service;

public class DetailDataService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		PatientDao pdao = new PatientDao();
		PatientDto dto = new PatientDto();
		
		String adm_cd = (String) request.getParameter("area-code");
//		String adm_cd = (String) session.getAttribute("regionCode");
		String region_name = pdao.getRegionName(adm_cd);
		int patient = pdao.patientCal(adm_cd);
		int employee = pdao.employeeCal(patient);
		int areaSize = pdao.areaSizeCal(patient);
		int sale = pdao.calculateSale(adm_cd);
		
		dto.setRegion_name(region_name);
		dto.setPatient(patient);
		dto.setEmployee(employee);
		dto.setAreasize(areaSize);
		dto.setSale(sale);
		request.setAttribute("dto", dto);
	}

}
