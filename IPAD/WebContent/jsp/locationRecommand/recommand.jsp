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
	href="${pageContext.request.contextPath}/css/recommand.css">
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css"
	rel="stylesheet">
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com">
<link rel="stylesheet"
	href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@500&display=swap">
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.5/font/bootstrap-icons.css">
<!-- script -->
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
<script src="https://code.jquery.com/jquery-3.7.1.min.js"></script>
<title>추천 지역</title>
<script>
	var contextPath = "${pageContext.request.contextPath}";
</script>
</head>

<body class="vh-100 overflow-hiddden">
	<!--nav------------------------------------------------------------------------------------------->
	<%@ include file="/jsp/common/header.jsp"%>
	<div style="height: 60px; width: 100px;"></div>


	<!-- map 과 메뉴 ------------------------------------------------------------------------------------->
	<div class="container">
		<div style="height: 50px; width: 100px;"></div>
		<!-- <h1>지도</h1> -->
		<!-- <div style="height: 50px; width: 100px;"></div> -->

	</div>

	<div class="container">
		<div class="row">
			<div class="col-lg-3">
				<div class="boxShadow vertical1">

					<div class="section">
						<div class="subtitle">희망 분야</div>
						<div style="height: 20px; width: 100px;"></div>
						<form action="submit.do" method="post">
							<table class="table">
								<tbody>
									<tr>
										<td>임플란트</td>
										<td><input type="checkbox" name="implant" value="implant"
											id="implant"></td>
									</tr>
									<tr>
										<td>교정</td>
										<td><input type="checkbox" name="orthodontics"
											value="orthodontics" id="orthodontics"></td>
									</tr>
								</tbody>
							</table>
							<input id="searchBtn" type="button" value="추천 지역 검색"
								onclick="getRankList();">
						</form>
					</div>
				</div>
				<div class="boxShadow vertical2">
					<div style="height: 20px; width: 100px;"></div>
					<div class="section">
						<div class="subtitle">추천 지역</div>
						<div style="height: 20px; width: 100px;"></div>
						<table id="areaTable" class="table">
							<tbody>
								<tr>
									<td>1.</td>
									<td id="first" onclick="selectRegion(event);">-</td>
									<td></td>
								</tr>
								<tr>
									<td>2.</td>
									<td id="second" onclick="selectRegion(event);">-</td>
									<td></td>
								</tr>
								<tr>
									<td>3.</td>
									<td id="third" onclick="selectRegion(event);">-</td>
									<td></td>
								</tr>
							</tbody>
						</table>
						<div style="height: 50px; width: 100px;"></div>
						<div class="subtitle" id="regionDetail">지역 정보</div>
						<div style="height: 20px; width: 100px;"></div>
						<table id="forecastTable" class="table">
							<thead>
								<tr>
									<td>예상 환자 수(월)</td>
									<td id="patient">-</td>

								</tr>
								<tr>
									<td>추천 간호사수</td>
									<td id="employee">-</td>
								</tr>
								<tr>
									<td>추천 평수</td>
									<td id="size">-</td>
								</tr>
								<tr>
									<td>예상 순수익</td>
									<td id="predictSale">-</td>
								</tr>
							</tbody>
						</table>
					</div>

				</div>
			</div>
			<!-- 지도 -->
			<div class="col-lg-9">
				<div>
					<div id="map" class="boxShadow" style="width: 100%;"></div>
				</div>
			</div>

		</div>
	</div>
	<!-- 설명 -->
	<div class="container">
		<!-- <h3>설명</h3> -->
		<div id="explain" class="boxShadow">
			※ 회귀분석으로 치과의원의 예상 매출과 환자 수를 구하는 회귀식을 구해 지역별 예상 매출, 환자 수 정보를 제공합니다.<br>
			※ 지역별 예상 매출과 연령대 인구수에 기반하여 사용자의 선택에 따른 점수를 부여하고, 이를 종합하여 높은 점수를 가진 지역을 추천합니다.<br>
			※ 예상 환자 수에 따라 의료법 의료기관 의료인 정원 기준을 만족하는 간호사 수를 추천합니다.<br> 
			※ 서울시의 평균 환자 수와 병원 평수를 기준으로 예상 환자에 따른 병원 평수를 추천합니다.<br>
			※ 추천 직원수와 추천 평수의 임대료로 예상 순수익 계산합니다.<br>
		</div>
	</div>
	<div style="height: 50px; width: 100px;"></div>


	<!--------------------------------------푸터 ------------------------------------------------------->

	<%@ include file="/jsp/common/footer.jsp"%>>

	<script type="text/javascript"
		src="//dapi.kakao.com/v2/maps/sdk.js?appkey=0983003a235a4d3c0ae25870de9c471a&libraries=services"></script>

	<script
		src="${pageContext.request.contextPath}/js/locationRecommand.js"></script>

</body>

</html>