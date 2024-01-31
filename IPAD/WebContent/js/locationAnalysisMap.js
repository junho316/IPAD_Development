function mapMenuClick(e) {
	document.querySelector('#mapMenu').innerHTML = e.innerHTML;
	document.querySelector('#areaMenu').style.display = 'none';
	var selall = document.querySelectorAll('.tempNum');
	for (let i = 0; i < selall.length; i++) {
		selall[i].innerHTML = e.innerHTML;
	}

}


function ClickPopUpBtn(e) {
	document.querySelector('#selectArea').innerHTML = e.innerHTML;
}

function ClickHosCnt() {
	document.querySelector('#areaMenu').style.display = 'block';
}

function hosLocClick(e) {
	document.querySelector('#areaMenu').innerHTML = e.innerHTML;
}

var t = document.querySelector('#hosLocT');

var mapContainer = document.getElementById('map'), // 지도를 표시할 div
	mapOption = {
		center: new kakao.maps.LatLng(37.47601668950402, 127.15099417223486), // 지도의
		// 중심좌표
		level: 6, // 지도의 확대 레벨
		disableDoubleClickZoom: true,
		scrollwheel: false,
		draggable: false
	};

var map = new kakao.maps.Map(mapContainer, mapOption);

// -----------------------------------------------------------------------

var markerPosition1 = new kakao.maps.LatLng(37.47860551575809, 127.16237294151435);
var markerPosition2 = new kakao.maps.LatLng(37.48274629824583, 127.13696522477319);
var markerPosition3 = new kakao.maps.LatLng(37.468393767232406, 127.14408328318119);

var marker1 = new kakao.maps.Marker({
	position: markerPosition1
});
var marker2 = new kakao.maps.Marker({
	position: markerPosition2
});
var marker3 = new kakao.maps.Marker({
	position: markerPosition3
});


// 지도에 표시 -------------------------------------------------------------
var moveLatLon;

function addArea() {
	deleteArea();
	displayArea(songpaPoly);
	displayArea(hanamPoly);
	displayArea(sungnamPoly);
}

function everyHos() {
	map.setLevel(6);
	moveLatLon = new kakao.maps.LatLng(37.47601668950402, 127.15099417223486);
	map.panTo(moveLatLon);
	deleteMarker();
	hanamHos();
	sungnamHos();
	songpaHos();
	overlayDel.setMap(null);

	for (let i = 0; i < array.length; i++) {
		if (regionArray[i].region == '전체') {
			hospitalCount.innerHTML = regionArray[i].hospitalCount;
			footTraffic.innerHTML = regionArray[i].footTraffic;
			residentPopulation.innerHTML = regionArray[i].residetnPopulation;
			hospitalPopulation.innerHTML = regionArray[i].hospitalPopulation;
			ageGroup.innerHTML = regionArray[i].maxAgeGroup;
			break;
		} else {
			hospitalCount.innerHTML = "-";
			footTraffic.innerHTML = "-";
			residentPopulation.innerHTML = "-";
			hospitalPopulation.innerHTML = "-";
			ageGroup.innerHTML = "-";
		}
	}
}

function songpaHosLoc() {
	map.setLevel(5);
	deleteArea();
	displayArea(songpaPoly);
	moveLatLon = new kakao.maps.LatLng(37.48274629824583, 127.13696522477319);
	map.panTo(moveLatLon);
	deleteMarker();
	songpaHos();
	overlayDel.setMap(null);

	for (let i = 0; i < array.length; i++) {
		if (regionArray[i].region == '송파구') {
			hospitalCount.innerHTML = regionArray[i].hospitalCount;
			footTraffic.innerHTML = regionArray[i].footTraffic;
			residentPopulation.innerHTML = regionArray[i].residetnPopulation;
			hospitalPopulation.innerHTML = regionArray[i].hospitalPopulation;
			ageGroup.innerHTML = regionArray[i].maxAgeGroup;
			break;
		} else {
			hospitalCount.innerHTML = "-";
			footTraffic.innerHTML = "-";
			residentPopulation.innerHTML = "-";
			hospitalPopulation.innerHTML = "-";
			ageGroup.innerHTML = "-";
		}
	}
}

function sungnamHosLoc() {
	map.setLevel(5);
	deleteArea();
	displayArea(sungnamPoly);
	moveLatLon = new kakao.maps.LatLng(37.468393767232406, 127.14408328318119);
	map.panTo(moveLatLon);
	deleteMarker();
	sungnamHos();
	overlayDel.setMap(null);

	for (let i = 0; i < array.length; i++) {
		if (regionArray[i].region == '성남시') {
			hospitalCount.innerHTML = regionArray[i].hospitalCount;
			footTraffic.innerHTML = regionArray[i].footTraffic;
			residentPopulation.innerHTML = regionArray[i].residetnPopulation;
			hospitalPopulation.innerHTML = regionArray[i].hospitalPopulation;
			ageGroup.innerHTML = regionArray[i].maxAgeGroup;
			break;
		} else {
			hospitalCount.innerHTML = "-";
			footTraffic.innerHTML = "-";
			residentPopulation.innerHTML = "-";
			hospitalPopulation.innerHTML = "-";
			ageGroup.innerHTML = "-";
		}
	}
}

