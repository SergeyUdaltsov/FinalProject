var newRubric = new Object();

$(window).ready(function () {


    $("#createRubric").click(function () {

        var rubricName = $("#name").val();
        newRubric.name = rubricName;
        saveRubric();
        $(location).attr('href', 'http://localhost:9999/CreateAdvertisement.html');


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
        })

}

