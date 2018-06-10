var wrapper = new Object();
var rubric = new Object();

$(window).ready(function () {

    $("#login").click(function () {

            wrapper.email = $("#email").val();
            wrapper.password = $("#password").val();

            validateAuthor();
        }
    );

    $("#registration").click(function () {
        $(location).attr('href', 'http://localhost:9999/Registration.html');
    });

    var input = document.getElementById('password');

    enterOnClick(input);



});

function enterOnClick(element) {

    element.addEventListener('keydown', function (event) {
        if (event.keyCode === 13) {
            $("#login").click();
        }
    });
}


function validateAuthor() {
    fetch('http://localhost:9999/agency/author/validate', {
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        method: 'POST',
        body: JSON.stringify(wrapper)
    })
        .then(function (res) {
            if (res.status === 404) {
                debugger;
                alert('password is wrong')
            }else {
                return res.json();

            }
        })
        .then(function (res) {
            if (res !== undefined) {

                var authorTostore = JSON.stringify(res);
                localStorage.setItem("author", authorTostore);
                $(location).attr('href', 'http://localhost:9999/Site.html?id=1');
            } else {
                alert("password or email is wrong")
            }
        })
}



















