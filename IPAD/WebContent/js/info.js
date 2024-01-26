const infoData = [];

function getInfo() {
  fetch(contextPath + '/json/info.do')
    .then(response => response.json())
    .then(data => {
      console.log(data)
      for (let i = 0; i < data.length; i++) {
        var newArray = [data[i]["adm_cd"], data[i]["population"], data[i]["floatingPp"], data[i]["houseHold"], data[i]["income"], data[i]["sale"], data[i]["dentalClinic"]];
        infoData.push(newArray);
      }
    })
    .catch(error => {
      console.error('Error:', error);
    });

}
function showInfo() {
  var box = document.querySelector('.iconBox');
  var value = box.getAttribute('name');

  for (let j = 0; j < infoData.length; j++) {

    eval('infoDatas.infoData' + j).innerText = infoData[j][value];
  }

}

