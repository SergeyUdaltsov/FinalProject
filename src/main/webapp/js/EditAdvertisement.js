var advToSaveDB = new Object();
var author = new Object();
var rubric = new Object();

$(window).ready(function () {

    var advertisement = JSON.parse(localStorage.getItem('advertisementToEdit'));

    fillAdvFields(advertisement);

    $("#save").click(function () {


        advToSaveDB.id = advertisement.id;
        advToSaveDB.title = $("#title").val();
        advToSaveDB.text = $("#text").val();
        advToSaveDB.price = $("#price").val();
        rubric.id = $(".select").val();
        author.id = advertisement.authorId;
        advToSaveDB.author = author;
        advToSaveDB.rubric = rubric;

        var check = document.getElementById('myCheck');

        if (check.checked === true) {
            advToSaveDB.closed = true;
        }
        var month = ((advertisement.date).monthValue < 10) ? '0' + (advertisement.date).monthValue :
            (advertisement.date).monthValue;

        var day = ((advertisement.date).dayOfMonth < 10) ? '0' + (advertisement.date).dayOfMonth :
            (advertisement.date).dayOfMonth;

        var date = (advertisement.date).year+ '-' + month + '-' + day;

        advToSaveDB.date = date;

        saveAdv(JSON.stringify(advToSaveDB));

        $(location).attr('href', 'http://localhost:9999/MyAdvertisements.html?id=1');

    });

    $("#home").click(function () {
        window.history.back();
    });
});

function prepareAdv(advertisement, advertisementToSend) {
    advertisementToSend.date = advertisement.date;
    alert(advertisementToSend.date);
    var advToBase = JSON.stringify(advertisementToSend);
    saveAdv(advToBase);
}

function saveAdv(data) {

    // data.date = dataDate;
    //
    // if (data.date !== null) {
    //     var dataToSend = JSON.stringify(data);
    // }

    fetch('http://localhost:9999/agency/advertisement/edit', {
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        method: 'POST',
        body: data
    })
        .then(function (res) {
            if (res.success) {
                return res.json();
            }
        })
        .then(function (res) {
        })

}


function fillAdvFields(advertisement) {

    var tag = '<p>Title<input type="text" id="title" value="' + advertisement.title + '"/></p>';
    $(".inputs").append(tag);

    tag = '<p>Text<input type="text" id="text" value="' + advertisement.text + '"/></p>';
    $(".inputs").append(tag);

    tag = '<p>Price<input type="number" id="price" value="' + advertisement.price + '"/></p>';
    $(".inputs").append(tag);

    loadRurics(advertisement);

}

function loadRurics(advertisement) {

    var select = $(".select");

    select.append($("<option value='" + advertisement.rubricId + "'></option>").text('previous rubric'));

    $.getJSON('http://localhost:9999/agency/rubric/get/all', function (result) {

        $.each(result, function () {

            var opt = $("<option value='" + this.id + "'></option>").text(this.name);
            select.append(opt);
        });
    });
}
// function myFunction() {
//     if($("#myCheck").checked === true) {
//         advToSaveDB.closed = true;
//         alert(advToSaveDB.closed);
//     }
// }