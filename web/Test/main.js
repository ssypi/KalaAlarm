/**
 * Created by Jussi on 7.5.2015.
 */

/*global jQuery*/

(function($, app) {
    "use strict";

    var gatewayService = app.gateway;

    $(document).ready(function() {
        // Attach click listeners after page has been fully loaded
        $('#errorbutton').click(function () {
            var error = {
                application : {
                    id : 1
                },
                message : "viesti"
            };
            gatewayService.postData("error", error)

        });
    });






})(jQuery, kalaApp);
