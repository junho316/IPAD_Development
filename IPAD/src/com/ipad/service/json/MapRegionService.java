package com.ipad.service.json;

import java.time.LocalDate;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ipad.dao.locationAnalysis.HospitalDao;
import com.ipad.dao.locationAnalysis.ResidentPopulationDao;
import com.ipad.dto.locationAnalysis.HospitalCountDto;
import com.ipad.dto.locationAnalysis.HospitalPopulationDTO;
import com.ipad.dto.locationAnalysis.RegionSummaryDto;
import com.ipad.dto.locationAnalysis.ResidentPopulationDto;
import com.ipad.service.Service;

public class MapRegionService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		ArrayList<RegionSummaryDto> regionSummaryDtos = new ArrayList<RegionSummaryDto>();

		// 병원수
		HospitalDao hospitalDao = new HospitalDao();
		ArrayList<HospitalCountDto> hospitalCountDtos = hospitalDao.getHospitalCount(LocalDate.now().getYear());
		for (HospitalCountDto dto : hospitalCountDtos) {
			RegionSummaryDto regionSummaryDto = new RegionSummaryDto();
			regionSummaryDto.setRegion(dto.getRegion());
			regionSummaryDto.setHospitalCount(dto.getCount());
			regionSummaryDtos.add(regionSummaryDto);
		}

		// 유동인구
		for (RegionSummaryDto dto : regionSummaryDtos) {
			dto.setFootTraffic("1");
		}

		// 주거인구
		ResidentPopulationDao residentPopulationDao = new ResidentPopulationDao();
		ArrayList<ResidentPopulationDto> residentPopulationDtos = residentPopulationDao.getTotalPopulation();

		for (int i = 0; i < regionSummaryDtos.size(); i++) {
			for (ResidentPopulationDto dto : residentPopulationDtos) {
				if (regionSummaryDtos.get(i).getRegion().equals(dto.getRegion_name_detail())) {
					RegionSummaryDto tem = regionSummaryDtos.get(i);
					tem.setResidetnPopulation(dto.getPopulation_total());
				}
			}
		}

		// 치과 당 인구
		ArrayList<ResidentPopulationDto> totalPopulationDtos = residentPopulationDao.getTotalPopulation();
		ArrayList<HospitalPopulationDTO> hospitalPopulationDTOs = new ArrayList<HospitalPopulationDTO>();

		for (HospitalCountDto hospitalCountDto : hospitalCountDtos) {
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

		for (int i = 0; i < regionSummaryDtos.size(); i++) {
			for (HospitalPopulationDTO dto : hospitalPopulationDTOs) {
				if (regionSummaryDtos.get(i).getRegion().equals(dto.getRegion())) {
					RegionSummaryDto tem = regionSummaryDtos.get(i);
					tem.setHospitalPopulation(dto.getPopulation());
				}
			}
		}

		// 가장많은 연령
		int temp10 = 0;
		int temp20 = 0;
		int temp30 = 0;
		int temp40 = 0;
		int temp50 = 0;
		int temp60 = 0;
		int temp70 = 0;

		ArrayList<ResidentPopulationDto> ageGroupfDtos = residentPopulationDao.getAgeGroup();
		for (int i = 0; i < regionSummaryDtos.size(); i++) {
			for (ResidentPopulationDto dto : ageGroupfDtos) {
				if (regionSummaryDtos.get(i).getRegion().equals(dto.getRegion_name_detail())) {

					int temp = Integer.parseInt(dto.getPopulation_10());
					String ageGroup = "10대";
					if (temp < Integer.parseInt(dto.getPopulation_20())) {
						temp = Integer.parseInt(dto.getPopulation_20());
						ageGroup = "20대";
					}
					if (temp < Integer.parseInt(dto.getPopulation_30())) {
						temp = Integer.parseInt(dto.getPopulation_30());
						ageGroup = "30대";
					}
					if (temp < Integer.parseInt(dto.getPopulation_40())) {
						temp = Integer.parseInt(dto.getPopulation_40());
						ageGroup = "40대";
					}
					if (temp < Integer.parseInt(dto.getPopulation_50())) {
						temp = Integer.parseInt(dto.getPopulation_50());
						ageGroup = "50대";
					}
					if (temp < Integer.parseInt(dto.getPopulation_60())) {
						temp = Integer.parseInt(dto.getPopulation_60());
						ageGroup = "60대";
					}
					if (temp < Integer.parseInt(dto.getPopulation_70())) {
						temp = Integer.parseInt(dto.getPopulation_70());
						ageGroup = "70대";
					}
					RegionSummaryDto tem = regionSummaryDtos.get(i);
					tem.setMaxAgeGroup(ageGroup);
					regionSummaryDtos.set(i, tem);

					// 전체 계산
					temp10 = temp + Integer.parseInt(dto.getPopulation_10());
					temp20 = temp + Integer.parseInt(dto.getPopulation_20());
					temp30 = temp + Integer.parseInt(dto.getPopulation_30());
					temp40 = temp + Integer.parseInt(dto.getPopulation_40());
					temp50 = temp + Integer.parseInt(dto.getPopulation_50());
					temp60 = temp + Integer.parseInt(dto.getPopulation_60());
					temp70 = temp + Integer.parseInt(dto.getPopulation_70());

				}
			}
		}

		// 전체 인구
		RegionSummaryDto regoinWide = new RegionSummaryDto();
		regoinWide.setRegion("전체");
		for (RegionSummaryDto dto : regionSummaryDtos) {
			if (regoinWide.getHospitalCount().isEmpty()) {
				regoinWide.setHospitalCount(dto.getHospitalCount());
			} else {
				regoinWide.setHospitalCount(String.valueOf(
						Integer.parseInt(dto.getHospitalCount()) + Integer.parseInt(regoinWide.getHospitalCount())));
			}

			if (regoinWide.getFootTraffic().isEmpty()) {
				regoinWide.setFootTraffic(dto.getFootTraffic());
			} else {
				regoinWide.setFootTraffic(String.valueOf(
						Integer.parseInt(dto.getFootTraffic()) + Integer.parseInt(regoinWide.getFootTraffic())));
			}

			if (regoinWide.getResidetnPopulation().isEmpty()) {
				regoinWide.setResidetnPopulation(dto.getResidetnPopulation());
			} else {
				regoinWide.setResidetnPopulation(String.valueOf(Integer.parseInt(dto.getResidetnPopulation())
						+ Integer.parseInt(regoinWide.getResidetnPopulation())));
			}
		}

		int population = Integer.parseInt(regoinWide.getResidetnPopulation());
		int hospitalCount = Integer.parseInt(regoinWide.getHospitalCount());

		regoinWide.setHospitalPopulation(String.valueOf(population / hospitalCount));
		regionSummaryDtos.add(regoinWide);

		int tempTotal = temp10;
		String ageGroup = "10대";
		if (tempTotal < temp20) {
			tempTotal = temp20;
			ageGroup = "20대";
		}
		if (tempTotal < temp30) {
			tempTotal = temp30;
			ageGroup = "30대";
		}
		if (tempTotal < temp40) {
			tempTotal = temp40;
			ageGroup = "40대";
		}
		if (tempTotal < temp50) {
			tempTotal = temp50;
			ageGroup = "50대";
		}
		if (tempTotal < temp60) {
			tempTotal = temp60;
			ageGroup = "60대";
		}
		if (tempTotal < temp70) {
			tempTotal = temp70;
			ageGroup = "70대";
		}
		regoinWide.setMaxAgeGroup(ageGroup);
		ObjectMapper objectMapper = new ObjectMapper();

		try {
			String json = objectMapper.writeValueAsString(regionSummaryDtos);
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			response.getWriter().write(json);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
