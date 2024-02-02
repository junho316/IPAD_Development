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

		<div class="modal fade" id="modalData" aria-hidden="true" aria-labelledby="modalDataLabel" tabindex="-1">
			<div class="modal-dialog modal-dialog-centered">
				<div class="modal-content">
					<div class="modal-header">
						<div class="col fs-3 text-end" style="color: #8f8f8f;" id="listname"></div>
						<h1 class="modal-title fs-6" id="modalDataLabel">분석 결과</h1>
						<button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
					</div>
					<div class="modal-body" id="analyzeInfo">
						<figure class="figure figurebox">
							<div id="s_salePredict_list" class="row justify-content-between list_box">
								<div class="col-1 s_bodyIcon"><img src="${pageContext.request.contextPath}/img/won.png"
										alt=""></div>
								<div class="col fs-6 text-end list_textBox" id="salePredictList"></div>
							</div>
						</figure>
						<figure class="figure figurebox">
							<div id="patientPredict_list" class="row justify-content-between list_box">
								<div class="col-1 s_bodyIcon"><img
										src="${pageContext.request.contextPath}/img/dentalPatient.png" alt="">
								</div>
								<p class="col fs-6 text-end list_textBox" id="patientList"></p>
							</div>
							<figcaption class="figure-caption text-end">해당 지역 취위생사들의 평균 임금으로서, 실제임금과 오차가 있을 수 잇으며,<br>
								경력, 계약형태 등에
								따라 차이가 크므로 참고자료로만 활용하세요.</figcaption>
						</figure>
						<figure class="figure figurebox">
							<div id="employee_list" class="row justify-content-between list_box">
								<div class="col-1 s_bodyIcon"><img
										src="${pageContext.request.contextPath}/img/people.png" alt="">
								</div>
								<p class="col fs-6 text-end list_textBox" id="employeeList"></p>
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
								<div class="col fs-6 text-end list_textBox" id="areasizeList"></div>
							</div>

						</figure>
						<figure class="figure figurebox">
							<div id="dept-amount_list" class="row list_box">
								<div class="col-1 s_bodyIcon"><img src="${pageContext.request.contextPath}/img/dept.png"
										alt="">
								</div>
								<div class="col fs-6 text-end list_textBox" id="deptamountList"></div>
							</div>
						</figure>
						<figure class="figure figurebox">
							<div id="income_list" class="row list_box">
								<div class="col-1 s_bodyIcon"><img
										src="${pageContext.request.contextPath}/img/income.png" alt="">
								</div>
								<div class="col fs-5 text-end list_textBox" id="incomeList"></div>
							</div>
						</figure>
					</div>
					<div class="modal-footer">
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
				<div style="height: 50px; width: 100px;"></div>
			</div>
			<div class="container">
				<div class="row">
					<div class="col-lg-3">
						<div class="boxShadow vertical1">
							<div class="inputSection">
								<div class="subtitle">희망 정보</div>
								<form action="netprofit.do" method="POST" target="result" id="infoForm"
									enctype="application/x-www-form-urlencoded" onsubmit="return validateForm();">
									<table class="table">
										<tbody>
											<tr>
												<td>
													<label for="area-size" class="col-form-label">개원예정 규모</label>
													<input type="number" class="form-control" name="areaSize"
														id="area-size" min="15" max="150">
													<p id="sizePredictText" style="font-size: 0.8rem;"></p>
												</td>
											<tr>
											<tr>
												<td>
													<label for="senior-employee-count" class="col-form-label">주임급 고용 예정
														간호사</label>
													<input type="number" class="form-control" name="seniorEmployeeCount"
														id="senior-employee-count" min="0" max="10">
												</td>
											</tr>
											<tr>
												<td>
													<label for="junior-employee-count" class="col-form-label">신입급 고용 예정
														간호사</label>
													<input type="number" class="form-control" name="juniorEmployeeCount"
														id="junior-employee-count" min="0" max="10">
													<p id="employeePredictText" style="font-size: 0.8rem;">
													</p>
												</td>
											<tr>
											<tr>
												<td>
													<label for="dept-amount" class="col-form-label">월 대출 상환 금액</label>
													<input type="number" class="form-control" name="deptamount"
														id="dept-amount" min="0" max="10000">
													<div class="form-text" id="dept-amountHelp">만 원단위로 입력해 주세요.</div>
													<input type="hidden" name="area-name" value="" id="area-name">
													<input type="hidden" name="regionCode" value="" id="area-code">
												</td>
											</tr>
										</tbody>
									</table>
									<button type="submit" class="btn btn-primary" id="submitButton">입력</button>
								</form>
							</div>
						</div>
					</div>
					<!-- 지도 -->
					<div class="col-lg-9">
						<div>
							<div id="map3" class="boxShadow"></div>
						</div>
					</div>
				</div>
			</div>
			<!-- 설명 -->
			<div class="container">
				<div class="boxShadow" style="height: 200px; margin-bottom: 50px;">
					<h3>설명</h3>
				</div>
			</div>

			<!-------------------------------------- ------------------------------------------------------->

			<%@ include file="/jsp/common/footer.jsp" %>

				<script src="${pageContext.request.contextPath}/js/search.js"></script>
	</body>

	</html>