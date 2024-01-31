package com.ipad.service;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ipad.dao.OptionDao;
import com.ipad.dto.OptionDto;

public class OptionService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {		
		OptionDao dao = new OptionDao();
//		String opt1 =request.getParameter("braces");
//		String opt2 =request.getParameter("implant");
		String opt1 = "b";
		String opt2 = "a";
		ArrayList<OptionDto> dtos = dao.selectRegion();
		
		for(int i=0; i<dtos.size();i++) {
			dao.setSaleScore(dtos.get(i));
			dao.setTwentiesScore(dtos.get(i));
			dao.setSixtiesScore(dtos.get(i));
			dao.setTotalScore(dtos.get(i),opt1,opt2);
		}
		
		for(int i=0; i<dao.getList(dtos).size();i++) {
		System.out.println(dao.getList(dtos).get(i).getAdm_nm());
		}
			
	

//		System.out.println(braceList);
//		System.out.println(saleList);
//		System.out.println(implantList);
	}

}
