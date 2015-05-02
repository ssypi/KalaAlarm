/*global jQuery*/
var kalaApp = kalaApp || {};

(function (app, $) {
    "use strict";

    var EmailService = function () {

        var emails = [];
        var url = "http://169.254.202.214/api/email";

        /**
         * Use the done and fail methods of the returned promise
         * example: getEmails().done(function(data) { console.log(data)});
         * @returns {*} jQuery promise with array of all emails
         */
        var getEmails = function () {
            var json = $.getJSON(url);
            return json;
        };

        var addEmail = function (emailAddress) {
            console.log("Adding email: " + emailAddress);

            var email = {
                address: emailAddress,
                software: "ASD"
            };

            var promise = $.ajax({
                type: 'POST',
                url: url,
                data: JSON.stringify(email),
                contentType: "application/json",
                dataType: 'json'
            });

            return promise;
        };

        var deleteEmail = function (id) {
            console.log("Removing email id: " + id);

            for (var i = emails.length - 1; i >= 0; i--) {
                if (emails[i].id == id) {
                    console.log("Removing email: " + emails[i].address);
                    emails.splice(i, 1);
                }
            }
        };

        return {
            getEmails: getEmails,
            addEmail: addEmail,
            deleteEmail: deleteEmail
        }
    };

    app.emailService = new EmailService();
})(kalaApp, jQuery);