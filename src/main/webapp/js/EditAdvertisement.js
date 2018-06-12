var advToSaveDB = new Object();
var author = new Object();
var rubric = new Object();

$(window).ready(function () {

    var advertisementFromStorage = JSON.parse(localStorage.getItem('advertisementToEdit'));

    fillAdvFields(advertisementFromStorage);

    $("#save").click(function () {

        advToSaveDB.id = advertisementFromStorage.id;
        advToSaveDB.title = $("#advTitle").val();
        advToSaveDB.text = $("#advText").val();
        advToSaveDB.price = $("#advPrice").val();
        rubric.id = $(".select").val();
        author.id = advertisementFromStorage.authorId;
        advToSaveDB.author = author;
        advToSaveDB.rubric = rubric;

        var checkClosed = document.getElementById('myCheck');

        checkClosed.checked ? advToSaveDB.closed = true : advToSaveDB.closed = false;

        advToSaveDB.date = advertisementFromStorage.date;

        saveAdv(JSON.stringify(advToSaveDB));

        $(location).attr('href', 'http://localhost:9999/MyAdvertisements.html?id=1');

    });

    $("#home").click(function () {
        window.history.back();
    });
});

function fillAdvFields(advertisement) {

    // var tag = '<p>Title<input type="text" id="title" value="' + advertisement.title + '"/></p>';
    // $(".inputs").append(tag);
    //
    // tag = '<p>Text<input type="text" id="text" value="' + advertisement.text + '"/></p>';
    // $(".inputs").append(tag);
    //
    // tag = '<p>Price<input type="number" id="price" value="' + advertisement.price + '"/></p>';
    // $(".inputs").append(tag);

    $("#advTitle").val(advertisement.title);
    $("#advText").val(advertisement.text);
    $("#advPrice").val(advertisement.price);

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

function saveAdv(data) {

    fetch('http://localhost:9999/agency/advertisement/save', {
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