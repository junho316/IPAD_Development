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
				<div class="boxShadow vertical">

					<div class="section">
						<button id="accordion-button" type="button">전문 분야</button>
						<div style="height: 20px; width: 100px;"></div>
						<form action="submit_form.php" method="post">
							<table id="areaTable" class="table">
								<tbody>
									<tr>
										<td>임플란트</td>
										<td><input type="checkbox" name="implant" value="implant"></td>
									</tr>
									<tr>
										<td>교정</td>
										<td><input type="checkbox" name="orthodontics"
											value="orthodontics"></td>
									</tr>
								</tbody>
							</table>
							<div style="height: 20px; width: 100px;"></div>
							<input type="submit" value="검색">
						</form>
					</div>
					<div style="height: 20px; width: 100px;"></div>
					<div class="section">
						<button id="accordion-button" type="button">추천 순위</button>
						<div style="height: 20px; width: 100px;"></div>
						<table id="areaTable" class="table">
							<tbody>
								<tr>
									<td>1.</td>
									<td>-</td>
								</tr>
								<tr>
									<td>2.</td>
									<td>-</td>
								</tr>
								<tr>
									<td>3.</td>
									<td>-</td>
								</tr>
							</tbody>
						</table>
						<div style="height: 100px; width: 100px;"></div>
						<button id="accordion-button" type="button">예상 매출</button>
						<div style="height: 20px; width: 100px;"></div>
						<table id="forecastTable" class="table">
							<thead>
								<tr>
									<td>예상 매출</td>
									<td>-</td>
								</tr>
							</thead>
							<tbody>
								<tr>
									<td>추천 직원 수</td>
									<td>-</td>
								</tr>
								<tr>
									<td>추천 평수</td>
									<td>-</td>
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
		<div class="boxShadow" style="height: 200px; margin-bottom: 50px;">
			<h3>설명</h3>
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