function printPage() {
    window.print();
}
function windowclose() {
    window.close();
}
var urlParams = new URLSearchParams(window.location.search);
var areaCode = urlParams.get('regionCode');
// var areaCode = '<%= session.getAttribute("regionCode") %>';
var areaname;
var monthSale;

window.onload = function () {

    console.log(areaCode);

    function NumberComma(number) {
        return new Intl.NumberFormat().format(number);
    }

    $.ajax({
        url: './calSale.do',
        method: 'GET',
        data: { areaCode: areaCode },
        success: function (data) {
            console.log(data);
            monthSale = NumberComma(data);
            monthSaleData();
            console.log("아작스는 돌아가냐");
        },
        error: function (error) {
            console.error('Error fetching data from server:', error);
        }
    });

    var salePredictList = document.getElementById("salePredictList");
    function monthSaleData() {
        salePredictList.innerText = "월 평균 추정매출 = " + monthSale + " 원";
    };

    // var employeepee = 238;
    // var totalEmployee = employeepee * employeeCount;
    // var rentpay = 10;
    // var totalRent = rentpay * areasize;
    // var salePredictText = document.getElementById("s_salePredictText");
    // salePredictText.innerText = "월평균 추정매출은 " + monthSale + "만원 입니다.";


    // var employeeList = document.getElementById("employeeList");
    // employeeList.innerText = "월 평균 임금 : " + employeepee + " 만원 X 고용인 수 " + employeeCount + "명 = " + totalEmployee + " 만원";
    // var areasizeList = document.getElementById("areasizeList");
    // areasizeList.innerText = "월 평균 임대료 : " + rentpay + " 만원 X 예정 평수 " + areasize + "평 = " + totalRent + " 만원";
    // var deptamountList = document.getElementById("deptamountList");
    // deptamountList.innerText = "월 대출 상환금 = " + deptamount + " 만원";
    // var income = monthSale - totalEmployee - totalRent;
    // var incomeList = document.getElementById("incomeList");
    // incomeList.innerText = "월 예상 수익 = " + income + " 만원";

    // const labels = ['2018년', '2019년', '2020년', '2021년', '2022년']
    // const livingdata = {
    //     labels: labels,
    //     datasets: [
    //         {
    //             label: areaname,
    //             data: ['10', '20', '30', '40', '50'],
    //             borderColor: 'rgb(255, 99, 132)',
    //             backgroundColor: 'rgb(255, 99, 132, 0.5)',
    //         }
    // {
    //     label: '서울시 행정동 평균',
    //     data: ['15', '25', '35', '45', '55'],
    //     borderColor: 'rgb(54, 162, 235)',
    //     backgroundColor: 'rgb(54,162,235,0.5)',
    // }
    //     ]
    // };

    // const livingChartbox = document.getElementById("livingChart").getContext('2d');
    // const livingChart = new Chart(livingChartbox, {
    //     type: 'line',
    //     data: livingdata,
    //     options: {
    //         responsive: true,
    //         plugins: {
    //             legend: {
    //                 position: 'top',
    //             },
    //             title: {
    //                 display: true,
    //                 text: '상주 인구 데이터'
    //             }
    //         }
    //     }
    // });

    // var genderdata = {
    //     datasets: [{
    //         data: [549, 581],
    //         backgroundColor: [
    //             "#36a2eb",
    //             "#ff6384"
    //         ],
    //     }],
    //     labels: ["남자", "여자"],
    //     borderColor: "#fff"
    // };
    // var genderoptions = {
    //     plugins: {
    //         tooltips: {
    //             enabled: false
    //         },
    //         datalabels: {
    //             formatter: (value, genderctx) => {
    //                 let sum = genderctx.dataset.data.reduce((a, b) => a + b, 0);
    //                 let percentage = ((value / sum) * 100).toFixed(2) + "%";
    //                 return percentage;
    //             },
    //             color: '#fff',
    //         },

    //         title: {
    //             display: true,
    //             text: `${areaname} 인구 성비`,
    //         }

    //     }

    // };
    // var genderctx = document.getElementById("genderChart").getContext('2d');
    // var genderChart = new Chart(genderctx, {
    //     plugins: [ChartDataLabels],
    //     type: 'pie',
    //     data: genderdata,
    //     options: genderoptions

    // });

    // var agedata = {
    //     datasets: [{
    //         data: [549, 581, 300, 450, 200, 700, 350], // 7개의 숫자로 늘림
    //         backgroundColor: [
    //             "#36a2eb",
    //             "#ff6384",
    //             "#4bc0c0",
    //             "#ffcc29",
    //             "#9966ff",
    //             "#ff9f40",
    //             "#4caf50"
    //         ],
    //     }],
    //     labels: ["10대", "20대", "30대", "40대", "50대", "60대", "70대"], // 라벨도 7개로 맞춰줌
    //     borderColor: "#fff"
    // };

    // var ageoptions = {
    //     plugins: {
    //         tooltips: {
    //             enabled: false
    //         },
    //         datalabels: {
    //             formatter: (value, agectx) => {
    //                 let sum = agectx.dataset.data.reduce((a, b) => a + b, 0);
    //                 let percentage = ((value / sum) * 100).toFixed(2) + "%";
    //                 return percentage;
    //             },
    //             color: '#fff',
    //         },

    //         title: {
    //             display: true,
    //             text: `${areaname} 연령별 비율`,
    //         }
    //     }
    // };

    // var agectx = document.getElementById("ageChart").getContext('2d');
    // var ageChart = new Chart(agectx, {
    //     plugins: [ChartDataLabels],
    //     type: 'doughnut',
    //     data: agedata,
    //     options: ageoptions
    // });

    // var householdData = {
    //     labels: ["2018년", "2019년", "2020년", "2021년", "2022년"],
    //     datasets: [{
    //         label: "가구",
    //         data: [80, 90, 70, 110, 100],
    //         borderColor: "#ff6384",
    //         fill: false,
    //     }]
    // };

    // var householdOptions = {
    //     plugins: {
    //         title: {
    //             display: true,
    //             text: '2018년부터 2022년까지 월별 가구 수 변화',
    //         },
    //         scales: {
    //             xAxes: [{
    //                 scaleLabel: {
    //                     display: true,
    //                     labelString: '년도'
    //                 }
    //             }],
    //             yAxes: [{
    //                 scaleLabel: {
    //                     display: true,
    //                     labelString: '가구 수'
    //                 }
    //             }]
    //         }
    //     }
    // };

    // var householdCtx = document.getElementById("householdChart").getContext('2d');
    // var householdChart = new Chart(householdCtx, {
    //     type: 'line',
    //     data: householdData,
    //     options: householdOptions
    // });

    // var schoolData = {
    //     labels: ["초등학교", "중학교", "고등학교"],
    //     datasets: [{
    //         label: "학교 개수",
    //         data: [150, 80, 50], // 각 학교 유형에 대한 개수
    //         backgroundColor: ["#36a2eb", "#ffcc29", "#4caf50"],
    //         borderColor: "#fff",
    //         borderWidth: 1
    //     }]
    // };

    // var schoolOptions = {
    //     title: {
    //         display: true,
    //         text: '초등학교, 중학교, 고등학교 개수',
    //     },
    //     scales: {
    //         xAxes: [{
    //             scaleLabel: {
    //                 display: true,
    //                 labelString: '학교 유형'
    //             }
    //         }],
    //         yAxes: [{
    //             scaleLabel: {
    //                 display: true,
    //                 labelString: '개수'
    //             }
    //         }]
    //     }
    // };

    // var schoolCtx = document.getElementById("schoolChart").getContext('2d');
    // var schoolChart = new Chart(schoolCtx, {
    //     type: 'bar',
    //     data: schoolData,
    //     options: schoolOptions
    // });


}