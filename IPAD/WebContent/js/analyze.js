function printPage() {
    window.print();
}
function windowclose() {
    window.close();
}

window.onload = function () {

    const queryString = window.location.search;

    const urlParams = new URLSearchParams(queryString);

    for (const [key, value] of urlParams) {
        console.log(`${key}: ${value}`);
    }


    const areaname = urlParams.get('area-name');
    const regionCode = urlParams.get('area-code');
    const areaSize = urlParams.get('area-size');
    const seniorEmployeeCount = urlParams.get('senior-employee-count');
    const juniorEmployeeCount = urlParams.get('junior-employee-count');
    const deptamount = urlParams.get('dept-amount');
    var listname = document.getElementById("listname");
    listname.innerText = areaname;


    function NumberComma(number) {
        return new Intl.NumberFormat().format(number);
    }

    $.ajax({
        url: './netprofit.do',
        method: 'GET',
        data: { regionCode: regionCode, areaSize: areaSize, seniorEmployeeCount: seniorEmployeeCount, juniorEmployeeCount: juniorEmployeeCount, deptamount: deptamount },
        success: function (data) {
            predictSale = NumberComma(data["predictSale"]);
            predictPatient = data["predictPatient"];
            employment_cost = NumberComma(data["employment_cost"]);
            rentFee = NumberComma(data["rentFee"]);
            dept = NumberComma(data["deptAmount"]);
            netProfit = NumberComma(data["netProfit"]);
            seniorEmployment_cost = NumberComma(data["seniorEmployment_cost"]);
            juniorEmployment_cost = NumberComma(data["juniorEmployment_cost"]);
            monthSaleData();
        },
        error: function (error) {
            console.error('Error fetching data from server:', error);
        }
    });

    var salePredictList = document.getElementById("salePredictList");
    var patientPredictList = document.getElementById("patientList");
    var employeeList = document.getElementById("employeeList");
    var areaSizeList = document.getElementById("areasizeList");
    var deptamountList = document.getElementById("deptamountList");
    var incomeList = document.getElementById("incomeList");

    function monthSaleData() {
        salePredictList.innerText = "월 평균 추정매출 = " + predictSale + " 원";
        patientPredictList.innerText = "월 평균 방문환자 = " + predictPatient + " 명";
        employeeList.innerText = "월 고용 간호사 임금 = " + employment_cost + " 원";
        areaSizeList.innerText = "월 납부 임대료 = " + rentFee + " 원";
        deptamountList.innerText = "월 납입 이자 = " + dept + " 원";
        incomeList.innerText = "월 평균 추정 순이익 = " + netProfit + " 원";
    };
}