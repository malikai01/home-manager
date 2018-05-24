/**
 * Created by malikai on 2018-5-24.
 */

window.onload = function () {
    try {
        $(parent.document.getElementsByTagName('iframe')[0]).css('height', $('body').height() + 50);
    } catch (e) {
    }
};
var count = 60; //间隔函数，1秒执行
var curCount;//当前剩余秒数
$(function () {
    if (''.length > 0) {
        Util.showMsg('');
    }
    //显示提示
    $('input').focus(function () {
        var ele = $(this);
        if (ele.attr('id') == 'ccode' || ele.attr('id') == 'securityCode') {
            ele = ele.next();
        } else if (ele.attr('id') == 'countrycode') {
            ele = ele.prev();
        }
        ele.next().next().children().eq(1).show();
        ele.next().next().children().eq(2).hide();
        ele.parent().removeClass("newhight");
        ele.parent().removeClass("warn");
    })
    //绑定上传点击事件
    $(".file").click(function () {
        $(this).next().click();
    });
    //font 实现类似 placehol der 的效果
    $('.Tabconter ul li input').focus(function () {
        if ($(this).next().attr('class') != 'file') {
            $(this).parent('li').find('font').hide();
        }
    }).blur(function () {
        if ($(this).val().length == 0) {
            $(this).parent('li').find('font').show();
        }
    })
    $('.Tabconter ul li font').on('click', function () {
        if ($(this).next().attr('class') != 'file') {
            $(this).hide();
            $(this).parent('li').find('input').focus();
        }
    })
    $('.tavlist ul li').on('click', function () {
        var index = $(this).index() + 1;
        $(this).addClass('active').siblings().removeClass("active");
        $('.conter>.Tabconter:nth-child(' + index + ')').show().siblings().hide();
    });
    //ajax 上传文件事件绑定
    //        $(":file").bind("change", ajaxUpload());
    if (false) {
        $(".mobile_validate").hide();
    }
});

function ajaxUpload(ele) {
    var item = $(ele);
    var type = $(ele).attr("id") == 'cardNumberAttach' ? "19" : "20";
    var parent = $(ele).parent();
    var id_input = item.next();
    $.ajaxFileUpload({
        url: '/bsp/register/upload_ajax',
        fileElementId: item.attr("id"),
        data: {
            type: type,
            attachId: item.next().val(),
        },
        dataType: 'json',
        success: function (data, status) {
            if (data.result) {
                var fileName = data.originalFilename;
                if (fileName.length > 20) {
                    fileName = fileName.replace(fileName.substring(7, fileName.length - 6), '...');
                }
                parent.prev().val(fileName);
                id_input.val(data.attachId);
                parent.prev().prev().html("");
                showTips(parent, true);
            } else {
                showTips(parent, false, data.message);
            }
        },
        error: function (data, status, e) {//服务器响应失败处理函数
            if (data.responseText.indexOf("文件过大") > 0) {
                showTips(parent, false, '文件过大,请重新上传文件');
            } else {
                Util.showMsg("系统错误");
            }
        }
    })
}


function sendMessage() {
    if (!getRandomVal()) {
        return;
    }
    curCount = count;
    //设置button效果，开始计时
    $("#btnSendCode").attr("onclick", "null");
    var index;
    index = setInterval(function () {
        if (curCount == 0) {
            $("#btnSendCode").attr("onclick", "sendMessage()");
            $("#btnSendCode").html("重 新 发 送");
            clearInterval(index);
        } else {
            $("#btnSendCode").html(curCount-- + "s后重新发送");
        }
    }, 1000);
}

function getRandomVal() {
    if (!validateInputValue(document.getElementById("mobile"))) {
        return false;
    }
    return Util.sendMessage('/bsp', $("#mobile").val(), $("#ccode").val(), function (d) {
        if (d.message != null) {
            switch (d.message) {
                case "2":
                    showTips($('#ccode'), false, "验证码失效");
                    break;
                case "3":
                    showTips($('#ccode'), false, "验证码超时");
                    break;
                case "4":
                    showTips($('#ccode'), false, "验证码错误 请重新填写");
                    break;
            }
            $("#ccodeImg").click();
            return false;
        } else {
            showTips($('#ccode'), true);
            return true;
        }
    },"");
}

function checkSecurityCode() {
    return Util.checkSecurityCoe('/bsp', $("#securityCode").val(), $("#mobile").val(), function (d) {
        if (!d.isCorrect) {
            showTips($('#securityCode'), false, "手机验证码错误，请重新填写或重新发送手机验证码");
            return false;
        } else {
            showTips($('#securityCode'), true);
            return true;
        }
    })
}


