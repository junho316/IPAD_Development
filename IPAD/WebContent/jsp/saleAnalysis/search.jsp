<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
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
		<div class="modal fade" id="detailModal" tabindex="-1" aria-labelledby="detailModalLabel" aria-hidden="true">
			<div class="modal-dialog modal-dialog-centered">
				<div class="modal-content">
					<form action="analyze.do" method="get" target="result" onsubmit="newpage();">
						<div class="modal-header">
							<h5 class="modal-title" id="detailModalLabel">상세 분석</h5>
							<button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
						</div>
						<div class="modal-body">
							<div>
								<label for="area-size" class="col-form-label">예정 평 수</label> <input type="text" class="form-control"
									name="area-size" id="area-size">
							</div>
							<div>
								<label for="employee-count" class="col-form-label">예정 종업원
									수</label> <input type="text" class="form-control" name="employee-count" id="employee-count">
							</div>
							<div>
								<label for="dept-amount" class="col-form-label">월 대출 상환
									금액</label> <input type="text" class="form-control" name="dept-amount" id="dept-amount">
								<div class="form-text" id="dept-amountHelp">만 원단위로 입력해 주세요.</div>
							</div>
							<div>
								<input type="hidden" name="area-name" value="" id="area-name">
								<input type="hidden" name="cal" value="">
							</div>
						</div>
						<div class="modal-footer">
							<button type="submit" class="btn btn-primary">입력</button>
							<button type="button" class="btn btn-secondary" data-bs-dismiss="modal">닫기</button>
						</div>
					</form>
				</div>
			</div>
		</div>

		<!--nav------------------------------------------------------------------------------------------->
		<%@ include file="/jsp/common/header.jsp" %>
			<div style="height: 60px; width: 100px;"></div>

			<!--------------------- M a p  ----------------------->

			<div class="container-fluid">
				<div id="map3" class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
					<div class="row" id="location">
						<span id="searchD" class="col-lg-3 col-md-6 col-sm-12 col-xs-12 text-nowrap"></span>
					</div>
					<div id="backSearch" class="col-lg-3 col-md-6 col-sm-12 col-xs-12">
					</div>
					<div id="searchInfo" class="col-lg-3 col-md-6 col-sm-12 col-xs-12">
						<h3>분 석 하 기</h3>
						<div class="border"></div>
						<div class="row" id="infoSection">
							<div id="selectSection" class="boxShadow">행정구역</div>
							<div id="regionSearch">
								<span> <input type="text" name="selectRegion" id="selectRegion" placeholder="지역을 입력해주세요.">
									<button id="searchBtn" class="btn btn-outline-secondary btn-sm">검색</button>
								</span>
							</div>
							<div class="col-lg-10" id="firstLow">
								<div class="box" onclick="">
									<div class="iconBox" name="population">
										<img src="${pageContext.request.contextPath}/img/people.png" alt="">
									</div>
									<p>인구</p>
								</div>
								<div class="box" onclick="">
									<div class="iconBox" name="floatingPp">
										<img src="${pageContext.request.contextPath}/img/population.png" alt="">
									</div>
									<p>유동인구</p>
								</div>
								<div class="box">
									<div class="iconBox" name="houseHold">
										<img src="${pageContext.request.contextPath}/img/apart.png" alt="">
									</div>
									<p>세대수</p>
								</div>
							</div>
							<div class="col-lg-10" id="secondLow">
								<div class="box" onclick="">
									<div class="iconBox" name="income">
										<img src="${pageContext.request.contextPath}/img/income.png" alt="">
									</div>
									<p>평균소득</p>
								</div>
								<div class="box" onclick="">
									<div class="iconBox" name="sale">
										<img src="${pageContext.request.contextPath}/img/won.png" alt="">
									</div>
									<p>매출</p>
								</div>
								<div class="box" onclick="">
									<div class="iconBox" name="dentalClinic">
										<img src="${pageContext.request.contextPath}/img/hospital.png" alt="">
									</div>
									<p>병원</p>
								</div>
							</div>

							<div class="col-lg-9" id="pickSubject">
								<select class="form-select" name="subject" id="subject">
									<option value="">진료 과목을 선택하세요.</option>
									<option value="치과" selected>치과</option>
								</select>
							</div>


							<div class="col-log-9" id="simpleSearch">
								<button type="submit" class="btn btn-outline-primary" disabled id="searchButton"
									data-bs-toggle="collapse" data-bs-target="#simpleInfo" aria-expanded="false"
									aria-controls="simpleInfo">
									<h3>간단 분석</h3>
								</button>
							</div>


							<div class="col-log-9" id="detailSearch">
								<button type="button" class="btn btn-outline-primary" id="detailButton" data-bs-toggle="modal"
									data-bs-target="#detailModal" disabled>
									<h3>상세 분석</h3>
								</button>
							</div>
						</div>
					</div>
					<div style="position: relative;" id="infoBox">
						<div class="col-lg-3 col-md-6 col-sm-12 col-xs-12 collapse collapse-horizontal " id="simpleInfo"
							style="overflow: scroll; overflow-x: hidden; height: 90vh; position: absolute;">
							<button type="button" class="btn-close" aria-label="Close" id="simpleClose"></button>
							<div id="choiceBox">
								<div id="regionChoice"></div>
								<div id="subjectChoice"></div>
							</div>
							<div id="salePredict">
								<div id="bodyIcon" class="bodyIcon">
									<img src="${pageContext.request.contextPath}/img/content.png" alt="">
								</div>
								<c:forEach var="dto" items="${info}">						
							
								<div class="textBox" id="salePredictText">월 평균 추정 매출은 ${dto.sale}만원 입니다.</div>
								</c:forEach>
							</div>
							<!-- <div class="chartBox"> 
						<canvas id="salePredictChart"></canvas>
					</div> -->
							<div id="population">
								<div id="bodyIcon" class="bodyIcon">
									<img src="${pageContext.request.contextPath}/img/people.png" alt="">
								</div>
								<div class="textBox" id="populationText"></div>
							</div>
							<div class="chartBox">
								<canvas id="populationChart"></canvas>
							</div>
							<div class="roundChartBox">
								<div class="comment"></div>
								<canvas id="populationRoundChart"></canvas>
							</div>
							<div id="floatingPp">
								<div id="bodyIcon" class="bodyIcon">
									<img src="${pageContext.request.contextPath}/img/population.png" alt="">
								</div>
								<div class="textBox" id="floatingPpText"></div>
							</div>
							<div class="chartBox">
								<canvas id="floatingPpChart"></canvas>
							</div>
							<div class="roundChartBox">
								<canvas id="floatingPpRoundChart"></canvas>
							</div>
							<div id="countHospital">
								<div id="bodyIcon" class="bodyIcon">
									<img src="${pageContext.request.contextPath}/img/hospital.png" alt="">
								</div>
								<div class="textBox" id="countHospitalText"></div>
							</div>
							<div class="chartBox">
								<canvas id="countHospitalChart"></canvas>
							</div>
							<div class="blank"></div>
						</div>

						<div class="col-lg-3 col-md-6 col-sm-12 col-xs-12 collapse collapse-horizontal " id="detailInfo"
							style="overflow: scroll; overflow-x: hidden; height: 90vh; position: absolute;">
							<button type="button" class="btn-close" aria-label="Close" id="detailClose"></button>
						</div>
					</div>
					<div class="searchInfoIcon" id="searchInfoIcon">
						<button type="button" class="btn btn-primary">분석 하기</button>
					</div>
				</div>
			</div>
			<script src="${pageContext.request.contextPath}/js/search.js"></script>

	</body>

	</html>