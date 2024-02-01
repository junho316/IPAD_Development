window.onload = function () {
	fetcData();
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

function writeRankList() {

	document.getElementById('first').innerText = list[0];
	document.getElementById('second').innerText = list[1];
	document.getElementById('third').innerText = list[2];
}

function selectRegion(event) {
	var clickedTd = event.target;
	var tdContent = clickedTd.innerText;
	showMapData(tdContent);

}

function showMapData(tdContent) {
	if (tdContent == '송파구 위례') {
		songpaHosLoc();
		document.getElementById('regionDetail').innerText = '송파구 위례';
	} else if (tdContent == '성남시 위례') {
		sungnamHosLoc();
		document.getElementById('regionDetail').innerText = '성남시 위례';
	} else if (tdContent == '하남시 위례') {
		hanamHosLoc();
		document.getElementById('regionDetail').innerText = '하남시 위례';
	}
}
var list = [];


function getRankList() {

	var checkImpl = document.getElementById('implant').checked;
	var checkOrth = document.getElementById('orthodontics').checked;
	var data = {
		checkImpl: checkImpl,
		checkOrth: checkOrth
	};

	fetch(contextPath + "/json/locationRecommand.do", {
		method: 'POST',
		headers: {
			'Content-Type': 'application/json'
		},
		body: JSON.stringify(data)
	})
		.then(response => {
			if (!response.ok) {
				throw new Error('Network response was not ok');
			}
			return response.json();
		})
		.then(jsonArray => {

			list.length = 0;

			for (let i = 0; i < jsonArray.length; i++) {
				list.push(jsonArray[i]["adm_nm"]);

			}
		})
		.then(() => {
			writeRankList();

			// 이제 writeRankList가 실행된 이후에 아래 코드가 실행됩니다.
			var tempcon = document.getElementById('first').innerText;
			showMapData(tempcon);
		})

		.catch(error => {
			console.error('에러 발생:', error);
		});
}

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
	if (currentInfoWindow) {
		currentInfoWindow.close();
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
	if (currentInfoWindow) {
		currentInfoWindow.close();
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
	if (currentInfoWindow) {
		currentInfoWindow.close();
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
			console.log(array);
		})
		.catch(error => console.error('에러:', error));
}

//예상 환자수 등등 가져오기
var predictData = [];
function fetcPredictData(name) {
	var data = {
		name: name
	};
	fetch(contextPath + '/json/predict.do', {
		method: 'POST',
		headers: {
			'Content-Type': 'application/json'
		},
		body: JSON.stringify(data)
	})
		.then(response => response.json)
		.then(jsonArray => {
			for (let i = 0; i < jsonArray.length; i++) {
				predictData.push(jsonArray[i])
			}
		})
		.then(() =>)
		.catch(error => console.error('에러 :', error))
}
function getRegionCode(event) {
	var regionName = event.target;
	var name = regionName.innerText;
	fetcPredictData(name);
}
//여기까지


function hanamHos() {
	for (let i = 0; i < array.length; i++) {
		if (array[i].region == '하남시' && array[i].business_status == '영업/정상') {
			displayMarker(array[i], openHosImg);
		} else if (array[i].region == '하남시' && array[i].business_status != '영업/정상') {
			displayMarker(array[i], closeHosImg);
		}

	}
}

function sungnamHos() {
	for (let i = 0; i < array.length; i++) {
		if (array[i].region == '성남시' && array[i].business_status == '영업/정상') {
			displayMarker(array[i], openHosImg);
		} else if (array[i].region == '성남시' && array[i].business_status != '영업/정상') {
			displayMarker(array[i], closeHosImg);
		}
	}
}

function songpaHos() {
	for (let i = 0; i < array.length; i++) {
		if (array[i].region == '송파구' && array[i].business_status == '영업/정상') {
			displayMarker(array[i], openHosImg);
		} else if (array[i].region == '송파구' && array[i].business_status != '영업/정상') {
			displayMarker(array[i], closeHosImg);
		}
	}
}

// 오버레이 ----------------------------------------
const markerArr = [];
var overlayDel = new kakao.maps.CustomOverlay({
	yAnchor: 3,
	position: null
});;

var openHosImg = contextPath + "/img/hosMark.png"
var closeHosImg = contextPath + "/img/closeHosMark.png"
var imageSize = new kakao.maps.Size(20, 20);
var hos = document.getElementById('hos');
var hosLoc = document.getElementById('hosLoc');

var currentInfoWindow = null;

function displayMarker(data, img) {
	// 마커 이미지 및 위치 설정
	var markerImage = new kakao.maps.MarkerImage(img, imageSize);
	var position = new kakao.maps.LatLng(Number(data.x_coordinate), Number(data.y_coordinate));

	// 마커 생성
	var marker = new kakao.maps.Marker({
		map: map,
		position: position,
		image: markerImage,
	});
	markerArr.push(marker);

	if (img == closeHosImg) {
		var infowindow = new kakao.maps.InfoWindow({
			content: '<div style="padding:10px;min-width:250px;">' +
				'<strong>' + data.hospital_name + '</strong><br>' +
				'폐업일 : ' + data.close_date
		});

	} else {
		var infowindow = new kakao.maps.InfoWindow({
			content: '<div style="padding:10px;min-width:250px;">' +
				'<strong>' + data.hospital_name + '</strong><br>' +
				'개업일 : ' + data.license_date
		});
	}

	kakao.maps.event.addListener(marker, 'click', function () {
		if (currentInfoWindow) {
			currentInfoWindow.close();
		}
		infowindow.open(map, marker);
		currentInfoWindow = infowindow;
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

