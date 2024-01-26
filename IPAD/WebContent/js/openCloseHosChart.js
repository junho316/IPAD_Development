const year = [];
const openings = [];
const closures = [];
let openCloseHosChart; // Declare the chart variable

function fetchDataAndRefreshChart() {
    return fetch(contextPath + '/json/hospital.do')
        .then(response => {
            if (!response.ok) {
                throw new Error('네트워크 응답이 올바르지 않습니다.');
            }
            return response.json();
        })
        .then(data => {
            for (let i = 0; i < data.length; i++) {
                year.push(data[i].year);
                openings.push(data[i].openings);
                closures.push(data[i].closures);
            }
        })
        .catch(error => console.error('에러:', error));
}

function chart() {
    var ctx = document.getElementById('openCloseHosChart').getContext('2d');
    openCloseHosChart = new Chart(ctx, {
        type: 'bar',
        data: {
            labels: year,
            datasets: [
                {
                    label: '개업',
                    backgroundColor: "#1E90FF",
                    data: openings
                },
                {
                    label: '폐업',
                    backgroundColor: "#F7464A",
                    data: closures
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
}

function resizeChart() {
    if (openCloseHosChart) {
        openCloseHosChart.resize();
    }
}

window.addEventListener('resize', resizeChart);

var temp = document.getElementById('temp1');
var pop = document.getElementById('temp2');

if (temp && pop) {
    temp.addEventListener("mouseover", function () {
        pop.style.display = 'block';
    });

    temp.addEventListener("mouseout", function () {
        pop.style.display = 'none';
    });
}

// Wait for the page to fully load before fetching data and creating the chart
window.onload = function () {
    fetchDataAndRefreshChart().then(chart);
};
