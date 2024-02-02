<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">

<head>
<meta charset="UTF-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />

<!-- css,font -->

<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css"
	rel="stylesheet">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/index.css">
<link rel="preconnect" href="https://fonts.gstatic.com">
<link rel="stylesheet"
	href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@500&display=swap">

<!-- 스크립트 -->
<script src="https://code.jquery.com/jquery-3.7.1.min.js"></script>
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>

<link rel="preconnect" href="https://fonts.googleapis.com">
<script src="${pageContext.request.contextPath}/js/main.js"></script>

<title>Main Page</title>
</head>

<body class="vh-100 overflow-hiddden">
	<!--nav------------------------------------------------------------------------------------------->
	<%@ include file="/jsp/common/header.jsp"%>

	<!--carousel ------------------------------------------------------------->
	<div>
		<div id="mainCarousel" class="carousel slide mb-6"
			data-bs-ride="carousel" data-bs-interval="4000">
			<!-- 하단 현재위치  -->
			<div class="carousel-indicators">
				<button type="button" data-bs-target="#mainCarousel"
					data-bs-slide-to="0" class="active">
					<span></span>
				</button>
				<button type="button" data-bs-target="#mainCarousel"
					data-bs-slide-to="1">
					<span></span>
				</button>
				<button type="button" data-bs-target="#mainCarousel"
					data-bs-slide-to="2">
					<span></span>
				</button>
			</div>

			<!-- carousel page1  -->
			<div class="carousel-inner">
				<div class="carousel-item active">
					<img src="img/carousel1.jpg" alt="">
					<div class="container">
						<div class="carousel-caption text-start">
							<div id="title"></div>
							<div id="detail" class="col-xs-12 col-sm-12 col-lg-6">
								치과 개원을 준비하는 의사를 위한 입지 분석<br>
								<!-- 유동인구, 치과 현황을 통한 입지분석 -->
							</div>
							<!-- <button type="button">
								<div class="temp">
									<a href="">자세히 알아보기</a>
								</div>
							</button> -->

						</div>
					</div>
				</div>

				<!-- carousel page2 -->
				<div class="carousel-item">
					<img src="img/carousel2.jpg" alt="">
					<div class="container">
						<div class="carousel-caption text-start">
							<div id="title">주거 인구 예측 서비스</div>
							<div id="detail" class="col-xs-12 col-sm-12 col-lg-6">주택 ·
								인구 데이터 분석을 통해 주거 인구 예측</div>
							<!-- <button type="button"><a href="">자세히 알아보기</a></button> -->
						</div>
					</div>
				</div>

				<!-- carousel page3 -->
				<div class="carousel-item">
					<img src="img/carousel3.jpg" alt="">
					<div class="container">
						<div class="carousel-caption text-start">
							<div id="title">매출 예측 서비스</div>
							<div id="detail" class="col-xs-12 col-sm-12 col-lg-6">빅데이터
								분석을 통한 매출 분석 서비스</div>
							<!-- <button type="button"><a href="">자세히 알아보기</a></button> -->
						</div>
					</div>
				</div>

				<!-- carousel 좌우 버튼 -->
				<button class="carousel-control-prev" type="button"
					data-bs-target="#myCarousel" data-bs-slide="prev"></button>
				<button class="carousel-control-next" type="button"
					data-bs-target="#myCarousel" data-bs-slide="next"></button>
			</div>
		</div>
	</div>

	<!-- 본문1 - 입지분석 팀 ------------------------------------------------------------------------------>

	<div id="content1" class="container-fluid">
		<div class="container-fluid">
			<div class="row">
				<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
					<h1 id="analysisTitle">
						치과의원 개업 시 입지 선정의 <br>중요 Point
					</h1>
				</div>
			</div>

			<div class="container">
				<div class="row">
					<div id="analysisLeft" class="col-lg-6 ">
						<img src="img/mainFirstContent1.png" alt="">
					</div>
					<div id="analysisRight" class="col-lg-6 ">
						<div>
							<h2>인구 분석</h2>
							<img src="img/mainFirstContent2.jpg" alt="">
							<h6>
								신도시 유동인구와 거주인구를 파악하여 <br> 어느입지에 치과를 설립해야 유리할까?
							</h6>
							<a href="populationStatus.html" class="btn">자세히 보기</a>
						</div>
						<div>
							<h2>치과 현황</h2>
							<img src="img/mainFirstContent2.jpg" alt="">
							<h6>
								주변의 치과의원 수와 <br> 개업 · 폐업의 수를 파악하여 개업이 가능할까?
							</h6>
							<a href="hospitalStatus.html" class="btn">자세히 보기</a>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- 본문2 - 매출 분석 ------------------------------------------------------------------------------>

	<div id="content2" class="container-fluid">
		<div class="container-fluid">
			<div class="row">
				<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
					<h1 id="analysisTitle">성공적인 개업을 위한 매출 분석</h1>
					<h6>
						정확한 분석을 원하시면 <a href="search.html">여기</a>를 클릭해주세요
					</h6>
				</div>
			</div>

			<div id="content2Bottom" class="container">
				<div class="row">
					<div id="content2Left" class="col-lg-6">
						<img src="img/mainSecondContent1.png" alt="">
					</div>
					<div id="content2Right" class="col-lg-6">
						<div>
							<div>
								<img src="img/mainSecondContent2.png" alt="">
							</div>
							<div>
								<img src="img/mainSecondContent3.png" alt="">
							</div>
						</div>
						<div>
							<div>
								<img src="img/mainSecondContent4.png" alt="">
							</div>
							<div>
								<img src="img/mainSecondContent5.png" alt="">
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>

	<!-- 본문3 - Q&A ------------------------------------------------------------------------------>

	<div class="container">
		<div class="row">

			<!-- 본문3 왼쪽 -->
			<div id="boardLeft" class="col-lg-6">
				<img src="img/mainThirdContent.jpg" alt="">
			</div>

			<!-- 본문3 오른쪽(게시판)------------------------------------------------------------------------------------------->
			<div id="boardRight" class="col-lg-6 ">
				<div id="title">
					<a href="#">Q&A</a>
				</div>
				<table class="table">
					<colgroup>
						<col width=10%>
						<col width=60%>
						<col width=30%>
					</colgroup>
					<thead>
					</thead>
					<tbody>
						<tr>
							<th>1</th>
							<td><a href="">게시글 1</a></td>
							<td>2022-02-22</td>
						</tr>
						<tr>
							<th>2</th>
							<td><a href="">게시글 2</a></td>
							<td>2022-02-22</td>
						</tr>
						<tr>
							<th>3</th>
							<td><a href="">게시글 3</a></td>
							<td>2022-02-22</td>
						</tr>
						<tr>
							<th>4</th>
							<td><a href="">게시글 4</a></td>
							<td>2022-02-22</td>
						</tr>
						<tr>
							<th>5</th>
							<td><a href="">게시글 5</a></td>
							<td>2022-02-22</td>
						</tr>
						<tr>
							<th>6</th>
							<td><a href="">게시글 6</a></td>
							<td>2022-02-22</td>
						</tr>
						<tr>
							<th>7</th>
							<td><a href="">게시글 7</a></td>
							<td>2022-02-22</td>
						</tr>
						<tr>
							<th>8</th>
							<td><a href="">게시글 8</a></td>
							<td>2022-02-22</td>
						</tr>
					</tbody>
				</table>
			</div>
		</div>
	</div>

	<!--footer------------------------------------------------------------------------------------------->

	<%@ include file="/jsp/common/footer.jsp"%>

</body>
</html>