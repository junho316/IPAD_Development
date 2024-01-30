<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">

<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css"
	rel="stylesheet">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/index.css">

<link rel="preconnect" href="https://fonts.gstatic.com">
<link rel="stylesheet"
	href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@500&display=swap">

<script src="https://code.jquery.com/jquery-3.7.1.min.js"></script>
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>

<link rel="preconnect" href="https://fonts.googleapis.com">
<title>개원 지역 추천</title>
</head>
<link rel="icon" href="data:;base64,iVBORw0KGgo=">

<body class="vh-100 overflow-hiddden">
	<%@ include file="/jsp/common/header.jsp"%>

	<div style="display: flex; margin-top: 100px;" class="container">
		<!--<div class="container mt-5">
					<h2>개원 지역 추천</h2>

					<form>
						
						<div class="form-group">
							<label for="expectedArea">예정 평수:</label> <input type="number" class="form-control"
								id="expectedArea" placeholder="평수를 입력하세요">
						</div>

						<div class="form-group">
							<label for="expectedEmployees">예정 고용인 수:</label> <input type="number" class="form-control"
								id="expectedEmployees" placeholder="고용인 수를 입력하세요">
						</div>

						
						<div class="form-group">
							<label for="loanRepayment">대출 월 상환(만원):</label> <input type="number" class="form-control"
								id="loanRepayment" placeholder="대출 월 상환금을 입력하세요">
						</div>

						 
						<div class="form-group">
							<label for="initialInvestment">초기 투자금:</label> <input type="number" class="form-control"
								id="initialInvestment" placeholder="초기 투자금을 입력하세요">
						</div>

						

						<button type="submit" class="btn btn-primary">히스토리 저장</button>
					</form>
				</div> -->
		<div class="container mt-5">
			<h2>상업 입지 선택</h2>

			<form>
				<fieldset style="margin-top: 1%;">
					<legend>전문 분야</legend>

					<input type="checkbox" name="ageGroup" id="youthRadio"
						value="youth"> <label for="youthRadio">임플란트</label> <input
						type="checkbox" name="ageGroup" id="middleAgeRadio"
						value="middleAge"> <label for="middleAgeRadio">교정</label>
					<input type="submit" value="검색" class="btn btn-primary"
						style="position: right;">
				</fieldset>




			</form>

			<!-- 추천해주는 수에 따라 최대 3개 div작성 -->
			<div class="row">
				<div class="col-md-4">

					<div id="result1" class="border rounded" style="min-height: 100px;">지역1</div>
				</div>
				<div class="col-md-4">

					<div id="result2" class="border rounded" style="min-height: 100px;">지역2</div>
				</div>
				<div class="col-md-4">

					<div id="result3" class="border rounded" style="min-height: 100px;">지역3</div>
				</div>
			</div>

			<!-- 한 지역 확인창 -->





		</div>
	</div>
	<div class="container">asdfsadfsadf</div>

	<%@ include file="/jsp/common/footer.jsp"%>
</body>

</html>