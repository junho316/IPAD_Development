package com.ipad.service.connectDB;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import com.ipad.service.locationAnalysis.OpenCloseCountService;

public class UpdateDBService {
	private ScheduledExecutorService scheduler;

	ConnectFootTrafficAPIService connectFootTrafficAPIService = new ConnectFootTrafficAPIService();
	ConnectHospitalApiService connectHospitalApiService = new ConnectHospitalApiService();
	ConnectResidentPopulationAPIService connectResidentPopulationAPIService = new ConnectResidentPopulationAPIService();
	OpenCloseCountService openCloseCountService = new OpenCloseCountService();
	DetailPopulationAPIService detailPopulationAPIService = new DetailPopulationAPIService();
	DetailBuildingAPIService detailBuildingAPIService = new DetailBuildingAPIService();
	ConnectPopulationAPIService connectPopulationService = new ConnectPopulationAPIService();
	PopulationForecastService populationForecastService = new PopulationForecastService();

	// DB 데이터 업데이트
	public void updateDB() {
		scheduler = Executors.newScheduledThreadPool(1);
		scheduler.scheduleAtFixedRate(this::runDailyTask, 0, 30, TimeUnit.MINUTES);
	}

	// 스케줄러 종료 메소드
	public void shutdownScheduler() {
		if (scheduler != null && !scheduler.isShutdown()) {
			scheduler.shutdown();
		}
	}

	// 일정 시간마다 반복해서 실행하는 메소드
	private void runDailyTask() {
//		connectPopulationService.insertData();
//		detailPopulationAPIService.insertData();
//		detailBuildingAPIService.insertData();
//		connectFootTrafficAPIService.insertData();
//		connectHospitalApiService.insertData();
//		connectResidentPopulationAPIService.insertData();
//		openCloseCountService.saveData();
//		populationForecastService.saveData();
	}

}
