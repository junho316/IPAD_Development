window.onload = function () {
	fetchData();
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

	if (selectedTd) {
		selectedTd.classList.remove('bold');
	}
	clickedTd.classList.add('bold');

	selectedTd = clickedTd;
	var tdContent = clickedTd.innerText;
	showMapData(tdContent);
	fetcPredictData(tdContent);
}

function showMapData(tdContent) {
	switch (tdContent) {
		case '송파구 위례':
			songpaHosLoc();
			document.getElementById('regionDetail').innerText = '송파구 위례';
			break;
		case '성남시 위례':
			sungnamHosLoc();
			document.getElementById('regionDetail').innerText = '성남시 위례';
			break;
		case '하남시 위례':
			hanamHosLoc();
			document.getElementById('regionDetail').innerText = '하남시 위례';
			break;
		default:
			break;
	}
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

function predictWrite() {

	   document.getElementById('patient').innerText = predictData[0].toLocaleString()+" 명";
	   document.getElementById('employee').innerText = predictData[1]+" 명";
	   document.getElementById('size').innerText = predictData[2]+" 평";
	   document.getElementById('predictSale').innerText = Number(String(predictData[3]).slice(0, 4)).toLocaleString()+" 만원"
	}


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
			fetcPredictData(tempcon);
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

function clickPopUpBtn(e) {
	document.querySelector('#selectArea').innerHTML = e.innerHTML;
}

function clickHosCnt() {
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

function fetchData() {
	fetch(contextPath + '/json/map.do')
		.then(response => {
			if (!response.ok) {
				throw new Error('네트워크 응답이 올바르지 않습니다.');
			}
			return response.json();
		})
		.then(data => {
			array = data;

		})
		.catch(error => console.error('에러:', error));
}


var predictData = [];
function fetcPredictData(regionName) {
	var data =
		{ name: regionName }

	fetch(contextPath + '/json/predict.do', {
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
		.then(data => {
			predictData.length = 0;
			predictData.push(data.predictPatient);
			predictData.push(data.employee);
			predictData.push(data.size);
			predictData.push(data.netProfit);
		})
		.then(() => {
			predictWrite();
		})
		.catch(error => console.error('에러 :', error))
}




function hanamHos() {

	array.forEach(data => {
		if (data.region == '하남시' && data.business_status == '영업/정상') {
			displayMarker(data, openHosImg);
		} else if (data.region == '하남시' && data.business_status != '영업/정상') {
			displayMarker(data, closeHosImg);
		}
	});
}

function sungnamHos() {
	array.forEach(data => {
		if (data.region == '성남시' && data.business_status == '영업/정상') {
			displayMarker(data, openHosImg);
		} else if (data.region == '성남시' && data.business_status != '영업/정상') {
			displayMarker(data, closeHosImg);
		}
	});
}

function songpaHos() {
	array.forEach(data => {
		if (data.region == '송파구' && data.business_status == '영업/정상') {
			displayMarker(data, openHosImg);
		} else if (data.region == '송파구' && data.business_status != '영업/정상') {
			displayMarker(data, closeHosImg);
		}
	});

}

// 오버레이 ----------------------------------------
const markerArr = [];
var overlayDel = new kakao.maps.CustomOverlay({
	yAnchor: 3,
	position: null
});

var openHosImg = contextPath + "/img/hosMark.png";
var closeHosImg = contextPath + "/img/closeHosMark.png";
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

	var infoContent = img == closeHosImg ?
		'폐업일: ' + data.close_date :
		'개업일: ' + data.license_date;

	var infowindow = new kakao.maps.InfoWindow({
		content: '<div id="infoWindow">' +
			'<strong>' + data.hospital_name + '</strong><br>' +
			infoContent +
			'</div>',
		removable: true  // 기본 InfoWindow 디자인 사용
	});





	kakao.maps.event.addListener(marker, 'click', function () {
		if (currentInfoWindow) {
			currentInfoWindow.close();
		}
		infowindow.open(map, marker);
		currentInfoWindow = infowindow;
	});
}

function deleteMarker() {
	markerArr.forEach(marker => marker.setMap(null));
}

// 폴리곤 ----------------------------------------
var songpaPoly;
var hanamPoly;
var sungnamPoly;
var polygon = [];

function displayArea(coordinates) {
	var path = [];
	coordinates.forEach(coordinate => {
		path.push(new kakao.maps.LatLng(coordinate[1], coordinate[0]));
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
	polygon.forEach(coordinate => coordinate.setMap(null));
}

function showCustomOverlay(data, infoContent) {
	document.getElementById('hospitalName').innerText = data.hospital_name;
	document.getElementById('infoContent').innerText = infoContent;

	var customOverlay = document.getElementById('customOverlay');
	customOverlay.style.display = 'block';

	// 팝업 위치 설정 (예시로 임의의 위치)
	var position = new kakao.maps.LatLng(Number(data.x_coordinate), Number(data.y_coordinate));

	// CustomOverlay 추가
	var customOverlayOptions = {
		content: customOverlay,
		position: position,
		zIndex: 1
	};

	var customOverlayObj = new kakao.maps.CustomOverlay(customOverlayOptions);
	customOverlayObj.setMap(map);

	// 팝업을 클릭하면 닫기
	customOverlay.onclick = function () {
		customOverlay.style.display = 'none';
		customOverlayObj.setMap(null);
	};
}

var selectedTd = null;