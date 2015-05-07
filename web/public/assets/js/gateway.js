/*global jQuery */
var kalaApp = kalaApp || {};

(function(app, $) {
    "use strict";

    /**
     * API Gateway used for accessing the REST api
     * uses and returns jQuery promises from all methods
     * @constructor
     */
    var Gateway = function() {
        var apiUrl = "http://hotwings.bubu.fi/api/";

        /**
         * Issue a HTTP GET request to a particular resource within the API
         * @param resource the name of the resource like "email" or "application"
         * @returns {*}
         */
        var getData = function(resource) {
            var uri = apiUrl + resource;
            var promise = $.getJSON(uri);
            return promise;
        };

        var getSubResource = function(resource, id, subResource) {
            var uri = apiUrl + resource + "/" + id + "/" + subResource;
            var promise = $.getJSON(uri);
            return promise;
        };

        var postSubResource = function(resource, id, subResource, data) {
            var uri = apiUrl + resource + "/" + id + "/" + subResource;
            var promise = $.ajax({
                type: 'POST',
                url: uri,
                data: JSON.stringify(data),
                contentType: "application/json",
                dataType: 'json'
            });
            return promise;
        };

        var postData = function(resource, data) {
            var uri = apiUrl + resource;
            var promise = $.ajax({
                type: 'POST',
                url: uri,
                data: JSON.stringify(data),
                contentType: "application/json",
                dataType: 'json'
            });
            return promise;
        };

        var deleteSubResource = function(resource, id, subResource, subResourceId) {
            var uri = apiUrl + resource + "/" + id + "/" + subResource + "/" + subResourceId;
            var promise = $.ajax({
                type: 'DELETE',
                url: uri,
                contentType: "application/json"
            });
            return promise;
        };

        var deleteData = function (resource, id) {
            var uri = apiUrl + resource + "/" + id;
            var promise = $.ajax({
                type: 'DELETE',
                url: uri,
                contentType: "application/json"
            });
            return promise;
        };

        return {
            getData : getData,
            getSubResource: getSubResource,
            postData : postData,
            deleteData : deleteData,
            postSubResource: postSubResource,
            deleteSubResource: deleteSubResource
        }
    };

    app.gateway = new Gateway();
})(kalaApp, jQuery);