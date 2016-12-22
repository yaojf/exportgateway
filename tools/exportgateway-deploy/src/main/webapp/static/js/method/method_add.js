/**
 * Created by yaojiafeng on 16/8/2.
 */

$(function () {

    var page = {
        model: {
            systemId: $('#system_id'),
            submit: $('#submit')
        },
        data: {
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

            that.model.submit.on('click', function () {
                var systemId = that.model.systemId.val();
                if (systemId == "0") {
                    alert("请选择外部系统");
                    return false;
                }
            });
        }
    };

    page.init();

});