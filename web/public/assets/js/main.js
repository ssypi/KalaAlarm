/*global jQuery*/

(function($, app) {
    "use strict";

    var emailService = app.emailService;

    $(document).ready(function() {
        // Attach click listeners after page has been fully loaded
        $('#addsoftwarebutton').click(function () {
            var name = prompt("Please insert software name");
            $("<li>" + "<button>" + name + "</button>" + "</li>").appendTo('#softwarelist');
        });

        $('#addemailbutton').click(function () {
            var email = prompt("Please insert email");
            emailService.addEmail(email).done(updateEmails);
        });

        $("#emaillist").click(".removeEmailButton", function(event) {
            var id = event.target.id;
            emailService.deleteEmail(id);
            console.log("Removing email")
        });

        updateEmails();
    });

    /**
     * Update the contents of the email list
     */
    var updateEmails = function () {
        emailService.getEmails().done(function (emails) {
            $('#emaillist').empty();
            emails.forEach(function (email) {
                $('#emaillist').append("<li>" + email.address
                + "<button class='removeEmailButton' id='"
                + email.id + "'>X</button>" + "</li>");
            })
        });
    };

})(jQuery, kalaApp);





