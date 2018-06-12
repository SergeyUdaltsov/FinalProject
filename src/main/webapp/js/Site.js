var author = new Object();
var order = new Object();
var countOfAdvOnPage = 5;

$(window).ready(function () {

    loadAuthorInfo();

    $("#logIn").click(function () {
        $(location).attr('href', 'http://localhost:9999/index.jsp');
    });

    var greeting = "<h2>Hello, " + author.firstName + ", here are advertisements for you!<h2>";

    $("#greeting").append(greeting);

    var page = window.location.href.split("?")[1].split("=")[1];

    showPageAdvertisements(page - 1, countOfAdvOnPage);

    buildPagination();

    fillRubric();

    $("#show").click(function () {

        order.rubricId = $("#filter").val();
        order.priceFrom = ($("#from").val() !== '') && ($("#from").val() !== NaN) ? $("#from").val() : 0;
        order.priceTo = ($("#to").val() !== '') && ($("#to").val() !== NaN)? $("#to").val() : 1000000;

        localStorage.setItem("order", JSON.stringify(order));
        $(location).attr('href', 'http://localhost:9999/SelectedAdv.html');

    });

    $("#createAdv").click(function () {

        $(location).attr('href', 'http://localhost:9999/CreateAdvertisement.html');

    });

    $("#myAdv").click(function () {
        $(location).attr('href', 'http://localhost:9999/MyAdvertisements.html?id=1');
    });

    $("#logOut").click(function () {

        localStorage.setItem("author", null);
        $(location).attr('href', 'http://localhost:9999/index.jsp');
    });

});

function loadAuthorInfo() {

    author = JSON.parse(localStorage.getItem('author'));

    (author === null) ? alert('Log in, please') : $("#logIn").hide();

}

function buildPagination() {
    $.get('http://localhost:9999/agency/advertisement/count', function (data) {

        var countOfPages = Math.ceil(data/countOfAdvOnPage);
        var pagingPlace = $("#firstPage");

        for (var i = 1; i < countOfPages + 1; i++){
            var page = '<a href="http://localhost:9999/Site.html?id=' + i + '">' + '    ' + i + '    ' + '</a>';
            pagingPlace.append(page);
        }

        var rightAquo = '<a href="http://localhost:9999/Site.html?id=' + countOfPages + '">&raquo;</a>';

        pagingPlace.append(rightAquo);

    });
}

function showPageAdvertisements(pageNumber, countOfAdv) {

    var request = 'http://localhost:9999/agency/advertisement/get/page/' + pageNumber + '/' + countOfAdv;

    $.getJSON(request, function (list) {
        $.each(list, function () {

            var advTitle = '<p><a href="http://localhost:9999/ShowAdvertisement.html?id=' + this.id + '&common">' +
                this.title + '</a></p><hr>';

            $("#siteAdv").append(advTitle);
        })
    })
}



function fillRubric() {
    $.getJSON('http://localhost:9999/agency/rubric/get/all', function (result) {

        $.each(result, function () {

            var opt = $("<option value='" + this.id + "'></option>").text(this.name);
            $("#filter").append(opt);
        });
    });

}




