var newRubric = new Object();

$(window).ready(function () {


    var rubrics = JSON.parse(localStorage.getItem('rubricNames'));

    $("#createRubric").click(function () {

        var rubricName = $("#name").val();
        var rubricExist = false;

        for (var i = 0; i < rubrics.length; i++) {
            if(rubrics[i] === rubricName) {
                rubricExist = true;
            }
        }

        newRubric.name = rubricName;

        rubricExist ? alert('Such name already exists') : saveRubric()

    })


});

function saveRubric() {

    fetch('http://localhost:9999/agency/rubric/save', {
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        method: 'POST',
        body: JSON.stringify(newRubric)
    })
        .then(function (res) {
            // debugger
            if (res.status === 200) {
                return res.json();
            }
            alert('The rubric must be unique');
        })
        .then(function (res) {
        });
    $(location).attr('href', 'http://localhost:9999/CreateAdvertisement.html');

}

