var ctx = document.getElementById('forecastChart').getContext('2d');
let songpaF = ['27870', '36332', '42590', '48764', '48764', '48764', '48764'];
let seongnamF = ['42793', '43000', '43500', '42000', '44000', '42000', '42000'];
let hanamF = ['17000', '18000', '25000', '26000', '27000', '27000', '27500'];
var forecastChart = new Chart(ctx, {
	type: 'line',
	data: {
		labels: ['2019년', '2020년', '2021년', '2022년', '2023년', '2024년', '2025년'],
		datasets: [{
			label: '송파구 위례',
			data: songpaF,
			borderColor: 'blue', // 첫 번째 라인의 색상
			borderWidth: 1
		}, {
			label: '송파구 위례',
			data: seongnamF,
			borderColor: 'red', // 두 번째 라인의 색상
			borderWidth: 1
		}, {
			label: '하남시 위례',
			data: hanamF,
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
	forecastChart.resize();
}

window.addEventListener('resize', resizeChart);

