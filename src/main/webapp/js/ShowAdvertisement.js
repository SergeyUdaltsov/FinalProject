var advertisement = null;

$(window).ready(function () {

    var advertisementId = window.location.href.split("?")[1].split("=")[1].split("&")[0];

    var checkIfSpecific = window.location.href.split("&")[1];

    if (checkIfSpecific === "common") {
        $("#editAdvertisement").hide();
    }


    var url = 'http://localhost:9999/agency/advertisement/get/' + advertisementId;

    showAdvertisementInfo(url);


    $("#home").click(function () {
        window.history.back();
    });

    $("#editAdvertisement").click(function () {
        localStorage.setItem('advertisementToEdit', JSON.stringify(advertisement));

        $(location).attr('href', 'http://localhost:9999/EditAdvertisement.html');

    });


});

function showAdvertisementInfo(url) {
    $.getJSON(url, function (res) {

        advertisement = res;

        $("#title").append(res.title);
        $("#text").append(res.text);
        $("#price").append(res.price);
        $("#citiesList").append(res.authorCity);

       if(res.closed){$("#closed").text('Advertisement was marked as closed and will be deleted.');}

        $("#date").append(res.date);

        var phones = res.authorPhones;
        for (var i = 0; i < phones.length; i++) {
            var phone = '<p>' + phones[i].number + '</p>';
            $("#phones").append(phone);
        }

        var seller = res.authorFirstName + ' ' + res.authorLastName;
        $("#seller").append(seller);

    });
}