function validateInputValue(ele, isCheckAll) {
    if (isCheckAll || $(ele).attr('id') == 'username') {
        if ($("#username").val().length <= 0) {
            showTips($('#username'), false, "姓名为必填项 请填写");
            return false;
        }
        if (!Util.checkValue($("#username").val(), YUI.Validator.RULES['name12'])) {
            showTips($('#username'), false, "您的名称格式不符,请重新输入填项");
            return false;
        }
        if (Util.checkForbiddenChar('/bsp', $("#username").val())) {
            showTips($('#username'), false, "包含非法字符，请重新输入");
            return false;
        }
        showTips($('#username'), true);
    }

    //证件号码
    if (isCheckAll || $(ele).attr('id') == 'cardNumber') {
        if ($("#cardNumber").val().length <= 0) {
            showTips($('#cardNumber'), false, "身份证号码为必填项，请填写");
            return false;
        }
        if (!Util.checkValue($("#cardNumber").val(), YUI.Validator.RULES['name122'])) {
            showTips($('#cardNumber'), false, "身份证号码格式错误，请正确填写");
            return false;
        }
        var result = Util.ajaxValidate("/bsp/userRegister/checkCarNum", {cardNumber: $('#cardNumber').val()}, function (data) {
            if (data.result) {
                showTips($('#cardNumber'), false, "已有用户使用该身份证注册");
            }
            return data.result;
        }, "json");
        if (!result) {
            showTips($('#cardNumber'), true);
        } else {
            return result;
        }
    }

    //登录账号
    if (isCheckAll || $(ele).attr('id') == 'loginNickName') {
        if ($("#loginNickName").val().length <= 0) {
            showTips($('#loginNickName'), false, "登录账号为必填项");
            return false;
        }
        var reg = /^[uUvV].*$/;
        if (reg.test($('#loginNickName').val())) {
            showTips($('#loginNickName'), false, "登录账号不能以u、U、v、V开头");
            return false;
        }
        if (!isNaN($('#loginNickName').val())) {
            showTips($('#loginNickName'), false, "6-18个字符，由字母、数字或下划线组成");
            return false;
        }
        if (!Util.checkValue($("#loginNickName").val(), YUI.Validator.RULES['username2'])) {
            showTips($('#loginNickName'), false, "6-18个字符，由字母、数字或下划线组成");
            return false;
        }
        if (Util.checkForbiddenChar('/bsp', $("#loginNickName").val())) {
            showTips($('#loginNickName'), false, "包含非法字符，请重新输入");
            return false;
        }
        var result = Util.ajaxValidate("/bsp/userRegister/checkUserName", {username: $('#loginNickName').val()}, function (data) {
            if (data == "success") {
                return true;
            } else if (data == "failure") {
                showTips($('#loginNickName'), false, "对不起，该账号已被注册，请更换账号");
                return false;
            }
        }, "text");
        if (result) {
            showTips($('#loginNickName'), true);
        } else {
            return result;
        }
    }

    //密码
    if (isCheckAll || $(ele).attr('id') == 'password') {
        if ($("#password").val().length <= 0) {
            showTips($('#password'), false, "密码为必填项");
            return false;
        }
        //校验密码是否为组合形式
        if(YUI.Validator.RULES['password2_1'].test($("#password").val()) || YUI.Validator.RULES['password2_2'].test($("#password").val())
            || YUI.Validator.RULES['password2_3'].test($("#password").val())){
            showTips($('#password'), false, "8—20个字符，组合字母、数字或符号(两种或以上)");
            return false;
        }
        if (!Util.checkValue($("#password").val(), YUI.Validator.RULES['password2'], isCheckAll)) {
            showTips($('#password'), false, "8—20个字符，组合字母、数字或符号(两种或以上)");
            return false;
        }
        showTips($('#password'), true);
    }

    //确认密码
    if (isCheckAll || $(ele).attr('id') == 'confirm-password') {
        if (!Util.checkValue($("#confirm-password").val(), null, isCheckAll)) {
            showTips($('#confirm-password'), false, "两次输入密码不一致");
            return false;
        }
        if ($("#confirm-password").val() != $("#password").val()) {
            showTips($('#confirm-password'), false, "两次输入密码不一致");
            return false;
        }
        showTips($('#confirm-password'), true);
    }

    //手机号码
    if (isCheckAll || $(ele).attr('id') == 'mobile') {
        var phone = $("#mobile").val();
        if (phone == null || $.trim(phone) == '') {
            showTips($('#mobile'), false, "请输入手机号码");
            return false;
        }
        var reg = YUI.Validator.RULES['mobile'];//11位数字为手机号码
        if (!reg.test($.trim(phone))) {
            showTips($('#mobile'), false, "手机号码格式不正确");
            return false;
        }
        var data = {mobile: document.getElementById('mobile').value};
        var result = Util.ajaxValidate("/bsp/userRegister/checkMobile", {mobile: $('#mobile').val()}, function (data) {
            if (data.result) {
                showTips($('#mobile'), false, "已有用户使用该手机注册");
            }
            return data.result;
        }, "json");
        if (!result) {
            showTips($('#mobile'), true);
        } else {
            return result;
        }
    }

    //手机验证码
    if ((isCheckAll || $(ele).attr('id') == 'securityCode') && true) {
        if ($("#securityCode").val().length == 0) {
            if (isCheckAll) {
                showTips($('#securityCode'), false, "手机验证码未填写");
            }
            return false
        }
        if (!checkSecurityCode()) {
            return false;
        }
        showTips($('#securityCode'), true);
    }

    //图片验证码
    if ((!isCheckAll && $(ele).attr('id') == 'ccode') && true) {
        if ($("#ccode").val().length == 0) {
            showTips($('#ccode'), false, "验证码未填写");
            return false;
        } else {
            Util.checkCcode('/bsp', $("#ccode").val(), false, function (d) {
                if (!d.isCorrect) {
                    showTips($('#ccode'), false, d.msg);
                    return false;
                } else {
                    showTips($('#ccode'), true);
                    return true;
                }
            });
        }
    }

    //手机验证码
    if ((isCheckAll || $(ele).attr('id') == 'securityCode') && true) {
        if ($("#securityCode").val().length == 0) {
            if (isCheckAll) {
                showTips($('#securityCode'), false, "请输入手机验证码");
                return false
            }
            showTips($('#securityCode'), true);
            return true;
        }
        if (!checkSecurityCode()) {
            return false;
        }
        showTips($('#securityCode'), true);
    }
    return true;
}


