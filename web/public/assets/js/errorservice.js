/*global jQuery*/
var kalaApp = kalaApp || {};

(function(app, $) {
    "use strict";

    var ErrorService = function() {
        /**
         * Use the done and fail methods of the returned promise
         * example: getErrors().done(function(data) { console.log(data)});
         * @returns {*} jQuery promise with array of all errors
         */
        var getErrors = function () {
            // TODO: get errors from server, currently just dummy data

            var d = $.Deferred();

            var dummyData = [];
            dummyData.push({
                id : 0,
                desc : "Error0",
                app : "app1"
            });
            dummyData.push({
                id : 1,
                desc : "Error1",
                app : "app1"
            });
            dummyData.push({
                id : 2,
                desc : "Error2",
                app : "app2"
            });

            d.resolve(dummyData);

            return d.promise();
        };

        return {
            getErrors : getErrors
        }
    };

    app.errorService = new ErrorService();
})(kalaApp, jQuery);