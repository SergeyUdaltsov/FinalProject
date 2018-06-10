$(window).ready(function () {

    var id = window.location.href.split("?")[1].split("=")[1].split("&")[0];

    var myAdv = window.location.href.split("&")[1];

    alert(myAdv);

    if(myAdv === "true") {
        $("#home").append('<button id="edit">Edit</button>');
    }


    var request = 'http://localhost:9999/agency/advertisement/get/' + id;
    $.getJSON(request, function (res) {

        $("#title").append(res.title);
        $("#text").append(res.text);
        $("#price").append(res.price);
        $("#citiesList").append(res.authorCity);

        if (res.closed) {
            $("#closed").append('Advertisement marked as closed and will be deleted');
        }



        var date = res.date.dayOfMonth + '-' + res.date.monthValue + '-' + res.date.year;
        $("#date").append(date);

        var phones = res.authorPhones;
        for(var i = 0; i < phones.length; i++) {
            var phone = '<p>' + phones[i].number + '</p>';
            $("#phones").append(phone);
        }

        var seller = res.authorFirstName + ' ' + res.authorLastName;
        $("#seller").append(seller);

    });

    $("#home").click(function () {
        window.history.back();
    })


});