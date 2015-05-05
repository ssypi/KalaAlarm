/*global jQuery*/

(function($, app) {
    "use strict";

    var emailService = app.emailService;
    var applicationService = app.applicationService;
    var errorService = app.errorService;
    var applicationId = null;

    $(document).ready(function() {
        // Attach click listeners after page has been fully loaded
        $('#addsoftwarebutton').click(function () {
            var name = prompt("Please insert software name");
            $("<li>" + "<button>" + name + "</button>" + "</li>").appendTo('#softwarelist');
        });

        $('#addemailbutton').click(function () {
            var email = prompt("Please insert email");
            emailService.addEmail(email, applicationId).done(updateEmails);
        });

        $("#emaillist").click(".removeEmailButton", function(event) {
            var emailId = event.target.id;
            emailService.deleteEmail(applicationId, emailId).done(updateEmails);
            console.log("Removing email")
        });

        $("#softwarelist").click(".applicationButton", function(event) {
            applicationId = event.target.id;
            updateEmails();
        });

        updateApplications();
    });

    /**
     * Update the contents of the email list
     */
    var updateEmails = function () {
        applicationService.getSubscribers(applicationId).done(function (emails) {
            $('#emaillist').empty();
            emails.forEach(function (email) {
                $('#emaillist').append("<li>" + email.address
                + "<button class='removeEmailButton' id='"
                + email.id + "'>X</button>" + "</li>");
            })
        });
    };

    var updateApplications = function () {
        applicationService.getApplications().done(function (applications) {
            $('#softwarelist').empty();
            applications.forEach(function (application) {
                $('#softwarelist').append("<li>"
                + "<button class='applicationButton' id='"
                + application.id + "'>" + application.name + "</button> </li>");
            })
        })
    };

    var updateErrorHistory = function () {
        errorService.getErrors(applicationId).done(function (errors) {

        });
    };

})(jQuery, kalaApp);





