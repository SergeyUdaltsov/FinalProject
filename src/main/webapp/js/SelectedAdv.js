$(window).ready(function () {

    var order = JSON.parse(localStorage.getItem("order"));

    var position = $("#siteAdv");

    $("#greeting").append('Here are advertisements filtered by selected parameters');

    showFilteredAdv(position, order.rubricId, order.priceFrom, order.priceTo);



    $("#back").click(function () {

        window.history.back();
    });
});


function showFilteredAdv(element, rubricId, priceFrom, priceTo) {

    var request = 'http://localhost:9999/agency/advertisement/filter/' + rubricId + '/' + priceFrom + '/' + priceTo;

    $.getJSON(request, function (list) {
        $.each(list, function () {

            var advTitle = '<p><a href="http://localhost:9999/ShowAdvertisement.html?id=' + this.id + '&common">' +
                this.title + '</a></p><hr>';

            $(element).append(advTitle);
        })
    })
}