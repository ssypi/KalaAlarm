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
                address: emailAddress
            };
            var promise = gateway.postSubResource("application", applicationId, "subscribers", email);
            return promise;
        };

        var deleteEmail = function (applicationId, emailId) {
            console.log("Removing email from application id: " + applicationId);
            return gateway.deleteSubResource("application", applicationId, "subscribers", emailId);
        };

        return {
            getEmails: getEmails,
            addEmail: addEmail,
            deleteEmail: deleteEmail
        }
    };

    app.emailService = new EmailService();
})(kalaApp, jQuery);