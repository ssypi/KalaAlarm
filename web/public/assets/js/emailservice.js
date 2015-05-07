/*global jQuery*/
var kalaApp = kalaApp || {};

(function (app, $) {
    "use strict";

    var EmailService = function () {
        var gateway = app.gateway;

        /**
         * Checks whether the provided e-mail address is in a proper
         * email format with user@domain.tld
         * @param emailAddress address to check for validity
         * @returns {boolean}
         */
        var isValidEmail = function (emailAddress) {
            var regExp = new RegExp("[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?");
            return (emailAddress.match(regExp) != null);
        };

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
            if (!isValidEmail(emailAddress)) {
                var d = $.Deferred();
                d.reject("Invalid e-mail address.");
                return d.promise();
            }
            var email = {
                address: emailAddress
            };
            var promise = gateway.postSubResource("application", applicationId, "subscribers", email);
            return promise;
        };

        /**
         * Deletes a subscriber from application
         * @param applicationId id of the application to delete from
         * @param emailId id of the email address
         * @returns {*}
         */
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