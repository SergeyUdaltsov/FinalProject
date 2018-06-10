var newAdv = new Object();
var author = new Object();
var rubric = new Object();

$(document).ready(function () {

    var authorFromStorage = JSON.parse(localStorage.getItem('author'));

    var select = $("#rubricName");

    $.getJSON('http://localhost:9999/agency/rubric/get/all', function (result) {

        $.each(result, function () {

            var opt = $("<option value='" + this.id + "'></option>").text(this.name);
            select.append(opt);
        });
    });


    $("#createNewRubric").click(function () {
        $(location).attr('href', 'http://localhost:9999/CreateRubric.html');
    });

    $("#createAdv").click(function () {
        author.id = authorFromStorage.id;
        rubric.id = $("#rubricName").val();
        newAdv.rubric = rubric;
        newAdv.author = author;
        newAdv.title = $("#titleName").val();
        newAdv.text = $("#text").val();
        newAdv.price = $("#price").val();

        console.log(newAdv);

        saveAdv();
        $(location).attr('href', 'http://localhost:9999/Site.html?id=1');

    });

    $("#cancel").click(function () {

        window.history.back();

    });
});

function saveAdv() {

    fetch('http://localhost:9999/agency/advertisement/save', {
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        method: 'POST',
        body: JSON.stringify(newAdv)
    })
        .then(function (res) {
            if (res.success) {
                return res.json();
            }
        })
        .then(function (res) {
        })

}

