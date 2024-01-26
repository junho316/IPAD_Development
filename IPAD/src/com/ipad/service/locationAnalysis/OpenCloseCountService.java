package com.ipad.service.locationAnalysis;

import java.time.LocalDate;

import com.ipad.dao.locationAnalysis.OpenCloseCountDao;

public class OpenCloseCountService {
	public void saveData() {
		OpenCloseCountDao dao = new OpenCloseCountDao();

		for (int i = 2015; i <= LocalDate.now().getYear(); i++) {
			dao.saveOpenData(i, dao.getOpenData(i));
			dao.saveCloseData(i, dao.getCloseData(i));
			dao.saveHospitalCountData(i, dao.getHospitalCount(i));
			dao.updateOpenData(i, dao.getOpenData(i));
		}

	}
}
