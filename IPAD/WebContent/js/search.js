const abc = [];
const polygons = [];
var regionCode = "";
const infoData = [];
const infoDatas = {};

var chartData = [];
function newpage() {
    window.open('analyze.html', 'result', 'width=800, height=1200, left=550');
    return true;
}

window.onload = function () {

    fetch(contextPath + '/json/info.do')
        .then(response => response.json())
        .then(data => {
            console.log(data)
            for (let i = 0; i < data.length; i++) {
                var newArray = [data[i]["population"], data[i]["floatingPp"], data[i]["houseHold"], data[i]["income"], data[i]["sale"], data[i]["dentalClinic"]];
                infoData.push(newArray);
            }
        })
        .catch(error => {
            console.error('Error:', error);
        });

    // console.log(infoData);



    var defaultCenter = new kakao.maps.LatLng(37.4775187, 127.1456706);

    var mapContainer = document.getElementById('map3'),
        mapOption = {
            center: new kakao.maps.LatLng(37.4775187, 127.1456706),
            level: 5
        };

    var map = new kakao.maps.Map(mapContainer, mapOption);

    window.addEventListener('resize', function () {
        map.setCenter(defaultCenter);
    });

    setZoomable(false);
    setDraggable(false);

    function setZoomable(zoomable) {
        map.setZoomable(zoomable);
    }

    function setDraggable(draggable) {
        map.setDraggable(draggable);
    }

    let title = [];
    let lat = [];
    let lng = [];

    var populationChartCanvas = document.getElementById("populationChart");
    var floatingPpChartCanvas = document.getElementById("floatingPpChart");
    var countHospitalChartCanvas = document.getElementById("countHospitalChart");
    var populationChart = null;
    var floatingPpChart = null;
    var countHospitalChart = null;

    var populationRoundChart = document.getElementById("populationRoundChart").getContext('2d');
    var floatingPpRoundChart = document.getElementById("floatingPpRoundChart").getContext('2d');

    $.ajax({
        url: './customOverlay.do',
        method: 'GET',
        data: {},
        success: function (data) {
            for (let i = 0; i < data.length; i++) {
                title.push(data[i]["name"]);
                lat.push(data[i]["lat"]);
                lng.push(data[i]["lng"]);
            }

            createCustomOverlay();
            getOverlay();
            overlayEvent();
        },
        error: function (error) {
            console.error('Error fetching data from server:', error);
        }
    });
    createCustomOverlay();
    function createCustomOverlay() {
        for (var i = 0; i < title.length; i++) {

            // 아이콘에 따른 지역정보 출력과 동 이름 출력할 커스텀 오버레이
            var dongName = title[i];

            var latlng = new kakao.maps.LatLng(lat[i], lng[i]);

            var customOverlay = new kakao.maps.CustomOverlay({
                map: map,
                clickable: true,
                content:
                    '<div  id="overlay' + i + '" style="background-color: #CDF281; border-radius: 50%; width: 120px; height: 120px; text-align: center; font-size: 1.2rem;"><br><div id="dong' + i + '" style="font-weight: bold; margin-bottom: 5px;">' + dongName + '</div><div id="infoData' + i + '"></div></div>',
                position: latlng,
                title: title[i],
                xAnchor: 0.5,
                yAnchor: 1,
                zIndex: 3
            });
            abc.push(customOverlay);
            customOverlay.setMap(map);
        }
    }

    var geocoder = new kakao.maps.services.Geocoder();

    var customOverlays = {};
    var dongs = {};

    var array = [];

    function getOverlay() {
        for (let i = 0; i < abc.length; i++) {
            customOverlays['co' + i] = document.getElementById('overlay' + i)
            infoDatas['infoData' + i] = document.getElementById('infoData' + i)
            dongs['dong' + i] = document.getElementById('dong' + i).innerText;
            array.push(eval('dongs.dong' + i))
        }
    }

    function overlayEvent() {
        for (let i = 0; i < abc.length; i++) {
            let currentOverlay = eval('customOverlays.co' + i);
            let OverlayDong = eval('dong' + i);

            let clickedOverlay = null;

            currentOverlay.addEventListener('mouseover', function () {
                currentOverlay.style.cursor = "pointer";
                currentOverlay.style.backgroundColor = "#FE69B1";
                currentOverlay.style.animation = "ripple 1s linear infinite";
            })

            currentOverlay.addEventListener('mouseout', function () {
                currentOverlay.style.backgroundColor = "#CDF281";
                currentOverlay.style.opacity = "0.7";
                currentOverlay.style.animation = "none";
                OverlayDong.style.fontWeight = "bold";
            })

            currentOverlay.addEventListener('click', function () {

                var areaname = document.getElementById("area-name");
                var regionInfo = document.getElementById("regionInfo");

                var simpleBtn = document.getElementById('searchButton');
                var detailBtn = document.getElementById('detailButton');

                simpleBtn.disabled = false;
                detailBtn.disabled = false;

                removePolygon();
                $.getJSON("/IPAD/json/emdTest.geojson", function (geojson) {
                    var data = geojson.features;
                    var name = ' ';
                    var a = array[i];

                    data.forEach(val => {
                        var coordinates = val.geometry.coordinates;
                        coordinates = coordinates[0];

                        if (val.properties.temp === a) {
                            name = val.properties.temp;
                            region = val.properties.adm_nm;
                            regionCode = val.properties.adm_cd;

                            areaname.value = region;
                            regionInfo = regionCode;
                            // 지역 이름 출력

                            var regionChoice = document.getElementById("regionChoice");
                            regionChoice.innerHTML = "<span>&#183;&nbsp;&nbsp;" + region + "</span>";

                            for (var i = 0; i < address.length; i++) {
                                if (coordinates.length === 0) return;

                                if (coordinates.length === 1 && address[i].addr == name) {
                                    displayArea(coordinates[0], val.properties.temp, val.properties);
                                } else {
                                    coordinates.forEach(polygonCoords => {
                                        displayArea(polygonCoords[0], val.properties.temp, val.properties);
                                    });
                                }
                            }
                        }
                    });

                    $.ajax({
                        url: './simpleChart.do',
                        method: 'GET',
                        data: { regionCode: regionCode },
                        success: function (data) {
                            chartData = [];
                            for (var i = 0; i < data.length; i++) {
                                newArray1.push(data[i]["population"]);
                                newArray2.push(data[i]["float_pp"]);
                                newArray3.push(data[i]["countClinic"]);
                            }

                            chartData.push(newArray1);
                            chartData.push(newArray2);
                            chartData.push(newArray3);
                        },
                        error: function (error) {
                            console.error('Error fetching data from server:', error);
                        },
                        complete: function () {
                            // 요청 완료 후 currentRequest 초기화
                            currentRequest = null;
                        }
                    });

                    var newArray1 = [];
                    var newArray2 = [];
                    var newArray3 = [];
                });

                function displayArea(coordinates, name, properties) {

                    var path = [];

                    coordinates.forEach(coordinate => {
                        var point = {
                            x: coordinate[1],
                            y: coordinate[0]
                        };
                        path.push(new kakao.maps.LatLng(coordinate[1], coordinate[0]));
                    });

                    var polygon = new kakao.maps.Polygon({
                        map: map,
                        path: path,
                        strokeWeight: 4,
                        strokeColor: '#CDF281',
                        strokeOpacity: 1,
                        fillColor: '#8AAAE6',
                        fillOpacity: 0.7
                    });

                    polygons.push(polygon)

                    kakao.maps.event.addListener(polygon, 'click', function (mouseEvent) {


                    });
                }
            });
        }
    }

    var box = document.querySelectorAll('.iconBox');

    for (let i = 0; i < box.length; i++) {
        box[i].onclick = function () {
            for (let j = 0; j < infoData.length; j++) {
                eval('infoDatas.infoData' + j).innerText = infoData[j][i]
            }

        }
    }

    var simpleSearch = document.getElementById('simpleSearch');
    var simpleclose = document.getElementById('simpleClose');
    var detailclose = document.getElementById('detailClose');

    function updateChart(chart, chartData) {
        console.log("업데이트 실행")

        if (chart instanceof Chart && chart.data) {
            chart.data.datasets[0].data = chartData;
            chart.update();
        }
    }

    simpleSearch.onclick = function () {
        console.log("심플 서치 실행");
        console.log(chartData);
        console.log(!populationChart);


        if (!populationChart) {
            populationChart = new Chart(populationChartCanvas, {
                type: 'line',
                data: {
                    labels: ['2019', '2020', '2021', '2022'],
                    datasets: [
                        {
                            data: chartData[0],
                            borderWidth: 3,
                            borderColor: 'black',
                            backgroundColor: 'black'

                        }

                    ]
                },
                options: {
                    reponsive: true,
                    maintainAspectRatio: false,
                    scales: {

                        y: {
                            grid: {
                                display: true,
                                color: 'rgba(0, 0, 0, 0.3)',
                            },
                            beginAtZero: true,
                            min: '0',
                            max: '50000',
                            position: 'top',
                            ticks: {
                                callback: function (value, index, values) {
                                    if (index === 0) {
                                        return '(단위: 명)' + value;
                                    } else {
                                        return value.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ',');
                                    }
                                },
                            },
                        },
                    },
                    plugins: {
                        title: {
                            display: true,
                            text: '총 인구수',
                            font: {
                                size: 25,
                            }
                        },
                        legend: {
                            display: false,
                            labels: {
                                usePointStyle: true
                            },
                        },
                    },
                }
            });
        } else {
            updateChart(populationChart, chartData[0]);
        }

        if (!floatingPpChart) {
            floatingPpChart = new Chart(floatingPpChartCanvas, {
                type: 'line',
                data: {
                    labels: ['2019', '2020', '2021', '2022'],
                    datasets: [
                        {
                            data: chartData[1],
                            borderWidth: 3,
                            borderColor: 'black',
                            backgroundColor: 'black'

                        }

                    ]
                },
                options: {
                    reponsive: true,
                    maintainAspectRatio: false,
                    scales: {

                        y: {
                            grid: {
                                display: true,
                                color: 'rgba(0, 0, 0, 0.3)',
                            },
                            beginAtZero: true,
                            min: '2000000',
                            max: '15000000',
                            position: 'top',
                            ticks: {
                                callback: function (value, index, values) {
                                    if (index === 0) {
                                        return '(단위: 명)' + value;
                                    } else {
                                        return value.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ',');
                                    }
                                },
                            },
                        },
                    },
                    plugins: {
                        title: {
                            display: true,
                            text: '유동인구',
                            font: {
                                size: 25,
                            }
                        },
                        legend: {
                            display: false,
                            labels: {
                                usePointStyle: true
                            },
                        },
                    },
                }
            });
        } else {
            updateChart(floatingPpChart, chartData[1]);
        }

        if (!countHospitalChart) {
            countHospitalChart = new Chart(countHospitalChartCanvas, {
                type: 'line',
                data: {
                    labels: ['2019', '2020', '2021', '2022'],
                    datasets: [
                        {
                            data: chartData[2],
                            borderWidth: 3,
                            borderColor: 'black',
                            backgroundColor: 'black'

                        }

                    ]
                },
                options: {
                    reponsive: true,
                    maintainAspectRatio: false,
                    scales: {

                        y: {
                            grid: {
                                display: true,
                                color: 'rgba(0, 0, 0, 0.3)',
                            },
                            beginAtZero: true,
                            min: '0',
                            max: '40',
                            position: 'top',
                            ticks: {
                                callback: function (value, index, values) {
                                    if (index === 0) {
                                        return '(개 수)' + value;
                                    } else {
                                        return value.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ',');
                                    }
                                },
                            },
                        },
                    },
                    plugins: {
                        title: {
                            display: true,
                            text: '동종 병원 수',
                            font: {
                                size: 25,
                            }
                        },
                        legend: {
                            display: false,
                            labels: {
                                usePointStyle: true
                            },
                        },
                    },
                }
            });
        } else {
            updateChart(countHospitalChart, chartData[2]);
        }


    }

    simpleclose.onclick = function () {
        var myCollapse = new bootstrap.Collapse(document.getElementById('simpleInfo'));
        myCollapse.hide();
    }

    detailclose.onclick = function () {
        var myCollapse = new bootstrap.Collapse(document.getElementById('detailInfo'))
        myCollapse.hide();
    }

    var address = [
        { addr: "성남시 위례동" },
        { addr: "하남시 위례동" },
        { addr: "송파구 위례동" }
    ];

    function removePolygon() {
        // 배열에 있는 모든 폴리곤을 순회하면서 지도에서 제거
        polygons.forEach(function (polygon) {
            polygon.setMap(null); // 지도에서 제거
        });

        // 배열 비우기
        polygons.length = 0;
    }

    var searchInfoIcon = document.getElementById('searchInfoIcon');

    searchInfoIcon.onclick = function () {

        var searchInfo = document.getElementById('searchInfo');
        var simpleInfo = document.getElementById('simpleInfo');
        var detailInfo = document.getElementById('detailInfo');

        if (searchInfo.style.display == "block") {
            searchInfo.style.display = "none";
            $(simpleInfo).collapse("hide");
            $(detailInfo).collapse("hide");

        }
        else {
            searchInfo.style.display = "block";
            searchInfo.style.width = "500px";
            searchInfo.style.opacity = "1";

        }
    }

    var searchButton = document.getElementById('searchButton');
    var detailButton = document.getElementById('detailButton');

    searchButton.onclick = function () {
        var simpleInfo = document.getElementById('simpleInfo');
        var detailInfo = document.getElementById('detailInfo');

        if (window.innerWidth <= 1200) {
            searchInfo.style.opacity = "0";
            searchInfo.style.transition = "opacity 0.5s ease";
            simpleInfo.style.width = "50%"
        }
    }
    detailButton.onclick = function () {
        if (window.innerWidth <= 1200) {
            searchInfo.style.opacity = "0";
            searchInfo.style.transition = "opacity 0.5s ease";
        }
    }
    // 진료과 출력
    var subject = document.getElementById("subject");
    var subjectOption = subject.options[subject.selectedIndex].value;

    var subjectChoice = document.getElementById("subjectChoice");
    subjectChoice.innerHTML = "<span>&#183;&nbsp;&nbsp;" + subjectOption + "</span>";

    var monthSale;
    var population;
    var floatingPp;
    var countHospital;

    // var salePredictText = document.getElementById("salePredictText");
    // salePredictText.innerText = "월평균 추정매출은 " + monthSale + "만원 입니다.";

    // var populationText = document.getElementById("populationText");
    // populationText.innerText = "지역 총 인구수는 " + population + "명 입니다.";

    // var floatingPpText = document.getElementById("floatingPpText");
    // floatingPpText.innerHTML = "일일평균 유동인구는 " + floatingPp + "명 입니다.";

    // var countHospitalText = document.getElementById("countHospitalText");
    // countHospitalText.innerText = "선택 진료과 병원 수는 " + countHospital + "개 입니다.";

    var searchBtn = document.getElementById("searchBtn");
    var selectRegion = document.getElementById('selectRegion');

    // 지역 검색창

    searchBtn.onclick = function () {
        var inputAddress = selectRegion.value;

        var ps = new kakao.maps.services.Places();

        // 키워드로 장소를 검색합니다
        ps.keywordSearch(inputAddress, placesSearchCB);

        // 키워드 검색 완료 시 호출되는 콜백함수 입니다
        function placesSearchCB(data, status, pagination) {
            if (status === kakao.maps.services.Status.OK) {

                // 검색된 장소 위치를 기준으로 지도 범위를 재설정하기위해
                // LatLngBounds 객체에 좌표를 추가합니다
                var bounds = new kakao.maps.LatLngBounds();

                for (var i = 0; i < data.length; i++) {
                    bounds.extend(new kakao.maps.LatLng(data[i].y, data[i].x));
                }

                // 검색된 장소 위치를 기준으로 지도 범위를 재설정합니다
                map.setLevel(5);
                map.setBounds(bounds);
            }
        }



    }

    // Chart



}


