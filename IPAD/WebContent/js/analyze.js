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
    const areacode = urlParams.get('area-code');
    const areasize = urlParams.get('area-size');
    const employeeCount = urlParams.get('employee-count');
    const deptamount = urlParams.get('dept-amount');
    var listname = document.getElementById("listname");
    listname.innerText = areaname;
    console.log(areaname);
    function NumberComma(number) {
        return new Intl.NumberFormat().format(number);
    }

    $.ajax({
        url: './calculate.do',
        method: 'GET',
        data: { areacode: areacode },
        success: function (data) {
            console.log(data);
            console.log(areasize);
            monthSale = NumberComma(data["predictSale"]);
            monthPatient = data["predictPatient"];
            employee = data["employee"];
            monthSaleData();
            console.log("아작스는 돌아가냐");
        },
        error: function (error) {
            console.error('Error fetching data from server:', error);
        }
    });

    var salePredictList = document.getElementById("salePredictList");
    var patientPredictList = document.getElementById("patientList");
    function monthSaleData() {
        salePredictList.innerText = "월 평균 추정매출 = " + monthSale + " 원";
        patientPredictList.innerText = "월 평균 방문환자 = " + monthPatient + " 명";
    };
}