function hanamHosLoc() {
	map.setLevel(5);
	deleteArea();
	displayArea(hanamPoly);
	moveLatLon = new kakao.maps.LatLng(37.47860551575809, 127.16237294151435);
	map.panTo(moveLatLon);
	deleteMarker();
	hanamHos();
	overlayDel.setMap(null);

	for (let i = 0; i < array.length; i++) {
		if (regionArray[i].region == '하남시') {
			hospitalCount.innerHTML = regionArray[i].hospitalCount;
			footTraffic.innerHTML = regionArray[i].footTraffic;
			residentPopulation.innerHTML = regionArray[i].residetnPopulation;
			hospitalPopulation.innerHTML = regionArray[i].hospitalPopulation;
			ageGroup.innerHTML = regionArray[i].maxAgeGroup;
			break;
		} else {
			hospitalCount.innerHTML = "-";
			footTraffic.innerHTML = "-";
			residentPopulation.innerHTML = "-";
			hospitalPopulation.innerHTML = "-";
			ageGroup.innerHTML = "-";
		}
	}
}


// json으로 가져오기----------------------------------------------------------------
var array = [];
function fetcData() {
	fetch(contextPath + '/json/map.do')
		.then(response => {
			if (!response.ok) {
				throw new Error('네트워크 응답이 올바르지 않습니다.');
			}
			return response.json();
		})
		.then(data => {
			for (let i = 0; i < data.length; i++) {
				array.push(data[i])
			}
		})
		.catch(error => console.error('에러:', error));
}

var regionArray = [];
function fetcRegionData() {
	fetch(contextPath + '/json/mapRegion.do')
		.then(response => {
			if (!response.ok) {
				throw new Error('네트워크 응답이 올바르지 않습니다.');
			}
			return response.json();
		})
		.then(data => {
			for (let i = 0; i < data.length; i++) {
				regionArray.push(data[i])
			}
		})
		.catch(error => console.error('에러:', error));
}

function hanamHos() {
	for (let i = 0; i < array.length; i++) {
		if (array[i].region == '하남시') {
			displayMarker(array[i]);
		}
	}
}

function sungnamHos() {
	for (let i = 0; i < array.length; i++) {
		if (array[i].region == '성남시') {
			displayMarker(array[i]);
		}
	}
}

function songpaHos() {
	for (let i = 0; i < array.length; i++) {
		if (array[i].region == '송파구') {
			displayMarker(array[i]);
		}
	}
}
// ----------------------------

// 오버레이 ----------------------------------------

const markerArr = [];
var overlayDel = new kakao.maps.CustomOverlay({
	yAnchor: 3,
	position: null
});;

var imageSrc = contextPath + "/img/hosMark.png"
var imageSize = new kakao.maps.Size(25, 25);
var markerImage = new kakao.maps.MarkerImage(imageSrc, imageSize);
var hos = document.getElementById('hos');
var hosLoc = document.getElementById('hosLoc');

function displayMarker(data) {
	var position = new kakao.maps.LatLng(Number(data.x_coordinate), Number(data.y_coordinate));

	var marker = new kakao.maps.Marker({
		map: map,
		position: position,
		image: markerImage,
	});

	markerArr.push(marker);

	var overlay = new kakao.maps.CustomOverlay({
		yAnchor: 3,
		position: marker.getPosition(),
	});

	kakao.maps.event.addListener(marker, 'click', function () {
		hos.innerHTML = data.hospital_name;
		hosLoc.innerHTML = data.address;
	});
}

function deleteMarker() {
	for (let i = 0; i < markerArr.length; i++) {
		markerArr[i].setMap(null);
	}
}

// 폴리곤 ----------------------------------------
var songpaPoly;
var hanamPoly;
var sungnamPoly;
var polygon = [];

window.onload = function () {
	fetcData();
	fetcRegionData();
	$.getJSON(contextPath + "/json/emd.geojson", function (geojson) {
		var data = geojson.features;

		data.forEach(val => {
			if (val.properties.temp.includes('송파구 위례동')) {
				songpaPoly = val.geometry.coordinates[0][0];
			}
			if (val.properties.temp.includes('하남시 위례동')) {
				hanamPoly = val.geometry.coordinates[0][0];
			}
			if (val.properties.temp.includes('성남시 위례동')) {
				sungnamPoly = val.geometry.coordinates[0][0];
			}
		});
	});
}

function displayArea(coordinates) {
	var path = [];
	coordinates.forEach(coordinate => {
		var point = {
			x: coordinate[1],
			y: coordinate[0]
		};
		path.push(new kakao.maps.LatLng(coordinate[1], coordinate[0]))
	});

	polygon.push(new kakao.maps.Polygon({
		map: map,
		path: path,
		strokeWeight: 4,
		strokeColor: 'red',
		strokeOpacity: 0.5,
		strokeStyle: 'solid',
		fillColor: 'red',
		fillOpacity: 0.05
	}));
}
function deleteArea() {
	polygon.forEach(coordinate => {
		coordinate.setMap(null)
	}
	)
}

