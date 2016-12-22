/**
 * Created by yaojiafeng on 16/8/1.
 */

/**
 * Created by yaojiafeng on 16/7/26.
 */

$(function () {

    var page = {
        model: {
            delete: $('.delete')
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

            that.model.delete.on('click',function(){
                return confirm("是否确认删除?");
            });
        }
    };

    page.init();

});
