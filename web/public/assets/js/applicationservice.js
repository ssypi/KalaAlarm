/*global jQuery*/
var kalaApp = kalaApp || {};

(function (app, $) {
    "use strict";

    var ApplicationService = function () {
        var gateway = app.gateway;

        /**
         * Use the done and fail methods of the returned promise
         * example: getApplications().done(function(data) { console.log(data)});
         * @returns {*} jQuery promise with array of all emails
         */
        var getApplications = function () {
            return gateway.getData("application");
        };

        var getSubscribers = function (applicationId) {
            return gateway.getSubResource("application", applicationId, "subscribers");
        };

        var addApplication = function (applicationName) {
            console.log("Adding application: " + applicationName);
            var application = {
                name: applicationName
            };
            var promise = gateway.postData("application", application);
            return promise;
        };

        return {
            getApplications: getApplications,
            addApplication: addApplication,
            getSubscribers: getSubscribers
        }
    };
    app.applicationService = new ApplicationService();
})(kalaApp, jQuery);