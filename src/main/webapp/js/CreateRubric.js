var newRubric = new Object();

$(window).ready(function () {


    var rubrics = JSON.parse(localStorage.getItem('rubricNames'));

    $("#createRubric").click(function () {

        var rubricName = $("#name").val();
        var rubricExist = false;

        for (var i = 0; i < rubrics.length; i++) {
            if(rubrics[i].toUpperCase() === rubricName.toUpperCase()) {
                rubricExist = true;
            }
        }

        newRubric.name = rubricName;

        (rubricExist || rubricName === '') ? alert('Such name already exists or you did not enter it.') : saveRubric()

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
            if (res.status === 200) {
                return res.json();
            }
            // alert('The rubric must be unique');
        })
        .then(function (res) {

        });
    $(location).attr('href', 'http://localhost:9999/CreateAdvertisement.html');

}

