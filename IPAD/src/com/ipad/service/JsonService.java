package com.ipad.service;

import java.time.LocalDate;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ipad.dao.locationAnalysis.FootTrafficDao;
import com.ipad.dao.locationAnalysis.HospitalDao;
import com.ipad.dao.locationAnalysis.OpenCloseCountDao;
import com.ipad.dao.locationAnalysis.ResidentPopulationDao;
import com.ipad.dto.locationAnalysis.HospitalCountDto;
import com.ipad.dto.locationAnalysis.HospitalDto;
import com.ipad.dto.locationAnalysis.HospitalPopulationDTO;
import com.ipad.dto.locationAnalysis.OpenCloseCountDto;
import com.ipad.dto.locationAnalysis.RegionSummaryDto;
import com.ipad.dto.locationAnalysis.ResidentPopulationDto;

public class JsonService {

	// 지도 페이지 지역 정보
	public void mapRegionData(HttpServletRequest request, HttpServletResponse response) {
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
			for (HospitalPopulationDTO dto2 : hospitalPopulationDTOs) {
				if (regionSummaryDtos.get(i).getRegion().equals(dto2.getRegion())) {
					RegionSummaryDto tem = regionSummaryDtos.get(i);
					tem.setHospitalPopulation(dto2.getPopulation());
				}
			}
		}

		// 가장많은 연령
		ArrayList<ResidentPopulationDto> ageGroupfDtos = residentPopulationDao.getAgeGroup();
		for (int i = 0; i < regionSummaryDtos.size(); i++) {
			for (ResidentPopulationDto dto2 : ageGroupfDtos) {
				if (regionSummaryDtos.get(i).getRegion().equals(dto2.getRegion_name_detail())) {
					int temp = Integer.parseInt(dto2.getPopulation_10());
					String ageGroup = "10대";
					if (temp < Integer.parseInt(dto2.getPopulation_20())) {
						temp = Integer.parseInt(dto2.getPopulation_20());
						ageGroup = "20대";
					}
					if (temp < Integer.parseInt(dto2.getPopulation_30())) {
						temp = Integer.parseInt(dto2.getPopulation_30());
						ageGroup = "30대";
					}
					if (temp < Integer.parseInt(dto2.getPopulation_40())) {
						temp = Integer.parseInt(dto2.getPopulation_40());
						ageGroup = "40대";
					}
					if (temp < Integer.parseInt(dto2.getPopulation_50())) {
						temp = Integer.parseInt(dto2.getPopulation_50());
						ageGroup = "50대";
					}
					if (temp < Integer.parseInt(dto2.getPopulation_60())) {
						temp = Integer.parseInt(dto2.getPopulation_60());
						ageGroup = "60대";
					}
					if (temp < Integer.parseInt(dto2.getPopulation_70())) {
						temp = Integer.parseInt(dto2.getPopulation_70());
						ageGroup = "70대";
					}
					RegionSummaryDto tem = regionSummaryDtos.get(i);
					tem.setMaxAgeGroup(ageGroup);
					regionSummaryDtos.set(i, tem);
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
		
		regionSummaryDtos.add(regoinWide);

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

	public void mapData(HttpServletRequest request, HttpServletResponse response) {
		ObjectMapper objectMapper = new ObjectMapper();
		HospitalDao dao = new HospitalDao();
		ArrayList<HospitalDto> dtos = dao.getHospitalData();
		try {
			String json = objectMapper.writeValueAsString(dtos);
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			response.getWriter().write(json);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void hospitalChart(HttpServletRequest request, HttpServletResponse response) {
		ObjectMapper objectMapper = new ObjectMapper();
		OpenCloseCountDao dao = new OpenCloseCountDao();
		ArrayList<OpenCloseCountDto> dtos = dao.getOpenData();
		try {
			String json = objectMapper.writeValueAsString(dtos);
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			response.getWriter().write(json);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void residentPopulationChart(HttpServletRequest request, HttpServletResponse response) {

		ObjectMapper objectMapper = new ObjectMapper();

		ResidentPopulationDao residentPopulationDao = new ResidentPopulationDao();
		FootTrafficDao footTrafficDao = new FootTrafficDao();

		ArrayList<ResidentPopulationDto> dtos = new ArrayList<ResidentPopulationDto>();
		ArrayList<String> admCds = residentPopulationDao.getAdmCode();

		try {
			// 전체 데이터를 담을 리스트
			ArrayList<ResidentPopulationDto> allData = new ArrayList<>();

			// 각 지역 코드에 대한 데이터를 전체 데이터에 추가
			for (String admCd : admCds) {
				ArrayList<ResidentPopulationDto> residentPopulationDtosdtos = residentPopulationDao
						.selectPopulationData(admCd);
				allData.addAll(residentPopulationDtosdtos);
			}

			// 전체 데이터를 JSON으로 변환하여 응답
			String json = objectMapper.writeValueAsString(allData);
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			response.getWriter().write(json);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
