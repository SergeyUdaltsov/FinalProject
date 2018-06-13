var newAuthor = new Object();
var phones = [];
var phone1 = new Object();
var phone2 = new Object();
var emails = new Set();
var registrable = false;
var emailCorrect = false;

$(window).ready(function () {

    var address = new Object();

    var elementToFill = $("#city");

    fillCity(elementToFill);

    validateEmail(function (emails) {

        var email = $("#email");
        var emailWarning = $("#em");
        email.on('input', function () {
            emailCorrect = true;
            emailWarning.empty();

            if (emails.has(email.val()) || email.val() === "") {

                emailWarning.append('already registered');

                emailCorrect = false;
            }
        });
    });

    $("#addPhone").click(function () {

        var tagInput = '<br><input id="additionalPhone"/>';

        $("#anotherPhone").append(tagInput);

        $("#addPhone").hide();
    });

    $("#register").click(function () {

        registrable = validateForm();

        if (!registrable) {
            return;
        }

        newAuthor.firstName = $("#firstName").val();
        newAuthor.lastName = $("#lastName").val();
        newAuthor.password = $("#password").val();
        newAuthor.email = $("#email").val();

        address.city = $("#city").val();
        address.address = $("#address").val();

        phone1.number = $("#phone").val();
        phone2.number = $("#additionalPhone").val();

        addPhone(phone1);
        addPhone(phone2);
        newAuthor.phones = phones;

        newAuthor.address = address;
        newAuthor.advertisements = [];

        var authorSerialized = JSON.stringify(newAuthor);

        saveAuthor(authorSerialized);

    })
});

function saveAuthor(body) {

    fetch('http://localhost:9999/agency/author/save', {
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json',
            'Charset': 'UTF-8'
        },
        method: 'POST',
        body: body
    })
        .then(function (res) {
            if (res.status === 200) {
                return res.json();
            }
        })
        .then(function (res) {
            if (res !== undefined) {

                var authorToStore = JSON.stringify(res);
                localStorage.setItem("author", authorToStore);
                $(location).attr('href', 'http://localhost:9999/Site.html?id=1');
            } else {
                alert("Something was wrong, try again.")
            }
        })
}

function addPhone(phone) {
    if ((phone.number !== undefined) && (phone.number !== "")) {
        phones.push(phone);
    }
}

function fillCity(select) {

    var citiesList = ['KHARKIV', 'KYIV', 'DNEPR', 'ODESSA', 'DONETSK', 'ZHITOMIR'];

    for (var i = 0; i < citiesList.length; i++) {
        var opt = $("<option value='" + citiesList[i] + "'></option>").text(citiesList[i]);
        select.append(opt);
    }

}

function validateEmail(callback) {

    var request = 'http://localhost:9999/agency/author/get/all';

    $.getJSON(request, function (list) {
        $.each(list, function () {

            emails.add(this.email);
        });
        callback(emails);
    });
}

function validateForm() {

    if (($("#firstName").val() === "") || ($("#password").val() === "")
        || ($("#phone").val() === "") || (!emailCorrect)) {
        alert('Fill empty lines or correct email');
        return false;
    }
    return true;
}

