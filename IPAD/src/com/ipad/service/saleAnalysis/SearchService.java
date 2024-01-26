package com.ipad.service.saleAnalysis;

import java.util.ArrayList;

import com.ipad.dao.saleAnalysis.SearchDao;

public class SearchService {

	
	public void updateData() {
		SearchDao dao = new SearchDao();
		
		ArrayList<String> code = dao.selectAdm();
		
		for (int i = 0; i < code.size(); i++) {
			dao.updateSale(code.get(i));
		}
		
	}


}
