<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

	<!DOCTYPE html>
	<html lang="ko">

	<head>
		<meta charset="UTF-8">
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
		<title>지도</title>
		<!-- css,font -->

		<!-- <link rel="stylesheet" href="css/search.css"> -->
		<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
		<link rel="preconnect" href="https://fonts.googleapis.com">
		<link rel="preconnect" href="https://fonts.gstatic.com">
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/map.css">
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/newSearch.css">
		<link rel="stylesheet"
			href="https://fonts.googleapis.com/css2?family=Black+Han+Sans&family=Do+Hyeon&family=Gothic+A1:wght@500&family=Noto+Sans+KR:wght@500&display=swap">
		<!-- script -->
		<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"></script>
		<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
		<script src="https://cdn.jsdelivr.net/npm/chart.js/dist/chart.umd.min.js"></script>
		<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.0/jquery.min.js"></script>
		<script type="text/javascript"
			src="//dapi.kakao.com/v2/maps/sdk.js?appkey=0983003a235a4d3c0ae25870de9c471a&libraries=services"></script>

		<title>지역 조회 페이지</title>
		<script>
			var contextPath = "${pageContext.request.contextPath}";
		</script>
	</head>

	<link rel="icon" href="data:;base64,iVBORw0KGgo=">

	<body class="vh-100 overflow-hiddden">
		<!-- modal -->
		<!-- <div class="modal fade" id="detailModal" tabindex="-1" aria-labelledby="detailModalLabel" aria-hidden="true">
			<div class="modal-dialog modal-dialog-centered">
				<div class="modal-content">
					<form action="analyze.do" method="get" target="result" onsubmit="newpage();">
						<div class="modal-header">
							<h5 class="modal-title" id="detailModalLabel">상세 분석</h5>
							<button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
						</div>
						<div class="modal-body">
							<div>
								<label for="area-size" class="col-form-label">예정 평 수</label> <input type="text"
									class="form-control" name="area-size" id="area-size">
							</div>
							<div>
								<label for="employee-count" class="col-form-label">예정 종업원
									수</label> <input type="text" class="form-control" name="employee-count"
									id="employee-count">
							</div>
							<div>
								<label for="dept-amount" class="col-form-label">월 대출 상환
									금액</label> <input type="text" class="form-control" name="dept-amount"
									id="dept-amount">
								<div class="form-text" id="dept-amountHelp">만 원단위로 입력해 주세요.</div>
							</div>
							<div>
								<input type="hidden" name="area-name" value="" id="area-name">
								<input type="hidden" name="area-code" value="" id="area">
							</div>
						</div>
						<div class="modal-footer">
							<button type="submit" class="btn btn-primary">입력</button>
							<button type="button" class="btn btn-secondary" data-bs-dismiss="modal">닫기</button>
						</div>
					</form>
				</div>
			</div>
		</div> -->

		<!-- 모달에서 모달로  -->

		<div class="modal fade" id="detailModal" aria-hidden="true" aria-labelledby="exampleModalToggleLabel"
			tabindex="-1">
			<div class="modal-dialog modal-dialog-centered">
				<div class="modal-content">
					<div class="modal-header">
						<h1 class="modal-title fs-5" id="exampleModalToggleLabel">상세 분석</h1>
						<button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
					</div>
					<div class="modal-body">
						<div>
							<label for="area-size" class="col-form-label">예정 평 수</label> <input type="text"
								class="form-control" name="area-size" id="area-size">
						</div>
						<div>
							<label for="employee-count" class="col-form-label">예정 종업원
								수</label> <input type="text" class="form-control" name="employee-count"
								id="employee-count">
							<input type="text" class="form-control" name="proemployee-count" id="proemployee-count">
						</div>
						<div>
							<label for="dept-amount" class="col-form-label">월 대출 상환
								금액</label> <input type="text" class="form-control" name="dept-amount" id="dept-amount">
							<div class="form-text" id="dept-amountHelp">만 원단위로 입력해 주세요.</div>
						</div>
						<div>
							<input type="hidden" name="area-name" value="" id="area-name">
							<input type="hidden" name="area-code" value="" id="area">
						</div>
					</div>
					<div class="modal-footer">
						<button class="btn btn-primary" data-bs-target="#modalData" data-bs-toggle="modal"
							onclick="getModalData();">입력</button>
						<button type="button" class="btn btn-secondary" data-bs-dismiss="modal">닫기</button>
					</div>
				</div>
			</div>
		</div>
		<div class="modal fade" id="modalData" aria-hidden="true" aria-labelledby="modalDataLabel" tabindex="-1">
			<div class="modal-dialog modal-dialog-centered">
				<div class="modal-content">
					<div class="modal-header">
						<h1 class="modal-title fs-5" id="modalDataLabel">분석 결과</h1>
						<button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
					</div>
					<div class="modal-body" id="analyzeInfo">
						<figure class="figure figurebox">
							<div id="s_salePredict_list" class="row justify-content-between list_box">
								<div class="col s_bodyIcon"><img src="${pageContext.request.contextPath}/img/won.png"
										alt=""></div>
								<div class="col fs-5 text-end list_textBox" id="salePredictList"></div>
							</div>
							<!-- <figcaption class="figure-caption text-end"></figcaption> -->
						</figure>
						<figure class="figure figurebox">
							<div id="patientPredict_list" class="row justify-content-between list_box">
								<div class="col-1 s_bodyIcon"><img
										src="${pageContext.request.contextPath}/img/dentalPatient.png" alt="">
								</div>
								<p class="col fs-5 text-end list_textBox" id="patientList"></p>
							</div>
							<!-- <figcaption class="figure-caption text-end">해당 지역 취위생사들의 평균 임금으로서, 실제임금과 오차가 있을 수 잇으며,<br> 경력, 계약형태 등에
								따라 차이가 크므로 참고자료로만 활용하세요.</figcaption> -->
						</figure>
						<figure class="figure figurebox">
							<div id="employee_list" class="row justify-content-between list_box">
								<div class="col-1 s_bodyIcon"><img
										src="${pageContext.request.contextPath}/img/people.png" alt="">
								</div>
								<p class="col fs-5 text-end list_textBox" id="employeeList"></p>
							</div>
							<!-- <figcaption class="figure-caption text-end">해당 지역 취위생사들의 평균 임금으로서, 실제와 오차가 있을 수 잇으며,<br>
								경력, 계약형태 등에
								따라 차이가 크므로 참고자료로만 활용하세요.</figcaption> -->
						</figure>
						<figure class="figure figurebox">
							<div id="area-size_list" class="row list_box">
								<div class="col-1 s_bodyIcon"><img
										src="${pageContext.request.contextPath}/img/hospital.png" alt="">
								</div>
								<div class="col fs-5 text-end list_textBox" id="areasizeList"></div>
							</div>
							<!-- <figcaption class="figure-caption text-end">임대료는 표본데이터 부족으로 실제시세와 오차가 있을 수 있으며,<br> 입지,
								건물상태,
								인지성에 따라 차이가
								크므로 참고자료로만 활용하세요.</figcaption> -->
						</figure>
						<figure class="figure figurebox">
							<div id="dept-amount_list" class="row list_box">
								<div class="col-1 s_bodyIcon"><img src="${pageContext.request.contextPath}/img/dept.png"
										alt="">
								</div>
								<div class="col fs-5 text-end list_textBox" id="deptamountList"></div>
							</div>
							<!-- <figcaption class="figure-caption text-end"></figcaption> -->
						</figure>
						<figure class="figure figurebox">
							<div id="income_list" class="row list_box">
								<div class="col-1 s_bodyIcon"><img
										src="${pageContext.request.contextPath}/img/income.png" alt="">
								</div>
								<div class="col fs-4 text-end list_textBox" id="incomeList"></div>
							</div>
							<!-- <figcaption class="figure-caption text-end"></figcaption> -->
						</figure>
						<!-- <span id="area-size-display"></span>
						<span id="employee-count-display"></span>
						<span id="proemployee-count-display"></span>
						<span id="dept-amout-display"></span> -->
					</div>
					<div class="modal-footer">
						<button class="btn btn-primary" data-bs-target="#detailModal"
							data-bs-toggle="modal">재입력</button>
						<button type="button" class="btn btn-secondary" data-bs-dismiss="modal">닫기</button>
					</div>
				</div>
			</div>
		</div>


		<!--nav------------------------------------------------------------------------------------------->
		<%@ include file="/jsp/common/header.jsp" %>
			<div style="height: 60px; width: 100px;"></div>

			<!--------------------- M a p  ----------------------->
			<div class="container" id="titleContainer">
				<h1 style="font-weight: bolder;">매출 분석</h1>
			</div>

			<div class="container" id="mapContainer">
				<div class="row">
					<!-- <div id="regionSearch" class="boxShadow col-md-12 col-sm-12 col-xs-12">
						<span> <input type="text" name="selectRegion" id="selectRegion" placeholder="지역을 입력해주세요.">
							<button id="searchBtn" class="btn btn-outline-secondary btn-sm">검색</button>
						</span>
					</div> -->
					<div id="map3" class="col-lg-12 col-md-12 col-sm-12 col-xs-12"></div>
				</div>
			</div>

			<!-- <div class="container-fluid" id="container">
				<div class="row">
					<div class="col-lg-3 col-md-3 col-sm-12 col-xs-12" id="sidebar">
				
						<h3>사이드바 내용</h3>
						<p>사</p>
						<p>이</p>
						<p>드</p>
						<p>바</p>
					</div> -->

			<!-- 지도 섹션 -->
			<!-- <div class="col-lg-9 col-md-9 col-sm-12 col-xs-12" id="map3">
						<div class="row">
							<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12" id="regionSearch">
								<span>
									<input type="text" name="selectRegion" id="selectRegion" placeholder="지역을 입력해주세요."
										class="form-control">
									<button id="searchBtn" class="btn btn-outline-secondary btn-sm">검색</button>
								</span>
							</div>
						</div>
						<div class="row">
							<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12" id="map3"></div>
						</div>
						<div class="row">
							<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12" id="explain">ㄹ
							</div>
						</div>
					</div>
				</div>
			</div> -->
			<!-------------------------------------- ------------------------------------------------------->

			<%@ include file="/jsp/common/footer.jsp" %>

				<script src="${pageContext.request.contextPath}/js/search.js"></script>
	</body>

	</html>