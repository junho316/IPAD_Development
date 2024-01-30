package com.ipad.service.connectDB;

import java.util.*;

import com.ipad.dao.locationAnalysis.PopulationForecastDao;
import com.ipad.dao.locationAnalysis.ResidentPopulationDao;
import com.ipad.dto.locationAnalysis.PopulationForecastDto;

public class PopulationForecastService {
	public void saveData() {
		ResidentPopulationDao residentPopulationDao = new ResidentPopulationDao();
		int year = Integer.parseInt(residentPopulationDao.getResentYear());

		ArrayList<PopulationForecastDto> dtos = new ArrayList<PopulationForecastDto>();
		PopulationForecastDao dao = new PopulationForecastDao();

		for (int i = 0; i < 5; i++) {
			PopulationForecastDto dto = new PopulationForecastDto();
			dto.setYear(year + i);
			dto.setRegion("성남시");
			dto.setBirth((int) ((33.05 * (year + i)) - 66409.12));
			dto.setDeath((int) ((28.81 * (year + i)) - 58030.02));
			if ((year + i) >= 2025) {
				dto.setNumberHouse(dao.getNumberHouse("성남시") + 1309);
			} else {
				dto.setNumberHouse(dao.getNumberHouse("성남시"));
			}
			dto.setPopulation((int) (160.4309 + (-6.8545 * dto.getBirth()) + (-59.0073 * dto.getDeath())
					+ (3.9788 * (dto.getNumberHouse()))));
			dao.saveData(dto);
		}

		for (int i = 0; i < 5; i++) {
			PopulationForecastDto dto = new PopulationForecastDto();
			dto.setYear(year + i);
			dto.setRegion("하남시");
			dto.setBirth((int) ((19.18 * (year + i)) - 38579.07));
			dto.setFamily((int) ((1114 * (year + i)) - 2241879));
			dto.setNumberHouse(dao.getNumberHouse("하남시"));
			dto.setFamily(dao.getFamily("하남시"));
			dto.setPopulation((int) (652.4941 + (-7.9297 * dto.getBirth()) + (1.8553 * dto.getNumberHouse())
					+ (1.6320 * dto.getFamily())));
			dao.saveData(dto);
		}

		for (int i = 0; i < 5; i++) {
			PopulationForecastDto dto = new PopulationForecastDto();
			dto.setYear(year + i);
			dto.setRegion("송파구");
			if ((year + i) >= 2026) {
				dto.setNumberHouse(dao.getNumberHouse("송파구") + 1416);
			} else {
				dto.setNumberHouse(dao.getNumberHouse("송파구"));
			}
			dto.setPopulation((int) (225.06441 + (3.12072 * dto.getNumberHouse())));
			dao.saveData(dto);
		}
	}

}
