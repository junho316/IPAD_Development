<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <!DOCTYPE html>
    <html lang="ko">

    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Document</title>
        <link rel="preconnect" href="https://fonts.googleapis.com">
        <link rel="preconnect" href="https://fonts.gstatic.com">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/analyze.css">
        <link rel="stylesheet"
            href="https://fonts.googleapis.com/css2?family=Black+Han+Sans&family=Do+Hyeon&family=Gothic+A1:wght@500&family=Noto+Sans+KR:wght@500&display=swap">
        <script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.bundle.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/chart.js@3.0.0/dist/chart.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/chartjs-plugin-datalabels@2.0.0"></script>
    </head>

    <body>

        <div class="container-fluid" id="analyzeInfo">
            <!-- <figure class="figure">
            <div id="s_salePredict" class="row justify-content-between">
                <div id="s_bodyIcon" class="col-1 s_bodyIcon"><img src="img/content.png" alt=""></div>
                <div class="col s_textBox text-center fs-3" id="s_salePredictText"></div>
            </div>
        </figure>
        <div class="s_chartBox">
            <canvas id="s_salePredictChart"></canvas>
        </div> -->
            <figure class="figure figurebox">
                <div class="row list_box">
                    <div class="col fs-3 text-end" style="color: #8f8f8f;" id="listname"></div>
                    <div class="col fs-3 text-center">상세 분석</div>
                </div>
            </figure>
            <figure class="figure figurebox">
                <div id="s_salePredict_list" class="row justify-content-between list_box">
                    <div class="col s_bodyIcon"><img src="${pageContext.request.contextPath}/img/won.png" alt=""></div>
                    <div class="col fs-5 text-end list_textBox" id="salePredictList"></div>
                </div>
                <figcaption class="figure-caption text-end"></figcaption>
            </figure>
            <figure class="figure figurebox">
                <div id="patientPredict_list" class="row justify-content-between list_box">
                    <div class="col-1 s_bodyIcon"><img src="${pageContext.request.contextPath}/img/dentalPatient.png"
                            alt="">
                    </div>
                    <p class="col fs-5 text-end list_textBox" id="patientList"></p>
                </div>
                <!-- <figcaption class="figure-caption text-end">해당 지역 취위생사들의 평균 임금으로서, 실제임금과 오차가 있을 수 잇으며,<br> 경력, 계약형태 등에
                    따라 차이가 크므로 참고자료로만 활용하세요.</figcaption> -->
            </figure>
            <figure class="figure figurebox">
                <div id="employee_list" class="row justify-content-between list_box">
                    <div class="col-1 s_bodyIcon"><img src="${pageContext.request.contextPath}/img/people.png" alt="">
                    </div>
                    <p class="col fs-5 text-end list_textBox" id="employeeList"></p>
                </div>
                <figcaption class="figure-caption text-end">해당 지역 취위생사들의 평균 임금으로서, 실제임금과 오차가 있을 수 잇으며,<br> 경력, 계약형태 등에
                    따라 차이가 크므로 참고자료로만 활용하세요.</figcaption>
            </figure>
            <figure class="figure figurebox">
                <div id="area-size_list" class="row list_box">
                    <div class="col-1 s_bodyIcon"><img src="${pageContext.request.contextPath}/img/hospital.png" alt="">
                    </div>
                    <div class="col fs-5 text-end list_textBox" id="areasizeList"></div>
                </div>
                <figcaption class="figure-caption text-end">임대료는 표본데이터 부족으로 실제시세와 오차가 있을 수 있으며,<br> 입지, 건물상태,
                    인지성에 따라 차이가
                    크므로 참고자료로만 활용하세요.</figcaption>
            </figure>
            <figure class="figure figurebox">
                <div id="dept-amount_list" class="row list_box">
                    <div class="col-1 s_bodyIcon"><img src="${pageContext.request.contextPath}/img/dept.png" alt="">
                    </div>
                    <div class="col fs-5 text-end list_textBox" id="deptamountList"></div>
                </div>
                <figcaption class="figure-caption text-end"></figcaption>
            </figure>
            <figure class="figure figurebox">
                <div id="income_list" class="row list_box">
                    <div class="col-1 s_bodyIcon"><img src="${pageContext.request.contextPath}/img/income.png" alt="">
                    </div>
                    <div class="col fs-4 text-end list_textBox" id="incomeList"></div>
                </div>
                <figcaption class="figure-caption text-end"></figcaption>
            </figure>
        </div>
        <script src="${pageContext.request.contextPath}/js/analyze.js"></script>
    </body>

    </html>