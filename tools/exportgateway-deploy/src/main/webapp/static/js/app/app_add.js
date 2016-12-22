/**
 * Created by yaojiafeng on 16/7/26.
 */

$(function () {

    var page = {
        model: {
            appId: $('#app_id'),
            appName: $('#app_name'),
            token: $('#token'),
            submit: $('#submit')
        },
        data: {
            appSaveUrl: '/app/save'
        },
        init: function () {
            var that = this;
            that.render();
            that.listen();
        },
        render: function () {
            var that = this;

        },
        listen: function () {
            var that = this;

            //that.model.submit.on('click', function () {
            //    var appId = that.model.appId.val();
            //    var appName = that.model.appName.val();
            //    var token = that.model.token.val();
            //
            //
            //
            //
            //});

        }
    };

    page.init();

});
