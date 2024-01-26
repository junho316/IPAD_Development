let year;
let groupedData;
let songpa;
let sungnam;
let hanam;

function getresidentChart() {
    fetch(contextPath + '/json/residentPopulation.do')
        .then(response => {
            if (!response.ok) {
                throw new Error('네트워크 응답이 올바르지 않습니다.');
            }
            return response.json();
        })
        .then(data => {
            console.log('데이터를 성공적으로 받았습니다:', data);
            groupedData = data.reduce(function (result, current) {
                if (!result[current.adm_cd]) {
                    result[current.adm_cd] = [];
                }
                result[current.adm_cd].push(parseInt(current.population_total));
                return result;
            }, {});

            var firstAdmCd = Object.keys(groupedData)[0];
            songpa = groupedData[firstAdmCd];
            var secondAdmCd = Object.keys(groupedData)[1];
            sungnam = groupedData[secondAdmCd];
            var thirdAdmCd = Object.keys(groupedData)[2];
            hanam = groupedData[thirdAdmCd];
            year = [...new Set(data.map(entry => entry.year))];
            updateChart();
        })
        .catch(error => console.error('에러:', error));
}

var ctx = document.getElementById('residentChart').getContext('2d');

var residentChart = new Chart(ctx, {
    type: 'line',
    data: {
        labels: year,
        datasets: [
            {
                label: '송파구 위례',
                data: songpa,
                backgroundColor: 'blue',
                type: 'bar'
            },
            {
                label: '성남시 위례',
                data: sungnam,
                backgroundColor: 'red',
                type: 'bar'
            },
            {
                label: '하남시 위례',
                data: hanam,
                backgroundColor: 'green',
                type: 'bar'
            }
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
    residentChart.resize();
}

window.addEventListener('resize', resizeChart);

function updateChart() {
    residentChart.data.labels = year;
    residentChart.data.datasets[0].data = songpa;
    residentChart.data.datasets[1].data = sungnam;
    residentChart.data.datasets[2].data = hanam;

    residentChart.update();
}

getresidentChart();
