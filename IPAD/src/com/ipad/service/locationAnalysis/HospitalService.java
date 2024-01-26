package com.ipad.service.locationAnalysis;

import java.time.LocalDate;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ipad.dao.locationAnalysis.HospitalDao;
import com.ipad.dao.locationAnalysis.OpenCloseCountDao;
import com.ipad.dao.locationAnalysis.ResidentPopulationDao;
import com.ipad.dto.locationAnalysis.HospitalCountDto;
import com.ipad.dto.locationAnalysis.HospitalPopulationDTO;
import com.ipad.dto.locationAnalysis.OpenCloseCountDto;
import com.ipad.dto.locationAnalysis.ResidentPopulationDto;
import com.ipad.service.Service;

public class HospitalService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// 개업 폐업수
		OpenCloseCountDao openCloseCountDao = new OpenCloseCountDao();
		HospitalDao hospitalDao = new HospitalDao();
		ResidentPopulationDao residentPopulationDao = new ResidentPopulationDao();
		ArrayList<HospitalCountDto> hospitalCountDtos = new ArrayList<HospitalCountDto>();
		ArrayList<OpenCloseCountDto> openCloseCountDtosdtos = openCloseCountDao.getOpenData();
		request.setAttribute("OpenCloseCount", openCloseCountDtosdtos);

		for (int i = LocalDate.now().getYear() - 4; i <= LocalDate.now().getYear(); i++) {
			ArrayList<HospitalCountDto> hospitalCountDtos2 = hospitalDao.getHospitalCount(i);
			for (HospitalCountDto dto : hospitalCountDtos2) {
				hospitalCountDtos.add(dto);
			}
		}
		request.setAttribute("HospitalCount", hospitalCountDtos);

		// 총 병원수
		ArrayList<HospitalCountDto> totalHospitalDtos = hospitalDao.getHospitalCount(LocalDate.now().getYear());
		ArrayList<ResidentPopulationDto> totalPopulationDtos = residentPopulationDao.getTotalPopulation();

		ArrayList<HospitalPopulationDTO> hospitalPopulationDTOs = new ArrayList<HospitalPopulationDTO>();
		for (HospitalCountDto hospitalCountDto : totalHospitalDtos) {
			for (ResidentPopulationDto residentPopulationDto : totalPopulationDtos) {
				if (hospitalCountDto.getRegion().equals(residentPopulationDto.getRegion_name_detail())) {
					HospitalPopulationDTO hospitalPopulationDTO = new HospitalPopulationDTO();
					hospitalPopulationDTO.setRegion(hospitalCountDto.getRegion());
					hospitalPopulationDTO
							.setPopulation(String.valueOf(Integer.parseInt(residentPopulationDto.getPopulation_total())
									/ Integer.parseInt(hospitalCountDto.getCount())));
					hospitalPopulationDTOs.add(hospitalPopulationDTO);
				}
			}
		}

		request.setAttribute("HospitalPopulation", hospitalPopulationDTOs);

		// 인구 예측

	}

}
