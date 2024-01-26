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
	href="${pageContext.request.contextPath}/css/hospitalStatus.css">
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css">
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link rel="stylesheet"
	href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@500&display=swap">
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.5/font/bootstrap-icons.css">
<!-- script -->

<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
<script src="https://code.jquery.com/jquery-3.7.1.min.js"></script>
<title>상권현황</title>
<script>
	var contextPath = "${pageContext.request.contextPath}";
</script>
</head>

<body class="vh-100 overflow-hiddden">
	<!--nav------------------------------------------------------------------------------------------->
	<%@ include file="/jsp/common/header.jsp"%>
	<div style="height: 60px; width: 100px;"></div>

	<!-- 배경  -->
	<div class="container-fluid" style="background-color: #F8F9FA;">
		<div style="height: 50px; width: 100px;"></div>

		<!--치과현황--------------------------------------------------->
		<div class="container ">
			<h1 class="bolder">치과의원 현황</h1>
		</div>

		<div id="OpenCloseHos" class="container boxShadow">
			<div style="height: 50px; width: 100px; margin-top: 50px;"></div>

			<h4 style="float: left;">
				개폐업 현황 <i class="bi bi-question-circle" id="firstBtn"></i>
			</h4>

			<div id="firstInfo">위례 신도시 연도별 치과의원 개·폐업 수</div>

			<div id="chartArea" class="col-sm-12">
				<canvas id="openCloseHosChart" style="width: 100%;"></canvas>
			</div>
			<div id="OpenCloseHosTable" class="table-responsive">
				<table
					class=" table table-bordered border-black align-middle text-center">
					<thead>
						<tr>
							<th></th>
							<c:forEach var="dto" items="${OpenCloseCount}">
								<th>${dto.year}년</th>
							</c:forEach>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td>개업</td>
							<c:forEach var="dto" items="${OpenCloseCount}">
								<td>${dto.openings}</td>
							</c:forEach>
						</tr>
						<tr>
							<td>폐업</td>
							<c:forEach var="dto" items="${OpenCloseCount}">
								<td>${dto.closures}</td>
							</c:forEach>
						</tr>
					</tbody>
				</table>
			</div>
		</div>


		<!--치과수 현황---------------------------------------------------------------  -->
		<div id=" numOfHos" class=" container boxShadow">
			<div style="height: 50px; width: 100px; margin-top: 50px;"></div>
			<h4 style="float: left;">
				치과수 현황 <i class="bi bi-question-circle" id="thirdBtn"></i>
			</h4>

			<div id="thirdInfo">지역별 치과의원 수 추이</div>


			<div id="numOfHosTable" class="table-responsive">
				<table
					class="table table-bordered border-black align-middle text-center">
					<thead>
						<tr>
							<th></th>
							<c:set var="prevYear" value="" />
							<c:forEach var="dto" items="${HospitalCount}">
								<c:if test="${dto.year ne prevYear}">
									<th>${dto.year}</th>
								</c:if>
								<c:set var="prevYear" value="${dto.year}" />
							</c:forEach>

						</tr>
					</thead>
					<tbody>


						<tr>
							<td>송파구 위례동</td>
							<c:forEach var="dto" items="${HospitalCount}">
								<c:if test="${dto.region eq '송파구'}">
									<td>${dto.count}</td>
								</c:if>
							</c:forEach>
						</tr>
						<tr>
							<td>성남시 위례동</td>
							<c:forEach var="dto" items="${HospitalCount}">
								<c:if test="${dto.region eq '성남시'}">
									<td>${dto.count}</td>
								</c:if>
							</c:forEach>
						</tr>
						<tr>
							<td>하남시 위례동</td>
							<c:forEach var="dto" items="${HospitalCount}">
								<c:if test="${dto.region eq '하남시'}">
									<td>${dto.count}</td>
								</c:if>
							</c:forEach>
						</tr>
					</tbody>
				</table>
			</div>
		</div>

		<!--치과당 인구수---------------------------------------------------------------  -->
		<div id=" popuDivideHos" class="container boxShadow">
			<div style="height: 50px; width: 100px; margin-top: 50px;"></div>

			<h4 style="float: left;">
				치과 1개당 인구수 <i class="bi bi-question-circle" id="secondBtn"></i>
			</h4>

			<div id="secondInfo">주거 인구를 치과의 수로 나눈 값</div>


			<div id="popuDivideHosTable" class="table-responsive">
				<table
					class="table table-bordered border-black align-middle text-center">
					<thead>
						<tr>
							<th></th>
							<th>치과 1개당 인구수</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td>송파구 위례동</td>
							<c:forEach var="dto" items="${HospitalPopulation}">
								<c:if test="${dto.region eq '송파구'}">
									<td>${dto.population}</td>
								</c:if>
							</c:forEach>
						</tr>
						<tr>
							<td>성남시 위례동</td>
							<c:forEach var="dto" items="${HospitalPopulation}">
								<c:if test="${dto.region eq '성남시'}">
									<td>${dto.population}</td>
								</c:if>
							</c:forEach>
						</tr>
						<tr>
							<td>하남시 위례동</td>
							<c:forEach var="dto" items="${HospitalPopulation}">
								<c:if test="${dto.region eq '하남시'}">
									<td>${dto.population}</td>
								</c:if>
							</c:forEach>
						</tr>
					</tbody>
				</table>
			</div>
		</div>
		<div style="height: 50px; width: 100px;"></div>
	</div>

	<!-------------------------------------- ------------------------------------------------------->


	<%@ include file="/jsp/common/footer.jsp"%>>

	<script src="${pageContext.request.contextPath}/js/hospitalStatus.js"></script>
	<script
		src="${pageContext.request.contextPath}/js/openCloseHosChart.js"></script>

</body>

</html>