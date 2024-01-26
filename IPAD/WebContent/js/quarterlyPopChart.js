let songpaQu = ['3331838', '3469064', '3643283', '3527469', '3467145'];
let seongnamQu = ['4000000', '4100000', '4300000', '4200000', '4500000'];
let hanamQu = ['3500000', '4000000', '4100000', '3900000', '4000000'];
var ctx = document.getElementById('quarterlyPopChart').getContext('2d');
var quarterlyPopChart = new Chart(ctx, {
	type: 'line',
	data: {
		labels: ['2020', '2021', '2022', '2023', '2024'],
		datasets: [{
			label: '송파구 위례',
			data: songpaQu,
			borderColor: 'blue', // 첫 번째 라인의 색상
			borderWidth: 1
		}, {
			label: '성남시 위례',
			data: seongnamQu,
			borderColor: 'red', // 두 번째 라인의 색상
			borderWidth: 1
		}, {
			label: '하남시 위례',
			data: hanamQu,
			borderColor: 'green', // 두 번째 라인의 색상
			borderWidth: 1
		},
		]
	},
	options: {
		plugins: {
			legend: {
				labels: {
					font: {
						size: 20,
						family: 'Noto Sans KR'
					}
				}
			}
		}
	}
});

function resizeChart() {
	quarterlyPopChart.resize();
}

window.addEventListener('resize', resizeChart);


