/*global jQuery*/
var kalaApp = kalaApp || {};

(function(app, $) {
    "use strict";

    var ErrorService = function() {
        var gateway = app.gateway;

        /**
         * Use the done and fail methods of the returned promise
         * example: getErrors().done(function(data) { console.log(data)});
         * @returns {*} jQuery promise with array of all errors
         */
        var getErrors = function (applicationId) {
            // TODO: get errors from server, currently just dummy data
            return gateway.getSubResource("application", applicationId, "errors");
        };

        return {
            getErrors : getErrors
        }
    };

    app.errorService = new ErrorService();
})(kalaApp, jQuery);