/**
 * Created by yaojiafeng on 16/8/2.
 */


$(function () {

    var page = {
        model: {
            appId: $('#app_id'),
            methodId: $('#method_id'),
            submit: $('#submit')
        },
        data: {},
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

            that.model.submit.on('click', function () {
                var appId = that.model.appId.val();
                var methodId = that.model.methodId.val();
                if (appId == "0") {
                    alert("请选择应用");
                    return false;
                }
                if (methodId == "0") {
                    alert("请选择方法");
                    return false;
                }
            });

        }
    };

    page.init();

});