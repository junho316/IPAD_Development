const abc = [];
const polygons = [];
var regionCode = "";
const infoData = [];
var chartData = [];
var roundChartData = [];

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
                    console.log(regionCode);

                    // var analyzeUrl = "../jsp/saleAnalysis/analyze.jsp?regionCode=" + encodeURIComponent(regionCode);
                    // var newWindow = window.open(analyzeUrl, 'result', 'width=800,height=1200,left=550');

                    $.ajax({
                        url: './analyze.do',
                        method: 'GET',
                        data: { regionCode: regionCode },
                        success: function (data) {

                        },
                        error: function (error) {
                            console.error('Error fetching data from server:', error);
                        }
                    });

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
        $('#employee-count').val('');
        $('#dept-amount').val('');
        $('#area-name').val('');
        $('#cal').val('');
    }

    function removePolygon() {
        // 배열에 있는 모든 폴리곤을 순회하면서 지도에서 제거
        polygons.forEach(function (polygon) {
            polygon.setMap(null); // 지도에서 제거
        });

        // 배열 비우기
        polygons.length = 0;
    }

    // 지역 검색창

    // searchBtn.onclick = function () {
    //     var map = abc[0].getMap();
    //     var inputAddress = selectRegion.value;

    //     var ps = new kakao.maps.services.Places();


    //     ps.keywordSearch(inputAddress, placesSearchCB);


    //     function placesSearchCB(data, status, pagination) {
    //         if (status === kakao.maps.services.Status.OK) {

    //             var bounds = new kakao.maps.LatLngBounds();

    //             for (var i = 0; i < data.length; i++) {
    //                 bounds.extend(new kakao.maps.LatLng(data[i].y, data[i].x));
    //             }

    //             map.setLevel(5);
    //             map.setBounds(bounds);
    //         }
    //     }
    // }





}


