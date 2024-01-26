window.onload = function () {


  var diseaseName = document.querySelector('#diseaseName');
  var diseaseNameOption = diseaseName.options[diseaseName.selectedIndex].innerText;

  var subject = document.querySelector('#subject');
  var subjectOption = subject.options[subject.selectedIndex].innerText;

  var subjectCaption = document.querySelector('#subjectCaption');
  var saleCaption = document.querySelector('#saleCaption');
  subjectCaption.innerText = '*연도별/연령대별 ' + diseaseNameOption + ' 환자 수(단위:만명)';
  saleCaption.innerText = '*연도별/연령대별 ' + diseaseNameOption + ' 매출 수(단위:억원)';


  subject.onchange = function () {
    if (subjectOption === '진료 과목') {
      diseaseNameOption.options.length = 0;
      diseaseNameOption.innerText = '상병'
    }
  }

  var data = {
    patientCount: {
      ['K05']: [[150, 157, 190, 114, 120, 130], [10, 12, 14, 16, 18, 19], [20, 25, 24, 26, 28, 21], [30, 35, 37, 34, 31, 39], [41, 40, 48, 42, 46, 43], [55, 57, 51, 59, 53, 56], [67, 61, 64, 69, 62, 65], [71, 76, 73, 79, 72, 75]],
      ['K02']: [[158, 170, 140, 164, 150, 110], [Math.round(Math.random() * 100), Math.round(Math.random() * 100), Math.round(Math.random() * 100), Math.round(Math.random() * 100), Math.round(Math.random() * 100), Math.round(Math.random() * 100)], [Math.round(Math.random() * 100), Math.round(Math.random() * 100), Math.round(Math.random() * 100), Math.round(Math.random() * 100), Math.round(Math.random() * 100), Math.round(Math.random() * 100)], [Math.round(Math.random() * 100), Math.round(Math.random() * 100), Math.round(Math.random() * 100), Math.round(Math.random() * 100), Math.round(Math.random() * 100), Math.round(Math.random() * 100)], [Math.round(Math.random() * 100), Math.round(Math.random() * 100), Math.round(Math.random() * 100), Math.round(Math.random() * 100), Math.round(Math.random() * 100), Math.round(Math.random() * 100)], [Math.round(Math.random() * 100), Math.round(Math.random() * 100), Math.round(Math.random() * 100), Math.round(Math.random() * 100), Math.round(Math.random() * 100), Math.round(Math.random() * 100)], [Math.round(Math.random() * 100), Math.round(Math.random() * 100), Math.round(Math.random() * 100), Math.round(Math.random() * 100), Math.round(Math.random() * 100), Math.round(Math.random() * 100)], [Math.round(Math.random() * 100), Math.round(Math.random() * 100), Math.round(Math.random() * 100), Math.round(Math.random() * 100), Math.round(Math.random() * 100), Math.round(Math.random() * 100)]],
      ['K04']: [[150, 157, 160, 154, 170, 180], [Math.round(Math.random() * 100), Math.round(Math.random() * 100), Math.round(Math.random() * 100), Math.round(Math.random() * 100), Math.round(Math.random() * 100), Math.round(Math.random() * 100)], [Math.round(Math.random() * 100), Math.round(Math.random() * 100), Math.round(Math.random() * 100), Math.round(Math.random() * 100), Math.round(Math.random() * 100), Math.round(Math.random() * 100)], [Math.round(Math.random() * 100), Math.round(Math.random() * 100), Math.round(Math.random() * 100), Math.round(Math.random() * 100), Math.round(Math.random() * 100), Math.round(Math.random() * 100)], [Math.round(Math.random() * 100), Math.round(Math.random() * 100), Math.round(Math.random() * 100), Math.round(Math.random() * 100), Math.round(Math.random() * 100), Math.round(Math.random() * 100)], [Math.round(Math.random() * 100), Math.round(Math.random() * 100), Math.round(Math.random() * 100), Math.round(Math.random() * 100), Math.round(Math.random() * 100), Math.round(Math.random() * 100)], [Math.round(Math.random() * 100), Math.round(Math.random() * 100), Math.round(Math.random() * 100), Math.round(Math.random() * 100), Math.round(Math.random() * 100), Math.round(Math.random() * 100)], [Math.round(Math.random() * 100), Math.round(Math.random() * 100), Math.round(Math.random() * 100), Math.round(Math.random() * 100), Math.round(Math.random() * 100), Math.round(Math.random() * 100)]],
      ['K07']: [[158, 130, 111, 164, 180, 110], [Math.round(Math.random() * 100), Math.round(Math.random() * 100), Math.round(Math.random() * 100), Math.round(Math.random() * 100), Math.round(Math.random() * 100), Math.round(Math.random() * 100)], [Math.round(Math.random() * 100), Math.round(Math.random() * 100), Math.round(Math.random() * 100), Math.round(Math.random() * 100), Math.round(Math.random() * 100), Math.round(Math.random() * 100)], [Math.round(Math.random() * 100), Math.round(Math.random() * 100), Math.round(Math.random() * 100), Math.round(Math.random() * 100), Math.round(Math.random() * 100), Math.round(Math.random() * 100)], [Math.round(Math.random() * 100), Math.round(Math.random() * 100), Math.round(Math.random() * 100), Math.round(Math.random() * 100), Math.round(Math.random() * 100), Math.round(Math.random() * 100)], [Math.round(Math.random() * 100), Math.round(Math.random() * 100), Math.round(Math.random() * 100), Math.round(Math.random() * 100), Math.round(Math.random() * 100), Math.round(Math.random() * 100)], [Math.round(Math.random() * 100), Math.round(Math.random() * 100), Math.round(Math.random() * 100), Math.round(Math.random() * 100), Math.round(Math.random() * 100), Math.round(Math.random() * 100)], [Math.round(Math.random() * 100), Math.round(Math.random() * 100), Math.round(Math.random() * 100), Math.round(Math.random() * 100), Math.round(Math.random() * 100), Math.round(Math.random() * 100)]],
      ['K08']: [[150, 156, 140, 184, 200, 180], [Math.round(Math.random() * 100), Math.round(Math.random() * 100), Math.round(Math.random() * 100), Math.round(Math.random() * 100), Math.round(Math.random() * 100), Math.round(Math.random() * 100)], [Math.round(Math.random() * 100), Math.round(Math.random() * 100), Math.round(Math.random() * 100), Math.round(Math.random() * 100), Math.round(Math.random() * 100), Math.round(Math.random() * 100)], [Math.round(Math.random() * 100), Math.round(Math.random() * 100), Math.round(Math.random() * 100), Math.round(Math.random() * 100), Math.round(Math.random() * 100), Math.round(Math.random() * 100)], [Math.round(Math.random() * 100), Math.round(Math.random() * 100), Math.round(Math.random() * 100), Math.round(Math.random() * 100), Math.round(Math.random() * 100), Math.round(Math.random() * 100)], [Math.round(Math.random() * 100), Math.round(Math.random() * 100), Math.round(Math.random() * 100), Math.round(Math.random() * 100), Math.round(Math.random() * 100), Math.round(Math.random() * 100)], [Math.round(Math.random() * 100), Math.round(Math.random() * 100), Math.round(Math.random() * 100), Math.round(Math.random() * 100), Math.round(Math.random() * 100), Math.round(Math.random() * 100)], [Math.round(Math.random() * 100), Math.round(Math.random() * 100), Math.round(Math.random() * 100), Math.round(Math.random() * 100), Math.round(Math.random() * 100), Math.round(Math.random() * 100)]]
    },
    saleCount: {
      ['K05']: [[Math.floor(Math.random() * 10000), Math.floor(Math.random() * 10000), Math.floor(Math.random() * 10000), Math.floor(Math.random() * 10000), Math.floor(Math.random() * 10000), Math.floor(Math.random() * 10000)], [Math.floor(Math.random() * 10000), Math.floor(Math.random() * 10000), Math.floor(Math.random() * 10000), Math.floor(Math.random() * 10000), Math.floor(Math.random() * 10000), Math.floor(Math.random() * 10000)], [Math.floor(Math.random() * 10000), Math.floor(Math.random() * 10000), Math.floor(Math.random() * 10000), Math.floor(Math.random() * 10000), Math.floor(Math.random() * 10000), Math.floor(Math.random() * 10000)], [Math.floor(Math.random() * 10000), Math.floor(Math.random() * 10000), Math.floor(Math.random() * 10000), Math.floor(Math.random() * 10000), Math.floor(Math.random() * 10000), Math.floor(Math.random() * 10000)], [Math.floor(Math.random() * 10000), Math.floor(Math.random() * 10000), Math.floor(Math.random() * 10000), Math.floor(Math.random() * 10000), Math.floor(Math.random() * 10000), Math.floor(Math.random() * 10000)], [Math.floor(Math.random() * 10000), Math.floor(Math.random() * 10000), Math.floor(Math.random() * 10000), Math.floor(Math.random() * 10000), Math.floor(Math.random() * 10000), Math.floor(Math.random() * 10000)], [Math.floor(Math.random() * 10000), Math.floor(Math.random() * 10000), Math.floor(Math.random() * 10000), Math.floor(Math.random() * 10000), Math.floor(Math.random() * 10000), Math.floor(Math.random() * 10000)], [Math.floor(Math.random() * 10000), Math.floor(Math.random() * 10000), Math.floor(Math.random() * 10000), Math.floor(Math.random() * 10000), Math.floor(Math.random() * 10000), Math.floor(Math.random() * 10000)]],
      ['K02']: [[Math.floor(Math.random() * 10000), Math.floor(Math.random() * 10000), Math.floor(Math.random() * 10000), Math.floor(Math.random() * 10000), Math.floor(Math.random() * 10000), Math.floor(Math.random() * 10000)], [Math.floor(Math.random() * 10000), Math.floor(Math.random() * 10000), Math.floor(Math.random() * 10000), Math.floor(Math.random() * 10000), Math.floor(Math.random() * 10000), Math.floor(Math.random() * 10000)], [Math.floor(Math.random() * 10000), Math.floor(Math.random() * 10000), Math.floor(Math.random() * 10000), Math.floor(Math.random() * 10000), Math.floor(Math.random() * 10000), Math.floor(Math.random() * 10000)], [Math.floor(Math.random() * 10000), Math.floor(Math.random() * 10000), Math.floor(Math.random() * 10000), Math.floor(Math.random() * 10000), Math.floor(Math.random() * 10000), Math.floor(Math.random() * 10000)], [Math.floor(Math.random() * 10000), Math.floor(Math.random() * 10000), Math.floor(Math.random() * 10000), Math.floor(Math.random() * 10000), Math.floor(Math.random() * 10000), Math.floor(Math.random() * 10000)], [Math.floor(Math.random() * 10000), Math.floor(Math.random() * 10000), Math.floor(Math.random() * 10000), Math.floor(Math.random() * 10000), Math.floor(Math.random() * 10000), Math.floor(Math.random() * 10000)], [Math.floor(Math.random() * 10000), Math.floor(Math.random() * 10000), Math.floor(Math.random() * 10000), Math.floor(Math.random() * 10000), Math.floor(Math.random() * 10000), Math.floor(Math.random() * 10000)], [Math.floor(Math.random() * 10000), Math.floor(Math.random() * 10000), Math.floor(Math.random() * 10000), Math.floor(Math.random() * 10000), Math.floor(Math.random() * 10000), Math.floor(Math.random() * 10000)]],
      ['K04']: [[Math.floor(Math.random() * 10000), Math.floor(Math.random() * 10000), Math.floor(Math.random() * 10000), Math.floor(Math.random() * 10000), Math.floor(Math.random() * 10000), Math.floor(Math.random() * 10000)], [Math.floor(Math.random() * 10000), Math.floor(Math.random() * 10000), Math.floor(Math.random() * 10000), Math.floor(Math.random() * 10000), Math.floor(Math.random() * 10000), Math.floor(Math.random() * 10000)], [Math.floor(Math.random() * 10000), Math.floor(Math.random() * 10000), Math.floor(Math.random() * 10000), Math.floor(Math.random() * 10000), Math.floor(Math.random() * 10000), Math.floor(Math.random() * 10000)], [Math.floor(Math.random() * 10000), Math.floor(Math.random() * 10000), Math.floor(Math.random() * 10000), Math.floor(Math.random() * 10000), Math.floor(Math.random() * 10000), Math.floor(Math.random() * 10000)], [Math.floor(Math.random() * 10000), Math.floor(Math.random() * 10000), Math.floor(Math.random() * 10000), Math.floor(Math.random() * 10000), Math.floor(Math.random() * 10000), Math.floor(Math.random() * 10000)], [Math.floor(Math.random() * 10000), Math.floor(Math.random() * 10000), Math.floor(Math.random() * 10000), Math.floor(Math.random() * 10000), Math.floor(Math.random() * 10000), Math.floor(Math.random() * 10000)], [Math.floor(Math.random() * 10000), Math.floor(Math.random() * 10000), Math.floor(Math.random() * 10000), Math.floor(Math.random() * 10000), Math.floor(Math.random() * 10000), Math.floor(Math.random() * 10000)], [Math.floor(Math.random() * 10000), Math.floor(Math.random() * 10000), Math.floor(Math.random() * 10000), Math.floor(Math.random() * 10000), Math.floor(Math.random() * 10000), Math.floor(Math.random() * 10000)]],
      ['K07']: [[Math.floor(Math.random() * 10000), Math.floor(Math.random() * 10000), Math.floor(Math.random() * 10000), Math.floor(Math.random() * 10000), Math.floor(Math.random() * 10000), Math.floor(Math.random() * 10000)], [Math.floor(Math.random() * 10000), Math.floor(Math.random() * 10000), Math.floor(Math.random() * 10000), Math.floor(Math.random() * 10000), Math.floor(Math.random() * 10000), Math.floor(Math.random() * 10000)], [Math.floor(Math.random() * 10000), Math.floor(Math.random() * 10000), Math.floor(Math.random() * 10000), Math.floor(Math.random() * 10000), Math.floor(Math.random() * 10000), Math.floor(Math.random() * 10000)], [Math.floor(Math.random() * 10000), Math.floor(Math.random() * 10000), Math.floor(Math.random() * 10000), Math.floor(Math.random() * 10000), Math.floor(Math.random() * 10000), Math.floor(Math.random() * 10000)], [Math.floor(Math.random() * 10000), Math.floor(Math.random() * 10000), Math.floor(Math.random() * 10000), Math.floor(Math.random() * 10000), Math.floor(Math.random() * 10000), Math.floor(Math.random() * 10000)], [Math.floor(Math.random() * 10000), Math.floor(Math.random() * 10000), Math.floor(Math.random() * 10000), Math.floor(Math.random() * 10000), Math.floor(Math.random() * 10000), Math.floor(Math.random() * 10000)], [Math.floor(Math.random() * 10000), Math.floor(Math.random() * 10000), Math.floor(Math.random() * 10000), Math.floor(Math.random() * 10000), Math.floor(Math.random() * 10000), Math.floor(Math.random() * 10000)], [Math.floor(Math.random() * 10000), Math.floor(Math.random() * 10000), Math.floor(Math.random() * 10000), Math.floor(Math.random() * 10000), Math.floor(Math.random() * 10000), Math.floor(Math.random() * 10000)]],
      ['K08']: [[Math.floor(Math.random() * 10000), Math.floor(Math.random() * 10000), Math.floor(Math.random() * 10000), Math.floor(Math.random() * 10000), Math.floor(Math.random() * 10000), Math.floor(Math.random() * 10000)], [Math.floor(Math.random() * 10000), Math.floor(Math.random() * 10000), Math.floor(Math.random() * 10000), Math.floor(Math.random() * 10000), Math.floor(Math.random() * 10000), Math.floor(Math.random() * 10000)], [Math.floor(Math.random() * 10000), Math.floor(Math.random() * 10000), Math.floor(Math.random() * 10000), Math.floor(Math.random() * 10000), Math.floor(Math.random() * 10000), Math.floor(Math.random() * 10000)], [Math.floor(Math.random() * 10000), Math.floor(Math.random() * 10000), Math.floor(Math.random() * 10000), Math.floor(Math.random() * 10000), Math.floor(Math.random() * 10000), Math.floor(Math.random() * 10000)], [Math.floor(Math.random() * 10000), Math.floor(Math.random() * 10000), Math.floor(Math.random() * 10000), Math.floor(Math.random() * 10000), Math.floor(Math.random() * 10000), Math.floor(Math.random() * 10000)], [Math.floor(Math.random() * 10000), Math.floor(Math.random() * 10000), Math.floor(Math.random() * 10000), Math.floor(Math.random() * 10000), Math.floor(Math.random() * 10000), Math.floor(Math.random() * 10000)], [Math.floor(Math.random() * 10000), Math.floor(Math.random() * 10000), Math.floor(Math.random() * 10000), Math.floor(Math.random() * 10000), Math.floor(Math.random() * 10000), Math.floor(Math.random() * 10000)], [Math.floor(Math.random() * 10000), Math.floor(Math.random() * 10000), Math.floor(Math.random() * 10000), Math.floor(Math.random() * 10000), Math.floor(Math.random() * 10000), Math.floor(Math.random() * 10000)]]
    }
  }
  tableChange('K02', data);

  var subjectArea = document.getElementById('patientChart').getContext('2d');
  var subjectChart = new Chart(subjectArea, {
    type: 'line',
    data: {
      labels: ['2017', '2018', '2019', '2020', '2021', '2022'],
      datasets: [
        {
          label: '총 환자수',
          data: [150, 157, 160, 154, 170, 180],
          borderWidth: 3,
          borderColor: 'black',
          backgroundColor: 'black'
          // tension: 0.4,
        },
        {
          label: '20세 미만',
          data: [10, 12, 14, 16, 18, 19],
          backgroundColor: ['red'],
          borderWidth: 1,
          borderColor: 'red',
          hidden: true
          // tension: 0.4,
        },
        {
          label: '20-29세',
          data: [20, 25, 24, 26, 28, 21],
          backgroundColor: ['blue'],
          borderWidth: 1,
          borderColor: 'blue',
          hidden: true
          // tension: 0.4,
        },
        {
          label: '30-39세',
          data: [30, 35, 37, 34, 31, 39],
          backgroundColor: ['green'],
          borderWidth: 1,
          borderColor: 'green',
          hidden: true
          // tension: 0.4,
        },
        {
          label: '40-49세',
          data: [41, 40, 48, 42, 46, 43],
          backgroundColor: ['purple'],
          borderWidth: 1,
          borderColor: 'purple',
          hidden: true
          // tension: 0.4,
        },
        {
          label: '50-59세',
          data: [55, 57, 51, 59, 53, 56],
          backgroundColor: ['orange'],
          borderWidth: 1,
          borderColor: 'orange',
          hidden: true
          // tension: 0.4,
        },
        {
          label: '60-69세',
          data: [67, 61, 64, 69, 62, 65],
          backgroundColor: ['brown'],
          borderWidth: 1,
          borderColor: 'brown',
          hidden: true
          // tension: 0.4,
        },
        {
          label: '70세 이상',
          data: [71, 76, 73, 79, 72, 75],
          backgroundColor: ['cadetblue'],
          borderWidth: 1,
          borderColor: 'cadetblue',
          hidden: true
          // tension: 0.4,
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
          min: 0,
          max: 200,
          position: 'top',
          ticks: {
            callback: function (value, index, values) {
              if (index === 0) {
                return '(단위:만명)' + value;
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
          text: diseaseNameOption + ' 환자 수 추이',
          font: {
            size: 30,
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

  var saleArea = document.getElementById('saleChart').getContext('2d');
  var saleChart = new Chart(saleArea, {
    type: 'line',
    data: {
      labels: ['2017', '2018', '2019', '2020', '2021', '2022'],
      datasets: [
        {
          label: '총 매출',
          data: [Math.floor(Math.random() * 10000), Math.floor(Math.random() * 10000), Math.floor(Math.random() * 10000), Math.floor(Math.random() * 10000), Math.floor(Math.random() * 10000), Math.floor(Math.random() * 10000)],
          borderWidth: 3,
          borderColor: 'black',
          backgroundColor: 'black',

          // tension: 0.4,
        },
        {
          label: '20세 미만',
          data: [Math.floor(Math.random() * 10000), Math.floor(Math.random() * 10000), Math.floor(Math.random() * 10000), Math.floor(Math.random() * 10000), Math.floor(Math.random() * 10000), Math.floor(Math.random() * 10000)],
          backgroundColor: ['red'],
          borderWidth: 1,
          borderColor: 'red',
          hidden: true
          // tension: 0.4,
        },
        {
          label: '20-29세',
          data: [Math.floor(Math.random() * 10000), Math.floor(Math.random() * 10000), Math.floor(Math.random() * 10000), Math.floor(Math.random() * 10000), Math.floor(Math.random() * 10000), Math.floor(Math.random() * 10000)],
          backgroundColor: ['blue'],
          borderWidth: 1,
          borderColor: 'blue',
          hidden: true
          // tension: 0.4,
        },
        {
          label: '30-39세',
          data: [Math.floor(Math.random() * 10000), Math.floor(Math.random() * 10000), Math.floor(Math.random() * 10000), Math.floor(Math.random() * 10000), Math.floor(Math.random() * 10000), Math.floor(Math.random() * 10000)],
          backgroundColor: ['green'],
          borderWidth: 1,
          borderColor: 'green',
          hidden: true
          // tension: 0.4,
        },
        {
          label: '40-49세',
          data: [Math.floor(Math.random() * 10000), Math.floor(Math.random() * 10000), Math.floor(Math.random() * 10000), Math.floor(Math.random() * 10000), Math.floor(Math.random() * 10000), Math.floor(Math.random() * 10000)],
          backgroundColor: ['purple'],
          borderWidth: 1,
          borderColor: 'purple',
          hidden: true
          // tension: 0.4,
        },
        {
          label: '50-59세',
          data: [Math.floor(Math.random() * 10000), Math.floor(Math.random() * 10000), Math.floor(Math.random() * 10000), Math.floor(Math.random() * 10000), Math.floor(Math.random() * 10000), Math.floor(Math.random() * 10000)],
          backgroundColor: ['orange'],
          borderWidth: 1,
          borderColor: 'orange',
          hidden: true
          // tension: 0.4,
        },
        {
          label: '60-69세',
          data: [Math.floor(Math.random() * 10000), Math.floor(Math.random() * 10000), Math.floor(Math.random() * 10000), Math.floor(Math.random() * 10000), Math.floor(Math.random() * 10000), Math.floor(Math.random() * 10000)],
          backgroundColor: ['brown'],
          borderWidth: 1,
          borderColor: 'brown',
          hidden: true
          // tension: 0.4,
        },
        {
          label: '70세 이상',
          data: [Math.floor(Math.random() * 10000), Math.floor(Math.random() * 10000), Math.floor(Math.random() * 10000), Math.floor(Math.random() * 10000), Math.floor(Math.random() * 10000), Math.floor(Math.random() * 10000)],
          backgroundColor: ['cadetblue'],
          borderWidth: 1,
          borderColor: 'cadetblue',
          hidden: true
          // tension: 0.4,
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
          min: 0,
          max: 16000,
          position: 'top',
          ticks: {
            callback: function (value, index, values) {
              if (index === 0) {
                return '(단위:억원)' + value;
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
          text: diseaseNameOption + ' 매출 추이',
          font: {
            size: 30,
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

  var checkBoxes = document.querySelectorAll('.checkBoxes input')
  // console.log(checkBoxes);

  for (var i = 0; i < checkBoxes.length; i++) {
    checkBoxes[i].onclick = function (event) {
      var result = '';
      if (event.target.checked) {
        result = parseInt(event.target.value);
        saleChart.show(result)
        subjectChart.show(result)
      } else {
        result = parseInt(event.target.value);
        saleChart.hide(result);
        subjectChart.hide(result);
      }
    }
  }

  //     var result = '';
  //     if (event.target.checked) {
  //       result = parseInt(event.target.value);
  //       console.log(result);
  //       subjectChart.data.datasets.push(chartData[0][result]);
  //       saleChart.data.datasets.push(chartData[1][result]);
  //       subjectChart.update();
  //       saleChart.update();
  //     } else {
  //       result = parseInt(event.target.value);
  //       for (var j = 0; j < saleChart.data.datasets.length; j++) {
  //         if (saleChart.data.datasets[j].label === chartData[0][result].label) {
  //           saleChart.data.datasets.splice(j, 1);
  //           subjectChart.data.datasets.splice(j, 1)
  //           break;
  //         }
  //       }
  //       subjectChart.update();
  //       saleChart.update();
  //     }
  //   }
  // }

  diseaseName.onchange = function () {
    var diseaseNames = diseaseName.options[diseaseName.selectedIndex].innerText;
    var diseaseOption = diseaseName.options[diseaseName.selectedIndex].value;
    var title = diseaseNames;
    saleChart.options.plugins.title.text = title + ' 매출 추이';
    subjectChart.options.plugins.title.text = title + ' 환자 수 추이';

    var subjectCaption = document.querySelector('#subjectCaption');
    var saleCaption = document.querySelector('#saleCaption');
    subjectCaption.innerText = '*연도별/연령대별 ' + title + ' 환자 수(단위:만명)';
    saleCaption.innerText = '*연도별/연령대별 ' + title + ' 매출(단위:억원)';
    console.log(Object.keys(data.patientCount).length)

    for (var j = 0; j < Object.keys(data.patientCount).length; j++) {
      if (diseaseOption === Object.keys(data.patientCount)[j]) {
        for (var i = 0; i < subjectChart.data.datasets.length; i++) {
          var newPatientData = data.patientCount[diseaseOption][i];
          var newSaleData = data.saleCount[diseaseOption][i];
          subjectChart.data.datasets[i].data = newPatientData;
          saleChart.data.datasets[i].data = newSaleData;
        }
        break;
      }
    }
    tableChange(diseaseOption, data);

    saleChart.update();
    subjectChart.update();
  }

  var showSubjectBtn = document.querySelector('#showSubjectTable');
  var subjectTable = document.querySelector('#subjectTable');
  showSubjectBtn.onclick = function () {

    if (subjectTable.style.display === 'none') {
      subjectTable.style.display = 'block';
    } else {
      subjectTable.style.display = 'none'
    }
  }
  var showSaleBtn = document.querySelector('#showSaleTable');
  var saleTable = document.querySelector('#saleTable');
  showSaleBtn.onclick = function () {

    if (saleTable.style.display === 'none') {

      saleTable.style.display = 'block';
    } else {
      saleTable.style.display = 'none'
    }
  }



}

function tableChange(e, f) {
  var subjectTable = document.querySelectorAll('#subjectTable tbody td');
  var saleTable = document.querySelectorAll('#saleTable tbody td');

  console.log(subjectTable);
  var k = 0;
  for (var i = 0; i < 6; i++) {
    for (var j = 0; j < 8; j++) {
      subjectTable[k].innerText = f.patientCount[e][j][i];
      saleTable[k].innerText = f.saleCount[e][j][i];
      k++;
    }
  }
}