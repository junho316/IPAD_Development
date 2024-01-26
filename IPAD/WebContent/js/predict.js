window.onload=function(){

var chartArea = document.getElementById('populationChart').getContext('2d');
  var myChart = new Chart(chartArea, {
    type: 'bar',
    data: {
      labels:  ['10대', '20대','30대','40대','50대','60대','70대 이상'],      
      datasets: [{
        // label: '인구',
        data: [Math.floor(Math.random() * 100),Math.floor(Math.random() * 100),Math.floor(Math.random() * 100),Math.floor(Math.random() * 100),Math.floor(Math.random() * 100),Math.floor(Math.random() * 100),Math.floor(Math.random() * 100)],
        backgroundColor: ['rgba(132, 235, 220, 0.82)','rgba(190, 132, 235, 0.82)','rgba(136, 235, 132, 0.82)','rgba(235, 197, 132, 0.82)','rgba(202, 33, 162, 0.82)','rgba(39, 110, 18, 0.82)','rgba(221, 146, 158, 0.92)'],
        // borderColor: 'none',
        borderWidth: 1        
      }]
    },
    options: {
      indexAxis:'y',
      plugins: {
        title: {
          display: false,
          text: '인구',
          font:{
            size: 30,
          }
        },
        legend: {
          display: false
 
        }
      },
      responsive: false,
      
    },
  });
  var chartArea = document.getElementById('saleChart').getContext('2d');
  var myChart = new Chart(chartArea, {
    type: 'doughnut',
    data: {
      labels:  ['10대', '20대','30대','40대','50대','60대','70대 이상'],      
      datasets: [{
        // label: '인구',
        data: [Math.floor(Math.random() * 100),Math.floor(Math.random() * 100),Math.floor(Math.random() * 100),Math.floor(Math.random() * 100),Math.floor(Math.random() * 100),Math.floor(Math.random() * 100),Math.floor(Math.random() * 100)],
        backgroundColor: ['rgba(132, 235, 220, 0.82)','rgba(190, 132, 235, 0.82)','rgba(136, 235, 132, 0.82)','rgba(235, 197, 132, 0.82)','rgba(202, 33, 162, 0.82)','rgba(39, 110, 18, 0.82)','rgba(221, 146, 158, 0.92)'],
        // borderColor: 'none',
        borderWidth: 1        
      }]
    },
    options: {
      plugins: {
        title: {
          display: false,
          text: '인구',
          font:{
            size: 30,
          }
        },
        legend: {
          display: true
 
        }
      },
      responsive: false,
      
    },
  });


}