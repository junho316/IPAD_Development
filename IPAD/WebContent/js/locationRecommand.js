function writeRankList() {
    getRankList();

    document.getElementById('first').innerText = list[0];
    document.getElementById('second').innerText = list[1];
    document.getElementById('third').innerText = list[2];
}
var list = [];

function getRankList() {
    var checkImpl = document.getElementById('implant').checked;
    var checkOrth = document.getElementById('orthodontics').checked;
    console.log(checkImpl);
    console.log(checkOrth);
    var formData = new FormData();
    formData.append("checkImpl", checkImpl);
    formData.append("checkOrth", checkOrth)

    var xhr = new XMLHttpRequest();
    xhr.open("POST", contextPath + "/locationRecommand/submit.do", true);
    xhr.setRequestHeader("Content-Type", "application/json");
    xhr.onreadystatechange = function () {
        if (xhr.status == 200) {
            var result = JSON.stringify(xhr.responseText);
            console.log(result);
        }
    };
    xhr.send(formData);
    //    var jsonData = new Object();
    //    jsonData.key1 = checkImpl;
    //    jsonData.key2 = checkOrth;
    //
    //    var sendData = JSON.stringify(jsonData);
    // $.ajax({
    //     url: contextPath + "/json/locationRecommand.do" + "?data=" + encodedURIComponent(sendData),
    //     success: function (retData) {
    //         var resObj = JSON.parse(retData)

    //     },
    //     error: function (error) {
    //         console.log(error);
    //     }

    // })

    // fetch(contextPath + "/json/locationRecommand.do")
    //     .then(response => response.json())
    //     .then(data => {
    //         console.log(data);
    //         for (let i = 0; i < data.length; i++) {
    //             list.push(data[i]["adm_nm"]);
    //         }
    //     })
    //     .catch(error => {
    //         console.error('Error :', error);
    //     });
}


