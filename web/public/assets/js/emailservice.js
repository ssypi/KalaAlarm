/*global jQuery*/
var kalaApp = kalaApp || {};

(function (app, $) {
    "use strict";

    var EmailService = function () {
        var gateway = app.gateway;

        /**
         * Use the done and fail methods of the returned promise
         * example: getEmails().done(function(data) { console.log(data)});
         * @returns {*} jQuery promise with array of all emails
         */
        var getEmails = function () {
            return gateway.getData("email");
        };

        /**
         *
         * @param emailAddress full email address
         * @returns {*} jQuery promise with results
         */
        var addEmail = function (emailAddress, applicationId) {
            console.log("Adding email: " + emailAddress);
            var email = {
                address: emailAddress,
                applicationId: applicationId
            };
            var promise = gateway.postData("email", email);
            return promise;
        };

        var deleteEmail = function (id) {
            console.log("Removing email id: " + id);
            // TODO: send delete request to server through gateway
            return gateway.deleteEmail("email", id);
        };

        return {
            getEmails: getEmails,
            addEmail: addEmail,
            deleteEmail: deleteEmail
        }
    };

    app.emailService = new EmailService();
})(kalaApp, jQuery);