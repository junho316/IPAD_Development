function writeRankList() {

    document.getElementById('first').innerText = list[0];
    document.getElementById('second').innerText = list[1];
    document.getElementById('third').innerText = list[2];
}

var list = [];

function getRankList() {

    var checkImpl = document.getElementById('implant').checked;
    var checkOrth = document.getElementById('orthodontics').checked;
    var data = {
        checkImpl: checkImpl,
        checkOrth: checkOrth
    };


    fetch(contextPath + "/locationRecommand/submit.do", {
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
            console.log(jsonArray);
            list.length = 0;
            for (let i = 0; i < jsonArray.length; i++) {
                list.push(jsonArray[i]["adm_nm"]);
            }
        })
        .then(() => writeRankList())
        .catch(error => {
            console.error('에러 발생:', error);
        });
}

