var countOfAdvOnPage = 5;

$(window).ready(function () {
    author = JSON.parse(localStorage.getItem('author'));

    var page = window.location.href.split("?")[1].split("=")[1];

    var authorId = author.id;

    showPageAdvertisements(page - 1, authorId);

    buildPagination(authorId);

    $("#home").click(function () {
        $(location).attr('href', 'http://localhost:9999/Site.html?id=1');
    })

});


function buildPagination(authorId) {

    var request = 'http://localhost:9999/agency/advertisement/count/author/' + authorId;

    $.get(request, function (data) {

        var countOfPages = Math.ceil(data / countOfAdvOnPage);
        var pagingPlace = $("#firstPage");

        for (var i = 1; i < countOfPages + 1; i++) {
            var page = '<a href="http://localhost:9999/MyAdvertisements.html?id=' + i + '">' + i + '</a>';
            pagingPlace.append(page);
        }

        var rightAquo = '<a href="http://localhost:9999/MyAdvertisements.html?id=' + countOfPages + '">&raquo;</a>';

        pagingPlace.append(rightAquo);

    });
}

function showPageAdvertisements(pageNumber, authorId) {

    var request = 'http://localhost:9999/agency/advertisement/get/by/author/' + authorId + '/' + pageNumber;

    $.getJSON(request, function (list) {
        $.each(list, function () {

            var advTitle = '<p><a href="http://localhost:9999/ShowAdvertisement.html?id=' + this.id + '&specific">' +
                this.title + '</a></p><hr>';

            $("#siteAdv").append(advTitle);
        })
    })
}