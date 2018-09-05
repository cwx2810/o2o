$(function () {

    // 从这个地址获取后台信息
    var initUrl = '/o2o/shopadmin/getshopinitinfo';
    // 从这个地址将前台注册信息发送给后台
    var registerShopUrl = '/o2o/shopadmin/registershop';

    //调试信息，提示我们已经在调取后台信息了，以免我么前台html地址写错了找不到错在哪里
    alert(initUrl);

    // 页面一加载就调用这个方法
    getShopInitInfo();

    function getShopInitInfo() {

        // 获取后台的店铺分类和区域列表，显示到前台
        $.getJSON(initUrl, function (data) {
            if (data.success) {
                var tempHtml = '';
                var tempAreaHtml = '';
                data.shopCategoryList.map(function (item, index) {
                    tempHtml += '<option data-id="' + item.shopCategoryId + '">' + item.shopCategoryName + '</option>';
                });
                data.areaList.map(function (item, index) {
                    tempAreaHtml += '<option data-id="' + item.areaId + '">' + item.areaName + '</option>';
                });
                $('#shop-category').html(tempHtml);
                $('#area').html(tempAreaHtml);
            }
        });

        // 前台提交的注册信息，发送到后台
        $('#submit').click(function () {
            var shop = {};
            // 接收普通信息
            shop.shopName = $('#shop-name').val();
            shop.shopAddr = $('#shop-address').val();
            shop.phone = $('#shop-phone').val();
            shop.shopDesc = $('#shop-desc').val();
            // 接收选择列表信息
            shop.shopCategory = {
                shopCategoryId : $('#shop-category').find('option').not(function() {
                    return !this.selected;
                }).data('id')
            };
            shop.area = {
                areaId : $('#area').find('option').not(function() {
                    return !this.selected;
                }).data('id')
            };
            // 接收图片信息
            var shopImg = $("#shop-img")[0].files[0];
            var formData = new FormData();
            formData.append('shopImg', shopImg);
            formData.append('shopStr', JSON.stringify(shop));
            //提交
            $.ajax({
                url : registerShopUrl,
                type : 'POST',
                // contentType: "application/x-www-form-urlencoded; charset=utf-8",
                data : formData,
                contentType : false,
                processData : false,
                cache : false,
                success : function(data) {
                    // 这里就是我们后台定义的那么多success信息的用处
                    if (data.success) {
                        $.toast('提交成功！');
                    } else {
                        $.toast('提交失败！' + data.errMsg);
                    }
                }
            });

        });
    }
})