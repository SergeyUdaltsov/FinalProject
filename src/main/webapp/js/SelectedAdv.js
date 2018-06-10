$(window).ready(function () {

    var order = JSON.parse(localStorage.getItem("order"));

    var position = $("#siteAdv");

    showFilteredAdv(position, order.rubricId, order.priceFrom, order.priceTo);



    $("#back").click(function () {

        window.history.back();
    });




});


function showFilteredAdv(element, rubricId, priceFrom, priceTo) {

    var request = 'http://localhost:9999/agency/advertisement/filter/' + rubricId + '/' + priceFrom + '/' + priceTo;

    $.getJSON(request, function (list) {
        $.each(list, function () {

            var advTitle = '<p><a href="http://localhost:9999/ShowAdvertisement.html?id=' + this.id + '">' +
                this.title + '</a></p>';

            $(element).append(advTitle);
        })
    })
}