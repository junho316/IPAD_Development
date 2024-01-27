<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html lang="ko">

<head>
<meta charset="UTF-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<!-- css,font -->
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/population.css">
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css">
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com">
<link rel="stylesheet"
	href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@500&display=swap">
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.5/font/bootstrap-icons.css">

<!-- script -->
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/chart.js"></script>

<title>인구분석</title>
<script>
	var contextPath = "${pageContext.request.contextPath}";
</script>
</head>

<body class="vh-100 overflow-hiddden">
	<!--nav------------------------------------------------------------------------------------------->
	<%@ include file="/jsp/common/header.jsp"%>
	<div style="height: 60px; width: 100px;"></div>

	<!-- 배경 -->
	<div class="container-fluid" style="background-color: #F8F9FA;">
		<div style="height: 50px; width: 100px;"></div>

		<!--유동인구------------------------------------------------------------------------------------------->

		<div class="container">
			<h1 style="font-weight: bolder;">유동인구</h1>
		</div>

		<!--분기별 유동인구 -->
		<div id="quarterlyPop" class="container boxShadow">
			<div style="height: 50px; width: 100px; margin-top: 50px;"></div>

			<!-- 설명 콜랩스 -->
			<h4 style="float: left;">
				분기별 유동인구 <i class="bi bi-question-circle" id="firstBtn"></i>
			</h4>

			<div id="firstInfo">분기별 지역에 방문한 유동인구 수</div>

			<div id="chartArea" class="col-sm-12">
				<canvas id="quarterlyPopChart" style="width: 100%;"></canvas>
			</div>

			<div id="quarterlyPopTable" class="table-responsive">
				<table
					class="table table-bordered border-black align-middle text-center">
					<thead>
						<tr>
							<th></th>
							<c:forEach var="dto" items="${FootTraffic}">
								<th>${dto.quater}</th>
							</c:forEach>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td>송파구 위례동</td>
							<c:forEach var="dto" items="${FootTraffic}">
								<td>${dto.population_total}</td>
							</c:forEach>

						</tr>
						<tr>
							<td>성남시 위례동</td>
							<td></td>
							<td></td>
							<td></td>
							<td></td>
							<td></td>
							<td></td>
							<td></td>
						</tr>
						<tr>
							<td>하남시 위례동</td>
							<td></td>
							<td></td>
							<td></td>
							<td></td>
							<td></td>
							<td></td>
							<td></td>
						</tr>
					</tbody>
				</table>

				<div style="height: 50px; width: 100px;"></div>
			</div>

		</div>
		<div id="dayOfTheWeek" class="container boxShadow">
			<div style="height: 50px; width: 100px; margin-top: 50px;"></div>


			<!-- 설명 콜랩스 -->
			<h4 style="float: left;">
				요일별 유동인구 <i class="bi bi-question-circle" id="secondBtn"></i>
			</h4>

			<div id="secondInfo">최근 분기 지역에 방문한 요일별 평균 유동인구 수</div>

			<div id="dayOfTheWeekTable" class="table-responsive">
				<table
					class="table table-bordered border-black align-middle text-center">
					<thead>
						<tr>
							<th></th>
							<th>월</th>
							<th>화</th>
							<th>수</th>
							<th>목</th>
							<th>금</th>
							<th>토</th>
							<th>일</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td>송파구 위례동</td>
							<c:forEach var="dto" items="${FootTraffic}" varStatus="loop">
								<c:if test="${loop.last}">
									<td>${dto.population_mon}</td>
									<td>${dto.population_tues}</td>
									<td>${dto.population_wed}</td>
									<td>${dto.population_thur}</td>
									<td>${dto.population_fri}</td>
									<td>${dto.population_sat}</td>
									<td>${dto.population_sun}</td>
								</c:if>
							</c:forEach>
						</tr>
						<tr>
							<td>성남시 위례동</td>
							<td></td>
							<td></td>
							<td></td>
							<td></td>
							<td></td>
							<td></td>
							<td></td>
						</tr>
						<tr>
							<td>하남시 위례동</td>
							<td></td>
							<td></td>
							<td></td>
							<td></td>
							<td></td>
							<td></td>
							<td></td>
						</tr>
					</tbody>
				</table>
				<div style="height: 50px; width: 100px;"></div>
			</div>
		</div>

		<!--거주인구------------------------------------------------------------------------------------------->
		<div style="height: 50px; width: 100px;"></div>
		<div class="container">
			<h1 style="font-weight: bolder;">주거 인구</h1>
		</div>

		<div id="resident" class="container boxShadow">

			<div style="height: 50px; width: 100px; margin-top: 50px;"></div>


			<!-- 설명 콜랩스 -->
			<h4 style="float: left;">
				주거 인구 추이 <i class="bi bi-question-circle" id="thirdBtn"></i>
			</h4>

			<div id="thirdInfo">연도별 주거 인구</div>

			<div id="chartArea" class="col-sm-12">
				<canvas id="residentChart" style="width: 100%;"></canvas>
			</div>

			<div id="residentTable" class="table-responsive">
				<table
					class="table table-bordered border-black align-middle text-center">
					<thead>
						<tr>
							<th>지역</th>
							<c:choose>
								<c:when test="${not empty requestScope[admCd[0]]}">
									<c:set var="dtos" value="${requestScope[admCd[0]]}" />
									<c:forEach var="dto" items="${dtos}">
										<th>${dto.year}년</th>
									</c:forEach>
								</c:when>
								<c:otherwise>
									<th>No data</th>
								</c:otherwise>
							</c:choose>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="admCode" items="${admCd}">
							<tr>
								<c:choose>
									<c:when test="${not empty requestScope[admCode]}">
										<c:set var="dtos" value="${requestScope[admCode]}" />
										<td>${dtos[0].region_name_detail}</td>
										<c:forEach var="dto" items="${dtos}">
											<td>${dto.population_total}</td>
										</c:forEach>
									</c:when>
									<c:otherwise>
										<td>No data</td>
									</c:otherwise>
								</c:choose>
							</tr>
						</c:forEach>
					</tbody>
				</table>
				<div style="height: 50px; width: 100px;"></div>
			</div>

		</div>
		<div id="ageGender" class="container boxShadow">
			<div style="height: 50px; width: 100px; margin-top: 50px"></div>


			<!-- 설명 콜랩스 -->

			<h4 style="float: left;">
				성별, 연령대별 주거 인구 <i class="bi bi-question-circle" id="fourthBtn"></i>
			</h4>

			<div id="fourthInfo">최근연도 성별, 연령별 주거 인구</div>

			<div id="ageGenderTable" class="table-responsive">
				<table
					class="table table-bordered border-black align-middle text-center">
					<thead>
						<tr>
							<th rowspan=" 2"></th>
							<th colspan="2">성별</th>
							<th colspan="7">연령</th>
						</tr>
						<tr>
							<th>남성</th>
							<th>여성</th>
							<th>10대</th>
							<th>20대</th>
							<th>30대</th>
							<th>40대</th>
							<th>50대</th>
							<th>60대</th>
							<th>70대</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="admCode" items="${admCd}">
							<tr>
								<c:choose>
									<c:when test="${not empty requestScope[admCode]}">
										<c:set var="dtos" value="${requestScope[admCode]}" />
										<td>${dtos[0].region_name_detail}</td>
										<c:forEach var="dto" items="${dtos}" varStatus="loop">
											<c:if test="${loop.last}">
												<td>${dto.population_male}</td>
												<td>${dto.population_female}</td>
												<td>${dto.population_10}</td>
												<td>${dto.population_20}</td>
												<td>${dto.population_30}</td>
												<td>${dto.population_40}</td>
												<td>${dto.population_50}</td>
												<td>${dto.population_60}</td>
												<td>${dto.population_70}</td>
											</c:if>
										</c:forEach>
									</c:when>
									<c:otherwise>
										<td>No data</td>
									</c:otherwise>
								</c:choose>
							</tr>
						</c:forEach>

					</tbody>
				</table>
				<div style="height: 50px; width: 100px;"></div>
			</div>
		</div>

		<!--인구예측------------------------------------------------------------------------------------------->

		<div style="height: 50px; width: 100px;"></div>
		<div class="container">
			<h1 style="font-weight: bolder;">인구예측</h1>
		</div>

		<div id="forecast" class="container boxShadow">
			<div style="height: 50px; width: 100px; margin-top: 50px;"></div>

			<!-- 설명 콜랩스 -->

			<h4 style="float: left;">
				인구 예측 <i class="bi bi-question-circle" id="fifthBtn"></i>
			</h4>

			<div id="fifthInfo">주택 수, 가구 수, 출생 건수 등을 이용해 회귀분석하여 예측한 인구수</div>

			<div id="chartArea" class="col-sm-12">
				<canvas id="forecastChart" style="width: 100%;"></canvas>
			</div>

			<div id="forecastTable" class="table-responsive">
				<table
					class="table table-bordered border-black align-middle text-center">
					<thead>
						<tr>
							<th></th>
							<c:forEach var="dto" items="${PopulationForecast}">
								<c:if test="${dto.region eq '송파구'}">
									<th>${dto.year}년</th>
								</c:if>
							</c:forEach>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td>송파구 위례동</td>
							<c:forEach var="dto" items="${PopulationForecast}">
								<c:if test="${dto.region eq '송파구'}">
									<td>${dto.population}</td>
								</c:if>
							</c:forEach>
						</tr>
						<tr>
							<td>성남시 위례동</td>
							<c:forEach var="dto" items="${PopulationForecast}">
								<c:if test="${dto.region eq '성남시'}">
									<td>${dto.population}</td>
								</c:if>
							</c:forEach>
						</tr>
						<tr>
							<td>하남시 위례동</td>
							<c:forEach var="dto" items="${PopulationForecast}">
								<c:if test="${dto.region eq '하남시'}">
									<td>${dto.population}</td>
								</c:if>
							</c:forEach>
						</tr>
					</tbody>
				</table>
				<div style="height: 50px; width: 100px;"></div>
			</div>
		</div>

		<div style="height: 50px; width: 100px;"></div>
	</div>

	<!-- footer------------------------------------ ------------------------------------------------------->

	<%@ include file="/jsp/common/footer.jsp"%>>


	<script src="${pageContext.request.contextPath}/js/population.js"></script>
	<script src="${pageContext.request.contextPath}/js/residentChart.js"></script>
	<script src="${pageContext.request.contextPath}/js/forecastChart.js"></script>
	<script
		src="${pageContext.request.contextPath}/js/quarterlyPopChart.js"></script>
</body>

</html>