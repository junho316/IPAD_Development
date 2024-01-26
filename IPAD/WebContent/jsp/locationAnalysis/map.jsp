<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">

<head>
<meta charset="UTF-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<!-- css,font -->
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/map.css">
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
<title>MAP Page</title>
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
				<div class=" boxShadow" style="height: 50px; margin-bottom: 50px;">
					<div id="mapBtn" class="dropdown-center">
						<div class="nav-link dropdown-toggle" role="button"
							data-bs-toggle="dropdown">
							<span id="selectArea">지역선택</span>
						</div>
						<ul class="dropdown-menu" style="border-radius: 30px;">
							<li onclick="ClickPopUpBtn(this); hanamHosLoc();">하남시 위례동</li>
							<li onclick="ClickPopUpBtn(this); songpaHosLoc();">송파구 위례동</li>
							<li onclick="ClickPopUpBtn(this); sungnamHosLoc();">성남시 위례동</li>
							<li onclick="ClickPopUpBtn(this);addArea(); everyHos();">지역전체</li>
						</ul>
					</div>
				</div>

				<div class="boxShadow" style="height: 800px; margin-bottom: 50px;">
					<div style="height: 50px; width: 100px;"></div>
					<div style="text-align: center;">
						<button id="accordion-button" type="button">지역 정보</button>
						<div style="height: 20px; width: 100px;"></div>
						<table id="areaTable" class="table"
							style="margin-left: 5%; width: 90%;">
							<thead>
								<tr>
									<!-- <th colspan="2" id="PopUpArea" style="text-align: left;">지역</th> -->
								</tr>
							</thead>
							<tbody>
								<tr>
									<td>치과 수</td>
									<td id="hospitalCount">-</td>
								</tr>
								<tr>
									<td>유동 인구</td>
									<td id="footTraffic">-</td>
								</tr>
								<tr>
									<td>주거 인구</td>
									<td id="residentPopulation">-</td>
								</tr>
								<tr>
									<td>치과 1개당 인구수</td>
									<td id="hospitalPopulation">-</td>
								</tr>
								<tr>
									<td style="width: 180px;">가장많은연령대</td>
									<td id="ageGroup">-</td>
								</tr>
							</tbody>
						</table>
						<div style="height: 100px; width: 100px;"></div>
						<button id="accordion-button" type="button">치과 주소</button>
						<div style="height: 20px; width: 100px;"></div>

						<table id="hosTable" class="table"
							style="margin-left: 5%; width: 90%;">
							<thead>
								<tr>
									<td id="hosName">치과명</td>
									<td id="hos">-</td>
								</tr>
							</thead>
							<tbody>
								<tr>
									<td id="hosLoca">위치</td>
									<td id="hosLoc">-</td>
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


	<!--------------------------------------푸터 ------------------------------------------------------->

	<%@ include file="/jsp/common/footer.jsp"%>>

	<script type="text/javascript"
		src="//dapi.kakao.com/v2/maps/sdk.js?appkey=9f622a57db8d51137b80a7e575e09fca&libraries=services"></script>

	<script
		src="${pageContext.request.contextPath}/js/locationAnalysisMap.js"></script>
</body>

</html>