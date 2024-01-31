package com.ipad.service.locationRecommand;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ipad.dao.locationRecommand.LocationRecommandDao;
import com.ipad.dto.locationRecommand.LocationRecommandDto;
import com.ipad.service.Service;

public class LocationRecommandService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {		
		LocationRecommandDao dao = new LocationRecommandDao();
		String opt1 =request.getParameter("implant");
		String opt2 =request.getParameter("orthodontics");
		
		ArrayList<LocationRecommandDto> dtos = dao.selectRegion();
		
		for(int i=0; i<dtos.size();i++) {
			dao.setSaleScore(dtos.get(i));
			dao.setTwentiesScore(dtos.get(i));
			dao.setSixtiesScore(dtos.get(i));
			dao.setTotalScore(dtos.get(i),opt2,opt1);
		}
		request.setAttribute("rank", dao.getTop3List(dtos));
		

	}

}
