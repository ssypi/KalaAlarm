/*global $*/

$('#addsoftwarebutton').click(function () {
    var name = prompt("Please insert software name");
    $("<li>" + "<button>" + name + "</button>" + "</li>").appendTo('#softwarelist');
});

$('#addemailbutton').click(function () {
    var email = prompt("Please insert email");
    kalaApp.emailService.addEmail(email);
    updateEmails();
});

var updateEmails = function () {
    kalaApp.emailService.getEmails().done(function (emails) {
        $('#emaillist').empty();
        emails.forEach(function (email) {
            $('#emaillist').append("<li>" + email.address
            + "<button class='removeEmailButton' id='"
            + email.id + "'>X</button>" + "</li>");
        })
    });
    $('.removeEmailButton').click(function () {
        var id = this.id;
        kalaApp.emailService.deleteEmail(id);
        updateEmails();
    });
};