function showTips(ele, validateResult, tipValue, showGreenIcon) {
    showGreenIcon = showGreenIcon == null ? true : showGreenIcon;
    if (ele.attr('id') == 'ccode' || ele.attr('id') == 'securityCode') {
        showGreenIcon = false;
        ele = ele.next();
    } else if (ele.attr('class') == 'file') {
        showGreenIcon = false;
    }
    ele.next().next().children().eq(1).hide();
    if (validateResult) {
        if (showGreenIcon) {
            ele.next().show();
        }
        ele.next().next().children().eq(2).hide();
        ele.parent().removeClass("newhight");
        ele.parent().removeClass("warn");
    } else {
        if (showGreenIcon) {
            ele.next().hide();
        }
        ele.next().next().children().eq(2).children("span").html(tipValue);
        ele.next().next().children().eq(2).show();
        ele.parent().addClass("newhight");
        ele.parent().addClass("warn");
    }
}

function submitForm() {
    $("#submitBtn").attr('disabled', 'true');
    if (!validateInputValue(null, true)) {
        $("#submitBtn").removeAttr('disabled');
        return;
    }
    //判断session 缓存中是否有,代注册标记
    var appsourFlag ='';
    if("1"==appsourFlag){
        var url = '/bsp/memberV/recordApplySource?type=4';//代个人注册申请来源 详细见 contants 配置 LOG_APPLYSOURCE_TYPE_4
        var retVal = Util.showModalDialog(url);
        //兼容IE 判断返回值类型
        var arr = retVal.split("||");
        var applySource = "";
        var linkMan = "";
        //alert("arr[0]="+arr[0]+",arr[1]="+arr[1]+",arr[2]="+arr[2]+",arr[3]="+arr[3]);
        if(arr[0]=='0'){ //不是ie 的模式
            //判断是否 取到申请类型 ,没有则报错
            applySource =$("#applySource").val();
            linkMan =$("#linkMan").val();
        }
        if(arr[0]=='1'){//兼容IE
            applySource = arr[2];
            linkMan = arr[3];
            //设置表单
            $("#applySourceType").val(arr[1]);
            $("#applySource").val(applySource)
            $("#linkMan").val(linkMan)
        }
        //alert(applySource+"|+++|");
        if(applySource==""){
            alert("您还未选择申请来源!")
            return;
        }
    }
    $("#registForm").submit();
}

