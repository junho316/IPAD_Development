const abc = [];
const polygons = [];
var regionCode = "";
const infoData = [];
var chartData = [];
var roundChartData = [];
var seniorEmployees;
var juniorEmployees;
var sizes;

function newpage() {
    window.open('../jsp/saleAnalysis/analyze.jsp', 'result', 'width=800, height=1200, left=550');
    return true;
}

window.onload = function () {

    function setMap() {
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

        return map;
    }



    let title = [];
    let lat = [];
    let lng = [];

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
            var map = setMap();
            createCustomOverlay(map);
            getOverlay();
            overlayEvent();
        },
        error: function (error) {
            console.error('Error fetching data from server:', error);
        }
    });

    function createCustomOverlay(map) {

        for (var i = 0; i < title.length; i++) {
            console.log(title[i]);
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
    var infoDatas = {};
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
                var areacode = document.getElementById("area-code");

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
                            areacode.value = regionCode;
                            regionInfo = regionCode;

                            for (var i = 0; i < title.length; i++) {
                                if (coordinates.length === 0) return;

                                if (coordinates.length === 1 && title[i] == name) {
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
                        url: './calculate.do',
                        method: 'GET',
                        data: { regionCode: regionCode },
                        success: function (data) {
                            sizes = data["size"];
                            employees = data["employee"];
                            seniorEmployees = 1;
                            juniorEmployees = employees - seniorEmployees;
                            modalText(sizes, seniorEmployees, juniorEmployees);
                        },
                        error: function (error) {
                            console.error('Error fetching data from server:', error);
                        }
                    });

                    var areaSize = document.getElementById('area-size');
                    var seniorEmployeeCount = document.getElementById('senior-employee-count');
                    var juniorEmployeeCount = document.getElementById('junior-employee-count');

                    function modalText(sizes, seniorEmployees, juniorEmployees) {

                        var areaSizeText = "선택 지역의 평균 개업 평수는 " + sizes + "평 입니다.";
                        var seniorEmployeeText = "선택 지역의 주임급 간호사 평균 수는 " + seniorEmployees + "명 입니다.";
                        var juniorEmployeeText = "선택 지역의 신입급 간호사 평균 수는 " + juniorEmployees + "명 입니다.";

                        areaSize.placeholder = areaSizeText;
                        seniorEmployeeCount.placeholder = seniorEmployeeText;
                        juniorEmployeeCount.placeholder = juniorEmployeeText;
                    }
                });

                function displayArea(coordinates, name, properties) {
                    var map = abc[0].getMap();
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
                var detailModal = $('#detailModal');
                clearModal();

                detailModal.modal('show');
            });
        }
    }

    function clearModal() {
        $('#area-size').val('');
        $('#senior-employee-count').val('');
        $('#junior-employee-count').val('');
        $('#dept-amount').val('');
        $('#area-name').val('');
        $('#area-code').val('');
    }

    function removePolygon() {

        polygons.forEach(function (polygon) {
            polygon.setMap(null);
        });

        polygons.length = 0;
    }


}


